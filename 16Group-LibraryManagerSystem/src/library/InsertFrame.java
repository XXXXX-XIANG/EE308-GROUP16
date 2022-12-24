package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertFrame extends JFrame {
	// ¼��ͼ����Ϣ��ʾ����ǩ
	private JLabel isbnLabel, nameLabel, authorLabel, publisherLabel;
	private JLabel priceLabel, pubDateLabel, depositLabel, quantifyLabel;
	// ¼��ͼ����Ϣ�ı���
	private JTextField isbnField, nameField, authorField, publisherField;
	private JTextField priceField, pubDateField, depositField, quantifyField;
	// �����е��ύ��ȡ�������ذ�ť
	private JButton submit, cancel, getback;
	// ����
	private JFrame jframe;
	// ��ʾ��Ϣ
	private JLabel showLabel;
	// �û�����ѧ�ţ�
	private long userId;

	// ��ʼ��¼��ͼ�����
	public InsertFrame(long userId) {
		jframe = new JFrame("��ӭ������¼��ͼ�����");
		this.userId = userId;
		jframe.setLayout(null); // ���ô����޲���
		// �����е�¼����ʾ��Ϣ
		showLabel = new JLabel("¼��ͼ��", JLabel.CENTER);
		isbnLabel = new JLabel("��        ��");
		nameLabel = new JLabel("��        ��");
		authorLabel = new JLabel("��        ��");
		publisherLabel = new JLabel("��   ��   ��");
		priceLabel = new JLabel("��        ��");
		pubDateLabel = new JLabel("����ʱ��");
		depositLabel = new JLabel("���λ��");
		quantifyLabel = new JLabel("��        ��");
		// ���ý����е��ı�������ݼ�����
		isbnField = new JTextField(25);
		nameField = new JTextField(25);
		authorField = new JTextField(25);
		publisherField = new JTextField(25);
		priceField = new JTextField(25);
		pubDateField = new JTextField(25);
		depositField = new JTextField(25);
		quantifyField = new JTextField(25);
		// ����ť�����ʾ��Ϣ
		submit = new JButton("�ύ");
		cancel = new JButton("ȡ��");
		getback = new JButton("��");
		// �����������ӵ�������
		jframe.add(showLabel);
		jframe.add(isbnLabel);
		jframe.add(nameLabel);
		jframe.add(authorLabel);
		jframe.add(publisherLabel);
		jframe.add(priceLabel);
		jframe.add(pubDateLabel);
		jframe.add(depositLabel);
		jframe.add(quantifyLabel);

		jframe.add(isbnField);
		jframe.add(nameField);
		jframe.add(authorField);
		jframe.add(publisherField);
		jframe.add(priceField);
		jframe.add(pubDateField);
		jframe.add(depositField);
		jframe.add(quantifyField);

		jframe.add(submit);
		jframe.add(cancel);
		jframe.add(getback);
		// ���ô����С
		jframe.setSize(1000, 800);
		// ������ʾ��Ϣ��ǩλ��
		showLabel.setBounds(400, 20, 200, 50);
		isbnLabel.setBounds(50, 200, 100, 50);
		nameLabel.setBounds(550, 200, 100, 50);
		authorLabel.setBounds(50, 290, 100, 50);
		publisherLabel.setBounds(550, 290, 100, 50);
		priceLabel.setBounds(50, 380, 100, 50);
		pubDateLabel.setBounds(550, 380, 100, 50);
		depositLabel.setBounds(50, 470, 100, 50);
		quantifyLabel.setBounds(550, 470, 100, 50);
		// �����ı���λ��
		isbnField.setBounds(150, 200, 300, 50);
		nameField.setBounds(650, 200, 300, 50);
		authorField.setBounds(150, 290, 300, 50);
		publisherField.setBounds(650, 290, 300, 50);
		priceField.setBounds(150, 380, 300, 50);
		pubDateField.setBounds(650, 380, 300, 50);
		depositField.setBounds(150, 470, 300, 50);
		quantifyField.setBounds(650, 470, 300, 50);
		// ����������ťλ��
		submit.setBounds(550, 600, 100, 70);
		cancel.setBounds(350, 600, 100, 70);
		getback.setBounds(50, 50, 80, 50);
		// ���ý����и������������ʽ����ɫ
		showLabel.setFont(new Font("", Font.BOLD, 40));
		showLabel.setForeground(Color.BLUE);
		isbnLabel.setFont(new Font("", Font.BOLD, 20));
		nameLabel.setFont(new Font("", Font.BOLD, 20));
		authorLabel.setFont(new Font("", Font.BOLD, 20));
		publisherLabel.setFont(new Font("", Font.BOLD, 20));
		priceLabel.setFont(new Font("", Font.BOLD, 20));
		pubDateLabel.setFont(new Font("", Font.BOLD, 20));
		depositLabel.setFont(new Font("", Font.BOLD, 20));
		quantifyLabel.setFont(new Font("", Font.BOLD, 20));
		isbnField.setFont(new Font("", Font.BOLD, 20));
		nameField.setFont(new Font("", Font.BOLD, 20));
		authorField.setFont(new Font("", Font.BOLD, 20));
		publisherField.setFont(new Font("", Font.BOLD, 20));
		priceField.setFont(new Font("", Font.BOLD, 20));
		pubDateField.setFont(new Font("", Font.BOLD, 20));
		depositField.setFont(new Font("", Font.BOLD, 20));
		quantifyField.setFont(new Font("", Font.BOLD, 20));
		submit.setFont(new Font("", Font.BOLD, 20));
		cancel.setFont(new Font("", Font.BOLD, 20));
		getback.setFont(new Font("", Font.BOLD, 40));
		getback.setForeground(Color.BLUE);

		// ��������ťע�ᶯ��������
		submit.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListener());
		getback.addActionListener(new GetbackListener());
		// ���¼��ͼ�����رռ����¼�
		jframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BooksManager app = new BooksManager(userId);// ����������
				jframe.dispose();// �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
			}
		});
		jframe.setResizable(false); // ���ô����С���ɸı�
		jframe.setLocationRelativeTo(null); // ���ô���λ�þ���
		jframe.setVisible(true); // ���ô���ɼ�
		jframe.getContentPane().setBackground(Color.PINK);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ͷŴ���
	}

	// ��ս���¼����Ϣ
	public void clearField() {
		isbnField.setText("");
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		priceField.setText("");
		pubDateField.setText("");
		depositField.setText("");
		quantifyField.setText("");
	}

	// ���'�ύ'���������¼�
	public class SubmitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ȡ�����е�¼���ı�����
			String isbn = isbnField.getText().trim();
			String name = nameField.getText().trim();
			String author = authorField.getText().trim();
			String publisher = publisherField.getText().trim();
			String price = priceField.getText().trim();
			String pubDate = pubDateField.getText().trim();
			String deposit = depositField.getText().trim();
			String quantify = quantifyField.getText().trim();
			if (isbn.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'���'��Ϊ�գ�����������д��");
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'����'��Ϊ�գ�����������д��");
			} else if(author.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'����;��Ϊ�գ�����������д��");
			} else if(publisher.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'������'��Ϊ�գ�����������д��");
			} else if(price.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'�۸�'��Ϊ�գ�����������д��");
			} else if(pubDate.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'����ʱ��'��Ϊ�գ�����������д��");
			} else if(deposit.equals("")) {
				JOptionPane.showMessageDialog(jframe, "'���λ��'Ϊ�գ�����������д��");
			} else if(quantify.equals("")||quantify.trim().equals("0")) {
				JOptionPane.showMessageDialog(jframe, "'����'��Ϊ��(0)������������д��");
			} else {//�ı������ݶ���Ϊ��ʱ
				Vector<String> bookInfo = new Vector<String>(); // Vector�����ʵ�ֿ������Ķ������顣
				// ����õø��ı�����������δ�ŵ�bookInfo�ַ���������
				bookInfo.add(isbn);
				bookInfo.add(name);
				bookInfo.add(author);
				bookInfo.add(publisher);
				bookInfo.add(price);
				bookInfo.add(pubDate);
				bookInfo.add(deposit);
				bookInfo.add(quantify);
	
				if (Service.addBook(bookInfo) == 0) {// ��¼����Ϣ�ɹ��洢�����ݿ���
					clearField(); // ��ս����ı�������
					JOptionPane.showMessageDialog(jframe, "���ͼ��ɹ���");
				} else { // ¼����Ϣ��ӵ����ݿ���ʧ��
					JOptionPane.showMessageDialog(jframe, "���ͼ��ʧ�ܣ�������ȷ����Ϣ�Ƿ���д��ȷ��");
				}
			}
			
		}
	}

	// ���'ȡ��'��ť���������¼�
	public class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clearField(); // �����ȡ��ʱ����ս����ı�������
		}
	}

	// ���'����'��ť���������¼�
	public class GetbackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame app = new BooksManager(userId); // ����������
			jframe.dispose(); // �ر�¼��ͼ�����
		}
	}
}
