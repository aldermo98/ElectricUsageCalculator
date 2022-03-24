package edu.csus.csc131.euc.output;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import edu.csus.csc131.euc.mainWindow.ShareData;
import edu.csus.csc131.euc.store.DailyData;

public class OutputTabPanel extends JPanel {
	
	private JButton returnBtn;
	private JButton terminateAppBtn;
	private TabPanelActionListener listener;
	public JLabel usageValueLbl;
	public JLabel usageValueLbl2;

	public OutputTabPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		setPreferredSize(dim);
		
		returnBtn = new JButton("Return to Util Calculator");
		terminateAppBtn = new JButton("Exit Application");
		listener = new TabPanelActionListener();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		// Placeholder Information
		String usageText = "44 kWH";
		String costText = "$54.35";
		
		// attempting to print the official data provided.
		if (!ShareData.dailyDataStore.isEmpty()) {
			usageText = String.format("%.5f kWH",ShareData.dailyDataStore.get(0).TotalUsage());
			costText = String.format("$%,.2f",ShareData.dailyDataStore.get(0).TotalCost());
		}
		
		
		JPanel panel = new JPanel(false);
		usageValueLbl = new JLabel(usageText);
		usageValueLbl.setFont(new Font("Courier", Font.BOLD, 35));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panel.add(usageValueLbl);
		
		JComponent usage_kWh = panel;
		tabbedPane.addTab("Usage (in kWh)", usage_kWh);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JPanel panel2 = new JPanel(false);
		usageValueLbl2 = new JLabel(costText);
		usageValueLbl2.setFont(new Font("Courier", Font.BOLD, 35));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panel2.add(usageValueLbl2);
		
		JComponent cost = panel2;
		tabbedPane.addTab("  Cost (in USD)  ", cost);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);	
		
		
	
		
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Electricity Usage");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 50;
		gbc.weighty = 100;
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		add(tabbedPane, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 50;
		gbc.weighty = 20;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.insets = new Insets(3,3,3,3);
		add(returnBtn, gbc);
		returnBtn.addActionListener(listener);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 50;
		gbc.weighty = 20;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.insets = new Insets(3,3,3,3);
		add(terminateAppBtn, gbc);
		terminateAppBtn.addActionListener(listener);
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel usageTotalLbl = new JLabel(text);
        JLabel usageValueLbl = new JLabel(text);

        usageValueLbl.setFont(new Font("Courier", Font.BOLD, 35));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
//        panel.setLayout(new GridLayout(0,1));
//        panel.add(usageTotalLbl);
        panel.add(usageValueLbl);
        return panel;
    }
	
	// All listen events for this panel will be handled here
	private class TabPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked = (JButton) e.getSource();
			OutputWindow ow = new OutputWindow();
			
			Object DailyData;
			if (clicked == returnBtn)
				ow.closeFrame();
			else if (clicked == terminateAppBtn) {
				System.exit(0);
			}
		}
		
	}
}
