import java.util.*;

public class Main {

    static void cardAuthenticator(long cardnumber) {
        // Luhn's algorithm
        String cardnum = String.valueOf(cardnumber);

        if (cardnum.length() < 15) {
            System.out.println("Invalid card number");
            return;
        }

        // Sum digits right-to-left, doubling every second digit
        int sum = 0;
        boolean doubleDigit = false;
        for (int i = cardnum.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardnum.charAt(i));
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }

        String validity = (sum % 10 == 0) ? "VALID" : "INVALID";

        if (validity.equals("VALID")) {
            String cardtype = null;
            if (cardnum.length() == 16) {
                if (cardnum.charAt(0) == '4') {
                    cardtype = "VISA";
                } else if (cardnum.charAt(0) == '5') {
                    cardtype = "MASTERCARD";
                } else if (cardnum.charAt(0) == '6') {
                    cardtype = "RUPAY";
                }
            } else if (cardnum.length() == 15) {
                cardtype = "AMEX";
            }
            System.out.println("Card is " + validity + (cardtype != null ? " (" + cardtype + ")" : ""));
        } else {
            System.out.println("Card is " + validity);
        }
    }

    public static void main(String[] args) {
        String[] inventory = {
            "1) laptop - 60,000 RS",
            "2) Gaming Laptop - 1,00,000 RS",
            "3) Android phone - 50,000 RS",
            "4) iPhone - 80,000 RS"
        };

        int cartValue = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n------INVENTORY------");
        for (String i : inventory) {
            System.out.println(i);
        }

        System.out.print("What number item do you want to buy? - ");
        int choice = Integer.parseInt(scanner.nextLine());
        int index = choice - 1;

        if (index <= inventory.length) {
            System.out.println("your cart is - " + inventory[index]);
        } else if (index > inventory.length) {
            System.out.println("Item doesnt exist.");
        }

        System.out.print("Enter your card Number - ");
        long cardin = Long.parseLong(scanner.nextLine());
        cardAuthenticator(cardin);

        System.out.print("Enter your CVV - ");
        int cvv = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your expiration date - ");
        long expdate = Long.parseLong(scanner.nextLine());

        System.out.println("Your payment was successful!");
    }
}
