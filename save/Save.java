package save;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Save {
    
    static int nbSauvegardes = 0;

    public void sauver(String name){
        String tmp = "sauvegardes/"+name+".data";
        File saveFile = new File(tmp);
        try{
            boolean res = saveFile.createNewFile();
            if(!res){
                System.out.println("Erreur à la création! Le fichier existe-t'il déjà?");
            }
        }
        catch(Exception e){
            System.out.println("Erreur à la création!");
        }

        FileWriter writer = new FileWriter(saveFile);

        //Ecriture du plateau
        int hauteur = getHauteurPlateau();
        int largeur = getLargeurPlateau();
        writer.write(hauteur+"*"+largeur);
        for (int i = 0; i < hauteur; i++){
            for(int j = 0; j < largeur; j++){
                Point p = new Point(i,j);
                int tailleTour = getNbEtage(p);
                writer.write(tailleTour+" ");
            }
            writer.write("\n");
        }
        writer.write("\n");

        //Ecriture de la pos des joueurs
        for (int i = 1; i <= 2; i++){
            Point[] pts = getPosiPions(i);
            writer.write(pts[1].getx+ " "+ pts[1].gety);
            writer.write(pts[2].getx+ " "+ pts[2].gety);
            writer.write("\n");
        }
        
        //Ecriture de l'histo

        this.nbSauvegardes++;
    }

    public void supprSave(String name){
        String tmp = "sauvegardes/"+name+".data";
        File f = new File(tmp);
        if (f.exists()){
            f.delete();
            System.out.println("Sauvegarde "+name+" supprimée!");
        }
        else{
            System.err.println("Cette sauvegarde n'existe pas!");
        }
        this.nbSauvegardes--;
    }

}