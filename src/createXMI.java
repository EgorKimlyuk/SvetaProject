import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;
import org.w3c.dom.*;
import java.util.*;
import java.io.*;

public class createXMI {

        public static void creatingXMI(ArrayList<String[]> list) throws Exception {

            Document doc = new DocumentImpl();

            Element xmi = doc.createElement("uml:Model");
            xmi.setAttribute("xmlns:uml", "http://www.omg.org/spec/UML/20090901");
            xmi.setAttribute("xmlns:xmi", "http://schema.omg.org/spec/XMI/2.1");
            xmi.setAttribute("xmi:version", "2.1");
            xmi.setAttribute("xmi:id", "_gUAagE6xEei0gMHq39tLdA");
            xmi.setAttribute("name", "БухгалтерияПредприятия");


            //for (int i = 2; i < list.size(); i++) {
            Element packagedElement = doc.createElement("tagName");
            for (int i = 2; i < list.size(); i++) {
        //    for (int i = 9036; i < list.size(); i++) {
                if (list.get(i)[0] == "CatalogObject.Конфигурации" || list.get(i)[0] == "CatalogObject.Объекты" || list.get(i)[0] == "CatalogObject.Свойства") {

                    packagedElement = doc.createElement("packagedElement");
                    packagedElement.setAttribute("xmi:type", "uml:Class");
                    packagedElement.setAttribute("xmi:id", "_gUAahE6xEei0gMHq39tLdA");
                    if (list.get(i)[0] == "CatalogObject.Конфигурации")
                        packagedElement.setAttribute("name", "CatalogObject.Конфигурации");
                    else if (list.get(i)[0] == "CatalogObject.Объекты")
                        packagedElement.setAttribute("name", "CatalogObject.Объекты");
                    else
                        packagedElement.setAttribute("name", "CatalogObject.Свойства");
                }
                else
                {
                    int j = i;
                    while (list.get(j)[0] != "CatalogObject.Конфигурации" && list.get(j)[0] != "CatalogObject.Объекты" && list.get(j)[0] != "CatalogObject.Свойства" && j < list.size() - 1) {

                        Element ownedAttribute = doc.createElement("ownedAttribute");
                        ownedAttribute.setAttribute("xmi:type", "uml:Property");
                        ownedAttribute.setAttribute("xmi:id", "_gUAah06xEei0gMHq39tLdA");
                        ownedAttribute.setAttribute("name", list.get(j)[0]); //Ref
                        ownedAttribute.setAttribute("visibility", "public");
                        ownedAttribute.setAttribute("isUnique", "false");

                        Element typeMy = doc.createElement("type");
                        typeMy.setAttribute("xmi:type", "uml:PrimitiveType");
                        typeMy.setAttribute("href", "http://schema.omg.org/spec/20090901/UML.xmi#String");
                        ownedAttribute.appendChild(typeMy);

                        if (list.get(j)[3] != "") {
                            Element defaultValue = doc.createElement("defaultValue");
                            defaultValue.setAttribute("xmi:type", "uml:LiteralString");
                            defaultValue.setAttribute("xmi:id", "_iKlKTE9kEeipSNTt5OS0cQ");
                            defaultValue.setAttribute("value", list.get(j)[3]);
                            ownedAttribute.appendChild(defaultValue);
                        }

                        packagedElement.appendChild(ownedAttribute);
                        j++;
                    }
                    xmi.appendChild(packagedElement);
                    if (j < list.size() - 1) {
                        i = j - 1;
                    }
                }

//                    //System.out.print(list.get(i)[j] + " ");

//                //System.out.println();
            }

            doc.appendChild(xmi);

//            for (int i = 0; i < list.size(); i++) {
//                for (int j = 0; j < 5; j++) {
//                    //System.out.print(list.get(i)[j] + " ");
//                }
//                //System.out.println();
//            }





//            for (int i = 0; i < list.size(); i++) {
//                for (int j = 0; j < 5; j++) {
//                    System.out.print(list.get(i)[j] + " ");
//                }
//                System.out.println();
//            }



// Create the XMI document from the parse tree. See the XML4J
// parser documentation for more details.
            OutputFormat format = new OutputFormat(doc, "UTF-8", true);
            FileWriter file = new FileWriter("DOMWriteGUI.xmi");
            XMLSerializer serial = new XMLSerializer(file, format);
            serial.asDOMSerializer();
            serial.serialize(doc.getDocumentElement());
            file.close();
        }

}
