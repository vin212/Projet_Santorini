package save;

import modele.*;
import global.*;
import controleurIA.*;
import interfaceUser.*;

import java.io.File;
import structure.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

//import javax.lang.model.util.ElementScanner6;

public class Save {
    
    Jeu jeu;
    Configuration prop;
    IA ia1;
    IA ia2;

    Fenetres fen;

    /*public Save(Jeu j){
        this.jeu = j; 
    }*/
    public Save(Jeu j, Configuration prop, Fenetres f)
    {
        this.jeu = j; 
        this.prop = prop;
        this.fen = f;
    }

    public Save(Jeu j, Configuration prop, IA ia1, IA ia2){
        this.jeu = j; 
        this.prop = prop;
        this.ia1 = ia1;
        this.ia2 = ia2;
    }

    public int sauver(String name){
        String tmp = "sauvegardes/"+name+".data";
        File saveFile = new File(tmp);
        try{
            boolean res = saveFile.createNewFile();
            if(!res){
                prop.envoyerLogger("Erreur à la création! Le fichier existe-t'il déjà?",TypeLogger.WARNING);
                return 1;
            }
        }
        catch(Exception e){
            prop.envoyerLogger("Erreur à la création!",TypeLogger.WARNING);
            return -1;
        }

        FileWriter writer;

        try{
            if (jeu.getTour() >= 2)
            {
            writer = new FileWriter(tmp);

            //Ecriture du plateau
            int hauteur = jeu.getHauteurPlateau();
            int largeur = jeu.getLargeurPlateau();
            writer.write(hauteur+" "+largeur + "\n");
            for (int j = 0; j < hauteur; j++){
                for(int i = 0; i < largeur; i++){
                    Point p = new Point(i,j);
                    int tailleTour = jeu.getNbEtage(p);
                    writer.write(tailleTour+" ");
                }
                writer.write("\n");
            }
            writer.write("\n");

            //Ecriture du tour courant
            writer.write(jeu.getTour()+"\n");


            //Ecriture de la pos des joueurs
            for (int i = 1; i <= 2; i++){
                Point[] pts = jeu.getPosiPions(i);
                
                writer.write(pts[0].getx()+ " "+ pts[0].gety()+"  ");
                writer.write(pts[1].getx()+ " "+ pts[1].gety());
                writer.write("\n");
            }
            
            //Ecriture de l'histo
            Integer posHisto = jeu.histoPosition();
            Historique histo = jeu.histo();
            writer.write(posHisto+"\n");
            Integer tailleHisto = histo.getTaille();
            writer.write(tailleHisto+"\n");
            for (int i = 0; i < tailleHisto; i++){
                Coup coup = new Coup();
                coup = histo.obtenirCoup(i);
                Point pi, pa, pc;
                Integer joueur;
                pi = coup.getDepart();
                if(coup.getArrive() != null)
                    pa = coup.getArrive();
                else 
                    pa = new Point(-1,-1);
                if(coup.getConstruction() != null)
                    pc = coup.getConstruction();
                else
                    pc = new Point(-1,-1);
                joueur = (Integer)coup.getJoueur();
                writer.write(pi.getx()+" "+pi.gety()+"  ");
                if (pa != null && pc != null)
                {
                    writer.write(pa.getx()+" "+pa.gety()+"  ");
                    writer.write(pc.getx()+" "+pc.gety()+"  "); // 1 1  2 2  3 3  1
                    
                }
                writer.write(joueur+" ");
                writer.write("\n");
            }
                if (ia1 == null){
                    writer.write(0 + "\n");
                } else if (ia1.type().equals("IA Facile")) {
                    writer.write(1 + "\n");
                }else if (ia1.type().equals("IA Normal")) {
                    writer.write(2 + "\n");
                }else if (ia1.type().equals("IA Difficile")) {
                    writer.write(3 + "\n");
                }
            
                if (ia2 == null){
                    writer.write(0 + "\n");
                } else if (ia2.type().equals("IA Facile")) {
                    writer.write(1 + "\n");
                }else if (ia2.type().equals("IA Normal")) {
                    writer.write(2 + "\n");
                }else if (ia2.type().equals("IA Difficile")) {
                    writer.write(3 + "\n");
                }
            
                writer.close();
            }
        
            else
            {
                prop.envoyerLogger("interdi de save !",TypeLogger.WARNING);
                return 2;
            }
        }
        catch(java.io.IOException e){
            new RuntimeException("Erreur", e).printStackTrace(); 
            return -1;  
        }
        return 0;
    }

    public void supprSave(String name){
        String tmp = "../sauvegardes/"+name+".data";
        File f = new File(tmp);
        if (f.exists()){
            f.delete();
            prop.envoyerLogger("Sauvegarde "+name+" supprimée!",TypeLogger.INFO);
        }
        else{
            prop.envoyerLogger("Cette sauvegarde n'existe pas!",TypeLogger.WARNING);
        }
    }

    public ArrayList<String> lesSauvegardes(){
        ArrayList<String> saves = new ArrayList<String>();
        File dossier = new File("sauvegardes");

        for (File file : dossier.listFiles()){
            String nom = file.getName();
            String[] nameCut = nom.split("\\.");
            saves.add(nameCut[0]);
        }

        return saves;
    }

    public Jeu chargerSauvegarde(String name){
        String save = "sauvegardes/"+name+".data";
        Jeu jeu = new Jeu(prop);

        File f = new File (save);
        Scanner scanner;
        try{
            scanner = new Scanner(f);
            //Initialisation du plateau
            int hauteur = scanner.nextInt();
            int largeur = scanner.nextInt();

            //Disposition des constructions
            for (int j = 0; j < hauteur ; j++){
                for (int i = 0; i < largeur ; i++){
                    int constru = scanner.nextInt();
                    for (int k = 0; k < constru; k++){
                        jeu.Construire(new Point(i,j));
                    }
                }
            }

            //Définition du tour
            jeu.setTour(scanner.nextInt());
            jeu.subTour();
            jeu.calculJoueurEnJeu();

            //Placement des joueurs
            for (int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Point pt = new Point(x,y);        
                    jeu.poserPersonnage (pt,i+1);       
                }
            }

            int curseurHisto = scanner.nextInt();
            int nbligne = scanner.nextInt();

            for(int i = 0; i < nbligne; i++){
                Coup coup;

                int xi = scanner.nextInt();
                int yi = scanner.nextInt();
                Point Pti = new Point(xi, yi);

                int xa = scanner.nextInt();
                int ya = scanner.nextInt();
                Point Pta = new Point(xa, ya);

                int xc = scanner.nextInt();
                int yc = scanner.nextInt();
                Point Ptc = new Point(xc, yc);

                int numJoueur = scanner.nextInt();
                if(!(xa == -1 || ya == -1)){
                    coup = new Coup(Pti, Pta, Ptc, numJoueur);
                }
                else
                {
                    coup = new Coup(Pti, numJoueur);
                }

                jeu.histoAjouterCoup(coup);

            }
            jeu.setPosition(curseurHisto);

            int numIA1  = scanner.nextInt();
            int numIA2  = scanner.nextInt();
            if (numIA1 == 0){ 
                fen.ia1 = null;
            } else if (numIA1 == 1) {
                fen.ia1 = IA.nouvelle(jeu,prop.recupValeur("IAFacile"),"IA Facile");
                fen.ia1.activeIA();
            } else if (numIA1 == 2){
                fen.ia1 = IA.nouvelle(jeu,prop.recupValeur("IANormal"),"IA Normal");
                fen.ia1.activeIA();
            } else if (numIA1 == 3){
                fen.ia1 = IA.nouvelle(jeu,prop.recupValeur("IADifficile"),"IA Difficile");
                fen.ia1.activeIA();
            }
            if (numIA2 == 0){ 
                fen.ia2 = null;
            } else if (numIA2 == 1) {
                fen.ia2 = IA.nouvelle(jeu,prop.recupValeur("IAFacile"),"IA Facile");
                fen.ia2.activeIA();
            } else if (numIA2 == 2){
                fen.ia2 = IA.nouvelle(jeu,prop.recupValeur("IANormal"),"IA Normal");
                fen.ia2.activeIA();
            } else if (numIA2 == 3){
                fen.ia2 = IA.nouvelle(jeu,prop.recupValeur("IADifficile"),"IA Difficile");
                fen.ia2.activeIA();
            }

            if (fen.ia1 != null)
            {
                jeu.addTour();
                jeu.setAction(1, Action.A_DEPLACER);
                jeu.calculJoueurEnJeu();
            }
            else if (fen.ia2 != null)
            {
                jeu.addTour();
                jeu.setAction(2, Action.A_DEPLACER);
                jeu.calculJoueurEnJeu();
            }


            scanner.close();
            
        }
        catch (Exception expt)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + expt);
        }
        return jeu;
    }

}