import twitter4j.*;
import twitter4j.auth.AccessToken;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: muttan
 * Date: 13/07/16
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    static Twitter getInst(){
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthAccessToken(new AccessToken(getprop().getProperty("oauth.accessToken"),getprop().getProperty("oauth.accessTokenSecret")));
        return twitter;
    }

    static String getPass(){
        String jarPath = System.getProperty("java.class.path");
        String dirPath = jarPath.substring(0, jarPath.lastIndexOf(File.separator)+1);
        return dirPath;
    }

    static Properties getprop(){
        Properties prop = new Properties();
        try {
            String dirPath = getPass();
            InputStream io = new FileInputStream(new File(dirPath  +"twitter4j.properties"));
            prop.load(io);
            io.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    static JDialog makeDialog (String type,JPanel panel, Status status){
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

    static ArrayList<String> getTweet(){
        ArrayList<String> tweet = new ArrayList<String>();
        Twitter twitter = getInst();
        java.util.List<Status> list = null;
        list = getTimeLine();
        for(Status status : list){
            tweet.add(status.getText());
        }

        return tweet;
    }

    static  ResponseList<Status> getTimeLine(){
        Twitter twitter = getInst();
        ResponseList<Status> timeline = null;
        try {
            timeline = twitter.getHomeTimeline(new Paging(1,200));
        } catch (TwitterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return timeline;
    }

    static  ResponseList<Status> getTimeLine(Paging paging){
        Twitter twitter = getInst();
        ResponseList<Status> timeline = null;
        try {
            timeline = twitter.getHomeTimeline(new Paging(1,200));
        } catch (TwitterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return timeline;
    }
}
