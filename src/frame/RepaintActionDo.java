package frame;
//===========================================
//ҳ���ػ�ʱ���õĺ��� RpaintActionDo.java
//===========================================
public class RepaintActionDo 
{
	String action ;
	MainFrame frame ; 
	RepaintActionDo(MainFrame frame)
	{
		this.frame = frame;
		action = frame.reDoCommand ; 
	}
	//==================================
	//���ڴ�С�ı�ʱ�����ػ�����
	//==================================
	public void repaintFrame() 
	{
		try
		{
			//��ȡ��ǰ�洢������
			SubMenuActionDo action = new SubMenuActionDo(frame);
			if(frame.reDoCommand.equals("paragraph"))
			{
				action.paragraphAction();
			}
			else if(frame.reDoCommand.equals("text"))
			{
				action.textAction();
			}
		}catch(Exception e){}
	}
}
