package snake;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;

//Creating a Pannel for Snake Game

public class GamePanel extends JPanel implements ActionListener {
	
//	Declearing variabels
	
	static final int SCREEN_WIDTH =750;
	static final int SCREEN_HEIGHT =750;
	static final int UNIT_SIZE=25;
	static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY=200;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    int bodyParts = 4;
    int foodEaten;
    int foodX;
    int foodY;
    int result;
    char direction = 'R';
    boolean running =false;
    
    
    
    Timer timer2;
    Timer timer;
    Random random;
    JLabel label;
	
    
   
	
	GamePanel(){
		random = new Random();
	
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		startGame();
	}
	
	public void startGame() {
		newFood();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		
		if(running) {
			/*
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0,  i*UNIT_SIZE, SCREEN_WIDTH,  i*UNIT_SIZE);
			}
			*/
		
			g.setColor(Color.red);
//			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
		
		
			for(int i=0; i<bodyParts; i++) {
				if(i==0) {
					g.setColor(new Color(60,245,57));
					g.fillOval(x[i], y[i],UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(7,150,6));
					g.fillOval(x[i], y[i], UNIT_SIZE , UNIT_SIZE);
				}
			}
			
			g.setColor(Color.magenta);
			g.setFont(new Font(null,Font.PLAIN,35));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score : "+foodEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : "+foodEaten))/2,g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	
	
	public void newFood() {
		foodX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		foodY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	
	public void move() {
		for(int i=bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
			
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
			
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
			
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkFood() {
		if((x[0]== foodX)&& (y[0]==foodY)) {
			bodyParts++;
			foodEaten++;
			
			newFood();
		}
	}
	
	public void checkCollusion() {
		//To Check head touch it's body
		for(int i=bodyParts; i>0; i--) {
			if((x[0]==x[i])  &&  (y[0]==y[i])) {
				running = false;
				runMusic();
			}
		}
		
		// To check Head touchs borders
		if(x[0]<0) {
			running = false;
			runMusic();
		}
		
		if(x[0] > SCREEN_WIDTH) {
			running = false;
			runMusic();
		}
		
		if(y[0]<0) {
			running = false;
			runMusic();
		}
		
		if(y[0]> SCREEN_HEIGHT) {
			running = false;
			runMusic();
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public static void runMusic() {
		try {
			 File file = new File("GameOver.wav");
			 AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			 Clip clip =AudioSystem.getClip();
			 clip.open(audio);
			 clip.start();
		}catch(Exception e){
			e.printStackTrace();
			}
	}

	
	public void gameOver(Graphics g) {
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Your Score is : "+foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Your Score is : "+foodEaten))/2,SCREEN_HEIGHT/5);
		
		
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/3);
//		g.drawString("Game Over", 100, 150);
		
		
	}
	
//	This is a Actionperformed method
	
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkFood();
			checkCollusion();
			
		}
		
		repaint();
		
		if(!running) {
			int result =JOptionPane.showConfirmDialog(null, "Do You Want to Try Again ?", "Game Over", JOptionPane.YES_NO_OPTION);
			
			if(result==0) {
				new HomeFrame();
				this.setVisible(false);
			}
			
			if(result!=0) {
				new HomeFrame();
				this.setVisible(false);		
			}																												
		}
		
		
	}
	
//	This is a Keypress events for Moving the Snake
	
	public class MyKeyAdapter extends KeyAdapter{
	
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
				
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
			
		}
	}

}
