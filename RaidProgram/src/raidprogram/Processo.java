/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raidprogram;

import java.util.Random;



/**
 *
 * @author marco
 */
public class Processo extends Thread {
    
    private int cilindri;
    private int disco;
    private Random rnd;
    
    public Processo(){
        this.disco = 0;
        this.rnd = new Random();
        this.cilindri = this.rnd.nextInt();
    }
    
    public void disco(int disco){
        this.disco = disco;
    }

    public int getDisco(){
        return disco;
    }

   
    
}
