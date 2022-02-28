package vkPost.urlShort.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Response {

    public String short_url;
    public String access_key;
    public String key;
    public String url;
}
