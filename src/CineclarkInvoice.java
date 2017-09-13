
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


public class CineclarkInvoice {



    public static void main(String args[]) {

        Scanner personScanner = null;
        Scanner productScanner = null;
        Scanner customerScanner = null;
        String personFile = "data/Persons.dat";
        String productFile = "data/Products.dat";
        String customerFile = "data/Customers.dat";
        try {
            personScanner = new Scanner(new File(personFile));
            productScanner = new Scanner(new File(productFile));
            customerScanner = new Scanner(new File(customerFile));

        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        }

        while(personScanner.hasNext()) {
            System.out.println(personScanner.nextLine());
        }

        System.out.println();

        while(productScanner.hasNext()) {
            System.out.println(productScanner.nextLine());
        }

        System.out.println();

        while(customerScanner.hasNext()) {
            System.out.println(customerScanner.nextLine());
        }

    }

}
