package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

//Creating A Home Page of game

public class HomeFrame extends JFrame implements ActionListener {
	
	
	JButton button;
	JButton button2;
	
	HomeFrame(){
		
		Border border = BorderFactory.createLineBorder(Color.green,3);
		ImageIcon icon = new ImageIcon("Logo.jpeg"); 
	
//		Creating a Labels
		JLabel label = new JLabel();
		label.setText("Well come to SNAKE GAME");
		label.setForeground(new Color(171,8,245));
		label.setFont(new Font(null,Font.BOLD,35));
//		label.setBorder(border);
		label.setBackground(Color.black);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 100, 765, 100);
		
		JLabel label2 = new JLabel();
		label2.setText("By Raimath@420");
		label2.setForeground(new Color(243,62,255));
		label2.setFont(new Font(null,Font.BOLD,40));
		label2.setBackground(Color.blue);
		label2.setVerticalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(0, 500, 760, 100);

		
//		Creating A Buttons
		
		button = new JButton();
		button.setBounds(250, 200, 250, 100);
		button.addActionListener(this);
		button.setText("START→");
		button.setFocusable(false);
		button.setForeground(Color.green);
		button.setBackground(Color.black);
		button.setFont(new Font(null,Font.PLAIN,50));
		
		button2 = new JButton();
		button2.setBounds(250, 350, 250, 100);
		button2.addActionListener(this);
		button2.setText("How to Play ?");
		button2.setFocusable(false);
		button2.setForeground(Color.green);
		button2.setBackground(Color.black);
		button2.setFont(new Font(null,Font.PLAIN,30));
		
		
//		Adding properties to Frame
		
		this.setTitle("SnakeGame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(765,790);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.black);
		this.setIconImage(icon.getImage());
		this.add(label);
		this.add(label2);
		this.add(button);
		this.add(button2);
		
		
		
		
	}
	
//	This is a ActionPerformed method on Button clicking
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			
			JFrame GameFrame= new JFrame();
			
			this.dispose();
			GameFrame.add(new GamePanel());
			GameFrame.setTitle("SnakeGame");
			GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GameFrame.setResizable(false);
			GameFrame.pack();
			GameFrame.setVisible(true);
			GameFrame.setLocationRelativeTo(null);
		
		}
		
		if(e.getSource()==button2) {
			
			JLabel label = new JLabel();
			label.setText("How To PLAY ?");
			label.setForeground(new Color(249,183,245));
			label.setFont(new Font(null,Font.BOLD,35));
			label.setBackground(Color.pink);	
			label.setVerticalAlignment(JLabel.CENTER);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBounds(0, 50, 600, 70);
			
			
			JLabel label2 = new JLabel();
			label2.setText("1. Move snake using arrow keys in your keyboard.");
			label2.setForeground(new Color(249,183,245));
			label2.setFont(new Font(null,Font.BOLD,17));
			label2.setBackground(Color.orange);
			label2.setVerticalAlignment(JLabel.CENTER);
			label2.setHorizontalAlignment(JLabel.LEFT);
			label2.setBounds(50, 170, 600, 80);

			JLabel label3 = new JLabel();
			label3.setText("2.Consume the food, Score and snake size will be increased.");
			label3.setForeground(new Color(249,183,245));
			label3.setFont(new Font(null,Font.BOLD,17));
			label3.setBackground(Color.blue);
			label3.setVerticalAlignment(JLabel.CENTER);
			label3.setHorizontalAlignment(JLabel.LEFT);
			label3.setBounds(50, 250, 600, 80);
			
			JLabel label5 = new JLabel();
			label5.setText("3.If head of Snake touchs it's Body or Any Border Game is over.");
			label5.setForeground(new Color(249,183,245));
			label5.setFont(new Font(null,Font.BOLD,17));
			label5.setBackground(Color.black);
			label5.setVerticalAlignment(JLabel.CENTER);
			label5.setHorizontalAlignment(JLabel.LEFT);
			label5.setBounds(50, 330, 600, 80);
			
			
			JLabel label4 = new JLabel();
			label4.setText("HAPPY GAMING :)");
			label4.setForeground(Color.orange);
			label4.setFont(new Font(null,Font.PLAIN,40));
			label4.setBackground(new Color(63,133,224));
			label4.setVerticalAlignment(JLabel.BOTTOM);
			label4.setHorizontalAlignment(JLabel.CENTER);
			label4.setBounds(200, 450, 500, 70);
			
			
			
			
			JFrame rules = new JFrame();
			
			rules.setTitle("How to play");
			rules.add(label);
			rules.add(label2);
			rules.add(label3);
			rules.add(label5);
			rules.add(label4);
			rules.setResizable(false);
			rules.getContentPane().setBackground(new Color(67,77,255));
			rules.pack();
			rules.setVisible(true);
			rules.setLocationRelativeTo(null);
			rules.setSize(600,600);
			rules.setLocationRelativeTo(null);
		}
		
	}
}
