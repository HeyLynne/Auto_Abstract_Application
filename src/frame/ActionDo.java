package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//===========================================
//�¼������� ActionDo.java
//===========================================
public class ActionDo implements ActionListener
{
	MainFrame frame;
	JButton button;
	//======================
	//������
	ConfigGet con ;
	Dimension sdm ;//�ֱ���
	//======================
	//Ƥ����ʽ��
	String img_dir = "skin_black";
	//======================
	//�ػ���
	RepaintActionDo action ;
	//======================
	//��ʼ��
	ActionDo(MainFrame f,JButton btn)
	{
		this.frame = f;
		this.button = btn;
		//������
		con = new ConfigGet();
		action = new RepaintActionDo(f);
		//Ƥ���ļ�
		img_dir = con.getSkinDir();
		//�ֱ���
		sdm = con.getScreenSize();
	}
	//======================
	//�¼�����
	public void actionPerformed(ActionEvent e) 
	{
		
		//==================
		//�������
		if(e.getActionCommand().equals("cmd_max"))
		{
			fullFrame();
		}
		//==================
		//����һ�㻯
		if(e.getActionCommand().equals("cmd_normal"))
		{
			normalFrame();
		}
		//==================
		//������С��
		else if(e.getActionCommand().equals("cmd_min"))
		{
			frame.setExtendedState(frame.ICONIFIED|frame.getExtendedState());
		}
		//==================
		//���ڹر�
		else if(e.getActionCommand().equals("cmd_close"))
		{
			System.exit(0);
		}
		//==================
		//�ر���߶����˵����
		else if(e.getActionCommand().equals("lmenu_close"))
		{
			frame.closeLeftMenu();
		}
		//==================
		//����߶����˵����
		else if(e.getActionCommand().equals("lmenu_open"))
		{
			frame.openLeftMenu();
		}
		//===================
		//�������ť��ʾ�����˵�
		else if(e.getActionCommand().equals("lmenu_add"))
		{
			addSubMenu();
		}
	}
	//==========================
	//ȫ��
	//==========================
	private void fullFrame()
	{
		//�������
		con.resetFrameSize(frame,sdm.width,sdm.height);
		con.fullAllWaysTop(frame);//�Ƿ���ǰ����ʾ,��Config.java������
		frame.isFullScreen = true;
		//���ð�ť
		button.setIcon(con.getImgUrl(("maxed.png")));
		button.setRolloverIcon(con.getImgUrl(("maxed_on.png")));
		frame.validate();//��Ч
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_normal");
		button.setToolTipText("����ģʽ");
		//new SubMenuActionDo(frame).btnExamKindAction();
		action.repaintFrame();
		frame.validate();
		//System.out.println(frame.panel.m_panel.scrollPane.getWidth()+"=========="+frame.panel.m_panel.p_right.getWidth());
	}
	//==========================
	//����������С
	//==========================
	private void normalFrame()
	{
		//�������
		Dimension fdm = con.getFrameSmallSize(false);//��ʼ�����Ƿ�ȫ��
		con.resetFrameSize(frame,fdm.width,fdm.height);
		con.normalAllWaysTop(frame);//�Ƿ���ǰ����ʾ,��Config.java������
		frame.isFullScreen = false;
		//���ð�ť
		button.setIcon(con.getImgUrl(("max.png")));
		button.setRolloverIcon(con.getImgUrl(("max_on.png")));
		//��Ч
		frame.validate();
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_max");
		button.setToolTipText("ȫ��ģʽ");
		//new SubMenuActionDo(frame).btnExamKindAction();
		action.repaintFrame();
		frame.validate();
		//System.out.println(frame.panel.m_panel.scrollPane.getWidth()+"=========="+frame.panel.m_panel.p_right.getWidth());
	}
	//==========================
	//����Ӳ˵�
	//==========================
	private void addSubMenu()
	{
		//============================
		//�����߲˵���崦�����״̬
		//============================
		if(!frame.lmenu_isopen)
		{
			frame.openLeftMenu();
		}
		frame.getSubMenu(button);
		frame.validate();
	}
}
