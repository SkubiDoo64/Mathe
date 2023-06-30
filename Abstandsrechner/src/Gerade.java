import java.util.ArrayList;
import java.util.List;

public class Gerade {
	Vektor StuetsVectorParameter;
	Vektor RichtungsVectorParameter;

	public Gerade() {

		StuetsVectorParameter = new Vektor();
		RichtungsVectorParameter = new Vektor();
	}

	// ein String Beispiel "(1,1,1)+s(3,2,5)"
	// Weitere Test Inputs "p(0,8,15)
	public void ConvertStringToPunkt(String str) {
		String workStr = "";
		// Prüfen pb ein Ortsvektor Vorhanden ist
		if (str.matches("^[(][0-9,]+[)].+")) {
			// Wenn ja StuetzVektorParameter = erstes (***)
			int klammerEnde = str.indexOf(")");
			workStr = str.substring(0, klammerEnde);

			workStr = workStr.replace("(", "");
			workStr = workStr.replace(")", "");

			String[] strArray = workStr.split(",");

			for (String nummer : strArray) {
				nummer = nummer.replace(",", "");

				StuetsVectorParameter.Werte.add(Double.parseDouble(nummer));

			}

			// Stützvektor aus dem String entfernen
			workStr = str.substring((klammerEnde + 1));
			int klammerAnfang = workStr.indexOf("(");
			klammerEnde = workStr.indexOf(")");

			workStr = workStr.substring(klammerAnfang, klammerEnde);
			workStr = workStr.replace("(", "");
			workStr = workStr.replace(")", "");
			strArray = workStr.split(",");
			for (String nummer : strArray) {
				RichtungsVectorParameter.Werte.add(Double.parseDouble(nummer));

			}
		} else if (str.matches(".+[(][0-9,]+[)].*")) {
			// wenn nein StuetzVektorParameter = (0,0,0)

			workStr = "0,0,0";
			String[] strArray = workStr.split(",");

			for (String nummer : strArray) {
				StuetsVectorParameter.Werte.add(Double.parseDouble(nummer));

			}
			;
			int klammerEnde = str.indexOf(")");
			int klammerAnfang = str.indexOf("(");
			workStr = str.substring(klammerAnfang, klammerEnde);
			workStr = workStr.replace("(", "");
			workStr = workStr.replace(")", "");
			strArray = workStr.split(",");
			for (String nummer : strArray) {
				RichtungsVectorParameter.Werte.add(Double.parseDouble(nummer));

			}

		} else {
			System.out.print("\n Die Eingegebenen Werte konnten nicht verarbeitet werden! \n");
		}

	}
}