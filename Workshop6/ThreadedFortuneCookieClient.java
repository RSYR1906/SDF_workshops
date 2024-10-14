package Workshop6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedFortuneCookieClient {

    // java-cp fortunecookie.jar fc.Client localhost:12345

    public static void main(String[] args) throws IOException {

        String[] hostnport = args[0].split(":"); // splitting the args (localhost:12345)
        String host = hostnport[0];
        int port = Integer.parseInt(hostnport[1]); // changing the string type to integer

        // create socket
        Socket sock = new Socket(host, port);

        System.out.println(">>>> Connected to server\n");

        // Output stream to server (request for cookies)
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

        // Input stream from server (receive cookies and display)
        BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        // Scanner for user input
        Scanner scan = new Scanner(System.in);

        while (true) { // creating an infinite loop so the program wont end unless there is a return
                       // statement
            System.out.println("To generate a random cookie, enter 'generate'");
            System.out.println("To quit, enter 'quit'");

            String command = scan.nextLine().trim().toLowerCase();

            switch (command) {
                case "generate":
                    // Send the "generate" message to the server to request for a cookie
                    bw.write("generate\n");
                    bw.flush();

                    // Read the cookie randomly generated from the server
                    String fortune = br.readLine();
                    System.out.println("Cookie generated: " + fortune);
                    break;

                case "quit":
                    // Send "quit" message to close server and client connection
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
