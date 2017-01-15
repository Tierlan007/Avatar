package avatar;

import avatar.Character.*;
import java.util.*;
import java.net.*;
import java.io.*;

public interface Avatare{
  public int Attacken();
  public void calcu_stats(); 
  public int getSTR();      
  public int getINT(); 
  public int getGES(); 
  public String getNAME();
  public ClientStart getMyClient();
}