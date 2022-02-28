package vkPost.urlShort;

import com.fasterxml.jackson.databind.ObjectMapper;
import vkPost.urlShort.JSON.Link;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Shorter implements IShortLink {

    private final String API_URL = "https://api.vk.com/method/";
    private final String METHOD = "utils.getShortLink";
    private String ACCESS_TOKEN;
    private String VERSION;
    private final String longUrl;

    public Shorter(String url) {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getClassLoader().getResourceAsStream("bot.properties"));
            ACCESS_TOKEN = prop.getProperty("token");
            VERSION = prop.getProperty("version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.longUrl = url;
    }

    private String getShortLink() throws MalformedURLException {
        URL url = new URL(API_URL + METHOD + "?url=" + longUrl + "&private=1&access_token=" +
                ACCESS_TOKEN + "&v=" + VERSION);
        String result = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                StringReader stringReader = new StringReader(json.toString());
                ObjectMapper mapper = new ObjectMapper();
                Link link = mapper.readValue(stringReader, Link.class);
                if (link.error != null) {
                    System.out.println(link);
                    throw  new Error("VK sent error line 42 Shorter");
                }
                return link.response.short_url;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLink() {
        try {
            return getShortLink();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
