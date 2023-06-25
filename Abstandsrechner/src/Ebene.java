import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ebene {
	List<Integer> StuetsVectorParameter;
	List<Integer> RichtungsVectorParameter1;
	List<Integer> RichtungsVectorParameter2;

	public Ebene() {
		StuetsVectorParameter = new ArrayList<Integer>();
		RichtungsVectorParameter1 = new ArrayList<Integer>();
		RichtungsVectorParameter2 = new ArrayList<Integer>();
	}

	/// Reinkommender String beispiel (1,1,1)+s(3,2,5)+r(1,2,3)
	public void ConvertStringToEbene(String str) {
		
		//Wird benötigt um herrauszufinden ob der erste eintrag ein stütz oder Richtungsvector ist
		Pattern suchpattern = Pattern.compile("[a-zA-Z]");
		String[] einzelneComponenten = str.split("\\)");
		

		for (int i = 0; i < einzelneComponenten.length; i++) {
			String string = einzelneComponenten[i];
			
			Matcher matcher = suchpattern.matcher(string);
			String[] strArray = meinReplace(string).split(",");
			if (!matcher.find()) {

				for (String nummer : strArray) {
					StuetsVectorParameter.add(Integer.parseInt(nummer));
				}
			}else if (matcher.find()&&RichtungsVectorParameter1.isEmpty()) {
				for (String nummer : strArray) {
					nummer = meinReplace(nummer);
					RichtungsVectorParameter1.add(Integer.parseInt(nummer));
				}
			}else {
				for (String nummer : strArray) {
					nummer = meinReplace(nummer);
					RichtungsVectorParameter1.add(Integer.parseInt(nummer));
				}
			}
		}
	}
	private String meinReplace(String str) {
		str = str.replaceAll("[A-Za-z \\(\\)]", "");
		return str;
	}
}