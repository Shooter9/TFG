/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mongodb.BasicDBObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author mario.arias
 */
public class PostingResponse {

    public static final String CONSUMER_KEY = "KLhJyVaqqmFJ08frIzl07fXC2";
    public static final String CONSUMER_SECRET = "SoQ3wtVExoYbU4oVuJvJwJjlRPmMMOhVGn0nfYxaCflVEhVfMM";
    public static final String ACCES_TOKEN = "928186006049886208-hDjjSC3Th0N6PVgNoZbtKWNTVMprtaX";
    public static final String ACCES_TOKEN_SECRET = "3K30FA9gAgmL0dxpxGnWPu0Jd5STuvfuSsVFZbxIZqx8Y";
    public static Twitter twitter;

    public PostingResponse() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCES_TOKEN)
                .setOAuthAccessTokenSecret(ACCES_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        
    }

    public static void postingResponseOk(Long replyID, String userName, File file, BasicDBObject serviceInformation) throws TwitterException, UnirestException, JSONException {
        
        String message;
        
        GetRequest request = Unirest.get((String)serviceInformation.get("URI"));
        
        String response = HttpRequest.get((String)serviceInformation.get("URI")).body();
        JSONObject jsonObj = new JSONObject((String) response);
        System.out.println("Response was: " + jsonObj.get("result"));       
        
        message = jsonObj.get("result")+"..." + "" + " @" + userName;
        StatusUpdate status = new StatusUpdate(message);
        status.setMedia(file); // set the image to be uploaded here.
        twitter.updateStatus(status);

    }

    public static void postingResponseKo(String userName) throws TwitterException {
        {
            String message = "No se ha podido ofrecer el servicio solicitado @" + userName + " ";
            StatusUpdate status = new StatusUpdate(message);
            twitter.updateStatus(status);

        }

    }

    private static String mensajeInicial() {
        broker.Respuesta_Service service = new broker.Respuesta_Service();
        broker.Respuesta port = service.getRespuestaPort();
        return port.mensajeInicial();
    }
    
    private static String executePost(String targetURL, String urlParameters) {
  HttpURLConnection connection = null;

  try {
    //Create connection
    URL url = new URL(targetURL);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", 
        "application/x-www-form-urlencoded");

    connection.setRequestProperty("Content-Length", 
        Integer.toString(urlParameters.getBytes().length));
    connection.setRequestProperty("Content-Language", "en-US");  

    connection.setUseCaches(false);
    connection.setDoOutput(true);

    //Send request
    DataOutputStream wr = new DataOutputStream (
        connection.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.close();

    //Get Response  
    InputStream is = connection.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
    String line;
    while ((line = rd.readLine()) != null) {
      response.append(line);
      response.append('\r');
    }
    rd.close();
    return response.toString();
  } catch (Exception e) {
    e.printStackTrace();
    return null;
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}

    
    
}

    
