/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attraversaponte;

import java.util.Random;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author marco
 */
public class Babbuino extends Thread {
    private Condition mycond;
    private boolean colore;
    private Random rnd;
    private Ponte myPonte;
    
    public Babbuino(Ponte p,int i){
        this.rnd = new Random();
        this.myPonte = p;
        int estratto = this.rnd.nextInt(100);
        if(estratto <= 60)
            colore = false;
        else
            colore = true;
        this.setName("Babbuino_"+i+" con colore "+ (this.colore ? "giallo" : "verde"));
        
    }
    

    boolean getColore() {
        return this.colore;
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(this.rnd.nextInt(1001));
            this.myPonte.cercaPosto(this);
            Thread.sleep(501);
            this.myPonte.esci();
            System.out.println(super.getName()+" ha attraversato il ponte!!");
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
