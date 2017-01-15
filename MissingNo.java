package avatar.Character;

import java.util.*;
import java.net.*;
import java.io.*;        
import avatar.*; 
import avatar.Attacken.*;

public abstract class MissingNo implements Avatare{

  public MissingNo(){
    
  }
  
  public int Attacken (){
    boolean loop1 = true;
    String eingabe;
    Scanner scan = new Scanner(System.in);
    System.out.println("<1> : ");  
    System.out.println("<2> : ");
    System.out.println("<3> : ");
    System.out.println("<4> : ");
    while (loop1 == true) { 
      eingabe = scan.next();
      if (eingabe.equals("1")) {
        System.out.println("MissingNo1");
        loop1 = false;
      } else if (eingabe.equals("2")){
        System.out.println("MissingNo2");                              
        loop1 = false;
      } else if (eingabe.equals("3")){
        System.out.println("MissingNo3");                               
        loop1 = false;
      } else if (eingabe.equals("4")){
        System.out.println("MissingNo4");                              
        loop1 = false;
      }
    }             
    System.out.println("Attacken eingabe verfehlt");
    return 0;
  }
}