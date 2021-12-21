package com.company.get_school;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;

public class GetList {
    public static String schools_url = "https://sluzby.bakalari.cz/api/v1/municipality";
    public static ArrayList<String> list_of_schools = new ArrayList<>();

    public GetList(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(schools_url);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("municipalityInfo");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String school = eElement.getElementsByTagName("name").item(0).getTextContent();
                    list_of_schools.add(school);
                    System.out.println(school);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list_of_schools);
    }
}