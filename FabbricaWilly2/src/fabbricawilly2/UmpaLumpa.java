/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricawilly2;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class UmpaLumpa extends Thread {
    private Random rnd;
    private int semi;
    private Condition c;
    private ReentrantLock mutex;
    private Fabbrica myFabbrica;
    
    public UmpaLumpa(int index,Fabbrica f){
        this.setName("UmpaLumpa_"+index);
        this.rnd = new Random();
        this.semi = this.rnd.nextInt(11)+10;
        this.mutex = new ReentrantLock();
        this.c = this.mutex.newCondition();
        this.myFabbrica = f;
    }
    
    public int getSemi() {
        return semi;
    }

    public void dormi(Condition c)throws InterruptedException {
        this.c = c;
        this.c.await();
    }

    public void sveglia() {
        this.c.signal();
    }
    
    @Override
    public void run(){
        try{
            this.myFabbrica.richiediForno(this);
            Thread.sleep(this.semi*10);
            this.myFabbrica.liberaForno(this);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
}
