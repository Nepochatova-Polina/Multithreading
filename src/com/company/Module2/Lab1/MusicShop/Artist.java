package com.company.Module2.Lab1.MusicShop;

import java.util.ArrayList;

public class Artist {
    private String name;
    private ArrayList<Album> albums = new ArrayList<>();

    public Artist() {

    }
    public Artist(String name, ArrayList<Album> albums) {
        this.name = name;
        this.albums = albums;
    }


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public ArrayList<Album> getAlbums() {return albums;}
    public void setAlbums(Album album) {this.albums.add(album);}
}
