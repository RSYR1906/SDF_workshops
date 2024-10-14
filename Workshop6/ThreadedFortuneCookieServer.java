package Workshop6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedFortuneCookieServer {

    // java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
    public static void main(String[] args) throws IOException {

        int portNumber = Integer.parseInt(args[0]); // 12345
        String filePath = args[1]; // cookie_file.txt

        // Create the cookies manager to handle the cookie file
        Cookies_new cookiesManager = new Cookies_new(filePath);

        ServerSocket server = new ServerSocket(portNumber); // Create the server socket

        String name = Thread.currentThread().getName();

        // Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(3); // creating threadpool with 3 workers

        while (true) { // infinite loop unless return statement/server close
            System.out.println("Waiting for connection...");

            // Wait for incoming connection, block
            Socket conn = server.accept();
            System.out.println("Got a client connection!");

            // Create a connection handler with the client socket
            CookieClientHandler handler = new CookieClientHandler(conn, cookiesManager);

            // Pass the handler (work) to the thread pool
            thrPool.submit(handler);

            System.out.printf("[%s] Submiited connection handler to thread pool\n", name);
        }

    }
}