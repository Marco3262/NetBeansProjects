/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producerconsumertest;

import java.util.Random;

/**
 *
 * @author marco
 */
public class Consumer extends Thread{
    private BoundedBuffer myBuffer;
    private Random rnd;
    
    public Consumer(BoundedBuffer b, String name){
        super(name);
        this.myBuffer = b;
        this.rnd = new Random();
    
    }
    
    // comportamento thread
    @Override
    public void run(){
        //consumo 100 elementi a velocità variabile
        for(int i = 0;i < 100;i++){
            int myItem = this.myBuffer.remove();
            //estraggo un numero casuale nell'intervallo 0 199 e mi aspettoche il buffer sia saturo perchè ho messo il produttore più veloce
            int timeToSleep = this.rnd.nextInt(200);
            try{
                Thread.sleep(timeToSleep);
            
            }catch(InterruptedException e){
                System.out.println(e);
            
            
            }
        
        
        }// end for
    
    System.out.println(super.getName()+" termina!");
    }// end metodo run

}// end classe