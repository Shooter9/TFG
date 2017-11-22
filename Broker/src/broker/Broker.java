package broker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public final class Broker {
public static long  botId;
    
    public static void main(String[] args) throws TwitterException {
        
        //Configuramos las claves y tokens de nuestra aplicacion de twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("aP0JA7NbPuyix3dNCTRXpghWI")
            .setOAuthConsumerSecret("tl2PJHzTPEXYFaWiqpwmlz8ermftiKeQZ7kmv9KLCmS648OHPf")
            .setOAuthAccessToken("928186006049886208-MWbI2nSWu7TlhHkciSSL87NOIAu1lZr")
            .setOAuthAccessTokenSecret("3ftbEBbDdgGnI9XM4mqJ2iynxOiIhdypq8EgR7CyyCsoT");
        
        //Creamos una instancia para ver nuestra cuenta en streaming (tiempo real)
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
            .getInstance();
        
        //Creamos el listener 
        StatusListener listener = new StatusListener() {
            @Overridez
            public void onStatus(Status status) {
                
                //Solo miraremos los tweets procedentes de otras cuentas hacia nosotros
                if(botId!=status.getUser().getId()){
                    try{
                        if(status.getHashtagEntities()[0].getText().equals("readThis")){
                        //Guardamos la imagen enviada a traves de twitter
                        URL url = new URL((status.getMediaEntities())[0].getMediaURL());
                        BufferedImage img = ImageIO.read(url);
                        File file = new File("C:\\Users\\User\\Desktop\\TFG\\Imagenes\\"+((status.getMediaEntities())[0].getExpandedURL()).split("/")+".jpg");
                        ImageIO.write(img, "jpg", file);
                        //Si todo ha salido correctamente mostramos el tweet
                        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                        }
                        else System.out.println("El hastag no se corresponde con ningun servicio");

                    }catch(Exception e){
                        //En caso de que el tweet venga vacio, mostraremos este mensaje
                        System.out.println("El tweet no contiene imagenes o viene sin hastag");

                    }
                } 
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
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
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        System.out.println(calc(5,6));
        //Filttramos los tweets por usuario y por palabras clave
        long id=twitterStream.getId();
        botId=id;
        twitterStream.addListener(listener);
        FilterQuery filtre = new FilterQuery();        
        filtre.follow();               
        twitterStream.filter();
    }

    private static int calc(int num1, int num2) {
        broker.Respuesta_Service service = new broker.Respuesta_Service();
        broker.Respuesta port = service.getRespuestaPort();
        return port.calc(num1, num2);
    }
    
    

}