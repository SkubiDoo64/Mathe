import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Abstand(scanner);

		scanner.close();

	}

	private static void Abstand(Scanner scanner) {
		// falls der Input eine Ebene ist
		System.out.print("Bitte geben Sie ihre erste Eingabe ein:");
		String input = scanner.nextLine();

		Ebene ebene = null;
		Gerade gerade1 = null;
		Gerade gerade2 = null;
		Punkt punkt = null;

		for (int i = 0; i < 2; i++) {
			if (i == 1) {
				System.out.print("Bitte geben Sie ihre nächste Eingabe ein:");
				input = scanner.nextLine();
			}
			// Falls der Input eine Ebene ist
			if (input.matches(
					".{1}[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]|^[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]")) {
				ebene = new Ebene();
				ebene.ConvertStringToEbene(input);

			}
			// falls der Input eine Gerade ist
			else if (input.matches(".{1}[(][0-9,-]+[)]|^[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]")) {
				if (gerade1 == null) {
					gerade1 = new Gerade();
					gerade1.ConvertStringToGerade(input);
				} else {
					gerade2 = new Gerade();
					gerade2.ConvertStringToGerade(input);
				}
			}
			// Falls der Input ein Punkt ist
			else if (input.matches("^[(][0-9,-]+[)]")) {

				punkt = new Punkt();
				punkt.ConvertStringToPunkt(input);
			} else {
				System.out.println("Ungültige Eingabe Festgestellt!");
			}
		}

		if (punkt != null && gerade1 != null) {
			System.out.println(Rechner.BerechneAbstand(punkt, gerade1));
		} else if (punkt != null && ebene != null) {
			System.out.println(Rechner.BerechneAbstand(punkt, ebene));
		} else if (gerade1 != null && gerade2 != null) {
			System.out.println(Rechner.BerechneAbstand(gerade1, gerade2));
		}else {
			System.out.println("Es ist nur möglich den Abstand von:");
			System.out.println("Punkt Gerade");
			System.out.println("Punkt Ebene");
			System.out.println("Gerade Gerade Paralel");
			System.out.println("Gerade Gerade Windschief");
			System.out.println("Berechnen zu lassen.");
			System.out.println("Bitte starten Sie das Programm neu.");
		}

	}
}
