/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinningphilosophers;

/**
 *
 * @author marco
 */
// classe che produrrà thread di tipo filosofo
public class Philosopher extends Thread {
    // attributo per condividere l'oggetto tavolo
    private Table mytable;
    // indice che identifica ogni filosofo
    private int index;
    
    // metodo costruttore
    public Philosopher(Table t,int i){
        // setto il nome del filosofo
        super("Philosopher "+i);
        this.mytable    = t;
        this.index      = i;
    }// end costruttore
    
    // possiamo definire il comportamento del thread
    @Override
    public void run(){
        // per cento volte il filosofo tenta di mangiare e poi va a pensare
        for(int i = 0;i < 100;i++){
            // cerca di mangiare
            this.mytable.eat(this.index);
            // ora si mette a pensare
            this.mytable.think(this.index);
        }// end ciclo for
        System.out.println(super.getName() + " termina!!");
    }// end metodo run
}// end classe filosofo
