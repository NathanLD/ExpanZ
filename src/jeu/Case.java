package jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import utils.Constantes;
import utils.Couleurs;

public class Case extends JPanel{
	private int colonne;
	private int ligne;
	private Couleurs color;
	private int joueur;

	public Case(int colonne, int ligne, Couleurs couleur){
		this.colonne=colonne;
		this.ligne=ligne;
		this.color=couleur;
	}

	public void paint(Graphics g){
		int cote = Constantes.TAILLE_COTE;
		g.setColor(color.getColor());
		g.fillRect(colonne*cote,ligne*cote,cote-1,cote-1);

		// Pour griser les cases de la couleur adverse (non prenable)
		if(Launcher.joueur==Constantes.J1){
			if(color==Launcher.j2.getColor() && joueur == Constantes.NEUTRE){
				g.setColor(Color.GRAY);
				g.fillRect(colonne*cote,ligne*cote,cote-1,cote-1);
			}
		}
		else if(Launcher.joueur==Constantes.J2){
			if(color==Launcher.j1.getColor() && joueur == Constantes.NEUTRE){
				g.setColor(Color.GRAY);
				g.fillRect(colonne*cote,ligne*cote,cote-1,cote-1);
			}
		}

		switch(joueur){
		case Constantes.J1:
			g.setColor(Color.WHITE);
			g.fillOval(colonne*cote+cote/4, ligne*cote+cote/4, cote/2, cote/2);
			g.setColor(Color.BLACK);
			g.drawOval(colonne*cote+cote/4, ligne*cote+cote/4, cote/2, cote/2);
			break;

		case Constantes.J2:
			g.setColor(Color.BLACK);
			g.fillOval(colonne*cote+cote/4, ligne*cote+cote/4, cote/2, cote/2);
			g.setColor(Color.WHITE);
			g.drawOval(colonne*cote+cote/4, ligne*cote+cote/4, cote/2, cote/2);
			break;

		default:
			break;
		}
	}

	public void doOnClick()
	{
		/*
		 * Choix de la première case
		 */

		if(Launcher.j1.getBloc().getCases().size()==0){
			this.joueur=Constantes.J1;
			Launcher.j1.addToBloc(this);
		}
		else if(Launcher.j2.getBloc().getCases().size()==0){
			this.joueur=Constantes.J2;
			Launcher.j2.addToBloc(this);
		}

		/*
		 * Autres coups
		 */

		else if(Launcher.joueur==Constantes.J1 && this.joueur==Constantes.NEUTRE){
			if(coupPossible(Constantes.J1)){
				this.joueur=Constantes.J1;
				Launcher.j1.addToBloc(this);			// On ajoute la case au bloc du joueur
				Launcher.j1.setColor(this.color);		// Le joueur prend la new color
				Launcher.j1.expand();					// On ajoute toutes les autres cases de cette couleur qui sont à la frontiere du bloc
				Launcher.j1.colorierBloc(this.color);	// On colorie le tout

				Launcher.joueur=Constantes.J2;			// On change le joueur actif
				Launcher.statut.setText("Joueur actif : "+Launcher.joueur);
				Launcher.erreur.setText("");
			}
			else{
				Launcher.erreur.setForeground(Color.RED);
				Launcher.erreur.setText("Coup invalide !");
			}
		}

		else if(Launcher.joueur==Constantes.J2 && this.joueur==Constantes.NEUTRE){
			if(coupPossible(Constantes.J2)){
				this.joueur=Constantes.J2;
				Launcher.j2.addToBloc(this);
				Launcher.j2.setColor(this.color);
				Launcher.j2.expand();
				Launcher.j2.colorierBloc(this.color);

				Launcher.joueur=Constantes.J1;
				Launcher.statut.setText("Joueur actif : "+Launcher.joueur);
				Launcher.erreur.setText("");
			}
			else{
				Launcher.erreur.setForeground(Color.RED);
				Launcher.erreur.setText("Coup invalide !");
			}
		}
		else{
			Launcher.erreur.setForeground(Color.RED);
			Launcher.erreur.setText("Choisissez une case neutre !");
		}
		
		Launcher.g.isFinished();
	}

	public boolean coupPossible(int joueur){
		Joueur autreJoueur = (Launcher.joueur==Constantes.J1)?Launcher.j2:Launcher.j1;

		boolean test = false;

		if(colonne!=Constantes.NB_COLONNES-1){
			if(Launcher.g.getCase(colonne+1,ligne).getJoueur()==joueur && color!=autreJoueur.getColor())	test=true;
		}
		if(colonne!=0){
			if(Launcher.g.getCase(colonne-1,ligne).getJoueur()==joueur && color!=autreJoueur.getColor())	test=true;
		}
		if(ligne!=Constantes.NB_LIGNES-1){
			if(Launcher.g.getCase(colonne,ligne+1).getJoueur()==joueur && color!=autreJoueur.getColor())	test=true;
		}
		if(ligne!=0){
			if(Launcher.g.getCase(colonne,ligne-1).getJoueur()==joueur && color!=autreJoueur.getColor())	test=true;
		}

		return test;
	}

	public Couleurs getColor(){
		return this.color;
	}

	public void setJoueur(int joueur){
		this.joueur=joueur;
	}

	public int getJoueur(){
		return this.joueur;
	}

	public int getColonne(){
		return this.colonne;
	}

	public int getLigne(){
		return this.ligne;
	}

	public void setColor(Couleurs color) {
		this.color = color;
	}
}