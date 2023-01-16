/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.mqtt;

import java.util.Random;

/**
 *
 * @author marco
 */
public class Publisher extends Thread{
    public Broker myBroker;
    public String topic;
    public Random rnd;
    
    public Publisher(Broker m,String name, String t){
        super(name);
        this.myBroker = m;
        this.topic = t;
        this.rnd = new Random();
    }
    
    @Override
    public void run(){
        try{
            for(int i = 0; i < 10;i++){
                Thread.sleep(this.rnd.nextInt(350));
                this.myBroker.publish(new Messaggio(this.topic,i));
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
