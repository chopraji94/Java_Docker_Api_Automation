package Test;

import ExecutionEndpoints.AuthApiEndpoints;
import Models.UserObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UsefBearerAuthAPITest {
    Faker fake = new Faker();
    UserObject createdData;
    Map<String,Object> object;

    @Test(priority = 1)
    public void postuserUsingAuth() throws IOException {
        object = new HashMap<String,Object>();
        String name = fake.name().firstName();
        object.put("name",name);
        object.put("email",name+"@gmail.com");
        object.put("gender","male");
        object.put("status","active");
        Response response = AuthApiEndpoints.postUser(object);
        System.out.println(response.getBody().asString());
        ObjectMapper mapper = new ObjectMapper();
        createdData = mapper.readValue(response.getBody().asString(),UserObject.class);
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test(priority = 2)
    public void checkDataValid() throws IOException {
        Assert.assertEquals(createdData.getName(),object.get("name"));
        Assert.assertEquals(createdData.getEmail(),object.get("email"));
        Assert.assertEquals(createdData.getGender(),object.get("gender"));
        Assert.assertEquals(createdData.getStatus(),object.get("status"));
    }
}
