import java.util.regex.*;

public class Patterns {
    private StringBuffer binElements = new StringBuffer();
    private int kol = 0;

    void Patterns(){}

    public void check(String arg){
        if(Pattern.matches("^([0-9a-fA-F]+)$", arg)){
            setBinElement(arg);
            kol++;
            System.out.println(" - number");
        } else System.out.println(" - string");
    }

    public void setBinElement(String arg){
        int number = Integer.parseInt(arg, 16);
        binElements.append(Integer.toBinaryString(number) + ' ');
    }

    public void print(){
        System.out.println("All binary numbers: " + kol + "\n" + binElements);
    }

    public static void main(String[] args){
        Patterns object = new Patterns();
        System.out.println("Number of elements: " + args.length + "\nAll elements:");
        for(int i = 0; i < args.length; i++){
            System.out.print(args[i]);
            object.check(args[i]);
        }
        object.print();
    }
}
