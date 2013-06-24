import twitter4j.Twitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: keisuke
 * Date: 13/06/23
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity  extends JFrame{
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JLabel l1 = new JLabel("認証を行います。");
    JLabel l2 = new JLabel("PIN");
    JButton b1 = new JButton("Open Browser");
    JTextField tf = new JTextField(15);

    Twitter twitter;

    LoginActivity(String name){
        setLayout(name);
    }

    //Layoutについてはこっちで行う。
    private void setLayout(String name){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "http://www.google.com.";
                try {
                    URI uri = new URI(str);
                    Desktop dt = Desktop.getDesktop();
                    dt.browse(uri);
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }

            }
        });

        //メッセージとボタン
        p1.add(l1);
        p1.add(b1);

        //PIN入力
        p2.add(l2);
        p2.add(tf);

        JDialog dialog = new JDialog();
        dialog.setResizable(false); //フレームのサイズを変更できるかどうか
        dialog.add(p1);
        dialog.add(p2);
        dialog.setTitle(name);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(new Dimension(400,200));
        dialog.setLocationRelativeTo(null);
        dialog.setModal(false);
        dialog.setVisible(true);

        return;
    }

    //main文
    public static void main(String[] args){
        new LoginActivity("Login");
    }

}
