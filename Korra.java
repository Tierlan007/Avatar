package avatar.Character;

import java.util.*;
import java.net.*;
import java.io.*;   
import avatar.*;  
import avatar.Attacken.*;

public class Korra implements Avatare{

  public String ATTACKE1 = "Waterwhip"; 
  public String ATTACKE2 = "Firepunch";
  public String ATTACKE3 = "";
  public String ATTACKE4 = "";
  
  private int STR;
  private int INT;
  private int GES;
  private String NAME = "Korra";
  private ClientStart myClient = new ClientStart();   
  public Scanner scan = new Scanner(System.in);
  
  public Korra(ClientStart clSt){
    myClient = clSt;
    calcu_stats();
  }
    
  public void calcu_stats(){
    Random rng = new Random();
    STR = rng.nextInt(30-20+1)+20;
    INT = rng.nextInt(60-50+1)+50;
    GES = rng.nextInt(30-20+1)+20;              
  }  
    
  public int Attacken (){
    boolean loop1 = true;
    String eingabe;
    System.out.println("<1> : "+ATTACKE1);  
    System.out.println("<2> : "+ATTACKE2);
    System.out.println("<3> : "+ATTACKE3);
    System.out.println("<4> : "+ATTACKE4); 
    System.out.println("<5> : Back");
    while (loop1 == true) { 
      eingabe = scan.next();
      if (eingabe.equals("1")) {   
        System.out.println(ATTACKE1);
        return 1;
      } else if (eingabe.equals("2")){
        System.out.println(ATTACKE2);
        return 2;
      } else if (eingabe.equals("3")){
        
        loop1 = false;
      } else if (eingabe.equals("4")){
        
        loop1 = false;
      } else if (eingabe.equals("5")){
        loop1 = false;
      } else if (eingabe.equals("ADMIN")){
        return 900;
      }
    }             
    return 0;
  }
  
  
  public int getSTR(){return STR;}    
  public int getINT(){return INT;} 
  public int getGES(){return GES;} 
  public String getNAME(){return NAME;}
  public ClientStart getMyClient(){return myClient;}
    
}