package avatar.Attacken;

import java.util.*;
import java.io.*;
import java.net.*;
import avatar.*;

public class Admin_Zusatz{
  
  public double tmp;
  
  private Match match;
  private double Str_t; 
  private double Int_t;
  private double Ges_t;
  private int plNr_t;
  private int atkNr_t;
  
  public Admin_Zusatz (Match m, int Str, int Int, int Ges, int plNr, int atkNr){
    setMatch(m);
    setStr_t(Double.parseDouble(Str+""));
    setInt_t(Double.parseDouble(Int+""));
    setGes_t(Double.parseDouble(Ges+""));
    setplNr_t(plNr);
    setatkNr_t(atkNr);
    
    if (atkNr_t == 900) {Insta_KO();} 
  }
  
  public void Insta_KO(){}  
  
  public void setMatch (Match m){
    match = m;
  }
  public void setStr_t (Double d){
    Str_t = d;
  }
  public void setInt_t (Double d){
    Int_t = d;
  }
  public void setGes_t (Double d){
    Ges_t = d;
  }
  public void setplNr_t (int i){
    plNr_t = i;
  } 
  public void setatkNr_t (int i){
    atkNr_t = i;
  }   
  
}