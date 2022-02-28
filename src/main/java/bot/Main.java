package bot;

import Article.RandomArticle;
import vkPost.Post;
import vkPost.urlShort.Shorter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            String articleUrl = new RandomArticle().getURL();
            Post.post(new Shorter(articleUrl));
            Thread.sleep(21_600_000);
        }
    }
}
