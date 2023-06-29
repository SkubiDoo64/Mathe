import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Punkt:");
		Punkt point = new Punkt();
		point.ConvertStringToPunkt("(6,10,2)");
		
		Ebene ebene = new Ebene();
		ebene.ConvertStringToEbene("(3,1,2)+s(1,1,6)+r(1,0,2)");
		
		System.out.println(Rechner.BerechneAbstand(point, ebene));
	}
}
