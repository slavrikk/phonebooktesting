package requests;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetContact {
 private String phoneNumber;
 private String idUser;
 private HttpGet request;
 private String result;
 private int responseCode;
 private int idContact;

    public GetContact(String idUser, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.idUser = idUser;
    }

    public GetContact(String idUser, int idContact) {
        this.idUser = idUser;
        this.idContact = idContact;
    }

    public String getResult() {
        return result;
    }

    public int getResponse() throws IOException {

        if(idContact!=0){
            request = new HttpGet("http://localhost:8080/users/" + idUser + "/contacts/" + idContact);
        }
        else {
            request = new HttpGet("http://localhost:8080/users/" + idUser + "/contacts/search?number=" + phoneNumber);
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                responseCode =  response.getStatusLine().getStatusCode();
            }

        }
        return responseCode;
    }
}
