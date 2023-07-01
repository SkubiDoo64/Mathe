import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Gerade gerade = new Gerade();
		gerade.ConvertStringToGerade("(2,1,1)+s(2,0,2)");;
		
		Punkt punkt = new Punkt();
		punkt.ConvertStringToPunkt("(5,2,3)");
		System.out.println(Rechner.BerechneAbstand(punkt,gerade));
		System.out.println(gerade.RichtungsVectorParameter + "\n");
		System.out.println(gerade.StuetsVectorParameter + "\n");
	}
}
