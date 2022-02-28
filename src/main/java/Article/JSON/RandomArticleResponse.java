package Article.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class RandomArticleResponse {

    public String batchcomplete;
    public Continue aContinue;
    public Query query;

    @JsonProperty("continue")
    public Continue getaContinue() {
        return aContinue;
    }
    @JsonProperty("continue")
    public void setaContinue(Continue aContinue) {
        this.aContinue = aContinue;
    }
}
