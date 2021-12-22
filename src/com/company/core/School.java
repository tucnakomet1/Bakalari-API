package com.company.core;

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

public class School {
    public static String city;
    public static ArrayList<String> list_of_NAMEs = new ArrayList<>();
    public static ArrayList<String> list_of_URLs = new ArrayList<>();
    public static String school_url = "https://sluzby.bakalari.cz/api/v1/municipality/";

    public static String encoded_school(String school) {
        school = URLEncoder.encode(school, StandardCharsets.UTF_8);
        school_url = (school_url + school).replaceAll("\\+", "%20");

        return school_url;
    }

    public static ArrayList<ArrayList<String>> getSchools(String school) {
        try {
            school_url = encoded_school(school);

            // creating factory for scraping JSON file
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(school_url);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("schoolInfo");

            // scraping the JSON file
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    String school_name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String school_Url = eElement.getElementsByTagName("schoolUrl").item(0).getTextContent();

                    list_of_NAMEs.add(school_name);
                    list_of_URLs.add(school_Url);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(list_of_NAMEs);
        result.add(list_of_URLs);

        return result;
    }

    public School(){
        ArrayList<ArrayList<String>> schools = getSchools(city);
        for (int i = 0; i < schools.get(0).size(); i++) {
            System.out.println(schools.get(0).get(i));
            System.out.println(schools.get(1).get(i));
        }
    }
}
