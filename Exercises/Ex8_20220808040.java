
public class Ex8_20220808040 {
    public static void main(String[] args) {

    }
}

interface Sellable {
    String getName();

    double getPrice();
}

interface Package<T> {
    boolean pack(T item);

    boolean isEmpty();

    T extract();
}

interface Wrappable extends Sellable {
    public boolean canBePut();
}

abstract class Product implements Sellable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return getClass().getSimpleName() + " (" + name + "," + price + ")";
    }

    @Override
    public String getName() {
        return this.name = name;
    }

    @Override
    public double getPrice() {
        return this.price = price;
    }
}

class Mirror extends Product {
    private int width;
    private int height;

    public Mirror(int width, int height) {
        super("mirror", 2);
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    public <T> T reflect(T item) {
        System.out.println("Reflected item is: " + item);
        return item;
    }

}

class Paper extends Product implements Wrappable {
    private String note;

    public Paper(String note) {
        super("A4", 3);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean canBePut() {
        return true;
    }
}

class Matroschka<T extends Wrappable> extends Product implements Wrappable {
    private T item;

    public Matroschka(T item) {
        super("Doll", 5 + item.getPrice());
        this.item = item;
    }

    public T extract() {
        if (isEmpty()) {
            return null;
        } else {
            T extractedItem = item;
            item = null;
            return extractedItem;
        }
    }

    public boolean pack(T item) {
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + item + "}";
    }

    @Override
    public boolean canBePut() {
        return true;
    }
}

class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;

    public Box() {
        item = null;
        seal = false;
    }

    public Box(T item) {
        seal = true;
    }

    public T extract() {
        seal = false;
        if (isEmpty()) {
            return null;
        } else {
            T extractedItem = item;
            item = null;
            return extractedItem;
        }
    }

    public boolean pack(T item) {
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + item + "} Seal:" + seal;
    }

}
