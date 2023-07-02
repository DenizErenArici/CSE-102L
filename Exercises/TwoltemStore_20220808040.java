
import java.util.Scanner;
public class TwoltemStore_20220808040 {
    public static void main(String[] args) {
//@author:Deniz Eren Arıcı
//@since:1.0
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of product 1");
        String s = input.next();
        String s1 = s.substring(0, 1).toUpperCase();
        String s2 = s.substring(1).toLowerCase();
        System.out.println("Enter the number of " + s1 + s2 + " we have");
        int numberOfs1 = input.nextInt();
        if (numberOfs1 < 0) {
            System.out.println("ERROR:Value cannot be negative");
        } else {
            System.out.println("Enter the cost of " + s1 + s2);
            double costofs1 = input.nextDouble();
            if (costofs1 < 0) {
                System.out.println("ERROR:Value cannot be negative");
            } else {
                System.out.println("Enter the name of product 2");
                String v = input.next();
                String v1 = v.substring(0, 1).toUpperCase();
                String v2 = v.substring(1).toLowerCase();
                System.out.println("Enter the number of " + v1 + v2 + " we have");
                int numberOfv1 = input.nextInt();
                if (numberOfv1 < 0) {
                    System.out.println("ERROR:Value cannot be negative");
                } else {
                    System.out.println("Enter the cost of " + v1 + v2);
                    double costofv1 = input.nextDouble();
                    if (costofv1 < 0) {
                        System.out.println("ERROR:Value cannot be negative");

                    } else {
                        System.out.println("Welcome to our store.We have the following.Please enter what you would like");
                        System.out.println("1- " + s1 + s2);
                        System.out.println("2- " + v1 + v2);
                        System.out.println("0- to checkout");
                        int chosen = input.nextInt();
                        if (chosen < 0 || chosen > 2) {
                            System.out.println("ERROR:Invalid menu option.Please enter valid menu option.");
                            chosen = input.nextInt();
                        }
                        int wantedv1 = 0;
                        int wanteds1 = 0;
                        while (chosen == 1 || chosen == 2 || chosen == 0) {




                            while (chosen == 1) {
                                System.out.println("How many " + s1 + s2 + " would you like?");
                                int wanteds2 = input.nextInt();
                                if (wanteds2 < 0) {
                                    System.out.println("ERROR:Invalid requested amount.");
                                }
                                if (wanteds2 > numberOfs1) {
                                    System.out.println("ERROR:We do not have that many remaining.");
                                }
                                wanteds1 += wanteds2;
                                int remainings1 = numberOfs1 - wanteds1;

                                if (remainings1 < 0) {
                                    System.out.println("We dont have that much " + s1 + s2);
                                    wanteds1 -= wanteds2;
                                    remainings1 += wanteds2;

                                }
                                System.out.println("Welcome to our store.We have the following.Please enter what you would like");
                                System.out.println("1- " + s1 + s2);
                                System.out.println("2- " + v1 + v2);
                                System.out.println("0- to checkout");
                                chosen = input.nextInt();

                                if (chosen == 0) {

                                    double totals1 = (wanteds1 * costofs1);
                                    double totalv1 = (wantedv1 * costofv1);

                                    System.out.println("***** Customer Total *****");
                                    System.out.println(s1 + s2 + " total cost is " + totals1);
                                    System.out.println(v1 + v2 + " total cost is " + totalv1);
                                    double totalDue = (totals1 + totalv1);
                                    System.out.println("------------------------------------------------------");
                                    System.out.println(totalDue);
                                    System.out.println("***** Final Report *****");
                                    System.out.println("We now have the remaining amounts of our products:");
                                    System.out.println(s1 + s2 + " = " + (numberOfs1 - wanteds1));
                                    System.out.println(v1 + v2 + " = " + (numberOfv1 - wantedv1));


                                }
                            }


                            while (chosen == 2) {
                                System.out.println("How many " + v1 + v2 + " would you like?");
                                int wantedv2 = input.nextInt();
                                if (wantedv2 < 0) {
                                    System.out.println("ERROR:Invalid rquested amount.");
                                }
                                if (wantedv2 > numberOfv1) {
                                    System.out.println("ERROR:We do not have that many remaining.");
                                }
                                wantedv1 += wantedv2;
                                int remainingv1 = numberOfv1 - wantedv1;

                                if (remainingv1 < 0) {
                                    System.out.println("We dont have that much " + v1 + v2);
                                    wantedv1 -= wantedv2;
                                    remainingv1 += wantedv2;

                                }

                                System.out.println("Welcome to our store.We have the following.Please enter what you would like");
                                System.out.println("1- " + s1 + s2);
                                System.out.println("2- " + v1 + v2);
                                System.out.println("0- to checkout");
                                chosen = input.nextInt();
                                if (chosen == 0) {

                                    double totals1 = (wanteds1 * costofs1);
                                    double totalv1 = (wantedv1 * costofv1);

                                    System.out.println("***** Customer Total *****");
                                    System.out.println(s1 + s2 + " total cost is " + totals1);
                                    System.out.println(v1 + v2 + " total cost is " + totalv1);
                                    double totalDue = (totals1 + totalv1);
                                    System.out.println("------------------------------------------------------");
                                    System.out.println(totalDue);
                                    System.out.println("***** Final Report *****");
                                    System.out.println("We now have the remaining amounts of our products:");
                                    System.out.println(s1 + s2 + " = " + (numberOfs1 - wanteds1));
                                    System.out.println(v1 + v2 + " = " + (numberOfv1 - wantedv1));


                                }

                                if (chosen == 0) {

                                    double totals1 = (wanteds1 * costofs1);
                                    double totalv1 = (wantedv1 * costofv1);

                                    System.out.println("***** Customer Total *****");
                                    System.out.println(s1 + s2 + " total cost is " + totals1);
                                    System.out.println(v1 + v2 + " total cost is " + totalv1);
                                    double totalDue = (totals1 + totalv1);
                                    System.out.println("------------------------------------------------------");
                                    System.out.println(totalDue);
                                    System.out.println("***** Final Report *****");
                                    System.out.println("We now have the remaining amounts of our products:");
                                    System.out.println(s1 + s2 + " = " + (numberOfs1 - wanteds1));
                                    System.out.println(v1 + v2 + " = " + (numberOfv1 - wantedv1));


                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
