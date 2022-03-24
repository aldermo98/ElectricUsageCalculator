package edu.csus.csc131.euc.mainWindow;

import java.awt.event.MouseEvent;
import org.junit.jupiter.api.Test;
import edu.csus.csc131.euc.mainWindow.MainWindow;

class MainWindowTest {
	private final MainWindow uut = new MainWindow();
	//Click help icon/////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testhelpIcon() {
		uut.getHelpIcon().dispatchEvent(new MouseEvent(
				uut.getHelpIcon(), 				//dispatch event on this component
				MouseEvent.MOUSE_CLICKED, 		//dispatch this event
				System.currentTimeMillis(), 	//dispatch after this many ms
				0, 								//no modifiers
				uut.getHelpIcon().getX(), uut.getHelpIcon().getY(),		//dispatch event at these coordinates
				1, 								//dispatch event after this many instances of event
				false));						//not a pop-up trigger
	}
	
	//Show Rates tests///////////////////////////////////////////////////////////////////////////////////////////
	@Test	
	void testValueChanged_1() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Peak", false);
	}
	
	@Test	
	void testValueChanged_2() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Peak", false);
	}
	
	@Test	
	void testValueChanged_3() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
	}
	
	@Test	
	void testValueChanged_4() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
	}
	
	@Test	
	void testValueChanged_5() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Off-peak", false);
	}
	
	@Test	
	void testValueChanged_6() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Off-peak", false);
	}
	
	//Show Rates tests/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	
	void testActionPerformed_showRate_1() {
		uut.getListPeriod().setSelectedValue("Peak", false);
		uut.getRdbtnSum().doClick();
	}
	
	@Test	
	void testActionPerformed_showRate_2() {
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
		uut.getRdbtnSum().doClick();	
	}
	
	@Test	
	void testActionPerformed_showRate_3() {
		uut.getListPeriod().setSelectedValue("Off-peak", false);
		uut.getRdbtnSum().doClick();	
	}
	
	@Test	
	void testActionPerformed_showRate_4() {
		uut.getListPeriod().setSelectedValue("Peak", false);
		uut.getRdbtnNSum().doClick();
	}
	
	@Test	
	void testActionPerformed_showRate_5() {
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
		uut.getRdbtnNSum().doClick();
	}
	
	@Test	
	void testActionPerformed_showRate_6() {
		uut.getListPeriod().setSelectedValue("Off-peak", false);
		uut.getRdbtnNSum().doClick();
	}
	
	//Set Button tests//////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	
	void testActionPerformed_setButton_1() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_2() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_3() {
		uut.getRdbtnSum().doClick();
		uut.getListPeriod().setSelectedValue("Off-peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_4() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_5() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Mid-peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_6() {
		uut.getRdbtnNSum().doClick();
		uut.getListPeriod().setSelectedValue("Off-peak", false);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText("0.7357");
		uut.getBtnSet().doClick();
	}

	@Test
	void testActionPerformed_setButton_7() {
		uut.getRdbtnSeasons().setSelected(null, true);
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_8() {
		uut.getRdbtnSeasons().setSelected(uut.getRdbtnSum().getModel(), true);
		uut.getListPeriod().clearSelection();
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_9() {
		uut.getRdbtnSeasons().setSelected(uut.getRdbtnSum().getModel(), true);
		uut.getListPeriod().setSelectedIndex(0);
		uut.getListTimes().clearSelection();
		uut.getBtnSet().doClick();
	}
	
	@Test
	void testActionPerformed_setButton_10() {
		uut.getRdbtnSeasons().setSelected(uut.getRdbtnSum().getModel(), true);
		uut.getListPeriod().setSelectedIndex(0);
		uut.getListTimes().setSelectedIndex(0);
		uut.getTxtFieldRate().setText(null);
		uut.getBtnSet().doClick();
	}
	
	//Browse Button tests////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testActionPerformed_browseButton_1() {
		uut.getBtnBrowse().doClick();
	}
	
	@Test
	void testActionPerformed_browseButton_2() {
		uut.getBtnBrowse().doClick();
	}
	
	//Import Button tests////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testActionPerformed_importButton_1() {
		uut.getBtnImport().doClick();
	}
	
	//Manually Enter Usage Button tests//////////////////////////////////////////////////////////////////////////////////
	@Test
	void testActionPerformed_manualImportButton_1() {
		uut.getBtnManualImport().doClick();
	}
	
	//View Log Button tests//////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testActionPerformed_viewLogButton_1() {
		uut.getBtnViewLog().doClick();
	}
	
	
}