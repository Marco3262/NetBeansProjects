/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autodemolitore;

import java.util.concurrent.locks.Condition;

/**
 *
 * @author marco
 */
public class Rottamatore extends Thread{
    
    private Autodemolizione myPlace;
    private Condition mySem;
    
    public Rottamatore(Autodemolizione a){
        super("Rottamatore");
        this.myPlace = a;
    }
    
    public void dormi()throws InterruptedException{
        Thread.sleep(200);
    }
    
    public void run(){
        boolean isAlive = true;
        while(isAlive){
            try{
                this.myPlace.smaltisci(this);
            }catch(InterruptedException e){
                System.out.println(e);
                System.out.println(this.getName()+" ha terminato!!!");
                isAlive = false;
            }
        }
    }
    
}
