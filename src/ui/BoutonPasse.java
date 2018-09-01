package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import jeu.Launcher;
import utils.Constantes;

public class BoutonPasse extends JButton implements MouseListener{

	public BoutonPasse(){
		super("Passer le tour");
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Launcher.joueur = (Launcher.joueur==Constantes.J1)?Constantes.J2:Constantes.J1;
		this.setText("Passer le tour") ;
		Launcher.statut.setText("Joueur actif : "+Launcher.joueur);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
