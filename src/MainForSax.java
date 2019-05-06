import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainForSax {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MyHandler handler = new MyHandler();
            parser.parse(new URL("https://www.cbar.az/currencies/18.03.2019.xml").openConnection().getInputStream(), handler);

            List<Valute> valuteList = handler.getValuteList();

            System.out.println( "SAX Parser" );
            for (Valute valute: valuteList)
                System.out.println( valute +"\n");



        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }
}
