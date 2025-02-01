package ExecutionEndpoints;

import Models.DynamicPostObject;
import Models.PostObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ApiEndpoints {

    static Properties properties;

    static String getEndPointsUrl(String urlType) throws IOException {
        FileReader propertyReaderFile = new FileReader(System.getProperty("user.dir")+"//src//test//resources//routes.properties");
        properties = new Properties();
        properties.load(propertyReaderFile);
        return properties.getProperty(urlType);
    }


    public static Response addObject(PostObject object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        baseURI  = getEndPointsUrl("base_url");
        String postUrl = getEndPointsUrl("post_url");
        String jsonPayload = mapper.writeValueAsString(object);
        Response response = given()
                .body(jsonPayload)
                .contentType(ContentType.JSON)
                .when()
                .post(postUrl);

        return response;
    }

    public static Response addDynamicObject(DynamicPostObject object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        baseURI  = getEndPointsUrl("base_url");
        String postUrl = getEndPointsUrl("post_url");
        String jsonPayload = mapper.writeValueAsString(object);
        Response response = given()
                .body(jsonPayload)
                .contentType(ContentType.JSON)
                .when()
                .post(postUrl);

        return response;
    }

    public static Response getDynamicAllTypeOfObject() throws IOException {
        baseURI  = getEndPointsUrl("base_url");
        String getMultipleUrl = getEndPointsUrl("get_multiple_url");
        Response response = given().when().get(getMultipleUrl);
        return  response;
    }

    public static Response getDynamicObject(String id) throws IOException {
        baseURI  = getEndPointsUrl("base_url");
        String getUrl = getEndPointsUrl("get_url");
        Response response = given().pathParams("id",id).when().get(getUrl);
        return  response;
    }

    public static Response updateDynamicObject(String id,DynamicPostObject object) throws IOException {
        baseURI  = getEndPointsUrl("base_url");
        String putUrl = getEndPointsUrl("put_url");
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(object);
        Response response = given()
                .body(jsonPayload)
                .contentType(ContentType.JSON)
                .pathParams("id",id)
                .when()
                .put(putUrl);
        return  response;
    }

    public static Response patchDynamicObject(String id, Map<String,String> object) throws IOException {
        baseURI  = getEndPointsUrl("base_url");
        String patchUrl = getEndPointsUrl("patch_url");
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(object);
        Response response = given()
                .body(jsonPayload)
                .contentType(ContentType.JSON)
                .pathParams("id",id)
                .when()
                .patch(patchUrl);
        return response;
    }

    public static Response deleteDynamicObject(String id) throws IOException {
        baseURI  = getEndPointsUrl("base_url");
        String deleteUrl = getEndPointsUrl("delete_url");
        Response response = given()
                .pathParams("id",id)
                .when()
                .delete(deleteUrl);
        return response;
    }
}
