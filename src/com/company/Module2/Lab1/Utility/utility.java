package com.company.Module2.Lab1.Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.company.Module2.Lab1.MusicShop.Album;
import com.company.Module2.Lab1.MusicShop.Artist;
import com.company.Module2.Lab1.MusicShop.MusicShop;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class utility {
    public static MusicShop shop = new MusicShop();

    public static void ReadXML() throws IOException {
        try {
            File inputFile = new File("src/com/company/Module2/Lab1/resources/MusicShop.xml");
            if (inputFile.exists()) {
                System.out.println("File exists");
            } else {
                System.out.println("Error");
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new SimpleErrorHandler());
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Item");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element artistElement = (Element) nNode;
                    Artist artist = new Artist();
                    Album album = new Album();
                    NodeList albums = artistElement.getElementsByTagName("Album");
                    artist.setName(artistElement.getElementsByTagName("artistName").item(0).getTextContent());
                    for (int x = 0; x < albums.getLength(); x++) {
                        Node albumNode = albums.item(x);
                        if (albumNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element albumElement = (Element) albumNode;
                            album.setId(Integer.parseInt(albumElement.getElementsByTagName("albumID").item(0).getTextContent()));
                            album.setName(albumElement.getElementsByTagName("albumName").item(0).getTextContent());
                            album.setNumOfCopies(Integer.parseInt(albumElement.getElementsByTagName("numOfCopies").item(0).getTextContent()));
                            album.setNumOfSongs(Integer.parseInt(albumElement.getElementsByTagName("numOfSongs").item(0).getTextContent()));
                            album.setType(albumElement.getElementsByTagName("type").item(0).getTextContent());
                            artist.setAlbums(album);
                        }
                    }
                    shop.setArtist(artist);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AddIntoXML() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("MusicShop");
            doc.appendChild(rootElement);
            ArrayList<Album> albums = new ArrayList<>();
            albums.add(new Album(44444, "Album1", 7, 333, "mini", "Vasiliev"));
            albums.add(new Album(55555, "Album2", 5, 343, "mini", "Vasiliev"));
            albums.add(new Album(66666, "Album3", 12, 433, "full", "Vasiliev"));
            Artist artist = new Artist("Vasiliev", albums);
            shop.setArtist(artist);

            for (Artist artist1 : shop.getArtist()) {
                rootElement.appendChild(setArtist(doc, artist1));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("src/com/company/Module2/Lab1/resources/MusicShop.xml"));
            transformer.transform(source, file);
            System.out.println("DONE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromXML() {
        Scanner scanner = new Scanner(System.in);
        String aName = null, albumName = null;
        int x = 0;
        while (x < 1 || x > 2) {
            System.out.println("""
                    Delete:
                    1.Artist
                    2.Album
                    """);
            x = scanner.nextInt();
        }
        switch (x) {
            case 1 -> {
                System.out.println("Enter artist's name:");
                aName = scanner.next();
            }
            case 2 -> {
                System.out.println("Enter artist's name:");
                aName = scanner.next();
                System.out.println("Enter album's name:");
                albumName = scanner.next();
            }
            default -> {
            }
        }
        ArrayList<Artist> currentArtist = shop.getArtist();
        if (albumName != null) {
            for (int i = 0; i < currentArtist.size(); i++) {
                Artist artist = currentArtist.get(i);
                if (Objects.equals(artist.getName(), aName)) {
                    for (int j = 0; j < artist.getAlbums().size(); j++) {
                        Album album1 = artist.getAlbums().get(j);
                        if (Objects.equals(album1.getName(), albumName)) {
                            if (artist.getAlbums().size() != 1) {
                                artist.getAlbums().remove(j);
                            } else {
                                for (i = 0; i < currentArtist.size(); i++) {
                                    artist = currentArtist.get(i);
                                    if (Objects.equals(artist.getName(), aName)) {
                                        shop.getArtist().remove(i);
                                    }
                                }
                            }
                        }
                    }

                }
            }
            shop.setArtist(currentArtist);
        } else {
            for (int i = 0; i < currentArtist.size(); i++) {
                Artist artist = currentArtist.get(i);
                if (Objects.equals(artist.getName(), aName)) {
                    shop.getArtist().remove(i);
                }
            }
        }
    }



    private static Node setArtist(Document doc, Artist x) {
        Element item = doc.createElement("Item");
        ArrayList<Album> albums = x.getAlbums();
        item.appendChild(getItemElements(doc, item, "artistName", x.getName()));
        Element albumsItem = doc.createElement("Albums");
        for (Album album : albums) {
            Element albumItem = doc.createElement("Album");
            albumItem.appendChild(getItemElements(doc, albumItem, "albumID", String.valueOf(album.getId())));
            albumItem.appendChild(getItemElements(doc, albumItem, "albumName", album.getName()));
            albumItem.appendChild(getItemElements(doc, albumItem, "numOfSongs", String.valueOf(album.getNumOfSongs())));
            albumItem.appendChild(getItemElements(doc, albumItem, "numOfCopies", String.valueOf(album.getNumOfCopies())));
            albumItem.appendChild(getItemElements(doc, albumItem, "type", album.getType()));
            albumsItem.appendChild(albumItem);
        }
        item.appendChild(albumsItem);
        return item;
    }


    private static Node getItemElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


    public static void writeIntofile(ArrayList<Artist> artists) throws IOException {
        FileWriter myWriter = new FileWriter("src/com/company/Module2/Lab1/resources/MusicShop.txt");
        for (Artist artist : artists) {
            ArrayList<Album> albums = artist.getAlbums();
            myWriter.write("Artist Name: " + artist.getName() + "\n");
            myWriter.write("Albums\n");
            for (Album album : albums) {
                myWriter.write("Album Name: " + album.getName() + "\n");
                myWriter.write("ID: " + album.getId() + "\n");
                myWriter.write("Type: " + album.getType() + "\n");
                myWriter.write("Num of songs: " + album.getNumOfSongs() + "\n");
                myWriter.write("Num of Copies: " + album.getNumOfCopies() + "\n");
                myWriter.write("\n");
            }
            myWriter.write("\n");
        }
        myWriter.close();
    }
}


