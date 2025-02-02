package ExecutionEndpoints;

import Models.UserObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.protocol.ResponseServer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class AuthApiEndpoints {

    static Properties properties;

    public static String getEndpoint(String urlType) throws IOException {
        FileReader readerFile = new FileReader(System.getProperty("user.dir")+"//src//test//resources//routes.properties");
        properties = new Properties();
        properties.load(readerFile);
        return properties.getProperty(urlType);
    }

    public static Response postUser(Map<String,Object> object) throws IOException {
        baseURI = getEndpoint("user_base_url");
        String post_Url = getEndpoint("user_post_url");
        String token = getEndpoint("bearer_token");

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(object);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .header("Authorization","Bearer "+token)
                .when()
                .post(post_Url);

        return  response;
    }

    public static Response getUser(int id) throws IOException {
        baseURI = getEndpoint("user_base_url");
        String get_Url = getEndpoint("user_get_url");

        Response response = given()
                .pathParams("id",id)
                .when()
                .get(get_Url);

        return response;
    }
}
