import java.util.ArrayList;
import java.util.List;

public class Rechner {

	/// (1,1,1)+s(3,2,5)+r(1,2,3)
	/// (5,2,1)
	public static int BerechneAbstand(Punkt punkt, Ebene ebene) {

		Vektor normalenVector = BerechneNormalenvectorEbene(ebene);
		Vektor differenzVektor = VectorSubtraction(punkt.StuetsVectorParameter, ebene.StuetsVectorParameter);
		Vektor produktVektor = VectorMultiplikation(normalenVector, differenzVektor);

		return 0;
	}

	public static int BerechneAbstand() {

		return 0;
	}

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