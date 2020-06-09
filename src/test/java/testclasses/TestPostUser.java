package testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import requests.PostUser;

import java.io.IOException;

public class TestPostUser {

    @Test
     public void testPostUser() throws IOException {
     PostUser postUser = new PostUser("Ivan","Vanko");
     int actualResponse  = postUser.sendPOST();
     Assert.assertEquals(actualResponse, 201);
    }

}
