package com.company.get_school;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GetSpecificCity {
    public static ArrayList<String> list_of_schools = GetList.list_of_schools;
    public static ArrayList<String> list_of_IDs = new ArrayList<>();
    public static ArrayList<String> list_of_NAMEs = new ArrayList<>();
    public static ArrayList<String> list_of_URLs = new ArrayList<>();
    public static String school_url = "https://sluzby.bakalari.cz/api/v1/municipality/";


    public GetSpecificCity(String school){
        if (list_of_schools.isEmpty()) {
            System.out.println("Oh");
            new GetList();
            list_of_schools = GetList.list_of_schools;
        }
        System.out.println("\n\n\n" + school);
        try {
            school = URLEncoder.encode(school, StandardCharsets.UTF_8);
            System.out.println(school);

            school_url = (school_url + school).replaceAll("\\+", "%20");


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(school_url);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("schoolInfo");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String school_id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String school_name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String school_Url = eElement.getElementsByTagName("schoolUrl").item(0).getTextContent();
                    list_of_IDs.add(school_id);
                    list_of_NAMEs.add(school_name);
                    list_of_URLs.add(school_Url);
                    System.out.println(school_id);
                    System.out.println(school_name);
                    System.out.println(school_Url + "\n");
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(list_of_IDs);
        System.out.println(list_of_NAMEs);
        System.out.println(list_of_URLs);
    }
}
