package code.fromscratch.httpserver.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper myObjectMapper = defaultObjectMapper();
    private static ObjectMapper defaultObjectMapper() {
       ObjectMapper m = new ObjectMapper();
       m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
       return m;
    }
    public static JsonNode parse(String jsonSrc) throws IOException {
        return  myObjectMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node,Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node,clazz);
    }
    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringyfy(JsonNode node) throws JsonProcessingException {
        return generateJson(node , false);

    }
    public static String stringyfyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node , false);

    }

    private static String generateJson(Object o,boolean pretty) throws JsonProcessingException {
        ObjectWriter objectwriter = myObjectMapper.writer();
        if(pretty){
            objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
        }

        return objectwriter.writeValueAsString(o);

    }
}
