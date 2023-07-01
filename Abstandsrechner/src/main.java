import java.util.Scanner;

public class main {
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//falls der Input  eine Ebene ist
		String input = scanner.nextLine();
		
		Ebene ebene = null;
		Gerade gerade1 = null;
		Gerade gerade2 = null;
		Punkt punkt = null;
		//Falls der Input eine Ebene ist
		if(input.matches(".{1}[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]|^[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]")) {
			 ebene = new Ebene();
			ebene.ConvertStringToEbene(input);
			System.out.println(" Eingabe ist eine Ebene!");
			
		}
		//falls der Input eine Gerade ist
		else if(input.matches(".{1}[(][0-9,-]+[)]|^[(][0-9,-]+[)][+-].{1}[(][0-9,-]+[)]")){
			if(gerade1 == null) {
				gerade1 = new Gerade();
				gerade1.ConvertStringToGerade(input);
				System.out.println(" Eingabe ist eine Gerade!");
			}else {
				gerade2 = new Gerade();
				gerade2.ConvertStringToGerade(input);
				System.out.println(" Eingabe ist eine Gerade!");
			}
		}
		//Falls der Input ein Punkt ist
		else if(input.matches("^[(][0-9,-]+[)]")){
			
			Punkt inputObjekt = new Punkt();
			inputObjekt.ConvertStringToPunkt(input);
			System.out.println(" Eingabe ist ein Punkt!");

			
		}else {
			System.out.println("Ungültige Eingabe Festgestellt!");
		}
		
	
		
		
		
	
		scanner.close();
	

	}
}
