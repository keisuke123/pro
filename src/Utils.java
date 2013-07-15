import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: keisuke
 * Date: 13/07/15
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    static Twitter getInst(){
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthAccessToken(new AccessToken(getprop().getProperty("oauth.accessToken"),getprop().getProperty("oauth.accessTokenSecret")));
        return twitter;
    }

    static Properties getprop(){
        Properties prop = new Properties();
        try {
            InputStream io = new FileInputStream(new File("./src\\twitter4j.properties"));
            prop.load(io);
            io.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
