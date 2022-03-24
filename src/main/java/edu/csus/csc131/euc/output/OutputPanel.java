package edu.csus.csc131.euc.output;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.csus.csc131.euc.mainWindow.ShareData;
import edu.csus.csc131.euc.store.DailyData;

public class OutputPanel extends JPanel {
	
	private JList dateList;
	private DefaultListModel<String> listModel;
	private String date;
	public DailyData[] days;
	// for updating the values there?
	
	public OutputPanel(OutputTabPanel theOtherPanel) {
		
		listModel = new DefaultListModel<String>();
		date = new Date().toString();
		
		
		// here it is either manually entered or imported through json.
		
		Iterator<DailyData> iterat = ShareData.dailyDataStore.iterator();
		while (iterat.hasNext()) { 
			listModel.addElement(iterat.next().dailyDate.toString());
		}
		
		// Create a new list and place it in a scroll pane
		dateList = new JList<String>(listModel);
		dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dateList.setSelectedIndex(0);
		dateList.setFixedCellHeight(20);
		JScrollPane listScrollpane = new JScrollPane(dateList);
		
		
		
		
		// Set size for JPanel
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		Border innerBorder = BorderFactory.createTitledBorder("Daily Entries");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		// Set layout manager for the JPanel
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		/*
		 	* gridWidth how many columns component will take up
		 	* gridHeight is how many rows the component will take up	
		 	* weight x, weight y is give layout manager a hint on how to adjust widths
		 	* 0 means it's going be fixed
		 	* insets handles padding around components
		 	* anchor is fill components if they do not completely fill a space
		 	* fill how a component can be stretched to fill
		 */
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 50;
		gbc.weighty = 100;
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		add(listScrollpane, gbc);
		
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  
		    	  JList list = (JList) listSelectionEvent.getSource();
		    	  int selection = list.getSelectedIndex();
		    	  ShareData.dailyData = ShareData.dailyDataStore.get(selection);
			    	// attempting to print the official data provided.
		    	  String usageText = "";
		    	  String costText = "";
		    	  
			  		if(ShareData.dailyData != null) {
			  			usageText = String.format("%.5f kWH",ShareData.dailyData.TotalUsage());
			  			costText = String.format("$%,.2f",ShareData.dailyData.TotalCost());
			  		}
			  		
			  		theOtherPanel.usageValueLbl.setText(usageText);
			  		theOtherPanel.usageValueLbl2.setText(costText);
			  		theOtherPanel.repaint();
		      }
		};
		dateList.addListSelectionListener(listSelectionListener);
	}
}
