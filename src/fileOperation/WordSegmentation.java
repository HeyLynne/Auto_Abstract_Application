package fileOperation;

import kevin.zhang.NLPIR;

public class WordSegmentation {

	private static NLPIR testNLPIR = new NLPIR();
	private static String encoding = "UTF-8"; // 编码格式
	private static int encode = 1; // 初始化格式，GB2312=0，UTF-8=1
	
	public static void NLPIR_Initial() { // 初始化分词组件
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
	
	public static void NLPIR_Exit() { // 退出分词组件
		try {
			NLPIR.NLPIR_Exit();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String StringSegm_PosTagged(String srcStr) { // 带词性标记的字符串分词
		
		String nativeStr = "";
		
		try {
			byte[] nativeBytes = testNLPIR.NLPIR_ParagraphProcess(srcStr.getBytes(encoding), 1);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, encoding);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return nativeStr;
	}
	
	public void FileSegm_PosTagged(String srcFile, String destFile) { // 带词性标记的文本分词
		try {
			testNLPIR.NLPIR_FileProcess(srcFile.getBytes(encoding), destFile.getBytes(encoding), 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String StringSegm_NotPosTagged(String srcStr) { // 不带词性标记的字符串分词
		
		String nativeStr = "";
		
		try {
			byte[] nativeBytes = testNLPIR.NLPIR_ParagraphProcess(srcStr.getBytes(encoding), 0);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, encoding);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return nativeStr;
	}
	
	public void FileSegm_NotPosTagged(String srcFile, String destFile) { // 不带词性标记的文本分词
		try {
			testNLPIR.NLPIR_FileProcess(srcFile.getBytes(encoding), destFile.getBytes(encoding), 0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
