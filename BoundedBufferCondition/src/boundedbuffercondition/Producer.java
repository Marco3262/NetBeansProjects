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
// classe per generare thread di tipo producer
public class Producer extends Thread{
    private BoundedBuffer myBuffer;
    private Random rnd;
    
    // costruttore della calsse
    public Producer(BoundedBuffer b, String name){
        // setto il nome a livello di sistema
        // passandolo al costruttore del padre
        super(name);
        this.myBuffer = b;
        // inizializzo il generatore di numeri pseudo-casuali
        this.rnd      = new Random();
    }//end del costruttore
    
    // definisco il comportamento del thread
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            // inserisco l'elemento nel buffer
            // estraggo un numero casuale
            // mi sospendo per l'equivalente in ms
            this.myBuffer.insert(i);
            // estrazione casuale nell'intervallo [0,100[
            int timeToSleep = this.rnd.nextInt(100);
            try{
                Thread.sleep(timeToSleep);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }//end ciclo for
        System.out.println(super.getName()+" termina!!!");
    }//end metodo run()
}// end classe









