package avatar;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClientStart{

  BufferedReader reader;
  PrintWriter writer;
  Socket sock;
  
  public ClientMatch clMa;
  
  public int Str_ava;
  public int Int_ava;
  public int Ges_ava;
  public int player_num;
  public int queue;
  public int winner = 0;                //1 = gewonnen 2 = verloren 0 = nicht zugewiesen
  public int warten = 0;
  public String NAME_AVA;
  
  public void los(){
    netzwerkVerbinden(); 
    Thread eingehend = new Thread(new inReader());
    eingehend.start();
    writer.flush();
    
    boolean loop1 = true;
    boolean loop2 = true;
    int tmp = 0;
    Scanner scan = new Scanner(System.in);
    
    while (loop1 == true) { 
      String eingabe = "";
      
      System.out.println("Waehlen sie ihren Avatar: ");
      System.out.println("<1> Aang      (Allrounder)");
      System.out.println("<2> Korra     (Inteligenz)");
      System.out.println("<3> Roku      (Staerke)");
      System.out.println("<4> Kyoshi    (Geschik)");
      System.out.println("Type 'exit' to exit the game");
      while (loop2 == true) { 
        eingabe = scan.next();
        if (eingabe.equals("1") || eingabe.equals("2") || eingabe.equals("3") || eingabe.equals("4")) {
          writer.println("21"+eingabe);
          writer.flush();
          loop2 = false;
          Avatar_stats(eingabe);
        }else if (eingabe.equals("exit")){
          System.exit(0);
        }else {
          System.out.println("Bitte geben sie einen richtigen Wert ein");
        } 
      } 
      queue = 2;
      tmp = Integer.parseInt(eingabe);
      writer.println("31"+tmp);
      writer.flush();
      System.out.println("Ein Gegner wird für sie gesucht, bitte warten");
      while (queue == 2) { 
        
      } 
      System.out.println("Match gefunden");
      queue = 1; 
      warten = 1;
      while (warten == 1) { 
        
      } 
      warten = 1;
      if (player_num == 1) {
        writer.println("01"+Str_ava);
        writer.flush(); 
        writer.println("02"+Int_ava);
        writer.flush();
        writer.println("03"+Ges_ava);
        writer.flush();
        
      } else {
        while (warten == 1) { 
        } 
        writer.println("11"+Str_ava);
        writer.flush();       
        writer.println("12"+Int_ava);
        writer.flush();
        writer.println("13"+Ges_ava);
        writer.flush();   
        
      } 
      while (warten == 1) { 
      }
      warten= 1;
      while (warten == 1) { 
      }
      warten= 1;
      clMa = new ClientMatch(this,Str_ava,Int_ava,Ges_ava, player_num);
      System.out.println("Out of Match");
      if (winner == 1) {   
        
      }else if (winner == 2){
        outWriter("9"+player_num,"");
      }  
      while (warten == 1) { 
      }
      warten= 1;
      loop1 = false;
    } 
  }
  
  public void Avatar_stats(String tmp){
    Random rng = new Random();
    
    if (tmp.equals("1")) {
      Str_ava = rng.nextInt(40-30+1)+30;
      Int_ava = rng.nextInt(40-30+1)+30;
      Ges_ava = rng.nextInt(40-30+1)+30;
      NAME_AVA = "Aang";
    }else if (tmp.equals("2")) {
      Str_ava = rng.nextInt(30-20+1)+20;
      Int_ava = rng.nextInt(60-50+1)+50;
      Ges_ava = rng.nextInt(30-20+1)+20;  
      NAME_AVA = "Korra";
    }else if (tmp.equals("3")) {
      Str_ava = rng.nextInt(60-50+1)+50;
      Int_ava = rng.nextInt(30-20+1)+20;
      Ges_ava = rng.nextInt(30-20+1)+20;  
      NAME_AVA = "Roku";
    }else if (tmp.equals("4")) {
      Str_ava = rng.nextInt(30-20+1)+20;
      Int_ava = rng.nextInt(30-20+1)+20;
      Ges_ava = rng.nextInt(60-50+1)+50;   
      NAME_AVA = "Kyoshi";
    }
  }
  
  public void netzwerkVerbinden(){
    try {
      sock = new Socket("127.0.0.1",4242);
      InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
      reader = new BufferedReader(streamReader);
      writer = new PrintWriter(sock.getOutputStream());
      System.out.println("Verbindung steht");
    } catch(Exception e) {
      System.out.println("keine Verbindung zum Server möglich\nVersuchen sie es später erneut");
      System.exit(0);
    } 
  }
  
  public class inReader implements Runnable{
    public inReader(){
      
    }
      
    public void run(){
      String nachricht;
      try {
        while ((nachricht = reader.readLine()) != null) { 
          sort(nachricht);
        } 
        
      } catch(Exception e) {}   
    }
  }
  
  public void outWriter(String messageCode,String message){
    String s = messageCode + message;
    writer.println(s);
    writer.flush();
    }
  
  public void sort(String s){
    char[] comp_message = s.toCharArray();
    String just_message = "";
    String messageCode = "";
    messageCode += comp_message[0];
    messageCode += comp_message[1]; 
    for (int i = 0;i<comp_message.length-2 ;i++ ) {
      just_message += comp_message[i+2];
    } 
    switch (messageCode) {
      case "01": 
        System.out.println(just_message);
        break;
      case "11":
        System.out.println(just_message);
        break;
      case "21":          
        writer.println(just_message);
        writer.flush();
        
        break;
      case "22":
        player_num = Integer.parseInt(just_message);
        System.out.println("player_num : "+player_num);
        break;
      case "23":
        warten = 0;
        break;  
      case "24":
        queue = 1;
        
        break; 
      case "81":
        clMa.setWarten(2);
        break;
      case "91":
        winner = 1;
        break;    
      default: 
        
    } 
    
  }
  
  public void setWinner(int i){
    winner = i;
    }
  
  public static void main(String[] args){
    new ClientStart().los();
  }
}