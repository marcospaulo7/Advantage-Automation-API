package com.br.advantage.utils;


import io.restassured.http.ContentType;

public class Constants {


    public static final String DEFAULT_API_URL = "http://www.advantageonlineshopping.com/";
    public static final ContentType APP_CONTENT_TYPE = ContentType.JSON;
    public static final String TOKEN_DEFAULT_USER="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJpc3MiOiJ3d3cuYWR2YW50YWdlb25saW5lc2hvcHBpbmcuY29tIiwidXNlcklkIjoyNDcwNjkzMzksInN1YiI6Im1hcmNvcyIsInJvbGUiOiJBRE1JTiJ9.Qk_MqposE24grQuYXQlI-w36EZp52biYgRPOy0Uxv0A";
    public static final Long MAX_TIMEOUT = 5000L;

}
