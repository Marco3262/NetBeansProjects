package bookingproject;

/**
 *
 * @author marco
 */

// Questo sarà l'oggetto con i vari thread della simulazione
public class BookingManager {
    // attributi funzionali
    // costante che definisce il numero massimo di posti prenotabili
    public static final int MAX_POSTI = 100;
    private int postiPrenotati;
    
    //costruttore della classe
    public BookingManager(){
        this.postiPrenotati = 0;
    }
    
    //Definisco l'interfaccia dell'oggetto condiviso
    public boolean book(Client c){
        boolean ret = true;
        // devo controllare se ci sono posti
        if(this.postiPrenotati < MAX_POSTI){
            System.out.println("Posti disponibili per " + c.getName());
            this.postiPrenotati++;
        }else{
            System.out.println("Posti esauriti!!");
            ret = false;
        }
        return ret;
    }//end del metodo book
    
    public int getPostiLiberi(){
        return MAX_POSTI - this.postiPrenotati;
    }
}//end classe bookingmanager
