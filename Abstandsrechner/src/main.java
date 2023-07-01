import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Gerade gerade = new Gerade();
		gerade.ConvertStringToGerade("(2,1,1)+s(2,0,2)");;
		
		Punkt punkt = new Punkt();
		punkt.ConvertStringToPunkt("(5,2,3)");
		System.out.println(Rechner.BerechneAbstand(punkt,gerade));
		
		Punkt punkt2 = new Punkt();
		punkt2.ConvertStringToPunkt("(6,10,2)");
		Ebene ebene = new Ebene();
		ebene.ConvertStringToEbene("(3,1,2)+s(1,1,6)+r(1,0,2)");
		
		System.out.println(Rechner.BerechneAbstand(punkt2, ebene));
		
		Gerade gerade1 = new Gerade();
		Gerade gerade2 = new Gerade();
		
		gerade1.ConvertStringToGerade("(2,0,1)+s(-4,1,1)");
		gerade2.ConvertStringToGerade("(0,5,6)+s(-8,2,2)");
		
		System.out.print(Rechner.BerechneAbstand(gerade1, gerade2));

		gerade1.ConvertStringToGerade("(2,1,1)+s(1,1,0)");
		gerade2.ConvertStringToGerade("(2,0,2)+s(2,0,1)");
		System.out.print(Rechner.BerechneAbstand(gerade1, gerade2));
		
		scanner.close();
	

	}
}
