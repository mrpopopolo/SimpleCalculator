package fr.pops.simpleCalc;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface {

	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        	}
	    	});
		}

	private static void createAndShowGUI() {
		Calculator c = new Calculator();
	    JFrame frame = new JFrame("Calculator");
	    frame.setMinimumSize(new Dimension(485, 340));
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    JPanel panel = new JPanel();
	    panel.setMinimumSize(frame.getSize());
	    frame.add(panel);

	    String calc = "";
	    
	    //The Screen and clear button
	    JTextField screen = new JTextField(calc);
	    Font font1 = new Font("SansSerif", Font.PLAIN, 24);
	    screen.setBounds(0, 5, 430, 50);
	    screen.setEditable(true);
	    screen.setFont(font1);
	    panel.add(screen);
	    JButton cl = new JButton("c");
		cl.setBounds(435, 5, 50, 50);
		cl.addActionListener(e -> screen.setText(""));
		panel.add(cl);
	    
	    //The Buttons
		String[] ope = { "+", "-", "*", "0", ".", "=", "/" };
		int num = 0, x = 10, y = 70, k = 1;
		for(int i = 1; i <= 16; i++) {
			JButton butt = new JButton();
			butt.setBounds(x, y, 100, 40);
			if(i % 4 == 0) {
				butt.setText(ope[num++]);
				y += 60;
				x = 10;
				}
			else {
				if(i < 13) {
					butt.setText(""+k++);
					x = x + 120;
					}
				else {
					butt.setText(ope[num++]);
					x += 120;
					}
				}
			if(butt.getText().equals("="))
				butt.addActionListener(e -> screen.setText(""+c.calculate(screen.getText())));
			else {butt.addActionListener(e -> screen.setText(screen.getText()+butt.getText()));}
			panel.add(butt);
		}
	    
	    //Display the window.
		panel.setLayout(null);
	    frame.pack();
	    frame.setVisible(true);
	    }
	}
