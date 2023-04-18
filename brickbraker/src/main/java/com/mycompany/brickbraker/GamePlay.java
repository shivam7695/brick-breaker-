/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbraker;

/**
 *
 * @author hp
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class GamePlay   extends JPanel implements KeyListener , ActionListener {
    private boolean play = true;
    private int score= 0 ;
    private int totalbrick = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerx = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    
    public GamePlay(){
    map = new MapGenerator(3,7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    Timer = new Timer(delay, this);
    Timer.start();
     
  }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0 , 0, 692, 592);
        
        map.draw((Graphics2D) g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0,0,692,3);
        g.fillRect(680, 0, 3, 592);
        
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("serif", Font.BOLD , 25));
        g.drawString("" + score, 590,  30);
        
        g.setColor(Color.green);
        g.fillRect(playerx, 550, 100, 8);
        
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(ballposY > 570){
           play = false;
           ballXdir = 0;
           ballYdir = 0 ; 
           g.setColor(Color.red);
           g.setFont(new Font("serif", Font.BOLD , 30));
           g.drawString("   GAME OVER SCORE" +  score ,190 , 300);
           
           g.setFont(new Font ("serif", Font.BOLD , 30));
           g.drawString("  Press Enter to Start", 190  , 340);
        }
        
        if(totalbrick == 0 ){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD , 30));
            g.drawString(" GAME OVER : " + score, 190, 300);

            g.setFont(new Font ( "serif" , Font.BOLD , 30));
            g.drawString("Press Enter to Start", 190, 340);

        }
         
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
      		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { 
			if(playerx >= 600) {
				playerx = 600;
			} else {
				moveRight();
					
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) { 
			if(playerx < 10) {
				playerx = 10;
			} else {
				moveLeft();
					
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				score = 0;
				totalbrick = 21;
				map = new MapGenerator(3,7);
				
				repaint();
                        }
                }
    }
     public void moveRight(){
         play = true;
         playerx += 20;
     }
     public void moveLeft(){
         
         play = true;
         playerx -= 20;
     }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        if(play){
            if(new Rectangle(ballposX , ballposY , 20 , 30 ).intersects(new Rectangle(playerx , 550 , 100 , 8))){
             ballYdir = -ballYdir;   
            }
            A:
            for(int i = 0 ; i < map.map.length; i++){
                for(int j = 0 ;  j < map.map[0].length;j++){
                    if(map.map[i][j]  > 0 ){
                 int brickX = j * map.brickwidth + 80;
		int brickY = i*map.brickheight + 50;
		int brickwidth= map.brickwidth;
		int brickheight = map.brickheight;
						
	Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
	Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
	Rectangle brickRect = rect;
						
	if(ballRect.intersects(brickRect) ) {
		map.setBrickValue(0, i, j);
                     totalbrick--;
		score+=5;
							
		if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width) 
		ballXdir = -ballXdir;
		else {
			ballYdir = -ballYdir;				
                    }
                break A;
                }
            }
        }
    
    }

        		ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) { 
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) { 
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) { 
				ballXdir = -ballXdir;  
			
			}
			
		}
        repaint();
		
    }
}

   
   
