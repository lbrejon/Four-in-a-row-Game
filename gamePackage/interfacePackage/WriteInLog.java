package gamePackage.interfacePackage;

import java.io.File;
import java.io.FileWriter;

public class WriteInLog{

  // Creation of the log.txt file
  public static void createLog(){
    String filename = "log.txt";

    try{
      File fileToCreate = new File(filename);
      fileToCreate.delete();      
    }
    catch (Exception e){
      System.err.println(e);
    }
  }

  // Writing in log.txt
  public static void writeBuffer(String buffer){
    String filename = "log.txt";
    try{
      FileWriter fileToWrite = new FileWriter(filename, true);
      fileToWrite.write(buffer+"\n");
      fileToWrite.close();
    }
    catch (Exception e){
      System.err.println(e);
    }
  }
}
