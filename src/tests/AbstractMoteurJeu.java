package tests;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import decorators.HotelVilleDecorator;
import services.IMoteurJeuService;
import services.IVillageoisService;
import enums.ECommande;
import enums.ERace;
import enums.EResultat;

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
		assertPerso("MJ init,Le nombre de pas de jeu n'est pas eale a 1664", moteurJeu.MaxPasJeu() == 1664);
		assertPerso("MJ init,La valeur du pas courant n'est pas initialise a 0", moteurJeu.pasJeuCourant() == 0);
		assertPerso("MJ init,La valeur de la largeur n'est pas initialise correctement", moteurJeu.LargeurTerrain() == 1000);
		assertPerso("MJ init,La valeur du pas courant n'est pas initialise correctement", moteurJeu.HauteurTerrain() == 1000);

		//villageois Hauteur Largeur
		for(int i=0 ;i<moteurJeu.numerosVillageois().size();i++){
			assertPerso("in moteurJeu init: Le villageois "+i+" a une largeur mal initialise",moteurJeu.getVillageois(i).getLargeur()==10);
			assertPerso("in moteurJeu init: Le villageois "+i+" a une hauteur mal initialise",moteurJeu.getVillageois(i).getHauteur()==10);	
		}

		//villageois attribut selon race...	
		for(int i=0 ;i<moteurJeu.numerosVillageois().size();i++){
			IVillageoisService temp =moteurJeu.getVillageois(i);
			if(temp.getRace() == ERace.HUMAIN ){
				assertPerso("in moteurJeu init: Le villageois HOMME"+i+" a une force mal initialise",temp.getForce()==3);
				assertPerso("in moteurJeu init: Le villageois HOMME"+i+" a une vitesse mal initialise",temp.getVitesse()==4);
				//distance(positionVillageoisX(M, V), positionVillageois(M, V), positionHotelVilleX(M, 1), positionHotelVilleY(M, 1)) <= 51
				Rectangle r = new Rectangle(moteurJeu.positionHotelVilleX(1), moteurJeu.positionHotelVilleY(1), 50, 50);
				Rectangle r1 = new Rectangle(moteurJeu.positionVillageoisX(temp) + 5 - 28, moteurJeu.positionVillageoisY(temp) + 5 - 28, 56, 56);

				assertPerso("in moteurJeu init: Le villageois HOMME"+i+" est a une dist initialiseee trop loin de son hotel de ville",r.intersects(r1));
			}else{
				assertPerso("in moteurJeu init: Le villageois ORC "+i+" a une largeur mal initialise",temp.getForce()==4);
				assertPerso("in moteurJeu init: Le villageois ORC "+i+" a une hauteur mal initialise",temp.getVitesse()==3);

				Rectangle r = new Rectangle(moteurJeu.positionHotelVilleX(2), moteurJeu.positionHotelVilleY(2), 50, 50);
				Rectangle r1 = new Rectangle(moteurJeu.positionVillageoisX(temp) + 5 - 28, moteurJeu.positionVillageoisY(temp) + 5 - 28, 56, 56);

				assertPerso("in moteurJeu init: Le villageois ORC"+i+"est a une dist initialisee trop loin de son hotel de ville ",r.intersects(r1));
			}
		}

		//pour toutes les mines
		for(int i=0 ;i<moteurJeu.numerosMine().size();i++){
			assertPerso("in moteurJeu init: La Mine "+i+" a une largeur mal initialisee",moteurJeu.getMine(i).getLargeur()==50);
			assertPerso("in moteurJeu init: La Mine "+i+" a une hauteur mal initialisee",moteurJeu.getMine(i).getHauteur()==50);	
		}

		//pour les hotel de ville
		//1
		assertPerso("in moteurJeu init: L hotel de ville 1 a une Hauteur a une largeur mal initialisee",moteurJeu.HotelDeVille(1).getHauteur()==50);
		assertPerso("in moteurJeu init: L hotel de ville 1 a une Largeur mal initialisee",moteurJeu.HotelDeVille(1).getLargeur()==50);
		assertPerso("in moteurJeu init: L hotel de ville 1 n'appartient pas aux HOMME a l'init alors qu'il le devrait",moteurJeu.HotelDeVille(1).appartenance()==ERace.HUMAIN);

		//2
		assertPerso("in moteurJeu init: L hotel de ville 2 a une Hauteur a une largeur mal initialisee",moteurJeu.HotelDeVille(2).getHauteur()==50);
		assertPerso("in moteurJeu init: L hotel de ville 2 a une Largeur mal initialisee",moteurJeu.HotelDeVille(2).getLargeur()==50);
		assertPerso("in moteurJeu init: L hotel de ville 2 n'appartient pas aux ORC a l'init alors qu'il le devrait",moteurJeu.HotelDeVille(2).appartenance()==ERace.ORC);

		//mine Miné => pas ici enfaite is invariant !
		assertPerso("in moteurJeu init: size MineMinee non ok car differente de size liste villageois",moteurJeu.MineMinee().size() == moteurJeu.numerosVillageois().size());
		//villageois attente => is invariant normally.
		assertPerso("in moteurJeu init: size villageoisAttente non ok car differente de size liste villageois",moteurJeu.VillageoisAttente().size() == moteurJeu.numerosVillageois().size());

		//check si aucune mine est miné a l'init
		for(int i=0;i<moteurJeu.MineMinee().size();i++){
			assertPerso("in moteurJeu init: le villageois"+i+ " est entrain de mine (trouve via MineMinee(i) != -1) alors qu'il ne devrait pas",moteurJeu.MineMinee().get(i)==-1);
		}

		//check si aucun villageois n'est en attente
		for(int i=0;i<moteurJeu.VillageoisAttente().size();i++){
			assertPerso("in moteurJeu init: le villageois"+i+ " est en attente (trouve via villageoisAttente(i) != -1) alors qu'il ne devrait pas",moteurJeu.VillageoisAttente().get(i)==-1);
		}

		//check pasJeu
		int previous = moteurJeu.pasJeuCourant();
		moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.DEPLACER, 1, 3, 240, 30);
		int actual = moteurJeu.pasJeuCourant();
		assertPerso("in moteurJeu init: pasJeu s'est mal effectuee",actual == previous + 1);


	}

	//entrer dans une mine
	@Test
	public void test1_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664, 1000, 1000);

		//on a bouger le villageois 1
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionMineX(moteurJeu.getMine(0)),moteurJeu.positionMineY(moteurJeu.getMine(0))));


		//Opération
		moteurJeu.pasJeu(ECommande.ENTRERMINE, ECommande.RIEN, 1, 3, 0, 5);
		//moteurJeu.getVillageois(1)

		//Oracle
		assertPerso("estEntrerMine a rate",moteurJeu.MineMinee().get(1)==0 );


	}

	//entrer dans un hotel de ville
	//prerequis de l'exemple : Un villageois homme, le 1 ,aura une piece d'or
	//MEMORISER L OR DANS UNE VARIABLE
	//pasJeu : ENTRERHOTELDEVILLE => HotelDeVille(1).QTOR = 
	@Test
	public void test2_1() {
		//Condition Initiale : aucune
		moteurJeu.init(1664, 1000, 1000);
		int OrHotelVilleBefore = 16; 
		//-> set piece d'or
		moteurJeu.getVillageois(1).setQtor(1);




		//Operation
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 1, 5);


		//Oracle
		assertPerso("Probleme Entrer HotelVille. L'orRestant devrait etre egale a l'orRestant a l'initialisation de HotelVille + 1", moteurJeu.HotelDeVille(1).orRestant()== OrHotelVilleBefore + 1 );

	}

	
	
	

	//entrer dans une mine qui ne t'appartient pas
	
	@Test
	public void test3_1() {
		//LES ORC ONT DEJA CONQUIS LA MINE

		//Condition Initiale : 
		moteurJeu.init(1664, 1000, 1000);
		moteurJeu.getMine(0).setAppartenance(ERace.ORC);

		//on a bouge le villageois 1
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionMineX(moteurJeu.getMine(0)),moteurJeu.positionMineY(moteurJeu.getMine(0))));

		//Opeation
		moteurJeu.pasJeu(ECommande.ENTRERMINE, ECommande.RIEN, 1, 3, 0, 5);

		//oracle
		assertPerso("test3 : L'appartenance devrait etre ORC ",moteurJeu.getMine(0).appartenance() == ERace.ORC );


	}

	//meme chose pour hotel de ville
	@Test
	public void test4_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);

		//on a bouge le villageois 1 a cote de l'hotel de ville des orcs //EN L OCCURENCE DEDANS...
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionHotelVilleX(2),moteurJeu.positionHotelVilleY(2)));

		//Operation

		//HotelVille du joueur2...
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 2, 5);

		assertPerso("test4 : L'appartenance devrait etre ORC pour l'hotel de ville J2 ce n'est pas le cas ",moteurJeu.HotelDeVille(2).appartenance() == ERace.ORC); 

	}

	//se deplacer en bas
	@Test
	public void test5_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);
		//on recupere l'ancience position du villageois.
		Point PosBefore = moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1));
		//Operation
		//le villageois descend
		moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.RIEN, 1, 3, 267 , 5);
		//Oracle

		assertPerso("test5 : Le villageois 1 ne s'est pas bien deplace", !PosBefore.equals(moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1)))); 


	}

	//se déplacé sur une muraille
	@Test
	public void test6_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);

		//on met le villageois juste en dessous d'une muraille
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(moteurJeu.positionMurailleX(moteurJeu.getMuraille(0)),moteurJeu.positionMurailleX(moteurJeu.getMuraille(0))+52));
		//Operation
		//le villageois 1  va vers le haut et se retrouve dans la muraille
		moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.RIEN, 1, 3, 90 , 5);

		//Oracle
		//je teste si le villageois est bien monte d'un pas qui le fait aller sur la muraille
		assertPerso("test6 : le villageois s'est deplace dans la muraille , c'est impossible ",!moteurJeu.estSurMuraille(moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1)))); 


	}

	//se deplace en dehors du terrain
	@Test
	public void test7_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(1,1));
		Point PosBefore = moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1));

		//Operation
		//on se deplace vers le haut en dehors du terrain

		moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.RIEN, 1, 3, 49 , 5);
		
		//Oracle
		assertPerso("test7 : le villageois s'est deplace en dehors du terrain puis doit etre remis a sa position d'origine normalement mais ce n'est pas le cas",PosBefore.equals(moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1)))); 

	}


	//entrer dans une hotel de ville abandonne
	//=>verifier que la partie est gagnee? 
	@Test
	public void test8_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);
		//l hotel de ville est abandonnee
		moteurJeu.HotelDeVille(1).abandoned();
		//Un villageois doit avoir au moins une piece d'or pour entrer dans HotelVille
		moteurJeu.getVillageois(1).setQtor(1);


		//Operation
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 1 , 5);

		//Oracle
		assertPerso("test8 : le villageois 1 prend possession de son hotel de ville",moteurJeu.HotelDeVille(1).appartenance() == ERace.HUMAIN); 

	}



	//deplacement meme case s'ils sont de races differentes
	@Test
	public void test9_1() {

		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);
		//HUMAIN
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1), new Point(1,1)); //1+4 =5
		//ORC
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(3), new Point(1,1));//8-3 =5
		//Operation
		moteurJeu.pasJeu(ECommande.DEPLACER, ECommande.DEPLACER, 1, 3, 49 , 49);
		//Oracle
		assertPerso("test9 : le villageois 1 HUMAIN et LE VILLAGEOIS 3 ORC , sont censes etre mis sur la meme case et pourtant. ", 
				moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(3)).equals(
						moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(1))) ); 


	}

	//test hotel de ville abandonne des ennemis 
	//verifier que la partie est gagnee
	@Test
	public void test10_1(){
		//Condition Initiale : aucune
		moteurJeu.init(1664,1000,1000);

		//l hotel de ville est abandonnee
		moteurJeu.HotelDeVille(2).abandoned();
		//un villageois pour entrer doit avoir un PO
		moteurJeu.getVillageois(1).setQtor(1);
		//mettre le villageois 1 a cote
		moteurJeu.positionsVillageois().put(moteurJeu.getVillageois(1),new Point(moteurJeu.positionHotelVilleX(2),moteurJeu.positionHotelVilleY(2) )); //1+4 =5


		//Operation
		moteurJeu.pasJeu(ECommande.ENTRERHOTELVILLE, ECommande.RIEN, 1, 3, 2 , 5);
		//Oracle
		assertPerso("test10_assert1 :HOTEL DE VILLE n'est pas DEVENUE HUMAINE alors qu'elle le devrait  ", moteurJeu.HotelDeVille(2).appartenance() ==ERace.HUMAIN);

		assertPerso("test10_assert2 : partie fini PAS FINI les HOMME DEVRAIT AVOIR GAGNE mais ce n'est pas le cas alors qu'il le devrait ", moteurJeu.estFini());


	}

	//tester 51 pas jeu => HotelVille abandonnee
	@Test
	public void test11_1(){
		//Condition Initiale : aucune
				moteurJeu.init(1664,1000,1000);
							
	    //Operation
				for(int i=0 ;i<51;i++)
					   moteurJeu.pasJeu(ECommande.RIEN, ECommande.RIEN, 1, 3, 0 , 0);
				
		//Oracle
				assertPerso("test11: l'hotel de ville n'est pas abandonnee alors qu'elle le devrait ", moteurJeu.HotelDeVille(1).appartenance() == ERace.RIEN);

		
		
	}

	//tester 51 pas jeu => mine abandonee
	@Test
	public void test12_1(){
		//Condition Initiale : 
		moteurJeu.init(1664,1000,1000);
		moteurJeu.getMine(1).accueil(ERace.HUMAIN);
		
//Opération
		for(int i=0 ;i<51;i++)
			   moteurJeu.pasJeu(ECommande.RIEN, ECommande.RIEN, 1, 3, 0 , 0);
		
//Oracle
		assertPerso("test12: la mine n'est pas abandonnee alors qu'elle le devrait ", moteurJeu.getMine(1).appartenance() == ERace.RIEN);

		
	}

	//tester FIN DE PARTIE AU BOUT DE 100 PAS JEU pour une partie a 100 pas jeu
	@Test
	public void test13_1(){
		//Condition Initiale : 
				moteurJeu.init(100,1000,1000);			
				
		//Operation
				for(int i=0 ;i<100;i++)
					   moteurJeu.pasJeu(ECommande.RIEN, ECommande.RIEN, 1, 3, 0 , 0);
				
		//Oracle
				assertPerso("test13_1: La partie devrait etre fini au bout de nbPasJeu max mais ce n'est pas le cas ", moteurJeu.estFini()==true);
				assertPerso("test13_2: La partie n est pas DRAW ,error ", moteurJeu.resultatFinal()==EResultat.DRAW);
				
		
	}

	//TESTER GAGNER PARTIE AVEC 1664 PO...
	@Test
	public void test14_1(){
		//Condition Initiale : 
		moteurJeu.init(1664,1000,1000);
		
		//Opération
		moteurJeu.HotelDeVille(1).depot(1664);
		
		//Oracle
		assertPerso("test14: L'HotelVillel HUMAIN contient 1664 pieces d'or. La partie devrait etre fini mais ce n'est pas le cas ", moteurJeu.estFini()==true);

	}
	
	
	

}



	

