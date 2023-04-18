/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbraker;

/**
 *
 * @author hp
 */
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;


public class MapGenerator {
    public int map[][];
    int brickwidth;
    int brickheight;
    public MapGenerator(int row,int col){
        map = new int [row][col];
        for(int i = 0 ; i < map.length; i++){
            for(int j = 0; j < map[0].length;j++){
                map[i][j]=1;
            }
        }
        brickwidth = 540/col;
        brickheight = 150 / row;
    }
    public void draw(Graphics2D g){
        for(int i  = 0 ; i < map.length;i++){
            for(int j = 0 ; j < map[0].length;j++){
                if(map[i][j] > 0){
                g.setColor(Color.red);
                g.fillRect(j * brickwidth + 80, i*brickheight+50, brickwidth, brickheight);
                       
                g.setStroke(new BasicStroke(4));
		g.setColor(Color.BLACK);
		g.drawRect(j*brickwidth + 80, i*brickheight+50, brickwidth , brickheight);
                }
            }
        }
    }

    void setBrickValue(int i, int i0, int j) {
        map[i][j]=i0;
    }
}
