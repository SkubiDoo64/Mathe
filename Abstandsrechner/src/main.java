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
		
		Gerade gerade1 = new Gerade();
		Gerade gerade2 = new Gerade();
		
		gerade1.ConvertStringToPunkt("(2,0,1)+s(-4,1,1)");
		gerade2.ConvertStringToPunkt("(0,5,6)+s(8,2,2)");
		
		Rechner.BerechneAbstand(gerade1, gerade2);
		
		scanner.close();
	}
}
