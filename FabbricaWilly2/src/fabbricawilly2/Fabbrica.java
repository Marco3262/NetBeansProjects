/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricawilly2;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Fabbrica {
    private Semaphore forni;
    private ReentrantLock mutex;
    private LinkedList<UmpaLumpa> listaAttesa;
    private Semaphore inAttesa;
    
    public Fabbrica(){
        this.forni = new Semaphore(3);
        this.listaAttesa = new LinkedList();
        this.mutex = new ReentrantLock();
        this.inAttesa = new Semaphore(0);
    }
    
    public void richiediForno(UmpaLumpa u)throws InterruptedException{
        this.mutex.lock();
        try{
            this.listaAttesa.add(u);
            System.out.println(u.getName()+" ha richiesto il forno -> "
                                +u.getSemi()+" semi!!");
            this.inAttesa.release();
            u.dormi(this.mutex.newCondition());
            
            
        }finally{
            
            this.mutex.unlock();
        }
    }
    
    public void liberaForno(UmpaLumpa u){
        System.out.println(u.getName()+" ha usato il forno!!");
        this.forni.release();
    }
    
    public void gestisciRichieste()throws InterruptedException{
        UmpaLumpa current = null;
        UmpaLumpa theBest = null;
        int min_semi = 20;
        
        this.inAttesa.acquire();
        this.mutex.lock();
        try{
            for(int i = 0;i < this.listaAttesa.size();i++){
                current = this.listaAttesa.get(i);
                if(current.getSemi() <= min_semi){
                    theBest = current;
                    min_semi = current.getSemi(); 
                }
            }
            
            this.listaAttesa.remove(theBest);
            theBest.sveglia();
            System.out.println("Willy Wonka ha concesso il forno a "+theBest.getName()+"!!");
        }finally{
            this.mutex.unlock();
        }
    }
}
