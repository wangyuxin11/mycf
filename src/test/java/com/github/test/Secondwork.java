package com.github.test;

public class Secondwork {
	
	public static void main(String args[]) { 
		String[] cs = {"���","����","ʲô","����"}; //�ʵ� 
		String a = "��ã��������ʲô���֣�"; 
		System.out.print("ԭ�䣺"); 
		System.out.print(a + '\n'); 
		System.out.print("��ֺ�Ϊ��"); 
		String[] cs2 = new String[100]; //������� 
		int jud=0;//�ҵ�ƥ���ַ������ı�־ 
		int j=0; 
		String temp=null;//��ʼ����ʱ�ַ��� 
		for(;a.length()>0;) {
			for(int i = 0;i<a.length();i++) {
				temp = a.substring(i);//ÿ�ν�ȡ���׸��ַ� 
				if(isin(cs,temp) == true)//���Ŀ���ַ����������� 
				{
					cs2[j] = temp; 
					jud = 1; 
					int number = temp.length(); 
					a = a.substring(0,a.length()-number); 
				} 
				if(jud == 0)//û���ҵ�ƥ���ַ��� 
				{
					cs2[j] = a.substring(a.length()-1,a.length());//�����һ��Ԫ�ط���cs2���� 
					a = a.substring(0, a.length()-1);//�ص����һ��Ԫ�ؼ���ѭ���� 
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
		
	/* * ����Ϊ�ж��ַ����Ƿ��ڴʵ��еĺ������� */ 
	static public boolean isin(String[] cs,String temp)//�ж�Ŀ���ַ����Ƿ��ڶԱ��ַ��������� 
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
