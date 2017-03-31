package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//===========================================
//事件处理类 ActionDo.java
//===========================================
public class ActionDo implements ActionListener
{
	MainFrame frame;
	JButton button;
	//======================
	//配置类
	ConfigGet con ;
	Dimension sdm ;//分辨率
	//======================
	//皮肤样式夹
	String img_dir = "skin_black";
	//======================
	//重画类
	RepaintActionDo action ;
	//======================
	//初始化
	ActionDo(MainFrame f,JButton btn)
	{
		this.frame = f;
		this.button = btn;
		//配置类
		con = new ConfigGet();
		action = new RepaintActionDo(f);
		//皮肤文件
		img_dir = con.getSkinDir();
		//分辨率
		sdm = con.getScreenSize();
	}
	//======================
	//事件处理
	public void actionPerformed(ActionEvent e) 
	{
		
		//==================
		//窗口最大化
		if(e.getActionCommand().equals("cmd_max"))
		{
			fullFrame();
		}
		//==================
		//窗口一般化
		if(e.getActionCommand().equals("cmd_normal"))
		{
			normalFrame();
		}
		//==================
		//窗口最小化
		else if(e.getActionCommand().equals("cmd_min"))
		{
			frame.setExtendedState(frame.ICONIFIED|frame.getExtendedState());
		}
		//==================
		//窗口关闭
		else if(e.getActionCommand().equals("cmd_close"))
		{
			System.exit(0);
		}
		//==================
		//关闭左边二级菜单面板
		else if(e.getActionCommand().equals("lmenu_close"))
		{
			frame.closeLeftMenu();
		}
		//==================
		//打开左边二级菜单面板
		else if(e.getActionCommand().equals("lmenu_open"))
		{
			frame.openLeftMenu();
		}
		//===================
		//点击主按钮显示二级菜单
		else if(e.getActionCommand().equals("lmenu_add"))
		{
			addSubMenu();
		}
	}
	//==========================
	//全屏
	//==========================
	private void fullFrame()
	{
		//设置最大化
		con.resetFrameSize(frame,sdm.width,sdm.height);
		con.fullAllWaysTop(frame);//是否最前端显示,在Config.java里配置
		frame.isFullScreen = true;
		//设置按钮
		button.setIcon(con.getImgUrl(("maxed.png")));
		button.setRolloverIcon(con.getImgUrl(("maxed_on.png")));
		frame.validate();//有效
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_normal");
		button.setToolTipText("正常模式");
		//new SubMenuActionDo(frame).btnExamKindAction();
		action.repaintFrame();
		frame.validate();
		//System.out.println(frame.panel.m_panel.scrollPane.getWidth()+"=========="+frame.panel.m_panel.p_right.getWidth());
	}
	//==========================
	//窗口正常大小
	//==========================
	private void normalFrame()
	{
		//设置最大化
		Dimension fdm = con.getFrameSmallSize(false);//初始窗体是否全屏
		con.resetFrameSize(frame,fdm.width,fdm.height);
		con.normalAllWaysTop(frame);//是否最前端显示,在Config.java里配置
		frame.isFullScreen = false;
		//设置按钮
		button.setIcon(con.getImgUrl(("max.png")));
		button.setRolloverIcon(con.getImgUrl(("max_on.png")));
		//有效
		frame.validate();
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_max");
		button.setToolTipText("全屏模式");
		//new SubMenuActionDo(frame).btnExamKindAction();
		action.repaintFrame();
		frame.validate();
		//System.out.println(frame.panel.m_panel.scrollPane.getWidth()+"=========="+frame.panel.m_panel.p_right.getWidth());
	}
	//==========================
	//添加子菜单
	//==========================
	private void addSubMenu()
	{
		//============================
		//如果左边菜单面板处理关了状态
		//============================
		if(!frame.lmenu_isopen)
		{
			frame.openLeftMenu();
		}
		frame.getSubMenu(button);
		frame.validate();
	}
}
