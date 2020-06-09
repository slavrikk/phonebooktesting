package requests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class PostUser {

  private JSONObject jsonObject;
  private JSONObject externalJsonObject;
  private int responseNumber;
  private int id;
  private String firstName="";
  private String lastName="";
  private String result;

    public PostUser(JSONObject externalJsonObject) {
        this.externalJsonObject = externalJsonObject;
    }

    public PostUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getResult() {
        return result;
    }

    public int sendPOST() throws IOException {

        HttpPost post = new HttpPost("http://localhost:8080/users");
        post.addHeader("content-type", "application/json");

        if(!firstName.equals("")) {
            jsonObject = new JSONObject();
            jsonObject.put("firstName", firstName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("id", id);
        }
        else {
            jsonObject=externalJsonObject;
        }
        post.setEntity(new StringEntity(jsonObject.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
           responseNumber  =  response.getStatusLine().getStatusCode();
           result = EntityUtils.toString(response.getEntity());
        }

        return responseNumber;
    }
}
