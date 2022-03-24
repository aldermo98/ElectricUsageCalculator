package edu.csus.csc131.euc.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONtoJava {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON file to Java object
            JSONdata data = mapper.readValue(new File("dailyElectricityUsage_2020_02_28.json"), JSONdata.class);
            System.out.println(data.toString());
            
            //get a specific variable from the json and print it
            System.out.println(data.getSiteTimeZoneId());
            
            //JSON-like print
            String prettyData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            System.out.println(prettyData);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}