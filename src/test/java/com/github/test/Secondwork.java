package com.github.test;

public class Secondwork {
	
	public static void main(String args[]) { 
		String[] cs = {"你好","请问","什么","名字"}; //词典 
		String a = "你好！请问你叫什么名字？"; 
		System.out.print("原句："); 
		System.out.print(a + '\n'); 
		System.out.print("拆分后为："); 
		String[] cs2 = new String[100]; //结果数组 
		int jud=0;//找到匹配字符串与否的标志 
		int j=0; 
		String temp=null;//初始化临时字符串 
		for(;a.length()>0;) {
			for(int i = 0;i<a.length();i++) {
				temp = a.substring(i);//每次截取掉首个字符 
				if(isin(cs,temp) == true)//如果目标字符串在数组中 
				{
					cs2[j] = temp; 
					jud = 1; 
					int number = temp.length(); 
					a = a.substring(0,a.length()-number); 
				} 
				if(jud == 0)//没有找到匹配字符串 
				{
					cs2[j] = a.substring(a.length()-1,a.length());//将最后一个元素放在cs2里面 
					a = a.substring(0, a.length()-1);//截掉最后一个元素继续循环。 
				} 
				jud = 0; 
				j++; 
			}
			for(;j >= 0;j--) 
			{ 
				if(cs2[j] != null) 
					System.out.print(cs2[j]+" "); 
			}
		}
	}
		
	/* * 下面为判断字符串是否在词典中的函数方法 */ 
	static public boolean isin(String[] cs,String temp)//判断目标字符串是否在对比字符串数组中 
	{
		int i; 
		for(i = 0;i<cs.length;) { 
			if(temp.equals(cs[i])) 
				i = cs.length+1; 
			else 
				i++; 
		} 
		if(i == cs.length+1) 
			return true; 
		else 
			return false;
	}
	
}
