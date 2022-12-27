/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinningphilosophers;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
// Sarà l'oggetto condiviso tra i 5 filosofi
// dovrà essere thread-safe
public class Table {
    // attributi di sincronizzazione
    private ReentrantLock chopsticks[];

    // mettiamo un generatore di numeri casuali
    // per avere una tempistica aleatoria
    private Random rnd;

    // metodo costruttore
    public Table(){
        this.chopsticks = new ReentrantLock[5];
        // inizializzo ogni bacchetta
        for(int i = 0; i < this.chopsticks.length;i++){
            this.chopsticks[i] = new ReentrantLock();
        }
        this.rnd = new Random();
    }
    
// metodi pubblici dell'oggetto
    // metodo eat per mangiare che useranno i filosofi per mangiare
    public void eat(int index){
        
        // il filosofo iesimo acquisisce la sua bacchetta di sinistra (i)
        // e poi la sua bacchetta di destra (i+1)
        if(index < 4){
            this.chopsticks[index].lock();
            this.chopsticks[(index+1)%5].lock();
        }else{
            // l'ultimo filosofo acquisisce l'ultima bacchetta
            // e la prima che si sarà liberata nel frattempo
            // così non rimane bloccato a richiedere una bacchetta impossibile
            this.chopsticks[(index+1)%5].lock();
            this.chopsticks[index].lock();
        }
        
        
        // se sono qua vuol dire che ho ottenuto entrambe le bacchette
        // ora posso mangiare
        
        // mi sospendo per un tempo casuale compreso nell'intervallo [0,30]ms 
        // quindi nella sleep metto 31 perchè toglie l'estremo, quindi prende anche 30 così
        // per simulare il tempo aleatorio per mangiare
        System.out.println("Il filosofo "+index+" mangia!");
        try{
            Thread.sleep(this.rnd.nextInt(31));
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        // ora rilascio le bacchette partendo dall'ultima acquisita
        this.chopsticks[(index+1)%5].unlock();
        this.chopsticks[index].unlock();
        // ora rilascio un permesso sul semaforo contatore    
    }// end metodo eat
    
    // metodo think per pensare che vuole mangiare
    public void think(int index){
        // metodo che simula un tempo aleatorio nel quale
        // il filosofo pensa e quindi non compete per le 
        // risorse
        System.out.println("Il filosofo "+ index + " pensa!!");
        try{
            // si sospende per un tempo estratto a caso nell intervallo
            // da zero a dieci ms compreso
            Thread.sleep(this.rnd.nextInt(11));
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
    }// end metodo pensa
    
}// end della classe
