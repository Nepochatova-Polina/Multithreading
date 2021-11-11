package com.company.Module2.Lab1;
import com.company.Module2.Lab1.Utility.Update;
import com.company.Module2.Lab1.Utility.utility;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException {

        utility.ReadXML();
        utility.deleteFromXML();
        Update.updateInfo();
        utility.AddIntoXML();
        utility.writeIntofile(utility.shop.getArtist());
    }
}
