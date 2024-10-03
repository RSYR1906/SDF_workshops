import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart2 {
    private static ArrayList<String> cart; // The shopping cart is an ArrayList of Strings

    public ShoppingCart2() { // Constructor to initialize the shopping cart
        cart = new ArrayList<>();
    }

    public void listCart() { // list feature (if else)

        if (cart.isEmpty()) { // check if cart is empty
            System.out.println("Your cart is empty");
        } else {
            System.out.println("Items in your cart");
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));
            }

        }
    }

    public void addItems(String items) { // add feature (for loop and check if item already in list)
        String[] itemList = items.split(",");

        for (String item : itemList) {
            if (!item.equals(item.toLowerCase())) {
                System.out.println("item name should be in lowercase");

                continue;
            }
            if (cart.contains(item)) {
                System.out.println("item already in list");
            } else {
                cart.add(item);
                System.out.println(item + " added to cart");
            }

        }

    }

    public void deleteItem(int index) { // delete feature (if index in list, delete. check also if the index is in
                                        // range)
        if (index >= 1 && index <= cart.size()) {
            String removedItem = cart.remove(index - 1);
            System.out.println(removedItem + " has been removed from the cart");
        } else {
            System.out.println("Incorrect item index");
        }

    }

    public static void main(String[] args) {

        ShoppingCart2 shoppingCart2 = new ShoppingCart2(); // initialise a new shoppingCart2

        Scanner scanner = new Scanner(System.in);
        String command = new String();

        System.out.println("Welcome to your shopping cart");

        while (!command.equals("quit")) {

            System.out.println("Please enter a commmand: [list],[add],[delete],[quit]");

            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "list":
                    shoppingCart2.listCart();
                    break;

                case "add":
                    System.out.println("Enter the name of items to be added to the cart");
                    String items = scanner.nextLine();
                    shoppingCart2.addItems(items);
                    break;

                case "delete":
                    System.out.println("Enter the index of the item to be deleted from cart");
                    String input = scanner.nextLine();

                    if (input.matches(".*[^a-zA-Z0-9].*")) { // Check if the input contains any special symbols
                                                             // (anything other than digits)
                        System.out.println("Please avoid special symbols.");
                        break;
                    }
                    if (!input.matches("\\d+")) {
                        System.out.println("Please enter an integer");
                    }

                    int index = Integer.parseInt(input);

                    if (index < 1 || index > cart.size()) {
                        System.out.println("Index out of range");
                    }

                    else {
                        shoppingCart2.deleteItem(index);
                    }
                    break;

                case "quit":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("input was not one of commands");

            }
        }

    }
}
