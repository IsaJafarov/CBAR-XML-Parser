import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private boolean isValCurs = false;
    private boolean isValType = false;
    private boolean isValute = false;
    private boolean isNominal = false;
    private boolean isName = false;
    private boolean isValue = false;
    private Valute valute;
    private LocalDate date;
    private String type;
    private List<Valute> valuteList = new LinkedList<>();

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("---------Document starts---------");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println( qName + " starts" );

        if ( qName.equals("ValCurs") ){
            isValCurs = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse( attributes.getValue("Date"), formatter );
        }
        else if ( qName.equals("ValType") ){
            isValType = true;
            type = attributes.getValue("Type");
        }
        else if ( qName.equals("Valute") ){
            isValute = true;
            valute = new Valute();
            valute.setCode( attributes.getValue("Code") );
        }
        else if ( qName.equals("Nominal") )
            isNominal = true;
        else if ( qName.equals("Name") )
            isName = true;
        else if ( qName.equals("Value") )
            isValue = true;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = String.copyValueOf( ch, start, length );

        if ( isValCurs ){
            if ( isValType ){
                if ( isValute ){
                    valute.setDate( date );
                    valute.setType( type );
                    if ( isNominal ){
//                        System.out.println( "nominal = "+data );
                        valute.setNominal( data );
                    }
                    else if ( isName ){
//                        System.out.println( "name = "+data );
                        valute.setName( data );
                    }
                    else if ( isValue ){
//                        System.out.println( "value = "+data );
                        valute.setValue( new BigDecimal( data ));
                    }
                }
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println(qName + " ends");

        if ( qName.equals("ValCurs") )
            isValCurs = false;
        else if ( qName.equals("ValType") )
            isValType = false;
        else if ( qName.equals("Valute") ){
            isValute = false;
            valuteList.add( valute );
        }
        else if ( qName.equals("Nominal") )
            isNominal = false;
        else if ( qName.equals("Name") )
            isName = false;
        else if ( qName.equals("Value") )
            isValue = false;

    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("---------Document ends---------");
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }
}
