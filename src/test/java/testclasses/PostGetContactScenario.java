package testclasses;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requests.GetContact;
import requests.GetUser;
import requests.PostContact;
import requests.PostUser;

import java.io.IOException;

public class PostGetContactScenario {

    private PostUser postUser;
    private int idUser;
    private int idContact;
    private String firstNameContact;
    private String lastNameContact;
    private String phone;
    private String email;
    private PostContact postContact;
    private int expectedResponse = 201;


    @BeforeClass
    public void createUserAndContactFields() throws IOException {
        postUser = new PostUser("Tony", "Stark");
        postUser.sendPOST();
        firstNameContact = "Pepper";
        lastNameContact = "Potts";
        phone = "9993332200";
        email = "qwerty@gmail.com";
    }

    @Test(priority = 1)
    public void createContact() throws IOException {
        GetUser getUser = new GetUser("Tony");
        String response = getUser.getResponse();

        JSONArray jsonArray = new JSONArray(response);
        String firstElement = jsonArray.get(0).toString();
        JSONObject jsonResponse = new JSONObject(firstElement);
        idUser = (int) jsonResponse.get("id");

        postContact = new PostContact(String.valueOf(idUser));
        postContact.setFirstName(firstNameContact);
        postContact.setLastName(lastNameContact);
        postContact.setPhone(phone);
        postContact.setEmail(email);
        int actualResponse = postContact.postContact();

        Assert.assertEquals(actualResponse,expectedResponse);
    }

    @Test(priority = 2)
    public void getContactByNumber() throws IOException {
        String responseContact = postContact.getResult();
        JSONObject jsonResponse = new JSONObject(responseContact);
        String phoneNumberResponse = (String) jsonResponse.get("phone");
        GetContact getContact = new GetContact(String.valueOf(idUser), phoneNumberResponse);
        int actualResponse = getContact.getResponse();

        Assert.assertEquals(actualResponse,200);
    }


}
