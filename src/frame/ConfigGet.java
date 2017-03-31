package frame;

import java.awt.*;
import java.util.*;

import javax.swing.*;

//===========================================
//���������Ϣ ConfigGet.java
//===========================================
public class ConfigGet 
{
	//===========================
	//һЩ��ʼ������
	//===========================
	String skin_dir , softtitle ;//���Ƥ�� | ����ı�������
	int width,height;//��ʼ������ 
	int leftmenuwidth , splitbarwidth ; //��Ҫ����,������˵����|���ҷָ����Ŀ��
	boolean iffulltop , ifnormaltop , openfull , lmenucanclose; //�������ʱ��ǰ����ʾ|����ģʽ��ǰ����ʾ|����ʱ��߲˵��Ƿ���ʾ|����Ƿ���Թر�
	
	//===========================
	//��ʼ��
	ConfigGet()
	{
		skin_dir = "skin_black";//���Ƥ���ļ���
		width = 800 ; //�����
		height = 600 ; //�����
		iffulltop = true ; //ȫ��Ļ�Ƿ�����ǰ����ʾ
		ifnormaltop = false ; //��ȫ���Ƿ�����ǰ����ʾ
		leftmenuwidth = 150 ; //��߶����˵����
		splitbarwidth = 6 ; //�ָ������
		openfull = false ; //�������ʱ��ȫ��
		softtitle = "�ı���������ժ��ȡ����";
		lmenucanclose = true; //�Ƿ���Թر���߲˵���
	}
	//===========================
	//����Ƥ���ļ���
	public String getSkinDir()
	{
		return skin_dir;//����Ƥ���ļ���
	}
	//=================================
	//���ô����С
	//=================================
	public void resetFrameSize(MainFrame f,int w,int h)
	{
		int tw,th,lw,rw; //��ʾ��߱���,lw:��Ҫ������߿�,rw��ʾ��Ҫ�����ұ߿��
		f.setSize(w,h);//�����С
		f.panel.toppanel.setPreferredSize(new Dimension(w,f.panel.toppanel.getHeight()));//��������С
		f.panel.menupanel.setPreferredSize(new Dimension(w,f.panel.menupanel.getHeight()));//�˵�����С
		tw = w-f.panel.btn_close_img.getIconWidth()-f.panel.btn_max_img.getIconWidth()-f.panel.btn_min_img.getIconWidth()-f.panel.img_t_bg_w-f.panel.img_t_mid_w;//���ñ�����ʽ
		f.panel.toptitle.setPreferredSize(new Dimension(tw,f.panel.img_t_l.getIconHeight()));//�������Զ��仯
		f.panel.infopanel.setPreferredSize(new Dimension(w,f.panel.img_info.getIconHeight()));//���ò˵��������Ϣ���Զ�����
		//��Ҫ���Ŀ��
		tw = w - 2*f.panel.img_m_w;//��ȥ��������������
		//��Ҫ���ĸ߶�
		th = h - f.panel.img_t_l.getIconHeight() - f.panel.img_menu.getIconHeight() - f.panel.img_info.getIconHeight() - f.panel.img_b_l.getIconHeight();//ȥ������ȫ���߶�,ȡʣ��߶�
		f.panel.m_panel.setPreferredSize(new Dimension(tw,th));//������Ҫ����Զ���С
		//==================
		//��Ҫ����С
		lw = this.getLeftMenuWidth() ;//lw = (w - splitbarwidth)*20/100 ; //�������Ϊ�ܿ��20%
		f.panel.m_panel.p_left.setPreferredSize(new Dimension(lw,th));//���·�����߿��
		if(!f.lmenu_isopen) lw = 0; //����ʱ�൱����߲˵����Ϊ0,����ȷ�����ұ߿�ܴ�С
		rw = w - splitbarwidth - lw  ; // �����ұ�ʣ����
		f.panel.m_panel.p_bar.setPreferredSize(new Dimension(splitbarwidth,th));//���÷ָ���
		f.panel.m_panel.p_bar.setLayout(this.getFlowLayout(1, 0, (th - f.panel.m_panel.btn_split_img.getIconHeight())/2-10)); // �������÷ָ����ϵķָ�ť
		f.panel.m_panel.scrollPane.setPreferredSize(new Dimension(rw-8,th));//���·����ұ߿��
		int pw = rw - 8 - 17 ;
		if(pw <619) pw = 619 ;//��ֹ��С,��С���Ϊ616
		f.panel.m_panel.p_right.setPreferredSize(new Dimension(pw,f.panel.m_panel.p_right.getHeight()));//���·����ұ߿��
		
		//�ײ����
		f.panel.bottompanel.setPreferredSize(new Dimension(w,f.panel.img_b_l.getIconHeight()));
	}
	// ==================================
	// ���ذ���ָ��ͼƬ����
	// ==================================
	public ImageIcon getImgUrl(String img) 
	{
		return new ImageIcon(MainFrame.class.getResource(getSkinDir() + "/" + img));
	}
	// ==================================
	// ���ش�����С�ߴ�
	// ==================================
	public Dimension getFrameSmallSize(boolean full)
	{
		if(!full)//������󻯳�ʼ
		{
			return new Dimension(width,height);
		}
		else//ȫ��
		{
			Toolkit kit = Toolkit.getDefaultToolkit();
			return new Dimension(kit.getScreenSize());
		}
	}
	// ==================================
	// ���طֱ���
	// ==================================
	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}
	// ==================================
	//  ���ʱ�Ƿ���ʾ��ǰ��,�翼��ϵͳ�������������
	// ==================================
	public void fullAllWaysTop(MainFrame frame)
	{
		frame.setAlwaysOnTop(iffulltop);
	}
	// ==================================
	//  ����ʱ�Ƿ���ʾ��ǰ��,�翼��ϵͳ�������������
	// ==================================
	public void normalAllWaysTop(MainFrame frame)
	{
		frame.setAlwaysOnTop(ifnormaltop);
	}
	// ==================================
	//  ���ز��ֶ���,����:0��,1��,2��
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
	//������߶����˵����Ŀ��
	//===================================
	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}
	//===================================
	//���طָ������
	//===================================
	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}
	//===================================
	//������߲˵�����
	//===================================
	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}
	//===================================
	//���طָ�ҳ�汳��ɫ
	//===================================
	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}
	//===================================
	//�����ұ�����ɫ��
	//===================================
	public Color getMainColor()
	{
		return new Color(55,55,55);
	}
	//===================================
	//��ʼ��UI
	//===================================
	public void setFrameUI()
	{
		Font font = new Font("����", Font.PLAIN, 12);
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
