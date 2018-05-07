import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.events.XMLEvent;;


public class readXML {

    public static ArrayList<String[]> readingXML(String pathName) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

          ArrayList<String[]> list = new ArrayList<>();
          list.add(new String[] {"nameElement", "nameAttribute", "valueAttribute", "textElement", "marker"});

          XMLInputFactory factory2 = XMLInputFactory.newInstance();
          XMLStreamReader parser2 = factory2.createXMLStreamReader(new FileInputStream(pathName));

          while (parser2.hasNext()) {
              //int event = parser2.next();
              if (parser2.getEventType() == XMLStreamConstants.START_ELEMENT) {
       //           System.out.println(parser2.getLocalName());
                  String[] strings = new String[5];
                  strings[0] = parser2.getLocalName();

                  for (int j = 0; j < parser2.getAttributeCount(); j++) {
                      //System.out.println(parser2.getAttributeName(j));
                      strings[1] = parser2.getAttributeLocalName(j);
                      strings[2] = parser2.getAttributeValue(j);
                      strings[3] = "";
                      strings[4] = "withAttribute";
                  }


                  try {
                      //System.out.println(parser2.getElementText());
                      strings[3] = parser2.getElementText();
                      strings[1] = "";
                      strings[2] = "";
                      strings[4] = "withText";
                  }catch (Exception e){
                      //System.out.println("ошибка");
                  }


                  list.add(strings);
              }
              else parser2.next();

          }

//        for (int i = 0; i < list.size(); i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(list.get(i)[j] + " ");
//            }
//            System.out.println();
//        }

        return list;
    }
}
