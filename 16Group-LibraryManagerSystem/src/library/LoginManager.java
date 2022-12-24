package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginManager{
	// �����¼������Ҫ�Ļ��������һ������������ǩ��һ���ı��ֶΣ��û�������һ�������ֶΣ����룩��һ����ť����¼��,һ����ť(ע��)
	private JFrame jframe1;
	private JLabel showLabel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton login;
	private JButton registe;
	private String str = "";
	public static void main(String[] args) {
		// ִ�г���
		new LoginManager();
	}
	
	// �Դ�����г�ʼ��
	public LoginManager() {
		jframe1 = new JFrame("��ӭ������ͼ�����ϵͳ");
		jframe1.setLayout(null); // �����޲��ָ�ʽ
		// ���ô���λ�ü���С(����һ�δ���ʵ�־���)
		jframe1.setSize(1000, 800);
		ImageIcon icon=new ImageIcon("image/Login.jpg");
		JLabel lbl=new JLabel(icon);
		lbl.setSize(icon.getIconWidth(),icon.getIconHeight());
		jframe1.getLayeredPane().add(lbl,new Integer(Integer.MIN_VALUE));
	    JPanel j=(JPanel)jframe1.getContentPane();
		j.setOpaque(false);

		// ����һ
		// Toolkit toolKit = Toolkit.getDefaultToolkit(); // ��ȡĬ�Ϲ��߰�
		// Dimension dimension = toolKit.getScreenSize(); // ��ȡ��Ļ����
		// double screenWidth = dimension.getWidth(); // ��ȡ��Ļ�Ŀ�͸�
		// double screenHight = dimension.getHeight();
		// int jframeWidth = this.getWidth(); // ��ȡ����Ŀ�͸�
		// int jframeHight = this.getHeight();
		// int x = (int) ((screenWidth - jframeWidth) / 2); // �õ���������λ��
		// int y = (int) ((screenHight - jframeHight) / 2);
		// jframe1.setLocation(x, y);
		// ������
		jframe1.setLocationRelativeTo(null);
		
		// ���������������
		showLabel = new JLabel("ͼ�����ϵͳ��¼����");
		userLabel = new JLabel("�û���");
		passwordLabel = new JLabel("����");
		userField = new JTextField(15);
		passwordField = new JPasswordField(15);
		passwordField.setEchoChar('*'); // ���ô� JPasswordField �Ļ����ַ�Ϊ*
		login = new JButton("��¼");
		login.addActionListener(new loginListener()); // ע�᣺��¼��ť�����¼�
		registe = new JButton("ע��");
		registe.addActionListener(new regisiteListener1());// ע�᣺ע�ᰴť�����¼�

		showLabel.setBounds(300, 50, 600, 80); // ����showLabel��ʾλ��
		userLabel.setBounds(210, 250, 130, 130); // ����"�û���"��ʾλ��
		passwordLabel.setBounds(210, 355, 130, 130); // ����"����"��ʾλ��
		userField.setBounds(350, 280, 450, 60); // �����ı��ֶ���ʾλ��
		passwordField.setBounds(350, 390, 450, 60); // ���ÿ����ֶ���ʾλ��
		login.setBounds(660, 550, 140, 70); // ���õ�¼��ť��ʾλ��
		registe.setBounds(200, 550, 140, 70); // ����ע�ᰴť��ʾλ��
		// ����Щ���������ӵ�������
		jframe1.add(showLabel);
		jframe1.add(userLabel);
		jframe1.add(passwordLabel);
		jframe1.add(userField);
		jframe1.add(passwordField);
		jframe1.add(login);
		jframe1.add(registe);
		// ����������������ʽ
		showLabel.setFont(new Font("����", Font.BOLD, 55));
		showLabel.setForeground(Color.BLACK);
		userLabel.setFont(new Font("����", Font.BOLD, 40));
		passwordLabel.setFont(new Font("����", Font.BOLD, 40));
		userField.setFont(new Font("����", Font.BOLD, 40));
		passwordField.setFont(new Font("����", Font.BOLD, 40));
		login.setFont(new Font("����", Font.BOLD, 40));
		registe.setFont(new Font("����", Font.BOLD, 40));
//		setBg();
		Service.windowClose(jframe1); // ��Ӵ���ر��¼�
		// loginManager.pack(); //�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ���
		jframe1.setResizable(false); // ���ô��岻�ɷŴ�
		jframe1.setVisible(true); // ʹ����ɼ�
//		jframe1.getContentPane().setBackground(Color.PINK);
		jframe1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �ͷŴ���
	}
//	public void setBg() {
//		label.setBounds(0,0, 1000,800);
//		jframe1.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
//		JPanel jp = (JPanel)jframe1.getContentPane();
//		jp.setOpaque(false);
//	}
	// ��д��¼��ť�����¼���������
	public class loginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String userName = userField.getText().trim(); // ��ȡ�ı��ֶ�������ݣ��˺ţ�
			String password = MD5.GetMD5Code(passwordField.getText().trim());// ��ȡ�����ֶ�������ݽ��м��ܣ����룩
			long id = Service.login(userName, password); // ��Ϊ�û���ʶ�ţ��˶Ե�¼��Ϣ
			if (id > 0) {// �����ݿ��п���ƥ��
				BooksManager app = new BooksManager(id); // ���������棬�����û���ʶ��
				jframe1.dispose();
				; // �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
			} else if (id == 0) {// �����ݿ�û����ȫƥ��
				JOptionPane.showMessageDialog(jframe1, "��������û��������벻��ȷ");
			} else {// �����ݿ���ȫ��ƥ��
				JOptionPane.showMessageDialog(jframe1, "��ѯ�û������");
			}
		}
	}
	// ��дע�ᰴť�����¼���������
	public class regisiteListener1 implements ActionListener {
		private JFrame jframe2 = new JFrame("��ӭ������ͼ�����ϵͳ"); // ����
		private JButton backButton = new JButton("��"); // ���ذ�ť
		private JLabel showLabel1 = new JLabel("ͼ�����ϵͳע�����", JLabel.CENTER); // ��ʾ��ʾ��Ϣ1
		private JLabel userName = new JLabel("ѧ��:"); // ��ʾ"�û���"������Ϣ
		private JLabel setPassword = new JLabel("��������:"); // ��ʾ"��������"������Ϣ
		private JLabel surePassword = new JLabel("ȷ������:"); // ��ʾ"ȷ������"������Ϣ
		private JLabel name = new JLabel("����:"); // ��ʾ"����"������Ϣ
		private JLabel sex = new JLabel("�Ա�:"); // ��ʾ"�Ա�"������Ϣ
		private JLabel birthday = new JLabel("��������:"); // ��ʾ"����������"������Ϣ
		private JLabel showLabel2 = new JLabel("*������6-12λ���֡��ַ����*"); // ��ʾ��ʾ��Ϣ2�����룩
		private JLabel showLabel3 = new JLabel("��ʽ��(****-**-**)"); // ��ʾ��ʾ��Ϣ3���������ڣ�
		private JTextField text1 = new JTextField(15); // ��ʾ�û�����Ӧ���ı���
		private JPasswordField text2 = new JPasswordField(15); // ��ʾ���������Ӧ���ı���
		private JPasswordField text3 = new JPasswordField(15); // ��ʾȷ�������Ӧ���ı���
		private JTextField text4 = new JTextField(15); // ��ʾ������Ӧ���ı���
		// private JTextField text5 = new JTextField(15); // ��ʾ�Ա��Ӧ���ı���
		private JRadioButton radio1 = new JRadioButton("��"); // ��ѡ��1
		private JRadioButton radio2 = new JRadioButton("Ů"); // ��ѡ��2
		private ButtonGroup group = new ButtonGroup(); // ��������Ϊһ�鰴ť����һ�����������
		private JTextField text6 = new JTextField(15); // ��ʾ�������ڶ�Ӧ���ı���
		private JButton regisiteButton = new JButton("ע��"); // ע�ᰴť
		public regisiteListener1() {
			text2.setEchoChar('*'); // ���ô� text2 �Ļ����ַ�Ϊ*���������룩
			text3.setEchoChar('*'); // ���ô� text3 �Ļ����ַ�Ϊ*��ȷ�����룩
			jframe2.setLayout(null); // ���ָ�ʽ���
			jframe2.setSize(1000, 800); // ���ô����С
			jframe2.setLocationRelativeTo(null); // ���ô������
			backButton.setBounds(30, 30, 80, 50); // ���÷��ذ�ťλ�ü���С
			// ע�᷵�ذ�ť���������¼�
			backButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginManager login = new LoginManager(); // ��������ذ�ťʱ����¼��������
					jframe2.dispose(); // �ͷ��ɴ�
										// Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
					// ��յ�¼��������
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text4.setText("");
					// text5.setText("");
					text6.setText("");
				}
			});
			// ע�ᵥѡ��1����������
			radio1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					str = "��";
				}
			});
			// ע�ᵥѡ��2����������
			radio2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					str = "Ů";
				}
			});
			// ���û�������Ĵ�С��λ��
			showLabel1.setBounds(200, 50, 600, 80);
			userName.setBounds(250, 170, 100, 40);
			setPassword.setBounds(250, 250, 100, 40);
			surePassword.setBounds(250, 330, 100, 40);
			name.setBounds(250, 410, 100, 40);
			sex.setBounds(250, 490, 100, 40);
			birthday.setBounds(250, 570, 100, 40);
			text1.setBounds(350, 170, 400, 40);
			text2.setBounds(350, 250, 400, 40);
			text3.setBounds(350, 330, 400, 40);
			text4.setBounds(350, 410, 400, 40);
			// text5.setBounds(350, 490, 400, 40);
			radio1.setBounds(350, 490, 100, 40);
			radio2.setBounds(500, 490, 100, 40);
			text6.setBounds(350, 570, 400, 40);
			regisiteButton.setBounds(800, 650, 100, 50);
			showLabel2.setBounds(350, 295, 400, 20);
			showLabel3.setBounds(350, 610, 400, 20);
			// ����Щ���������ӵ�ע�������
			jframe2.add(backButton);
			jframe2.add(userName);
			jframe2.add(showLabel1);
			jframe2.add(setPassword);
			jframe2.add(surePassword);
			jframe2.add(name);
			jframe2.add(sex);
			jframe2.add(birthday);
			jframe2.add(text1);
			jframe2.add(text2);
			jframe2.add(text3);
			jframe2.add(text4);
			// jframe2.add(text5);
			group.add(radio1); // ���һ�����ʵ�ֵ�ѡ
			group.add(radio2);
			jframe2.add(radio1);
			jframe2.add(radio2);
			jframe2.add(text6);
			jframe2.add(regisiteButton);
			jframe2.add(showLabel2);
			jframe2.add(showLabel3);
			// ����������ݵ�������ʽ����ɫ
			backButton.setFont(new Font("", Font.BOLD, 40));// ������ʽ���壬40
			backButton.setForeground(Color.BLUE);// ������ɫΪ��ɫ
			userName.setFont(new Font("", Font.BOLD, 20));
			showLabel1.setFont(new Font("", Font.BOLD, 40));
			showLabel1.setForeground(Color.BLUE);
			setPassword.setFont(new Font("", Font.BOLD, 20));
			surePassword.setFont(new Font("", Font.BOLD, 20));
			name.setFont(new Font("", Font.BOLD, 20));
			sex.setFont(new Font("", Font.BOLD, 20));
			birthday.setFont(new Font("", Font.BOLD, 20));
			text1.setFont(new Font("", Font.BOLD, 20));
			text2.setFont(new Font("", Font.BOLD, 20));
			text3.setFont(new Font("", Font.BOLD, 20));
			text4.setFont(new Font("", Font.BOLD, 20));
			// text5.setFont(new Font("", Font.BOLD, 20));
			radio1.setFont(new Font("", Font.BOLD, 20));
			radio2.setFont(new Font("", Font.BOLD, 20));
			text6.setFont(new Font("", Font.BOLD, 20));
			regisiteButton.setFont(new Font("", Font.BOLD, 20));
			regisiteButton.setForeground(Color.BLUE);
			showLabel2.setFont(new Font("", Font.BOLD, 18));
			showLabel2.setForeground(Color.RED);
			showLabel3.setFont(new Font("", Font.BOLD, 18));
			showLabel3.setForeground(Color.RED);
		}

		// �����¼�������ע�ᰴť�����¼�
		@Override
		public void actionPerformed(ActionEvent e) {
			jframe2.setResizable(false); // ����ע������С���ɸı�
			jframe2.getContentPane().setBackground(Color.PINK);
			jframe2.setVisible(true); // �������¼�����ע�ᰴťʱ����ע�����ɼ�
			jframe1.dispose();
			; // �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
				// ��յ�¼������������
			userField.setText("");
			passwordField.setText("");

			Service.windowClose(jframe2);// ע�����Ĵ��ڼ����¼�
			jframe2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ͷ�ע����洰��
			regisiteButton.addActionListener(new regisiteListener2()); // ע��������ע�ᰴť���������¼�
		}

		// ����ע��������ע�ᰴť���������¼�
		public class regisiteListener2 implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (text1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jframe2, "ѧ����Ϊ�գ�����������д��");
				} else if (text2.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jframe2, "����������Ϊ�գ�����������д��");
				} else if (text3.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jframe2, "ȷ��������Ϊ�գ�������д��д��");
				} else if (text4.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jframe2, "������Ϊ�գ�������д��д��");
				} // else if (text5.getText().trim().equals("")) {
				else if (str.equals("")) {
					JOptionPane.showMessageDialog(jframe2, "�Ա���Ϊ�գ�������д��д��");
				} else if (text6.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jframe2, "����������Ϊ�գ�������д��д��");
				} else {
					// ע���û���Ϣ¼�����ݿ�
					Service.regisiteUser(text1.getText().trim(), text2.getText().trim(), text3.getText().trim(),
							text4.getText().trim(), /* text5.getText().trim() */str, text6.getText().trim());
				}
			}
		}
	}


}
