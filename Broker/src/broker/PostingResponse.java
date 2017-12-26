/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import java.io.File;
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

    public static void postingResponseOk(Long replyID, String userName, File file) throws TwitterException {
        String message = "..." + mensajeInicial() + " @" + userName;
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
    
    

}
