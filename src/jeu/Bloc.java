package jeu;

import java.util.HashSet;
import java.util.Set;

public class Bloc {
	private Set<Case> cases = new HashSet<Case>();
	private int joueur;

	public void addCase(Case c){
		cases.add(c);
	}

	public boolean inBloc(Case c){
		return cases.contains(c);
	}

	public int getJoueur(){
		return this.joueur;
	}

	public Set<Case> getCases(){
		return cases;
	}
}
