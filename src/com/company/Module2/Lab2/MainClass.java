package com.company.Module2.Lab2;

public class MainClass {

    public static void main(String[] args) throws Exception {
        utility ut = new utility();
        utility.showMusicians();
//        utility.addMusician("Veselov");
//        utility.updateMusician("Ivanov","Ivanchenkov");
        utility.showAlbums();
        utility.deleteMusician(7);
        utility.showMusicians();
        utility.showAlbums();
//        utility.addAlbum("Tree",55,676,"mini",7);
//        utility.updateAlbum();
//        utility.deleteAlbum("moss");
//        utility.showAlbums();
//        utility.findMusicianByName("Sidorov");
        ut.stop();
    }
}
