package Workshop1_and_3;
public class ShoppingCartApp {
    public static void main(String[] args) {
        String cartDirectory;

        // Check if directory is provided via command-line argument
        if (args.length > 0) {
            cartDirectory = args[0];
        } else {
            cartDirectory = "db"; // Default directory
        }

        ShoppingCart shoppingCart = new ShoppingCart(cartDirectory);
        shoppingCart.start();
    }
}