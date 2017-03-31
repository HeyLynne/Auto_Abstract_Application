package frame;

import java.awt.*;
import java.util.*;

import javax.swing.*;

//===========================================
//软件配置信息 ConfigGet.java
//===========================================
public class ConfigGet 
{
	//===========================
	//一些初始化参数
	//===========================
	String skin_dir , softtitle ;//软件皮肤 | 上面的标题文字
	int width,height;//初始窗体宽高 
	int leftmenuwidth , splitbarwidth ; //主要区域,左二级菜单宽度|左右分格面板的宽度
	boolean iffulltop , ifnormaltop , openfull , lmenucanclose; //窗体最大时总前端显示|正常模式总前端显示|启动时左边菜单是否显示|左边是否可以关闭
	
	//===========================
	//初始化
	ConfigGet()
	{
		skin_dir = "skin_black";//软件皮肤文件夹
		width = 800 ; //窗体宽
		height = 600 ; //窗体高
		iffulltop = true ; //全屏幕是否在最前端显示
		ifnormaltop = false ; //非全屏是否在最前端显示
		leftmenuwidth = 150 ; //左边二级菜单宽度
		splitbarwidth = 6 ; //分格面板宽度
		openfull = false ; //软件启动时就全屏
		softtitle = "文本分析与文摘提取工具";
		lmenucanclose = true; //是否可以关闭左边菜单栏
	}
	//===========================
	//返回皮肤文件夹
	public String getSkinDir()
	{
		return skin_dir;//返回皮肤文件夹
	}
	//=================================
	//重置窗体大小
	//=================================
	public void resetFrameSize(MainFrame f,int w,int h)
	{
		int tw,th,lw,rw; //表示宽高变题,lw:主要区域左边宽,rw表示主要区域右边宽度
		f.setSize(w,h);//窗体大小
		f.panel.toppanel.setPreferredSize(new Dimension(w,f.panel.toppanel.getHeight()));//上面面板大小
		f.panel.menupanel.setPreferredSize(new Dimension(w,f.panel.menupanel.getHeight()));//菜单面板大小
		tw = w-f.panel.btn_close_img.getIconWidth()-f.panel.btn_max_img.getIconWidth()-f.panel.btn_min_img.getIconWidth()-f.panel.img_t_bg_w-f.panel.img_t_mid_w;//设置标题样式
		f.panel.toptitle.setPreferredSize(new Dimension(tw,f.panel.img_t_l.getIconHeight()));//标题栏自动变化
		f.panel.infopanel.setPreferredSize(new Dimension(w,f.panel.img_info.getIconHeight()));//设置菜单下面的信息条自动伸缩
		//主要面板的宽度
		tw = w - 2*f.panel.img_m_w;//除去两边线其它长度
		//主要面板的高度
		th = h - f.panel.img_t_l.getIconHeight() - f.panel.img_menu.getIconHeight() - f.panel.img_info.getIconHeight() - f.panel.img_b_l.getIconHeight();//去除上下全部高度,取剩余高度
		f.panel.m_panel.setPreferredSize(new Dimension(tw,th));//设置主要面板自动大小
		//==================
		//主要面板大小
		lw = this.getLeftMenuWidth() ;//lw = (w - splitbarwidth)*20/100 ; //分配左边为总宽的20%
		f.panel.m_panel.p_left.setPreferredSize(new Dimension(lw,th));//重新分配左边宽度
		if(!f.lmenu_isopen) lw = 0; //隐藏时相当于左边菜单宽度为0,来正确计算右边框架大小
		rw = w - splitbarwidth - lw  ; // 计算右边剩余宽度
		f.panel.m_panel.p_bar.setPreferredSize(new Dimension(splitbarwidth,th));//重置分格条
		f.panel.m_panel.p_bar.setLayout(this.getFlowLayout(1, 0, (th - f.panel.m_panel.btn_split_img.getIconHeight())/2-10)); // 重新设置分格栏上的分格按钮
		f.panel.m_panel.scrollPane.setPreferredSize(new Dimension(rw-8,th));//重新分配右边宽度
		int pw = rw - 8 - 17 ;
		if(pw <619) pw = 619 ;//防止缩小,最小宽度为616
		f.panel.m_panel.p_right.setPreferredSize(new Dimension(pw,f.panel.m_panel.p_right.getHeight()));//重新分配右边宽度
		
		//底部面板
		f.panel.bottompanel.setPreferredSize(new Dimension(w,f.panel.img_b_l.getIconHeight()));
	}
	// ==================================
	// 返回包中指定图片函数
	// ==================================
	public ImageIcon getImgUrl(String img) 
	{
		return new ImageIcon(MainFrame.class.getResource(getSkinDir() + "/" + img));
	}
	// ==================================
	// 返回窗体最小尺寸
	// ==================================
	public Dimension getFrameSmallSize(boolean full)
	{
		if(!full)//不是最大化初始
		{
			return new Dimension(width,height);
		}
		else//全屏
		{
			Toolkit kit = Toolkit.getDefaultToolkit();
			return new Dimension(kit.getScreenSize());
		}
	}
	// ==================================
	// 返回分辨率
	// ==================================
	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}
	// ==================================
	//  最大化时是否显示在前端,如考试系统不许打开其它东西
	// ==================================
	public void fullAllWaysTop(MainFrame frame)
	{
		frame.setAlwaysOnTop(iffulltop);
	}
	// ==================================
	//  正常时是否显示在前端,如考试系统不许打开其它东西
	// ==================================
	public void normalAllWaysTop(MainFrame frame)
	{
		frame.setAlwaysOnTop(ifnormaltop);
	}
	// ==================================
	//  返回布局对象,参数:0左,1中,2右
	// ==================================
	public FlowLayout getFlowLayout(int flag,int hgap,int vgap)
	{
		FlowLayout fl = new FlowLayout();
		fl.setHgap(hgap);
		fl.setVgap(vgap);
		fl.setAlignment(flag);
		return fl;
	}
	//===================================
	//返回左边二级菜单面板的宽度
	//===================================
	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}
	//===================================
	//返回分格面板宽度
	//===================================
	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}
	//===================================
	//返回左边菜单背景
	//===================================
	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}
	//===================================
	//返回分格页面背景色
	//===================================
	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}
	//===================================
	//返回右边区域色彩
	//===================================
	public Color getMainColor()
	{
		return new Color(55,55,55);
	}
	//===================================
	//初始化UI
	//===================================
	public void setFrameUI()
	{
		Font font = new Font("宋体", Font.PLAIN, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		
		Color gray = new Color(240,240,240);
		Color black = new Color(0,0,0);
		
        UIManager.put("ScrollBar.track", black);
        UIManager.put("ScrollBar.trackHighlight",black);
		
        UIManager.put("ScrollBar.background", gray);
        UIManager.put("ScrollBar.shadow", gray);
        UIManager.put("ScrollBar.darkShadow", gray);
        
        UIManager.put("ScrollBar.thumb", gray);
        UIManager.put("ScrollBar.thumbShadow",gray);
        UIManager.put("ScrollBar.thumbDarkShadow",gray);
        UIManager.put("ScrollBar.thumbHighlight", new Color(250,250,250)); 
        UIManager.put("ScrollBar.width", 17);
	}
}
