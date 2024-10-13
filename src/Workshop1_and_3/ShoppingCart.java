package Workshop1_and_3;
import java.io.IOException;
import java.util.*;

public class ShoppingCart {
    private List<String> cartItems;
    private ShoppingCartDB cartDB;
    private String currentUser;

    public ShoppingCart(String cartDirectory) {
        this.cartItems = new ArrayList<>();
        this.cartDB = new ShoppingCartDB(cartDirectory);
        this.currentUser = null;
    }

    // List items in the cart
    public void listCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart:");
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.println((i + 1) + ". " + cartItems.get(i));
            }
        }
    }

    // Add items to the cart
    public void addItem(String items) {
        String[] itemList = items.split(",");

        for (String item : itemList) {
            if (!item.equals(item.toLowerCase())) {
                System.out.println("\nitem name should be in lowercase");

                continue;
            }
            if (cartItems.contains(item)) {
                System.out.println("\nitem already in list");
            } else {
                cartItems.add(item);
                System.out.println("\n" + item + " added to cart");
            }

        }
    }

    // Delete item from the cart
    public void deleteItem(int index) {
        if (index > 0 && index <= cartItems.size()) {
            System.out.println("Removed: " + cartItems.remove(index - 1));
        } else {
            System.out.println("Index out of range");
        }
    }

    // Login a user and load their cart
    public void login(String username) {
        try {
            cartItems = cartDB.loadCart(username);
            currentUser = username;
            System.out.println("Logged in as " + username);
        } catch (IOException e) {
            System.err.println("Error loading cart for user: " + username);
        }
    }

    // Save the cart for the current user
    public void save() {
        if (currentUser == null) {
            System.out.println("Please login before saving.");
        } else {
            try {
                cartDB.saveCart(currentUser, cartItems);
            } catch (IOException e) {
                System.err.println("Error saving cart for user: " + currentUser);
            }
        }
    }

    // List all users
    public void listUsers() {
        List<String> users = cartDB.listUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users:");
            for (String user : users) {
                System.out.println("- " + user);
            }
        }
    }

    // Start interaction with the user
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter command (list, add, delete, login, save, users, quit): ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "list":
                    listCart();
                    break;

                case "add":
                    System.out.print("Enter item to add: ");
                    String item = scanner.nextLine();
                    addItem(item);
                    break;

                case "delete":
                    System.out.print("Enter index of item to delete: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    deleteItem(index);
                    break;

                case "login":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    login(username);
                    break;

                case "save":
                    save();
                    break;

                case "users":
                    listUsers();
                    break;

                case "quit":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}