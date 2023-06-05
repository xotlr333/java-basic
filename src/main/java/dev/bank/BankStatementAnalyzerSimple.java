package dev.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementAnalyzerSimple {

	private static final String RESOURCES = "src/main/resources/";

	public static void main(String[] args) throws IOException {
		// 1번째 기능 요구사항, 모든 입출금 내역의 총 액수를 계산
		// 파일 읽어들여야함(read)
		final Path path = Paths.get(RESOURCES + "bank-data-simple.txt");

		List<String> lines = Files.readAllLines(path);

//		 System.out.println(lines.get(0));

		// 총 입출금 내역 조회
		// 향상된 forEach 사용

		double total = 0d;// 총 액수를 보관할 변수

		for (String line : lines) {
			final String[] columns = line.split("\t"); // tab으로 구분
			final double amount = Double.parseDouble(columns[1]); // 문자열 -> double 변환

			total += amount; // 총 액수 합산
		}
		System.out.println("총 사용 금액은 " + total + "입니다.");
		
		// 2번째 요구사항 - 특정 월(ex. 1월)의 총 입출금액 조회
		System.out.println("1월의 총 입출금액은" + findTransactionsInJanuary() + "입니다");
		
		// 날짜 타입으로 변환? LocalDate 클래스 사용
	}
	
	// 2번째 요구사항
	private static double findTransactionsInJanuary() throws IOException {
		final Path path = Paths.get(RESOURCES + "bank-data-simple.txt");

		List<String> lines = Files.readAllLines(path);
		double total = 0d;
//		 날짜 타입 포매팅 - 
		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		for (String line : lines) {
			final String[] columns = line.split("\t"); // tab으로 구분
			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN); // 문자열 -> double 변환
			
			if (date.getMonth() == Month.JANUARY) {
				final double amount = Double.parseDouble(columns[1]); // 문자열 -> double 변환

				total += amount; // 총 액수 합산
			}

		}
		return total;
		
	}

}
