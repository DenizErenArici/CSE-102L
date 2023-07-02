public class Ex1_20220808040 {
    public static void main(String[] args) {
        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.previousClosingPrice = 34.5;
        stock.currentPrice = 34.35;

    }
}

class Stock {

    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    // For the rate of change per day.
    public double getChangePercent() {
        return (currentPrice - previousClosingPrice) / previousClosingPrice * 100;
    }

    Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;

    }
}

class Fan {
    final private int SLOW = 1;
    final private int MEDIUM = 2;
    final private int FAST = 3;
    private int speed = SLOW;
    String Speed = Integer.toString(speed);
    private static boolean on = false;
    private double radius = 5.0;
    private String color = "Blue";

    Fan(String color, double radius) {
        this.color = color;
        this.radius = radius;
    }

    Fan(boolean on) {
        this.on = on;
    }

    public String toString() {
        if (on == true)
            return "Fan speed is " + Speed + " .Color is " + this.color + "Radius is " + this.radius + " .";
        else
            return  "Color is "+this.color+"and the Fan is off";

    }
}


