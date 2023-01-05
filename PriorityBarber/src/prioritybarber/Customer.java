/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prioritybarber;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author marco
 */
// Un thred cliente dotato di
// un meccanismo interno per la
// sospensione e il risveglio selettivo
public class Customer extends Thread {
    // attributi funzionali
    private BarberShop myshop;
    // attributo interno che definisce la priorità
    private int myPriority;
    private Random rnd;
    // attributi di sincronizzazione
    private Semaphore mySem;
    
    public Customer(BarberShop b,String name){
        // setto il nome del costruttore
        super(name);
        this.myshop = b;
        this.rnd = new Random();
        this.mySem = new Semaphore(0);
        this.myPriority = this.rnd.nextInt(100); // priorità data a caso da 0 a 99
    }
    // dichiaro i metodi publici per sospendere e risvegliare il thread
    public void block() throws InterruptedException{
        this.mySem.acquire();
    }
    
    public void wakeUp(){
        this.mySem.release();
    }
    
    // metodo per leggere la priorità del cliente
    public int getMyPriority(){
        return this.myPriority;
    }
    
    // comportamento del thread 
    @Override
    public void run(){
        // vado a farmi tagliare i capelli dieci volte 
        for(int i = 0; i < 10;i++){
            try{
                this.myshop.enter(this);
                // mi sospendo per simulare la ricrescita
                Thread.sleep(this.rnd.nextInt(21)+10); // PER FARE UN INTERVALLO CHE VA DA 10 A 30
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println(super.getName()+" termina!!");
    }
    
    
}
