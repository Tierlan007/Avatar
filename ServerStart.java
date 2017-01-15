package avatar;

import java.util.*;
import java.io.*;
import java.net.*;

public class ServerStart {
  public static int HIGHT = 800;
  public static int WIDTH = 600;
  
  public InetAddress addr;
  public int clientCounter = 0;
  FileWriter fw;
  BufferedWriter logger;  
                       
  
  ArrayList<PrintWriter> clientWriter;
  ArrayList<InetAddress> inetAdrr;
  ArrayList<Integer> clientAvatar;
  ArrayList<Integer> queueArray;
  ArrayList<Match> matches;
  ArrayList<Integer> match_num;
  
  public boolean queue_state;
  public boolean loop1 = true; //serverClient() ClientHandler
  public boolean queue_super = true;
  
  public int queueSucher = 0;
  
  
  public class clientHandler implements Runnable{
    BufferedReader reader;
    Socket sock;
    int position; 
    int numMatch = 0;
    
    public clientHandler(Socket clientSock, int pos){
      position = pos;
      try {
        sock = clientSock;
        InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(isReader);
      } catch(Exception e) {
        e.printStackTrace();
      } 
      logging("Connected\t\t: "+inetAdrr.get(position).getHostName());
      System.out.println("Connecting to: "+inetAdrr.get(position).getHostName());
    }                                                  
      
    public void run(){
      String message;
      
      try {
        while ((message = reader.readLine()) != null) { 
          if (queueArray.get(position) == 2) {
            if (numMatch == 0) {
              numMatch = Integer.parseInt(message);
              einzelnenClientAnsprechen("24",position);
            }else {
              matches.get(numMatch-1).sort(message);
            } 
          } else {       
            sort(message,position);
          } 
        } 
      } catch(Exception e) {
        System.out.println("Connection Lost to "+inetAdrr.get(position).getHostName());
        logging("Connection Lost\t\t: "+inetAdrr.get(position).getHostName());
      } 
    }
    
  }
  
  public void sort(String message, int pos){
    int position = pos;
    int tmp = 0;
    char[] comp_message = message.toCharArray();
    String just_message = "";
    String messageCode = "";
    messageCode+= comp_message[0];   
    messageCode+= comp_message[1];
    for (int i = 0;i<comp_message.length-2;i++ ) {
      just_message += comp_message[i+2];
    } 
    
    switch (messageCode) {
      case  "00": 
        System.out.println(message);
        
        break;
      case  "01":     
        System.out.println("SERVER : "+message);
        break;
      case  "02":     
        break;        
      case "11":    
        System.out.println(inetAdrr.get(pos).getHostName()+" : "+message);
        messageWeiterleiten(inetAdrr.get(pos).getHostName()+" : "+just_message);
        
        break;  
      case "12":
        System.out.println(inetAdrr.get(pos).getHostName()+" : "+message);   
        break;
      case "21" :                                                                                                                           
        tmp = Integer.parseInt(just_message);
        clientAvatar.set(position,tmp);
        break;
      case "31" :                 
        queueArray.set(position,1);
        System.out.println("Spieler: "+inetAdrr.get(pos).getHostName()+" betrat die queue");
        queueSucher += 1;
        break;   
      default:        
        
    } 
  }
  
  public void los(){
    
    Thread s_thread = new Thread(new ServerProg());
    s_thread.start();
    clientWriter = new ArrayList<PrintWriter>();
    inetAdrr = new ArrayList<InetAddress>();
    clientAvatar = new ArrayList<Integer>();
    queueArray = new ArrayList<Integer>();
    match_num = new ArrayList<Integer>();
    
    try {
      ServerSocket serverSocket = new ServerSocket(4242);
      while (loop1 == true) { 
        Socket clientSocket = serverSocket.accept();
        inetAdrr.add(clientSocket.getInetAddress());
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
        clientWriter.add(writer);
        clientAvatar.add(0);
        queueArray.add(999);
        match_num.add(0);
        Thread t = new Thread(new clientHandler(clientSocket,clientCounter));
        t.start();
        System.out.println("Connecttion ready to: "+inetAdrr.get(clientCounter).getHostName());  
        try {  
          einzelnenClientAnsprechen("235",clientCounter);
        } catch(Exception ex) {
          ex.printStackTrace();   
        } 
        clientCounter++;
      } 
    } catch(Exception e) {
      e.printStackTrace();
    } 
  }
  
  public class Matchqueue implements Runnable{
    public Matchqueue(){
      
    }
    
    public void run(){
      matches = new ArrayList<Match>();
      queue_state = true;
      queue_super = true;
      int queueplay1;
      int queueplay2;
      int anzahlMatch = 0;                                                              
      Socket sock1;
      Socket sock2;
      System.out.println("Matchqueue aktiv");
      while (queue_super == true) {   
        
        queueplay1 = clientCounter+1;
        queueplay2 = clientCounter+1;
        
        if (queue_state == true) {
          if (queueSucher >= 2) {
            for (int i = 0;i<clientCounter ;i++ ) {
              if (queueArray.get(i) == 1) {
                queueplay1 = i;
                i = clientCounter+1;
              } 
            }
            for (int i = 0;i<clientCounter ;i++ ) {
              if (queueArray.get(i) == 1 && i != queueplay1) {
                queueplay2 = i;
                i = clientCounter+1;
              } 
            } 
            if (queueplay1 != clientCounter+1 && queueplay2 != clientCounter) {
              System.out.println("creating Match");  
              matches.add(new Match());
              matches.get(anzahlMatch).start(clientWriter.get(queueplay1),clientWriter.get(queueplay2),queueplay1,queueplay2,anzahlMatch);
              queueArray.set(queueplay1,2); 
              queueArray.set(queueplay2,2);
              match_num.set(queueplay1,1);
              match_num.set(queueplay2,1);                 
              einzelnenClientAnsprechen("21"+(anzahlMatch+1),queueplay1);
              einzelnenClientAnsprechen("21"+(anzahlMatch+1),queueplay2);
              queueplay2 = clientCounter+1;
              queueplay1 = clientCounter+1;
              queueSucher -= 2;
              anzahlMatch++;
            } else {
              System.out.println("Match Making failed");
            } 
          } else {
            
          } 
          
        } else {
          
        } 
        
      }
      System.out.println("Matchqueue deaktiviert"); 
    }  
  }
  
  public class ServerProg implements Runnable{
    public ServerProg(){
      
    }
    
    public void run(){
      Scanner scan = new Scanner(System.in);
      Thread queue;
      
      try {
        fw = new FileWriter("log.txt");
        logger = new BufferedWriter(fw); 
      } catch(Exception e) {
        e.printStackTrace();
      }
      
      queue = new Thread(new Matchqueue());
      queue.start();
      
      while (true) { 
        String message = scan.next();
        if (message.equals("matchqueue")) {
          queue_state = true;
        }else if (message.equals("closequeue")){ 
          queue_state = false;
        } else{
          message = message;
          messageWeiterleiten("01SERVER : "+message);
          sort("01"+message,0);
        }
      } 
    }  
  } 
  
  public void messageWeiterleiten(String mes){
    Iterator it = clientWriter.iterator();
    while (it.hasNext()) { 
      try {
        PrintWriter writer = (PrintWriter) it.next();
        writer.println(mes);
        writer.flush();
      } catch(Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void einzelnenClientAnsprechen(String mes, int clnr){
    try {
      PrintWriter writer = clientWriter.get(clnr);
      writer.println(mes);
      writer.flush();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public void logging(String log){
    String tmp = log;
    try {
      logger.write(tmp);
      logger.newLine();
      logger.flush();
    } catch(Exception e) {
      e.printStackTrace();
    } 
    
  }
  
  public static void main(String[] args){
    new ServerStart().los();
  }
  
  public ArrayList<PrintWriter> getclientWriter(){
    return clientWriter;
  }
  
  public ArrayList<InetAddress> inetAdrr(){
    return inetAdrr;
  }
  
  public ArrayList<Integer> getclientAvatar(){
    return clientAvatar;
  }
  
}