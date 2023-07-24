package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class XMLParsing {

    public static void main(String[] args) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;

        {
            try {
                builder = factory.newDocumentBuilder();

                Document document = builder.parse(new File("intrebaria.xml"));

                document.getDocumentElement().normalize();

                NodeList problems = document.getElementsByTagName("S");


                for(int i = 0; i < 1; i++){

                    Node problem = problems.item(i);

                    if(problem.getNodeType() == Node.ELEMENT_NODE){

                        Element problemElement = (Element) problem;

                        NodeList words = problem.getChildNodes();

                        for(int j = 0; j < words.getLength(); j++){

                            Node word = words.item(j);

                            if(word.getNodeType() == Node.ELEMENT_NODE){

                                Element wordElement = (Element) word;



                            }

                        }

                    }

                }

                System.out.println(problems.getLength());


            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
