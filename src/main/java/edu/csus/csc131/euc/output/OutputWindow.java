//package gbolanos.com;
package edu.csus.csc131.euc.output;	//Alder: i dont have your gbolanos.com package so i need this package so i can use your file. 

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class OutputWindow {

	private static JFrame frame = new JFrame();
	public OutputPanel panelWest;
	public OutputTabPanel panelEast;
	
	public JFrame getFrame() {	//Alder: i also need this here so that i can open your window from MainWindow.java 
		return this.frame;
	}
	
	public void closeFrame() {
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
	public OutputWindow() {
		frame.setTitle("Display Calculations");
		ImageIcon appIcon = new ImageIcon("images/appIcon.png");
		frame.setIconImage(appIcon.getImage());
		frame.setLayout(new BorderLayout());
		
		panelEast = new OutputTabPanel();
		panelWest = new OutputPanel(panelEast);
		
		
		// Add components to the content of the JFrame
		frame.add(panelWest, BorderLayout.WEST);
		frame.add(panelEast, BorderLayout.EAST);
		
		frame.setSize(610, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	/* changed to DISPOSE_ON_CLOSE so that only this window closes 
																		instead of entire application */
		
	}
}