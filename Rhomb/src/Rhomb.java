public class Rhomb {
    String name;
    double diag1, diag2;
    double x0, y0;
    double area;

    public Rhomb() {}

    public Rhomb(String name, double diag1, double diag2, double x0, double y0){
        if(diag1 <= 0 || diag2 <= 0) throw new IllegalArgumentException("value of 'rad1' or 'rad2' " +
                "is negative: rad1=" + diag1 + ", rad2=" + diag2);
        if(name.equals("")) throw new NullPointerException("Null exception");
        this.name = name;
        this.diag1 = diag1;
        this.diag2 = diag2;
        this.x0 = x0;
        this.y0 = y0;
    }
    public void setName(String name) {
        if(name.equals("")) throw new NullPointerException("Null exception");
        this.name = name;
    }
    public void setRadiuses(double rad1, double rad2) {
        if(rad1 <= 0 || rad2 <= 0) throw new IllegalArgumentException("value of 'rad1' or 'rad2' " +
                "is negative: rad1=" + rad1 + ", rad2=" + rad2);
        this.diag1 = rad1;
        this.diag2 = rad2;
    }
    public void setKoord(double x0, double y0) {
        this.x0 = x0;
        this.y0 = y0;
    }
    public double lenthFromCen(double x1, double y1){ // user enter koordinates
        return Math.sqrt(Math.pow((x0 - x1), 2) + Math.pow((y0 - y1), 2));
    }
    public double lenthFromCen(){   //user don`t enter koordinates, koordinates are (0,0)
        return Math.sqrt(x0 * x0 + y0 * y0);
    }
    public void setArea() {
        this.area = diag1 * diag2 * 0.5;
    }
    public String getName() {
        return name;
    }
    public double getArea() {
        return area;
    }
    public double side(){
        return Math.sqrt(diag1 * diag1 + diag2 * diag2) / 2;
    }
    public double height(){
        return (diag1 * diag2 /(2 * side()));
    }
    public double perimeter(){
        return 4 * side();
    }
}

