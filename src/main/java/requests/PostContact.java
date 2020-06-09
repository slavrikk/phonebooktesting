package requests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;


public class PostContact {

    private String idUser;
    private String firstName ="";
    private String lastName="";
    private String phone="";
    private String email="";
    private JSONObject jsonObject;
    private JSONObject externalJsonObject;
    private int responseNumber;
    String result;



    public PostContact(String idUser) {
        this.idUser = idUser;
    }

    public PostContact(String idUser, JSONObject externalJsonObject) {
        this.idUser = idUser;
        this.externalJsonObject = externalJsonObject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public int postContact() throws IOException {
        HttpPost post = new HttpPost("http://localhost:8080/users/"+idUser+"/contacts");

        post.addHeader("content-type", "application/json");

        if(!firstName.equals("")) {
            jsonObject = new JSONObject();
            jsonObject.put("firstName", firstName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("phone", phone);
            jsonObject.put("email",email);

        }
        else {
            jsonObject=externalJsonObject;
        }
        post.setEntity(new StringEntity(jsonObject.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            result = EntityUtils.toString(response.getEntity());
            responseNumber  =  response.getStatusLine().getStatusCode();
        }

        return responseNumber;
    }

}
