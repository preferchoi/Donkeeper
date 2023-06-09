package com.ssafy.moneykeeperbackend.accountbook.service;

import java.util.List;

import com.ssafy.moneykeeperbackend.accountbook.dto.BudgetDTO;
import com.ssafy.moneykeeperbackend.member.entity.Member;

public interface BudgetService {

	BudgetDTO addMonthBudget(BudgetDTO budgetDTO, Member member);

	BudgetDTO getMonthBudget(Member member, int year, int month, Long majorSpendingClassificationId);

	List<BudgetDTO> getMonthAllBudget(Member member, int year, int month);

	BudgetDTO updateBudget(Member member, BudgetDTO budgetDTO);

	void deleteBudget(Member member, int year, int month, Long majorSpendingClassificationId);
}
