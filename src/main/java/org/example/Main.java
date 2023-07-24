package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("intrebaria2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList sList = doc.getElementsByTagName("S");
            Map<String, Integer> posSequences = new HashMap<>();
            Map<String, String> exampleSentences = new HashMap<>();

            for (int temp = 0; temp < sList.getLength(); temp++) {
                Node sNode = sList.item(temp);
                if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList wList = ((Element) sNode).getElementsByTagName("W");
                    StringBuilder sequenceBuilder = new StringBuilder();
                    StringBuilder sentenceBuilder = new StringBuilder();
                    for (int i = 0; i < wList.getLength(); i++) {
                        Node wNode = wList.item(i);
                        if (wNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element wElement = (Element) wNode;
                            String currentPos = wElement.getAttribute("POS");
                            String word = wElement.getTextContent();
                            sequenceBuilder.append(currentPos).append(" ");
                            sentenceBuilder.append(word).append(" ");
                        }
                    }
                    String sequence = sequenceBuilder.toString().trim();
                    String sentence = sentenceBuilder.toString().trim();
                    posSequences.put(sequence, posSequences.getOrDefault(sequence, 0) + 1);
                    if (!exampleSentences.containsKey(sequence)) {
                        exampleSentences.put(sequence, sentence);
                    }
                }
            }

            Map<String, Integer> sortedSequences = posSequences.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (String sequence : sortedSequences.keySet()) {
                System.out.println("POS sequence: " + sequence);
                System.out.println("Frequency: " + sortedSequences.get(sequence));
                System.out.println("Example sentence: " + exampleSentences.get(sequence));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
