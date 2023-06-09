package com.ssafy.moneykeeperbackend.accountbook.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moneykeeperbackend.accountbook.dto.request.IncomeRequest;
import com.ssafy.moneykeeperbackend.accountbook.dto.request.SpendingRequest;
import com.ssafy.moneykeeperbackend.accountbook.dto.response.IncomeResponse;
import com.ssafy.moneykeeperbackend.accountbook.dto.response.SpendingResponse;
import com.ssafy.moneykeeperbackend.member.entity.Member;

public interface IncomeService {

	IncomeResponse addIncomeRecord(IncomeRequest incomeRequest, Member member);

	List<IncomeResponse> getAllIncome(Member member, Pageable pageable);

	List<IncomeResponse> getMonthIncome(Member member, int year, int month);

	IncomeResponse getDetailIncome(Member member, Long incomeId);

	int getMonthIncomeAmount(Member member, int year, int month);

	IncomeResponse updateIncome(Member member, Long incomeId, IncomeRequest incomeRequest);

	void deleteIncome(Long incomeId);


}
