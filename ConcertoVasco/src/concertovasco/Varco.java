/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concertovasco;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Varco {
    
    private ReentrantLock mutex;
    // per quelli senza foto
    private Condition filaA;
    // per quelli con foto
    private Condition filaB;
    private int controllati;
    
    private Semaphore filaGenerale;
    private int numA;
    
    public Varco(){
        this.mutex = new ReentrantLock();
        this.filaA = this.mutex.newCondition();
        this.filaB = this.mutex.newCondition();
        this.filaGenerale = new Semaphore(0,true);
        this.controllati = 0;
    }
    
    public void richiediAccesso(Persona p)throws InterruptedException{
        this.mutex.lock();
        try{
            this.filaGenerale.release();
            if(p.getStato()){
                System.out.println(p.getName()+" è in attesa!!");
                this.numA++;
                this.filaA.await();
            }else{
                System.out.println(p.getName()+ " è in attesa!!");
                this.filaB.await();
            }
            
        }finally{
            this.mutex.unlock();
        }
    }
    
    public void controllaPersona()throws InterruptedException{
        
        
        
        
        
        //this.filaGenerale.acquire();
        this.mutex.lock();
        try{
            if(this.controllati < 30){
                this.controllati++;
                this.filaGenerale.acquire();
                this.filaA.signal(); 
                
            }else
                this.filaGenerale.acquire();
                this.filaB.signal();
        }finally{
            this.mutex.unlock();
        }
    }
}
