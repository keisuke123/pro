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
        JLabel label = new JLabel("POTATO");

        JPanel panel1 = new JPanel();                                          //パネル作成
        panel1.setBackground(Color.blue);                                   //背景色の設定
        panel1.setPreferredSize(new Dimension(100,200));           //パネルのサイズを設定 Dimension(int width,int height)
        panel1.add(label);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);
        panel2.setPreferredSize(new Dimension(100,200));

        setLayout(new FlowLayout());                                         //FlowLayoutにて設定

        add(panel1);                                                                   //パネルの追加
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


    }
}
