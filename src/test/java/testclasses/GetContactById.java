package testclasses;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.GetContact;
import java.io.IOException;


public class GetContactById {


    /*
    This test is going to be failed due to the bug. When getting the id 1 user`s contacts,
    the system shows that this user possess two contacts (id 1, id 2). Requesting the second contact by id
    the system response is 404 but this contact exists.
     */
    @Test
    public void testGetContactById() throws IOException {
    GetContact getContactById = new GetContact("1",2);
    int actualResponse = getContactById.getResponse();
        Assert.assertEquals(actualResponse, 200);
}
}
