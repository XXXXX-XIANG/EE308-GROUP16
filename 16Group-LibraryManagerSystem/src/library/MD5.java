package library;

public class MD5 {
	//�򵥼���
	public static String GetMD5Code(String password) {
		String sourceStr = "����ع������������������";
		char[] s = sourceStr.toCharArray();
		char[] p = password.toCharArray();
		int len1 = s.length;
		int len2 = p.length;
		for (int i = 0; i < len2; i++) {
			int t = p[i] + s[i % len1];
			p[i] = (char) t;//��ԭ������м���
		}
		return String.valueOf(p);
	}
	//�򵥽���
	public static String SetMD5Code(String password) {
		String sourceStr = "����ع������������������";
		char[] s = sourceStr.toCharArray();
		char[] p = password.toCharArray();
		int len1 = s.length;
		int len2 = p.length;
		for (int i = 0; i < len2; i++) {
			int t = p[i] - s[i % len1];
			p[i] = (char) t;//��Ϊԭ����
		}
		return String.valueOf(p);
	}
}
