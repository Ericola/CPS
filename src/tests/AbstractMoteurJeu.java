package tests;


import java.awt.Point;

import services.IMoteurJeuService;
import services.IVillageoisService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ECommande;
import enums.ERace;

public abstract class AbstractMoteurJeu extends AbstractAssertion {
	
	protected IMoteurJeuService moteurJeu;
	@Before
	public abstract void before();

	@After
	public void after() {
		moteurJeu = null;
	}
	
	@Test
	public void test0_1() {
		
		//Condition Initiale : aucune
		
		//Opération
		moteurJeu.init(1664, 1000, 1000);
		
		//Oracle
		assertPerso("MJ init,Le nombre de pas de jeu n'est pas égale à 1664", moteurJeu.MaxPasJeu() == 1664);
		assertPerso("MJ init,La valeur du pas courant n'est pas initialiser à 0", moteurJeu.pasJeuCourant() == 0);
		assertPerso("MJ init,La valeur de la largeur n'est pas initialiser correctement", moteurJeu.LargeurTerrain() == 1000);
		assertPerso("MJ init,La valeur du pas courant n'est pas initialiser correctement", moteurJeu.HauteurTerrain() == 0);
		
		//villageois Hauteur Largeur
		for(int i=0 ;i<moteurJeu.numerosVillageois().size();i++){
				assertPerso("in moteurJeu init: Le villageois "+i+"a une largeur mal initialiser",moteurJeu.getVillageois(i).getLargeur()==10);
				assertPerso("in moteurJeu init: Le villageois "+i+"a une hauteur mal initialiser",moteurJeu.getVillageois(i).getHauteur()==10);	
		}
		
		//villageois attribut selon race...	
		for(int i=0 ;i<moteurJeu.numerosVillageois().size();i++){
			IVillageoisService temp =moteurJeu.getVillageois(i);
			if(temp.getRace() == ERace.HUMAIN ){
				assertPerso("in moteurJeu init: Le villageois HOMME"+i+"a une force mal initialiser",temp.getForce()==3);
				assertPerso("in moteurJeu init: Le villageois HOMME"+i+"a une vitesse mal initialiser",temp.getVitesse()==4);
				//distance(positionVillageoisX(M, V), positionVillageois(M, V), positionHotelVilleX(M, 1), positionHotelVilleY(M, 1)) <= 51
						
				int dist = (int) Point.distance(moteurJeu.positionVillageoisX(temp),moteurJeu.positionVillageoisX(temp), moteurJeu.positionHotelVilleX(1), moteurJeu.positionHotelVilleY(1));
				assertPerso("in moteurJeu init: Le villageois HOMME"+i+"est à une dist initialiser trop loin de son hotel de ville",dist<=51);
			}else{
				assertPerso("in moteurJeu init: Le villageois ORC "+i+"a une largeur mal initialiser",temp.getForce()==4);
				assertPerso("in moteurJeu init: Le villageois ORC "+i+"a une hauteur mal initialiser",temp.getVitesse()==3);
			
				int dist = (int) Point.distance(moteurJeu.positionVillageoisX(temp),moteurJeu.positionVillageoisX(temp), moteurJeu.positionHotelVilleX(1), moteurJeu.positionHotelVilleY(1));
			
				assertPerso("in moteurJeu init: Le villageois ORC"+i+"est à une dist initialiser trop loin de son hotel de villeinitialiser",dist<=51);
			}
		}
		
		//pour toutes les mines
		for(int i=0 ;i<moteurJeu.numerosMine().size();i++){
			assertPerso("in moteurJeu init: La Mine "+i+"a une largeur mal initialiser",moteurJeu.getMine(i).getLargeur()==50);
			assertPerso("in moteurJeu init: La Mine "+i+"a une hauteur mal initialiser",moteurJeu.getMine(i).getHauteur()==50);	
		}
		
		//pour les hotel de ville
		 //1
			assertPerso("in moteurJeu init: L hotel de ville 1 a une Hauteur a une largeur mal initialiser",moteurJeu.HotelDeVille(1).getHauteur()==50);
			assertPerso("in moteurJeu init: L hotel de ville 1 a une Largeur mal initialiser",moteurJeu.HotelDeVille(1).getLargeur()==50);
			assertPerso("in moteurJeu init: L hotel de ville 1 n'appartient pas au HOMME a l'init alors qu'il le devrait",moteurJeu.HotelDeVille(1).appartenance()==ERace.HUMAIN);
		
		 //2
			assertPerso("in moteurJeu init: L hotel de ville 2 a une Hauteur a une largeur mal initialiser",moteurJeu.HotelDeVille(2).getHauteur()==50);
			assertPerso("in moteurJeu init: L hotel de ville 2 a une Largeur mal initialiser",moteurJeu.HotelDeVille(2).getLargeur()==50);
			assertPerso("in moteurJeu init: L hotel de ville 2 n'appartient pas au ORC a l'init alors qu'il le devrait",moteurJeu.HotelDeVille(2).appartenance()==ERace.ORC);
			
	   //mine Miné => pas ici enfaite is invariant !
			assertPerso("in moteurJeu init: size MineMinee non ok car different de size liste villageois",moteurJeu.MineMinee().size() == moteurJeu.numerosVillageois().size());
		//villageois attente => is invariant normally.
			assertPerso("in moteurJeu init: size villageoisAttente non ok car different de size liste villageois",moteurJeu.VillageoisAttente().size() == moteurJeu.numerosVillageois().size());
	
			//check si aucune mine est miné a l'init
			for(int i=0;i<moteurJeu.MineMinee().size();i++){
				assertPerso("in moteurJeu init: le villageois"+i+ "est entrain de miné (trouver via MineMinee(i) != -1) alors qu'il ne devrait pas",moteurJeu.MineMinee().get(i)==-1);
			}
			
			//check si aucun villageois n'est en attente
			for(int i=0;i<moteurJeu.VillageoisAttente().size();i++){
				assertPerso("in moteurJeu init: le villageois"+i+ "est en attente (trouver via villageoisAttente(i) != -1) alors qu'il ne devrait pas",moteurJeu.VillageoisAttente().get(i)==-1);
			}
			
			//check pasJeu
			int previous = moteurJeu.pasJeuCourant();
			moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.DEPLACER, 1, 3, 240, 30);
			int actual = moteurJeu.pasJeuCourant();
			assertPerso("in moteurJeu init: pasJeu s'est mal effectué",actual == previous + 1);
					
			
	}
	
	//entrer dans une mine
	public void test1_1() {
		
		//Condition Initiale : aucune
		moteurJeu.init(1664, 1000, 1000);
		
		//on a bouger le villageois 1
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionMineX(moteurJeu.getMine(0)),moteurJeu.positionMineY(moteurJeu.getMine(0))));
		
		
		//Opération
		moteurJeu.pasJeu(ECommande.ENTRERMINE, ECommande.RIEN, 1, 3, 0, 5);
		//moteurJeu.getVillageois(1)
		
		//Oracle
		assertPerso("estEntrerMine a raté",moteurJeu.MineMinee().get(1)==0 );
		
		
	}
	
	//entrer dans un hotel de ville
	//prerequis de l'exemple : Un villageois homme, le 1 ,aura une piece d'or
				//MEMORISER L OR DANS UNE VARIABLE
		//pasJeu : ENTRERHOTELDEVILLE => HotelDeVille(1).QTOR = 
	public void test2_1() {
		//Condition Initiale : aucune
		moteurJeu.init(1664, 1000, 1000);
		int OrHotelVilleBefore = 16; 
		//-> set piece d'or
			moteurJeu.getVillageois(1).setQtor(1);
			
			
	
		
		//Opération
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 1, 5);
		
		
		//Oracle
		assertPerso("Entrer dans Hotel Ville pas ok car posséder par l'hotel de ville est différent de la quatité a l'initialisation + 1  ", moteurJeu.HotelDeVille(1).orRestant()== OrHotelVilleBefore + 1 );
	
	}

	//entrer dans une mine qui ne t'appartient pas
	public void test3_1() {
		//LES ORC ON DEJA CONQUIS LA MINE
		
		//Condition Initiale : 
			moteurJeu.init(1664, 1000, 1000);
			moteurJeu.getMine(0).setAppartenance(ERace.ORC);
				
			//on a bouger le villageois 1
			moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionMineX(moteurJeu.getMine(0)),moteurJeu.positionMineY(moteurJeu.getMine(0))));
				
		//Opération
			moteurJeu.pasJeu(ECommande.ENTRERMINE, ECommande.RIEN, 1, 3, 0, 5);
			
		//oracle
			assertPerso("test3 : L'appartenance devrait être ORC ce n'est pas le cas  ",moteurJeu.getMine(0).appartenance() == ERace.ORC );
			
	
	}
	
	//même chose pour hotel de ville
	public void test4_1() {
		
		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);
		
		//on a bouger le villageois 1 a côté de l'hotel de ville des orcs //EN L OCCURENCE DEDANS...
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionHotelVilleX(2),moteurJeu.positionHotelVilleY(2)));
		
		//Opération
		//hotel ville du joueur2...
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 2, 5);
		//Oracle
		assertPerso("test4 : L'appartenance devrait être ORC pour l'hotel de ville J2 ce n'est pas le cas  ",moteurJeu.HotelDeVille(2).appartenance() == ERace.ORC); 
	
	}
	
	//se déplacer
	public void test5_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}
	
	//se déplacé sur une muraille
	public void test6_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}
	
	//se déplacé en dehors du terrain
	public void test7_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}
	
	
	//entrer dans une hotel de ville abandonné
			//=>verifier que la partie est gagner? 
	public void test8_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}
	
	//entrer dans une mine abandonné
	public void test9_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}
	
	//deplacement même case si meme race ok sinon error
	public void test10_1() {
		
		//Condition Initiale : aucune
		
		//Opération
	
	}

	//test hotel de ville abandonné au bout de 51 pas de JEU
	public void test11_01(){
		
	}

}
	

