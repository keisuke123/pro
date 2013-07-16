import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.awt.Dimension;
import java.io.*;
import java.util.List;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JPanel;

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

    static JDialog makeDialog (String type,JPanel panel,Status status){
        JDialog dialog = new JDialog();
    	dialog.setResizable(true); //フレームのサイズを変更できるかどうか
        dialog.add(panel);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(new Dimension(400, 200));
        dialog.setTitle("send "+type +" To @"+status.getUser().getScreenName());
    	return dialog;
    }

    static JDialog makeDialog (String type,JPanel panel){
    	JDialog dialog = new JDialog();
    	dialog.setResizable(true); //フレームのサイズを変更できるかどうか
        dialog.add(panel);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(new Dimension(400, 200));
        dialog.setTitle("send "+type);
    	return dialog;
    }
/*
    static List<Status> getTimeline(){
    	Twitter twitter = getInst();
    	List<Status> list;
		try {
			list = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


    	return list;
    }
    */
}
