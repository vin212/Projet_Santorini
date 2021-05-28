package save;

import modele.*;
import java.io.File;
import structure.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

//import javax.lang.model.util.ElementScanner6;

public class Save {
    
    Jeu jeu;

    public Save(Jeu j){
        this.jeu = j; 
    }

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
            System.err.println("Erreur à la création!");
        }

        FileWriter writer;

        try{
            writer = new FileWriter(tmp);

            //Ecriture du plateau
            int hauteur = jeu.getHauteurPlateau();
            int largeur = jeu.getLargeurPlateau();
            writer.write(hauteur+" "+largeur + "\n");
            for (int i = 0; i < hauteur; i++){
                for(int j = 0; j < largeur; j++){
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
                System.out.println("pts : " + pts + "writer : "+ writer);
                writer.write(pts[0].getx()+ " "+ pts[0].gety()+"  ");
                writer.write(pts[1].getx()+ " "+ pts[1].gety());
                writer.write("\n");
            }
            
            //Ecriture de l'histo
            Integer posHisto = jeu.histoPosition();
            //System.out.println("\nPosition de l'histo : "+ posHisto+"\n");
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
                //System.out.println("\nJoueur : "+ joueur+" \n");
                writer.write(pi.getx()+" "+pi.gety()+"  ");
                if (pa != null && pc != null)
                {
                    writer.write(pa.getx()+" "+pa.gety()+"  ");
                    writer.write(pc.getx()+" "+pc.gety()+"  "); // 1 1  2 2  3 3  1
                    
                }
                writer.write(joueur+" ");
                writer.write("\n");
            }
            writer.close();
        }
        catch(java.io.IOException e){
            new RuntimeException("Erreur", e).printStackTrace();    
        }
    }

    public void supprSave(String name){
        String tmp = "../sauvegardes/"+name+".data";
        File f = new File(tmp);
        if (f.exists()){
            f.delete();
            System.out.println("Sauvegarde "+name+" supprimée!");
        }
        else{
            System.err.println("Cette sauvegarde n'existe pas!");
        }
    }

    public ArrayList<String> lesSauvegardes(){
        ArrayList<String> saves = new ArrayList<String>();
        File dossier = new File("sauvegardes");

        for (File file : dossier.listFiles()){
            String nom = file.getName();
            System.out.println("nom : " + nom.split(".d"));
            String[] nameCut = nom.split(".d");
            saves.add(nameCut[0]);
        }

        return saves;
    }

    public Jeu chargerSauvegarde(String name){
        String save = "sauvegardes/"+name+".data";
        Jeu jeu = new Jeu();

        File f = new File (save);
        Scanner scanner;
        try{
            scanner = new Scanner(f);

            //Initialisation du plateau
            int hauteur = scanner.nextInt();
            int largeur = scanner.nextInt();
            Plateau p = new Plateau(hauteur, largeur);

            //Disposition des constructions
            for (int i = 0; i < 5 ; i++){
                for (int j = 0; j < 5 ; j++){
                    Case nvCase = new Case();
                    int constru = scanner.nextInt();
                    for (int k = 0; k < constru; k++){
                        nvCase.ajoutEtage();
                    }
                    p.cases[i][j] = nvCase;
                }
            }

            //Définition du tour
            jeu.setTour(scanner.nextInt());

            //Placement des joueurs
            for (int i = 0; i < 2; i++){
                Joueur joueur = new Joueur();
                for (int j = 0; j < 1; j++){
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Point pt = new Point(x,y);        
                    if (i == 0)
                        joueur.placerPerso1(pt);
                    else
                        joueur.placerPerso2(pt);
                    jeu.poserPersonnage(pt, i);        
                }
                jeu.setJoueur(i, joueur);
            }

            //Chargement de l'historique
            Historique histo = new Historique();

            while (scanner.hasNextInt()){
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
                    coup = new Coup(Pti, numJoueur);

                histo.ajouteCoup(coup);

                }
            jeu.setHisto(histo);
            scanner.close();

        }
            
        catch(Exception e){
            System.err.println("Erreur lors de la lecture du fichier");
        }       

        return jeu;
    }

}