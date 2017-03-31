package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

//===========================================
//       ��������� EETSoftFrame.java
//===========================================
class MainFrame extends JFrame
{
	//�������panel
	MainPanel panel;
	boolean isFullScreen = true; // �Ƿ�ȫ��
	boolean lmenu_isopen = true ; // ��߶����˵��Ƿ�չ��
	String img_dir = "skin_black";//Ƥ���ļ���
	Dimension fdm,sdm;//�����С:����|�ֱ���
	ConfigGet con;//������
	Vector<MenuButtonBox> menulist = new Vector<MenuButtonBox>();//�˵�����\
	String reDoCommand ;
	RepaintActionDo action;
	//====================
	//��ʼ��
	MainFrame(String title)
	{
		super(title);
		//��ʼ������
		setWindow(this);
		//���´���
		updateWindow(this);
	}
	//===============================
	//�����ʼ��
	//===============================
	private void setWindow(MainFrame f)
	{
		//=============================
		//��ȡ������
		con = new ConfigGet();
		con.setFrameUI();
		action = new RepaintActionDo(f);
		//����ʱ�Ƿ�ȫ��
		isFullScreen = con.openfull;
		//��ʼ��Ƥ���ļ�
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(isFullScreen);
		sdm = con.getScreenSize();
		f.setSize(fdm);//���ô����С
		addMainPanel(this,fdm.width,fdm.height,isFullScreen);//��������
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);//λ��
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(Entrance.class.getResource(img_dir + "/���籭logo.png")));//ͼ��
		f.setBackground(Color.WHITE);//���ñ���ɫ
	}
	//===============================
	//���´���
	//===============================
	private void updateWindow(JFrame f)
	{
		f.setUndecorated(true);//����û�б߿�
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ϵͳ�˳��¼�
		f.validate();//��Ч
		f.setVisible(true);//�ɼ�
	}
	//================================
	//��������
	//================================
	private void addMainPanel(final MainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		//=====================
		//�������
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);
		//=====================
		//�رհ�ť�¼�
		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new ActionDo(this,panel.btn_close));
		//=====================
		//��С����ť�¼�
		if(iffull)
		{//�����ȫ����ʾ
			panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
			panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
			panel.btn_max.setActionCommand("cmd_normal");
			panel.btn_max.addActionListener(new ActionDo(this,panel.btn_max));
		}
		else
		{//����ģʽ
			panel.btn_max.setActionCommand("cmd_max");
			panel.btn_max.addActionListener(new ActionDo(this,panel.btn_max));
		}
		//=====================
		//��С��
		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new ActionDo(this,panel.btn_min));
		//=====================
		//����¼������
		final Point point = new Point();
		//=====================
		//����panel����¼�,����˫��
		panel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) //���,��¼���λ���Ա��϶�ʱ����
			{
				point.x = e.getX();
				point.y = e.getY();
			}
			public void mouseClicked(MouseEvent e)
			{//���
				if(e.getClickCount() == 2)
				{//˫��
					if(panel.btn_max.getActionCommand().equals("cmd_normal"))//������С
					{
						fdm = con.getFrameSmallSize(false);
						con.resetFrameSize(frame,fdm.width,fdm.height);
						validate(); //�ȸ���,�Ա���水��������
						con.normalAllWaysTop(frame);//�Ƿ���ǰ����ʾ,��Config.java������
						isFullScreen = false;
						//���ð�ť
						panel.btn_max.setIcon(con.getImgUrl("max.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("max_on.png"));
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						panel.btn_max.setActionCommand("cmd_max");
						panel.btn_max.setToolTipText("ȫ��ģʽ");
						action.repaintFrame();
						//new SubMenuActionDo(frame).btnExamKindAction();
						validate();
					}
					else//ȫ��ģʽ
					{
						con.resetFrameSize(frame,sdm.width,sdm.height);
						validate(); //�ȸ���
						con.fullAllWaysTop(frame);//�Ƿ���ǰ����ʾ,��Config.java������
						isFullScreen = true;
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						//���ð�ť
						panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
						panel.btn_max.setActionCommand("cmd_normal");
						panel.btn_max.setToolTipText("����ģʽ");
						action.repaintFrame();
						//new SubMenuActionDo(frame).btnExamKindAction();
						validate();
					}
				}
			}
		});
		//================
		//����Panel�϶��¼�
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
		//  ���½ǵ��϶���ť
		//================================
		panel.bottom_resize.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				//���ݸ����ּ�����С����С�ڶ��ٳߴ�
				int min_w = frame.panel.img_t_bg_w + frame.panel.img_t_mid_w + frame.panel.btn_close_img.getIconWidth() + frame.panel.btn_max_img.getIconWidth()  + frame.panel.btn_min_img.getIconWidth();
				int min_h = frame.panel.img_t_l.getIconHeight() + frame.panel.img_menu.getIconHeight() + frame.panel.img_info.getIconHeight() + frame.panel.img_b_l.getIconHeight() ;
				//����ܳ����ֱ���
				int max_w = con.getScreenSize().width;
				int max_h = con.getScreenSize().height;
				int fw = frame.getWidth(); //�����
				int fh = frame.getHeight(); //�����
				int ex = fw + e.getX() - panel.bottom_resize.getWidth(); //����ƶ���Ŀ�/
				int ey = fh + e.getY() - panel.bottom_resize.getHeight(); // ����ƶ���ĸ�
				if( ex < min_w ) ex = min_w ; //С����С��
				if( ey < min_h ) ey = min_h ; //С�����
				if( ex > max_w ) ex = max_w ; //����Ȳ��ó����ֱ��ʿ�
				if( ey > max_h ) ey = max_h ; //���߶Ȳ��ó����ֱ��ʸ�
				//��߶�û�ﵽ��С
				if(!isFullScreen)
				{
					//�ػ�
					con.resetFrameSize(frame, ex, ey);
					frame.validate();
				}
			}
		});
		//������,�ұ�����ҲҪ��Ӧ���С
		panel.bottom_resize.addMouseListener (new MouseAdapter() 
		{
			public void mouseReleased(MouseEvent e) //���,��¼���λ���Ա��϶�ʱ����
			{
				action.repaintFrame();
				//new SubMenuActionDo(frame).btnExamKindAction();
			}
		});
	}
	//==========================
	//����߲˵�
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
		this.panel.m_panel.btn_split.setToolTipText("�ر���߲˵����");
		this.validate();
		//new SubMenuActionDo(this).btnExamKindAction();
		action.repaintFrame();
		this.validate();
	}
	//==========================
	//�ر���߲˵�
	//==========================
	public void closeLeftMenu()
	{
		if(!con.lmenucanclose) return; //�������ر���˵��������κδ���
		int tw = this.panel.m_panel.p_left.getWidth() ; 
		this.panel.m_panel.p_left.setVisible(false);
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.panel.m_panel.scrollPane.setPreferredSize(new Dimension(this.panel.m_panel.scrollPane.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.lmenu_isopen = false;
		this.panel.m_panel.btn_split.setIcon(con.getImgUrl("btn_split_r.png"));
		this.panel.m_panel.btn_split.setActionCommand("lmenu_open");
		this.panel.m_panel.btn_split.setToolTipText("����߲˵����");
		this.validate();
		//new SubMenuActionDo(this).btnExamKindAction();
		action.repaintFrame();
		this.validate();
	}
	//===============================
	//�����˵�����,
	//===============================
	public JButton getMenuButton(String img,String rimg,String text,String flag)
	{
		String menudir ; //�˵�ͼ���ļ���
		if(flag.equals("mainmenu")) menudir = "mainmenu/";//���˵�
		else menudir = "submenu/"; //�����˵�
		ImageIcon btn_img = con.getImgUrl(menudir+img);
		JButton btn = new JButton(btn_img);
		btn.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		btn.setBorder(null);
		btn.setFocusCycleRoot(false);
		btn.setRolloverIcon(con.getImgUrl(menudir+rimg));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setToolTipText(text);
		return btn;//���ذ�ť
	}
	//===============================
	//���ز˵����
	//===============================
	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}
	//===============================
	//���ض����˵����
	//===============================
	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}
	//===============================
	//�������˵�,����:���˵���ť
	//===============================
	public void setMainMenu(JButton button)
	{
		//����¼�
		button.addActionListener(new ActionDo(this,button));
		button.setActionCommand("lmenu_add");//��������
		getMenuPanel().add(button);
	}
	//===============================
	// ��֯�˵���Ӧ����,����:���˵���ť,��ӵĲ˵���ť,��ʼ����
	//===============================
	public void addSubMenu(JButton mainbutton,JButton button,String comand)
	{
		//���Ӳ˵���ӵ��˵�������
		menulist.add(new MenuButtonBox(mainbutton,button,comand));
	}
	//===============================
	//����Ĭ�ϲ˵�,����:�˵���ť
	//===============================
	public void setDefaultMenu(JButton button)
	{
		getSubMenu(button);
	}
	//===============================
	//��ʾָ���˵����Ӳ˵�
	//===============================
	public void getSubMenu(JButton button)
	{
		//============================
		//��ʾС�˵�
		//============================
		//�������
		JPanel submenupanel = this.getSubMenuPanel();
		submenupanel.removeAll();//ɾ��ԭ����
		this.repaint();//���»滭
		Vector<MenuButtonBox> vct = this.menulist;//�˵�����
		SubMenuActionDo saction = new SubMenuActionDo(this);
		//����������С��
		for(int i=0;i<vct.size();i++)
		{
			//��������˵����ڵ�ǰ������ť
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
	//�����ұ߿���С���
	//==================================
	public int getMinMainBoardWidth()
	{
		int w =this.panel.m_panel.scrollPane.getWidth() - 17;
		if(w<619) w = 619;
		return w;
	}

}
