package code.fromscratch.httpserver.config;

import code.fromscratch.httpserver.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){

    }
    public static ConfigurationManager getInstance(){
        if(myConfigurationManager == null){
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }
    //use to load configuration file path
    public void loadConfiguration(String filepath) {
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(filepath);
        }
        catch (FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try{
            while((i = fileReader.read())!= -1){
                sb.append((char)i);
            }
        }catch (IOException e){
//            e.printStackTrace();
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            throw new HttpConfigurationException("Error Parsing the file",e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
            throw new HttpConfigurationException("Error parsing the Configuration",e);
        }
    }
    //return current loaded configuration
    public Configuration getConfiguration(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No current configuration found");
        }
        return myCurrentConfiguration;
    }
}
