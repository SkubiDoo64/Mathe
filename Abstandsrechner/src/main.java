import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Punkt:");
		Punkt point = new Punkt();
		point.ConvertStringToPunkt("(6,0,3)");
		
		Ebene ebene = new Ebene();
		ebene.ConvertStringToEbene("(0,2,4)+s(2,2,4)+r(1,0,-1)");
		
		System.out.println(Rechner.BerechneAbstand(point, ebene));
	}
}
