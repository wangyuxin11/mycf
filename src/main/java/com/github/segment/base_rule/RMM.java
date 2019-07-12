package com.github.segment.base_rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 逆向最大匹配法
 * 
 * 算法描述
 * 与正向最大匹配法原理一样，只是匹配的开始为句尾
 * 
 * 
 * https://blog.csdn.net/qq_30843221/article/details/52735423
 * 
 * @author wangyx
 *
 */
public class RMM {
	private String m_sResult = ""; // 切分后的结果串
	private int m_nPosIndex; // 游标指针
	private int m_MaxLen; // 最大取词长
	private int totalMaxlen; // 总最大取词长
	private Set<String> dictionary; // 分词字典

	public RMM(int maxLen) {
		this.m_MaxLen = maxLen;
		this.totalMaxlen = maxLen;
		try {
			this.dictionary = loadFile();
		} catch (IOException ex) {
			Logger.getLogger(RMM.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public RMM() {
		this.m_MaxLen = 3;
		this.totalMaxlen = 3;
		try {
			this.dictionary = loadFile();
		} catch (IOException ex) {
			Logger.getLogger(RMM.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Set<String> loadFile() throws IOException {
		// 读取字典
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

	public String RMMSegment(String source) {
		int len = totalMaxlen;
		this.m_nPosIndex = source.length();
		int frompos = this.m_nPosIndex;
		rmm(source, m_MaxLen, m_nPosIndex);
		
		// 将结果按顺序输出
		String[] token = m_sResult.split("/");
		String result = "";
		for (int i = token.length - 1; i > 0; i--) {
			result += token[i] + "/ ";
		}
		return result;
	}

	public String getSubString(String source, int m_nPosIndex, int len) {
		int startIndex = m_nPosIndex - len;
		// 判断越界条件
		while (startIndex < 0) {
			startIndex += 1;
		}
		String sub = source.substring(startIndex, m_nPosIndex);
		return sub;
	}

	public void rmm(String source, int len, int frompos) {
		if (m_nPosIndex < 0)
			return;
		String sub = getSubString(source, m_nPosIndex, len);
		if (dictionary.contains(sub)) {
			// 匹配成功
			m_sResult += "/" + sub;
			m_nPosIndex = m_nPosIndex - m_MaxLen;
			m_MaxLen = totalMaxlen;
			rmm(source, m_MaxLen, m_nPosIndex);
		} else {
			// 不匹配
			if (m_MaxLen > 1) {
				m_MaxLen = m_MaxLen - 1;
				rmm(source, m_MaxLen, m_nPosIndex);
			} else {
				m_sResult += "/" + sub;
				m_nPosIndex -= 1;
				m_MaxLen = totalMaxlen;
				rmm(source, m_MaxLen, m_nPosIndex);
			}
		}
	}

	public static void main(String[] args) {
		// TODO code application logic here
		RMM myRMM = new RMM();
		String source = "记录最佳前候选词列表，最佳的记录";
		String result = myRMM.RMMSegment(source);
		System.out.println(result);
	}

}
