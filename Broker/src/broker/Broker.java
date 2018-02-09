package broker;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Mario Arias Escalona
 */
public final class Broker {

    public static final String CONSUMER_KEY = "aP0JA7NbPuyix3dNCTRXpghWI";
    public static final String CONSUMER_SECRET = "tl2PJHzTPEXYFaWiqpwmlz8ermftiKeQZ7kmv9KLCmS648OHPf";
    public static final String ACCES_TOKEN = "928186006049886208-MWbI2nSWu7TlhHkciSSL87NOIAu1lZr";
    public static final String ACCES_TOKEN_SECRET = "3ftbEBbDdgGnI9XM4mqJ2iynxOiIhdypq8EgR7CyyCsoT";
    private static DBCollection tablaServicios;
    private static DB db;

    public static void main(String[] args) throws TwitterException, UnknownHostException {

        //Conectamos el broker a la base de datos
        conectToMongoDB();

        //Configuramos las claves y tokens de nuestra aplicacion de twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCES_TOKEN)
                .setOAuthAccessTokenSecret(ACCES_TOKEN_SECRET);

        //Creamos una instancia para ver nuestra cuenta en streaming (tiempo real)
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
                .getInstance();

        twitterStream.addListener(listener);
        // El metodo user() internamente crea threads que manipulan las llamadas a la API de twitter y hace continuamete llamadas, dependiendo de la actividad de la cuenta.
        twitterStream.user();

    }

    private static final UserStreamListener listener = new UserStreamListener() {
        @Override
        public void onStatus(Status status) {
            if (("cvcBoT17").equals(status.getInReplyToScreenName())) {
                PostingResponse response = new PostingResponse();
                System.out.println("onStatus @" + status.getUser().getScreenName() + " - " + status.getText());
                try {
                    //DBObject query = new BasicDBObject("serviceName", status.getHashtagEntities()[0].getText());
                    //BasicDBObject serviceInformation = (BasicDBObject) tablaServicios.findOne(query);                    
                    
                    
                    if (status.getHashtagEntities()[0].getText().equals("readthis")) {
                       
                        //Guardamos la imagen enviada a traves de twitter
                        URL url = new URL((status.getMediaEntities())[0].getMediaURL());
                        BufferedImage img = ImageIO.read(url);          
                        File file = new File(System.getProperty("user.home")+"/TFG/textRecognition/imagenAProcesar.jpg");
                        ImageIO.write(img, "jpg", file);
                        ReadThis_Application.readThisExec();
                        //response.postingResponseOk(status.getInReplyToUserId(), status.getUser().getScreenName(), file);
                        //Si todo ha salido correctamente mostramos el tweet
                        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

                    } else {
                        

                    }
            
                    


                } catch (Exception e) {
                       System.out.println("El tweet viene sin hastag");
                }
            }

        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        @Override
        public void onDeletionNotice(long directMessageId, long userId) {
            System.out.println("Got a direct message deletion notice id:" + directMessageId);
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got a track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning:" + warning);
        }

        @Override
        public void onFriendList(long[] friendIds) {

        }

        @Override
        public void onFavorite(User source, User target, Status favoritedStatus) {
            System.out.println("onFavorite source:@"
                    + source.getScreenName() + " target:@"
                    + target.getScreenName() + " @"
                    + favoritedStatus.getUser().getScreenName() + " - "
                    + favoritedStatus.getText());
        }

        @Override
        public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
            System.out.println("onUnFavorite source:@"
                    + source.getScreenName() + " target:@"
                    + target.getScreenName() + " @"
                    + unfavoritedStatus.getUser().getScreenName()
                    + " - " + unfavoritedStatus.getText());
        }

        @Override
        public void onFollow(User source, User followedUser) {
            System.out.println("onFollow source:@"
                    + source.getScreenName() + " target:@"
                    + followedUser.getScreenName());
        }

        @Override
        public void onUnfollow(User source, User followedUser) {
            System.out.println("onFollow source:@"
                    + source.getScreenName() + " target:@"
                    + followedUser.getScreenName());
        }

        @Override
        public void onDirectMessage(DirectMessage directMessage) {
            System.out.println("onDirectMessage text:"
                    + directMessage.getText());
        }

        @Override
        public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
            System.out.println("onUserListMemberAddition added member:@"
                    + addedMember.getScreenName()
                    + " listOwner:@" + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
            System.out.println("onUserListMemberDeleted deleted member:@"
                    + deletedMember.getScreenName()
                    + " listOwner:@" + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
            System.out.println("onUserListSubscribed subscriber:@"
                    + subscriber.getScreenName()
                    + " listOwner:@" + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
            System.out.println("onUserListUnsubscribed subscriber:@"
                    + subscriber.getScreenName()
                    + " listOwner:@" + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListCreation(User listOwner, UserList list) {
            System.out.println("onUserListCreated  listOwner:@"
                    + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListUpdate(User listOwner, UserList list) {
            System.out.println("onUserListUpdated  listOwner:@"
                    + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserListDeletion(User listOwner, UserList list) {
            System.out.println("onUserListDestroyed  listOwner:@"
                    + listOwner.getScreenName()
                    + " list:" + list.getName());
        }

        @Override
        public void onUserProfileUpdate(User updatedUser) {
            System.out.println("onUserProfileUpdated user:@" + updatedUser.getScreenName());
        }

        @Override
        public void onUserDeletion(long deletedUser) {
            System.out.println("onUserDeletion user:@" + deletedUser);
        }

        @Override
        public void onUserSuspension(long suspendedUser) {
            System.out.println("onUserSuspension user:@" + suspendedUser);
        }

        @Override
        public void onBlock(User source, User blockedUser) {
            System.out.println("onBlock source:@" + source.getScreenName()
                    + " target:@" + blockedUser.getScreenName());
        }

        @Override
        public void onUnblock(User source, User unblockedUser) {
            System.out.println("onUnblock source:@" + source.getScreenName()
                    + " target:@" + unblockedUser.getScreenName());
        }

        @Override
        public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
            System.out.println("onRetweetedRetweet source:@" + source.getScreenName()
                    + " target:@" + target.getScreenName()
                    + retweetedStatus.getUser().getScreenName()
                    + " - " + retweetedStatus.getText());
        }

        @Override
        public void onFavoritedRetweet(User source, User target, Status favoritedRetweet) {
            System.out.println("onFavroitedRetweet source:@" + source.getScreenName()
                    + " target:@" + target.getScreenName()
                    + favoritedRetweet.getUser().getScreenName()
                    + " - " + favoritedRetweet.getText());
        }

        @Override
        public void onQuotedTweet(User source, User target, Status quotingTweet) {
            System.out.println("onQuotedTweet" + source.getScreenName()
                    + " target:@" + target.getScreenName()
                    + quotingTweet.getUser().getScreenName()
                    + " - " + quotingTweet.getText());
        }

        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
            System.out.println("onException:" + ex.getMessage());
        }
    };

    private static void conectToMongoDB() throws UnknownHostException {

        Mongo mongo = new Mongo("localhost", 27017);
        db = mongo.getDB("webServiceInfo");
        tablaServicios = db.getCollection("Services");

    }

    public static String findDocumentByServiceName(String serviceName) {

        BasicDBObject query = new BasicDBObject();
        query.put("serviceName", serviceName);
        DBObject dbObj = tablaServicios.findOne(query);
        return dbObj.get("serviceName").toString();
    }

}
