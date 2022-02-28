package Article;

import Article.JSON.RandomArticleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

public class RandomArticle {

    private final String randomArticleURL;
    private static final String baseURL = "https://ru.wikipedia.org/wiki/";

    public RandomArticle() {
        this.randomArticleURL = baseURL + titleToURL();
    }

    public String getURL() {
        return randomArticleURL;
    }

    private static String getJSONArticle() throws IOException {
        URL url = new URL("https://ru.wikipedia.org/w/api.php?action=query&format=json&list=random&ascii=1&rnnamespace=0&rnlimit=1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            System.out.println(line);
        }
        return stringBuilder.toString();
    }

    private String getTitle() {
        try {
            String json;
            try {
                json = getJSONArticle();
            } catch (IOException e) {
                return "";
            }
            StringReader reader = new StringReader(json);
            ObjectMapper mapper = new ObjectMapper();
            RandomArticleResponse response = mapper.readValue(reader, RandomArticleResponse.class);
            return response.query.random.get(0).title;
        } catch (NoClassDefFoundError | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String titleToURL() {
        String urlTitle;
        try {
            urlTitle = URLEncoder.encode(Objects.requireNonNull(getTitle()), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        urlTitle = urlTitle.replace('+', '_');
        return urlTitle;

    }
}
