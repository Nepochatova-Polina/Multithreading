package com.company.Module2.Lab2;

import java.sql.*;
import java.text.MessageFormat;

public class MainClass {

    public static void main(String[] args) throws Exception {
        utility ut = new utility();
//        utility.addMusician("Laverov");
//        utility.showCMusicians();
//        utility.showCMusicians();
//        utility.updateMusician("Vasilyov","Ivanov");
//        utility.showMusicians();
//        utility.addAlbum("moss",55,676,"mini",7);
//        utility.showMusicians();
        utility.showAlbums();
        ut.stop();
    }
}
