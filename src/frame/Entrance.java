package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.io.*;

import javax.swing.*;
//===========================================
//软件主体 Entrance.java
//===========================================
public class Entrance
{
	public static void main(String[] args) 
	{ 
		//创建新实例
		MainFrame frame = new MainFrame("冯如杯");//创建新窗体
		setMenu(frame);//初始化菜单
		
		frame.validate();
	}
	//============================
	//添加菜单
	//============================
	private static void setMenu(MainFrame frame)
	{
		//====================================================================
		//getMenuButton(a,b,c,d)参数:a:菜单图片,b:鼠标经过图片,c:提示文字,d:是否主菜单
		//====================================================================
		//=====================================
		// 源文本
		//=====================================
		JButton source;
		JButton paragraph,text,clear,start;
		source = frame.getMenuButton("source.png", "source_on.png", "源文本","mainmenu");
		frame.setMainMenu(source);
		//段落文本
		paragraph = frame.getMenuButton("paragraph.png", "paragraph_on.png", "段落文本","submenu");;
		frame.addSubMenu(source, paragraph, "paragraph");
		//文本文档
//		text = frame.getMenuButton("text.png", "text_on.png", "文本文档","submenu");;
//		frame.addSubMenu(source, text, "text");
		//重置
		clear = frame.getMenuButton("clear.png", "clear_on.png", "重置","submenu");;
		frame.addSubMenu(source, clear, "clear");
		//启动
		start = frame.getMenuButton("start.png", "start_on.png", "启动","submenu");;
		frame.addSubMenu(source, start, "start");

		//=====================================
		// 关键词
		//=====================================
		JButton keyword;
		JButton people , location , all;
		keyword = frame.getMenuButton("keyword.png", "keyword_on.png", "关键词","mainmenu");
		frame.setMainMenu(keyword);
		//人物关键词
		people = frame.getMenuButton("people.png", "people_on.png", "人物","submenu");;
		frame.addSubMenu(keyword, people, "people");
		//地点关键词
		location = frame.getMenuButton("location.png", "location_on.png", "地点","submenu");;
		frame.addSubMenu(keyword, location, "location");
		//所有关键词
		all = frame.getMenuButton("all.png", "all_on.png", "全部","submenu");;
		frame.addSubMenu(keyword, all, "all");
		
		//=====================================
		// 文摘提取
		//=====================================
		JButton digest;
		JButton count, percent;
		digest = frame.getMenuButton("digest.png", "digest_on.png", "文摘提取","mainmenu");
		frame.setMainMenu(digest);
		//限定字数
		count = frame.getMenuButton("count.png", "count_on.png", "限定字数300","submenu");;
		frame.addSubMenu(digest, count, "count");
		//限定长度百分比
		percent = frame.getMenuButton("percent.png", "percent_on.png", "全文长度10%","submenu");;
		frame.addSubMenu(digest, percent, "percent");
				
		//=====================================
		// 系统信息
		//=====================================
//		JButton info;
//		info = frame.getMenuButton("info.png", "info_on.png", "系统信息","mainmenu");
//		frame.setMainMenu(info);
		
		//=====================================
		//设置默认菜单,也就是左边在软件运行时直接显示什么
		frame.setDefaultMenu(source);

	}
}
