import javax.swing.*;
import java.awt.*;

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

        JPanel panel1 = new JPanel();                                          //パネル作成
        panel1.setBackground(Color.blue);                                   //背景色の設定
        panel1.setPreferredSize(new Dimension(100,200));           //パネルのサイズを設定 Dimension(int width,int height)

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);
        panel2.setPreferredSize(new Dimension(100,200));

        setLayout(new FlowLayout());
        add(panel1);
        add(panel2);

    }

    public static void main(String[] args){
        new Main("hoge");
    }
}
