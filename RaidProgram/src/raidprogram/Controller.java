/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raidprogram;

import static java.lang.Math.abs;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Controller {
    private int controllo;
    private Semaphore notFull;
    private ReentrantLock mutex;
    private Semaphore attesaA;
    private Semaphore attesaB;
    private int posizioneA;
    private int posizioneB;
    
    public Controller(){
        this.controllo = 0;
        this.notFull = new Semaphore(0);
        this.attesaA = new Semaphore(0,true);
        this.attesaB = new Semaphore(0,true);
    }
    
    public void richiediCilindro(int cilindro, Processo p) throws InterruptedException{
        this.mutex.lock();
        try{
            if(this.IndirizzaDisco(cilindro) == 0){
                p.disco(0);
                this.posizioneA = cilindro;
                this.attesaA.acquire();
                
            }else{
                p.disco(1);
                this.posizioneB = cilindro;
                this.attesaB.acquire();
                
            }
            this.notFull.release();
        }finally{
            this.mutex.unlock();
        }
    }
    
    public int IndirizzaDisco(int cilindro){
        
        int i = 0;
        
            if(abs(cilindro - this.posizioneA) < abs(cilindro - this.posizioneB))
                i = 0;
            else
                i = 1;
        return i;
    }
    
    public void serviRichiesta(Processo p,Gestore gestoreA,Gestore) throws InterruptedException{
        this.mutex.lock();
        try{
            this.notFull.acquire();
            if(p.getDisco() == 0)
                this.attesaA.release();
                this.gestoreA.sveglia();
            else
                this.attesaB.release();
        }finally{
            this.mutex.unlock();
        }
    }
}
