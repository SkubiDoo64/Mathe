import java.util.ArrayList;
import java.util.List;

public class Punkt {
	Vektor StuetsVectorParameter;
	
	public Punkt() {
		StuetsVectorParameter = new Vektor();
	}
	public void ConvertStringToPunkt(String str) {
		str = str.replace("(","");
		str = str.replace(")","");
		
		String[] strArray = str.split(",");
		
		for (String nummer : strArray) {			
			StuetsVectorParameter.Werte.add(Double.parseDouble(nummer));
		}
	}
}