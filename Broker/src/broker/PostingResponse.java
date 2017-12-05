/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

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

    public static void postingResponse(Long replyID, String userName) throws TwitterException {
      
    ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCES_TOKEN)
                .setOAuthAccessTokenSecret(ACCES_TOKEN_SECRET);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
                    twitter.updateStatus("..."+mensajeInicial(1)+" @"+userName); //ThrowsTwitterException

        /*String consumerKey = CONSUMER_KEY; 
        String consumerSecret = CONSUMER_SECRET;
        String twitterToken = ACCES_TOKEN;
       
        String twitterSecret = ACCES_TOKEN_SECRET;
       
        String replyMessage = mensajeInicial();                
        Long inReplyToStatusId = replyID; 
        
            TwitterFactory factory = new TwitterFactory();
    Twitter twitter = factory.getInstance();
    twitter.setOAuthConsumer(consumerKey, consumerSecret);
    AccessToken accessToken = new AccessToken(twitterToken, twitterSecret);
    twitter.setOAuthAccessToken(accessToken);
    StatusUpdate statusUpdate = new StatusUpdate(replyMessage);
    statusUpdate.setInReplyToStatusId(inReplyToStatusId);
    Status status = twitter.updateStatus(statusUpdate);
}*/



    
    }

    private static String mensajeInicial(int arg0) {
        initialservice.Respuesta_Service service = new initialservice.Respuesta_Service();
        initialservice.Respuesta port = service.getRespuestaPort();
        return port.mensajeInicial(arg0);
    }


}
