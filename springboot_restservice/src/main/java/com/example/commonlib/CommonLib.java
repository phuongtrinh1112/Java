package com.example.commonlib;

import com.example.objects.Associate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class CommonLib {


        //Read properties file with Java properties
        public HashMap readPropertiesFile(String filePath){
            HashMap propertiesDic = new HashMap();
            Properties prop = new Properties();
            File file = null;
            InputStream input = null;
            try {
                file = new File(filePath);
                input = new FileInputStream(file);
                prop.load(input);
                prop.putAll(propertiesDic);
                for (Map.Entry<Object, Object> entry : prop.entrySet()){
                    propertiesDic.put((String) entry.getKey(), (String) entry.getValue());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return propertiesDic;
        }

        //Reade Json file
        public HashMap readJsonFile(String filePath){
            File file = new File(filePath);
            Scanner sc;
            HashMap dictionary = new HashMap();
            try {
                sc = new Scanner(file);
                while (sc.hasNextLine()){
                    String[] lineContent = sc.nextLine().split(":");
                    dictionary.put(lineContent[0], lineContent[1]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return dictionary;
        }

    //Convert Json to list object
    public List<Associate> convertJsonToObject(String json){
        Gson gson = new Gson();
        Associate associate1 = gson.fromJson(json, Associate.class);
        List<Associate> associates =  Arrays.asList(associate1);
        return associates;
    }

    //Convert list object to json using gson
    public String convertObjectToJson(List<Associate> associates){
        Gson gson = new Gson();
        return gson.toJson(associates);
    }

    //Convert Json to Map use Jackson library
    public Map convertJsonToMap(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Map keyValue = null;
        try {
            keyValue = objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyValue;
    }

    //Convert Map to Json use gson
    public String convertMapToJson(Map aMap){
        Gson gson = new Gson();
        Type gsonType = new TypeToken<Map>(){}.getType();
        return gson.toJson(aMap,gsonType);
    }
}
