/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author marco
 */
public class Customer extends Thread {
    // attributi funzionali 
    private BarberShop myShop;
    
    // costruttore
    public Customer(BarberShop s, String name){
        super(name);
        this.myShop = s;
    }
    
    // definiamo il comportamento del thread
    @Override
    public void run(){
        for(int i = 0;i < 10; i++){
            //provo a tagliare i capelli
            if(this.myShop.lookAndWait(this)){
                //mi sono stati tagliati i capelli
                System.out.println(super.getName() + " ho tagliato i capelli");
                // simulo la ricrescita
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }else{
                // attendo un po prima di tornare a vedere se c'è posto
                System.out.println(super.getName()+" non ha trovato posto!!");
                try{
                    Thread.sleep(20);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        }// end for
        System.out.println(super.getName() + " termina!!");
    }// end metodo run
    
}// end classe
