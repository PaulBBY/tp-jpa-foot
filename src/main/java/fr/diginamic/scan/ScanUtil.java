package fr.diginamic.scan;

import java.util.Scanner;

public class ScanUtil {

	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	public static String askString() {
		String stringResult = "";
		System.out.print(">");
		
		while (stringResult.isEmpty()) {
			stringResult = scanner.nextLine().trim();
		}
		return stringResult;

	}

	public static String askLowerString() {
		
		return askString().toLowerCase();

	}
	

}
