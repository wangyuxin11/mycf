package com.github.segment.base_statistics;

//基于统计的中文分词算法
//基本思想
//
//选择概率最大的分词路径作为最优结果
//利用动态规划算法来实现,即最优路径中的第i个词w i 的累计概率等于它的左相邻词w i-1 的累积概率乘以w i 自身的概率
//具体算法
//
//(1)对一个待分词的字串S,按照从左到右的顺序取出全部候选词w 1 ,w 2 ,…,w i ,w n ;
//(2)计算每个候选词的概率值P(w i ),记录每个候选词的全部左邻词;
//(3)计算每个候选词的累计概率,累计概率最大的候选词为最佳左邻词;
//如果当前词w n 是字串的尾词,且累计概率P’(w n )最大,则w n 是S的终点词;
//(4)从w n 开始,按照从右到左顺序,依次将每个词的最佳左邻词输出,即S的分词结果.
//--------------------- 
//作者：Quincy1994 
//来源：CSDN 
//原文：https://blog.csdn.net/qq_30843221/article/details/52735423 
//版权声明：本文为博主原创文章，转载请附上博文链接！



import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 字典树
 * 
 * 又称单词查找树，Trie树，是一种树形结构，是一种哈希树的变种。
 * 典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），
 * 所以经常被搜索引擎系统用于文本词频统计。
 * 它的优点是：利用字符串的公共前缀来减少查询时间，
 * 最大限度地减少无谓的字符串比较，查询效率比哈希树高。
 * 
 * 
 * @author wangyx
 *
 */
public class TireNode {

	private String character; // 单个汉字
	private int frequency = -1; // 词频, -1来区别某条路径上的字串是否是一个词组
	private double antilog = -1; // 对数化的词频
	private Map<String, TireNode> children; // 下一个节点

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getAntilog() {
		return antilog;
	}

	public void setAntilog(double antilog) {
		this.antilog = antilog;
	}

	public void addChild(TireNode node) {
		if (children == null) {
			children = new HashMap<String, TireNode>();
		}
		if (!children.containsKey(node.getCharacter())) {
			children.put(node.getCharacter(), node);
		}
	}

	public TireNode getChild(String ch) {
		if (children == null || !children.containsKey(ch)) {
			return null;
		}
		return children.get(ch);
	}

	public void removeChildren(String ch) {
		if (children == null || !children.containsKey(ch)) {
			return;
		}
		children.remove(ch);
	}

}
