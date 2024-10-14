package Workshop6;

import java.io.*;
import java.net.Socket;

public class CookieClientHandler implements Runnable {

    private Socket socket;
    private Cookies_new cookieManager;

    // Cookies_new cookiesManager = new Cookies_new(filePath); // Instantiate
    // Cookies object

    public CookieClientHandler(Socket socket, Cookies_new cookieManager) {
        this.socket = socket;
        this.cookieManager = cookieManager;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName(); // getting the name of the thread

        try (// Input stream to read client messages
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Output stream to send response back to client
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Continue serving the client until "quit" is received
            while (true) {
                String incomingMSG = br.readLine(); // Read message from client

                // Check if the incoming message is requesting for a cookie
                if ("generate".equals(incomingMSG)) {
                    String fortune = cookieManager.generateRandomCookie();
                    System.out.println("Client " + name + ": Generating random cookie and sending to client");
                    // Send the fortune cookie back to the client
                    bw.write(fortune + "\n");
                    bw.flush();

                } else if ("quit".equals(incomingMSG)) {
                    // If client sends "quit", close the connection and break the loop
                    System.out.println("Client " + name + " requested to quit. Closing connection.");
                    br.close();
                    bw.close();
                    socket.close();
                    return;
                } else {
                    System.out.println("Invalid message from client.");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
