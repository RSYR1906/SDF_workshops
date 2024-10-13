package Workshop1_and_3;
import java.io.*;
import java.util.*;

public class ShoppingCartDB {
    private File cartDirectory;
    private File userFile;

    // Constructor to initialize the cart directory
    public ShoppingCartDB(String cartDirectoryPath) {
        this.cartDirectory = new File(cartDirectoryPath);

        // Create the directory if it doesn't exist
        if (!this.cartDirectory.exists()) {
            this.cartDirectory.mkdirs();
        }
    }

    // Load cart from a specific user's database file
    public List<String> loadCart(String username) throws IOException {
        userFile = new File(cartDirectory, username + ".db");

        // If file doesn't exist, create an empty cart file
        if (!userFile.exists()) {
            userFile.createNewFile();
        }

        List<String> cartItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cartItems.add(line.trim()); // Add each line (item) to the cart
            }
        }
        return cartItems;
    }

    // Save the shopping cart to the user's database file
    public void saveCart(String username, List<String> cartItems) throws IOException {
        userFile = new File(cartDirectory, username + ".db");

        try (PrintWriter writer = new PrintWriter(new FileWriter(userFile))) {
            for (String item : cartItems) {
                writer.println(item); // Write each item on a new line
            }
        }
        System.out.println("Cart saved to " + userFile.getAbsolutePath());
    }

    // List all users (list all files in the cart directory)
    public List<String> listUsers() {
        List<String> users = new ArrayList<>();
        File[] files = cartDirectory.listFiles((dir, name) -> name.endsWith(".db"));
        if (files != null) {
            for (File file : files) {
                users.add(file.getName().replace(".db", ""));
            }
        }
        return users;
    }
}