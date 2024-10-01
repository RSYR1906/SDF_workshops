import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    private static ArrayList<String> cart; // The shopping cart is an ArrayList of Strings

    public ShoppingCart() { // Constructor to initialize the shopping cart
        cart = new ArrayList<>();
    }

    public void listCart() { // Method to list the contents of the cart
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
    public void addItems(String items) { // Method to add items to the cart
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
            }

            else {
                System.out.println(item + " is already in the cart.");
            }
        }
    }

    public void deleteItem(int index) { // Method to delete an item from the cart
        if (index >= 1 && index <= cart.size()) {
            String removedItem = cart.remove(index - 1);
            System.out.println(removedItem + " removed from the cart.");
        } else {
            System.out.println("Invalid index. Please provide a correct item number.");
        }
    }

    public static void main(String[] args) { // Main method for the program
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Shopping Cart!");

        while (true) { // Command loop
            System.out.print("\n[Enter a command] \n \n list, add, delete, or quit: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) { // different cases of the Shopping Cart
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
                    String input = scanner.nextLine();

                    // Check if the input is a number
                    if (!input.matches("\\d+")) { // This checks if input contains only digits
                        System.out.println("Incorrect item index,please enter numeric values.");
                        break;
                    }

                    int index = Integer.parseInt(input);

                    // Check if index is within range
                    if (index < 0 || index > cart.size()) { // Assuming cart.size() returns the
                                                            // number of items
                        System.out.println("Incorrect item index, index out of range");
                    } else {
                        shoppingCart.deleteItem(index); // Proceed with deletion if index is valid
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