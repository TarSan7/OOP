import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;
import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        Scanner in = new Scanner(System.in);
        InputStreamReader inp = new InputStreamReader(System.in);
        System.out.println("Enter name of rhomb, please: ");
        String name = in.nextLine();
        System.out.println("Enter 2 radiuses, please: ");
        double rad1 = in.nextDouble(), rad2 = in.nextDouble();
        System.out.println("Enter koordinates of center, please: ");
        double x0 = in.nextDouble(), y0 = in.nextDouble();

        Rhomb object = new Rhomb(name, rad1, rad2, x0,y0);
        object.setArea();
        System.out.println("Name of the rhomb : " + object.getName());
        System.out.println("Area of this rhomb : " + nf.format(object.getArea()));
        System.out.println("If you want to find height (h) or side (s) or perimeter (p), enter appropriate symbol.");
        int symbol = inp.read();
        switch (symbol){
            case 'h':
                System.out.println("Height of the rhomb: " + nf.format(object.height()));
                break;
            case 's':
                System.out.println("Side of the rhomb: " + nf.format(object.side()));
                break;
            case 'p':
                System.out.println("Perimeter of the rhomb: " + nf.format(object.perimeter()));
                break;
            default: break;
        }
        System.out.println("Length from center of rhomb to center of koord: " + nf.format(object.lenthFromCen()));
        System.out.println("Length from center of rhomb to point: " + nf.format(object.lenthFromCen(1, 1)));
    }
}
