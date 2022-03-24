package edu.csus.csc131.euc.mainWindow;

import edu.csus.csc131.euc.json.JSONdata;
import edu.csus.csc131.euc.output.OutputWindow;
import edu.csus.csc131.euc.store.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import javax.swing.border.LineBorder;

public class MainWindow extends JFrame implements ActionListener, ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	//Initialize window components
	private JPanel panel;	//panel to hold components
	private JLabel lbl_helpIcon, lbl_selectRate, lbl_setRate, lbl_selectPeriod, 
				   lbl_selectTimes, lbl_enterRate, lbl_enterElecUse, lbl_elecUseCalcs, lbl_split;
	private JRadioButton rdbtnSum, 				//to select 'Summer' rate
						 rdbtnNSum;				//to select 'Non-summer' rate
	private JList<String> listPeriod; 				//to display different period options: peak, mid-peak, off-peak
	private JList<String> listTimes;				//to display hours
	private JTextField txtFieldFileLoc,			//used to enter file location
					   txtFieldRate;			//used to enter rate 
	private JButton btnSet, 				//sets rate plan
					btnBrowse, 				//opens file dialog box
					btnManualImport, 		//opens window for user electricity usage input 
					btnImport, 				//imports file selected from 'Browse' button
					btnDisplayResults;				//opens usage-calculation display window 
	private JFileChooser fileChooser;	//displays a file browser for your PC
	private ButtonGroup rdbtnSeasons; 	//used for containing season options in a group
	private ShareData data = new ShareData();		//used to access the rate plans

	//Create the frame
	public MainWindow() {
	//Set window attributes/////////////////////////////////////////////////////////////////////////////////////
		setTitle("Electricity Usage Calculator");
		ImageIcon appIcon = new ImageIcon("images/appIcon.png");
		setIconImage(appIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//window and application ends by clicking the window's 'X' close button
		setBounds(500, 200, 340, 320);	//moves and resizes window; setBounds(x-coordinate, y-coordinate, width, height)
		setResizable(false);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);		//set 'panel' to hold all the components for JFrame 
		panel.setLayout(null);		//null = no layout manager used. other options include gridBagLayout, borderLayout, and more
	//Add components to window////////////////////////////////////////////////////////////////////////////////////////
		addLabels();
		addSeasonOptions();
		addRateCustomization();
		addElecUsageImport();
		addDisplayResultsBtn();
	}

	private void addLabels() {
	//Create and configure labels////////////////////////////////////////////////////////////////////
		lbl_helpIcon = new JLabel();
		lbl_helpIcon.setBounds(299, 0, 25, 25);
		ImageIcon helpIcon = new ImageIcon(new ImageIcon("images/helpIcon.png").getImage().getScaledInstance(lbl_helpIcon.getWidth(), lbl_helpIcon.getHeight(), Image.SCALE_SMOOTH));
		lbl_helpIcon.setIcon(helpIcon);
		lbl_helpIcon.setToolTipText("This program calculates and displays the given electricity usage and cost. Click this icon to learn more");
		
		lbl_selectRate = new JLabel("Select Rate");
		lbl_selectRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_selectRate.setBounds(10, 11, 60, 14);
		
		lbl_setRate = new JLabel("Set Rate");
		lbl_setRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_setRate.setBounds(10, 59, 48, 14);
		
		lbl_selectPeriod = new JLabel("Select period");
		lbl_selectPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_selectPeriod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_selectPeriod.setBounds(41, 78, 64, 14);
		
		lbl_selectTimes = new JLabel("Select times");
		lbl_selectTimes.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_selectTimes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_selectTimes.setBounds(113, 78, 85, 14);
		
		lbl_enterRate = new JLabel("Enter rate");
		lbl_enterRate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_enterRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_enterRate.setBounds(208, 78, 72, 14);
		
		lbl_enterElecUse = new JLabel("Enter Electricity Usage");
		lbl_enterElecUse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_enterElecUse.setBounds(10, 155, 115, 14);
		
		lbl_elecUseCalcs = new JLabel("Electricity Usage Calculations");
		lbl_elecUseCalcs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_elecUseCalcs.setBounds(10, 253, 139, 14);
		
		lbl_split = new JLabel("_____________________________________________________________________________"); 	//split the file import and manual import
		lbl_split.setFont(new Font("Tahoma", Font.PLAIN, 5));
		lbl_split.setBounds(48, 199, 232, 10);
		panel.add(lbl_split);
	//Add labels to window////////////////////////////////////////////////////////////////////////
		panel.add(lbl_helpIcon);
		panel.add(lbl_selectRate);
		panel.add(lbl_setRate);
		panel.add(lbl_selectPeriod);
		panel.add(lbl_selectTimes);
		panel.add(lbl_enterRate);
		panel.add(lbl_enterElecUse);
		panel.add(lbl_elecUseCalcs);
	//Add functionality to help icon/////////////////////////////////////////////////////////////////////////////////
		lbl_helpIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), e.paramString()));
			}
		});
	}

	private void addSeasonOptions() {
	//Create and configure radio buttons/////////////////////////////////////////////////////////////////////////////
		rdbtnSeasons = new ButtonGroup();		//adding buttons in a group constrains them to only one button selected at a time
			rdbtnSum = new JRadioButton("Summer");
			rdbtnSum.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnSum.setBounds(85, 26, 70, 23);
			
			rdbtnNSum = new JRadioButton("Non-summer");		//use custom non-summer rates
			rdbtnNSum.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnNSum.setBounds(155, 26, 90, 23);
		rdbtnSeasons.add(getRdbtnSum());
		rdbtnSeasons.add(rdbtnNSum);
	//Add functionality to radio-buttons////////////////////////////////////////////////////////////////////////////////
		rdbtnSum.addActionListener(this);
		
		rdbtnNSum.addActionListener(this);
	//Add radio buttons to window/////////////////////////////////////////////////////////////////////////////////
		panel.add(getRdbtnSum());	
		panel.add(rdbtnNSum);	
	}
	
	private void addRateCustomization() {
	//Create and configure lists, textbox, and 'Set' button/////////////////////////////////////////////////////////////////////////////
		listPeriod = new JList(data.getPeriods());
		listPeriod.setBorder(new LineBorder(Color.GRAY));
		listPeriod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//set for one item to be selected at a time... multiple selection is default if not set
		listPeriod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listPeriod.setBounds(43, 94, 60, 50);
		
		listTimes = new JList(data.getTimes());
		listTimes.setToolTipText("Select a single value or (CTRL + click) to select many ");	//text shows when mouse hovers over list
		listTimes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listTimes.setBounds(90, 106, 72, 50);
		//Give 'listTimes' list a scrolling factor 
		JScrollPane listTimes_scrollPane = new JScrollPane(listTimes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listTimes_scrollPane.setBounds(113, 94, 85, 50);
		
		txtFieldRate = new JTextField();
		txtFieldRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFieldRate.setHorizontalAlignment(SwingConstants.CENTER);	
		txtFieldRate.setForeground(Color.BLACK);
		txtFieldRate.setBounds(208, 94, 72, 25);
		
		btnSet = new JButton("Set");
		btnSet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSet.setBounds(208, 118, 72, 25);
		
	//Add functionalities to/////////////////////////////////////////////////////////////////////////////
		listPeriod.addListSelectionListener(this);
		
		btnSet.addActionListener(this);
	//Add lists and 'Set' button to window///////////////////////////////////////////////////////////////////
		panel.add(listPeriod);
		panel.add(listTimes_scrollPane);	//adds listTimes as a scrollable list	
		panel.add(txtFieldRate);
		panel.add(btnSet);	
	}
	
	private void addElecUsageImport() {
	//Create and configure components for hourly usage import//////////////////////////////////////////////////////////
		txtFieldFileLoc = new JTextField();
		txtFieldFileLoc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFieldFileLoc.setHorizontalAlignment(SwingConstants.RIGHT);	
		txtFieldFileLoc.setForeground(Color.BLACK);
		txtFieldFileLoc.setBounds(20, 176, 119, 24);
		btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBrowse.setBounds(138, 176, 72, 23);
		
		btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImport.setBounds(220, 176, 89, 23);
		
		btnManualImport = new JButton("Manually enter usage");
		btnManualImport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnManualImport.setBounds(68, 215, 192, 23);
	//Add functionality to the buttons///////////////////////////////////////////////////////////////////
		btnBrowse.addActionListener(this);
		
		btnImport.addActionListener(this);
		
		btnManualImport.addActionListener(this);
	//Add components to window/////////////////////////////////////////////////////////////////////
		panel.add(txtFieldFileLoc);
		panel.add(btnBrowse);
		panel.add(btnImport);
		panel.add(btnManualImport);
	}
	
	private void addDisplayResultsBtn() {
	//Create and configure button to display calculations///////////////////////////////////////////
		btnDisplayResults = new JButton("Display results");	//click button to pop up electricity usage log window
		btnDisplayResults.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDisplayResults.setBounds(159, 249, 109, 23);
	//Add functionality to the button///////////////////////////////////////////////////////////////
		btnDisplayResults.addActionListener(this);
	//Add button to window//////////////////////////////////////////////////////////////////////////
		panel.add(btnDisplayResults);
	}
	
	public void valueChanged(ListSelectionEvent e) {
	/**	This function listens to changes in the selection of the Period list:
   	 * if a period is selected, along with a season, and has a defined rate plan already   ---> display it using other lists and text box
   	 * if a period is selected, along with a season, and does not have a defined rate plan ---> clear and current data on the other list and text box
	 */
		try {
			if(listPeriod.getSelectedValue().equals("Peak")) {		//if peak period selected
				if(rdbtnSum.isSelected()) {		//Summer rate plan
					showRatePlan(ShareData.summer_peak);	
				}
				if(rdbtnNSum.isSelected()) {	//Non-summer rate plan
					showRatePlan(ShareData.nonSummer_peak);
				}
			}
			if(listPeriod.getSelectedValue().equals("Mid-peak")) {		//if mid-peak period selected
				if(rdbtnSum.isSelected()) {		//Summer rate plan
					showRatePlan(ShareData.summer_midPeak);	
				}
				if(rdbtnNSum.isSelected()) {	//Non-summer rate plan
					showRatePlan(ShareData.nonSummer_midPeak);
				}
			}
			if(listPeriod.getSelectedValue().equals("Off-peak")) {		//if off-peak period selected
				if(rdbtnSum.isSelected()) {		//Summer rate plan
					showRatePlan(ShareData.summer_offPeak);	
				}
				if(rdbtnNSum.isSelected()) {	//Non-summer rate plan
					showRatePlan(ShareData.nonSummer_offPeak);
				}
			}
		}catch(Exception x) {}
	}

	//Set component functionalities here
	public void actionPerformed(ActionEvent e) {
	/**	This function decides which action to do based on which button was clicked  */
	
		/**Help icon*/
		if(e.getSource().equals(lbl_helpIcon)) {
			new HelpWindow().setVisible(true);
		}
		
		/**Season Buttons*/
		/**if a season is selected, along with a period, and has a defined rate plan already   ---> display it using other lists/text box
		 * if a season is selected, along with a period, and does not have a defined rate plan ---> clear current data on the other list/text box
		 */
		try {	
			if(e.getSource().equals(rdbtnSum)) {		//if summer season selected
				if(listPeriod.getSelectedValue().equals("Peak") ) {				//peak rate plan
					showRatePlan(ShareData.summer_peak);
				}
				if(listPeriod.getSelectedValue().equals("Mid-peak") ) {	//mid-peak rate plan
					showRatePlan(ShareData.summer_midPeak);
				}
				if(listPeriod.getSelectedValue().equals("Off-peak") ) {	//off-peak rate plan
					showRatePlan(ShareData.summer_offPeak);
				}
			}
			if(e.getSource().equals(rdbtnNSum)) {		//if non-summer season selected
				if(listPeriod.getSelectedValue().equals("Peak") ) {				//peak rate plan
					showRatePlan(ShareData.nonSummer_peak);
				}
				if(listPeriod.getSelectedValue().equals("Mid-peak") ) {	//mid-peak rate plan
					showRatePlan(ShareData.nonSummer_midPeak);
				}
				if(listPeriod.getSelectedValue().equals("Off-peak") ) {	//off-peak rate plan
					showRatePlan(ShareData.nonSummer_offPeak);
				}
			}
		}catch(NullPointerException x) {}
		
		/**Set Button*/
		/**The following nested if statements set the custom rate plan if one is specified, 
		 * otherwise default values from Rate.java are set to each rate plan in the ImportData.java file
		 */
		if(e.getSource().equals(btnSet)) {
			if( (rdbtnSeasons.getSelection() == null) || listPeriod.isSelectionEmpty() || listTimes.isSelectionEmpty() || !isDouble(txtFieldRate.getText()) ) {	//if no season, period, time, or rate is specified
				JOptionPane.showMessageDialog(null, "You have not selected a season, period, timeframe, or rate!", "Error", JOptionPane.WARNING_MESSAGE);
			}else {
				if(rdbtnSum.isSelected()) {
					if(listPeriod.getSelectedValue().equals("Peak")) {	//summer peak
						setRatePlan(ShareData.summer_peak);
					}
					if(listPeriod.getSelectedValue().equals("Mid-peak")) {	//summer mid-peak
						setRatePlan(ShareData.summer_midPeak);
					}
					if(listPeriod.getSelectedValue().equals("Off-peak")) {	//summer off-peak
						setRatePlan(ShareData.summer_offPeak);
					}
					
				}
				if(rdbtnNSum.isSelected()) {
					if(listPeriod.getSelectedValue().equals("Peak")) {	//non-summer peak
						setRatePlan(ShareData.nonSummer_peak);
					}
					if(listPeriod.getSelectedValue().equals("Mid-peak")) {	//non-summer mid-peak
						setRatePlan(ShareData.nonSummer_offPeak);
					}
					if(listPeriod.getSelectedValue().equals("Off-peak")) {	//non-summer off-peak
						setRatePlan(ShareData.nonSummer_offPeak);
					}
				}
			}
			//rdbtnSeasons.clearSelection();	//the following four lines clear the data to prepare for new rate plan
			listPeriod.clearSelection();		
			listTimes.clearSelection();		
			txtFieldRate.setText("");
			
		}
	
		/**Browse Button*/
		if(e.getSource().equals(btnBrowse)) {		//if 'Browse' button is clicked
			fileChooser = new JFileChooser();							
			fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));	//filters all non-json files from users view
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				ShareData.file = fileChooser.getSelectedFile();	//holds the complete file path
				txtFieldFileLoc.setText(ShareData.file.getName());	//file.getName() returns the name of the specified file
			}else {
				txtFieldFileLoc.setText("No file selected");
			}
 		}
		
		/**Import Button*/
		if(e.getSource().equals(btnImport)) {		//if 'import' button is clicked
			//set action here. work with Josh for this
			
			if(txtFieldFileLoc.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "No file has been selected.", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			ObjectMapper mapper = new ObjectMapper();

	        try {
	            // JSON file to Java object
	        	
	            JSONdata data = mapper.readValue(new File(txtFieldFileLoc.getText()), JSONdata.class);
	            List<Map<String, String>> currentReads = data.getReads();
	            ListIterator<Map<String, String>> iterat = currentReads.listIterator();
	            
	            DailyData jsonData = null;
	            
	            while (iterat.hasNext()) { 
	            	Map<String,String> currentRead = iterat.next();
                    LocalDateTime start = LocalDateTime.parse(currentRead.get("startTime"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    LocalDateTime end = LocalDateTime.parse(currentRead.get("endTime"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    Double value = Double.parseDouble(currentRead.get("value"));
                    
                    if(jsonData == null){
                    	jsonData = new DailyData();
                    	jsonData.dailyDate = start.toLocalDate();
                    }
                    else if(start.toLocalDate().isAfter(jsonData.dailyDate)) {
                    	ShareData.dailyDataStore.add(jsonData);
                		jsonData = new DailyData();
                    	jsonData.dailyDate = start.toLocalDate();
                    }
                    
                    jsonData.setHourlUsage(value, start.getHour());
                    
                    if(!iterat.hasNext()) {
                    	ShareData.dailyDataStore.add(jsonData);
                    }
                }
			
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			// I am thinking import and display?
			txtFieldFileLoc.setText("");
			
			if( (rdbtnSeasons.getSelection() == null)) {
				JOptionPane.showMessageDialog(null, "You have not selected a rate plan, please select one or create one.", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Iterator<DailyData> iterat = ShareData.dailyDataStore.iterator();
				while (iterat.hasNext()) {
					DailyData Idata = iterat.next();
					
					if(e.getSource().equals(rdbtnNSum)) {
						// here it should not overwrite changes a user makes because it is retrieving the values that the user modified, not the default values
						Idata.setPeakHours(ShareData.nonSummer_midPeak.getHours(), "midpeak");
						Idata.setPeakRates(ShareData.nonSummer_midPeak.getPrice(), "midpeak");
						
						Idata.setPeakHours(ShareData.nonSummer_peak.getHours(), "peak");
						Idata.setPeakRates(ShareData.nonSummer_peak.getPrice(), "peak");
						
						Idata.setPeakHours(ShareData.nonSummer_offPeak.getHours(), "offpeak");
						Idata.setPeakRates(ShareData.nonSummer_offPeak.getPrice(), "offpeak");
					}
					else {
						Idata.setPeakHours(ShareData.summer_midPeak.getHours(), "midpeak");
						Idata.setPeakRates(ShareData.summer_midPeak.getPrice(), "midpeak");
						
						Idata.setPeakHours(ShareData.summer_peak.getHours(), "peak");
						Idata.setPeakRates(ShareData.summer_peak.getPrice(), "peak");
						
						Idata.setPeakHours(ShareData.summer_offPeak.getHours(), "offpeak");
						Idata.setPeakRates(ShareData.summer_offPeak.getPrice(), "offpeak");
					}
				}
				
				
				OutputWindow calcImport = new OutputWindow();
				// create one day of data.
				
				// update usage?
				//dt.setPeakHours(hours, "offpeak");
			    //dt.setPeakRates(4.0, "offpeak"); // 4 dollar per kW
			    ///dt.setUsage(3.4, 0); // set hour 1  to 3.4kW
				
				calcImport.getFrame().setVisible(true);
			}
		
		}
	
		/**Manually Enter Usage Button*/
		if(e.getSource().equals(btnManualImport)) {	//if 'manually enter usage' button is clicked
			ManualInputStorage calc = new ManualInputStorage();
			calc.getFrame().setVisible(true);
		}
		
		/**Display Results Button*/
		if(e.getSource().equals(btnDisplayResults)) {	//if 'view log' button is clicked, open outputWindow.java
			
			if (ShareData.dailyDataStore.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You have not entered any Usage Information.", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
			
				//FIXME
				// updating rates
				Iterator<DailyData> iterat = ShareData.dailyDataStore.iterator();
				while (iterat.hasNext()) {
					DailyData Idata = iterat.next();
					
					if(e.getSource().equals(rdbtnNSum)) {
						// here it should not overwrite changes a user makes because it is retrieving the values that the user modified, not the default values
						Idata.setPeakHours(ShareData.nonSummer_midPeak.getHours(), "midpeak");
						Idata.setPeakRates(ShareData.nonSummer_midPeak.getPrice(), "midpeak");
						
						Idata.setPeakHours(ShareData.nonSummer_peak.getHours(), "peak");
						Idata.setPeakRates(ShareData.nonSummer_peak.getPrice(), "peak");
						
						Idata.setPeakHours(ShareData.nonSummer_offPeak.getHours(), "offpeak");
						Idata.setPeakRates(ShareData.nonSummer_offPeak.getPrice(), "offpeak");
					}
					else {
						Idata.setPeakHours(ShareData.summer_midPeak.getHours(), "midpeak");
						Idata.setPeakRates(ShareData.summer_midPeak.getPrice(), "midpeak");
						
						Idata.setPeakHours(ShareData.summer_peak.getHours(), "peak");
						Idata.setPeakRates(ShareData.summer_peak.getPrice(), "peak");
						
						Idata.setPeakHours(ShareData.summer_offPeak.getHours(), "offpeak");
						Idata.setPeakRates(ShareData.summer_offPeak.getPrice(), "offpeak");
					}
				}
				
				OutputWindow calc = new OutputWindow();
				calc.getFrame().setVisible(true);
			}
		}
		
		// updating the rate plan with changes.
					Iterator<DailyData> iterat = ShareData.dailyDataStore.iterator();
					while (iterat.hasNext()) {
						DailyData Idata = iterat.next();
						
						if(e.getSource().equals(rdbtnNSum)) {
							// here it should not overwrite changes a user makes because it is retrieving the values that the user modified, not the default values
							Idata.setPeakHours(ShareData.nonSummer_midPeak.getHours(), "midpeak");
							Idata.setPeakRates(ShareData.nonSummer_midPeak.getPrice(), "midpeak");
							
							Idata.setPeakHours(ShareData.nonSummer_peak.getHours(), "peak");
							Idata.setPeakRates(ShareData.nonSummer_peak.getPrice(), "peak");
							
							Idata.setPeakHours(ShareData.nonSummer_offPeak.getHours(), "offpeak");
							Idata.setPeakRates(ShareData.nonSummer_offPeak.getPrice(), "offpeak");
						}
						else {
							Idata.setPeakHours(ShareData.summer_midPeak.getHours(), "midpeak");
							Idata.setPeakRates(ShareData.summer_midPeak.getPrice(), "midpeak");
							
							Idata.setPeakHours(ShareData.summer_peak.getHours(), "peak");
							Idata.setPeakRates(ShareData.summer_peak.getPrice(), "peak");
							
							Idata.setPeakHours(ShareData.summer_offPeak.getHours(), "offpeak");
							Idata.setPeakRates(ShareData.summer_offPeak.getPrice(), "offpeak");
						}
					}
	}
	
	public void setRatePlan(Rate rate) {
		rate.setHours(Arrays.stream(listTimes.getSelectedValuesList().toArray()).toArray(String[]::new));	//all this to get list values, convert it to an array, then convert that array to a String array, and set the String array to our rate's hours	
		rate.setPrice(Double.parseDouble(txtFieldRate.getText()));		//set rate plans' hours
		rate.setListTimesIndices(listTimes.getSelectedIndices());		//used to later show hours in showRatePlan
		rate.setIsCustomized(true);
	}
	
	public void showRatePlan(Rate rate) {
		if(!rate.isCustomized()) {	//if rate plan data is still set to defaults, then dont show data
			listTimes.clearSelection();
			txtFieldRate.setText("");
		}else {
			listTimes.setSelectedValue(rate.getHours(0), true);			//scroll to the first hour
			listTimes.setSelectedIndices(rate.getListTimesIndices());	//otherwise set the rate plans' specified hours to be selected on the times list
			txtFieldRate.setText(Double.toString(rate.getPrice()));		//also show the rate plans' price on the text box
		}
	}
	
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					new MainWindow().setVisible(true);		//ONE LINER: create window and make visible
			}
		});
	}

//Getters(used in MainWindowTest.java)///////////////////////////////////////////////////////////////////////////////////////////////////////
	public JList<String> getListPeriod() {
		return listPeriod;
	}
	
	public JList<String> getListTimes() {
		return listTimes;
	}

	public JRadioButton getRdbtnSum() {
		return rdbtnSum;
	}
	
	public JRadioButton getRdbtnNSum() {
		return rdbtnNSum;
	}
	
	public JTextField getTxtFieldRate() {
		return txtFieldRate;
	}
	
	public JButton getBtnSet() {
		return btnSet;
	}
	
	public JButton getBtnBrowse() {
		return btnBrowse;
	}
	
	public JButton getBtnImport() {
		return btnImport;
	}
	
	public JButton getBtnManualImport() {
		return btnManualImport;
	}

	public ButtonGroup getRdbtnSeasons() {
		return rdbtnSeasons;
	}

	public JButton getBtnViewLog() {
		return btnDisplayResults;
	}
	
	public JLabel getHelpIcon() {
		return lbl_helpIcon;
	}
}