package edu.csus.csc131.euc.mainWindow;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Cursor;

public class HelpWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel hyperlink;
	private JTextPane txtHelpInfo;

	public HelpWindow() {
		setTitle("Help");
		ImageIcon appIcon = new ImageIcon("images/appIcon.png");
		setIconImage(appIcon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//window and application ends by clicking the window's 'X' close button
		setBounds(500, 60, 345, 625);	//moves and resizes window; setBounds(x-coordinate, y-coordinate, width, height)
		setResizable(false);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);		//set 'panel' to hold all the components for JFrame 
		panel.setLayout(null);
	//Add stuff/////////////////////////////////////////////////////////////////////////////////////////////////////////
		addHelpInfo();
	}
	
	public void addHelpInfo() {
		hyperlink = new JLabel("<HTML><U>here</U></HTML>");		//enclosed in html tags to get the text underlined
		hyperlink.setFont(new Font("Dialog", Font.PLAIN, 12));
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.setBounds(47, 36, 30, 14);
		panel.add(hyperlink);
		
		txtHelpInfo = new JTextPane();
		txtHelpInfo.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtHelpInfo.setBackground(UIManager.getColor("Panel.background"));
		txtHelpInfo.setText("Electricity usage and cost will be calculated using user-defined rates. If a rate is not defined, default rates, defined         , will be used. Peak, mid-peak, and off-peak rates can be defined for the summer and non-summer seasons by following the steps below.\r\n\r\n    Select Rate:\r\n      Select a season whose rate you want to customize for a \r\n      specific period.\r\n\r\n    Set Rate: \r\n      Select a period, from the list, whose rate you wish to \r\n      customize for the selected season.\r\n\r\n      Select the specified times you wish to included in the rate\r\n      plan. You can select a single time or multiple times. To \r\n      select mulitple times, hold 'ctrl' (Windows) or \r\n      'command' (mac) and click the times you want to\r\n      include. You can also select an interval by selecting a\r\n      start value, hold 'Shift', then select the last value.  \r\n\r\n      Type in the rate, for the plan being defined, into the text \r\n      box, then click 'Set' to confirm the plan.\r\n\r\n    Enter Electricity Usage:\r\n      The option is given to enter the electricity usage \r\n      manually, or by importing a JSON file.  \r\n\r\n    Calculate Electricity Usage:\r\n      The data will be calculated and shown in a seperate \r\n      window, once the 'Display results' button is clicked.\r\n\r\nHow to view customized rate plan: \r\n    Once a rate plan has been set, by hitting the 'Set' \r\n    button, one can then view said rate plan by selecting \r\n    the season and period relative to the desired \r\n    customized rate plan to view. ");
		txtHelpInfo.setBounds(0,  0,  340,  580);
		panel.add(txtHelpInfo);
		txtHelpInfo.setEditable(false);
	//Add functionality to hyperlink/////////////////////////////////////////////////////////////////////
		hyperlink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new DisplayDefRatePlan().setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HelpWindow().setVisible(true);
			}
		});
	}
	
//Getters for test//////////////////////////////////////////////////////////////////////////////////////
	public JLabel getHyperlink() {
		return hyperlink;
	}
}
