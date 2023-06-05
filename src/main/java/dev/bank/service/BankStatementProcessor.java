package dev.bank.service;

import java.time.Month;
import java.util.List;

import dev.bank.domain.BankTransaction;

// 세 번째 관심사인 입출금 내역을 연산처리하는 역할을 담당하는 클래스
// 비즈니스 로직 -> 일반적으로 service라는 이름의 패키지에 관리
public class BankStatementProcessor {
	
	private List<BankTransaction> bankTransactions;

	public BankStatementProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double calculateTotalAmount() {
		
		double total = 0d;
		for(BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	// 2번째 요구사항 개선 - 1월뿐만 아니라 다른 월도 조회가 가능하도록 개선(메서드 파라미터화, parameterized)
	public double calculateTotalInMonth(Month month) {
		double total = 0d;
	
		for(BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDate().getMonth() == month) {
				total += bankTransaction.getAmount();
			}
		}
		
		return total;
	}
	
	// 카테고리별 총 입출금액을 조회할 수 있는 메소드
	public double calculateTotalForCategory(String description) {
		double total = 0d;
		
		for(BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDescription().equals(description)) {
				total += bankTransaction.getAmount();
			}
		}
		
		return total;
	}
}
