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
	
	private static String encoding = "UTF-8"; // 编码格式
	
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
		
		boolean mode = false; // true为文本摘要，false为段落摘要
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
//			String src = "\n\n				\n\n				\n\n        \n            \n\n                  \n                \n				\n\n　　政策保持稳健基调  国际资本流入较多\n\n \n\n　　货币供应年内总体比较充裕(市场观察)\n\n　　本报记者  罗兰\n\n　　中国人民银行最新发布的报告显示，9月末金融机构人民币各项贷款余额70.28万亿元，同比增长14.3%，增速比上季末高0.1个百分点。专家指出，数据表明当前社会信贷需求上升，总体资金需求比较旺盛。而前段时期不时爆出“钱紧”的信息，那么，年内货币供应能否满足市场对资金的需求？\n\n　　跨境资金流入较多\n\n　　从前三季度看，市场上货币供应一直比较充裕。据国家统计局新闻发言人盛来运介绍，前三季度货币供应增长较快，截至9月末，我国广义货币(M2)余额107.74万亿元，同比增长14.2%；狭义货币(M1)余额31.23万亿元，增长8.9%；流通中货币(M0)余额5.65万亿元，增长5.7%。\n\n　　此外，从外汇占款看，当前跨境资金流入量也在剧增。据中国人民银行发布的数据：9月末金融机构外汇占款余额升至27.52万亿元，而8月底为27.39万亿元。9月外汇占款增加1263.62亿元，创下今年以来新高。\n\n　　中国经济在三季度企稳向好，人民币汇率升值，进出口贸易保持了顺差，再加上美国一再延迟退出QE，这些因素综合作用吸引了国际资金流入中国。\n\n　　四季度流动性或偏松\n\n　　其实，中国市场上的货币供应一直比较充裕。据暨南大学国际商学院副院长孙华妤介绍，中国货币政策多以稳健为主，在此基调下长期偏松。今年年初，国家制定的全年M2增长预期目标是13%，但事实上，每个月的M2增速都超出了目标。\n\n　　衡量货币供应是否合适有一公式：货币供给增长率等于GDP增长率与通胀率之和。“中国连续几年是货币供应大国，货币供应增长率大于GDP增长率与通胀率之和。”首都经济贸易大学金融学院院长谢太峰在接受本报记者采访时分析说，美国2012年M2是10万多亿美元，GDP是15万亿美元，计算下来，一美元货币供应能拉动1.5美元的GDP。而中国2012年M2是100万亿元人民币，GDP是51万亿元人民币，相当于1元人民币拉动0.5元人民币的GDP。从中可看出，中国经济增长有很大因素是靠货币堆出来的。\n\n　　然而有趣的是，货币供应大国却不时发生“钱紧”的情况。对此，孙华妤认为，因过去多年来M2增速超过17%，现在降到了15%左右，与过去相比，大家感觉还是偏紧。谢太峰指出，一个重要原因是现在银行间市场正在脱离实体经济，已成为赚钱的渠道。\n\n　　“从前三季度看，四季度货币供应量还是会超过GDP增长率与通胀率之和。一般年底货币供应都会增加，各单位要突击花钱，估计第四季度货币供应会高于前三季度”，谢太峰认为，未来热钱流入情况要看人民币汇率的升降。假如国际预期人民币升值，热钱会流入增多，反之，则会少。而人民币汇率是否升值不仅是经济问题，还是政治问题。按道理人民币不应该再升值了，但美日欧施压中国，人民币还会升，但升值的空间不会太大了。\n\n　　货币政策将稳中偏紧\n\n　　如果未来市场流动性过多，大家普遍预计国家将维持稳健甚至偏紧的货币政策。渣打银行[微博]发布报告称，中国经济进入复苏模式，预期四季度复苏态势将会持续，同时预期四季度货币政策立场将维持不变。\n\n　　近期中国央行表态暗示银行体系流动性供应总体较为充裕，尤其是三季度以来净资本流入恢复。四季度银行间利率将会保持稳定。央行行长周小川此前也表态，下半年会继续执行稳健的货币政策。\n\n　　有专家建议，为保证经济增长不跌破底线，货币政策可以在稳中适度放松。而谢太峰则认为，从现在的CPI看，通胀率不算很高，似乎可以实行宽松的货币政策，但实际上我国的CPI没有包括与百姓息息相关的房价。从经济发展情况看，货币宽松就会增加投资，走的依然是投资拉动经济的老方式。现在需要转变为依靠消费拉动经济增长，这就需要增加百姓收入，把最近遏制下来的“三公”消费需求由老百姓的消费补上，否则整个消费需求会下降。\n\n\n\n				\n\n			";
			
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
	
	public static void wordPreTreat(String s) throws Exception { // 词语预处理
		
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
	
	public static void add_words(byte[] aword, byte[] apos) throws Exception { // 名词动词次品统计，句子分离
		int w, p;
		for(w = 0; aword[w] != '\0'; w++);
		for(p = 0; apos[p] != '\0'; p++);
		String a_word = new String(aword, 0, w, encoding);
		String a_pos = new String(apos, 0, p, encoding);
//		if(Arrays.equals(a_word.getBytes(encoding), "聽".getBytes(encoding))) {
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
	
	public static void sentence_weigh() throws Exception { // 句子权重计算
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
