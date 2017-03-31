package fileOperation;

public class Word {

	private String word;// = ""; // ����
	private String pos;// = ""; // ����
	private int frequency;// = 0; // ��Ƶ
	
	public Word(String aWord, String aPos, int aFrequency) {
		word = aWord;
		pos = aPos;
		frequency = aFrequency;
	}
	
	public String getWord() {
		return word;
	}
	public String getPos() {
		return pos;
	}
	public int getFrequency() {
		return frequency;
	}
	
	public void setWord(String aWord) {
		word = aWord;
	}
	public void setPos(String aPos) {
		pos = aPos;
	}
	public void setFrequency(int aFrequency) {
		frequency = aFrequency;
	}
}
