package com.jeu.classes;

public class Grille {
	
	private int grille[][];
	private int taille;
	private int ligne0; //position case vide
	private int colonne0; //position case vide
	
	public Grille(int g[][],int t,int c, int l)
	{
		this.grille=g;
		taille=t;
		colonne0=c;
		ligne0=l;
	}
	
	public int[][] getGrille()
	{
		return this.grille;
	}

	public int getTaille()
	{
		return this.taille;
	}
	public void setTaille(int taille)
	{
		this.taille=taille;
	}
	
	public int getLigne0()
	{
		return this.ligne0;
	}
	public void setLigne0(int ligne0)
	{
		this.ligne0=ligne0;
	}
	public int getColonne0()
	{
		return this.colonne0;
	}
	public void setColonne0(int colonne0)
	{
		this.colonne0=colonne0;
	}
	
	
	public int getValeur(int i, int j)
	{
		return this.grille[i][j];
	}
	
	public int[][] copier()
	{
		int[][] g=new int[taille][taille];
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				g[i][j]=this.grille[i][j];
			}
		}
		return g;
	}
	
	
	public boolean equals(Object y)
	{
		Grille g=null;
		boolean etat=true;
		//verifier si l'objet et de meme type que la grille
		if(y instanceof Grille)
		{
			//si oui convertir l'objet on  grille
			g=(Grille) y;
			
		}
		else
			etat=false;
		
		if(g !=null && taille==g.getTaille())
		{
			for(int i=0;i<taille;i++)
			{
				for(int j=0;j<taille;j++)
				{
					if(this.grille[i][j]!=g.getValeur(i, j))
						{
							etat=false;
							break;
						}
				}
			}
		}
		else
			etat=false;
		
		return etat;
	}
	
	@Override
	public String toString()
	{ 
		String result="";
		for(int i=0;i<taille;i++)
		{
			result+="\t";
			for(int j=0;j<taille;j++)
			{
				if(i==ligne0 && j==colonne0)
					result+="*" + " | ";
				else
					result+=this.grille[i][j] + " | ";
			}
			result+="\n";
		}
		return result;
	}
}














