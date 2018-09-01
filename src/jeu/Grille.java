package jeu;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;

import ui.Controller;
import utils.Constantes;
import utils.Couleurs;

public class Grille extends JPanel {
	private int colonnes = Constantes.NB_COLONNES;
	private int lignes = Constantes.NB_LIGNES;
	private Case[][] cases = new Case[Constantes.NB_COLONNES][Constantes.NB_LIGNES];
	private Controller controller;

	public Grille(){
		for(int i=0;i<Constantes.NB_COLONNES;i++){
			for(int j=0;j<Constantes.NB_LIGNES;j++){
				Case c = new Case(i,j,Couleurs.randomColor());
				cases[i][j] = c;
			}
		}
		this.controller=new Controller(this);
		this.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setFocusable(true);
	}

	public void paint(Graphics g){
		super.getRootPane().updateUI();
		for(int i=0;i<Constantes.NB_COLONNES;i++){
			for(int j=0;j<Constantes.NB_LIGNES;j++){
				cases[i][j].paint(g);
			}
		}
	}

	public void processClic(MouseEvent e) {
		int x=e.getX()/Constantes.TAILLE_COTE;
		int y=e.getY()/Constantes.TAILLE_COTE;
		cases[x][y].doOnClick();
	}

	public void isFinished(){
		Joueur autreJoueur = (Launcher.joueur==Constantes.J1)?Launcher.j2:Launcher.j1;

		boolean test = true;
		for(int i=0;i<Constantes.NB_COLONNES;i++){
			for(int j=0;j<Constantes.NB_LIGNES;j++){
				if(cases[i][j].getJoueur()==Constantes.NEUTRE && cases[i][j].getColor()!=autreJoueur.getColor())	{
					test=false;
				}
			}
		}

		if(test)	Launcher.etat=Constantes.PARTIE_TERMINEE;
	}

	public Case getCase(int colonne, int ligne){
		return cases[colonne][ligne];
	}
}
