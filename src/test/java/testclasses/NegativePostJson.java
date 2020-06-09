package testclasses;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import requests.PostUser;
import java.io.IOException;

public class NegativePostJson {

    @Test
    public void testNegativePostJson() throws IOException {

        TestNG testNG = new TestNG();
        testNG.setUseDefaultListeners(false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ddddddd", 123);
        jsonObject.put("fffffff","ffsfsfsf");
        jsonObject.put("ttttttttt","ffsfsfsf");
        PostUser postUser = new PostUser(jsonObject);
        int actualResponse = postUser.sendPOST();
        int expectedResponse = 400;
        Assert.assertEquals(actualResponse,expectedResponse);
    }
}
