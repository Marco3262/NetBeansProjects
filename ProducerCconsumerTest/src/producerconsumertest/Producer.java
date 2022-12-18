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
public class Producer extends Thread {
    // riferimento a oggetto da condividere
    private BoundedBuffer myBuffer;
    // dichiaro un generatore di numeri casuali generico (di qualunque tipo)
    private Random rnd;
    
    // costruttore della classe
    public Producer(BoundedBuffer b, String name){
        // setto il nome al thread
        super(name);
        this.myBuffer = b;
        //inizializzo il generatore di numeri
        this.rnd = new Random();
    }// end costruttore
    
    // comportamento del thread 
    @Override
    public void run(){
        // produce cento elementi ad una velocità variabile
        // inserisco elemento
        // estraggo un numero casuale
        // mi sospendo per il tempo uguale al numero estratto casualmente
        // poi torno in cima
        
        for(int i = 0;i < 100; i++){
            this.myBuffer.insert(i);
            // estraggo ora un numero casuale
            // nell'intervallo da 0 a 99
            int timeToSleep = this.rnd.nextInt(100); 
            // così vado a dormire per un equivalente in millisecondi
            try{
                Thread.sleep(timeToSleep);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }// end for
        System.out.println(super.getName()+" termina!");


    }// ed metodo run
    
}
