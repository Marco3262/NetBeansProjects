/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attraversaponte;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Ponte {
    private ReentrantLock mutex;
    private Semaphore gialli;
    private Semaphore verdi;
    private int numGialli;
    private int numVerdi;
    private Semaphore nuovoBabbuino;
    private Semaphore capacita;
    
    public Ponte(){
        this.mutex = new ReentrantLock();
        this.gialli = new Semaphore(0,true);
        this.verdi = new Semaphore(0,true);
        this.nuovoBabbuino = new Semaphore(0);
        this.capacita = new Semaphore(3,true);
        this.numGialli = 0;
        this.numVerdi = 0;
        
    }
    
    public void cercaPosto(Babbuino b) throws InterruptedException{
        try{
            this.mutex.lock();
            System.out.println(b.getName()+ " si presenta al ponte!!");
            if(b.getColore())
                this.numGialli++;
            else
                this.numVerdi++;
            this.nuovoBabbuino.release();
        }finally{
            this.mutex.unlock();
        }
        
        if(b.getColore())
            this.gialli.acquire();
        else
            this.verdi.acquire();
    }
    
    public void esci(){
        this.capacita.release();
    }
    
    public void gestisciAttraversamento() throws InterruptedException{
        this.nuovoBabbuino.acquire();
        this.capacita.acquire();
        try{
            this.mutex.lock();
            if(this.numGialli >= this.numVerdi){
                this.gialli.release();
                this.numGialli--;
            }else{
                this.verdi.release();
                this.numVerdi--;
            }
        }finally{
            this.mutex.unlock();
        }
    }
}
