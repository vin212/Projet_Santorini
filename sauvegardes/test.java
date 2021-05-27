package sauvegardes;

import java.util.Scanner;
import java.io.File;

public class test {
    
    public static void main(String[] args){
        File file = new File("C:\\Users\\natha\\Documents\\Etudes\\L3\\Projet_Santorini\\sauvegardes\\test.data");
        Scanner scan;
        try{
            scan = new Scanner(file);
            while(scan.hasNext()){
                int val = scan.nextInt();
                System.out.println(val);
            }
        } 
        catch(Exception e){
            System.err.println("marche pas");
        }
        
    }

}
