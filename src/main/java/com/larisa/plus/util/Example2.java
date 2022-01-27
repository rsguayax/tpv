package com.larisa.plus.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map.Entry;

public class Example2 {
    public static void main(String[] args) throws IOException {
        //read json file data to String
        String columna_json = "{\"inicio\":{\"id\":1, \"nombre\":\"test\"}}";
        System.out.println(columna_json);
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(columna_json.getBytes());
        JsonNode nosNode = rootNode.path("inicio");
//      Iterator elements = nosNode.elements();
        Iterator<Entry<String, JsonNode>> elements = nosNode.fields();
        System.out.println("KEY\tVALUE\tPATH");
        recorre2(elements, "");
    }

    public static void recorre(Iterator elements, String path) {
        while (elements.hasNext()) {
            Entry<String, JsonNode> e = (Entry<String, JsonNode>) elements.next();
            if (e.getValue().isTextual())
                System.out.println(e.getKey() + "\t" + e.getValue() + "\t" + path+e.getKey()+"/");
            else if (e.getValue().isArray())
                for (int i = 0; i < e.getValue().size(); i++)
                    recorre((Iterator<Entry<String, JsonNode>>) e.getValue().get(i).fields(), path+e.getKey()+ "/"+i +"/");
            else if (e.getValue().isObject())
                recorre(e.getValue().fields(), path+e.getKey()+"/");
        }
    }

    public static void recorre2(Iterator elements, String path) {
        while (elements.hasNext()) {
            Entry<String, JsonNode> e = (Entry<String, JsonNode>) elements.next();
            if (e.getKey().equals("docs")) {
                System.out.println("");
            }
            if (e.getValue().isTextual() || e.getValue().isNumber()) {
                System.out.println(e.getKey() + "\t" + e.getValue() + "\t" + path + e.getKey() + "/");
            } else if (e.getValue().isArray()) {
                for (int i = 0; i < e.getValue().size(); i++) {
                    if (e.getValue().get(i).isObject()) {
                        recorre((Iterator<Entry<String, JsonNode>>) e.getValue().get(i).fields(), path + e.getKey() + "/" + i + "/");
                    }else {
                        System.out.println(e.getKey() + "\t" + e.getValue().get(i) + "\t" + path + e.getKey() + "/" + i + "/");
                    }
                }
            } else if (e.getValue().isObject()) {
                recorre(e.getValue().fields(), path + e.getKey() + "/");
            }
        }
    }
}