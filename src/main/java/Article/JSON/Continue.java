package Article.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Continue {

    public String rncontinue;
    private String continueProp;

    @JsonProperty("continue")
    public String getContinueProp() {
        return continueProp;
    }

    @JsonProperty("continue")
    public void setContinueProp(String continueProp) {
        this.continueProp = continueProp;
    }
}
