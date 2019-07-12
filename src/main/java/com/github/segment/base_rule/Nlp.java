package com.github.segment.base_rule;

import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.HashSet; 
import java.util.Set; 
import java.util.logging.Level; 
import java.util.logging.Logger; 

/**
 * 正向最大匹配法
 * 
 * 
 * 算法描述
 * 
 * 设MaxLen表示最大词长,D为分词词典 
 * (1) 从待切分语料中按正向取长度为MaxLen的字串str,令Len=MaxLen; 
 * (2) 把str与D中的词相匹配;
 * (3) 若匹配成功,则认为该字串为词,指向待切分语料的指针向前移Len个汉字(字节),返回到(1); 
 * (4) 若不成功:如果Len>1,则将Len减2,从待切分语料中取长度为Len的字串str,返回到(2)。否则,得到长度为2的单字词,指向待切分语料的指针向前移1个汉字,返回(1)。
 * 
 * @author wangyx
 *
 */
public class Nlp {

	private String m_sResult = ""; //切分后的结果串
	private int m_nPosIndex; //指向待切分语料的指针的具体位置
	private int m_MaxLen; //最大取词长
	private int totalMaxLen; //总最大取词长
	private Set<String> dictionary; //分词字典

	public Nlp(int maxLen) {
		this.m_MaxLen = maxLen;
		this.m_nPosIndex = 0;
		this.totalMaxLen = maxLen;
		try {
			this.dictionary = this.loadFile();
		} catch (IOException ex) {
			Logger.getLogger(Nlp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Nlp() {
		this.m_MaxLen = 3;
		this.totalMaxLen = 3;
		this.m_nPosIndex = 0;
		try {
			this.dictionary = this.loadFile();
		} catch (IOException ex) {
			Logger.getLogger(Nlp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Set<String> loadFile() throws FileNotFoundException, IOException {
		//读取字典
		Set<String> dictionary = new HashSet<String>();
		String filename = "C:\\Users\\wangyx\\github\\mycf\\src\\main\\java\\com\\github\\segment\\base_rule\\dict.txt";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp;
		while ((tmp = br.readLine()) != null) {
			String[] token = tmp.split(",");
			String word = token[0];
			dictionary.add(word);
		}
		return dictionary;
	}

	public String MMSegment(String source) {
		int len = totalMaxLen;
		int frompos = 0;
		MM(source, len, frompos);
		return m_sResult;
	}

	public String getSubString(String source, int m_nPosIndex, int len) {
		int endIndex = m_nPosIndex + len;
		int length = source.length();
		//需要判断是否超出句子边界
		while (endIndex > length) {
			endIndex -= 1;
		}
		String sub = source.substring(m_nPosIndex, endIndex);
		return sub;
	}

	public void MM(String source, int len, int frompos) {
		//递归匹配
		if (m_nPosIndex >= source.length())
			return;
		String sub = getSubString(source, m_nPosIndex, len);
		if (dictionary.contains(sub)) {
			//匹配
			m_sResult += sub + "/ ";
			m_nPosIndex = m_nPosIndex + m_MaxLen;
			m_MaxLen = totalMaxLen;
			MM(source, m_MaxLen, m_nPosIndex);
		} else {
			//不匹配
			if (m_MaxLen > 1) {
				m_MaxLen = m_MaxLen - 1;
				MM(source, m_MaxLen, m_nPosIndex);
			} else {
				m_sResult += sub + "/ ";
				m_nPosIndex += 1;
				m_MaxLen = totalMaxLen;
				MM(source, m_MaxLen, m_nPosIndex);
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Nlp nlp = new Nlp();
		String source = "今天天气不错！";
		String result = nlp.MMSegment(source);
		System.out.println(result);
	}

}
