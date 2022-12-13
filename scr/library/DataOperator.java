package library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DataOperator {
	Connection con = null; // ���ض����ݿ�����ӣ��Ự������������������ִ�� SQL ��䲢���ؽ��
	private PreparedStatement pstmt; // ��ʾԤ����� SQL ���Ķ���ʹ���ݿ����Ч�ʽϸ�Ч
	private String sql;// �洢sql���
	// �������ݿ�JDBC��������

	public void loadDatabaseDriver() {
		try {
			System.out.println("���ݿ������ɹ�");
			Class.forName("com.mysql.cj.jdbc.Driver"); // ����JDBC-MySQL����
		} catch (ClassNotFoundException e) {
			System.out.println("");
			System.out.println(e);
		}
	}

	// ����myBooks���ݿ�
	public void connect() {
		String uri = "jdbc:mysql://localhost:3306/mybooks";
		String user = "root";
		String password = "slb13646960781";
		try { // ��ͼ�������������ݿ� URL �����ӡ�
			//System.out.println("���ݿ����ӳɹ�");
			con = DriverManager.getConnection(uri, user, password);
		} catch (SQLException e) {
			System.out.println("���ݿ����ӳ���");
			System.out.println(e);
		}
	}

	// ��user�������admin�û�(���userΪ��)
	public void addSuperUser() {
		sql = "select * from user"; // �洢sql���

		try {
			pstmt = con.prepareStatement(sql); // �õ�Ԥ����������
			ResultSet rs = pstmt.executeQuery(); // ����ѯ�õ����ݿ����������ݱ�
			if (!rs.next()) {
				String name = "Admin";
				String password = MD5.GetMD5Code("123456"); // �Գ����û�������м���
				sql = "insert into user values(?,?)"; // ʹ��ͨ���?�������ֶε�ֵ
				pstmt = con.prepareStatement(sql); // Ԥ����sql���
				pstmt.setString(1, name); // ���ó����û���
				pstmt.setString(2, password); // ���ó����û�����
				pstmt.executeUpdate(); // �������ݿ�
				con.close();

			}
		} catch (SQLException e) {
			System.out.println("��ӳ����û�����");
			System.out.println(e);
		}

	}

	// ��ѯ�û����˶��û����������Ƿ���ȷ
	public long userQuery(String userName, String password) {
		sql = "select userName from user where userName = ? and setPassword = ?"; // �洢sql���
		try {
			pstmt = con.prepareStatement(sql); // Ԥ����sql���
			pstmt.setString(1, userName);
			pstmt.setString(2, MD5.SetMD5Code(password));
			ResultSet rs = pstmt.executeQuery(); // ����ѯ�õ�
			if (rs.next()) {// �˶���Ϣ�ɹ�
				return rs.getLong(1);// �� Java ��������� int ����ʽ��ȡ�� ResultSet
										// ����ĵ�ǰ���е�1��ֵ��
			}
			return 0;
		} catch (SQLException e) {
			return -1; // ��ѯ��������-1
		}
	}

	// ע���û���Ϣ
	public void regisite(String str1, String str2, String str3, String str4, String str5, String str6) {
		loadDatabaseDriver();
		connect();
		sql = "insert into user values(0,?,?,?,?,?,?)"; // ����SQL�������
		int ok = 0;
		String s1 = ""; // �ݴ���������
		String s2 = ""; // �ݴ�ȷ������
		try {
			pstmt = con.prepareStatement(sql);// �õ�Ԥ����������
			pstmt.setLong(1, Long.parseLong(str1));// ע���û������˺ţ�
			pstmt.setString(2, str2);// ע����������
			pstmt.setString(3, str3);// ע��ȷ������
			pstmt.setString(4, str4);// ע������
			pstmt.setString(5, str5);// ע���Ա�
			pstmt.setString(6, str6);// ע���������
			s1 += str2;
			s2 += str3;
			if (s1.equals(s2)) {
				ok = pstmt.executeUpdate();// �������ݿ�
			}
			con.close();// �ر����ݿ�
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "ѧ���ѱ�ע��", "����", JOptionPane.WARNING_MESSAGE);
		}
		if (ok != 0) {
			JOptionPane.showMessageDialog(null, "ע��ɹ�", "��ϲ", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ��趨���벻һ��", "����", JOptionPane.WARNING_MESSAGE);
		}
	}

	// ¼��ͼ����Ϣ
	public int insert(Vector<String> bookInfo) {
		sql = "insert into books values(0,?,?,?,?,?,?,?,?,0)"; // �洢sql���
		try {
			pstmt = con.prepareStatement(sql); // Ԥ����sql���
			for (int i = 1; i <= bookInfo.size(); i++) {// ѭ�������õ�¼������
				if (i == 8) { // ¼���鱾����Ϊ������Ҫת��Ϊ����
					pstmt.setInt(i, Integer.parseInt(bookInfo.elementAt(i - 1).trim()));// elmentAt����ָ���������������
				} else { // ¼������Ϊ�ַ���
					pstmt.setString(i, bookInfo.elementAt(i - 1).trim());
				}
			}
			pstmt.executeUpdate(); // �������ݿ�
			return 0; // ¼��ɹ�
		} catch (SQLException e) {
			return -1; // ¼��ʧ��
		}

	}

	// ��ȡ��������Ϣ
	public Vector<String> publisherQuery() {
		Vector<String> publisherInfo = new Vector<String>();
		sql = "select distinct publisher from books";
		try {
			pstmt = con.prepareStatement(sql);
			publisherInfo.add("");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				publisherInfo.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return publisherInfo;
	}

	// ��ȡ������ͼ����Ϣ
	public Vector<String> selectbooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		sql = "select * from books";
		int flag = 0;
		if (name.equals("")) {
			if (author.equals("")) {
				if (!publisher.equals("")) {
					sql += " where publisher like ?";
					flag = 1;
				}
			} else {
				if (publisher.equals("")) {
					sql += " where author like ?";
					flag = 2;
				} else {
					sql += " where author like ? and publisher like ?";
					flag = 3;
				}
			}
		} else {
			if (author.equals("") && publisher.equals("")) {
				sql += " where name like ?";
				flag = 4;
			}
			if (author.equals("") && !publisher.equals("")) {
				sql += " where name like ? and publisher like ?";
				flag = 5;
			}
			if (!author.equals("") && publisher.equals("")) {
				sql += " where name like ? and author like ?";
				flag = 6;
			}
			if (!author.equals("") && !publisher.equals("")) {
				sql += " where name like ? and author like ? and publisher like ?";
				flag = 7;
			}
		}
		if (radio.equals("ģ����ѯ")) {
			name = "%" + name + "%";
			author = "%" + author + "%";
			publisher = "%" + publisher + "%";
		}
		try {
			pstmt = con.prepareStatement(sql);
			switch (flag) {
			case 0:
				break;
			case 1:
				pstmt.setString(1, publisher);
				break;
			case 2:
				pstmt.setString(1, author);
				break;
			case 3:
				pstmt.setString(1, author);
				pstmt.setString(2, publisher);
				break;
			case 4:
				pstmt.setString(1, name);
				break;
			case 5:
				pstmt.setString(1, name);
				pstmt.setString(2, publisher);
				break;
			case 6:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				break;
			case 7:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			String str = "";
			while (rs.next()) {
				for (int i = 1; i <= 10/* rs.getMetaData().getColumnCount() */; i++) {
					if (i == 1 || i == 9 || i == 10) {
						if (i == 1) {
							str += "���" + rs.getInt(i) + ": ";
						}
						if (i == 9) {
							str += "�����������: " + rs.getInt(i) + "; ";
						}
						if (i == 10) {
							str += "�ѱ����ߵ�����: " + rs.getInt(i) + ";";
						}
					} else {
						if (i == 2) {
							str += "���: ";
						}
						if (i == 3) {
							str += "����: ";
						}
						if (i == 4) {
							str += "����: ";
						}
						if (i == 5) {
							str += "������: ";
						}
						if (i == 6) {
							str += "�۸�: ";
						}
						if (i == 7) {
							str += "��������: ";
						}
						if (i == 8) {
							str += "λ��: ";
						}
						str += rs.getString(i).trim() + "; ";
					}
				}
				str = str.substring(0, str.length());
				result.add(str);
				str = "";
			}
		} catch (SQLException e) {
			System.out.println("��ѯ���ݿ����");
			System.out.println(e);
		}

		return result;
	}
	
	// ɾ��ͼ��
	public int deleteId(int id) {
		sql = "delete from books where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ɾ�����ݳ���");
			System.out.println(e);
			return -1;
		}
		return 0;
	}

	// �ر����ݿ�����
	public void disconnect() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// ����ͼ����Ϣ
	public int update(Vector<String> bookInfo1, String id) {
		int Id = Integer.parseInt(id);
		//System.out.println(Id);
		sql = "update books set isbn = ?, name = ?, author = ?, publisher = ?, price = ?, pubDate = ?, deposit = ?, quantify = ? where id = ?"; // �洢sql���
		try {
			pstmt = con.prepareStatement(sql); // Ԥ����sql���
			for (int i = 1; i <= bookInfo1.size(); i++) {// ѭ�������õ�¼������
				//System.out.println(bookInfo1.elementAt(i - 1).trim());
				if (i == 8) { // ¼���鱾����Ϊ������Ҫת��Ϊ����
					pstmt.setInt(i, Integer.parseInt(bookInfo1.elementAt(i - 1).trim()));// elmentAt����ָ���������������
				} else { // ¼������Ϊ�ַ���
					pstmt.setString(i, bookInfo1.elementAt(i - 1).trim());
				}
			}
			pstmt.setInt(bookInfo1.size() + 1, Id);
			pstmt.executeUpdate(); // �������ݿ�
			return 0; // ¼��ɹ�
		} catch (SQLException e) {
			System.out.println(e);
			return -1; // ¼��ʧ��
		}

	}

	// ��ʼ����
	public int lend(int id, long userId, int lendQuantify) {
		String name = "";
		sql = "select name from user where userName = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			name = rs.getString(1);
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}

		try {
			con.setAutoCommit(false);// �ر����ݿ������Զ�����ģʽ
			if (updateStock(id, lendQuantify) == -1) {
				return -1;
			}
			if (insertLendRecord(id, userId, name) == -1) {
				return -1;
			}
			con.commit();
			con.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			rollback();
			return -1;
		}
	}

	// �ع�,��������
	public void rollback() {
		if (con == null) {
			return;
		}
		System.err.println("�����쳣�����ڳ�������");
		try {
			con.rollback();// ��������
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// �޸Ľ衢��ͼ��ʱ�Ľ�������
	public int updateStock(int id, int lendQuantify) {
		sql = "update books set lend = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, lendQuantify);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			System.err.println("�޸Ŀ���¼����");
			System.out.println(e);
			rollback();
			return -1;
		}
	}

	// �ڽ����¼������ӽ��������
	public int insertLendRecord(int id, long userId, String name) {
		sql = "insert into lendrecord values(0,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setLong(2, userId);
			pstmt.setString(3, name);
			java.util.Date d = new java.util.Date();
			pstmt.setString(4, d.toString());
			pstmt.setString(5, "����");
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			System.err.println("�洢�����¼����!");
			System.out.println(e);
			rollback();
			return -1;
		}
	}

	// �鿴�����¼
	public Vector<String> selectrecordBooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		sql = "select lendRecord.id,books.isbn,books.name,books.author,books.publisher,books.pubDate,lendRecord.userId,"
				+ "lendRecord.borrowName,lendRecord.borrowDate,lendRecord.state from lendRecord inner join books on "
				+ "lendRecord.bookId = books.id inner join user on lendRecord.userId = user.userName";
		int flag = 0;
		if (name.equals("")) {
			if (author.equals("")) {
				if (!publisher.equals("")) {
					sql += " where publisher like ?";
					flag = 1;
				}
			} else {
				if (publisher.equals("")) {
					sql += " where author like ?";
					flag = 2;
				} else {
					sql += " where author like ? and publisher like ?";
					flag = 3;
				}
			}
		} else {
			if (author.equals("") && publisher.equals("")) {
				sql += " where books.name like ?";
				flag = 4;
			}
			if (author.equals("") && !publisher.equals("")) {
				sql += " where books.name like ? and publisher like ?";
				flag = 5;
			}
			if (!author.equals("") && publisher.equals("")) {
				sql += " where books.name like ? and author like ?";
				flag = 6;
			}
			if (!author.equals("") && !publisher.equals("")) {
				sql += " where books.name like ? and author like ? and publisher like ?";
				flag = 7;
			}
		}
		if (radio.equals("ģ����ѯ")) {
			name = "%" + name + "%";
			author = "%" + author + "%";
			publisher = "%" + publisher + "%";
		}
		try {
			pstmt = con.prepareStatement(sql);
			switch (flag) {
			case 0:
				break;
			case 1:
				pstmt.setString(1, publisher);
				break;
			case 2:
				pstmt.setString(1, author);
				break;
			case 3:
				pstmt.setString(1, author);
				pstmt.setString(2, publisher);
				break;
			case 4:
				pstmt.setString(1, name);
				break;
			case 5:
				pstmt.setString(1, name);
				pstmt.setString(2, publisher);
				break;
			case 6:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				break;
			case 7:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			String str = "";
			while (rs.next()) {
				for (int i = 1; i <= 10/* rs.getMetaData().getColumnCount() */; i++) {
					if (i == 1) {
						str += "��¼���" + rs.getInt(i) + ": ";
					} else if (i == 7) {
						str += "ѧ��: " + rs.getLong(i) + "; ";
					} else {
						if (i == 2) {
							str += "���: ";
						}
						if (i == 3) {
							str += "����: ";
						}
						if (i == 4) {
							str += "����: ";
						}
						if (i == 5) {
							str += "������: ";
						}
						if (i == 6) {
							str += "��������: ";
						}
						if (i == 8) {
							str += "�û�����: ";
						}
						if (i == 9) {
							str += "��������: ";
						}
						if (i == 10) {
							str += "����״̬: ";
						}
						str += rs.getString(i).trim() + "; ";
					}
				}
				str = str.substring(0, str.length());
				result.add(str);
				str = "";
			}
		} catch (SQLException e) {
			System.out.println("��ѯ���ݿ����");
			System.out.println(e);
		}

		return result;
	}
	// �鿴�����¼
	public Vector<String> selectreturnBooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		sql = "select lendRecord.id,books.isbn,books.name,books.author,books.publisher,books.pubDate,lendRecord.userId,"
				+ "lendRecord.borrowName,lendRecord.borrowDate,lendRecord.state from lendRecord inner join books on "
				+ "lendRecord.bookId = books.id inner join user on lendRecord.userId = user.userName where state = ?";
		int flag = 0;
		if (name.equals("")) {
			if (author.equals("")) {
				if (!publisher.equals("")) {
					sql += " and publisher like ?";
					flag = 1;
				}
			} else {
				if (publisher.equals("")) {
					sql += " and author like ?";
					flag = 2;
				} else {
					sql += " and author like ? and publisher like ?";
					flag = 3;
				}
			}
		} else {
			if (author.equals("") && publisher.equals("")) {
				sql += " and books.name like ?";
				flag = 4;
			}
			if (author.equals("") && !publisher.equals("")) {
				sql += " and books.name like ? and publisher like ?";
				flag = 5;
			}
			if (!author.equals("") && publisher.equals("")) {
				sql += " and books.name like ? and author like ?";
				flag = 6;
			}
			if (!author.equals("") && !publisher.equals("")) {
				sql += " and books.name like ? and author like ? and publisher like ?";
				flag = 7;
			}
		}
		if (radio.equals("ģ����ѯ")) {
			name = "%" + name + "%";
			author = "%" + author + "%";
			publisher = "%" + publisher + "%";
		}
		try {
			pstmt = con.prepareStatement(sql);
			switch (flag) {
			case 0:
				pstmt.setString(1, "�ѻ�");
				break;
			case 1:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, publisher);
				break;
			case 2:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, author);
				break;
			case 3:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				break;
			case 4:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, name);
				break;
			case 5:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, name);
				pstmt.setString(3, publisher);
				break;
			case 6:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, name);
				pstmt.setString(3, author);
				break;
			case 7:
				pstmt.setString(1, "�ѻ�");
				pstmt.setString(2, name);
				pstmt.setString(3, author);
				pstmt.setString(4, publisher);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			String str = "";
			while (rs.next()) {
				for (int i = 1; i <= 10/* rs.getMetaData().getColumnCount() */; i++) {
					if (i == 1) {
						str += "��¼���" + rs.getInt(i) + ": ";
					} else if (i == 7) {
						str += "ѧ��: " + rs.getLong(i) + "; ";
					} else {
						if (i == 2) {
							str += "���: ";
						}
						if (i == 3) {
							str += "����: ";
						}
						if (i == 4) {
							str += "����: ";
						}
						if (i == 5) {
							str += "������: ";
						}
						if (i == 6) {
							str += "��������: ";
						}
						if (i == 8) {
							str += "�û�����: ";
						}
						if (i == 9) {
							str += "��������: ";
						}
						if (i == 10) {
							str += "����״̬: ";
						}
						str += rs.getString(i).trim() + "; ";
					}
				}
				str = str.substring(0, str.length());
				result.add(str);
				str = "";
			}
		} catch (SQLException e) {
			System.out.println("��ѯ���ݿ����");
			System.out.println(e);
		}

		return result;
	}
	// �鿴δ������Ϣ
	public Vector<String> selectreturn1Books(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		sql = "select lendRecord.id,books.isbn,books.name,books.author,books.publisher,books.pubDate,lendRecord.userId,"
				+ "lendRecord.borrowName,lendRecord.borrowDate,lendRecord.state from lendRecord inner join books on "
				+ "lendRecord.bookId = books.id inner join user on lendRecord.userId = user.userName where state = ?";
		int flag = 0;
		if (name.equals("")) {
			if (author.equals("")) {
				if (!publisher.equals("")) {
					sql += " and publisher like ?";
					flag = 1;
				}
			} else {
				if (publisher.equals("")) {
					sql += " and author like ?";
					flag = 2;
				} else {
					sql += " and author like ? and publisher like ?";
					flag = 3;
				}
			}
		} else {
			if (author.equals("") && publisher.equals("")) {
				sql += " and books.name like ?";
				flag = 4;
			}
			if (author.equals("") && !publisher.equals("")) {
				sql += " and books.name like ? and publisher like ?";
				flag = 5;
			}
			if (!author.equals("") && publisher.equals("")) {
				sql += " and books.name like ? and author like ?";
				flag = 6;
			}
			if (!author.equals("") && !publisher.equals("")) {
				sql += " and books.name like ? and author like ? and publisher like ?";
				flag = 7;
			}
		}
		if (radio.equals("ģ����ѯ")) {
			name = "%" + name + "%";
			author = "%" + author + "%";
			publisher = "%" + publisher + "%";
		}
		try {
			pstmt = con.prepareStatement(sql);
			switch (flag) {
			case 0:
				pstmt.setString(1, "����");
				break;
			case 1:
				pstmt.setString(1, "����");
				pstmt.setString(2, publisher);
				break;
			case 2:
				pstmt.setString(1, "����");
				pstmt.setString(2, author);
				break;
			case 3:
				pstmt.setString(1, "����");
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				break;
			case 4:
				pstmt.setString(1, "����");
				pstmt.setString(2, name);
				break;
			case 5:
				pstmt.setString(1, "����");
				pstmt.setString(2, name);
				pstmt.setString(3, publisher);
				break;
			case 6:
				pstmt.setString(1, "����");
				pstmt.setString(2, name);
				pstmt.setString(3, author);
				break;
			case 7:
				pstmt.setString(1, "����");
				pstmt.setString(2, name);
				pstmt.setString(3, author);
				pstmt.setString(4, publisher);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			String str = "";
			while (rs.next()) {
				for (int i = 1; i <= 10/* rs.getMetaData().getColumnCount() */; i++) {
					if (i == 1) {
						str += "��¼���" + rs.getInt(i) + ": ";
					} else if (i == 7) {
						str += "ѧ��: " + rs.getLong(i) + "; ";
					} else {
						if (i == 2) {
							str += "���: ";
						}
						if (i == 3) {
							str += "����: ";
						}
						if (i == 4) {
							str += "����: ";
						}
						if (i == 5) {
							str += "������: ";
						}
						if (i == 6) {
							str += "��������: ";
						}
						if (i == 8) {
							str += "�û�����: ";
						}
						if (i == 9) {
							str += "��������: ";
						}
						if (i == 10) {
							str += "����״̬: ";
						}
						str += rs.getString(i).trim() + "; ";
					}
				}
				str = str.substring(0, str.length());
				result.add(str);
				str = "";
			}
		} catch (SQLException e) {
			System.out.println("��ѯ���ݿ����");
			System.out.println(e);
		}

		return result;
	}
	// ����ʱ���ݽ����¼��ʶ��ѯ��ͼ���ʶ
	public int bookIdQuery(int lendRecordId) {
		sql = "select bookId from lendRecord where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, lendRecordId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			return -1;
		}
		return 0;
	}

	// ����ʱ������ͼ���ʶ��ѯ��ͼ����Ϣ
	public String queryBookInfo(int bookId) {
		sql = "select * from books where id = ?";
		String str[] = new String[10];
		String bookInfo = "";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// �����ѯ������
				for (int i = 0; i < 10; i++) {
					if (i == 0 || i == 8 || i == 9) {
						str[i] = String.valueOf(rs.getInt(i + 1));
					} else {
						str[i] = rs.getString(i + 1);
					}
				}
			}
		} catch (SQLException e) {
			return null;
		}
		bookInfo = "���: " + str[1] + "\n" + "����: " + str[2] + "\n" + "����: " + str[3] + "\n" + "������: " + str[4] + "\n"
				+ "�۸�: " + str[5] + "\n" + "����ʱ��: " + str[6] + "\n" + "���λ��: " + str[7] + "\n" + "�����������: " + str[8]
				+ "\n" + "�������: " + str[9] + "\n";
		return bookInfo;
	}

	// ����Ĳ���
	public int returnBook(int lendRecordId, int bookId, long userId, int lendQuantify) {
		try {
			con.setAutoCommit(false);// �ر����ݿ������Զ�����ģʽ
			if (updateStateOfLendRecord(lendRecordId) != 0) {
				return -1;
			}
			if (updateStock(bookId, lendQuantify) == -1) {
				return -1;
			}
			if (insertReturnRecord(bookId, userId) == -1) {
				return -1;
			}
			con.commit();
			con.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			rollback();
			return -1;
		}
	}

	// ����ʱ�޸Ľ����¼��״ֵ̬
	public int updateStateOfLendRecord(int lendRecordId) {
		sql = "update lendRecord set state = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "�ѻ�");
			pstmt.setInt(2, lendRecordId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			rollback();
			return -1;
		}
		return 0;
	}

	// �ڻ��������ӻ��������
	public int insertReturnRecord(int bookId, long userId) {
		String name1 = "";
		sql = "select userName from user where userName = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				name1 = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			rollback();
			return -1;
		}
		sql = "insert into returnrecord values(0,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.setLong(2, userId);
			pstmt.setString(3, name1);
			java.util.Date d = new java.util.Date();
			pstmt.setString(4, d.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			rollback();
			return -1;
		}
		return 0;
	}
}
