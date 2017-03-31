package frame;

import java.awt.*;

import javax.swing.*;
//===========================================
//  中间面板,软件窗体中除了上面菜单,标题,信息面板外
//  MiddlePanel.java
//===========================================
public class MiddlePanel extends JPanel
{
	//宽高
	public int width;
	public int height, rightwidth;
	//左,中分线,右边三个Panel
	JPanel p_left , p_bar , p_right;
	//右边用到的滚动
	JScrollPane scrollPane ; 
	//=====================
	//配置参数类
	ConfigGet con ;
	//=====================
	//分格条按钮
	ImageIcon btn_split_img ;
	JButton btn_split ;
	//=====================
	//窗体参数
	MainFrame frame;
	//=====================
	//初始化
	//=====================
	MiddlePanel(MainFrame frame,int width,int height)
	{
		//=============================

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
	}
	//=====================
	//初始化相关参数
	//=====================
	private void initPanel()
	{
		//================
		//配置类
		con = new ConfigGet();
		btn_split_img = con.getImgUrl("btn_split_l.png");//因为在JButton前就要用,所以在前面先实例化
		int splitbarwidth = con.getSplitBarWidth() ; //配置文件中读取分格条的宽度//int leftwidth = (width-splitbarwidth)*20/100 ; //分配左边为总宽的20%
		int leftwidth = con.getLeftMenuWidth() ;
		rightwidth = width - splitbarwidth - leftwidth ; // 计算右边剩余宽度
		//================
		//面板样式
		this.setPreferredSize(new Dimension(width,height));
		this.setOpaque(false);
		this.setLayout(con.getFlowLayout(0,0,0));//0左,1 中,2右
		//================
		//左边面板
		p_left = new JPanel();
		p_left.setPreferredSize(new Dimension(leftwidth,height));
		p_left.setBackground(con.getLeftBgColor());
		p_left.setLayout(con.getFlowLayout(1, 0, 10));//左对齐,垂直间距为20,水平间距为0
		this.add(p_left);
		//================
		//中间分格线
		p_bar = new JPanel();
		p_bar.setPreferredSize(new Dimension(splitbarwidth,height));
		p_bar.setBackground(con.getSplitBgColor());
		p_bar.setLayout(con.getFlowLayout(1, 0, (height - btn_split_img.getIconHeight())/2-30));
		this.add(p_bar);
		//分格线上面的按钮
		btn_split = new JButton(btn_split_img);
		btn_split.setPreferredSize(new Dimension(splitbarwidth,btn_split_img.getIconHeight()));
		btn_split.setToolTipText("关闭");
		btn_split.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_split.setBorder(null);
		btn_split.setFocusCycleRoot(false);
		btn_split.setActionCommand("lmenu_close");
		btn_split.addActionListener(new ActionDo(frame,btn_split));
		btn_split.setOpaque(true);
		p_bar.add(btn_split);
		//================
		//右边面板
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(rightwidth,height));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setBlockIncrement(100);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.getHorizontalScrollBar().setBlockIncrement(100);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
		

		
		//=================
		//右边滚动面板里的面板,以后主要面板都添加到这个面板
		p_right = new JPanel();
		p_right.setPreferredSize(new Dimension(rightwidth-17,height));
		//p_right.setBackground(con.getMainColor());
		p_right.setLayout(con.getFlowLayout(0, 0, 0));
		scrollPane.setViewportView(p_right);

		
	}
}
