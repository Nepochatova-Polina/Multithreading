package com.company.Module2.Lab1.Utility;

import com.company.Module2.Lab1.MusicShop.Album;
import com.company.Module2.Lab1.MusicShop.Artist;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Update {
    public static void updateInfo() {
        ArrayList<Artist> artists = utility.shop.getArtist();
        Scanner scanner = new Scanner(System.in);
        String artist, newName, albumName;
        System.out.println("Enter the name of the artist you want to update");
        artist = scanner.next();
        int x = 0, y = 0;
        while (x < 1 || x > 2) {
            System.out.println("""
                    You want to update
                    1.Artist's info
                    2.Album's info
                    """);
            x = scanner.nextInt();
        }
        switch (x) {
            case 1 -> {
                System.out.println("Enter new artist's name:");
                newName = scanner.next();
                for (int i = 0; i < artists.size(); i++) {
                    Artist artist1 = artists.get(i);
                    if (Objects.equals(artist1.getName(), artist)) {
                        artist1.setName(newName);
                    }
                    utility.shop.setArtist(artists);
                }
            }
            case 2 -> {
                System.out.println("Enter album's name:");
                albumName = scanner.next();
                System.out.println("""
                        You want to update
                        1.Album's name
                        2.Album's id
                        3.Album's number of songs
                        4.Album's number of copies
                        5.Album's type
                        """);
                y = scanner.nextInt();
                updateAlbumInfo(artist, albumName, y);
            }
            default -> {
            }
        }
    }

    public static void updateAlbumInfo(String artist, String album, int x) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Artist> artists = utility.shop.getArtist();
        String newInfo = null;
        int newNumInfo;
        switch (x) {
            case 1 -> {
                System.out.println("Enter new album's name");
                newInfo = scanner.next();
                for (Artist artist1 : artists) {
                    if (Objects.equals(artist1.getName(), artist)) {
                        for (int j = 0; j < artist1.getAlbums().size(); j++) {
                            Album album1 = artist1.getAlbums().get(j);
                            if (Objects.equals(album1.getName(), album)) {
                                album1.setName(newInfo);
                            }
                        }
                    }
                }
            }
            case 2 -> {
                System.out.println("Enter new album's id");
                newNumInfo = scanner.nextInt();
                for (Artist artist1 : artists) {
                    if (Objects.equals(artist1.getName(), artist)) {
                        for (int j = 0; j < artist1.getAlbums().size(); j++) {
                            Album album1 = artist1.getAlbums().get(j);
                            if (Objects.equals(album1.getName(), album)) {
                                album1.setId(newNumInfo);
                            }
                        }
                    }
                }
            }
            case 3 -> {
                System.out.println("Enter new album's number of songs");
                newNumInfo = scanner.nextInt();
                for (Artist artist1 : artists) {
                    if (Objects.equals(artist1.getName(), artist)) {
                        for (int j = 0; j < artist1.getAlbums().size(); j++) {
                            Album album1 = artist1.getAlbums().get(j);
                            if (Objects.equals(album1.getName(), album)) {
                                album1.setNumOfSongs(newNumInfo);
                            }
                        }
                    }
                }
            }
            case 4 -> {
                System.out.println("Enter new album's number of copies");
                newNumInfo = scanner.nextInt();
                for (Artist artist1 : artists) {
                    if (Objects.equals(artist1.getName(), artist)) {
                        for (int j = 0; j < artist1.getAlbums().size(); j++) {
                            Album album1 = artist1.getAlbums().get(j);
                            if (Objects.equals(album1.getName(), album)) {
                                album1.setNumOfCopies(newNumInfo);
                            }
                        }
                    }
                }
            }
            case 5 -> {
                System.out.println("Enter new album's type");
                newInfo = scanner.next();
                for (Artist artist1 : artists) {
                    if (Objects.equals(artist1.getName(), artist)) {
                        for (int j = 0; j < artist1.getAlbums().size(); j++) {
                            Album album1 = artist1.getAlbums().get(j);
                            if (Objects.equals(album1.getName(), album)) {
                                album1.setType(newInfo);
                            }
                        }

                    }

                }
            }
            default -> {
            }
        }
        utility.shop.setArtist(artists);

    }
}
