/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autodemolitore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Autodemolizione {
    
    private ReentrantLock mutex;
    private Semaphore daDemolire;
    private LinkedList<Autovettura> listaAttesa;
    
    
    public Autodemolizione(){
        this.mutex = new ReentrantLock();
        this.listaAttesa = new LinkedList();
        this.daDemolire = new Semaphore(0);
    }
    
    public void richiediAccesso(Autovettura a)throws InterruptedException{
        this.mutex.lock();
        try{
            this.listaAttesa.add(a);
            System.out.println(a.getName()+" è in attesa");
            this.daDemolire.release();
            a.dormi(this.mutex.newCondition());
        }finally{
            this.mutex.unlock();
        }
    }
    
    public void smaltisci(Rottamatore r) throws InterruptedException{
        
        Autovettura demolisci = null;
        
        this.daDemolire.acquire(4);
        this.mutex.lock();
        try{
            for(int i = 0; i < 4;i++){
                demolisci = this.daDemolire();
                r.dormi();
                System.out.println(demolisci.getName()+" è stata demolita!!");
                demolisci.sveglia();
            }
        }finally{
            this.mutex.unlock();
        }
        
    
    }
    
    public Autovettura daDemolire(){
        
        int tonn_massima =  0;
        Autovettura current = null;
        Autovettura demolisci = null;
        
        for(int i = 0; i < this.listaAttesa.size();i++){
            current = this.listaAttesa.get(i);
            if(current.getTonnellate() > tonn_massima){
                demolisci = current;
                tonn_massima = current.getTonnellate();
                
            }
        }
        this.listaAttesa.remove(demolisci);
        return demolisci;
    }
    
    
    
}
