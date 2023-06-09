package com.ssafy.moneykeeperbackend.statistics.repository;

import com.ssafy.moneykeeperbackend.accountbook.entity.IncomeClassification;
import com.ssafy.moneykeeperbackend.member.entity.Member;
import com.ssafy.moneykeeperbackend.statistics.entity.IncomeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeGroupRepository extends JpaRepository<IncomeGroup, Long> {
    List<IncomeGroup> findAllByOrderByBelowAsc();

    List<IncomeClassification> findByMember(Member member);
}
