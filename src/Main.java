import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
* Created with IntelliJ IDEA.
* User: keisuke
* Date: 13/06/23
* Time: 9:45
* To change this template use File | Settings | File Templates.
*/
public class Main extends JFrame implements ListSelectionListener{

    private JPanel panel1;
    private final JTextField text = null;
    private JButton button;

    private static InputStream io = null;
    private static ArrayList<String> tweet = new ArrayList<String>();
    private static long id;
    private static List<Status>timeline = null;

    private int height = 0;
    private int width = 450;
    int old = 0;
    int count=0;

    JList list;
    private Dialog dialog;
    Main(String name){

        setTitle(name); //タイトルの設定
        setSize(900,500); //サイズの設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×を押した時の動作（プログラム終了）
        /*Panelの設定
         * 背景色の設定→JPanel#setBackgroun
         * サイズの設定→JPanel#setPreferredSize(Dimention)
         * 透明度の設定→JPanel#setOpaque(boolean)不透明でない場合はtrue
         * 枠線の設定　→JPanel#setBorder(Border) interface border
        * */

        JPanel panel1 = new JPanel(); //パネル作成
        final JTextField text = new JTextField();
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

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("DM");
        menuBar.add(menu);
        JMenuItem item = new JMenuItem("Send DM");
        final JTextField field = new JTextField();
        item.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
	            JPanel panel = new JPanel();
	            final JTextArea text = new JTextArea(3,1);
	            JButton button = new JButton("送信");
	            button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String str = text.getText();
						String name = field.getText();
						Twitter twitter = Utils.getInst();
						try {
							twitter.sendDirectMessage(name,str);
						} catch (TwitterException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						}
						dialog.dispose();
					}
				});
	            field.setPreferredSize(new Dimension(Short.MAX_VALUE,50));

	            button.setAlignmentX(0.5f);
	            text.setLineWrap(true);
	            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	            panel.add(field);
	            panel.add(text);
	            panel.add(button);
				dialog = Utils.makeDialog("DirectMessage",panel);
				dialog.setVisible(true);
			}
		});


        menu.add(item);
        setJMenuBar(menuBar);

        panel1.setLayout(new BoxLayout(panel1,BoxLayout.LINE_AXIS));
        panel1.add(text);
        panel1.add(button);

        list = new JList(timeline.toArray());
        list.setCellRenderer(new setTimeline());
        list.addListSelectionListener(this);

        JScrollPane pane = new JScrollPane();
        pane.getViewport().setView(list);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
        panel2.add(panel1);
        panel2.add(pane);

        add(panel2);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tweet = text.getText();
                Twitter twitter = Utils.getInst();
                try {
                    twitter.updateStatus(tweet);
                    text.setText("");
                } catch (TwitterException e1) {
                    e1.printStackTrace();
                }
            }
        });
        setVisible(true); //表示
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

        final Status status = (Status)list.getSelectedValue();

        if(old!=list.getSelectedIndex()){
            old = list.getSelectedIndex();
            JPanel panel = new JPanel();
            final JTextArea text = new JTextArea(3,1);
            final JButton button = new JButton("送信");

            button.setAlignmentX(0.5f);
            text.setLineWrap(true);
            text.setText("@" + status.getUser().getScreenName()+" ");
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(text);
            panel.add(button);
            final JDialog dialog = Utils.makeDialog("Reply",panel, status);
            dialog.setVisible(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Twitter twitter = Utils.getInst();
                    String message = text.getText();
                    try {
                    	twitter.updateStatus(new StatusUpdate(message).inReplyToStatusId(status.getId()));
                    	dialog.dispose();
                    } catch (TwitterException e1) {
                        e1.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            });

        }
    }


    class setTimeline implements ListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Status status = (Status)value;
            //サムネと名前
            JLabel image = null;
            try {
                image = new JLabel(new ImageIcon(new URL(status.getUser().getProfileImageURL())));
            } catch (MalformedURLException e) {
                e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
            }

            String tweet = status.getUser().getName();
            tweet = tweet + " @" + status.getUser().getScreenName();
            tweet += "\n";
            tweet += status.getText();
            JTextArea name = new JTextArea(tweet);

            String[] array = tweet.split("\n");
            int count = array.length;
            height = count>2?27*count : 65;

            //パネル
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
            panel.setSize(Short.MAX_VALUE, 60);
            panel.setPreferredSize(new Dimension(width, height));
            panel.setMinimumSize(new Dimension(width, height));
            panel.setBorder(new LineBorder(Color.black,1));

            image.setSize(50,50);
            image.setPreferredSize(new Dimension(50, 50));
            image.setMinimumSize(new Dimension(50,50));

            Dimension d1 = name.getMaximumSize();
            d1.width = Short.MAX_VALUE;
            name.setMaximumSize(d1);

            name.setRows(count);
            name.setEditable(false);
            name.setPreferredSize(null); //new Dimension(Short.MAX_VALUE,size));
            name.setMargin(new Insets(0, 0, 5, 0));
            name.setLineWrap(true);

            panel.add(image);
            panel.add(name);


            return panel; //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public static void main(String[] args){
        Properties prop;

        prop = Utils.getprop();

        //もしAccessTokenが保存されていなかったら
        if(prop.getProperty("oauth.accessToken")==null){
            new LoginActivity("hoge");
        }

        Twitter twitter = Utils.getInst();
        try {
            timeline = twitter.getHomeTimeline(new Paging(1,200));
        } catch (TwitterException e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        }

        for(Status status : timeline){
            tweet.add(status.getText());
        }
        new Main("hoge");
    }
}
