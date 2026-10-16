import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Product[] inventory = {
            new Product("Laptop", 60000),
            new Product("Gaming Laptop", 100000),
            new Product("Android phone", 50000),
            new Product("iPhone", 80000)
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n------INVENTORY------");
        for (int i = 0; i < inventory.length; i++) {
            System.out.println((i + 1) + ") " + inventory[i]);
        }

        try {
            System.out.print("What number item do you want to buy? - ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            Product item = inventory[choice - 1];        // wrong number -> ArrayIndexOutOfBoundsException
            System.out.println("Your cart is - " + item);

            System.out.print("Enter your card number - ");
            Card card = new Card(scanner.nextLine());     // wrong card -> InvalidCardException

            System.out.println("Card is VALID (" + card.getCardType() + ")");
            System.out.println("Your payment was successful!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Item doesn't exist.");
        } catch (InvalidCardException e) {
            System.out.println("Invalid card: " + e.getMessage());
            System.out.println("Payment failed.");
        } finally {
            scanner.close();
        }
    }
}

// Our own exception, thrown when the card number is not acceptable
class InvalidCardException extends Exception {
    public InvalidCardException(String message) {
        super(message);
    }
}

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " RS";
    }
}

class Card {
    private String number;

    // The constructor checks the number. If it is bad, no Card is created.
    public Card(String input) throws InvalidCardException {
        String digits = input.replace(" ", "");

        if (!digits.matches("\\d{15,16}")) {
            throw new InvalidCardException("Card number must be 15 or 16 digits.");
        }
        if (!isLuhnValid(digits)) {
            throw new InvalidCardException("Card number failed the Luhn check.");
        }
        this.number = digits;
    }

    // Luhn algorithm: from the right, double every 2nd digit, add everything up,
    // and the total must be divisible by 10.
    private boolean isLuhnValid(String digits) {
        int sum = 0;
        boolean doubleIt = false;

        for (int i = digits.length() - 1; i >= 0; i--) {
            int digit = digits.charAt(i) - '0';
            if (doubleIt) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleIt = !doubleIt;
        }
        return sum % 10 == 0;
    }

    public String getCardType() {
        int firstTwo = Integer.parseInt(number.substring(0, 2));

        if (number.length() == 15 && (firstTwo == 34 || firstTwo == 37)) {
            return "AMEX";
        } else if (number.startsWith("4")) {
            return "VISA";
        } else if (firstTwo >= 51 && firstTwo <= 55) {
            return "MASTERCARD";
        } else if (firstTwo == 60 || firstTwo == 65 || firstTwo == 81 || firstTwo == 82) {
            return "RUPAY";
        }
        return "UNKNOWN";
    }
}
