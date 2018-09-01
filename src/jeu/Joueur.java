package jeu;

import utils.Constantes;
import utils.Couleurs;

public class Joueur {
	private int ordre;
	private Couleurs color;			// Couleur actuelle
	private Bloc bloc = new Bloc();

	public Joueur(int ordre){
		this.ordre=ordre;
	}

	public Couleurs getColor(){
		return this.color;
	}

	public int getOrdre(){
		return this.ordre;
	}

	public void setColor(Couleurs color){
		this.color=color;
	}

	public void colorierBloc(Couleurs color){
		for(Case c : bloc.getCases()){
			c.setColor(color);
		}
	}

	public void addToBloc(Case c){
		bloc.addCase(c);
	}

	public Bloc getBloc(){
		return bloc;
	}

	public void expand(){
		for(int i=0;i<Constantes.NB_COLONNES;i++){
			for(int j=0;j<Constantes.NB_LIGNES;j++){
				Case c=Launcher.g.getCase(i, j);
				// Tester si la case est neutre
				if(c.getJoueur()==Constantes.NEUTRE){

					// Tester si une des cases autour est au joueur et de la bonne couleur
					if(c.getColonne()!=Constantes.NB_COLONNES-1){
						if(Launcher.g.getCase(c.getColonne()+1,c.getLigne()).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getColonne()!=0){
						if(Launcher.g.getCase(c.getColonne()-1,c.getLigne()).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getLigne()!=Constantes.NB_LIGNES-1){
						if(Launcher.g.getCase(c.getColonne(),c.getLigne()+1).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getLigne()!=0){
						if(Launcher.g.getCase(c.getColonne(),c.getLigne()-1).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
				}
			}
		}


		// C'est un peu dégueu mais on refait dans l'autre sens pour être sur de balayer toutes les cases (ex: si on a BLEU[NEUTRE] BLEU[NEUTRE] BLEU[J1] -> Le premier bleu va pas etre pris alors qu'il devrait)
		for(int j=Constantes.NB_LIGNES-1;j>=0;j--){
			for(int i=Constantes.NB_COLONNES-1;i>=0;i--){
				Case c=Launcher.g.getCase(i, j);
				// Tester si la case est neutre
				if(c.getJoueur()==Constantes.NEUTRE){

					// Tester si une des cases autour est au joueur et de la bonne couleur
					if(c.getColonne()!=Constantes.NB_COLONNES-1){
						if(Launcher.g.getCase(c.getColonne()+1,c.getLigne()).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getColonne()!=0){
						if(Launcher.g.getCase(c.getColonne()-1,c.getLigne()).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getLigne()!=Constantes.NB_LIGNES-1){
						if(Launcher.g.getCase(c.getColonne(),c.getLigne()+1).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
					if(c.getLigne()!=0){
						if(Launcher.g.getCase(c.getColonne(),c.getLigne()-1).getJoueur()==ordre && c.getColor()==this.color){
							addToBloc(c);
							c.setJoueur(ordre);
						}
					}
				}
			}
		}
	}
}
