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
import java.util.Arrays;
import java.util.Map;

public class DynamicApiGetAllTypeOfData {


    @Test(priority = 2)
    public void getAddedObject() throws IOException {
        ChainTestListener.log("-------------Get all types of Object------------");
        Response response = ApiEndpoints.getDynamicAllTypeOfObject();
        ObjectMapper mapper = new ObjectMapper();
        DynamicGetObject[] newData = mapper.readValue(response.getBody().asString(), DynamicGetObject[].class);
        DynamicGetObject object1 = Arrays.stream(newData).filter(e -> e.getId().equals("1")).toList().getFirst();
        DynamicGetObject object2 = Arrays.stream(newData).filter(e -> e.getId().equals("2")).toList().getFirst();
        DynamicGetObject object3 = Arrays.stream(newData).filter(e -> e.getId().equals("3")).toList().getFirst();
        DynamicGetObject object4 = Arrays.stream(newData).filter(e -> e.getId().equals("4")).toList().getFirst();
        DynamicGetObject object5 = Arrays.stream(newData).filter(e -> e.getId().equals("5")).toList().getFirst();
        DynamicGetObject object6 = Arrays.stream(newData).filter(e -> e.getId().equals("6")).toList().getFirst();
        DynamicGetObject object7 = Arrays.stream(newData).filter(e -> e.getId().equals("7")).toList().getFirst();
        DynamicGetObject object8 = Arrays.stream(newData).filter(e -> e.getId().equals("8")).toList().getFirst();
        DynamicGetObject object9 = Arrays.stream(newData).filter(e -> e.getId().equals("9")).toList().getFirst();
        DynamicGetObject object10 = Arrays.stream(newData).filter(e -> e.getId().equals("10")).toList().getFirst();
        DynamicGetObject object11 = Arrays.stream(newData).filter(e -> e.getId().equals("11")).toList().getFirst();
        DynamicGetObject object12 = Arrays.stream(newData).filter(e -> e.getId().equals("12")).toList().getFirst();
        DynamicGetObject object13 = Arrays.stream(newData).filter(e -> e.getId().equals("13")).toList().getFirst();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
