package projetAI;

import java.util.Scanner;

class test {
    static bloc[][] mat;
    static int n;
    
    public static void main(String[] args) {
   	 Scanner sc = new Scanner(System.in);
   	 
   	 System.out.print("entrez la taille de la matrice: ");
   	 n = sc.nextInt();
   	 
   	 mat = new bloc[n][n];
   	 for(int i=0; i<n; i++) {
   		 mat[i] = new bloc[n];
   		 for(int j=0; j<n; j++)
   			 mat[i][j] = new bloc();
   	 }
   	 
   	 System.out.print("\n entrez le nombre de puit: ");
   	 int pits = sc.nextInt();
   	 
   	 for(int i=0; i<pits; i++) {
   		 System.out.print("entrez la position du puit " + (i+1) + ": ");
   		 AjouPuit(n-sc.nextInt(), sc.nextInt()-1);
   	 }
   	 
   	 System.out.print("\n entrez la position du Wampus: ");
   	 AjouWumpus(n-sc.nextInt(), sc.nextInt()-1);
   	 
   	 System.out.print("\n entrez la position de l or: ");
   	 AjouOr(n-sc.nextInt(), sc.nextInt()-1);
   	 
   	 System.out.print("\nVotre emplacement actuel est : [1] [1]");
   	 int r = n - 1;
   	 int c = 1- 1;
   	 int rPrev = -1, cPrev = -1;
   	 
   	 int Deplacements = 0;
   	 System.out.println("\netat initial:");
   	 printmat(r, c);
   	 
   	 while(!mat[r][c].AOr) {
   		 mat[r][c].Visite = true;
   		 mat[r][c].StatutPuit = bloc.NonPresent;
   		 mat[r][c].StatutWampus = bloc.NonPresent;
   		 
   		 if(!mat[r][c].AFroid) {
   			 if(r >= 1 && mat[r-1][c].StatutPuit == bloc.PasSur)
   				 mat[r-1][c].StatutPuit = bloc.NonPresent;
   			 if(r <= (n-2) && mat[r+1][c].StatutPuit == bloc.PasSur)
   				 mat[r+1][c].StatutPuit = bloc.NonPresent;
   			 if(c >= 1 && mat[r][c-1].StatutPuit == bloc.PasSur)
   				 mat[r][c-1].StatutPuit = bloc.NonPresent;
   			 if(c <= (n-2) && mat[r][c+1].StatutPuit == bloc.PasSur)
   				 mat[r][c+1].StatutPuit = bloc.NonPresent;
   		 }
   		 
   		 if(!mat[r][c].APuanteur) {
   			 if(r >= 1 && mat[r-1][c].StatutWampus == bloc.PasSur)
   				 mat[r-1][c].StatutWampus = bloc.NonPresent;
   			 if(r <= (n-2) && mat[r+1][c].StatutWampus == bloc.PasSur)
   				 mat[r+1][c].StatutWampus = bloc.NonPresent;
   			 if(c >= 1 && mat[r][c-1].StatutWampus == bloc.PasSur)
   				 mat[r][c-1].StatutWampus = bloc.NonPresent;
   			 if(c <= (n-2) && mat[r][c+1].StatutWampus == bloc.PasSur)
   				 mat[r][c+1].StatutWampus = bloc.NonPresent;
   		 }
 
   		 boolean NouvChemain = false;
   		 
   		 if(r >= 1 && !((r-1) == rPrev && c == cPrev) && mat[r-1][c].Visite == false && mat[r-1][c].StatutPuit == bloc.NonPresent && mat[r-1][c].StatutWampus == bloc.NonPresent) {
   			 rPrev = r;
   			 cPrev = c;
   			 
   			 r--;
   			 NouvChemain = true;
   		 }
   		 else if(r <= (n-2) && !((r+1) == rPrev && c == cPrev) && mat[r+1][c].Visite == false && mat[r+1][c].StatutPuit == bloc.NonPresent && mat[r+1][c].StatutWampus == bloc.NonPresent) {
   			 rPrev = r;
   			 cPrev = c;
   			 
   			 r++;
   			 NouvChemain = true;
   		 }
   		 else if(c >= 1 && !(r == rPrev && (c-1) == cPrev) && mat[r][c-1].Visite == false && mat[r][c-1].StatutPuit == bloc.NonPresent && mat[r][c-1].StatutWampus == bloc.NonPresent) {
   			 rPrev = r;
   			 cPrev = c;
   			 
   			 c--;
   			 NouvChemain = true;
   		 }
   		 else if(c <= (n-2) && !(r == rPrev && (c+1) == cPrev) && mat[r][c+1].Visite == false && mat[r][c+1].StatutPuit == bloc.NonPresent && mat[r][c+1].StatutWampus == bloc.NonPresent) {
   			 rPrev = r;
   			 cPrev = c;
   			 
   			 c++;
   			 NouvChemain = true;
   		 }
   		 
   		 if(!NouvChemain) {
   			 int temp1 = rPrev;
   			 int temp2 = cPrev;
   			 
   			 rPrev = r;
   			 cPrev = c;
   			 
   			 r = temp1;
   			 c = temp2;
   		 }
   		 
   		 Deplacements++;
 
   		 System.out.println("\n\nDeplacement " + Deplacements + ":");
   		 printmat(r, c);
 
   		 if(Deplacements > n*n) {
   			 System.out.println("\nPas de solution!");
   			 break;
   		 }
   	 }
   	 
   	 if(Deplacements <= n*n)
   		 System.out.println("\nOr touve en  " + Deplacements + " Deplacements.");
   	 
   	 sc.close();
    }
    
    static void AjouPuit(int r, int c) {
   	 mat[r][c].APuit = true;
   	 
   	 if(r >= 1)
   		 mat[r-1][c].AFroid = true;
   	 if(r <= (n-2))
   		 mat[r+1][c].AFroid = true;
   	 if(c >= 1)
   		 mat[r][c-1].AFroid = true;
   	 if(c <= (n-2))
   		 mat[r][c+1].AFroid = true;
    }
    
    static void AjouWumpus(int r, int c) {
   	 mat[r][c].AWumpus = true;
   	 
   	 if(r >= 1)
   		 mat[r-1][c].APuanteur = true;
   	 if(r <= (n-2))
   		 mat[r+1][c].APuanteur = true;
   	 if(c >= 1)
   		 mat[r][c-1].APuanteur = true;
   	 if(c <= (n-2))
   		 mat[r][c+1].APuanteur = true;
    }
    
    static void AjouOr(int r, int c) {
   	 mat[r][c].AOr = true;
    }
    
    static void printmat(int r, int c) {
   	 for(int i=0; i<n; i++) {
   		 for(int j=0; j<n; j++) {
   			 char charToPrint = '-';
   			 if(r == i && c == j)
   				 charToPrint = '*';
   			 else if(mat[i][j].APuit)
   				 charToPrint = 'O';
   			 else if(mat[i][j].AWumpus)
   				 charToPrint = 'X';
   			 else if(mat[i][j].AOr)
   				 charToPrint = '$';
   			 
   			 System.out.print(charToPrint + "\t");
   		 }
   		 System.out.println();
   	 }
    }
}