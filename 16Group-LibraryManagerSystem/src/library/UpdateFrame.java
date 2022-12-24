package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import library.InsertFrame.CancelListener;
import library.InsertFrame.GetbackListener;
import library.InsertFrame.SubmitListener;

public class UpdateFrame extends JFrame {
	// ¼��ͼ����Ϣ��ʾ����ǩ
	private JLabel isbnLabel, nameLabel, authorLabel, publisherLabel;
	private JLabel priceLabel, pubDateLabel, depositLabel, quantifyLabel;
	// ¼��ͼ����Ϣ�ı���
	private JTextField isbnField, nameField, authorField, publisherField;
	private JTextField priceField, pubDateField, depositField, quantifyField;
	// �����е��ύ��ȡ�������ذ�ť
	private JButton update, cancel, getback;
	// ����
	private JFrame jframe;
	// ��ʾ��Ϣ
	private JLabel showLabel;
	// �û�����ѧ�ţ�
	private long userId;
	//�洢Ҫ�޸ĵĸ�ʽ�����ͼ����Ϣ
	private Vector<String> bookInfo = new Vector<String>();
	//�洢Ҫ�޸ĵ�ͼ����Ϣ
	private String bookValue;

	// ��ʼ��¼��ͼ�����
	public UpdateFrame(long userId, String bookValue) {
		jframe = new JFrame("��ӭ�������޸�ͼ����Ϣ����");
		this.userId = userId;
		this.bookValue = bookValue;
		jframe.setLayout(null); // ���ô����޲���
		// �����е�¼����ʾ��Ϣ
		showLabel = new JLabel("�޸�ͼ����Ϣ", JLabel.CENTER);
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
		update = new JButton("�޸�");
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

		jframe.add(update);
		jframe.add(cancel);
		jframe.add(getback);
		// ���ô����С
		jframe.setSize(1000, 800);
		// ������ʾ��Ϣ��ǩλ��
		showLabel.setBounds(400, 20, 280, 50);
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
		update.setBounds(550, 600, 100, 70);
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
		update.setFont(new Font("", Font.BOLD, 20));
		cancel.setFont(new Font("", Font.BOLD, 20));
		getback.setFont(new Font("", Font.BOLD, 40));
		getback.setForeground(Color.BLUE);
		//�õ���ʽ�����ͼ����Ϣ
		bookInfo = getBookInfo(bookValue);
		isbnField.setText(bookInfo.elementAt(1));
		nameField.setText(bookInfo.elementAt(2));
		authorField.setText(bookInfo.elementAt(3));
		publisherField.setText(bookInfo.elementAt(4));
		priceField.setText(bookInfo.elementAt(5));
		pubDateField.setText(bookInfo.elementAt(6));
		depositField.setText(bookInfo.elementAt(7));
		quantifyField.setText(bookInfo.elementAt(8));
		// ��������ťע�ᶯ��������
		update.addActionListener(new Update1Listener());
		cancel.addActionListener(new CancelListener());
		getback.addActionListener(new GetbackListener());
		// ���¼��ͼ�����رռ����¼�
		jframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UpdateFrame1 update = new UpdateFrame1(userId);// ����������
				jframe.dispose();// �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
			}
		});
		jframe.setResizable(false); // ���ô����С���ɸı�
		jframe.setLocationRelativeTo(null); // ���ô���λ�þ���
		jframe.setVisible(true); // ���ô���ɼ�
		jframe.getContentPane().setBackground(Color.PINK);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ͷŴ���
	}
	//��ͼ����Ϣ��ʽ��
	public Vector<String> getBookInfo(String bookValue) {
		Vector<String> result = new Vector<String>();
		String str = bookValue;
		int index = str.indexOf(':');
		result.add(str.substring(2,index));
		int index1 = 0;
		str = str.substring(index+1);
		for(int i=1;i<=8;i++){
			index = str.indexOf(':');
			index1 = str.indexOf(';');
			String s = str.substring(index+1,index1).trim();
			result.add(s);
			str = str.substring(index1+1);
		}
		return result;
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
	public class Update1Listener implements ActionListener {
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
				Vector<String> bookInfo1 = new Vector<String>(); // Vector�����ʵ�ֿ������Ķ������顣
				// ����õø��ı�����������δ�ŵ�bookInfo�ַ���������
				bookInfo1.add(isbn);
				bookInfo1.add(name);
				bookInfo1.add(author);
				bookInfo1.add(publisher);
				bookInfo1.add(price);
				bookInfo1.add(pubDate);
				bookInfo1.add(deposit);
				bookInfo1.add(quantify);
	
				if (Service.updateBook(bookInfo1, bookInfo.elementAt(0).trim()) == 0) {// ��¼����Ϣ�ɹ��洢�����ݿ���
					clearField(); // ��ս����ı�������
					JOptionPane.showMessageDialog(jframe, "�޸�ͼ��ɹ���");
				} else { // ¼����Ϣ��ӵ����ݿ���ʧ��
					JOptionPane.showMessageDialog(jframe, "�޸�ͼ��ʧ�ܣ�������ȷ����Ϣ�Ƿ���д��ȷ��");
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
			UpdateFrame1 update = new UpdateFrame1(userId); // ����������
			jframe.dispose(); // �ر�¼��ͼ�����
		}
	}
}
