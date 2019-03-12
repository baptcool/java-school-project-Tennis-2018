import java.util.ArrayList;

import javafx.util.Pair;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		Joueur Nadal = new Joueur(99, 99, "Nadal", 2500, 1);
		Joueur Arnaud = new Joueur(10,90, "Arnaud", 4000, 2);
	    System.out.println(Match.jouerUnMatch(Nadal, Arnaud, "masculin"));
		/*int Nad=0;
		int Arn=0;
		for (int i = 0 ; i<10000; i++) {
			ArrayList<Pair<Integer,Integer>>score = Match.jouerUnMatch(Nadal, Arnaud, "masculin");

			if (score.get(score.size()-1).getKey()>score.get(score.size()-1).getValue()) Nad++;
			else Arn++;
		}
		System.out.println("Arnaud : "+Arn);
		System.out.println("Nadal : "+Nad);*/
		
		/*	System.out.println(Match.jouerUnMatch(Nadal, Arnaud, "masculin"));
		int Nad=0;
		int Arn=0;
		for (int i = 0 ; i<10000; i++) {
			Joueur score = Match.jouerUnPoint(Nadal, Arnaud, 0);

			if (score==Nadal) Nad++;
			else Arn++;
		}
		System.out.println("Arnaud : "+Arn);
		System.out.println("Nadal : "+Nad);
		Nad=0;
		Arn=0;
		for (int i = 0 ; i<10000; i++) {
			double v1 = (int)( Math.random()*281);
			double v2 = (int)( Math.random()*151);
			if(v1>v2)Nad++;
			else Arn++;
		}
		System.out.println("Arnaud : "+Arn);
		System.out.println("Nadal : "+Nad);*/
		/*try {
			int fatigue = (int)( Math.random()*151);
			if (fatigue<150) {
			throw new FatigueException("la fatigue est au plus haut");
			}
			System.out.println("lolol");
			} 
		catch (FatigueException e) {
			e.printStackTrace();
			System.out.println("tamer");
		}
		System.out.println("ok");*/
		
	    try {
			System.out.println(Match.jouerUnSet(Nadal, Arnaud, 0, true));
		} catch (FatigueException e) {
			e.printStackTrace();
		}
		
		
	}
}
