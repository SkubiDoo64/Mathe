import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ebene {
	Vektor StuetsVectorParameter;
	Vektor RichtungsVectorParameter1;
	Vektor RichtungsVectorParameter2;

	public Ebene() {
		StuetsVectorParameter = new Vektor();
		RichtungsVectorParameter1 = new Vektor();
		RichtungsVectorParameter2 = new Vektor();
	}
	
	/// Reinkommender String beispiel (1,1,1)+s(3,2,5)+r(1,2,3)
	public void ConvertStringToEbene(String str) {

		// Wird benötigt um herrauszufinden ob der erste eintrag ein stütz oder
		// Richtungsvector ist
		Pattern suchpattern = Pattern.compile("[a-zA-Z]");
		String[] einzelneComponenten = str.split("\\)");

		for (int i = 0; i < einzelneComponenten.length; i++) {
			String string = einzelneComponenten[i];

			Matcher matcher = suchpattern.matcher(string);
			String[] strArray = meinReplace(string).split(",");
			if (!matcher.find()) {

				for (String nummer : strArray) {
					StuetsVectorParameter.Werte.add(Double.parseDouble(nummer));
				}
			} else if (RichtungsVectorParameter1.Werte.isEmpty()) {
				for (String nummer : strArray) {
					nummer = meinReplace(nummer);
					RichtungsVectorParameter1.Werte.add(Double.parseDouble(nummer));
				}
			} else {
				for (String nummer : strArray) {
					nummer = meinReplace(nummer);
					RichtungsVectorParameter2.Werte.add(Double.parseDouble(nummer));
				}
			}
		}
	}

	private String meinReplace(String str) {
		str = str.replaceAll("[A-Za-z \\(\\)]", "");
		return str;
	}
}