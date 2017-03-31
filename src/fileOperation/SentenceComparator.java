package fileOperation;

import java.util.Comparator;

public class SentenceComparator implements Comparator<Sentence> {

	public int compare(Sentence st1, Sentence st2) {
		return (int)(st2.getTotalWeigh() - st1.getTotalWeigh());
	}
}
