package frame;
//===========================================
//页面重画时调用的函数 RpaintActionDo.java
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
	//窗口大小改变时进行重画函数
	//==================================
	public void repaintFrame() 
	{
		try
		{
			//读取当前存储的命令
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
