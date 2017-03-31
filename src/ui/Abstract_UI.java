package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.mindview.util.*;
import static net.mindview.util.SwingConsole.*;

public class Abstract_UI extends JFrame {
	
	private JTextArea src = new JTextArea(100, 200);
	private JTextArea rst = new JTextArea(30, 200);
	private JButton Start = new JButton("Start");
	private JButton Clear = new JButton("Clear");
	private JButton Cancel = new JButton("Cancel");
	
	public Abstract_UI() {
		
	}

	public static void main(String[] args) {
		run(new Abstract_UI(), 200, 300);
	}

}
