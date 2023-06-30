import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Gerade gerade = new Gerade();
		gerade.ConvertStringToGerade("(1,1,1)+s(3,2,5)");;
		
		System.out.println(gerade.RichtungsVectorParameter + "\n");
		System.out.println(gerade.StuetsVectorParameter + "\n");
	}
}
