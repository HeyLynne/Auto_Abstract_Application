package frame;

import java.awt.*;

import javax.swing.*;

//===========================================
//   ��Ҫ���  MainPanel.java
//===========================================
class MainPanel extends JPanel 
{
	//======================
	// �ϱ߿�ͼƬ�����:��߿�|��뱳��|�м����|�ұ߱���| �ұ߱߿�
	ImageIcon img_t_l , img_t_bg , img_t_mid , img_t_rbg ,img_t_r;
	int img_t_l_w , img_t_bg_w , img_t_mid_w , img_t_rbg_w , img_t_r_w;
	
	// =====================
	// ���ұ���
	ImageIcon img_m;
	int img_m_w;
	
	// =====================
	// ��β��:��߽�|����|�ұ߽�
	ImageIcon img_b_l , img_b_bg , img_b_r ;
	int img_b_l_w , img_b_bg_w , img_b_r_w;
	
	//======================
	//�˵�����Ϣ������ͼƬ:�˵�����ͼƬ|�˵�������Ϣ������
	ImageIcon img_menu , img_info;
	
	// =====================
	//���
	int width,height;
	
	//======================
	//�õ�����
	//======================
	ConfigGet con = new ConfigGet();
	
	//======================
	//�м���Ҫ���
	//======================
	MiddlePanel m_panel;
	
	//======================
	//��Ҫ���
	//======================
	JPanel toppanel;//��һ��
	JLabel toptitle;//����������
	//Ҫ�õ��İ�ťͼƬ:��С��ͼƬ|���ͼƬ|�ر�ͼƬ,�����Ƕ�Ӧ�İ�ť
	ImageIcon btn_min_img,btn_max_img,btn_close_img;//
	JButton btn_min,btn_max,btn_close;//Ҫ�õ��İ�ť
	
	//======================
	//�˵����
	//======================
	JPanel menupanel;

	//======================
	//��Ϣ���
	//======================
	JPanel infopanel;
	
	//======================
	//�ײ����
	//======================
	JPanel bottompanel ; 
	ImageIcon bottom_resize_img;
	JButton bottom_resize;
	
	//======================
	//  ���ڵĴ���
	MainFrame frame;
	//======================
	//��ʼ��,����:���
	MainPanel(MainFrame frame,int w,int h) 
	{
		//��ʼ�����
		this.width = w;
		this.height = h;
		this.frame = frame;
		this.setOpaque(true);
		initSkinImg(); // ��ʼ��Ƥ����Ϣ
		this.setLayout(con.getFlowLayout(1,0,0)); //���ж���
		//=============================
		//��ʼ����ť
		setTop();
		//=============================
		//��ʼ���˵�
		setMenu();
		//��ʼ���˵��������Ϣ��
		setInfoPanel();
		//=============================
		//�����Ҫ���
		setMiddlePanel();
		//=============================
		//��ӵײ����
		setBottomPanel();
	}
	//===================================
	//   Ϊ�����Ӵ�С�رռ���չ��ť
	//===================================
	public void setTop()
	{
		//================================================
		//���һ�������һ��panel,�������С����ť
		//================================================
		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_l.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); // �Ҷ���
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(toppanel);
		//=================================
		//��������ļ���ʾ����
		toptitle = new JLabel();
		//���еĿ�ȼ������
		int w = width-btn_close_img.getIconWidth()-btn_max_img.getIconWidth()-btn_min_img.getIconWidth()-img_t_l_w-img_t_bg_w-img_t_mid_w;
		toptitle.setPreferredSize(new Dimension(w,img_t_l.getIconHeight()));
		toptitle.setForeground(Color.white);
		toptitle.setFont(new Font("����",0,12));
		toptitle.setText(con.softtitle);
		toppanel.add(toptitle);
		//=================================
		//��С����ť;
		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("��С��ģʽ");
		toppanel.add(btn_min);
		//=================================
		//��󻯰�ť
		btn_max = new JButton(btn_max_img);
		btn_max.setPreferredSize(new Dimension(btn_max_img.getIconWidth(),btn_max_img.getIconHeight()));
		btn_max.setBorder(null);
		btn_max.setFocusCycleRoot(false);
		btn_max.setRolloverIcon(con.getImgUrl("max_on.png"));
		btn_max.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_max.setToolTipText("ȫ��ģʽ");
		toppanel.add(btn_max);
		//=================================
		//�رմ��ڰ�ť
		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("�رմ���");
		toppanel.add(btn_close);
	}
	//===================================
	//   ��ʼ���˵����
	//===================================
	public void setMenu()
	{
		//================================================
		//�½��˵���岢���
		//================================================
		menupanel = new JPanel();
		menupanel.setPreferredSize(new Dimension(width,img_menu.getIconHeight()));
		menupanel.setOpaque(false);
		menupanel.setLayout(con.getFlowLayout(0,0,0)); // �����
		this.add(menupanel);
	}
	//===================================
	//��Ӳ˵� 
	//===================================
	public void addMenu(JButton button)
	{
		menupanel.add(button);
	}
	//===================================
	//�˵��������Ϣ��
	//===================================
	public void setInfoPanel()
	{
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(width,img_info.getIconHeight()));
		infopanel.setOpaque(false);
		infopanel.setLayout(con.getFlowLayout(0,0,0)); //�����
		this.add(infopanel);
		
		//=====================
		//����������������
	}
	//===================================
	//�����Ҫ���
	//===================================
	public void setMiddlePanel()
	{
		int w = width - 2*this.img_m_w;
		//�õ�ʣ��߶�
		int h = height - this.img_t_l.getIconHeight() - this.img_menu.getIconHeight() - this.img_info.getIconHeight() - this.img_b_l.getIconHeight();
		m_panel = new MiddlePanel(frame,w,h);
		this.add(m_panel);
	}
	//===================================
	//  �ײ����
	//===================================
	public void setBottomPanel()
	{
		bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(width,this.img_b_l.getIconHeight()));
		bottompanel.setLayout(con.getFlowLayout(2,0,0));//�Ҷ���
		bottompanel.setOpaque(false);
		this.add(bottompanel);
		//=====================
		//������½ǿ���������С��ͼ��
		bottom_resize_img = con.getImgUrl("bottom_resize.png"); //���½ǵ��ܱ仯�����С�İ�ťͼ��
		bottom_resize = new JButton(bottom_resize_img);
		bottom_resize.setPreferredSize(new Dimension(bottom_resize_img.getIconWidth(),bottom_resize_img.getIconHeight()));
		bottom_resize.setBorder(null);
		bottom_resize.setFocusCycleRoot(false);
		bottom_resize.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		bottompanel.add(bottom_resize);
	}
	//===================================
	//��ʼ��Ƥ��ͼƬ����
	//===================================
	private void initSkinImg()
	{
		//========================================
		//���ͷ��Ӧ����
		//========================================
		img_t_l = con.getImgUrl("exam_h_l.png");// ���
		img_t_l_w = img_t_l.getIconWidth();
		img_t_bg = con.getImgUrl("exam_h_lc.png");// ��߱���
		img_t_bg_w = 120;
		img_t_mid = con.getImgUrl("exam_h_tag.png");// ����ͼƬ
		img_t_mid_w = img_t_mid.getIconWidth();
		img_t_rbg = con.getImgUrl("exam_h_rc.png");// �ұ߱���
		img_t_rbg_w = img_t_rbg.getIconWidth();
		img_t_r = con.getImgUrl("exam_h_r.png");// �ұ߱߿�
		img_t_r_w = img_t_r.getIconWidth();
		//============
		//��ť��
		btn_min_img= con.getImgUrl("min.png");
		btn_max_img= con.getImgUrl("max.png");
		btn_close_img= con.getImgUrl("close.png");
		
		
		//========================================
		//�˵���
		//========================================
		img_menu = con.getImgUrl("menu_bg.png");//�˵�����
		img_info = con.getImgUrl("info_bg.png");//��Ϣ����
		
		//========================================
		// ���ұ���
		//========================================
		img_m = con.getImgUrl("exam_b_line.png");//����
		img_m_w = img_m.getIconWidth();//����

		//========================================
		// β��
		//========================================
		img_b_l = con.getImgUrl("exam_b_l.png");//��߽�
		img_b_l_w = img_b_l.getIconWidth();
		img_b_bg = con.getImgUrl("exam_b_bg.png");// �м�
		img_b_bg_w = img_b_bg.getIconWidth();
		img_b_r = con.getImgUrl("exam_b_r.png");//�ұ߽�
		img_b_r_w = img_b_r.getIconWidth();
	}
	// ==================================
	// �����Ƥ������
	// ==================================
	public void paintComponent(Graphics g) 
	{
		//===========================================
		//��ʼ��Ƥ��
		//===========================================
		
		//==========================
		// �����ͷ
		g.drawImage(img_t_l.getImage(), 0, 0, this);
		// ����߱���
		g.drawImage(img_t_bg.getImage(), 0, 0, img_t_bg_w, img_t_bg.getIconHeight(), this);
		// ���ͼ��
		g.drawImage(con.getImgUrl("���籭logo.png").getImage(), 5, 3, 16,16, this);
		// ��������
		g.setColor(Color.white);
		g.drawString("��24����籭", 30, 15);
		g.setFont(new Font("����", 0, 9));
		// ��������,��Ϊ��߱���������ֱ������,�������ﲻ��Ҫ����߱߿��ȼ���
		g.drawImage(img_t_mid.getImage(), img_t_bg_w, 0, img_t_mid_w, img_t_bg.getIconHeight(), this);
		// �ұ߱���
		// ��ʼ��
		int spot = img_t_bg_w + img_t_mid_w;
		int epot = this.getWidth() - img_t_r_w;
		g.drawImage(img_t_rbg.getImage(), spot, 0, epot, img_t_rbg.getIconHeight(), this);
		// ���ұ�,ʹ��������ͼ����յ�
		g.drawImage(img_t_r.getImage(), epot, 0, this);
		
		//=============================
		//���˵�����
		g.drawImage(img_menu.getImage(),0,img_t_l.getIconHeight(),this.getWidth(),img_menu.getIconHeight(),this);
		//����Ϣ��
		g.drawImage(img_info.getImage(),0,img_t_l.getIconHeight()+img_menu.getIconHeight(),this.getWidth(),img_info.getIconHeight(),this);
		
		// ============================
		// �����ұ���
		g.drawImage(img_m.getImage(), 0, img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);
		g.drawImage(img_m.getImage(), this.getWidth() - img_m.getIconWidth(),img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);

		// ============================
		// β��
		g.drawImage(img_b_l.getImage(), 0, this.getHeight()- img_b_l.getIconHeight(), this);
		g.drawImage(img_b_bg.getImage(), img_b_l_w, this.getHeight()- img_b_bg.getIconHeight(), this.getWidth()- img_b_r.getIconWidth(), img_b_r.getIconHeight(), this);
		g.drawImage(img_b_r.getImage(), this.getWidth()- img_b_r.getIconWidth(), this.getHeight()- img_b_r.getIconHeight(), this);		
	}
}
