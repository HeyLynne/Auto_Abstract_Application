package fileOperation;

public class Sentence {

	private String sentence = ""; // ԭʼ����
	private String sentencePosed = ""; // �����Ա�ǵľ���
	private int appearPosition = 0; // ����������λ��
	private int length = 0; // ���ӳ���
	private double positionWeigh = 0; // ����λ��Ȩ��
	private double totalWeigh = 0; // ������Ȩ��
	
	public Sentence(String aSentence, String aSentencePosed, int aAppearPosition) {
		sentence = aSentence;
		sentencePosed = aSentencePosed;
		appearPosition = aAppearPosition;
	}
	
	public String getSentence() {
		return sentence;
	}
	public String getSentencePosed() {
		return sentencePosed;
	}
	public int getAppearPosition() {
		return appearPosition;
	}
	public int getLength() {
		return length;
	}
	public double getPositionWeigh() {
		return positionWeigh;
	}
	public double getTotalWeigh() {
		return totalWeigh;
	}
	
	public void setSentence(String aSentence) {
		sentence = aSentence;
	}
	public void setSentencePosed(String aSentencePosed) {
		sentencePosed = aSentencePosed;
	}
	public void setAppearPosition(int aAppearPositon) {
		appearPosition = aAppearPositon;
	}
	public void setLength(int aLength) {
		length = aLength;
	}
	public void setPositionWeigh(double aPositionWeigh) {
		positionWeigh = aPositionWeigh;
	}
	public void setTotalWeigh(double aTotalWeigh) {
		totalWeigh = aTotalWeigh;
	}
}
