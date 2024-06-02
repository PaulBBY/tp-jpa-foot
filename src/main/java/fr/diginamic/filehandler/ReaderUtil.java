package fr.diginamic.filehandler;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

import fr.diginamic.main.MenuUtil;
import fr.diginamic.scan.ScanUtil;

public class ReaderUtil {

	private static final String[] FILES_NAMES = { "/film_realisateurs.csv", "/acteurs.csv", "/castingPrincipal.csv",
			"/films.csv", "/pays.csv", "/realisateurs.csv", "/roles.csv" };

	public static ArrayList<Path> checkFile() {

		String formatedDirectoryPath;
		File directoryString;

		ArrayList<Path> validePaths = new ArrayList<>();

		while (true) {

			System.out.println("Please indicate a valide path for the directory file...");
			try {

				formatedDirectoryPath = ScanUtil.askString().replace("\\", "/");
				directoryString = new File(formatedDirectoryPath);

				if (!directoryString.isDirectory()) {
					System.out.println("It seems like the path indicated does not exist..." + "\n"
							+ "Would you like to retry ?" + "\n" + "Y\\N (exit)");
					Boolean response = MenuUtil.askYN();

					if (!response) {
						MenuUtil.goodBye();

					}

				} else {

					for (String f : FILES_NAMES) {

						StringBuilder testString = new StringBuilder(formatedDirectoryPath).append(f);
						File testFile = new File(testString.toString());

						System.out.println(testString);

						if (!testFile.isFile()) {
							System.out.println("It seems like one or many files indicated does not exist..." + "\n"
									+ "Would you like to retry ?" + "\n" + "Y\\N (exit)");

							Boolean response = MenuUtil.askYN();

							if (!response) {
								MenuUtil.goodBye();

							}
						} else {
							Path validePath = Paths.get(testString.toString());
							validePaths.add(validePath);
						}
					}

					return validePaths;

				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}

	}

	public static List<String[]> loadFile(Path path, int size) {

		List<String[]> returnedLine = new ArrayList<>();

		try {

			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.remove(0);

			String[] lineHas;
			for (String line : lines) {
				lineHas = line.split(";");
				if(lineHas.length == size) {
					returnedLine.add(lineHas);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e + "There was an error. Exiting ...");
			MenuUtil.goodBye();
		}

		return returnedLine;

	}

	public static Float parseFloat(String toParse) {

		try {
			Float parsed = Float.parseFloat(toParse);
			return parsed;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}

	}

	public static Integer parseInt(String toParse) {
		
		try {
			int parsed = Integer.parseInt(toParse);
			return parsed;

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
		
	}

	public static Date parseDate(String toParse) throws ParseException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
			return formatter.parse(toParse.trim());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}

	}
}
