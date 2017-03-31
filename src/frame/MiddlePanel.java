package frame;

import java.awt.*;

import javax.swing.*;
//===========================================
//  �м����,��������г�������˵�,����,��Ϣ�����
//  MiddlePanel.java
//===========================================
public class MiddlePanel extends JPanel
{
	//���
	public int width;
	public int height, rightwidth;
	//��,�з���,�ұ�����Panel
	JPanel p_left , p_bar , p_right;
	//�ұ��õ��Ĺ���
	JScrollPane scrollPane ; 
	//=====================
	//���ò�����
	ConfigGet con ;
	//=====================
	//�ָ�����ť
	ImageIcon btn_split_img ;
	JButton btn_split ;
	//=====================
	//�������
	MainFrame frame;
	//=====================
	//��ʼ��
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
	//��ʼ����ز���
	//=====================
	private void initPanel()
	{
		//================
		//������
		con = new ConfigGet();
		btn_split_img = con.getImgUrl("btn_split_l.png");//��Ϊ��JButtonǰ��Ҫ��,������ǰ����ʵ����
		int splitbarwidth = con.getSplitBarWidth() ; //�����ļ��ж�ȡ�ָ����Ŀ��//int leftwidth = (width-splitbarwidth)*20/100 ; //�������Ϊ�ܿ��20%
		int leftwidth = con.getLeftMenuWidth() ;
		rightwidth = width - splitbarwidth - leftwidth ; // �����ұ�ʣ����
		//================
		//�����ʽ
		this.setPreferredSize(new Dimension(width,height));
		this.setOpaque(false);
		this.setLayout(con.getFlowLayout(0,0,0));//0��,1 ��,2��
		//================
		//������
		p_left = new JPanel();
		p_left.setPreferredSize(new Dimension(leftwidth,height));
		p_left.setBackground(con.getLeftBgColor());
		p_left.setLayout(con.getFlowLayout(1, 0, 10));//�����,��ֱ���Ϊ20,ˮƽ���Ϊ0
		this.add(p_left);
		//================
		//�м�ָ���
		p_bar = new JPanel();
		p_bar.setPreferredSize(new Dimension(splitbarwidth,height));
		p_bar.setBackground(con.getSplitBgColor());
		p_bar.setLayout(con.getFlowLayout(1, 0, (height - btn_split_img.getIconHeight())/2-30));
		this.add(p_bar);
		//�ָ�������İ�ť
		btn_split = new JButton(btn_split_img);
		btn_split.setPreferredSize(new Dimension(splitbarwidth,btn_split_img.getIconHeight()));
		btn_split.setToolTipText("�ر�");
		btn_split.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_split.setBorder(null);
		btn_split.setFocusCycleRoot(false);
		btn_split.setActionCommand("lmenu_close");
		btn_split.addActionListener(new ActionDo(frame,btn_split));
		btn_split.setOpaque(true);
		p_bar.add(btn_split);
		//================
		//�ұ����
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
		//�ұ߹������������,�Ժ���Ҫ��嶼��ӵ�������
		p_right = new JPanel();
		p_right.setPreferredSize(new Dimension(rightwidth-17,height));
		//p_right.setBackground(con.getMainColor());
		p_right.setLayout(con.getFlowLayout(0, 0, 0));
		scrollPane.setViewportView(p_right);

		
	}
}
