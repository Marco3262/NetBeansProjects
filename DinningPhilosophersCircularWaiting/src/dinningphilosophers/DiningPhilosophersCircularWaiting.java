/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dinningphilosophers;

/**
 *
 * @author marco
 */
public class DiningPhilosophersCircularWaiting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creazione oggetto condiviso
        Table table = new Table();
        // creazione dei cinque filosofi
        Philosopher philosophers[] = new Philosopher[5];
        // inizializzo e lancio i filosofi
        for(int i = 0;i < philosophers.length;i++){
            philosophers[i] = new Philosopher(table,i);
            philosophers[i].start();
        }
        
        // ora mi metto in attesa per la terminazione
        try{
            for(int i = 0; i < philosophers.length;i++){
                philosophers[i].join();
            }
        
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata!!");
        
    }// end metodo main
    
}// end classe
