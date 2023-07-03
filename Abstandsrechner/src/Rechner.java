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

	public static double BerechneAbstand(Gerade gerade1, Gerade gerade2) {

		boolean parallel = ProofVektorVielfachVonEinander(gerade1.RichtungsVectorParameter,
				gerade2.RichtungsVectorParameter);
		double abstand = 0;
		if (parallel) {
			double skalarprodukt = VectorMultiplikation(gerade1.RichtungsVectorParameter,
					gerade1.StuetsVectorParameter);
			for (double wert : gerade2.StuetsVectorParameter.Werte) {
				skalarprodukt = skalarprodukt - wert;
			}
			double x = 0;
			for (int i = 0; i < gerade2.RichtungsVectorParameter.Werte.size(); i++) {
				x = x + gerade1.RichtungsVectorParameter.Werte.get(i) * gerade2.RichtungsVectorParameter.Werte.get(i);
			}
			double gleichungsloesung = skalarprodukt / x;

			// gleichungsloesung in die zeite gerade einsetzen um den punkt zu errechnen;
			Punkt paralelerPunkt = new Punkt();

			for (int i = 0; i < gerade2.RichtungsVectorParameter.Werte.size(); i++) {
				paralelerPunkt.StuetsVectorParameter.Werte.add(gerade2.StuetsVectorParameter.Werte.get(i)
						+ gleichungsloesung * gerade2.RichtungsVectorParameter.Werte.get(i));
			}

			Vektor verbindungsVektor = VectorSubtraction(paralelerPunkt.StuetsVectorParameter,
					gerade1.StuetsVectorParameter);

			abstand = BetragBerechnenVektor(verbindungsVektor);
		} else {

			Ebene geradeGH = new Ebene();

			if (PruefeSchnittpunkt(gerade1, gerade2)) {
				return 0;
			}

			for (int i = 0; i < gerade1.RichtungsVectorParameter.Werte.size(); i++) {
				geradeGH.StuetsVectorParameter.Werte
						.add(gerade1.StuetsVectorParameter.Werte.get(i) - gerade2.StuetsVectorParameter.Werte.get(i));
			}
			geradeGH.RichtungsVectorParameter1 = gerade1.RichtungsVectorParameter;
			geradeGH.RichtungsVectorParameter2 = gerade2.RichtungsVectorParameter;

			double linksdergleichung = 0;
			double rechtsdergleichung = 0;
			double x = 0;
			for (int i = 0; i < gerade1.RichtungsVectorParameter.Werte.size(); i++) {
				rechtsdergleichung = rechtsdergleichung + (-1) * (geradeGH.StuetsVectorParameter.Werte.get(i)
						* geradeGH.RichtungsVectorParameter1.Werte.get(i));

				linksdergleichung = linksdergleichung + geradeGH.RichtungsVectorParameter1.Werte.get(i)
						* geradeGH.RichtungsVectorParameter1.Werte.get(i);
				abstand = 0;
			}
			x = rechtsdergleichung / linksdergleichung;
			rechtsdergleichung = 0;
			linksdergleichung = 0;
			double y = 0;
			for (int i = 0; i < gerade1.RichtungsVectorParameter.Werte.size(); i++) {
				rechtsdergleichung = rechtsdergleichung + (-1) * (geradeGH.StuetsVectorParameter.Werte.get(i)
						* geradeGH.RichtungsVectorParameter2.Werte.get(i));

				linksdergleichung = linksdergleichung + (-1) * geradeGH.RichtungsVectorParameter2.Werte.get(i)
						* geradeGH.RichtungsVectorParameter2.Werte.get(i);
				abstand = 0;
			}
			y = rechtsdergleichung / linksdergleichung;

			Vektor vektor = new Vektor();

			for (int i = 0; i < geradeGH.StuetsVectorParameter.Werte.size(); i++) {
				double ergebnis = geradeGH.StuetsVectorParameter.Werte.get(i)
						+ x * geradeGH.RichtungsVectorParameter1.Werte.get(i);
				ergebnis = ergebnis - y * geradeGH.RichtungsVectorParameter2.Werte.get(i);

				vektor.Werte.add(ergebnis);
			}
			abstand = BetragBerechnenVektor(vektor);
		}

		return abstand;

	}

	public static int BerechneAbstand() {

		return 0;
	}

	public static double BerechneAbstand(Punkt punkt, Gerade gerade) {
//Zunächst muss man das Lotfällen dafür benötigen wir eine Hilfsebene
		Ebene hilfsebene = new Ebene();
		// Die Koeffizienten Der ebenengleichung entnehmen wir dem Richtungsvektoren der
		// gerade
		double x = punkt.StuetsVectorParameter.Werte.get(0);
		double y = punkt.StuetsVectorParameter.Werte.get(1);
		double z = punkt.StuetsVectorParameter.Werte.get(2);
		double r = x * gerade.RichtungsVectorParameter.Werte.get(0) + y * gerade.RichtungsVectorParameter.Werte.get(1)
				+ z * gerade.RichtungsVectorParameter.Werte.get(2);

		double lamda = gerade.RichtungsVectorParameter.Werte.get(0) * gerade.RichtungsVectorParameter.Werte.get(0)
				+ gerade.RichtungsVectorParameter.Werte.get(1) * gerade.RichtungsVectorParameter.Werte.get(1)
				+ gerade.RichtungsVectorParameter.Werte.get(2) * gerade.RichtungsVectorParameter.Werte.get(2);
		double schnittpunktWerte = gerade.RichtungsVectorParameter.Werte.get(0)
				* gerade.StuetsVectorParameter.Werte.get(0)
				+ gerade.RichtungsVectorParameter.Werte.get(1) * gerade.StuetsVectorParameter.Werte.get(1)
				+ gerade.RichtungsVectorParameter.Werte.get(2) * gerade.StuetsVectorParameter.Werte.get(2);
		lamda = (r - schnittpunktWerte) / lamda;

		Punkt lotfußpunkt = new Punkt();
		for (int i = 0; i < 3; i++) {
			double tempWert = gerade.StuetsVectorParameter.Werte.get(i)
					+ gerade.RichtungsVectorParameter.Werte.get(i) * lamda;
			lotfußpunkt.StuetsVectorParameter.Werte.add(tempWert);

		}
		Vektor verbindungsVektor = new Vektor();
		for (int i = 0; i < 3; i++) {
			verbindungsVektor.Werte
					.add(punkt.StuetsVectorParameter.Werte.get(i) - lotfußpunkt.StuetsVectorParameter.Werte.get(i));
		}

		return BetragBerechnenVektor(verbindungsVektor);
	}

	private static boolean ProofVektorVielfachVonEinander(Vektor vektor1, Vektor vektor2) {
		boolean vielfaches = false;
		boolean ersterVekotor = true;

		double testWert1 = vektor1.Werte.get(0);
		double testWert2 = vektor1.Werte.get(0);
		if (testWert1 != 0 && testWert2 != 0) {
			if (testWert1 % testWert2 != 0) {
				ersterVekotor = false;
			}
		}

		for (int i = 0; i < vektor1.Werte.size(); i++) {
			double wert1 = vektor1.Werte.get(i);
			double wert2 = vektor2.Werte.get(i);
			if (wert1 == 0 && wert2 == 0) {
				vielfaches = true;
			} else if (wert1 != 0 && wert2 != 0) {
				if (wert1 % wert2 == 0 && ersterVekotor) {
					vielfaches = true;
				} else if (wert2 % wert1 == 0 && !ersterVekotor) {
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
		List<Double> berechnungszeile = new ArrayList<Double>();

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

		berechnungszeile = MapListeToListe(zeilen.get(2));

		// multiplizieren zweite und dritte mit dem wert und subtrahiere dann die dritte
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			zeilen.get(1).set(i, zeilen.get(1).get(i) * wert1);
			berechnungszeile.set(i, berechnungszeile.get(i) * wert2);
			// dritte von der zweiten abziehen;
			zeilen.get(1).set(i, zeilen.get(1).get(i) - berechnungszeile.get(i));
		}

		// wert 3. zeile multiplizieren
		wert1 = zeilen.get(0).get(0);
		// wert 1. zeile multiplizieren
		wert2 = zeilen.get(2).get(0);
		berechnungszeile = MapListeToListe(zeilen.get(0));
		// multiplizieren 1.und 3. mit dem wert und subtrahiere dann die 1.
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			zeilen.get(2).set(i, zeilen.get(2).get(i) * wert1);
			berechnungszeile.set(i, berechnungszeile.get(i) * wert2);
			// dritte von der zweiten abziehen;
			zeilen.get(2).set(i, zeilen.get(2).get(i) - berechnungszeile.get(i));
		}

		// wert 2. zeile multiplizieren
		wert1 = zeilen.get(2).get(1);
		// wert 3. zeile multiplizieren
		wert2 = zeilen.get(1).get(1);

		berechnungszeile = MapListeToListe(zeilen.get(1));
		// multiplizieren 1.und 3. mit dem wert und subtrahiere dann die 2.
		// zeile von der zweiten.
		for (int i = 0; i < zeilen.get(0).size(); i++) {
			berechnungszeile.set(i, berechnungszeile.get(i) * wert1);
			zeilen.get(2).set(i, zeilen.get(2).get(i) * wert2);
			// 2. von der 3. abziehen;
			zeilen.get(2).set(i, zeilen.get(2).get(i) - berechnungszeile.get(i));

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

		return schnittPunkt;
	}

	private static List<Double> MapListeToListe(List<Double> zeilen) {
		List<Double> berechnungszeile = new ArrayList<Double>();
		for (int i = 0; i < zeilen.size(); i++) {
			berechnungszeile.add(zeilen.get(i));
		}
		return berechnungszeile;
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

	public static Vektor BerechneKreuzproduktLaplacescher(List<Vektor> vektoren, int dimensionen) {
		/// Erstellen der Muttermatrix
		Matrix hauptMatrix = new Matrix();
		for (int i = 0; i < dimensionen; i++) {
			hauptMatrix.Matrix.add(new ArrayList<>());
			for (int j = 0; j < vektoren.size(); j++) {
				hauptMatrix.Matrix.get(i).add(vektoren.get(j).Werte.get(i));
			}
		}

		List<Matrix> matrixen = new ArrayList<Matrix>();
		// 4x4 matrix erstellung
		for (int i = 0; i < dimensionen; i++) {
			Matrix neueMatrix = new Matrix();
			for (int a = 0; a < dimensionen; a++) {
				if (i != a) {
					neueMatrix.Matrix.add(hauptMatrix.Matrix.get(a));
				} else {
					if (a % 2 == 0) {
						neueMatrix.Multiplikator = hauptMatrix.Matrix.get(a).get(0);
					} else {
						neueMatrix.Multiplikator = -1 * hauptMatrix.Matrix.get(a).get(0);
					}
				}
			}
			neueMatrix.dimensionen = neueMatrix.Matrix.size();

			matrixen.add(neueMatrix);
		}
		Vektor vektor = new Vektor();
		for (int i = 0; i < matrixen.size(); i++) {
			List<Matrix> determinante = MachKleiner(matrixen.get(i));
			if (i % 2 == 0) {
				vektor.Werte.add(determinante.get(0).determinante);
			} else {
				vektor.Werte.add(-1 * determinante.get(0).determinante);
			}
		}
		return vektor;
	}

	private static List<Matrix> MachKleiner(Matrix mutterMatrix) {
		List<Matrix> neueMatrixen = new ArrayList<Matrix>();

		for (int i = 0; i < mutterMatrix.dimensionen; i++) {
			Matrix neueMatrix = new Matrix();
			for (int a = 0; a < mutterMatrix.dimensionen; a++) {
				List<Double> neueZeile = new ArrayList<Double>();

				if (i != a) {
					for (int j = 1; j < mutterMatrix.dimensionen; j++) {
						neueZeile.add(mutterMatrix.Matrix.get(a).get(j));
					}
					neueMatrix.Matrix.add(neueZeile);
					/// neueMatrix.Matrix.add(mutterMatrix.Matrix.get(a));
				} else {

					if (a % 2 == 0) {
						neueMatrix.Multiplikator = mutterMatrix.Matrix.get(a).get(0);
					} else {
						neueMatrix.Multiplikator = -1 * mutterMatrix.Matrix.get(a).get(0);
					}
				}
			}
			neueMatrix.dimensionen = neueMatrix.Matrix.size();

			neueMatrixen.add(neueMatrix);
		}
		if (neueMatrixen.get(0).Matrix.size() == 2) {
			double determinantenProdukt = 0;
			for (Matrix matrix : neueMatrixen) {
				double determinante = matrix.Matrix.get(0).get(0) * matrix.Matrix.get(1).get(1);
				determinante = determinante - matrix.Matrix.get(1).get(0) * matrix.Matrix.get(0).get(1);
				determinantenProdukt = determinantenProdukt + matrix.Multiplikator * determinante;
			}
			neueMatrixen.clear();
			Matrix matrix = new Matrix();
			matrix.determinante = determinantenProdukt;
			neueMatrixen.add(matrix);
		} else {
			double determinantenProdukt = 0;
			for (Matrix matrix : neueMatrixen) {
				List<Matrix> matrixen = MachKleiner(matrix);
				if (matrixen.size() == 1) {
					determinantenProdukt = determinantenProdukt + (matrix.Multiplikator * matrixen.get(0).determinante);
				}
			}
			neueMatrixen.clear();
			Matrix matrix = new Matrix();
			matrix.determinante = determinantenProdukt;
			neueMatrixen.add(matrix);
		}
		return neueMatrixen;
	}

	public static Vektor BerechneKreuzproduktSarrus(List<Vektor> vektoren, int dimensionen) {
		Vektor kreuzproduktVektor = new Vektor();
		List<List<Double>> vektorMatrix = new ArrayList<List<Double>>();
		List<List<Double>> bigMatrix = new ArrayList<List<Double>>();

		for (int i = 0; i < dimensionen; i++) {
			bigMatrix.add(new ArrayList<Double>());

			for (int a = 0; a < dimensionen - 1; a++) {
				bigMatrix.get(i).add(vektoren.get(a).Werte.get(i));
			}
		}

		for (int i = 0; i < dimensionen; i++) {
			for (int a = 0; a < dimensionen; a++) {
				if (i != a) {
					vektorMatrix.add(bigMatrix.get(a));
				}
			}
		}
		/// Determinante benutzen Regel von Sarrus
		for (int i = 0; i < 3 * (vektorMatrix.size() / 3); i = i + 3) {
			double wert = 1;
			double determinantepos = 0;
			double determinantenegativ = 0;
			for (int a = 0; a < 3; a++) {
				wert = wert * vektorMatrix.get(0 + i).get(a);
				if (a + 1 < 3) {
					wert = wert * vektorMatrix.get(1 + i).get(a + 1);
				} else {
					wert = wert * vektorMatrix.get(1 + i).get(a + 1 - (3));
				}
				if (a + 2 < 3) {
					wert = wert * vektorMatrix.get(2 + i).get(a + 2);
				} else {
					wert = wert * vektorMatrix.get(2 + i).get(a + 2 - (3));
				}
				determinantepos = determinantepos + wert;
				wert = 1;
			}
			wert = 1;
			for (int a = 0; a < 3; a++) {
				wert = wert * vektorMatrix.get(2 + i).get(a);

				if (a + 1 < dimensionen - 1) {
					wert = wert * vektorMatrix.get(1 + i).get(a + 1);
				} else {
					wert = wert * vektorMatrix.get(1 + i).get(a + 1 - (3));
				}

				if (a + 2 < dimensionen - 1) {
					wert = wert * vektorMatrix.get(0 + i).get(a + 2);
				} else {
					wert = wert * vektorMatrix.get(0 + i).get(a + 2 - (3));
				}
				determinantenegativ = determinantenegativ + (-1 * wert);
				wert = 1;
			}
			wert = 1;
			if ((i / 3) % 2 == 0) {
				kreuzproduktVektor.Werte.add(determinantepos + determinantenegativ);
			} else {
				kreuzproduktVektor.Werte.add(-1 * (determinantepos + determinantenegativ));
			}
		}

		return kreuzproduktVektor;
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

	private static boolean PruefeSchnittpunkt(Gerade gerade1, Gerade gerade2) {
		List<List<Double>> gleichungen = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			gleichungen.add(new ArrayList<Double>());
			gleichungen.get(i).add(gerade1.RichtungsVectorParameter.Werte.get(i));
			gleichungen.get(i).add(gerade2.RichtungsVectorParameter.Werte.get(i));
			double einzelwert = gerade2.StuetsVectorParameter.Werte.get(i) - gerade1.StuetsVectorParameter.Werte.get(i);
			gleichungen.get(i).add(einzelwert);
		}
		
		List<Double> zwischengleichung = new ArrayList<>();		
		for (int i = 0; i < 3; i++) {
			zwischengleichung.add(gleichungen.get(1).get(i));
		}

		double wert1 = gleichungen.get(0).get(1);
		double wert2 = gleichungen.get(1).get(1);
		
		for (int i = 0; i < 3; i++) {
			gleichungen.get(0).set(i,gleichungen.get(0).get(i) * wert2);
			zwischengleichung.set(i,zwischengleichung.get(i) * wert1);
			
			gleichungen.get(0).set(i, gleichungen.get(0).get(i)- zwischengleichung.get(i));
		}
		double r = gleichungen.get(0).get(0) / gleichungen.get(0).get(2);
		double s = gleichungen.get(1).get(0) * r - gleichungen.get(1).get(2);
		s = s / gleichungen.get(1).get(1);
		
		if(r == s) {
			return true;
		}
		
		return false;
	}
}