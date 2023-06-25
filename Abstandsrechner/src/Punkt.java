import java.util.ArrayList;
import java.util.List;

public class Punkt {
	List<Integer> StuetsVectorParameter;

	public Punkt()
	{
		StuetsVectorParameter = new ArrayList<Integer>();
	}
	
	public void ConvertStringToPunkt(String str) {
		str = str.replace("(","");
		str = str.replace(")","");
		
		String[] strArray = str.split(",");
		
		for (String nummer : strArray) {			
			StuetsVectorParameter.add(Integer.parseInt(nummer));
		}
	}
}