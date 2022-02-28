package vkPost.urlShort.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;

@JsonAutoDetect
public class Link {

    public Response response;
    public Error error;

    @JsonIgnore
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, this);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "toString error in Link";
        }
    }
}
