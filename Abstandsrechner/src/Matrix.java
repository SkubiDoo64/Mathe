import java.util.ArrayList;
import java.util.List;

public class Matrix {
	public List<List<Double>> Matrix;
	public double Multiplikator;
	public double dimensionen;
	public double determinante;
	
	public Matrix() {
		Matrix = new ArrayList<List<Double>>();
		Multiplikator = 0;
		determinante = 0;
	}
}