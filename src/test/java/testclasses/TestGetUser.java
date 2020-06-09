package testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import requests.GetUser;

import java.io.IOException;

public class TestGetUser {


@Test
    public void responseUser() throws IOException {
    int userId = 1;
    String expectedResponse = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Snow\"}";
    GetUser getUserById = new GetUser(userId);
    String actualResponse = getUserById.getResponse();
    Assert.assertEquals(actualResponse,expectedResponse);
}


}
