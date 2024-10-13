package Workshop4;
import java.io.*;
import java.util.*;

public class Cookies {
    private String filePath;
    ArrayList<String> cookiesList;
    private Random random;

    public Cookies(String filePath) throws IOException {
        this.filePath = filePath;
        this.cookiesList = new ArrayList<>();
        this.random = new Random();

        openFile();
    }

    // get cookies randomly method
    private void openFile() throws IOException {
        // FileReader to read the cookies file
        FileReader fr = new FileReader(filePath);
        BufferedReader br3 = new BufferedReader(fr);

        String line;

        // Read all cookies from file into list
        while ((line = br3.readLine()) != null) {
            cookiesList.add(line);
        }
        br3.close();
    }

    public String generateRandomCookie() {
        if (cookiesList.isEmpty()) {
            return "No cookies available";
        }
        // Generate a random index to pick a fortune cookie
        int index = random.nextInt(cookiesList.size());

        return cookiesList.get(index);
    }

}

// close cookie file method

// open cookie file method
