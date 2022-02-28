package vkPost.urlShort.JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class Error {

    public int error_code;
    public String error_msg;
    public List<RequestParameter> request_params;
}
