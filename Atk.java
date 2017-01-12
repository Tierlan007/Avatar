package avatar;
 
import avatar.Character.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class Atk{
  
  public Random rng = new Random();
  
  public double Attacken;
  
  public Atk(){
    
  }
    
  public double Airslash(int Str, int Ges){
    double tmp = rng.nextInt((100-1)+1)+1;
    if (tmp < Ges/10) {
      return ((((Str/100)*40) + ((Ges/100)*60)));
    } else {
      return ((Str/100)*20) + ((Ges/100)*30);
    }
  }
  
}