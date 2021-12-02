package com.company.Module2.Lab2;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class utility {
    public static Connection con;
    public static Statement stmt;

    public utility() throws Exception {
        String url = "jdbc:postgresql://localhost:15435/postgres";
        String login = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        try {
            con = DriverManager.getConnection(url, login, password);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (con != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        stmt = con.createStatement();
    }

    public void stop() throws SQLException {
        con.close();
    }

    public static void addMusician(String name) {
        String SQL = "INSERT into musicians (name) values (?)";

        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println(name + "'s  successfully added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteMusicianAlbums(int id) {
        String sql = MessageFormat.format("DELETE FROM albums WHERE artist_id = {0}", id);
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                System.out.println("Musician's albums  successfully deleted!");
            } else {
                System.out.println("Musician's albums not found");
            }
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
    }
    public static String deleteMusician(int id) {
        String sql = MessageFormat.format("DELETE FROM musicians WHERE id = {0}", id);
        String response = null;
        try {
            int c = stmt.executeUpdate(sql);
            deleteMusicianAlbums(id);
            if (c > 0) {
               response = "Musician " + id + " successfully deleted!";
            } else {
                response = "Musician " + id + " not found";
            }
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
        return response;
    }


    public static void updateMusician(String name, String oldName) {
        String SQL = "UPDATE musicians "
                + "SET name = ? "
                + "WHERE name = ?";

        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, oldName);
            pstmt.executeUpdate();
            System.out.println(oldName + "'s info successfully updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String showMusicians() {
        StringBuilder sql = new StringBuilder().append("SELECT * FROM musicians");
        StringBuilder result = new StringBuilder();
        try {
            ResultSet rs = stmt.executeQuery(String.valueOf(sql));
            result.append("List of musicians:#");
//            System.out.println("List of musicians:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                result.append(" >> ").append(id).append("-").append(name).append("#");
                System.out.println(" >> " + id + " - " + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
        return result.toString();
    }
    public static void findMusicianByName(String name) {
        String sql = MessageFormat.format("SELECT FROM musicians WHERE name = {0}", name);
        try {
            ResultSet rs = stmt.executeQuery(String.valueOf(sql));
            System.out.println("List of musicians:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String Mname = rs.getString("name");
                System.out.println(" >> " + id + " - " + Mname);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(
                    "Exeption");
            System.out.println(" >> " + e.getMessage());
        }
    }

    public static void addAlbum(String newName, int numOfSongs, int numOfCopies, String type, int musID) {
        String sql = "insert into albums (name, num_of_songs,num_of_copies,type,artist_id) values(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, newName);
            pstmt.setInt(2, numOfSongs);
            pstmt.setInt(3, numOfCopies);
            pstmt.setString(4, type);
            pstmt.setInt(5, musID);
            pstmt.executeUpdate();
            System.out.println("Album " + newName + " successfully added!");
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
    }


    public static String deleteAlbum(String name) {
        String sql = MessageFormat.format("DELETE FROM albums WHERE name = {0}", name);
        String response = null;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                response = "Album " + name + " successfully deleted!";
            } else {
                response = "Album " + name + " not found";
            }
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
       return response;
    }
    public static String deleteAlbumByID(int id) {
        String sql = MessageFormat.format("DELETE FROM albums WHERE id = {0}", id);
        String response = null;
        try {
            int c = stmt.executeUpdate(sql);
            if (c > 0) {
                response = "Album " + id + " successfully deleted!";
            } else {
                response = "Album " + id + " not found";
            }
        } catch (SQLException e) {
            System.out.println("Exeption");
            System.out.println(" >> " + e.getMessage());
        }
        return response;
    }
    public static void updateAlbum() {
        Scanner scanner = new Scanner(System.in);
        String sql = null, newInfo = null;
        int newIntInfo = 0;
        boolean flag = false;
        System.out.println("Enter name of album you want to update:");
        String name = scanner.nextLine();
        System.out.println("""
                What field you want to update:
                1.Name
                2.Number of songs
                3.Number of copies
                4.type
                5.artist id""");
        int field = scanner.nextInt();
        switch (field) {
            case 1 -> {
                System.out.println("Enter new name:");
                newInfo = scanner.nextLine();
                sql = "UPDATE albums "
                        + "SET name = ? "
                        + "WHERE name = ?";
            }
            case 2 -> {
                System.out.println("Enter new number of songs:");
                newIntInfo = scanner.nextInt();
                sql = "UPDATE albums "
                        + "SET num_of_songs = ? "
                        + "WHERE name = ?";
                flag = true;
            }
            case 3 -> {
                System.out.println("Enter new number of copies:");
                newIntInfo = scanner.nextInt();
                sql = "UPDATE albums "
                        + "SET num_of_copies = ? "
                        + "WHERE name = ?";
                flag = true;
            }
            case 4 -> {
                do {
                    System.out.println("Enter new type:");
                    newInfo = scanner.nextLine();
                } while (!Objects.equals(newInfo, "mini") && !Objects.equals(newInfo, "full"));
                sql = "UPDATE albums "
                        + "SET type = ? "
                        + "WHERE name = ?";
            }
            case 5 -> {
                System.out.println("Enter new artist id:");
                newIntInfo = scanner.nextInt();
                sql = "UPDATE albums "
                        + "SET num_of_copies = ? "
                        + "WHERE name = ?";
                flag = true;
            }
        }

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (!flag) {
                pstmt.setString(1, newInfo);
                pstmt.setString(2, name);
            } else {
                pstmt.setInt(1, newIntInfo);
                pstmt.setString(2, name);
            }
            pstmt.executeUpdate();
            System.out.println(name + "'s info successfully updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String showAlbums() {
        StringBuilder sql = new StringBuilder().append("SELECT * FROM albums");
        StringBuilder result = new StringBuilder();
        try {
            ResultSet rs = stmt.executeQuery(String.valueOf(sql));
//            System.out.println("List of albums:");
            result.append("List of albums:#");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int numOfSongs = rs.getInt("num_of_songs");
                int numOfCopies = rs.getInt("num_of_copies");
                String type = rs.getString("type");
                result.append(" >> ").append(id).append("-").append(name).append("-").append(numOfSongs)
                        .append("-").append(numOfCopies).append("-").append(type).append("#");
                System.out.println(" >> " + id + " - " + name + " - " + numOfSongs + " - " + numOfCopies + " - " + type);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(
                    "Exeption");
            System.out.println(" >> " + e.getMessage());
        }
        return result.toString();
    }
}
