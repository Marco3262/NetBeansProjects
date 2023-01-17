/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autodemolitore;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Autovettura extends Thread{
    
    private int tonnellate;
    private ReentrantLock mutex;
    private Condition mySem;
    private Random rnd;
    private Autodemolizione myPlace;
    
    public Autovettura(Autodemolizione a,int i){
        this.myPlace = a;
        this.rnd = new Random();
        this.tonnellate = this.rnd.nextInt(21)+1;
        this.setName("Auto_"+i+" di tonnellate "+this.getTonnellate());
        this.mutex = new ReentrantLock();
        this.mySem = this.mutex.newCondition();
    }
    
    public int getTonnellate() {
        return this.tonnellate;
    }

    public void dormi(Condition c) throws InterruptedException{
        this.mySem = c;
        this.mySem.await();
    }

    public void sveglia() {
        this.mySem.signal();
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(this.rnd.nextInt(501)+100);
            this.myPlace.richiediAccesso(this);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
    }
}
