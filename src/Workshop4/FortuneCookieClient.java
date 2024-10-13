package Workshop4;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FortuneCookieClient {

    // java-cp fortunecookie.jar fc.Client localhost:12345

    public static void main(String[] args) throws IOException {

        String[] hostnport = args[0].split(":");
        String host = hostnport[0];
        int port = Integer.parseInt(hostnport[1]);

        // create socket
        Socket sock = new Socket(host, port);

        // Output stream to server (request for cookies)
        System.out.println(">>>> Connected to server\n");

        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // Input stream from server (receive cookies and display)
        InputStream is = sock.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        // Scanner for user input
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("To generate a random cookie, enter 'generate'");
            System.out.println("To quit, enter 'quit'");

            String command = scan.nextLine().trim().toLowerCase();

            switch (command) {
                case "generate":
                    // Send the "generate" message to the server
                    bw.write("generate\n");
                    bw.flush();

                    // Read the cookie generated from the server
                    String fortune = br.readLine();
                    System.out.println("Cookie generated: " + fortune);
                    break;

                case "quit":
                    // Send "quit" message and close connection
                    bw.write("quit\n");
                    bw.flush();
                    System.out.println("Closing connection...");
                    scan.close();
                    sock.close();
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}