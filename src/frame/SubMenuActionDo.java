package frame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import algorithmOperation.AlgorithmEntrance;

import java.awt.*;
import java.util.Vector;
//===========================================
//二级菜单响应事件处理  SubMenuActionDo.java
//===========================================
public class SubMenuActionDo implements ActionListener
{
	//对应窗体
	MainFrame frame;
	JPanel panel ; 
	ConfigGet con ;
	
	JTextArea t;
	//==========================
	//函数初始化
	SubMenuActionDo(MainFrame frame)
	{
		this.frame = frame;
		this.panel = frame.panel.m_panel.p_right;
		con = new ConfigGet();
	}
	//==========================
	//事件响应
	//==========================
	public void actionPerformed(ActionEvent e)
	{
		
		//=====================
		//如果是添加
		if(e.getActionCommand().equals("paragraph")) {
			t = paragraphAction();
		}
		else if(e.getActionCommand().equals("text")) {
			t = textAction();
		}
		else if(e.getActionCommand().equals("clear")) {
			clearAction();
		}
		else if(e.getActionCommand().equals("start")) {
			startAction();
		}
		else if(e.getActionCommand().equals("all")) {
			allAction();
		}
		else if(e.getActionCommand().equals("people")) {
			peopleAction();
		}
		else if(e.getActionCommand().equals("location")) {
			locationAction();
		}
		else if(e.getActionCommand().equals("count")) {
			countAction();
		}
		else if(e.getActionCommand().equals("percent")) {
			percentAction();
		}
		else {
			
		}
	}
	//===================================
	//
	public JTextArea  paragraphAction() {
		
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 400));
		//System.out.println(panel.getWidth()+"=========");
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("源文本：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getSource());
		frame.validate();
		frame.reDoCommand = "paragraphAction"; //当前存下现在窗体重化执行函数名
		return txt;
	}
	public JTextArea textAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("文本路径：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(5, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText("");
		
		frame.validate();
		frame.reDoCommand = "textAction" ; //当前存下现在窗体重化执行函数名
		return txt;
	}
	public void clearAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		AlgorithmEntrance.info.reset();
	}
	public void startAction() {
		if(frame.reDoCommand == "paragraphAction") {
			AlgorithmEntrance.info.setSource(t.getText());
		}
		try {
			AlgorithmEntrance.get_abstract();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void allAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("关键词：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getKeyword());
		
		frame.validate();
		frame.reDoCommand = "allAction" ; //当前存下现在窗体重化执行函数名
	}
	public void peopleAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("人物关键词：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getPeople());
		
		frame.validate();
		frame.reDoCommand = "peopleAction" ; //当前存下现在窗体重化执行函数名
	}
	public void locationAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("地理位置关键词：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getLocation());
		
		frame.validate();
		frame.reDoCommand = "locationAction" ; //当前存下现在窗体重化执行函数名
	}
	public void countAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("文摘：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getDigest());
		
		frame.validate();
		frame.reDoCommand = "countAction" ; //当前存下现在窗体重化执行函数名
	}
	public void percentAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//这里添加需要对象
		JLabel lb = new JLabel("文摘：");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getDigest1());
		
		frame.validate();
		frame.reDoCommand = "percentAction" ; //当前存下现在窗体重化执行函数名
	}
}