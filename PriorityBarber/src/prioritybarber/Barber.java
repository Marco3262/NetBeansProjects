/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prioritybarber;

/**
 *
 * @author marco
 */
public class Barber extends Thread {
    private BarberShop myShop;
    
    //costruttore
    public Barber(BarberShop b){
        super("Barber");
        this.myShop = b;
    }
    // definiamo il comportamento del thread
    // deve essere in terminazione deferita
    @Override
    public void run(){
        boolean isAlive = true;
        while(isAlive){
            try{
                this.myShop.cut();
            }catch(InterruptedException e){
                System.out.println(e);
                isAlive = false;
            }
        }// end while
        System.out.println(super.getName()+ " termina!!");
    }// end metodo run
}// end classe
