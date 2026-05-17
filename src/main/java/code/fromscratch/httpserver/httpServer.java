package code.fromscratch.httpserver;

import code.fromscratch.httpserver.config.Configuration;
import code.fromscratch.httpserver.config.ConfigurationManager;

//Driver class for HTTP server
public class httpServer {
    public static void main(String[] args){
        System.out.println("Severe Starting");
//        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        ConfigurationManager.getInstance().loadConfiguration(
                "/Users/lynux/Desktop/BackEnd/Web Server/webserver/src/main/resources/http.json"
        );
        Configuration conf = ConfigurationManager.getInstance().getConfiguration();


        System.out.println("Using port : " + conf.getPort());
        System.out.println("Using Webroot : " + conf.getWebroot());
    }
}
