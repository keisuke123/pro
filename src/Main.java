import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: keisuke
 * Date: 13/06/23
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JFrame{

    Main(String name){

        setTitle(name);                                                               //タイトルの設定
        setSize(300,300);                                                            //サイズの設定
        setVisible(true);                                                              //表示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //×を押した時の動作（プログラム終了）

        /*Panelの設定
        * 背景色の設定→JPanel#setBackgroun
        * サイズの設定→JPanel#setPreferredSize(Dimention)
        * 透明度の設定→JPanel#setOpaque(boolean)不透明でない場合はtrue
        * 枠線の設定　→JPanel#setBorder(Border) interface border
        * */

        JPanel panel1 = new JPanel();                                          //パネル作成
        JTextField text = new JTextField();
        JButton button = new JButton("Tweet");

        //Dimension(縦と横に関する情報)の最大値取得
        Dimension d1 = text.getMaximumSize();
        Dimension d2 = button.getMaximumSize();

        //Short型の最大サイズ
        d1.width = Short.MAX_VALUE;
        //ボタンの高さに揃える
        d1.height = d2.height;
        //値をセット
        text.setMaximumSize(d1);

        panel1.setLayout(new BoxLayout(panel1,BoxLayout.LINE_AXIS));
        panel1.add(text);
        panel1.add(button);

        JScrollPane pane = new JScrollPane();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
        panel2.add(panel1);
        panel2.add(pane);

        add(panel2);
    }

    public static void main(String[] args){
        Properties prop = new Properties();
        InputStream io = null;
        try {
            io = new FileInputStream(new File("./src\\twitter4j.properties"));
            prop.load(io);

            //もしAccessTokenが保存されていなかったら
            if(prop.getProperty("oauth.accessToken")==null){
                new LoginActivity("hoge");
            }
            io.close();
        } catch (FileNotFoundException e){
        }catch(IOException ioe){
        }
        new Main("hoge");

        Twitter twitter = new TwitterFactory().getInstance();


    }
}
