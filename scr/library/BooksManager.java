package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BooksManager extends JFrame implements ActionListener {
	// ����Ϊ8�����ܲ˵���
	private JMenuItem insertMenuItem; // ¼��ͼ��˵���
	private JMenuItem deleteMenuItem; // ɾ��ͼ��˵���
	private JMenuItem updateMenuItem; // �޸�ͼ����Ϣ�˵���
	private JMenuItem selectMenuItem; // ����ͼ��˵���
	private JMenuItem lendMenuItem; // ��ʼ����˵���
	private JMenuItem lendRecordMenuItem; // �����¼�˵���
	private JMenuItem returnMenuItem; // ��ʼ����˵���
	private JMenuItem returnRecordMenuItem; // �����¼�˵���
	private JMenuItem exitloadMenuItem;

	private JInternalFrame titleFrame; // �ṩ�ܶ౾�����幦�ܵ�������������Щ���ܰ����϶����رա����ͼ�ꡢ������С��������ʾ��֧�ֲ˵�����
	private JDesktopPane desktop = new JDesktopPane(); // ���ڴ������ĵ�������������������
	private long userId;// ѧ�ţ������û���ʶ
	// ��ʼ��ʼ������

	public BooksManager(long userId) {
		super("ͼ�������Ϣϵͳ"); // ���ڱ���
		this.userId = userId; // �õ����ݹ�����ѧ��
		// �����˵������˵����˵������
		JMenuBar menubar = new JMenuBar();
		JMenu manageMenu = new JMenu("ͼ�����");
		JMenu lendMenu = new JMenu("����ͼ��");
		JMenu returnMenu = new JMenu("�黹ͼ��");
		JMenu exitMenu = new JMenu("�˳���¼");
		insertMenuItem = new JMenuItem("¼��ͼ��");
		deleteMenuItem = new JMenuItem("ɾ��ͼ��");
		updateMenuItem = new JMenuItem("�޸�ͼ����Ϣ");
		selectMenuItem = new JMenuItem("����ͼ��");
		lendMenuItem = new JMenuItem("��ʼ����");
		lendRecordMenuItem = new JMenuItem("���ļ�¼");
		returnMenuItem = new JMenuItem("��ʼ����");
		returnRecordMenuItem = new JMenuItem("�����¼");
		exitloadMenuItem = new JMenuItem("�˳�");
		// �Ѳ˵�����ӵ���ӦexitloadMenuItem�Ĳ˵���Ѳ˵���ӵ��˵�����
		returnMenu.add(returnMenuItem);
		returnMenu.add(returnRecordMenuItem);
		lendMenu.add(lendMenuItem);
		lendMenu.add(lendRecordMenuItem);
		manageMenu.add(insertMenuItem);
		manageMenu.add(deleteMenuItem);
		manageMenu.add(updateMenuItem);
		manageMenu.add(selectMenuItem);
		exitMenu.add(exitloadMenuItem);
		menubar.add(manageMenu);
		menubar.add(lendMenu);
		menubar.add(returnMenu);
		menubar.add(exitMenu);
		// ���������˵����ݵ�������ʽ����ɫ�����壬20����ɫ
		manageMenu.setFont(new Font("", Font.BOLD, 20));
		lendMenu.setFont(new Font("", Font.BOLD, 20));
		returnMenu.setFont(new Font("", Font.BOLD, 20));
		exitMenu.setFont(new Font("", Font.BOLD, 20));
		
		manageMenu.setForeground(Color.BLUE);
		lendMenu.setForeground(Color.BLUE);
		returnMenu.setForeground(Color.BLUE);
		exitMenu.setForeground(Color.BLUE);
		
		insertMenuItem.setFont(new Font("", Font.BOLD, 20));
		deleteMenuItem.setFont(new Font("", Font.BOLD, 20));
		updateMenuItem.setFont(new Font("", Font.BOLD, 20));
		selectMenuItem.setFont(new Font("", Font.BOLD, 20));
		lendMenuItem.setFont(new Font("", Font.BOLD, 20));
		lendRecordMenuItem.setFont(new Font("", Font.BOLD, 20));
		returnMenuItem.setFont(new Font("", Font.BOLD, 20));
		returnRecordMenuItem.setFont(new Font("", Font.BOLD, 20));
		exitloadMenuItem.setFont(new Font("", Font.BOLD, 20));

		this.setJMenuBar(menubar); // �Ѳ˵�����ӵ�������
		this.setSize(1000, 800); // ���ô����С
		Container con = this.getContentPane(); // ���ش˴���� contentPane ���� ��
		con.add(desktop); // ������������ӵ���������
		JLabel label = new JLabel("��ӭ��ʹ��ͼ�������Ϣϵͳ", JLabel.CENTER); // ������Ļ�ӭ��Ϣ
		label.setFont(new Font("����", Font.BOLD, 50)); // ��Ϣ��������ʽ
		label.setForeground(Color.BLUE); // ��Ϣ������Ϊ��ɫ
		titleFrame = new JInternalFrame(null, true); // �����ര������ޱ��⣬���ɹر�
		Container c = titleFrame.getContentPane(); // �õ��˶ര�����
		c.add(label, BorderLayout.CENTER); // �ѻ�ӭ������ӵ��ര����
		titleFrame.setSize(990, 740); // ���öര��Ĵ�С
		desktop.add(titleFrame); // �Ѷര����ӵ���������
		titleFrame.setVisible(true); // ���öര��ɼ�
		titleFrame.getContentPane().setBackground(Color.PINK);
		this.setLocationRelativeTo(null);// ���������
		this.setResizable(false); // �������С���ɸı�
		this.setVisible(true); // ������ɼ�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ͷ������洰��

		Service.windowClose(this);// ������رմ����¼�
		// Ϊ�����˵�����Ӷ��������¼�
		insertMenuItem.addActionListener(this);
		deleteMenuItem.addActionListener(this);
		updateMenuItem.addActionListener(this);
		selectMenuItem.addActionListener(this);
		lendMenuItem.addActionListener(this);
		lendRecordMenuItem.addActionListener(this);
		returnMenuItem.addActionListener(this);
		returnRecordMenuItem.addActionListener(this);
		exitloadMenuItem.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); // ���������Event���󣬷���Object
		if (source == insertMenuItem) {// ����������¼��ͼ��˵���
			InsertFrame insertFrame = new InsertFrame(userId);// ����¼��ͼ�����
			this.dispose();// �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
		}
		if (source == deleteMenuItem) {// ����������ɾ��ͼ��˵���
			DeleteFrame deleteFrame = new DeleteFrame(userId); // ɾ��ͼ��
			this.dispose();
			// ���ɾ��ͼ�����رռ����¼�
		}
		if (source == updateMenuItem) {// �����������޸�ͼ����Ϣ�˵���
			UpdateFrame1 updateFrame = new UpdateFrame1(userId); // �޸�ͼ����Ϣ
			this.dispose(); // �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
			// ����޸�ͼ����Ϣ����رռ����¼�

		}
		if (source == selectMenuItem) {// ���������Ǽ���ͼ��˵���
			SelectFrame selectFrame = new SelectFrame(userId);// ��ѯͼ��
			this.dispose();
			// ��Ӽ���ͼ�����رռ����¼�
		}
		if (source == lendMenuItem) {// ���������ǿ�ʼ����˵���
			LendFrame lendFrame = new LendFrame(userId);// ��ʼ����
			this.dispose();
		}
		if (source == lendRecordMenuItem) {// ���������ǽ����¼�˵���
			LendRecordFrame lendRecordFrame = new LendRecordFrame(userId); // �����¼
			this.dispose();
			// ��ӽ����¼����رռ����¼�
		}
		if (source == returnMenuItem) {// �������Ŀ�ʼ����˵���
			ReturnFrame returnFrame = new ReturnFrame(userId); // ��ʼ����
			this.dispose();
			// ��ӿ�ʼ�������رռ����¼�
		}
		if (source == returnRecordMenuItem) {// ���������ǻ����¼�˵���
			ReturnRecordFrame returnRecordFrame = new ReturnRecordFrame(userId);
			// �����¼
			this.dispose();
			// ��ӻ����¼����رռ����¼�
		}
		if(source == exitloadMenuItem) {
			LoginManager lm = new LoginManager();
			this.dispose();
			// ����˳���¼����,�رռ����¼�
		}
	}
}
