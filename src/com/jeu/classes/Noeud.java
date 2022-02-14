package com.jeu.classes;

import java.util.ArrayList;

public class Noeud {
	
	private Grille grille;
	private Noeud pere;
	private int g;
	
	public Noeud(Grille grille,Noeud p,int g)
	{
		this.grille=grille;
		pere=p;
		this.g=g;
	}
	
	public Grille getGrille()
	{
		return this.grille;
	}
	public Noeud getPere()
	{
		return this.pere;
	}
	
	public int h()
	{
		//pour h=h1
		int somme=0;
		int case_value;
		int current_value;
		for(int i=0;i<grille.getTaille();i++)
		{
			for(int j=0;j<grille.getTaille();j++)
			{
				//calcule de la vrai valeur de la case
				case_value=(grille.getTaille()*i+j+1);
				
				//la valeur actuel de la case
				current_value=(grille.getValeur(i, j));
				
				//si la case n'est pas vide
				if(grille.getValeur(i, j)!=0)
				{
					if(case_value!=current_value)
						somme++;
				}
			}
		}
		return somme;
	}
	
	public int g()
	{
		return g;
	}
	
	public int f()
	{
		return this.g()+this.h();
	}
	
	public boolean estUnEtatFinal()
	{
		boolean etat=false;
		
		  if(this.h()==0)
		 	etat= true;
		 return etat;
		 
		
	}	
	
	//public ArrayList<Noeud> successeurs()
	public ArrayList<Grille> successeurs()
	{
		ArrayList<Grille> successeurs=new ArrayList<Grille>();
		int col0=this.grille.getColonne0();
		int lig0=this.grille.getLigne0();
		int taille=this.grille.getTaille();
		int[][] grille=this.grille.copier();
		Grille g=null;
		
		//dans ce cas on a 4 successeurs
		if(col0!=0 && lig0!=0 && col0!=(taille-1) && lig0!=(taille-1))
		{

			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);

			//bas
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0][lig0+1];
			grille3[col0][lig0+1]=0;
			g=new Grille(grille3,taille,col0,lig0+1);
			successeurs.add(g);

			//gauche
			int[][] grille4=this.grille.copier();
			grille4[col0][lig0]=grille[col0-1][lig0];
			grille4[col0-1][lig0]=0;
			g=new Grille(grille4,taille,col0-1,lig0);
			successeurs.add(g);

			//droite
			int[][] grille5=this.grille.copier();
			grille5[col0][lig0]=grille[col0+1][lig0];
			grille5[col0+1][lig0]=0;
			g=new Grille(grille5,taille,col0+1,lig0);
			successeurs.add(g);
		}
		//dans ce cas on a 3 successeurs: haut,droit,bas
		if(col0==0 && lig0!=0 && lig0!= (taille-1))
		{
			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);
			
			//bas
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0][lig0+1];
			grille3[col0][lig0+1]=0;
			g=new Grille(grille3,taille,col0,lig0+1);
			successeurs.add(g);
			
			//droite
			int[][] grille4=this.grille.copier();
			grille4[col0][lig0]=grille[col0+1][lig0];
			grille4[col0+1][lig0]=0;
			g=new Grille(grille4,taille,col0+1,lig0);
			successeurs.add(g);
		}
		//dans ce cas on a 3 successeurs: gauche,bas,droit
		if(col0!= (taille-1) && col0!=0 && lig0==0)
		{
			//bas
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0+1];
			grille2[col0][lig0+1]=0;
			g=new Grille(grille2,taille,col0,lig0+1);
			successeurs.add(g);
			
			//gauche
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0-1][lig0];
			grille3[col0-1][lig0]=0;
			g=new Grille(grille3,taille,col0-1,lig0);
			successeurs.add(g);
			
			//droite
			int[][] grille4=this.grille.copier();
			grille4[col0][lig0]=grille[col0+1][lig0];
			grille4[col0+1][lig0]=0;
			g=new Grille(grille4,taille,col0+1,lig0);
			successeurs.add(g);
		}
		//dans ce cas on a 3 successeurs: haut,gauche,bas
		if(col0==(taille-1) && lig0!=0  && lig0!=(taille-1))
		{
			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);
			
			//bas
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0][lig0+1];
			grille3[col0][lig0+1]=0;
			g=new Grille(grille3,taille,col0,lig0+1);
			successeurs.add(g);
			
			//gauche
			int[][] grille4=this.grille.copier();
			grille4[col0][lig0]=grille[col0-1][lig0];
			grille4[col0-1][lig0]=0;
			g=new Grille(grille4,taille,col0-1,lig0);
			successeurs.add(g);
			
		}
		//dans ce cas on a 3 successeurs: gauche,haut,droit
		if(col0!=0 && col0!=(taille-1) && lig0==(taille-1))
		{
			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);
			
			//gauche
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0-1][lig0];
			grille3[col0-1][lig0]=0;
			g=new Grille(grille3,taille,col0-1,lig0);
			successeurs.add(g);
			
			//droite
			int[][] grille4=this.grille.copier();
			grille4[col0][lig0]=grille[col0+1][lig0];
			grille4[col0+1][lig0]=0;
			g=new Grille(grille4,taille,col0+1,lig0);
			successeurs.add(g);
		}
		
		//dans ce cas on a 2 successeurs: droit,bas
		if(col0==0 && lig0==0)
		{
			//bas
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0+1];
			grille2[col0][lig0+1]=0;
			g=new Grille(grille2,taille,col0,lig0+1);
			successeurs.add(g);

			//droite
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0+1][lig0];
			grille3[col0+1][lig0]=0;
			g=new Grille(grille3,taille,col0+1,lig0);
			successeurs.add(g);
		}
		//dans ce cas on a 2 successeurs: haut,droit
		if(col0==0 && lig0==(taille-1))
		{
			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);
			
			//droite
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0+1][lig0];
			grille3[col0+1][lig0]=0;
			g=new Grille(grille3,taille,col0+1,lig0);
			successeurs.add(g);
		}
		//dans ce cas on a 2 successeurs: gauche,bas
		if(col0==(taille-1) && lig0==0)
		{
			//bas
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0+1];
			grille2[col0][lig0+1]=0;
			g=new Grille(grille2,taille,col0,lig0+1);
			successeurs.add(g);
			
			//gauche
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0-1][lig0];
			grille3[col0-1][lig0]=0;
			g=new Grille(grille3,taille,col0-1,lig0);
			successeurs.add(g);

		}
		//dans ce cas on a 2 successeurs: gauche,haut
		if(col0==(taille-1) && lig0==(taille-1))
		{
			//haut
			int[][] grille2=this.grille.copier();
			grille2[col0][lig0]=grille[col0][lig0-1];
			grille2[col0][lig0-1]=0;
			g=new Grille(grille2,taille,col0,lig0-1);
			successeurs.add(g);

			//gauche
			int[][] grille3=this.grille.copier();
			grille3[col0][lig0]=grille[col0-1][lig0];
			grille3[col0-1][lig0]=0;
			g=new Grille(grille3,taille,col0-1,lig0);
			successeurs.add(g);

		}
		
		return successeurs;
	}
	

	
}









