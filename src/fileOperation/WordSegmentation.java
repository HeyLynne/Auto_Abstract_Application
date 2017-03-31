package fileOperation;

import kevin.zhang.NLPIR;

public class WordSegmentation {

	private static NLPIR testNLPIR = new NLPIR();
	private static String encoding = "UTF-8"; // �����ʽ
	private static int encode = 1; // ��ʼ����ʽ��GB2312=0��UTF-8=1
	
	public static void NLPIR_Initial() { // ��ʼ���ִ����
		try {
			String argu = "./file/";
//			System.out.println("NLPIR_Init");
			if(NLPIR.NLPIR_Init(argu.getBytes(), encode) == false) {
				System.out.println("Init Failed!");
				return;
			}
//			System.out.println("Init Successful!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void NLPIR_Exit() { // �˳��ִ����
		try {
			NLPIR.NLPIR_Exit();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String StringSegm_PosTagged(String srcStr) { // �����Ա�ǵ��ַ����ִ�
		
		String nativeStr = "";
		
		try {
			byte[] nativeBytes = testNLPIR.NLPIR_ParagraphProcess(srcStr.getBytes(encoding), 1);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, encoding);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return nativeStr;
	}
	
	public void FileSegm_PosTagged(String srcFile, String destFile) { // �����Ա�ǵ��ı��ִ�
		try {
			testNLPIR.NLPIR_FileProcess(srcFile.getBytes(encoding), destFile.getBytes(encoding), 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String StringSegm_NotPosTagged(String srcStr) { // �������Ա�ǵ��ַ����ִ�
		
		String nativeStr = "";
		
		try {
			byte[] nativeBytes = testNLPIR.NLPIR_ParagraphProcess(srcStr.getBytes(encoding), 0);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, encoding);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return nativeStr;
	}
	
	public void FileSegm_NotPosTagged(String srcFile, String destFile) { // �������Ա�ǵ��ı��ִ�
		try {
			testNLPIR.NLPIR_FileProcess(srcFile.getBytes(encoding), destFile.getBytes(encoding), 0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
