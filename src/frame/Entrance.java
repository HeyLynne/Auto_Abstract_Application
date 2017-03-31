package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.io.*;

import javax.swing.*;
//===========================================
//������� Entrance.java
//===========================================
public class Entrance
{
	public static void main(String[] args) 
	{ 
		//������ʵ��
		MainFrame frame = new MainFrame("���籭");//�����´���
		setMenu(frame);//��ʼ���˵�
		
		frame.validate();
	}
	//============================
	//��Ӳ˵�
	//============================
	private static void setMenu(MainFrame frame)
	{
		//====================================================================
		//getMenuButton(a,b,c,d)����:a:�˵�ͼƬ,b:��꾭��ͼƬ,c:��ʾ����,d:�Ƿ����˵�
		//====================================================================
		//=====================================
		// Դ�ı�
		//=====================================
		JButton source;
		JButton paragraph,text,clear,start;
		source = frame.getMenuButton("source.png", "source_on.png", "Դ�ı�","mainmenu");
		frame.setMainMenu(source);
		//�����ı�
		paragraph = frame.getMenuButton("paragraph.png", "paragraph_on.png", "�����ı�","submenu");;
		frame.addSubMenu(source, paragraph, "paragraph");
		//�ı��ĵ�
//		text = frame.getMenuButton("text.png", "text_on.png", "�ı��ĵ�","submenu");;
//		frame.addSubMenu(source, text, "text");
		//����
		clear = frame.getMenuButton("clear.png", "clear_on.png", "����","submenu");;
		frame.addSubMenu(source, clear, "clear");
		//����
		start = frame.getMenuButton("start.png", "start_on.png", "����","submenu");;
		frame.addSubMenu(source, start, "start");

		//=====================================
		// �ؼ���
		//=====================================
		JButton keyword;
		JButton people , location , all;
		keyword = frame.getMenuButton("keyword.png", "keyword_on.png", "�ؼ���","mainmenu");
		frame.setMainMenu(keyword);
		//����ؼ���
		people = frame.getMenuButton("people.png", "people_on.png", "����","submenu");;
		frame.addSubMenu(keyword, people, "people");
		//�ص�ؼ���
		location = frame.getMenuButton("location.png", "location_on.png", "�ص�","submenu");;
		frame.addSubMenu(keyword, location, "location");
		//���йؼ���
		all = frame.getMenuButton("all.png", "all_on.png", "ȫ��","submenu");;
		frame.addSubMenu(keyword, all, "all");
		
		//=====================================
		// ��ժ��ȡ
		//=====================================
		JButton digest;
		JButton count, percent;
		digest = frame.getMenuButton("digest.png", "digest_on.png", "��ժ��ȡ","mainmenu");
		frame.setMainMenu(digest);
		//�޶�����
		count = frame.getMenuButton("count.png", "count_on.png", "�޶�����300","submenu");;
		frame.addSubMenu(digest, count, "count");
		//�޶����Ȱٷֱ�
		percent = frame.getMenuButton("percent.png", "percent_on.png", "ȫ�ĳ���10%","submenu");;
		frame.addSubMenu(digest, percent, "percent");
				
		//=====================================
		// ϵͳ��Ϣ
		//=====================================
//		JButton info;
//		info = frame.getMenuButton("info.png", "info_on.png", "ϵͳ��Ϣ","mainmenu");
//		frame.setMainMenu(info);
		
		//=====================================
		//����Ĭ�ϲ˵�,Ҳ����������������ʱֱ����ʾʲô
		frame.setDefaultMenu(source);

	}
}
