package frame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import algorithmOperation.AlgorithmEntrance;

import java.awt.*;
import java.util.Vector;
//===========================================
//�����˵���Ӧ�¼�����  SubMenuActionDo.java
//===========================================
public class SubMenuActionDo implements ActionListener
{
	//��Ӧ����
	MainFrame frame;
	JPanel panel ; 
	ConfigGet con ;
	
	JTextArea t;
	//==========================
	//������ʼ��
	SubMenuActionDo(MainFrame frame)
	{
		this.frame = frame;
		this.panel = frame.panel.m_panel.p_right;
		con = new ConfigGet();
	}
	//==========================
	//�¼���Ӧ
	//==========================
	public void actionPerformed(ActionEvent e)
	{
		
		//=====================
		//��������
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
		//���������Ҫ����
		JLabel lb = new JLabel("Դ�ı���");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getSource());
		frame.validate();
		frame.reDoCommand = "paragraphAction"; //��ǰ�������ڴ����ػ�ִ�к�����
		return txt;
	}
	public JTextArea textAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//���������Ҫ����
		JLabel lb = new JLabel("�ı�·����");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(5, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText("");
		
		frame.validate();
		frame.reDoCommand = "textAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
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
		//���������Ҫ����
		JLabel lb = new JLabel("�ؼ��ʣ�");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getKeyword());
		
		frame.validate();
		frame.reDoCommand = "allAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
	}
	public void peopleAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//���������Ҫ����
		JLabel lb = new JLabel("����ؼ��ʣ�");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getPeople());
		
		frame.validate();
		frame.reDoCommand = "peopleAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
	}
	public void locationAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//���������Ҫ����
		JLabel lb = new JLabel("����λ�ùؼ��ʣ�");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getLocation());
		
		frame.validate();
		frame.reDoCommand = "locationAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
	}
	public void countAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//���������Ҫ����
		JLabel lb = new JLabel("��ժ��");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getDigest());
		
		frame.validate();
		frame.reDoCommand = "countAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
	}
	public void percentAction() {
		panel.setPreferredSize(new Dimension(frame.getMinMainBoardWidth(), 500));
		panel.removeAll();
		panel.repaint();
		//==============================
		//���������Ҫ����
		JLabel lb = new JLabel("��ժ��");
		panel.add(lb);
		
		JTextArea txt = new JTextArea(26, 56);
		txt.setLineWrap(true);
		panel.add(new JScrollPane(txt));
		txt.setText(AlgorithmEntrance.info.getDigest1());
		
		frame.validate();
		frame.reDoCommand = "percentAction" ; //��ǰ�������ڴ����ػ�ִ�к�����
	}
}