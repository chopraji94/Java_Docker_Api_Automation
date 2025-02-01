package Test;

import ExecutionEndpoints.ApiEndpoints;
import Models.DynamicGetObject;
import Models.DynamicPostObject;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicApiDeleteTest {
    DynamicGetObject data;
    DynamicGetObject newData;
    DynamicGetObject updatedData;

    @Test(priority = 1)
    public void postDynamicObject() throws IOException {
        ChainTestListener.log("-------------Post Object------------");
        DynamicPostObject object = new DynamicPostObject();
        object.setName("Apple MacBook Pro 20");
        Map<String,Object> details = new HashMap<String,Object>();
        details.put("year",2019);
        details.put("price",1849.99);
        details.put("CPU model","Intel Core i9");
        details.put("Hard disk size","1 TB");
        object.setData(details);
        Response response = ApiEndpoints.addDynamicObject(object);
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(response.getBody().asString(), DynamicGetObject.class);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void getAddedObject() throws IOException {
        ChainTestListener.log("-------------Get Object------------");
        Response response = ApiEndpoints.getDynamicObject(data.getId());
        ObjectMapper mapper = new ObjectMapper();
        newData = mapper.readValue(response.getBody().asString(), DynamicGetObject.class);
        Assert.assertEquals(data.getId(),newData.getId());
        Assert.assertEquals(data.getName(),newData.getName());
        Map<String,Object> oldDetails = data.getData();
        Map<String,Object> newDetails = newData.getData();
        Assert.assertEquals(oldDetails.get("year"),newDetails.get("year"));
        Assert.assertEquals(oldDetails.get("price"),newDetails.get("price"));
        Assert.assertEquals(oldDetails.get("CPU model"),newDetails.get("CPU model"));
        Assert.assertEquals(oldDetails.get("Hard disk size"),newDetails.get("Hard disk size"));
    }

    @Test(priority = 3)
    public void deleteAddedObject() throws IOException {
        ChainTestListener.log("-------------Delete Object------------");
        Response response = ApiEndpoints.deleteDynamicObject(newData.getId());
        ObjectMapper mapper = new ObjectMapper();
        DynamicGetObject deletedData = mapper.readValue(response.getBody().asString(), DynamicGetObject.class);
        String actualMessage = String.format("Object with id = %s has been deleted.",newData.getId());
        Assert.assertEquals(deletedData.getMessage(),actualMessage);
    }
}
