import java.util.ArrayList;

import javafx.util.Pair;

public class Match {

	public static Joueur jouerUnPoint(Joueur j1, Joueur j2, int niemePoint)throws FatigueException {
		double valeur1 = 0;
		double valeur2 = 0;
		double newEndurance1 = j1.getEndurance()-(j1.getPuissance()/100)*niemePoint;
		double newEndurance2 = j2.getEndurance()-(j2.getPuissance()/100)*niemePoint;
		
		if (newEndurance1 <=0 || newEndurance2 <= 0)throw new FatigueException("Fin du match. Joueur trop fatigué");
		while (valeur1 == valeur2) {
		valeur1 = (j1.getPuissance() / (100-newEndurance1) )* (int)( Math.random()*101);
		valeur2 = (j2.getPuissance() / (100-newEndurance2) )* (int)( Math.random()*101);
		}
		if(valeur1>valeur2) {return j1;}
		return j2;
	}

	public static ArrayList<Integer> jouerUnJeu(Joueur j1, Joueur j2, int niemePoint) throws FatigueException {
		int score1 = 0;
		int score2 = 0;
		//TANT QUE , L'un des deux n'est pas à 4 ou que il n'y a pas 2 points de différence.. On joue des points.
		while ((score1<4 && score2<4) || Math.abs(score1-score2)<2) {
			Joueur gagnant = jouerUnPoint(j1, j2, niemePoint);
			if ((score1==3 && score2 ==3 ) && gagnant==j1) {score2--;}
			else if ((score1==3 && score2 ==3 ) && gagnant==j2) {score1--;}
			else if ((gagnant == j1)) {score1++;}
			else {score2++;}
			niemePoint++;
		}
		
		//RETURN TableauScore [J1,J2,NbPointsJoues]
		ArrayList<Integer>resultat = new ArrayList<Integer>();
			resultat.add(score1);
			resultat.add(score2);
			resultat.add(niemePoint);
			return resultat;	
	}
	
	public static ArrayList<Integer> jouerTieBreak (Joueur j1, Joueur j2, int niemePoint) throws FatigueException {
		int score1 =0;
		int score2 =0;
		while ((score1<7 && score2<7) || Math.abs(score1-score2)<2) {
			Joueur gagnant = jouerUnPoint(j1, j2, niemePoint);
			if ((score1==7 && score2==7 ) && gagnant==j1) {score2--;}
			else if ((score1==6 && score2 ==6 ) && gagnant==j2) {score1--;}
			else if ((gagnant == j1)) {score1++;}
			else {score2++;}	
			niemePoint++;
		}
		//RETURN TableauScore [J1,J2,NbPointsJoues]
		ArrayList<Integer>resultat = new ArrayList<Integer>();
			resultat.add(score1);
			resultat.add(score2);
			resultat.add(niemePoint);
			return resultat;	
	}
	
	
	public static ArrayList<Integer> jouerUnSet(Joueur j1, Joueur j2, int niemePoint, boolean tieBreak) throws FatigueException {
		int nbJeuxJ1 = 0;
		int nbJeuxJ2 = 0;
		if (tieBreak) {//Jeu décisif
			while( (nbJeuxJ1<6|| nbJeuxJ2<6) && ((nbJeuxJ1<6 && nbJeuxJ2<6) || Math.abs(nbJeuxJ1-nbJeuxJ2)<2)) {
				//selon les règles du tennis, tant que ca ne fait pas 6-6 7-5 ou un score gagnant normal.
				ArrayList<Integer>jeu = jouerUnJeu(j1,j2,niemePoint);
				Joueur gagnant;
				niemePoint = jeu.get(2);
				if(jeu.get(0)>jeu.get(1))gagnant=j1;
				else gagnant = j2;
				
				if (gagnant == j1) nbJeuxJ1++;
				else nbJeuxJ2++;
			}
			if (nbJeuxJ1==nbJeuxJ2 && nbJeuxJ1==6) {
				// Si 6-6 on joue tieBreak car c'est le if avec tieBreak
				ArrayList<Integer>resultat = jouerTieBreak(j1, j2, niemePoint);
				if(resultat.get(0)>resultat.get(1)) {
					resultat.set(0, 7);
					resultat.set(1, 6);
					return resultat;
				}
				else {
					resultat.set(0, 6);
					resultat.set(1, 7);
					return resultat;
				}
			}
			else {// Si pas de 6-6 pas de tieBreak de toute façon
				ArrayList<Integer>resultat = new ArrayList<Integer>();
				resultat.add(nbJeuxJ1);
				resultat.add(nbJeuxJ2);
				resultat.add(niemePoint);
				return resultat;
				
			}	
		}
		else {//pas de tieBreak du tout car dernier set
			int compteur = 0;
			while((nbJeuxJ1<6 && nbJeuxJ2<6) || Math.abs(nbJeuxJ1-nbJeuxJ2)<2) {
				//on joue tant qu'il n'y pas deux points d'écarts. (on garde les variables à 6 max mais on incrémente
				// un compteur pour mémoire des scores réels.
				ArrayList<Integer>jeu = jouerUnJeu(j1,j2,niemePoint);
				compteur++;
				Joueur gagnant;
				niemePoint = jeu.get(2);
				if(jeu.get(0)>jeu.get(1))gagnant=j1;
				else gagnant = j2;
				
				if ((nbJeuxJ1==6 && nbJeuxJ2 ==6 ) && gagnant==j1) {nbJeuxJ2--;}
				else if ((nbJeuxJ1==6 && nbJeuxJ2 ==6 ) && gagnant==j2) {nbJeuxJ1--;}
				else if ((gagnant == j1)) {nbJeuxJ1++;}
				else {nbJeuxJ2++;}				
			}
			ArrayList<Integer>resultat = new ArrayList<Integer>();
			if (nbJeuxJ1>nbJeuxJ2) {
				// Puisqu'on limite les variables a 6/6 alors qu'elles peuvent potentiellement etre plus grandes s'il n'y pas de tieBreak
				// comme dit ci-dessus, on a gardé une mémoire (compteur) on re-répartie donc les points
				nbJeuxJ1 = compteur +1;
				nbJeuxJ2 = compteur -1;
			}
			else {
				nbJeuxJ1= compteur -1;
				nbJeuxJ2= compteur +1;
			}
			resultat.add(nbJeuxJ1);
			resultat.add(nbJeuxJ2);
			resultat.add(niemePoint);
			return resultat;
		}
	}
	
	
	public static ArrayList<Pair<Integer,Integer>> jouerUnMatch(Joueur j1, Joueur j2, String genre){
		int nbDeSetsJ1 = 0;
		int nbDeSetsJ2 = 0;
		int niemePoint = 0;
		//Nième point est la variable qui découlera dans chaque fonction pour considérer l'épuisement progressif des joueurs dans le match
		boolean tieBreak = true;//devient false si c'est le dernier set  ==> pas de tieBreak
		ArrayList<Pair<Integer, Integer>> score = new ArrayList<Pair<Integer, Integer>>();
try {
		if (genre.equals("feminin")) {//2sets gagnants
			while (nbDeSetsJ1<2 && nbDeSetsJ2<2) {
				if(nbDeSetsJ1==1 && nbDeSetsJ2==nbDeSetsJ1) {tieBreak = false;}
				ArrayList<Integer> set = jouerUnSet(j1,j2,niemePoint, tieBreak);
				niemePoint = set.get(2);
				Joueur gagnant;
				Pair <Integer,Integer> scoreSet = new Pair<Integer,Integer>(set.get(0),set.get(1));
				score.add(scoreSet);
				if(set.get(0)>set.get(1))gagnant=j1;
				else gagnant = j2;
				
				if (gagnant==j1)nbDeSetsJ1++;
				else nbDeSetsJ2++;		
			}
		}
		else {// if genre.equals("masculin"), 3sets gagnants
			while (nbDeSetsJ1<3 && nbDeSetsJ2<3) {
				if(nbDeSetsJ1==2 && nbDeSetsJ2==nbDeSetsJ1) {tieBreak = false;}
				ArrayList<Integer> set = jouerUnSet(j1,j2,niemePoint, tieBreak);
				niemePoint = set.get(2);
				Joueur gagnant;
				Pair <Integer,Integer> scoreSet = new Pair<Integer,Integer>(set.get(0),set.get(1));
				score.add(scoreSet);
				if(set.get(0)>set.get(1))gagnant=j1;
				else gagnant = j2;
				
				if (gagnant==j1)nbDeSetsJ1++;
				else nbDeSetsJ2++;	
			
		}
	}
		score.add(new Pair<Integer,Integer>(nbDeSetsJ1,nbDeSetsJ2));
		return score;
}
catch(FatigueException e) {
	score.add(new Pair<Integer,Integer>(nbDeSetsJ1,nbDeSetsJ2));
	return score;
	
}
}
	public static Joueur getGagnant(Joueur j1, Joueur j2,ArrayList<Pair<Integer,Integer>> match) {
		if (match.get(match.size()-1).getKey()>match.get(match.size()-1).getValue()) return j1;
		return j2;
		}
	
	public static double calculerPoints(Joueur j1, Joueur j2, ArrayList<Pair<Integer,Integer>> match, double numeroRonde) {
		double nbDeJeuxJ1=0;
		double nbDeJeuxJ2=0;
		for (int i = 0; i < match.size()-2;i++) {
			nbDeJeuxJ1 +=  match.get(i).getKey();
			nbDeJeuxJ2 += match.get(i).getValue();
		}
		Joueur gagnant = getGagnant(j1,j2, match);
		Joueur perdant;
		if (gagnant == j1) perdant =j2;
		else perdant = j1;
		System.out.println("winner : "+ gagnant.getName());
		System.out.println("loser : "+ perdant.getName());
		System.out.println("1/numeroRonde "+numeroRonde);
		System.out.println("pts/pts "+ perdant.getPoints()/gagnant.getPoints());
		System.out.println("nbJeuxJ1 "+nbDeJeuxJ1);
		System.out.println("nbJeuxJ2 "+ nbDeJeuxJ2);
		System.out.println("and "+Math.abs(nbDeJeuxJ1-nbDeJeuxJ2));


		return (numeroRonde/7)* (perdant.getPoints()/gagnant.getPoints())*Math.abs(nbDeJeuxJ1-nbDeJeuxJ2);
	}
}
	