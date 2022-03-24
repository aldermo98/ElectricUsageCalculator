package edu.csus.csc131.euc.store;
/*
Author: Harlan Nguyen
Version: 1.5
*/

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;

import edu.csus.csc131.euc.mainWindow.ShareData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//this program stores user input regarding the starting date, ending date, and the elctrical rate
public class ManualInputStorage {
    private JFrame frame;
    private JPanel panelMain;
    private JLabel startDateGeneralLabel;
    private JLabel endDateGeneralLabel;
    private JLabel startIntervalLabel;
    private JLabel endIntervalLabel;
    private JLabel elecUsageLabel;
    private UtilDateModel startModel;
    private JDatePanelImpl startDatePanel;
    private JDatePickerImpl startDatePicker;
    private UtilDateModel endModel;
    private JDatePanelImpl endDatePanel;
    private JDatePickerImpl endDatePicker;
    private JComboBox<Integer> startIntervalCBox;
    private JComboBox<Integer> endIntervalCBox;
    private JRadioButton startAMJRadioButton;
    private JRadioButton startPMJRadioButton;
    private JRadioButton endAMJRadioButton;
    private JRadioButton endPMJRadioButton;
    private JTextField elecUsageTextField;
    private JButton addUsageButton;
    private JTextArea toStringTextArea;
    private List<UsageInfo> usageInfos = new ArrayList<>();
    
    // create array of DailyDatas for the number of days of information that needs to be stored. still TODO
    public DailyData[] DaysWithUsage;

    public List<UsageInfo> getUsageInfos() {
        return usageInfos;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ManualInputStorage() {
        //sets up the frame
        frame = new JFrame();
        frame.setTitle("Manual Input Storage");
        ImageIcon appIcon = new ImageIcon("images/appIcon.png");
		frame.setIconImage(appIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(675, 225);
        //Creates the main panel and labels
        panelMain = new JPanel();
        frame.setContentPane(panelMain);
        startDateGeneralLabel = new JLabel("Select Starting Date:");
        endDateGeneralLabel = new JLabel("Select Ending Date:");
        //hourIntervalNoteLabel = new JLabel("         *When inputting the Hourly Intervals: 12 + AM = 12 PM, and 12 + PM = 12 AM*         ");
        startIntervalLabel = new JLabel("Enter Starting Hourly Interval:");
        endIntervalLabel = new JLabel("Enter Ending Hourly Interval:");
        elecUsageLabel = new JLabel("Enter Electricity Usage:");

        //Creates the DatePicker objects
        startModel = new UtilDateModel();
        startDatePanel = new JDatePanelImpl(startModel);
        startDatePicker = new JDatePickerImpl(startDatePanel);
        endModel = new UtilDateModel();
        endDatePanel = new JDatePanelImpl(endModel);
        endDatePicker = new JDatePickerImpl(endDatePanel);

        //Creates the text fields
        elecUsageTextField = new JTextField(10);

        //creates the combo boxes
        Integer[] hours = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        startIntervalCBox = new JComboBox<>(hours);
        endIntervalCBox = new JComboBox<>(hours);

        //creates the radio buttons
        startAMJRadioButton = new JRadioButton("AM");
        startAMJRadioButton.setSelected(true);
        startPMJRadioButton = new JRadioButton("PM");
        endAMJRadioButton = new JRadioButton("AM");
        endAMJRadioButton.setSelected(true);
        endPMJRadioButton = new JRadioButton("PM");

        //Creates the text areas ,where the inputs are displayed, and the button that stores the inputs
        toStringTextArea = new JTextArea(5, 25);
        toStringTextArea.setEditable(false);
        JScrollPane textArea_scrollPane = new JScrollPane(toStringTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        addUsageButton = new JButton("Add Start Date, End Date, and Electrical Usage");

        //Adds the various elements to the main panel
        panelMain.add(startDateGeneralLabel);
        frame.add(startDatePicker);
        panelMain.add(endDateGeneralLabel);
        frame.add(endDatePicker);
        panelMain.add(startIntervalLabel);
        panelMain.add(startIntervalCBox);
        panelMain.add(startAMJRadioButton);
        panelMain.add(startPMJRadioButton);
        panelMain.add(endIntervalLabel);
        panelMain.add(endIntervalCBox);
        panelMain.add(endAMJRadioButton);
        panelMain.add(endPMJRadioButton);
        panelMain.add(elecUsageLabel);
        panelMain.add(elecUsageTextField);
        panelMain.add(addUsageButton);
        panelMain.add(textArea_scrollPane);

        //Action Listeners for the radio buttons
        startAMJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startAMJRadioButton.isSelected()) {
                    startPMJRadioButton.setSelected(false);
                }
            }
        });
        startPMJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startPMJRadioButton.isSelected()) {
                    startAMJRadioButton.setSelected(false);
                }
            }
        });
        endAMJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endAMJRadioButton.isSelected()) {
                    endPMJRadioButton.setSelected(false);
                }
            }
        });
        endPMJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endPMJRadioButton.isSelected()) {
                    endAMJRadioButton.setSelected(false);
                }
            }
        });

        //Stores the values into an arraylist
        addUsageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if the toStringTextArea is empty
                    if (!toStringTextArea.getText().isBlank()) {
                        toStringTextArea.setText("");
                    }
                    //If both text fields are blank, then nothing happens
                    if (!elecUsageTextField.getText().isBlank()) {
                        //turns the input data from strings to numeric values
                        int startDateMonthInt = 1+startDatePicker.getModel().getMonth();
                        int startDateDayInt = startDatePicker.getModel().getDay();
                        int startDateYearInt = startDatePicker.getModel().getYear();
                        int startHour = getMilitaryHour(Integer.parseInt(startIntervalCBox.getSelectedItem().toString()), startAMJRadioButton.isSelected());
                        int endDateMonthInt = 1 + endDatePicker.getModel().getMonth();
                        int endDateDayInt = endDatePicker.getModel().getDay();
                        int endDateYearInt = endDatePicker.getModel().getYear();
                        int endHour = getMilitaryHour(Integer.parseInt(endIntervalCBox.getSelectedItem().toString()), endAMJRadioButton.isSelected());
                        Double elecUsageDouble = Double.parseDouble(elecUsageTextField.getText());

                        //checks if the starting year, ending year, and electrical rate is not negative
                        if (startDateYearInt > 0 && endDateYearInt > 0 && elecUsageDouble > -0.000001) {
                            LocalDateTime start = LocalDateTime.of(startDateYearInt, startDateMonthInt, startDateDayInt, startHour, 0);
                            LocalDateTime end = LocalDateTime.of(endDateYearInt, endDateMonthInt, endDateDayInt, endHour, 0);
                            //checks if the starting year is smaller than the ending year
                            if (start.isBefore(end) || startDateYearInt <= endDateYearInt) {
                                ManualInputInfo euc = new ManualInputInfo(start, end, elecUsageDouble);
                                usageInfos.add(euc);
                                String op = "";
                                for(int i = 0; i < usageInfos.size(); i++) {
                                	op += usageInfos.get(i).toString() + "\n\n";
                                }
                                toStringTextArea.setText(op);

                                // create all the usage Data here:
                                
                    			DailyData newData = new DailyData();
                    			newData.dailyDate = start.toLocalDate();
                    			// if hours between is more than 24 - start.getHour() then create a new daily data with next date and check hours between again until you run out of hours?
                    			
                    			int numberOfHours = (int) ChronoUnit.HOURS.between(start, end);
                    			int hoursProcessed = 0;
                                
                            	/// update the hourly usage
                            	for(int z = 0; z < numberOfHours; z++) {
                            		// check if you reach the end of the day.
                            		if((start.getHour() + z) >= 24) break;
                            		// update usage
                            		hoursProcessed++;
                            		newData.setHourlUsage((elecUsageDouble/numberOfHours), start.getHour() + z);
                            	}
                            	
                            	ShareData.dailyDataStore.add(newData);
                            	
                            	LocalDate dateReference = newData.dailyDate;
                            	
                            	while(hoursProcessed < numberOfHours) {
                            		// create a new day and repeat above process.
                            		DailyData newData2 = new DailyData();
                        			dateReference = dateReference.plus(1, ChronoUnit.DAYS);
                        			newData2.dailyDate  = dateReference;
                            		
                            		int dayCounter = 0;
                            		for(; hoursProcessed < numberOfHours; hoursProcessed++) {
                            			// check if you reach the end of the day.
                                		if((dayCounter) >= 24) break;
                                		newData2.setHourlUsage((elecUsageDouble/numberOfHours), dayCounter);
                                		dayCounter++;
                            		}
                            		ShareData.dailyDataStore.add(newData2);
                            	}
                                	
                                JOptionPane.showMessageDialog(null, "Updated the Usage Rates", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
                                
                            } else {
                                toStringTextArea.setText("Error: the starting date is after the ending date");
                            }
                        } else {
                            toStringTextArea.setText("Error: One or more values are negative");
                        }
                    } else {
                        toStringTextArea.setText("Error: One or more fields are empty");
                    }
            }
        });
    }

    public JButton getAddRatesButton() {
		return addUsageButton;
	}

	public void setAddRatesButton(JButton addRatesButton) {
		this.addUsageButton = addRatesButton;
	}

	public static void main(String[] args) {
        ManualInputStorage calc = new ManualInputStorage();
        calc.frame.setVisible(true);
    }

    private int getMilitaryHour (int hour, boolean isAm) {
        int militaryHour = hour;
        if(!isAm) {
        	if(militaryHour != 12) 
        		militaryHour += 12;
        }else {
        	if(militaryHour == 12) 
        		militaryHour = 0;
        }
        return militaryHour;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
