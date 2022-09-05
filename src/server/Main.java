package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("server started");
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.printf("New connection accepted on port %s%n", clientSocket.getPort());
                out.println("Hi, what is your name?");
                final String name = in.readLine();
                out.printf("Nice to meet you %s! What is the legal age in your area?%n", name);
                final int legalAge = Integer.parseInt(in.readLine());
                out.printf("What is your age, %s?%n", name);
                final int clientLegalAge = Integer.parseInt(in.readLine());
                if (clientLegalAge >= legalAge) {
                    out.printf("Welcome to our space, %s! " +
                            "You have full access to our content. Enjoy!%n", name);
                } else {
                    out.printf("Welcome to our space, %s! " +
                            "You have full access to content suitable for your age. Enjoy!%n", name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}