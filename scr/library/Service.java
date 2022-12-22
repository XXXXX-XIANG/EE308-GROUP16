package library;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;

public class Service {
	// �������ݿ��������
	private static DataOperator dataOperator = new DataOperator();

	// ���û�Ҫ��¼��һϵ�в����������û�����ѧ�ţ�����:userName:�û�����ѧ�ţ���password:����
	public static long login(String userName, String password) {
		dataOperator.loadDatabaseDriver(); // �������ݿ��JDBC��������
		dataOperator.connect(); // �������ݿ�
		// dataOperator.addSuperUser(); //���admin�û�(���users��Ϊ��)
		return dataOperator.userQuery(userName, password); // �˶��û����������Ƿ���ȷ
	}

	// �ر����ݿ�����
	public static void quit() {
		dataOperator.disconnect();
	}

	// �رմ��ڣ��ͷų��� ������jframe:����
	public static void windowClose(JFrame jframe) {
		// ��ӹرմ����¼�
		jframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Service.quit(); // ����ҵ��������ر����ݿ�����
				System.exit(0); // �رմ���
			}
		});
	}

	// ע���û���Ϣ ������str1: �û�����ѧ�ţ���str2:��������,str3:ȷ�����룬str4:������str5:�Ա�str6:��������
	public static void regisiteUser(String str1, String str2, String str3, String str4, String str5, String str6) {
		dataOperator.regisite(str1, str2, str3, str4, str5, str6);
	}

	// ���ͼ�飬����0���ɹ���ӣ��������ʧ�� ������bookInfo:ͼ����Ϣ
	public static int addBook(Vector<String> bookInfo) {
		if (dataOperator.insert(bookInfo) == 0) { // ¼��ͼ��ɹ�
			return 0;
		} else { // ¼��ͼ��ʧ��
			return -1;
		}
	}

	// ��ȡ��������Ϣ������Vector�ַ�������
	public static Vector<String> getPublisher() {
		return dataOperator.publisherQuery();
	}

	// ����ͼ����Ϣ������Vector�ַ�������Ϊ��ʾͼ����Ϣ
	// ������name:������author:���ߣ�publisher:�����磬radio����ѯ��ʽ����ȫһ�£�ģ����ѯ
	public static Vector<String> selectBooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		result = dataOperator.selectbooks(name, author, publisher, radio);
		return result;
	}

	// ɾ��ͼ����Ϣ��ͨ��ͼ��˳���ʶ��ɾ�� ������id:ͼ��˳���
	public static int deleteBooks(int id) {
		return dataOperator.deleteId(id);
	}

	// �޸�ͼ�� ����0��ʾ�޸ĳɹ��������޸�ʧ�� ������bookInfo1:ͼ����Ϣ��id:ͼ��˳��ţ��ַ����ͣ�
	public static int updateBook(Vector<String> bookInfo1, String id) {
		if (dataOperator.update(bookInfo1, id) == 0) { // ¼��ͼ��ɹ�
			return 0;
		} else { // ¼��ͼ��ʧ��
			return -1;
		}
	}

	// ����ͼ�� ����ֵ��ʾ�Ƿ���ĳɹ�������id:ͼ��˳��ţ�uesrId:�û�����ѧ�ţ���lendQuantify:������
	public static int lendBook(int id, long userId, int lendQuantify) {
		return dataOperator.lend(id, userId, lendQuantify);
	}

	// �鿴����ͼ���¼ ������name:������author:���ߣ�publisher:�����磬radio����ѯ��ʽ����ȫһ�£�ģ����ѯ
	public static Vector<String> selectRecordBooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		result = dataOperator.selectrecordBooks(name, author, publisher, radio);
		return result;
	}
	// �鿴�����¼ ������name:������author:���ߣ�publisher:�����磬radio����ѯ��ʽ����ȫһ�£�ģ����ѯ
	public static Vector<String> selectReturnBooks(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		result = dataOperator.selectreturnBooks(name, author, publisher, radio);
		return result;
	}
	// �鿴δ�����¼ ������name:������author:���ߣ�publisher:�����磬radio����ѯ��ʽ����ȫһ�£�ģ����ѯ
	public static Vector<String> selectReturn1Books(String name, String author, String publisher, String radio) {
		Vector<String> result = new Vector<String>();
		result = dataOperator.selectreturn1Books(name, author, publisher, radio);
		return result;
	}

	// ��ѯ��������Ӧ��ͼ��˳��ı�ʶ��    ������lendRecordId:�����¼��ʶ��
	public static int BookIdQuery(int lendRecordId) {
		return dataOperator.bookIdQuery(lendRecordId);
	}

	// ����ͼ���ʶ��ѯ��ͼ����Ϣ    ������bookId:ͼ��˳���ʶ��
	public static String QueryBookInfo(int bookId) {
		return dataOperator.queryBookInfo(bookId);
	}

	// ������� ������lendRecordId:�����¼��ʶ�ţ�bookId:ͼ��˳���ʶ�ţ�userId:�û�����ѧ�ţ�,lnndQuantify:�������
	public static int ReturnBook(int lendRecordId, int bookId, long userId, int lendQuantify) {
		return dataOperator.returnBook(lendRecordId, bookId, userId, lendQuantify);
	}
}
