import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainForDom {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse( new URL("https://www.cbar.az/currencies/18.03.2019.xml").openConnection().getInputStream() );

            Element documentElement = document.getDocumentElement();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateStr = documentElement.getAttribute("Date");
            LocalDate date = LocalDate.parse( dateStr, formatter );

            NodeList ValTypeNodeList = documentElement.getElementsByTagName("ValType");
            List<Valute> ValuteList = new ArrayList<>();

            for ( int i=0; i<ValTypeNodeList.getLength(); i++ ){
                Element ValTypeElement;
                if ( ValTypeNodeList.item(0) instanceof Element ) {
                    ValTypeElement = (Element) ValTypeNodeList.item(i);
                    String type = ValTypeElement.getAttribute("Type");
                    NodeList ValuteNodeList = ValTypeNodeList.item(i).getChildNodes();

                    for (int j = 0; j < ValuteNodeList.getLength(); j++) {
                        Node ValuteNode = ValuteNodeList.item(j);
                        Element ValuteElement;
                        if (ValuteNode instanceof Element) {
                            ValuteElement = (Element) ValuteNode;

                            Valute valute = new Valute();
                            valute.setDate(date);
                            valute.setType(type);
                            valute.setCode(ValuteElement.getAttribute("Code"));
                            valute.setNominal(ValuteElement.getElementsByTagName("Nominal").item(0).getTextContent());
                            valute.setName(ValuteElement.getElementsByTagName("Name").item(0).getTextContent());
                            valute.setValue(new BigDecimal(ValuteElement.getElementsByTagName("Value").item(0).getTextContent()));

                            ValuteList.add(valute);
                        }
                    }
                }
            }

            System.out.println( "DOM Parser" );
            for ( Valute valute: ValuteList )
                System.out.println(valute+"\n");


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }
}
