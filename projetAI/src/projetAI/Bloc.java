package projetAI;

public class Bloc {
    public static final int NonPresent = 0;
    public static final int PasSur = 1;
    public static final int Fleche = 1;
    
    public boolean AFroid, APuit;
    public int StatutPuit = PasSur;
    
    public boolean APuanteur, AWumpus;
    public int StatutWampus = PasSur;
    
    public boolean AOr;
    
    public boolean Visite;
}