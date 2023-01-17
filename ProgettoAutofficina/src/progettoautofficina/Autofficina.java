/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettoautofficina;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Autofficina {
    
    private ReentrantLock mutex;
    private ReentrantLock attesaAuto;
    private Semaphore codaInPanne;
    private Semaphore codaLievi;
    private int numInPanne;
    private int numLievi;
    private Semaphore codaTotale;
    private int giaInPausa;
    
    
    public Autofficina(){
        this.mutex = new ReentrantLock();
        this.attesaAuto = new ReentrantLock();
        this.codaInPanne = new Semaphore(0,true);
        this.codaLievi = new Semaphore(0,true);
        this.numInPanne = 0;
        this.numLievi = 0;
        this.codaTotale = new Semaphore(0,true);
        
    }
    
    public void richiediRiparazione(Auto a) throws InterruptedException{
        this.mutex.lock();
        try{
            
            if(a.getStato()){
                this.numInPanne++;
                
                System.out.println(a.getName()+" si mette in attesa");
                this.codaInPanne.release();
                this.codaTotale.release();
            }else{
                
                this.numLievi++;
                System.out.println(a.getName()+" si mette in attesa");
                this.codaLievi.release();
                this.codaTotale.release();
            }
        }finally{
            this.mutex.unlock();
        }
    
    }
    
    public void riparaAuto(Meccanico m) throws InterruptedException{
        this.attesaAuto.lock();
        try{
            
            this.codaTotale.acquire();
            if(this.numInPanne > 0){
                this.numInPanne--;
                this.codaInPanne.acquire();
            }else{
                this.numLievi--;
                this.codaLievi.acquire();
            }
        }finally{
            this.attesaAuto.unlock();
        }
    }
    
    public void farePausa(Meccanico m)throws InterruptedException{
        this.mutex.lock();
        try{
            if(this.giaInPausa == 4){
                m.pausa();
                m.finepausa();
            }else{
                this.giaInPausa++;
                m.aspetta();
            }
        }finally{
            this.mutex.unlock();
        }
    }
}
