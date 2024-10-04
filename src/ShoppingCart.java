import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {

    static ArrayList<String> cart = new ArrayList<>(); // The shopping cart is an ArrayList of Strings

    // public ShoppingCart2() { // Constructor to initialize the shopping cart
    // cart = new ArrayList<>();
    // }

    public void listCart() { // list feature (if else)

        if (cart.isEmpty()) { // check if cart is empty
            System.out.println("\nYour cart is empty");
        } else {
            System.out.println("\nItems in your cart");
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));
            }

        }
    }

    public void addItems(String items) { // add feature (for loop and check if item already in list)
        String[] itemList = items.split(",");

        for (String item : itemList) {
            if (!item.equals(item.toLowerCase())) {
                System.out.println("\nitem name should be in lowercase");

                continue;
            }
            if (cart.contains(item)) {
                System.out.println("\nitem already in list");
            } else {
                cart.add(item);
                System.out.println("\n" + item + " added to cart");
            }

        }

    }

    public void deleteItem(int index) { // delete feature (if index in list, delete. check also if the index is in
                                        // range)
        if (index >= 1 && index <= cart.size()) {
            String removedItem = cart.remove(index - 1);
            System.out.println(removedItem + " has been removed from the cart");
        } else {
            System.out.println("\nIncorrect item index");
        }

    }

    public static void main(String[] args) {

        ShoppingCart shoppingCart2 = new ShoppingCart(); // initialise a new shoppingCart2

        Scanner scanner = new Scanner(System.in);
        String command = new String();

        System.out.println("\nWelcome to your shopping cart");
        System.out.println("-----------------------------");
        System.out.println("Enter [list] to display the shopping cart list");
        System.out.println(
                "Enter [add] to add items to the list. Items entered should be separated by , (eg.banana,apple)");
        System.out.println("Enter [delete] to delete an item in the list");
        System.out.println("Enter [quit] to leave the programme");

        while (!command.equals("quit")) {

            System.out.printf("\nPlease enter a commmand: ");

            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "list":
                    shoppingCart2.listCart();
                    break;

                case "add":
                    System.out.printf("\nEnter the name of items to be added to the cart: ");
                    String items = scanner.nextLine();
                    shoppingCart2.addItems(items);
                    break;

                case "delete":
                    System.out.printf("\nEnter the index of the item to be deleted from cart: ");
                    String input = scanner.nextLine();

                    if (input.matches(".*[^a-zA-Z0-9].*")) { // Check if the input contains any special symbols
                                                             // (anything other than digits)
                        System.out.println("\nPlease avoid special symbols.");
                        break;
                    }
                    if (!input.matches("\\d+")) {
                        System.out.println("\nPlease enter an integer");
                    }

                    int index = Integer.parseInt(input); // convert String to Integer

                    if (index < 1 || index > cart.size()) {
                        System.out.println("\nIndex out of range");
                    }

                    else {
                        shoppingCart2.deleteItem(index);
                    }
                    break;

                case "quit":
                    System.out.println("\nGoodbye!");
                    return;

                default:
                    System.out.println("\ninput was not one of commands");

            }
            scanner.close();

        }

    }
}
