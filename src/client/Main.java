package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8080;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String srvResp, resp;
            while (true) {
                srvResp = in.readLine();
                System.out.println(srvResp);
                if (srvResp.contains("Enjoy!")) {
                    break;
                }
                resp = scanner.next();
                out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}