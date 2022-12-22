package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import library.ReturnFrame.GetbackListener;
import library.ReturnFrame.SelectListener;
import library.ReturnFrame.returnListener;

public class ReturnRecordFrame extends JFrame {
	private JLabel showLabel, nameLabel, authorLabel, publisherLabel, hintLabel1, hintLabel2;
	private JTextField nameField, authorField;
	private JComboBox publisherChoice;
	private JRadioButton radio1, radio2;
	private ButtonGroup group;
	private JButton selectButton, getbackButton, returnRecordButton;
	private JList list;
	private long userId;
	private JScrollPane scroll;

	public ReturnRecordFrame(long userId) {
		super("��ӭ�����������¼ͼ�����");
		this.userId = userId;
		this.setLayout(null);
		Vector<String> publisherInfo = Service.getPublisher();
		showLabel = new JLabel("�����¼");
		nameLabel = new JLabel("��  ����");
		authorLabel = new JLabel("��  �ߣ�");
		publisherLabel = new JLabel("�����磺");
		hintLabel1 = new JLabel("����������");
		hintLabel2 = new JLabel("��������������¼����");

		nameField = new JTextField(30);
		authorField = new JTextField(20);
		publisherChoice = new JComboBox(publisherInfo);
		radio1 = new JRadioButton("��ȫһ��", true);
		radio2 = new JRadioButton("ģ����ѯ");
		group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("", Font.BOLD, 20));
		scroll = new JScrollPane();
		scroll.getViewport().setView(list);

		selectButton = new JButton("��ʼ����");
		getbackButton = new JButton("��");
		returnRecordButton = new JButton("�鿴��ϸ��Ϣ");

		this.add(showLabel);
		this.add(nameLabel);
		this.add(authorLabel);
		this.add(publisherLabel);
		this.add(hintLabel1);
		this.add(hintLabel2);
		this.add(nameField);
		this.add(authorField);
		this.add(publisherChoice);
		this.add(radio1);
		this.add(radio2);
		this.add(selectButton);
		this.add(getbackButton);
		this.add(returnRecordButton);
		this.add(scroll);

		this.setSize(1000, 800);
		showLabel.setBounds(400, 20, 200, 50);
		nameLabel.setBounds(160, 100, 80, 50);
		authorLabel.setBounds(160, 170, 80, 50);
		publisherLabel.setBounds(160, 240, 100, 50);
		nameField.setBounds(260, 100, 600, 50);
		authorField.setBounds(260, 170, 600, 50);
		publisherChoice.setBounds(260, 240, 600, 50);
		hintLabel1.setBounds(250, 300, 110, 50);
		radio1.setBounds(360, 300, 110, 50);
		radio2.setBounds(470, 300, 110, 50);
		selectButton.setBounds(630, 300, 130, 50);
		hintLabel2.setBounds(30, 350, 240, 50);
		scroll.setBounds(30, 400, 940, 320);
		getbackButton.setBounds(25, 25, 80, 50);
		returnRecordButton.setBounds(410, 720, 180, 40);

		showLabel.setFont(new Font("", Font.BOLD, 40));
		showLabel.setForeground(Color.BLUE);
		nameLabel.setFont(new Font("", Font.BOLD, 20));
		authorLabel.setFont(new Font("", Font.BOLD, 20));
		publisherLabel.setFont(new Font("", Font.BOLD, 20));
		nameField.setFont(new Font("", Font.BOLD, 20));
		authorField.setFont(new Font("", Font.BOLD, 20));
		publisherChoice.setFont(new Font("", Font.BOLD, 20));
		hintLabel1.setFont(new Font("", Font.BOLD, 20));
		radio1.setFont(new Font("", Font.BOLD, 20));
		radio2.setFont(new Font("", Font.BOLD, 20));
		selectButton.setFont(new Font("", Font.BOLD, 20));
		hintLabel2.setFont(new Font("", Font.BOLD, 20));
		scroll.setFont(new Font("", Font.BOLD, 20));
		getbackButton.setFont(new Font("", Font.BOLD, 40));
		getbackButton.setForeground(Color.BLUE);
		returnRecordButton.setFont(new Font("", Font.BOLD, 20));

		getbackButton.addActionListener(new GetbackListener());
		selectButton.addActionListener(new SelectListener());
		returnRecordButton.addActionListener(new returnRecordListener());

		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BooksManager app = new BooksManager(userId);
				dispose();
			}
		});
		this.setVisible(true);
		this.getContentPane().setBackground(Color.PINK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public class GetbackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BooksManager app = new BooksManager(userId);
			dispose();
		}
	}

	public class SelectListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Vector<String> selectResult = new Vector<String>();
			list.setListData(selectResult);// ����һ�� Vector ����ֻ��
											// ListModel�������ʾ�����ѯ���б�
			String name = nameField.getText().trim();
			String author = authorField.getText().trim();
			String publisher = publisherChoice.getSelectedItem().toString();
			String radio = "";
			if (radio1.isSelected()) {
				radio = radio1.getText().trim();
			}
			if (radio2.isSelected()) {
				radio = radio2.getText().trim();
			}
			selectResult = Service.selectReturnBooks(name, author, publisher, radio);
			list.setListData(selectResult);
			if (selectResult.isEmpty()) {
				JOptionPane.showMessageDialog(null, "û�м���������������ͼ�飬�����������룡");
			}
			nameField.setText("");
			authorField.setText("");
			publisherChoice.setSelectedIndex(0);
		}
	}

	public class returnRecordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String result = (String) (list.getSelectedValue());
			if (result == null) {
				JOptionPane.showMessageDialog(null, "�����ڼ��������ѡ��һ��������䣡");
				return;
			}
			String str[] = new String[10];
			int index = 0;
			index = result.indexOf(':');
			str[0] = result.substring(0, index).trim();
			result = result.substring(index + 1);
			for (int i = 1; i <= 9; i++) {
				index = result.indexOf(';');
				str[i] = result.substring(0, index).trim();
				result = result.substring(index + 1);
			}
			JOptionPane.showMessageDialog(null,
					"��Ҫ�鿴��һ�������¼����ϸ��Ϣ���£�\n" + str[0] + "\n" + str[1] + "\n" + str[2] + "\n" + str[3] + "\n" + str[4]
							+ "\n" + str[5] + "\n" + str[6] + "\n" + str[7] + "\n" + str[8] + "\n" + str[9] + "\n");
		}
	}
}
