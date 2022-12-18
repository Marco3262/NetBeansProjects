/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producerconsumertest;

import java.util.Random;

/**
 *
 * @author Lattanzi
 */
// classe per creare thread Produttori
public class Producer extends Thread{
    // riferimento interno all'oggetto da condividere
    private BoundedBuffer myBuffer;
    // dichiaro un generatore di numeri pseudocasuali
    private Random rnd;
    
    // costruttore della classe
    public Producer(BoundedBuffer b, String name){
        // setto il nome al thread 
        super(name);
        this.myBuffer = b;
        // inizializzo il generatore 
        this.rnd = new Random();        
    }
    // comportamento del thread 
    @Override
    public void run(){
        // produce cento elementi 
        // ad una velocità variabile 
        // inserisco l'elemento
        // estraggo un nuemero casuale
        // mi sospendo per il tempo uguale al numero estratto
        // torno in cima
        for(int i = 0; i < 100; i++){
            this.myBuffer.insert(i);
            // estraggo un numero casuale
            // nell'intervallo [0,100[            
            int timeToSleep = this.rnd.nextInt(100); 
            // vado a dormire per un equivalente in ms
            try{    
                Thread.sleep(timeToSleep);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }// end for
        System.out.println(super.getName()+ " termina!!");
    }// end metodo run()
    
}// end classe





