package com.jeu.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solveur {
	
	private ArrayList<Noeud> liste_noeuds_ouverts;
	private ArrayList<Noeud> liste_noeuds_fermes;
	
	public Solveur()
	{
		liste_noeuds_ouverts=new ArrayList<Noeud>();
		liste_noeuds_fermes=new ArrayList<Noeud>();
	}
	
	public Grille chargerFichier(String nomFichier)
	{
		Grille g=null;
		int[][] grille=null;
		int taille=0;
		try
		{
			File file = new File(nomFichier);
			Scanner scanner = new Scanner(file);
			taille=scanner.nextInt();

			grille = new int [taille][taille];
			int i=0,j=0;
			while(scanner.hasNextInt())
			{
				//pour detecter la fin de la ligne
				if(j == taille)
				{
					i++;
					j=0;
				}
				grille[i][j] = scanner.nextInt();
				j++;
				
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int posi=2;
		int posj=2;
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				if(grille[i][j]==0)
				{
					posi=i;
					posj=j;
					break;
				}
			}
		}
		g=new Grille(grille,taille,posi,posj);
		
		return g;
	}
	
	//fonction pour trouver l meilleur moeud de la liste donnees
	public Noeud get_next_node(ArrayList<Noeud> list)
	{
		Noeud min_node=null;
		if(list.size()!=0)
		{
			min_node=list.get(0);
		}
			
		
		for(int i = 0; i < list.size(); i++) {   
		    if(list.get(i).f()<min_node.f())
		    {
		    	min_node=list.get(i);
		    }
		    	
		} 
		
		return min_node;
	}
	
	public Noeud algoAstar(Grille initial)
	{
		//1. On initialise un noeud avec la grille initiale et on le met dans la liste ouverte liste ouverte.
		Noeud noeud_init=new Noeud(initial, null, 0);
		this.liste_noeuds_ouverts.add(noeud_init);
		
		Noeud noeudCourant=noeud_init;
		//6. tant qu'on est pas arriver a l'etat finale et on a encore des successeurs
		while(!noeudCourant.estUnEtatFinal() && !liste_noeuds_ouverts.isEmpty())
		{
		
		//2. On cherche le meilleur noeud de la liste ouverte.
		noeudCourant = get_next_node(liste_noeuds_ouverts);
		
		//3. On ajoute noeudCourant a la liste fermee et on le retire de la liste ouverte.
		this.liste_noeuds_fermes.add(noeudCourant);
		this.liste_noeuds_ouverts.remove(noeudCourant);
		
		//4. On genere tous les noeuds successeurs du noeudCourant en deplacant la case vide.
		ArrayList<Grille> liste_succ=new ArrayList<Grille>();
		liste_succ=noeudCourant.successeurs();
		
		//5. 
		Noeud successeur;
		for(int i = 0; i < liste_succ.size(); i++) 
		{   
			
			successeur=new Noeud(liste_succ.get(i), noeudCourant, noeudCourant.g()+1);
			
			if (!check_in_liste_fermes(successeur))
			{
				Noeud n = check_in_liste_ouvertes(successeur);
				if(n==null)
				{
					liste_noeuds_ouverts.add(successeur);
				}
				else if(successeur.f()<n.f())
				{
					int pos=liste_noeuds_ouverts.indexOf(n);
					liste_noeuds_ouverts.set(pos,successeur);
				}
				  	
			}
		} 
		}
		
		if(noeudCourant.estUnEtatFinal())
		{
			
			return noeudCourant;
		}
		else
			return null;
	}
	
	public Noeud check_in_liste_ouvertes(Noeud node) {
        for(int j = 0; j < this.liste_noeuds_ouverts.size(); j++)
        {
            if (this.liste_noeuds_ouverts.get(j).getGrille().equals(node.getGrille()))
                return this.liste_noeuds_ouverts.get(j);
        }
        return null;
    }

    public boolean check_in_liste_fermes(Noeud node) {
    	boolean etat=false;
    	for(int j = 0; j < liste_noeuds_fermes.size(); j++)
        {
            if (liste_noeuds_fermes.get(j).getGrille().equals(node.getGrille())) 
            {
                etat= true;
                break;
            }
        }
        return etat;
    }
	
	public ArrayList<Grille> cheminComplet(Noeud noeudFinal)
	{
		
		ArrayList<Grille> gri=new ArrayList<Grille>();
		Noeud node = noeudFinal;
		//ajouter la grille du noeud final a la liste
		gri.add(noeudFinal.getGrille());
		
		// nombre de mouvements est g car on l'incremente a chaque fois on a generer un successeur
        System.out.println("nombre de mouvements est : " + noeudFinal.g());
        System.out.print("le Chemin est : \n");
        // tant que le noeud il a un pere on ajoute la grille de ce pere a la liste 
        while (node.getPere() != null){
        	node = node.getPere();
            gri.add(node.getGrille());
        }
        
		return gri;
	}

	
	public static void main(String[] args )
	{
		Solveur s=new Solveur();
		
		Grille g = s.chargerFichier("puzzles/puzzle04.txt");
		
		System.out.print("la grille du jeu est \n"+g);

		System.out.print("\n ------------------------- \n");
		
		Noeud n= s.algoAstar(g);
		
		if(n==null)
			System.out.print("le resultat apres A star : Pas de Solution \n");
		else
			System.out.print("le resultat apres A star est :\n"+n.getGrille());
		
		if(n!=null)
		{
			ArrayList<Grille> lg=s.cheminComplet(n);
			for(int i=lg.size()-1;i>=0;i--)
			{
				System.out.print("----------------------------------- \n");
				System.out.print(" "+lg.get(i));
			}
				
		}	
	}

}
