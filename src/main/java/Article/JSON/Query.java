package Article.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class Query {

    public List<Random> random;
}
