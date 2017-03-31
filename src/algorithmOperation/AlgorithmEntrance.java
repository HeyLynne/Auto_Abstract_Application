package algorithmOperation;

import fileOperation.*;
import java.io.*;
import java.util.*;

public class AlgorithmEntrance {
	
	public static AbstractInfo info = new AbstractInfo();

	private static ArrayList<Word> word;// = new ArrayList<Word>();
	private static LinkedList<Sentence> sentence;// = new LinkedList<Sentence>();
	private static int sentenceNum;// = 0;
	private static String current_sentence;// = "";
	private static String current_sentencePosed;// = "";
	
	private static String encoding = "UTF-8"; // �����ʽ
	
	private static String abstract_result1 = "";
	private static int total_length = 0;

//	public static void main(String[] args) throws Exception {
//		start(); 
//	}
	
	public static void get_abstract() throws Exception {
		word = new ArrayList<Word>();
		sentence = new LinkedList<Sentence>();
		sentenceNum = 0;
		current_sentence = "";
		current_sentencePosed = "";
		abstract_result1 = "";
		total_length = 0;
		String abstract_result = "";

		WordSegmentation wordSegm = new WordSegmentation();
		WordSegmentation.NLPIR_Initial();
		
		String s_src = wordSegm.StringSegm_PosTagged(info.getSource());
		wordPreTreat(s_src);
		sentence_weigh();
		abstract_result = abstract_out();
		
		for(Word wd : word) {
			info.setKeyword(info.getKeyword() + wd.getWord() + "\n");
			if(wd.getPos().equals("nr")) {
				info.setPeople(info.getPeople() + wd.getWord() + "\n");
			}
			if(wd.getPos().equals("ns")) {
				info.setLocation(info.getLocation() + wd.getWord() + "\n");
			}
		}
		
		WordSegmentation.NLPIR_Exit();
		info.setDigest(abstract_result);
		info.setDigest1(abstract_result1);
	}
	            
	public static String get_abstract(String src) throws Exception {
		
		word = new ArrayList<Word>();
		sentence = new LinkedList<Sentence>();
		sentenceNum = 0;
		current_sentence = "";
		current_sentencePosed = "";
		
		boolean mode = false; // trueΪ�ı�ժҪ��falseΪ����ժҪ
		sentenceNum = 0;
		
		String abstract_result = "";

		WordSegmentation wordSegm = new WordSegmentation();
		
		WordSegmentation.NLPIR_Initial();
		
		if(mode) {
		
			String fileIn = "";
			String fileOut = "";
			
			wordSegm.FileSegm_PosTagged(fileIn, fileOut);
			
			file_wordFrequency(fileOut);
		}
		
		else {
//			String src = "\n\n				\n\n				\n\n        \n            \n\n                  \n                \n				\n\n�������߱����Ƚ�����  �����ʱ�����϶�\n\n \n\n�������ҹ�Ӧ��������Ƚϳ�ԣ(�г��۲�)\n\n������������  ����\n\n�����й������������·����ı�����ʾ��9��ĩ���ڻ�������Ҹ���������70.28����Ԫ��ͬ������14.3%�����ٱ��ϼ�ĩ��0.1���ٷֵ㡣ר��ָ�������ݱ�����ǰ����Ŵ����������������ʽ�����Ƚ���ʢ����ǰ��ʱ�ڲ�ʱ������Ǯ��������Ϣ����ô�����ڻ��ҹ�Ӧ�ܷ������г����ʽ������\n\n�����羳�ʽ�����϶�\n\n������ǰ�����ȿ����г��ϻ��ҹ�Ӧһֱ�Ƚϳ�ԣ���ݹ���ͳ�ƾ����ŷ�����ʢ���˽��ܣ�ǰ�����Ȼ��ҹ�Ӧ�����Ͽ죬����9��ĩ���ҹ��������(M2)���107.74����Ԫ��ͬ������14.2%���������(M1)���31.23����Ԫ������8.9%����ͨ�л���(M0)���5.65����Ԫ������5.7%��\n\n�������⣬�����ռ�����ǰ�羳�ʽ�������Ҳ�ھ��������й��������з��������ݣ�9��ĩ���ڻ������ռ���������27.52����Ԫ����8�µ�Ϊ27.39����Ԫ��9�����ռ������1263.62��Ԫ�����½��������¸ߡ�\n\n�����й�������������������ã�����һ�����ֵ��������ó�ױ�����˳��ټ�������һ���ӳ��˳�QE����Щ�����ۺ����������˹����ʽ������й���\n\n�����ļ��������Ի�ƫ��\n\n������ʵ���й��г��ϵĻ��ҹ�Ӧһֱ�Ƚϳ�ԣ�������ϴ�ѧ������ѧԺ��Ժ���ﻪ楽��ܣ��й��������߶����Ƚ�Ϊ�����ڴ˻����³���ƫ�ɡ���������������ƶ���ȫ��M2����Ԥ��Ŀ����13%������ʵ�ϣ�ÿ���µ�M2���ٶ�������Ŀ�ꡣ\n\n�����������ҹ�Ӧ�Ƿ������һ��ʽ�����ҹ��������ʵ���GDP��������ͨ����֮�͡����й����������ǻ��ҹ�Ӧ��������ҹ�Ӧ�����ʴ���GDP��������ͨ����֮�͡����׶�����ó�״�ѧ����ѧԺԺ��л̫���ڽ��ܱ������߲ɷ�ʱ����˵������2012��M2��10�������Ԫ��GDP��15������Ԫ������������һ��Ԫ���ҹ�Ӧ������1.5��Ԫ��GDP�����й�2012��M2��100����Ԫ����ң�GDP��51����Ԫ����ң��൱��1Ԫ���������0.5Ԫ����ҵ�GDP�����пɿ������й����������кܴ������ǿ����Ҷѳ����ġ�\n\n����Ȼ����Ȥ���ǣ����ҹ�Ӧ���ȴ��ʱ������Ǯ������������Դˣ��ﻪ���Ϊ�����ȥ������M2���ٳ���17%�����ڽ�����15%���ң����ȥ��ȣ���Ҹо�����ƫ����л̫��ָ����һ����Ҫԭ�����������м��г���������ʵ�徭�ã��ѳ�Ϊ׬Ǯ��������\n\n��������ǰ�����ȿ����ļ��Ȼ��ҹ�Ӧ�����ǻᳬ��GDP��������ͨ����֮�͡�һ����׻��ҹ�Ӧ�������ӣ�����λҪͻ����Ǯ�����Ƶ��ļ��Ȼ��ҹ�Ӧ�����ǰ�����ȡ���л̫����Ϊ��δ����Ǯ�������Ҫ������һ��ʵ��������������Ԥ���������ֵ����Ǯ���������࣬��֮������١�������һ����Ƿ���ֵ�����Ǿ������⣬�����������⡣����������Ҳ�Ӧ������ֵ�ˣ�������ŷʩѹ�й�������һ�����������ֵ�Ŀռ䲻��̫���ˡ�\n\n�����������߽�����ƫ��\n\n�������δ���г������Թ��࣬����ձ�Ԥ�ƹ��ҽ�ά���Ƚ�����ƫ���Ļ������ߡ���������[΢��]��������ƣ��й����ý��븴��ģʽ��Ԥ���ļ��ȸ���̬�ƽ��������ͬʱԤ���ļ��Ȼ�������������ά�ֲ��䡣\n\n���������й����б�̬��ʾ������ϵ�����Թ�Ӧ�����Ϊ��ԣ���������������������ʱ�����ָ����ļ������м����ʽ��ᱣ���ȶ��������г���С����ǰҲ��̬���°�������ִ���Ƚ��Ļ������ߡ�\n\n������ר�ҽ��飬Ϊ��֤�������������Ƶ��ߣ��������߿����������ʶȷ��ɡ���л̫������Ϊ�������ڵ�CPI����ͨ���ʲ���ܸߣ��ƺ�����ʵ�п��ɵĻ������ߣ���ʵ�����ҹ���CPIû�а��������ϢϢ��صķ��ۡ��Ӿ��÷�չ����������ҿ��ɾͻ�����Ͷ�ʣ��ߵ���Ȼ��Ͷ���������õ��Ϸ�ʽ��������Ҫת��Ϊ���������������������������Ҫ���Ӱ������룬��������������ġ������������������ϰ��յ����Ѳ��ϣ�������������������½���\n\n\n\n				\n\n			";
			
			String s_src = wordSegm.StringSegm_PosTagged(src);
			
//			System.out.println(src);
//			System.out.println(s_src);
			
			wordPreTreat(s_src);
			sentence_weigh();
			abstract_result = abstract_out();
			
			for(Word wd : word) {
				System.out.println(wd.getWord());
			}
		}
		WordSegmentation.NLPIR_Exit();
		return abstract_result;
	}
	
	public static void wordPreTreat(String s) throws Exception { // ����Ԥ����
		
		byte[] s_byte = s.getBytes(encoding);
		byte[] current_word = new byte[1000];
		byte[] current_pos =  new byte[100];
		byte by;
		
		int j = 0, k = 0;
		boolean tag = false;
		current_word[0] = '\0';
		current_pos[0] = '\0';
		
		try {
		
		for(int i = 0; i < s_byte.length; i++) {
			by = s_byte[i];
//			System.out.print((char)by);
			if((!tag) && (by == ' ' || by == '\n' || by == '\t')) continue;
//			if((!tag) && ( ((by >= 'a') && (by <= 'z')) || ((by >= 'A') && (by <= 'Z'))) ) {
//				while(by != ' ' || by != '\n' || by != '\t') {
//					i++;
//					by = s_byte[i];
//					System.out.print((char)by);
//				}
//			}
			if(tag && (by != ' ')) {
				current_pos[k++] = by;
				current_pos[k] = '\0';
				continue;
			}
			if(tag && (by == ' ')) {
				tag = false;
				add_words(current_word, current_pos);
				j = 0;
				k = 0;
				continue;
			}
			if(!tag && (by != '/')) {
				current_word[j++] = by;
				current_word[j] = '\0';
				continue;
			}
			if(!tag && (by == '/')) tag = true;
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			Collections.sort(word, new WordComparator());
			} catch (Exception e){
				e.printStackTrace();
			}
	}
	
	public static void add_words(byte[] aword, byte[] apos) throws Exception { // ���ʶ��ʴ�Ʒͳ�ƣ����ӷ���
		int w, p;
		for(w = 0; aword[w] != '\0'; w++);
		for(p = 0; apos[p] != '\0'; p++);
		String a_word = new String(aword, 0, w, encoding);
		String a_pos = new String(apos, 0, p, encoding);
//		if(Arrays.equals(a_word.getBytes(encoding), " ".getBytes(encoding))) {
//			return;
//		}
		if(apos[0] == 'n' || apos[0] == 'v') {
			boolean tmp = true;
			for(Word wd : word) {
				if(Arrays.equals(wd.getWord().getBytes(encoding), a_word.getBytes(encoding)) &&
						Arrays.equals(wd.getPos().getBytes(encoding), a_pos.getBytes(encoding))) {
					wd.setFrequency(wd.getFrequency() + 1);
					tmp = false;
					break;
				}
			}
			if(tmp) {
				Word wd = new Word(a_word, a_pos, 1);
				word.add(wd);
			}
		}
		
		current_sentence += a_word;
		if(!(current_sentencePosed.equals(""))) {
			current_sentencePosed += " ";
		}
		current_sentencePosed += a_word + "/" + a_pos;
		if(a_pos.equals("wj") || a_pos.equals("ww") || a_pos.equals("wt") || a_pos.equals("ws")) {
			sentenceNum++;
			sentence.add(new Sentence(current_sentence, current_sentencePosed, sentenceNum));
			current_sentence = "";
			current_sentencePosed = "";
		}
	}
	
	public static void sentence_weigh() throws Exception { // ����Ȩ�ؼ���
		for(Sentence st : sentence) {
			st.setLength(st.getSentence().length());
			byte[] st_byte = st.getSentencePosed().getBytes(encoding);
			byte[] current_word = new byte[1000];
			byte[] current_pos =  new byte[100];
			byte by;
			
			int j = 0, k = 0;
			boolean tag = false;
			current_word[0] = '\0';
			current_pos[0] = '\0';
			for(int i = 0; i < st_byte.length; i++) {
				by = st_byte[i];
				if((!tag) && (by == ' ' || by == '\n' || by == '\t')) continue;
				if(tag && (by != ' ')) {
					current_pos[k++] = by;
					current_pos[k] = '\0';
					continue;
				}
				if(tag && (by == ' ')) {
					tag = false;
					set_weigh(st, current_word, current_pos);
					j = 0;
					k = 0;
					continue;
				}
				if(!tag && (by != '/')) {
					current_word[j++] = by;
					current_word[j] = '\0';
					continue;
				}
				if(!tag && (by == '/')) tag = true;
			}
			st.setTotalWeigh(st.getTotalWeigh()/st.getLength());
		}
	}
	
	public static void set_weigh(Sentence st, byte[] aword, byte[] apos) throws Exception {
		int w, p;
		for(w = 0; aword[w] != '\0'; w++);
		for(p = 0; apos[p] != '\0'; p++);
		String a_word = new String(aword, 0, w, encoding);
		String a_pos = new String(apos, 0, p, encoding);
		
		for(Word wd : word) {
			if(Arrays.equals(wd.getWord().getBytes(encoding), a_word.getBytes(encoding)) &&
					Arrays.equals(wd.getPos().getBytes(encoding), a_pos.getBytes(encoding))) {
				st.setTotalWeigh(st.getTotalWeigh() + wd.getFrequency());
				return;
			}
		}
	}
	
	public static String abstract_out() throws Exception {
/*
		for(Sentence st : sentence) {
			System.out.println(st.getSentence());
			System.out.println(st.getSentencePosed());
		}
*/		
		try {
		Collections.sort(sentence, new SentenceComparator());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		String abstract_sentences = "";
		
		int abstract_length = 0;
		for(Sentence st : sentence) {
			total_length += st.getLength();
			abstract_length += st.getLength();
			if(st.getLength() < 20 || abstract_length > 300) {
				abstract_length -= st.getLength();
				continue;
			}
			abstract_sentences += st.getSentence();
		}
		abstract_length = 0;
		for(Sentence st : sentence) {
			abstract_length += st.getLength();
			if(st.getLength() < 20 || abstract_length > total_length/10) {
				abstract_length -= st.getLength();
				continue;
			}
			abstract_result1 += st.getSentence();
		}
//		System.out.println(abstract_sentences);
		return abstract_sentences;
	}
	
	
	
	

	
	
	
	public static void file_wordFrequency(String filename) throws Exception {

		FileReader file = new FileReader(filename);
		BufferedReader buff = new BufferedReader(file);
		boolean eof = false;
		
		while(!eof) {
			String line = buff.readLine();
			if(line == null) {
				eof = true;
			}
			else {
				
				
				
				
				
			}
		}
	}

}
