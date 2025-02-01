package Test;

import ExecutionEndpoints.ApiEndpoints;
import Models.DataObject;
import Models.PostObject;
import com.aventstack.chaintest.plugins.ChainTestListener;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.io.IOException;

public class APITest {

    @Test(priority = 1)
    public void postObject() throws IOException {
        ChainTestListener.log("-------------Post simple Object------------");
        PostObject obj = new PostObject();
        obj.setName("Pranav");
        DataObject dataobj = new DataObject();
        dataobj.setYear("1999");
        dataobj.setPrice("2000");
        dataobj.setCPUmodel("Intel core i12");
        dataobj.setHardDiskSize("212");
        obj.setData(dataobj);
        Response response = ApiEndpoints.addObject(obj);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
