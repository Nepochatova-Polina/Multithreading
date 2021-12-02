package com.company.Module2.Lab3;

import com.company.Module2.Lab2.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private PrintWriter out = null;
    private BufferedReader in = null;

    public Server() throws Exception {
        utility uti = new utility();

    }

    public void start(int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket sock = server.accept();
            in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
            while (processQuery());
        }
    }

    private boolean processQuery() {
        String response = "Done!";
        try {
            String query = in.readLine();
            if (query == null) return false;
            String[] fields = query.split("#");
            try {
                String oper = fields[0];
                System.out.println(oper);
                int id = Integer.parseInt(fields[1]);
                switch (oper) {
                    case "Show Musicians" -> response = utility.showMusicians();
                    case "Show Albums" -> response = utility.showAlbums();
                    case "Add Musician" -> {
                        utility.addMusician(fields[1]);
                        response = "Done!";
                    }
                    case "Add Album" -> {
                        String[] vars = fields[1].split("/");
                        String name = vars[0];
                        int songNum = Integer.parseInt(vars[1]);
                        int copies = Integer.parseInt(vars[2]);
                        String type = vars[3];
                        int artist = Integer.parseInt(vars[4]);
                        utility.addAlbum(name, songNum, copies, type, artist);
                        response = "Done!";
                    }
                    case "Delete Musician" -> response = utility.deleteMusician(id);
                    case "Delete Album"-> response =  utility.deleteAlbumByID(id);
                    default -> {}
                }

            } catch (NumberFormatException ignored) {}
            out.println(response);
            return true;
        } catch (IOException e) {

            return false;
        }
    }

    public static void main(String[] args) {
        try {
            Server srv = new Server();
            srv.start(12345);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}