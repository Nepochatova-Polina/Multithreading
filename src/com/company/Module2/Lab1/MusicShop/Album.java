package com.company.Module2.Lab1.MusicShop;

public class Album {
    private int id;
    private String name;
    private int numOfSongs;
    private int numOfCopies;
    private String type;

    public Album() {

    }
    public Album(int id, String name, int numOfSongs, int numOfCopies, String type, String artist) {
        this.id = id;
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.numOfCopies = numOfCopies;
        this.type = type;
    }


    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public int getNumOfSongs() {return this.numOfSongs;}
    public void setNumOfSongs(int numOfSongs) {this.numOfSongs = numOfSongs;}

    public int getNumOfCopies() {return this.numOfCopies;}
    public void setNumOfCopies(int numOfCopies) {this.numOfCopies = numOfCopies;}

    public String getType() {return this.type;}
    public void setType(String type) {this.type = type;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
}
