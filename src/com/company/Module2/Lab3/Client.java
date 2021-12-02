package com.company.Module2.Lab3;

import com.company.Module2.Lab2.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final Socket sock;
    private final PrintWriter out;
    private final BufferedReader in;

    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        in = new BufferedReader(
                new InputStreamReader(sock.getInputStream()));
        out = new PrintWriter(sock.getOutputStream(), true);
    }

    private String sendQuery(String operation, Object object) throws IOException {
        String query = operation + "#" + object;
        out.println(query);
        String response = in.readLine();
        try {
            if (response == null) {
                System.out.println("Wrong answer from server");
            }
        } catch (NumberFormatException e) {
            throw new IOException("Invalid response from server");

        }
        return response;
    }

    public void show(String i) throws IOException {
        String response = sendQuery(i, 0);

        if (response != null) {
            String[] fields = response.split("#");
            for (String field : fields) {
                System.out.println(field);
            }
        }
    }

    public void delete(String id, int itemId) throws IOException {
        System.out.println(sendQuery(id, itemId));
    }

    public void add(String id, String info) throws IOException {
        System.out.println(sendQuery(id, info));
    }

    public void disconnect() throws IOException {
        sock.close();
        if (sock.isClosed())
            System.out.println("Client disconnected");
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            client.show("Show Musicians");
            client.delete("Delete Musician", 2);
            client.delete("Delete Album", 1);
            client.add("Add Musician","Grigorko");
            client.add("Add Albums","moss/898/888/full/5");


            client.disconnect();
        } catch (IOException e) {
            System.out.println("Возникла ошибка");
            e.printStackTrace();
        }
    }
}