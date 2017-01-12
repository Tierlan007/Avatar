package avatar.Character;

import java.util.*;
import java.net.*;
import java.io.*;      
import avatar.*;

public class Kyoshi implements Avatare{

  public String ATTACKE1 = "Airslash"; 
  public String ATTACKE2 = "Firepunch";
  public String ATTACKE3 = "";
  public String ATTACKE4 = "";
  
  public Atk atk = new Atk();
  
  public Kyoshi(){
    
    }
    
  public double Attacken (int str, int inti, int ges){
    boolean loop1 = true;
    String eingabe;
    Scanner scan = new Scanner(System.in);
    System.out.println("<1> : "+ATTACKE1);  
    System.out.println("<2> : "+ATTACKE2);
    System.out.println("<3> : "+ATTACKE3);
    System.out.println("<4> : "+ATTACKE4);
    while (loop1 == true) { 
      eingabe = scan.next();
      if (eingabe.equals("1")) {
        return atk.Airslash(str,ges);
      } else if (eingabe.equals("2")){
                                      
        loop1 = false;
      } else if (eingabe.equals("3")){
                                       
        loop1 = false;
      } else if (eingabe.equals("4")){
                                      
        loop1 = false;
      }
    } 
    return 0;
  }
}