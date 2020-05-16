/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mayıntarlası;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MayınTarlası implements ActionListener{
 
    JFrame frame = new JFrame("MayınTarlası");
    JButton reset = new JButton("Tekrar Oyna");
    JButton[][] butonlar = new JButton[20][20];
    int[][] sayımlar = new int[20][20];
    Container grid = new Container();
    final int MAYIN = 10;
    
    public MayınTarlası(){
       frame.setSize(1000,700);
       frame.setLayout(new BorderLayout());
       frame.add(reset,BorderLayout.NORTH);
       reset.addActionListener(this);
       grid.setLayout(new GridLayout(20,20));
        for (int a = 0; a < butonlar.length; a++) {
            for (int b = 0; b < butonlar[0].length; b++) {
                butonlar[a][b] = new JButton();
                butonlar[a][b].addActionListener(this);
                grid.add(butonlar[a][b]);
            }
        }
        frame.add(grid, BorderLayout.CENTER);
        RandomMayın();
  
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    
    }
    
    public static void main(String[] args) {
        new MayınTarlası();
    }

    public void RandomMayın(){
       //Rastgele liste kısmı
        ArrayList<Integer> liste = new ArrayList<Integer>();
        for (int x = 0; x < sayımlar.length; x++) {
            for (int y = 0; y < sayımlar[0].length; y++) {
                liste.add(x*100+y);    
            }          
        }
        // iptal ve mayın seçim kısmı
         sayımlar = new int[20][20];
         for (int a = 0; a < 30; a++) {
            int seçim = (int)(Math.random() * liste.size());
            sayımlar[liste.get(seçim) / 100][liste.get(seçim) % 100] = MAYIN;
            liste.remove(seçim);
        }
         //komşu sayılar
         for (int x = 0; x < sayımlar.length; x++) {
             for (int y = 0; y < sayımlar[0].length; y++) {
                 if(sayımlar[x][y] != MAYIN){
                 int komşusayısı = 0;
                 if ( x > 0 && y > 0 && sayımlar[x-1][y-1] == MAYIN) {
                     komşusayısı++;           
                 }
                 if(y > 0 && sayımlar[x][y-1] == MAYIN){
                     komşusayısı++;
             }
            if( x < sayımlar.length -  1 && y < sayımlar[0].length - 1 && sayımlar[x+1][y+1] == MAYIN){
                komşusayısı++;
            }
            sayımlar[x][y] = komşusayısı;           
        }    
      }   
    }        
}
    
    public void Kaybet(){
         for (int x = 0; x < butonlar.length; x++){
             for (int y = 0; y < butonlar[0].length; y++){
                 if (butonlar[x][y].isEnabled()){
                     if(sayımlar[x][y] != MAYIN){
                          butonlar[x][y].setText(sayımlar[x][y] + "");
                           butonlar[x][y].setEnabled(false);
                     }  
                     else{
                          butonlar[x][y].setText("X");
                          butonlar[x][y].setEnabled(false);
                     }
                 }
             }
         }
        
    }
    public void Temizle(ArrayList<Integer> toClear){
        if(toClear.size() == 0){
            return;
        }
        else{
            int x = toClear.get(0) / 100;
            int y = toClear.get(0) % 100;
            toClear.remove(0);         
                if( x > 0 && y > 0 && butonlar[x-1][y-1].isEnabled()){
                    butonlar[x-1][y-1].setText(sayımlar[x-1][y-1] + "");
                    butonlar[x-1][y-1].setEnabled(false);
                    if(sayımlar[x-1][y-1] == 0){
                        toClear.add((x-1) * 100 + (y-1));
                    }
                }
                if(y > 0 && butonlar[x][y-1].isEnabled()){
                    butonlar[x][y-1].setText(sayımlar[x][y-1] + "");
                    butonlar[x][y-1].setEnabled(false);
                       if(sayımlar[x][y-1] == 0){
                        toClear.add(x * 100 + (y-1));
                    }
                }
                if(x < sayımlar.length - 1 && y > 0 && butonlar[x+1][y-1].isEnabled()){
                    butonlar[x+1][y-1].setText(sayımlar[x+1][y-1] + "");
                    butonlar[x+1][y-1].setEnabled(false);
                       if(sayımlar[x+1][y-1] == 0){
                        toClear.add((x+1) * 100 + (y-1));
                    }
                } if( x > 0 && butonlar[x-1][y].isEnabled()){
                    butonlar[x-1][y].setText(sayımlar[x-1][y] + "");
                    butonlar[x-1][y].setEnabled(false);
                    if(sayımlar[x-1][y] == 0){
                        toClear.add((x-1) * 100 + y);
                    }
                }
                if(x < sayımlar.length - 1 && butonlar[x+1][y-1].isEnabled()){
                    butonlar[x+1][y].setText(sayımlar[x+1][y] + "");
                    butonlar[x+1][y].setEnabled(false);
                       if(sayımlar[x+1][y] == 0){
                        toClear.add((x+1) * 100 + y);
                    }
                }
                 if( x > 0 && y < sayımlar[0].length - 1 && butonlar[x-1][y+1].isEnabled()){
                    butonlar[x-1][y+1].setText(sayımlar[x-1][y+1] + "");
                    butonlar[x-1][y+1].setEnabled(false);
                    if(sayımlar[x-1][y+1] == 0){
                        toClear.add((x-1) * 100 + (y+1));
                }
                if(y < sayımlar[0].length - 1 && butonlar[x][y+1].isEnabled()){
                    butonlar[x][y+1].setText(sayımlar[x][y+1] + "");
                    butonlar[x][y+1].setEnabled(false);
                       if(sayımlar[x][y+1] == 0){
                        toClear.add(x * 100 + (y+1));
                    }
                }
                if(x < sayımlar.length - 1 && y < sayımlar[0].length - 1 && butonlar[x+1][y+1].isEnabled()){
                    butonlar[x+1][y+1].setText(sayımlar[x+1][y+1] + "");
                    butonlar[x+1][y+1].setEnabled(false);
                       if(sayımlar[x+1][y+1] == 0){
                        toClear.add((x+1) * 100 + (y+1));
                    }
            }
            Temizle(toClear);
        }
        
    }
    }
   
    public  void Kazan(){
        boolean win = true;
        for (int x = 0; x < sayımlar.length; x++){
            for (int y = 0; y < sayımlar[0].length; y++){
                if(sayımlar[x][y] != MAYIN && butonlar[x][y].isEnabled() == true){
                    win = false;
                }
            }
        }  
        if(win == true){
           JOptionPane.showMessageDialog(frame , "KAZANDIN!");
        }
    }
    

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(reset)){
            for (int x = 0; x < butonlar.length; x++) {
                 for (int y = 0; y < butonlar[0].length; y++){
                     butonlar[x][y].setEnabled(true);
                     butonlar[x][y].setText("");
                 }
            }
            RandomMayın();
        }
        else{
               for (int x = 0; x < butonlar.length; x++) {
                    for (int y = 0; y < butonlar[0].length; y++) {
                        if(e.getSource().equals(butonlar[x][y])){
                            if(sayımlar[x][y] == MAYIN){
                                butonlar[x][y].setForeground(Color.red);
                                butonlar[x][y].setText("X");
                                Kaybet();
                            }
                            else if(sayımlar[x][y] == 0){
                            butonlar[x][y].setText(sayımlar[x][y] + "");
                            butonlar[x][y].setEnabled(false);
                            ArrayList<Integer> toClear = new ArrayList<Integer>();
                            toClear.add(x*100+y);
                            Temizle(toClear);
                            Kazan();
                            }
                            else{
                            butonlar[x][y].setText(sayımlar[x][y] + "");
                            butonlar[x][y].setEnabled(false);
                            Kazan();
                            }
                        }
                    }
              }
        }
    }
    
}
