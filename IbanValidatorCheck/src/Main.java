
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		IbanValidator validator = new IbanValidator();

		System.out.println("Hello, this is IBAN Validator app.");
		System.out.println("Press 0 to exit this app");
		System.out.println("Press 1 for interactive IBAN checking ");
		System.out.println("Press 2 for IBAN checking from a file");

		String menu = scanner.nextLine();
		switch (menu) {
		case "0":
			System.out.println("Thank you! See you later!");
			break;
		case "1":
			System.out.println("Enter IBAN Nr:");
			String ibanInput = scanner.nextLine();
			if (validator.validator(ibanInput)) {
				System.out.println("YES, It is an IBAN ");
			} else {
				System.out.println("NO, It is not an IBAN ");
			}
			break;

		case "2":
			System.out.println("Insert path:");
			String path = scanner.nextLine();

			System.out.print("Enter file name without [.txt] extension:");
			String file = scanner.nextLine();

			String outf = "check.out";

			String fullName = path + file;

			BufferedReader in = null;
			BufferedWriter out = null;

			String[] user = new String[30];
			String line = "";
			try {
				in = new BufferedReader(new FileReader(fullName + ".txt"));

				out = new BufferedWriter(new FileWriter(fullName + ".out"));

				while ((line = in.readLine()) != null) {
					if (validator.validator(line)) {
						System.out.println(line + ";true" + "\n");
						out.write(line + ";true" + "\n");
					} else {
						System.out.println(line + ";false" + "\n");
						out.write(line + ";false" + "\n");
					}
				}
			} catch (FileNotFoundException ex) {
				System.out.println("File does not exist");
			} finally {

				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
			System.out.println("File is created in directory:" + fullName + ".out");

		default:
			System.out.println("You should choose from 0, 1 or 2");
		}
	}

}
