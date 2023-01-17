/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettoautofficina;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Meccanico extends Thread {
    
    private Random rnd;
    private Autofficina myAutofficina;
    private int lavoriCompletati;
    private Condition stato;
    private boolean isAlive = true;
    private ReentrantLock mutex;
    
    public Meccanico(Autofficina t,String name){
        super(name);
        this.rnd = new Random();
        this.myAutofficina = t;
        this.lavoriCompletati = 0;
        this.mutex = new ReentrantLock();
    }
    
    public void pausa() throws InterruptedException {
        this.lavoriCompletati = 0;
        Thread.sleep(500);
    }

    public void finepausa() {
        this.stato.signalAll();
    }

    public void aspetta() throws InterruptedException{
        this.lavoriCompletati = 0;
        System.out.println(this.getName()+" aspetta i colleghi per la pausa!!");
        this.stato.await();
    }
    
    @Override
    public void run(){
        try{ 
            
            while(isAlive){
                this.mutex.lock();
                if(this.lavoriCompletati == 6)
                    this.myAutofficina.farePausa(this);
                else
                    
                    this.myAutofficina.riparaAuto(this);
                    this.lavoriCompletati++;
                    System.out.println(this.getName()+" ha sistemato "+ this.lavoriCompletati+" auto!!");
                    Thread.sleep(200);
                this.mutex.unlock();
                    
            }
        }catch(InterruptedException e){
            isAlive = false;
            System.out.println(e);
            System.out.println(this.getName() + " ha terminato!!");
        }
    }
    


    

    
    
}
