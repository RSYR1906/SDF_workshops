package Workshop4;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FortuneCookieServer {

    // java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
    public static void main(String[] args) throws IOException {

        int portNumber = Integer.parseInt(args[0]);
        String filePath = args[1];
        // Create the server socket
        ServerSocket server = new ServerSocket(portNumber);

        Cookies cookiesManager = new Cookies(filePath);

        while (true) {
            System.out.println("Waiting for connection...");

            // Wait for incoming connection, block
            Socket conn = server.accept();
            System.out.println("Got a client connection!");

            // Input stream to read client messages
            InputStream is2 = conn.getInputStream();
            Reader reader2 = new InputStreamReader(is2);
            BufferedReader br2 = new BufferedReader(reader2);

            // Output stream to send response back to client
            OutputStream os = conn.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // Continue serving the client until "quit" is received
            while (true) {
                String incomingMSG = br2.readLine(); // Read message from client

                // Check if the incoming message is requesting for a cookie
                if ("generate".equals(incomingMSG)) {
                    String fortune = cookiesManager.generateRandomCookie();
                    // Send the fortune cookie back to the client
                    bw.write(fortune + "\n");
                    bw.flush();

                } else if ("quit".equals(incomingMSG)) {
                    // If client sends "quit", close the connection and break the loop
                    System.out.println("Client requested to quit. Closing connection.");
                    br2.close();
                    bw.close();
                    conn.close();
                    break;
                } else {
                    System.out.println("Invalid message from client.");
                }
            }

            // Once the loop breaks, close the server (if desired)
            server.close();
            System.out.println("Server shut down.");
        }

    }
}