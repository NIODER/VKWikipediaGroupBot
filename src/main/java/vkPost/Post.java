package vkPost;

import org.jetbrains.annotations.NotNull;
import vkPost.urlShort.IShortLink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class Post {

    private static final String user_id = "188873995";

    private static String ACCESS_TOKEN;
    private static final String API_URL = "https://api.vk.com/method/";
    private static final String METHOD = "wall.post";
    private static final String GROUP_ID = "-210277057";
    private static final String VERSION = "5.131";

    public static @NotNull String post(IShortLink shortLink) throws IOException {
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("bot.properties"));
            ACCESS_TOKEN = prop.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String article = shortLink.getLink();
        if (article == null) {
            throw new Error("API doesn't work");
        }
        String stringBuilder = API_URL +
                METHOD +
                "?owner_id=" + GROUP_ID +
                "&from_group=1" +
                "&attachments=" + shortLink.getLink() +
                "&v=" + VERSION +
                "&access_token=" + ACCESS_TOKEN;
        final URL url = new URL(stringBuilder);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}
