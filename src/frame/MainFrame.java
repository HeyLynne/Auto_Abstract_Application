package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

//===========================================
//       软件主窗体 EETSoftFrame.java
//===========================================
class MainFrame extends JFrame
{
	//软件主体panel
	MainPanel panel;
	boolean isFullScreen = true; // 是否全屏
	boolean lmenu_isopen = true ; // 左边二级菜单是否展开
	String img_dir = "skin_black";//皮肤文件夹
	Dimension fdm,sdm;//窗体大小:窗体|分辨率
	ConfigGet con;//配置类
	Vector<MenuButtonBox> menulist = new Vector<MenuButtonBox>();//菜单数组\
	String reDoCommand ;
	RepaintActionDo action;
	//====================
	//初始化
	MainFrame(String title)
	{
		super(title);
		//初始化窗体
		setWindow(this);
		//更新窗口
		updateWindow(this);
	}
	//===============================
	//窗体初始化
	//===============================
	private void setWindow(MainFrame f)
	{
		//=============================
		//读取配置类
		con = new ConfigGet();
		con.setFrameUI();
		action = new RepaintActionDo(f);
		//启动时是否全屏
		isFullScreen = con.openfull;
		//初始化皮肤文件
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(isFullScreen);
		sdm = con.getScreenSize();
		f.setSize(fdm);//设置窗体大小
		addMainPanel(this,fdm.width,fdm.height,isFullScreen);//添加主面板
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);//位置
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(Entrance.class.getResource(img_dir + "/冯如杯logo.png")));//图标
		f.setBackground(Color.WHITE);//设置背景色
	}
	//===============================
	//更新窗口
	//===============================
	private void updateWindow(JFrame f)
	{
		f.setUndecorated(true);//设置没有边框
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//添加系统退出事件
		f.validate();//有效
		f.setVisible(true);//可见
	}
	//================================
	//添加主面板
	//================================
	private void addMainPanel(final MainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		//=====================
		//创建面板
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);
		//=====================
		//关闭按钮事件
		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new ActionDo(this,panel.btn_close));
		//=====================
		//大小化按钮事件
		if(iffull)
		{//如果是全屏显示
			panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
			panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
			panel.btn_max.setActionCommand("cmd_normal");
			panel.btn_max.addActionListener(new ActionDo(this,panel.btn_max));
		}
		else
		{//正常模式
			panel.btn_max.setActionCommand("cmd_max");
			panel.btn_max.addActionListener(new ActionDo(this,panel.btn_max));
		}
		//=====================
		//最小化
		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new ActionDo(this,panel.btn_min));
		//=====================
		//鼠标事件点击点
		final Point point = new Point();
		//=====================
		//顶部panel点击事件,单击双击
		panel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) //点击,记录点击位置以便拖动时计算
			{
				point.x = e.getX();
				point.y = e.getY();
			}
			public void mouseClicked(MouseEvent e)
			{//点击
				if(e.getClickCount() == 2)
				{//双击
					if(panel.btn_max.getActionCommand().equals("cmd_normal"))//正常大小
					{
						fdm = con.getFrameSmallSize(false);
						con.resetFrameSize(frame,fdm.width,fdm.height);
						validate(); //先更新,以便后面按正常更新
						con.normalAllWaysTop(frame);//是否最前端显示,在Config.java里配置
						isFullScreen = false;
						//设置按钮
						panel.btn_max.setIcon(con.getImgUrl("max.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("max_on.png"));
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						panel.btn_max.setActionCommand("cmd_max");
						panel.btn_max.setToolTipText("全屏模式");
						action.repaintFrame();
						//new SubMenuActionDo(frame).btnExamKindAction();
						validate();
					}
					else//全屏模式
					{
						con.resetFrameSize(frame,sdm.width,sdm.height);
						validate(); //先更新
						con.fullAllWaysTop(frame);//是否最前端显示,在Config.java里配置
						isFullScreen = true;
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						//设置按钮
						panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
						panel.btn_max.setActionCommand("cmd_normal");
						panel.btn_max.setToolTipText("正常模式");
						action.repaintFrame();
						//new SubMenuActionDo(frame).btnExamKindAction();
						validate();
					}
				}
			}
		});
		//================
		//顶部Panel拖动事件
		panel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(!isFullScreen)
				{
					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
				}
			}
		});
		//================================
		//  右下角的拖动按钮
		//================================
		panel.bottom_resize.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				//根据各部分计算最小不能小于多少尺寸
				int min_w = frame.panel.img_t_bg_w + frame.panel.img_t_mid_w + frame.panel.btn_close_img.getIconWidth() + frame.panel.btn_max_img.getIconWidth()  + frame.panel.btn_min_img.getIconWidth();
				int min_h = frame.panel.img_t_l.getIconHeight() + frame.panel.img_menu.getIconHeight() + frame.panel.img_info.getIconHeight() + frame.panel.img_b_l.getIconHeight() ;
				//最大不能超过分辨率
				int max_w = con.getScreenSize().width;
				int max_h = con.getScreenSize().height;
				int fw = frame.getWidth(); //窗体宽
				int fh = frame.getHeight(); //窗体高
				int ex = fw + e.getX() - panel.bottom_resize.getWidth(); //鼠标移动后的宽/
				int ey = fh + e.getY() - panel.bottom_resize.getHeight(); // 鼠标移动后的高
				if( ex < min_w ) ex = min_w ; //小于最小宽
				if( ey < min_h ) ey = min_h ; //小于最高
				if( ex > max_w ) ex = max_w ; //最大宽度不得超过分辨率宽
				if( ey > max_h ) ey = max_h ; //最大高度不得超过分辨率高
				//宽高都没达到最小
				if(!isFullScreen)
				{
					//重画
					con.resetFrameSize(frame, ex, ey);
					frame.validate();
				}
			}
		});
		//窗体变大,右边容器也要对应变大小
		panel.bottom_resize.addMouseListener (new MouseAdapter() 
		{
			public void mouseReleased(MouseEvent e) //点击,记录点击位置以便拖动时计算
			{
				action.repaintFrame();
				//new SubMenuActionDo(frame).btnExamKindAction();
			}
		});
	}
	//==========================
	//打开左边菜单
	//==========================
	public void openLeftMenu()
	{
		int tw = this.panel.m_panel.p_left.getWidth() ; 
		this.panel.m_panel.p_left.setVisible(true);
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() - tw -17 ,this.panel.m_panel.getHeight()));
		this.panel.m_panel.scrollPane.setPreferredSize(new Dimension(this.panel.m_panel.scrollPane.getWidth() - tw,this.panel.m_panel.getHeight()));
		this.lmenu_isopen = true;
		this.panel.m_panel.btn_split.setIcon(con.getImgUrl("btn_split_l.png"));
		this.panel.m_panel.btn_split.setActionCommand("lmenu_close");
		this.panel.m_panel.btn_split.setToolTipText("关闭左边菜单面板");
		this.validate();
		//new SubMenuActionDo(this).btnExamKindAction();
		action.repaintFrame();
		this.validate();
	}
	//==========================
	//关闭左边菜单
	//==========================
	public void closeLeftMenu()
	{
		if(!con.lmenucanclose) return; //如果不许关闭左菜单栏不做任何处理
		int tw = this.panel.m_panel.p_left.getWidth() ; 
		this.panel.m_panel.p_left.setVisible(false);
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.panel.m_panel.scrollPane.setPreferredSize(new Dimension(this.panel.m_panel.scrollPane.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.lmenu_isopen = false;
		this.panel.m_panel.btn_split.setIcon(con.getImgUrl("btn_split_r.png"));
		this.panel.m_panel.btn_split.setActionCommand("lmenu_open");
		this.panel.m_panel.btn_split.setToolTipText("打开左边菜单面板");
		this.validate();
		//new SubMenuActionDo(this).btnExamKindAction();
		action.repaintFrame();
		this.validate();
	}
	//===============================
	//创建菜单函数,
	//===============================
	public JButton getMenuButton(String img,String rimg,String text,String flag)
	{
		String menudir ; //菜单图象文件夹
		if(flag.equals("mainmenu")) menudir = "mainmenu/";//主菜单
		else menudir = "submenu/"; //二级菜单
		ImageIcon btn_img = con.getImgUrl(menudir+img);
		JButton btn = new JButton(btn_img);
		btn.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		btn.setBorder(null);
		btn.setFocusCycleRoot(false);
		btn.setRolloverIcon(con.getImgUrl(menudir+rimg));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setToolTipText(text);
		return btn;//返回按钮
	}
	//===============================
	//返回菜单面板
	//===============================
	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}
	//===============================
	//返回二级菜单面板
	//===============================
	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}
	//===============================
	//设置主菜单,参数:主菜单按钮
	//===============================
	public void setMainMenu(JButton button)
	{
		//添加事件
		button.addActionListener(new ActionDo(this,button));
		button.setActionCommand("lmenu_add");//设置命令
		getMenuPanel().add(button);
	}
	//===============================
	// 组织菜单对应数组,参数:主菜单按钮,添加的菜单按钮,初始命令
	//===============================
	public void addSubMenu(JButton mainbutton,JButton button,String comand)
	{
		//把子菜单添加到菜单向量中
		menulist.add(new MenuButtonBox(mainbutton,button,comand));
	}
	//===============================
	//设置默认菜单,参数:菜单按钮
	//===============================
	public void setDefaultMenu(JButton button)
	{
		getSubMenu(button);
	}
	//===============================
	//显示指定菜单的子菜单
	//===============================
	public void getSubMenu(JButton button)
	{
		//============================
		//显示小菜单
		//============================
		//清除所有
		JPanel submenupanel = this.getSubMenuPanel();
		submenupanel.removeAll();//删除原来的
		this.repaint();//重新绘画
		Vector<MenuButtonBox> vct = this.menulist;//菜单向量
		SubMenuActionDo saction = new SubMenuActionDo(this);
		//根据容器大小算
		for(int i=0;i<vct.size();i++)
		{
			//如果二级菜单属于当前操作按钮
			if(vct.elementAt(i).getMainbutton() == button)
			{
				JButton tbutton = vct.elementAt(i).getButton();
				tbutton.addActionListener(saction);
				tbutton.setActionCommand(vct.elementAt(i).getActioncommand());
				submenupanel.add(tbutton);
			}
		}
	}
	//==================================
	//返回右边框最小宽度
	//==================================
	public int getMinMainBoardWidth()
	{
		int w =this.panel.m_panel.scrollPane.getWidth() - 17;
		if(w<619) w = 619;
		return w;
	}

}
