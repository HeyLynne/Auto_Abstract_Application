package frame;

import javax.swing.*;
//===========================================
//²Ëµ¥ÈÝÆ÷
//MiddlePanel.java
//===========================================
public class MenuButtonBox 
{
	JButton mainbutton;
	JButton button;
	String actioncommand;

	MenuButtonBox(JButton m,JButton b,String a)
	{
		this.mainbutton = m;
		this.button = b; // °´Å¥
		this.actioncommand = a; //ÃüÁî
	}
	public JButton getButton() 
	{
		return button;
	}
	public void setButton(JButton button) 
	{
		this.button = button;
	}
	public String getActioncommand() 
	{
		return actioncommand;
	}
	public void setActioncommand(String actioncomand) 
	{
		this.actioncommand = actioncomand;
	}
	public JButton getMainbutton() 
	{
		return mainbutton;
	}
	public void setMainbutton(JButton mainbutton) 
	{
		this.mainbutton = mainbutton;
	}
}
