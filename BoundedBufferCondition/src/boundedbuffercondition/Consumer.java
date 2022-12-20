/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundedbuffercondition;

import java.util.Random;

/**
 *
 * @author Lattanzi
 */
// classe per creare thread di tipo Consumer
public class Consumer extends Thread {
    private BoundedBuffer myBuffer;
    private Random rnd;
    
    // costruttore della classe
    public Consumer(BoundedBuffer b, String name){
        super(name);
        this.myBuffer = b;
        this.rnd      = new Random();        
    }//end costruttore
    
    // implementazione del comportamento del thread
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            int removed     = this.myBuffer.remove();
            // estraggo un numero casuale nell'intervallo [0,50[
            int timeToSleep = this.rnd.nextInt(150);
            try{
                Thread.sleep(timeToSleep);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }//end for
        System.out.println(super.getName()+" termina!!");
    }// end metodo run()    
}// end classe Consumer







