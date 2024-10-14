package Workshop6;

import java.io.*;
import java.util.*;

public class Cookies_new {
    private String filePath;
    ArrayList<String> cookiesList;
    private Random random;

    public Cookies_new(String filePath) throws IOException {
        this.filePath = filePath;
        this.cookiesList = new ArrayList<>();
        this.random = new Random();

        openFile(); // always open file when called
    }

    // get cookies randomly method
    private void openFile() throws IOException {
        // FileReader to read the cookies file
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String line;

        // Read all cookies from file into list
        while ((line = br.readLine()) != null) {
            cookiesList.add(line);
        }
        br.close();
    }

    public String generateRandomCookie() {
        if (cookiesList.isEmpty()) {
            return "No cookies available";
        }
        // Generate a random index to pick a fortune cookie
        int index = random.nextInt(cookiesList.size());

        return cookiesList.get(index);
    }

    public void closeCookie() { // no need to create this method as the server and client will close
    }
}
