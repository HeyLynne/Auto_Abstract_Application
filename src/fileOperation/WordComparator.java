package fileOperation;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

	public int compare(Word wd1, Word wd2) {
		return (int)(wd2.getFrequency() - wd1.getFrequency());
	}
}
