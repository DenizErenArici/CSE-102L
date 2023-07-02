public class Ex9_20220808040 {
    public static void main(String[] args) {

    }
}
interface Wrappable extends Sellable {
    public boolean canBePut();
}
interface Sellable {
    String getName();

    double getPrice();
}
interface Package<T> {
    boolean pack(T item);
    boolean isEmpty();
    T extract();
    double getPriority();
}
interface Common <T>{
    boolean isEmpty();
    T peek();
    int size();
}
interface Stack <T> extends Common{
    boolean push(T item);
    T pop();
}
interface Node <T>{
    int DEFAULT_CAPACITY = 2;
     T getNext();
     void setNext(T item);
     double getPriority();
}
interface PriorityQueue <T> extends Common{
    int FLEET_CAPACITY= 3;
    boolean enqueue(T item);
    T dequeue();
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
class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;
    private int distanceToAddress;

    public Box() {
        item = null;
        seal = false;
    }

    public Box(int distanceToAddress,T item) {
        seal = true;
        this.distanceToAddress=distanceToAddress;
        this.item=item;
    }
    public Box(T item){
        this.item = item;
        seal = true;
    }
    public Box(int distanceToAddress){
        this.distanceToAddress=distanceToAddress;
    }
    public int getDistanceToAddress() {
        return distanceToAddress;
    }
    public T getItem() {
        return item;
    }

    public T extract() {
        if (isEmpty()) {
            return null;
        } else {
            T extractedItem = item;
            item = null;
            seal = false;
            return extractedItem;
        }
    }
    @Override
    public double getPriority() {
        return distanceToAddress / item.getPrice();
    }

    public boolean pack(T item) {
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + item + "} Seal:" + seal;

    }
}
class Container implements Stack<Box>, Node<Container>, Comparable<Container> {
    private Box[] boxes;
    private int top;
    private int size;
    private double priority;
    private Container next;
    public Container(){
        boxes=new Box[DEFAULT_CAPACITY];
        top=-1;
        priority=0;
        next=null;
    }
    public String toString(){
        return "Container with priority: " + priority;
    }
    @Override
    public boolean isEmpty(){
        return top==-1;
    }
    @Override
    public Box<?> peek() {
        if (isEmpty()) {
            return null;
        }
        return boxes[top];
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public int compareTo(Container other){
        double thisPrio=0.0;
        double thisDistance=0.0;
        double prioTarget=0.0;
        double distanceTarget=0.0;

        for(int i = 0; i < size; i++){
            thisPrio += boxes[i].getPriority();
        }

        for(int i = 0; i < other.size; i++){
            prioTarget += other.boxes[i].getPriority();
        }

        for(int i = 0; i < size; i++){
            thisDistance += boxes[i].getDistanceToAddress();
        }

        for(int i = 0; i < other.size; i++){
            distanceTarget += other.boxes[i].getDistanceToAddress();
        }

        if(thisPrio < prioTarget){
            return -1;
        }else if(thisPrio > prioTarget){
            return 1;
        }else{
            if(thisDistance < distanceTarget){
                return -1;
            }else if(thisDistance > distanceTarget) {
                return 1;
            }else{
                return 0;
            }
        }
    }
   @Override
    public void setNext(Container item) {
        next = item;
    }
    @Override
    public Container getNext() {
        return next;
    }
    @Override
    public double getPriority() {
        double total = 0;
        for (Box box : boxes) {
            if (box != null && !box.isEmpty()) {
                total += box.getPriority();
            }
        }
        return total;
    }
    @Override
    public boolean push(Box item) {
        if (size() < DEFAULT_CAPACITY){
            boxes[++top] = item;
            size++;
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Box<?> pop() {
        if (top == -1) {
            return null;
        }
        Box<?> tool = boxes[top];
        boxes[--top] = null;
        size--;
        return tool;
    }

}
class CargoFleet implements PriorityQueue<Container> {
    private Container head;
    private int size;

    public CargoFleet() {
        head = null;
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        if (head != null) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public Container peek() {
        return head;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean enqueue(Container item) {
        if (head == null) {
            head = item;
        } else {
            Container current = head;
            Container previous = null;

            while (current != null && current.compareTo(item) < 0) {
                previous = current;
                current = current.getNext();
            }

            if (previous == null) {
                item.setNext(head);
                head = item;
            } else {
                previous.setNext(item);
                item.setNext(current);
            }
        }
        size++;
        return true;
    }
    @Override
    public Container dequeue() {
        if (isEmpty()) {
            return null;
        }
        Container removed = head;
        head = head.getNext();
        removed.setNext(null);
        size--;
        return removed;
    }

}
class CargoCompany {
    private Container stack;
    private CargoFleet queue;

    public CargoCompany() {
        stack = new Container();
        queue = new CargoFleet();
    }
    public <T extends Box<?>> Sellable deliver(T box) {
        return box.extract();
    }

    public void empty(Container container) {
        while (!container.isEmpty()) {
            Box<?> box = container.pop();
            Object result = deliver(box);
            System.out.print(result);
        }
    }
    public void ship(CargoFleet fleet){
        while (!fleet.isEmpty()) {
            Container container = fleet.dequeue();
            empty(container);
        }
    }
    public <T extends Box<?>> void add(T box) {
        if (stack.isEmpty()) {
            stack.push(box);
        } else {
            if (((PriorityQueue<Container>) queue).enqueue(stack)) {
                stack = new Container();
                add(box);
            } else {
                ship(queue);
            }
        }
    }
}
