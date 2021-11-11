package com.company.Module2.Lab1.MusicShop;

import java.util.ArrayList;

public class MusicShop {
    private ArrayList<Artist> artist = new ArrayList<>();

    public ArrayList<Artist> getArtist() {return this.artist;}
    public void setArtist(Artist artist) {this.artist.add(artist);}
    public void setArtist(ArrayList<Artist> artist) {this.artist = artist;}
}
