package vkPost.urlShort.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

@JsonAutoDetect
public class RequestParameter {

    public String key;
    public String value;

    @JsonIgnore
    public Map<String, String> getRequestParameterMap() {
        Map<String, String> map = new HashMap<>();

        map.put(key, value);

        return map;
    }
}
