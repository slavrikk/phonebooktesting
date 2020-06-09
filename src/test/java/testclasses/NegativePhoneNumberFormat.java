package testclasses;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.PostContact;
import java.io.IOException;

public class NegativePhoneNumberFormat {

    @Test
    public void wrongNumberFormatRequest() throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Natalie ");
        jsonObject.put("lastName", "Rushman");
        jsonObject.put("phone", "123456789f");
        PostContact postContact = new PostContact("1",jsonObject);
        int actualResponse = postContact.postContact();
        Assert.assertEquals(actualResponse, 400);
    }
}
