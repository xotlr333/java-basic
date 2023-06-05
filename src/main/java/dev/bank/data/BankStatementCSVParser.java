package dev.bank.data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.domain.BankTransaction;

public class BankStatementCSVParser implements BankStatementParser{
	
	final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	@Override
	public BankTransaction parseFrom(String line) {
		final String[] columns = line.split(",");
		
		double amount = Double.parseDouble(columns[1]);
		LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		String description = columns[2];
		
		return new BankTransaction(date, amount, description);
	}
	
	@Override
	public List<BankTransaction> parseLinesFrom(List<String> lines) {
		List<BankTransaction> list = new ArrayList<>();
		
		for(String line : lines) {
			list.add(parseFrom(line));
		}
		
		return list;
		
	}

	
}
