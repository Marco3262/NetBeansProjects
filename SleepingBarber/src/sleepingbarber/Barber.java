/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author marco
 */
public class Barber extends Thread{
    private BarberShop shop;
    
    // costruttore
    public Barber(BarberShop s){
        super("Barber");
        this.shop = s;
    }
    
    // definisco il comportamento del barbiere
    // che si trova in terminazione deferita
    @Override
    public void run(){
        // Terminazione deferita 
        // deve essere in un ciclo infinito
        // finche non arriva un interrupt
        boolean isAlive = true;
        while(isAlive){
            try{
                this.shop.cut();
            }catch(InterruptedException e){
                System.out.println("Il barbiere riceve l'interrupt");
                System.out.println(e);
                isAlive = false;
            }
        }// end metodo while
        System.out.println(super.getName()+" termina!!");
    }// end metodo run
    
}
