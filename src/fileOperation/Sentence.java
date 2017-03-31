package fileOperation;

public class Sentence {

	private String sentence = ""; // 原始句子
	private String sentencePosed = ""; // 带词性标记的句子
	private int appearPosition = 0; // 句子在文中位置
	private int length = 0; // 句子长度
	private double positionWeigh = 0; // 句子位置权重
	private double totalWeigh = 0; // 句子总权重
	
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
