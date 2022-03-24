package edu.csus.csc131.euc.mainWindow;

import java.awt.event.MouseEvent;

import org.junit.jupiter.api.Test;
import edu.csus.csc131.euc.mainWindow.HelpWindow;

public class HelpWindowTest {
	private final HelpWindow uut = new HelpWindow();
	
	@Test
	public void testHyperlink() {
		uut.getHyperlink().dispatchEvent(new MouseEvent(
				uut.getHyperlink(), 
				MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 
				0, 
				uut.getHyperlink().getX(), uut.getHyperlink().getY(),
				1,
				false));
	}
	
	
}
