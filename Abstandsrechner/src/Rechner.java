import java.util.ArrayList;
import java.util.Iterator;
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

		Vektor ps = VectorSubtraction(schnittpunkt.StuetsVectorParameter, punkt.StuetsVectorParameter);
		return BetragBerechnenVektor(ps);
	}

	public static double BerechneAbstand(Gerade gerade, Gerade gerade2) {

		boolean parallel = ProofVektorVielfachVonEinander(gerade.RichtungsVectorParameter,
				gerade2.RichtungsVectorParameter);
double abstand = 0;
		if (parallel) {
			double skalarprodukt = VectorMultiplikation(gerade.RichtungsVectorParameter, gerade.StuetsVectorParameter);
			for (double wert : gerade2.StuetsVectorParameter.Werte) {
				skalarprodukt = skalarprodukt - wert;
			}
			double x = 0;
			for (int i = 0; i < gerade2.RichtungsVectorParameter.Werte.size(); i++) {
				x = x + gerade.RichtungsVectorParameter.Werte.get(i) * gerade2.RichtungsVectorParameter.Werte.get(i);
			}
			double gleichungsloesung = skalarprodukt / x;

			// gleichungsloesung in die zeite gerade einsetzen um den punkt zu errechnen;
			Punkt paralelerPunkt = new Punkt();

			for (int i = 0; i < gerade2.RichtungsVectorParameter.Werte.size(); i++) {
				paralelerPunkt.StuetsVectorParameter.Werte.add(gerade2.StuetsVectorParameter.Werte.get(i)
						+ gleichungsloesung * gerade2.RichtungsVectorParameter.Werte.get(i));
			}

			Vektor verbindungsVektor = VectorSubtraction(paralelerPunkt.StuetsVectorParameter,
					gerade.StuetsVectorParameter);

			abstand = BetragBerechnenVektor(verbindungsVektor);
		} else {
			
			
			
			
			abstand = 0;
		}
		return abstand;
	}

	public static int BerechneAbstand() {

		return 0;
	}
	public static double BerechneAbstand(Punkt punkt, Gerade gerade) {
//Zunächst muss man das Lotfällen dafür benötigen wir eine Hilfsebene
	Ebene hilfsebene = new Ebene();
	// Die Koeffizienten Der ebenengleichung entnehmen wir dem Richtungsvektoren der gerade
	double x = punkt.StuetsVectorParameter.Werte.get(0);
	double y = punkt.StuetsVectorParameter.Werte.get(1);
	double z = punkt.StuetsVectorParameter.Werte.get(2);
	double r = x * gerade.RichtungsVectorParameter.Werte.get(0) + y * gerade.RichtungsVectorParameter.Werte.get(1) + z * gerade.RichtungsVectorParameter.Werte.get(2);
	
	double lamda = gerade.RichtungsVectorParameter.Werte.get(0) * gerade.RichtungsVectorParameter.Werte.get(0)
				+ gerade.RichtungsVectorParameter.Werte.get(1) * gerade.RichtungsVectorParameter.Werte.get(1)
				+ gerade.RichtungsVectorParameter.Werte.get(2) * gerade.RichtungsVectorParameter.Werte.get(2);  
	double schnittpunktWerte = gerade.RichtungsVectorParameter.Werte.get(0) * gerade.StuetsVectorParameter.Werte.get(0) + gerade.RichtungsVectorParameter.Werte.get(1) * gerade.StuetsVectorParameter.Werte.get(1)  + gerade.RichtungsVectorParameter.Werte.get(2) * gerade.StuetsVectorParameter.Werte.get(2)  ;
	lamda = (r - schnittpunktWerte) / lamda;
	
	Punkt lotfußpunkt = new Punkt();
	for (int i = 0; i<3;i++) {
	double tempWert = gerade.StuetsVectorParameter.Werte.get(i) + gerade.RichtungsVectorParameter.Werte.get(i) * lamda;
		lotfußpunkt.StuetsVectorParameter.Werte.add(tempWert );
		
	}
	Vektor verbindungsVektor = new Vektor();
	for (int i=0;i<3;i++) {
		verbindungsVektor.Werte.add(punkt.StuetsVectorParameter.Werte.get(i) - lotfußpunkt.StuetsVectorParameter.Werte.get(i));
	}
	
	
	return BetragBerechnenVektor(verbindungsVektor);
	} 


	private static boolean ProofVektorVielfachVonEinander(Vektor vektor1, Vektor vektor2) {
		boolean vielfaches = false;

		for (int i = 0; i < vektor1.Werte.size(); i++) {
			double wert1 = vektor1.Werte.get(i);
			double wert2 = vektor2.Werte.get(i);
			if (wert1 == 0 && wert2 == 0) {
				vielfaches = true;
			} else if (wert1 != 0 && wert2 != 0) {
				if (wert1 % wert2 == 0) {
					vielfaches = true;
				} else if (wert2 % wert1 == 0) {
					vielfaches = true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return vielfaches;
	}

	// Ebene und Gerade gleichsetzen.
	private static Punkt BerechneGeradeEbeneSchnittpunkt(Gerade gerade, Ebene ebene) {
		Punkt schnittPunkt = new Punkt();

		/// GausAlgorithmus;

		List<List<Double>> zeilen = new ArrayList<>();

		for (int i = 0; i < gerade.StuetsVectorParameter.Werte.size(); i++) {
			List<Double> neueZeile = new ArrayList<Double>();

			/// vor dem gleichzeichen
			neueZeile.add(ebene.RichtungsVectorParameter1.Werte.get(i));
			neueZeile.add(ebene.RichtungsVectorParameter2.Werte.get(i));
			neueZeile.add(-1 * gerade.RichtungsVectorParameter.Werte.get(i));

			/// hinter dem gleichzeichen
			neueZeile.add(gerade.StuetsVectorParameter.Werte.get(i) - ebene.StuetsVectorParameter.Werte.get(i));

			zeilen.add(neueZeile);
		}

		// wert mit der die zweite zeile multipliziert werden muss
		double wert1 = zeilen.get(2).get(0);

		// wert mit der die dritte zeile mulipliziert werden muss
		double wert2 = zeilen.get(1).get(0);

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
		double punkt3 = zeilen.get(2).get(3) / zeilen.get(2).get(2);
		double punkt2 = zeilen.get(1).get(3) - zeilen.get(1).get(2) * punkt3;
		punkt2 = punkt2 / zeilen.get(1).get(1);
		double punkt1 = zeilen.get(0).get(3) - (zeilen.get(0).get(2) * punkt3 + zeilen.get(0).get(1) * punkt2);
		punkt1 = punkt1 / zeilen.get(0).get(0);

		for (int i = 0; i < gerade.StuetsVectorParameter.Werte.size(); i++) {
			schnittPunkt.StuetsVectorParameter.Werte.add(
					gerade.StuetsVectorParameter.Werte.get(i) + punkt3 * gerade.RichtungsVectorParameter.Werte.get(i));
		}

		// schnittPunkt.StuetsVectorParameter.Werte.add(punkt3);
		// schnittPunkt.StuetsVectorParameter.Werte.add(punkt1);
		// schnittPunkt.StuetsVectorParameter.Werte.add(punkt2);
		return schnittPunkt;
	}

	/// Hier wird der Betrag eines ergebnisses einer Vektor multiplication berechnet
	private static double BetragBerechnen(Vektor vektor) {
		double betrag = 0;

		for (double zahl : vektor.Werte) {
			betrag = betrag + zahl;
		}
		;
		double quadratzahl = betrag * betrag;
		return Math.sqrt(quadratzahl);
	}

	private static double BetragBerechnenVektor(Vektor vektor) {
		double betrag = 0;

		for (double zahl : vektor.Werte) {
			double quadratzahl = zahl * zahl;
			betrag = betrag + quadratzahl;
		}
		;
		return Math.sqrt(betrag);
	}

///Berechnet einen Vektor wenn Zwei Vektoren mit einander Multipliziert werden.
	private static double VectorMultiplikation(Vektor vector1, Vektor vector2) {
		double result = 0;

		for (int i = 0; i < vector1.Werte.size(); i++) {
			result = result + (vector1.Werte.get(i) * vector2.Werte.get(i));
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
			double zwischensumme = hilfsvector1.Werte.get(i + 1) * hilfsvector2.Werte.get(i + 2);
			double zwischensumme2 = hilfsvector1.Werte.get(i + 2) * hilfsvector2.Werte.get(i + 1);

			normalenVector.Werte.add(zwischensumme - zwischensumme2);
		}
		return normalenVector;
	}
}