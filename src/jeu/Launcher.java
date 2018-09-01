package jeu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.BoutonFin;
import ui.BoutonPasse;
import ui.BoutonStart;
import utils.Constantes;

public class Launcher {

	public static int etat = Constantes.PARTIE_NON_COMMENCEE;
	public static int joueur = Constantes.J1;
	public static Joueur j1 = new Joueur(Constantes.J1);		// Pions blancs
	public static Joueur j2 = new Joueur(Constantes.J2);		// Pions noirs
	public static Grille g = new Grille();
	public static JLabel statut = new JLabel("Joueur actif : "+joueur);
	public static JLabel erreur = new JLabel("");
	static JPanel buttons = new JPanel();
	static JFrame fenetre = new JFrame();

	public static void main(String[] args) {

		fenetre.setTitle("ExpanZ");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		fenetre.setVisible(true);
		fenetre.setLocation(new Point(400,25));
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(false);

		fenetre.add(g,BorderLayout.NORTH);
		g.setVisible(false);
		g.setPreferredSize(new Dimension((Constantes.NB_COLONNES)*Constantes.TAILLE_COTE, (Constantes.NB_LIGNES)*Constantes.TAILLE_COTE));

		buttons.setLayout(new BorderLayout());
		fenetre.add(buttons,BorderLayout.CENTER);
		BoutonStart start = new BoutonStart();
		buttons.add(start,BorderLayout.CENTER);
		
		JPanel labels = new JPanel();
		labels.setLayout(new BorderLayout());
		labels.add(statut, BorderLayout.WEST);
		labels.add(erreur, BorderLayout.EAST);
		fenetre.add(labels, BorderLayout.SOUTH);

		fenetre.pack();	// Met tous les composants à la bonne preferredsize

		while(etat==Constantes.PARTIE_NON_COMMENCEE){
			// On affiche le jeu
		}

		g.setVisible(true);
		buttons.remove(start);
		fenetre.pack();

		(new Thread() {		// Swing est monothread donc ça pose des problemes, surtout avec des while true. Donc faut faire un thread
			public void run() {
				play();
			}
		}
				).start();
	}

	public static void play(){
		BoutonPasse passe = new BoutonPasse();
		buttons.add(passe, BorderLayout.CENTER);

		BoutonFin fin = new BoutonFin();
		buttons.add(fin, BorderLayout.EAST);

		fenetre.pack();

		while(etat!=Constantes.PARTIE_TERMINEE){
			// On joue
		}

		System.out.println("jeu fini");
		passe.setEnabled(false);
		fin.setEnabled(false);
		int score1 = j1.getBloc().getCases().size();
		int score2 = j2.getBloc().getCases().size();

		if(score1==score2)	statut.setText("Et c'est une égalité : "+score1+" à "+score2+" ! Que diriez-vous d'une revanche ?");
		else{
			int winner = (score1>score2)?1:2;
			statut.setText("Partie terminée. Félicitations Joueur "+winner+", vous avez gagné "+j1.getBloc().getCases().size()+" à "+j2.getBloc().getCases().size()+" !");
		}	
	}

}
