package dev.bank.data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.domain.BankTransaction;

//data패키지 - 외부 데이터와 관련된 역할을 하는 패키지

// 읽어들인 TSV(.txt) 파일을 Java 프로그램에서 사용할 수 있도록 변환해주는 처리
public class BankStatementTSVParser implements BankStatementParser{
	
	final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	// 한 줄만 파싱하는 기능
	// 이 메소드는 이 클래스에서만 사용할 계정
	@Override
	public BankTransaction parseFrom(final String line) {
		final String[] columns = line.split("\t");
		
		double amount = Double.parseDouble(columns[1]);
		LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		String description = columns[2];
		
		return new BankTransaction(date, amount, description);
	}
	
	// 한 줄씩 파싱된 데이터를 리스트에 추가
	// 이 메소드는 외부에서 호출 가능하도록
	@Override
	public List<BankTransaction> parseLinesFrom(List<String> lines) {
		List<BankTransaction> list = new ArrayList<>();
		
		for(String line : lines) {
			list.add(parseFrom(line));
		}
		
		return list;
		
	}
	
}
