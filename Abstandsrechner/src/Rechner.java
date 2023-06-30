import java.util.ArrayList;
import java.util.List;

public class Rechner {

	/// (1,1,1)+s(3,2,5)+r(1,2,3)
	/// (5,2,1)
	public static double BerechneAbstand(Punkt punkt, Ebene ebene) {

		Vektor normalenVector = BerechneNormalenvectorEbene(ebene);

		Gerade hilfsgerade = new Gerade();
		hilfsgerade.StuetsVectorParameter = punkt.StuetsVectorParameter;
		hilfsgerade.RichtungsVectorParameter = normalenVector;

		Punkt schnittpunkt = BerechneGeradeEbeneSchnittpunkt(hilfsgerade, ebene);

		Vektor differenzVektor = VectorSubtraction(punkt.StuetsVectorParameter, ebene.StuetsVectorParameter);
		Vektor produktVektor = VectorMultiplikation(normalenVector, differenzVektor);

		double result = BetragBerechnen(produktVektor) / BetragBerechnenVektor(normalenVector);
		return result;
	}

	public static int BerechneAbstand() {

		return 0;
	}

	// Ebene und Gerade gleichsetzen.
	private static Punkt BerechneGeradeEbeneSchnittpunkt(Gerade gerade, Ebene ebene) {
		Punkt schnittPunkt = new Punkt();

		/// GausAlgorithmus;

		List<List<Integer>> zeilen = new ArrayList<>();

		for (int i = 0; i < gerade.StuetsVectorParameter.Werte.size(); i++) {
			List<Integer> neueZeile = new ArrayList<Integer>();

			/// vor dem gleichzeichen
			neueZeile.add(ebene.RichtungsVectorParameter1.Werte.get(i));
			neueZeile.add(ebene.RichtungsVectorParameter2.Werte.get(i));
			neueZeile.add(-1 * gerade.RichtungsVectorParameter.Werte.get(i));

			/// hinter dem gleichzeichen
			neueZeile.add(gerade.StuetsVectorParameter.Werte.get(i) - ebene.StuetsVectorParameter.Werte.get(i));

			zeilen.add(neueZeile);
		}

		// wert mit der die zweite zeile multipliziert werden muss
		int wert1 = zeilen.get(2).get(0);

		// wert mit der die dritte zeile mulipliziert werden muss
		int wert2 = zeilen.get(1).get(0);

		// multiplizieren zweite und dritte mit dem wert und subtrahiere dann die dritte
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			zeilen.get(1).set(i, zeilen.get(1).get(i) * wert1);
			zeilen.get(2).set(i, zeilen.get(2).get(i) * wert2);
			// dritte von der zweiten abziehen;
			zeilen.get(1).set(i, zeilen.get(1).get(i) - zeilen.get(2).get(i));

			// zurücksetzen der 3. zeile
			zeilen.get(2).set(i, zeilen.get(2).get(i) / wert2);
		}

		// wert 3. zeile multiplizieren
		wert1 = zeilen.get(0).get(0);
		// wert 1. zeile multiplizieren
		wert2 = zeilen.get(2).get(0);

		// multiplizieren 1.und 3. mit dem wert und subtrahiere dann die 1.
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			zeilen.get(2).set(i, zeilen.get(2).get(i) * wert1);
			zeilen.get(0).set(i, zeilen.get(0).get(i) * wert2);
			// dritte von der zweiten abziehen;
			zeilen.get(2).set(i, zeilen.get(2).get(i) - zeilen.get(0).get(i));

			// zurücksetzen der 3. zeile
			zeilen.get(0).set(i, zeilen.get(0).get(i) / wert2);
		}

		// wert 2. zeile multiplizieren
		wert1 = zeilen.get(2).get(1);
		// wert 3. zeile multiplizieren
		wert2 = zeilen.get(1).get(1);

		// multiplizieren 1.und 3. mit dem wert und subtrahiere dann die 2.
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			zeilen.get(1).set(i, zeilen.get(1).get(i) * wert1);
			zeilen.get(2).set(i, zeilen.get(2).get(i) * wert2);
			// 2. von der 3. abziehen;
			zeilen.get(2).set(i, zeilen.get(2).get(i) - zeilen.get(1).get(i));

			// zurücksetzen der 3. zeile
			zeilen.get(1).set(i, zeilen.get(1).get(i) / wert2);
		}

		// Punkte berechnen:
		// 3. punkt
		int punkt3 = zeilen.get(2).get(3) / zeilen.get(2).get(2);
		int punkt2 = zeilen.get(1).get(3) - zeilen.get(1).get(2) * punkt3;
		punkt2 = punkt2 / zeilen.get(1).get(1);
		int punkt1 = zeilen.get(0).get(3)-(zeilen.get(0).get(2)*punkt3 +zeilen.get(0).get(1)*punkt2);
		punkt1 = punkt1 / zeilen.get(0).get(0);
		
		for (int i = 0; i < gerade.StuetsVectorParameter.Werte.size(); i++) {			
			schnittPunkt.StuetsVectorParameter.Werte.add(gerade.StuetsVectorParameter.Werte.get(i)+punkt3* gerade.RichtungsVectorParameter.Werte.get(i));
		}
		
		//schnittPunkt.StuetsVectorParameter.Werte.add(punkt3);
		//schnittPunkt.StuetsVectorParameter.Werte.add(punkt1);
		//schnittPunkt.StuetsVectorParameter.Werte.add(punkt2);
		return schnittPunkt;
	}

	/// Hier wird der Betrag eines ergebnisses einer Vektor multiplication berechnet
	private static double BetragBerechnen(Vektor vektor) {
		double betrag = 0;

		for (int zahl : vektor.Werte) {
			betrag = betrag + zahl;
		}
		;
		double quadratzahl = betrag * betrag;
		return Math.sqrt(quadratzahl);
	}

	private static double BetragBerechnenVektor(Vektor vektor) {
		double betrag = 0;

		for (int zahl : vektor.Werte) {
			double quadratzahl = zahl * zahl;
			betrag = betrag + quadratzahl;
		}
		;
		return Math.sqrt(betrag);
	}

///Berechnet einen Vektor wenn Zwei Vektoren mit einander Multipliziert werden.
	private static Vektor VectorMultiplikation(Vektor vector1, Vektor vector2) {
		Vektor result = new Vektor();

		for (int i = 0; i < vector1.Werte.size(); i++) {
			result.Werte.add(vector1.Werte.get(i) * vector2.Werte.get(i));
		}

		return result;
	}

	private static Vektor VectorSubtraction(Vektor vector1, Vektor vector2) {
		Vektor result = new Vektor();

		for (int i = 0; i < vector1.Werte.size(); i++) {
			result.Werte.add(vector1.Werte.get(i) - vector2.Werte.get(i));
		}

		return result;
	}

	private static Vektor BerechneNormalenvectorEbene(Ebene ebene) {
		Vektor normalenVector = new Vektor();
		Vektor hilfsvector1 = new Vektor();
		Vektor hilfsvector2 = new Vektor();

		int groeße = ebene.RichtungsVectorParameter1.Werte.size();

		for (int i = 0; i < groeße * 2; i++) {
			if (i < ebene.RichtungsVectorParameter1.Werte.size()) {
				hilfsvector1.Werte.add(ebene.RichtungsVectorParameter1.Werte.get(i));
				hilfsvector2.Werte.add(ebene.RichtungsVectorParameter2.Werte.get(i));
			} else {
				hilfsvector1.Werte.add(ebene.RichtungsVectorParameter1.Werte.get(i - groeße));
				hilfsvector2.Werte.add(ebene.RichtungsVectorParameter2.Werte.get(i - groeße));
			}
		}

		for (int i = 0; i < groeße; i++) {
			int zwischensumme = hilfsvector1.Werte.get(i + 1) * hilfsvector2.Werte.get(i + 2);
			int zwischensumme2 = hilfsvector1.Werte.get(i + 2) * hilfsvector2.Werte.get(i + 1);

			normalenVector.Werte.add(zwischensumme - zwischensumme2);
		}
		return normalenVector;
	}
}