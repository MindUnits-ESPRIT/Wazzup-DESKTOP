/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Misow3002
 */
public class Anti_BadWords {
    public String TextAnalyzer(String str)
    {
         String Result="";
            File F=new File("C:/Users/Misow3002/Documents/NetBeansProjects/Insult detector/insults.txt");
     try {
         Scanner Scan=new Scanner(F);
         List<String>FC=new ArrayList<String>();
         while(Scan.hasNextLine())
         FC.add(Scan.nextLine());
         String[] arrOfStr = str.split(" ");
         String Hash="";
         boolean Found;
        
         int L = 0;
         for (String S : arrOfStr)
         {
            Found=false;
            for (String P:FC)
           {
             L=P.length();
             // System.out.println(S.equalsIgnoreCase(P) +" EL INDEX OF" +S.indexOf(P,0) );
             //  System.out.println("EL LAST INDEX : "+P.lastIndexOf(S,P.length()-1));
             if ((S.equalsIgnoreCase(P)) || (S.indexOf(P,0)==0 && S.lastIndexOf(P,P.length()-1)==0))
             {
                 
                 System.out.println("Insult Typed : "+S+" Insult Found In textFile: "+P);
                 Found=!Found;
                 break;
             }
          //     System.out.println("Counter : "+i);
     //         System.out.println("Compare :"+S.compareToIgnoreCase(P));
           }
            
           if (!Found)
           {
               Result=Result.concat(S+" ");
           }
           else
           {
            int j=0;
            Hash="";
            while(j<L)
            {
               Hash+="*";
                j++;
            
            }
           Result=Result.concat(Hash+" "); 
           }
             
         }
         System.out.println(Result);
     } catch (FileNotFoundException ex) {
         Logger.getLogger(Anti_BadWords.class.getName()).log(Level.SEVERE, null, ex);
     }
        return Result;
    
    }
    }

