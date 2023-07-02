import java.util.Date;
import java.util.ArrayList;
public class Ex6_20220808040 {
}
abstract class Product implements Comparable<Product>{
    private String name;
    private double price;
    Product(){};
    public Product(String name,double price){
        this.name=name;
        this.price=price;
    }
    public int compareTo(Product otherProduct) {
        if (this.price < otherProduct.price) {
            return -1;
        } else if (this.price > otherProduct.price) {
            return 1;
        } else {
            return 0;
        }
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int forCompare(Product p){
        if(this.price<p.getPrice()){
            return -1;
        }else if(this.price==p.getPrice()){
            return 0;
        }else{
            return 1;
        }
    }
    @Override
    public String toString(){
        return getClass().getSimpleName() + "[name=" + name + ", price=" + price + "]";
    }
}
abstract class Book extends Product{
    private String author;
    private int pageCount;
    public Book(String name, double price,String author,int pageCount){
        super(name, price);
        this.author=author;
        this.pageCount=pageCount;
    }
    public String getAuthor() {
        return author;
    }
    public int getPageCount() {
        return pageCount;
    }

}
class ReadingBook extends Book{
    private String genre;
    public ReadingBook(String name,double price,String author,int pageCount,String genre){
        super(name,price,author,pageCount);
        this.genre=genre;
    }

    public String getGenre() {
        return genre;
    }
}
class ColoringBook extends Book implements Colorable{
    private String color;
    public ColoringBook(String name,double price,String author,int pageCount){
        super(name,price,author,pageCount);
        this.color=color;
    }
    public String getColor() {
        return color;
    }
    public void paint(String color){
        this.color=color;
    }


}
class ToyHorse extends Product implements Rideable{
    public void ride(){

    }
}
class Bicycle extends Product implements Colorable,Rideable{
    private String color;

    public String getColor() {
        return color;
    }

    @Override
    public void ride() {
    }

    @Override
    public void paint(String color) {
        this.color=color;
    }
}
class User{
    private String username;
    private String email;
    private PaymentMethod payment;
    private ArrayList<Product> cart=new ArrayList<Product>();
    public User(String username,String email){
        this.username=username;
        this.email=email;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }
    public Product getProduct(int index){
        return cart.get(index);
    }
    public void removeProduct(int index){
        cart.remove(cart.get(index));
    }
    public void addProduct(Product product){
        cart.add(product);
    }
    public void purchase(){
        double totalPrice=0.0;
        for(Product product:cart){
            totalPrice+=product.getPrice();
        }
        if(payment.pay(totalPrice)){
            cart.clear();
            System.out.println("Purchase succesful.");
        }else{
            System.out.println("Purchase failed. Insufficient funds.");
        }
    }
}
class CreditCard implements PaymentMethod{
    private long cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private int cvv;
    public CreditCard(long cardNumber,String cardHolderName,Date expirationDate,int cvv){
        this.cardNumber=cardNumber;
        this.cardHolderName=cardHolderName;
        this.expirationDate=expirationDate;
        this.cvv=cvv;
    }
    @Override
    public boolean pay(double amount) {
        return true;
    }

}
class SimpleEncryption {

    public static String encrypt(String message, int secretKey, int minValue, int maxValue) {

        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int value = message.charAt(i);
            int encryptedValue = (value + secretKey) % (maxValue - minValue + 1) + minValue;
            encrypted += (char) encryptedValue;
        }
        return encrypted;
    }

}
class PayPal extends SimpleEncryption implements PaymentMethod{
    private String username;
    private String password;
    public PayPal(String username,String password){
        this.username=username;
        this.password=encrypt(password,3,32,126);
    }
    @Override
    public boolean pay(double amount) {
        return true;
    }
}
interface Colorable{
    void paint(String color);
}
interface Rideable{
    void ride();
}
interface PaymentMethod{
    boolean pay(double amount);
}