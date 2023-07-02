import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bitte entscheiden Sie sich für eine der Folgenden Optionen:");
		System.out.println("1: Kreuzprodukt Vektor n Dimenionalerraum n>=3");
		System.out.println("2: Abstandsberechning R³");
		System.out.println("Für 1. geben Sie bitte eine \"1\" ein:");
		System.out.println("Für 2. geben Sie bitte eine \"2\" ein:");

		// String entscheidung = scanner.nextLine();
		String entscheidung = "1";
		if (entscheidung.equalsIgnoreCase("1")) {
			Kreuzprodukt(scanner);
		} else if (entscheidung.equals("2")) {
			Abstand(scanner);
		} else {
			System.out.println("Bitte geben Sie eine gültige Eingabe ein");
			System.out.println("und starten Sie dafür das Programm bitte neu.");
		}

		scanner.close();

	}

	private static void Kreuzprodukt(Scanner scanner) {
		System.out.println("In welchem Vektorraum möchten Sie das Kreutzprodukt berechnen Lassen?");
		System.out.print("N>3 :");
		// int vektorraum = Integer.parseInt(scanner.nextLine());
		int vektorraum = 5;
		System.out.println("Bitte geben Sie die Vektoren in der Folgenden Form an: (1,2,3,...,n)");
		List<Vektor> vektoren = new ArrayList<Vektor>();
		for (int i = 0; i < 2 - 1; i++) {
			Punkt punkt = new Punkt();
			punkt.ConvertStringToPunkt("1,2,0,3,5");
			vektoren.add(punkt.StuetsVectorParameter);

			Punkt punkt2 = new Punkt();
			punkt2.ConvertStringToPunkt("3,2,1,2,13");
			vektoren.add(punkt2.StuetsVectorParameter);

			Punkt punkt3 = new Punkt();
			punkt3.ConvertStringToPunkt("2,1,2,5,6");
			vektoren.add(punkt3.StuetsVectorParameter);

			Punkt punkt4 = new Punkt();
			punkt4.ConvertStringToPunkt("5,1,8,3,7");
			vektoren.add(punkt4.StuetsVectorParameter);
		}

		if (vektorraum == 4) {

			System.out.println("Bei einem deminsionalen Vektor werden die Regeln von Sarrus");
			System.out.println("Angewant um die Determinanten zu berechnen:");
			Vektor kreuzVektor = Rechner.BerechneKreuzproduktSarrus(vektoren, vektorraum);
			for (double vektorParameter : kreuzVektor.Werte) {
				System.out.println(vektorParameter);
			}
		} else {
			Vektor kreuzVektor = Rechner.BerechneKreuzproduktLaplacescher(vektoren, vektorraum);
			for (double vektorParameter : kreuzVektor.Werte) {
				System.out.println(vektorParameter);
			}
		}

	}

	private static void Abstand(Scanner scanner) {
		// falls der Input eine Ebene ist
		System.out.println("Bitte geben Sie eine Ebene in dem Folgenden Format an: (0,0,0)+s(0,0,0)+r(0,0,0)");
		System.out.println("Bitte geben Sie eine Gerade in dem Folgenden Format an: (0,0,0)+s(0,0,0)");
		System.out.println("Bitte geben Sie ein Punkt2 in dem Folgenden Format an: (0,0,0)");
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
			System.out.print("Der Abstand beträgt ");
			System.out.print(Rechner.BerechneAbstand(punkt, gerade1));
			System.out.print(" Längeneinheiten.");
		} else if (punkt != null && ebene != null) {
			System.out.print("Der Abstand beträgt ");
			System.out.print(Rechner.BerechneAbstand(punkt, ebene));
			System.out.print(" Längeneinheiten.");
		} else if (gerade1 != null && gerade2 != null) {
			System.out.print("Der Abstand beträgt ");
			System.out.print(Rechner.BerechneAbstand(gerade1, gerade2));
			System.out.print(" Längeneinheiten.");
		} else {
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
