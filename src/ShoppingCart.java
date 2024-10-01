import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    // The shopping cart is an ArrayList of Strings
    private ArrayList<String> cart;

    // Constructor to initialize the shopping cart
    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    // Method to list the contents of the cart
    public void listCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));
            }
        }
    }

    /**
     * @param items
     */
    // Method to add items to the cart
    public void addItems(String items) {
        String[] itemList = items.split(",");
        for (String item : itemList) {
            item = item.trim();
            if (!item.equals(item.toLowerCase())) {
                System.out.println(
                        "Error: Item '" + item + "' contains uppercase letters. Only lowercase items are allowed.");
                continue; // Skip this item and continue with the next one
            }
            if (!cart.contains(item)) {
                cart.add(item);
                System.out.println(item + " added to the cart.");
            } else {
                System.out.println(item + " is already in the cart.");
            }
        }
    }

    // Method to delete an item from the cart
    public void deleteItem(int index) {
        if (index >= 1 && index <= cart.size()) {
            String removedItem = cart.remove(index - 1);
            System.out.println(removedItem + " removed from the cart.");
        } else {
            System.out.println("Invalid index. Please provide a correct item number.");
        }
    }

    // Main method for the program
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Shopping Cart!");

        // Command loop
        while (true) {
            System.out.print("\nEnter a command (list, add, delete, or quit): ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "list":
                    shoppingCart.listCart();
                    break;

                case "add":
                    System.out.print("Enter items to add (separate items by commas): ");
                    String items = scanner.nextLine();
                    shoppingCart.addItems(items);
                    break;

                case "delete":
                    System.out.print("Enter the item number to delete: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine());
                        shoppingCart.deleteItem(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "quit":
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }
}