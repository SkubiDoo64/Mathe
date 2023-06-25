import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		String[] einzelneComponenten = str.split("\\+");

		for (int i = 0; i < einzelneComponenten.length; i++) {
			einzelneComponenten[i] = einzelneComponenten[i].replaceAll("[A-Za-z \\(\\)]", "");
		}

		for (int i = 0; i < einzelneComponenten.length; i++) {
			String string = einzelneComponenten[i];
			String[] strArray = string.split(",");

			if (i == 0) {
				for (String nummer : strArray) {
					StuetsVectorParameter.add(Integer.parseInt(nummer));
				}
			}
			if (i == 1) {
				for (String nummer : strArray) {
					RichtungsVectorParameter1.add(Integer.parseInt(nummer));
				}
			}
			if (i == 2) {
				for (String nummer : strArray) {
					RichtungsVectorParameter1.add(Integer.parseInt(nummer));
				}
			}
		}

	}
}