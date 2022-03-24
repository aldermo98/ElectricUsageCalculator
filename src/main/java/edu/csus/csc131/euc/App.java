package edu.csus.csc131.euc;

import java.awt.EventQueue;

import edu.csus.csc131.euc.mainWindow.MainWindow;

public class App {

	public static void main(String[] args) {
		
		//launch the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow().setVisible(true);		//ONE LINER: create window and make visible
			}
		});
	}
}