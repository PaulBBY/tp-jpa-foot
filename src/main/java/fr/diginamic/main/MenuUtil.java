package fr.diginamic.main;

import fr.diginamic.scan.ScanUtil;

public class MenuUtil {
	
	public static void goodBye () {
		System.out.println("Good Bye");
		System.exit(0);
	}
	
	public static boolean askYN () {
		
		while(true) {

			String yesOrNo = ScanUtil.askLowerString();
			
			if (yesOrNo.equals("n") || yesOrNo.equals("no")) {
				return false;
			} else if (yesOrNo.equals("y") || yesOrNo.equals("ye") || yesOrNo.equals("yes")) {
				return true;
			} else {
				System.out.println("I did not understood your response. Please retry");
			}
		}
	}

}
