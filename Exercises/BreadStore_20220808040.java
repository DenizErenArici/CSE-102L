import java.util.Scanner;

public class BreadStore_20220808040 {
    public static void main(String[] args) {
        System.out.println("Enter the number of bread we have");
        Scanner input = new Scanner(System.in);
        int countofBread = input.nextInt();
        if (countofBread <= 0) {
            System.out.println("Error");
        }
        if (countofBread > 0) {
            System.out.println("Enter the cost of bread");
            double costofBread = input.nextDouble();
            if (costofBread <= 0) {
                System.out.println("Error");
            }
            if (costofBread > 0) {
                System.out.println("Welcome to ARICI bread store.We have " + countofBread + " breads available.How many would you like?");

                int customerRequest = input.nextInt();
                if (customerRequest <= 0 || customerRequest > countofBread) {
                    System.out.println("Error");
                } else if (customerRequest > 0 || customerRequest <= countofBread) {
                    double totalCost = (customerRequest * costofBread);
                    System.out.println("Your cost is " + totalCost + " liras");
                    System.out.println("Thank you for choosing us ");
                    int remainingBread = (countofBread - customerRequest);
                    System.out.println("We now have " + remainingBread + " loaves of bread remaining.");

                }
            }
        }
    }
}

