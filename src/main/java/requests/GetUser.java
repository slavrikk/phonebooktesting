package requests;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

public class GetUser {

    private String name;
    private int id;
    private HttpGet request;
    private String result;

    public GetUser(int id) {
        this.id = id;
    }

    public GetUser(String name) {
        this.name = name;
    }

    public String getResponse() throws IOException {

        if(id!=0) {
            request = new HttpGet("http://localhost:8080/users/" + id);
        }
        else {
            request = new HttpGet("http://localhost:8080/users/search?name=" + name);
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        }
        return result;
    }

}

