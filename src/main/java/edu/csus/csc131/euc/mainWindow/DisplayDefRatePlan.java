package edu.csus.csc131.euc.mainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DisplayDefRatePlan extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel lbl_defRatePlan;

	public DisplayDefRatePlan() {
		setTitle("Default Rate Plan");
		setIconImage(new ImageIcon("images/appIcon.png").getImage());
		setBounds(325, 100, 700, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
	//Add components////////////////////////////////////
	    try {
	          add();
	      }catch (IOException e) { e.printStackTrace(); }
	}
		
    public void add() throws IOException {
        Image image = new ImageIcon("images/defaultRatePlan.png").getImage();
        JLabel lbl_defRatePlan = new JLabel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
            }
        };
        add(lbl_defRatePlan);
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DisplayDefRatePlan().setVisible(true);
			}
		});
	}
}
