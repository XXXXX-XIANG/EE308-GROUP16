package library;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WelcomeFrame {
    //�������
    private JFrame jf;
    private JLayeredPane layeredPane;
    private JPanel contentPanel;
    private ImageIcon image = new ImageIcon("image/welcome.jfif");
    private JLabel imageLabel = new JLabel(image);
    private JButton ok = new JButton("����ϵͳ");
    private JLabel h1 = new JLabel("�� ӭ �� �� ͼ �� �� �� ϵ ͳ");
    private JLabel h2 = new JLabel("Ժ    ϵ: ");
    private JLabel h3 = new JLabel("÷Ŭ˹���ʹ���ѧԺ");
    private JLabel h4 = new JLabel("ר    ҵ: ");
    private JLabel h5 = new JLabel("������Ϣ����      ");
    private JLabel h6 = new JLabel("��    ��: ");
    private JLabel h7 = new JLabel("������           ");
    private JLabel h8 = new JLabel("��    ��: ");
    private JLabel h9 = new JLabel("16Group         ");
    private JLabel h10 = new JLabel("������ʦ: ");
    private JLabel h11 = new JLabel("�ֆ���          ");

    private Box vbox = Box.createVerticalBox();
    private Box box1 = Box.createHorizontalBox();
    private Box box2 = Box.createHorizontalBox();
    private Box box3 = Box.createHorizontalBox();
    private Box box4 = Box.createHorizontalBox();
    private Box box5 = Box.createHorizontalBox();
    private Box box6 = Box.createHorizontalBox();
    private Box box7 = Box.createHorizontalBox();

    //��װ��ͼ
    public WelcomeFrame() {
        init();
    }

    void init() {
        jf = new JFrame("��ӭ����ͼ�����ϵͳ");
        imageLabel.setSize(image.getIconWidth(), image.getIconHeight());
        layeredPane = jf.getLayeredPane();
        layeredPane.add(imageLabel, new Integer(Integer.MIN_VALUE));
        contentPanel = (JPanel) jf.getContentPane();
        contentPanel.setOpaque(false);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setBounds(300, 50, 1000, 800);
        //jf.getContentPane().setBackground(Color.PINK);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        h1.setFont(new Font("����", Font.PLAIN, 55));
        h2.setFont(new Font("����", Font.PLAIN, 35));
        h3.setFont(new Font("����", Font.PLAIN, 35));
        h4.setFont(new Font("����", Font.PLAIN, 35));
        h5.setFont(new Font("����", Font.PLAIN, 35));
        h6.setFont(new Font("����", Font.PLAIN, 35));
        h7.setFont(new Font("����", Font.PLAIN, 35));
        h8.setFont(new Font("����", Font.PLAIN, 35));
        h9.setFont(new Font("����", Font.PLAIN, 35));
        h10.setFont(new Font("����", Font.PLAIN, 35));
        h11.setFont(new Font("����", Font.PLAIN, 35));
        ok.setFont(new Font("����", Font.PLAIN, 35));
        box1.add(h1);
        box2.add(h2);
        box2.add(h3);
        box3.add(h4);
        box3.add(h5);
        box4.add(h6);
        box4.add(h7);
        box5.add(h8);
        box5.add(h9);
        box6.add(h10);
        box6.add(h11);
        box7.add(ok);
        vbox.add(Box.createVerticalStrut(150));
        vbox.add(box1);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(box2);
        vbox.add(Box.createVerticalStrut(20));
        vbox.add(box3);
        vbox.add(Box.createVerticalStrut(20));
        vbox.add(box4);
        vbox.add(Box.createVerticalStrut(20));
        vbox.add(box5);
        vbox.add(Box.createVerticalStrut(20));
        vbox.add(box6);
        vbox.add(Box.createVerticalStrut(150));
        vbox.add(box7);
        ok.setContentAreaFilled(false);
        ok.setBorder(BorderFactory.createBevelBorder(1, Color.cyan, Color.pink));
        contentPanel.add(vbox);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginManager();
                jf.dispose();
            }
        });
    }
    public static void main(String[] args) {
        new WelcomeFrame();
    }
}

