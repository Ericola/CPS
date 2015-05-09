package services;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;

import enums.ECommande;
import enums.EResultat;

public interface IMoteurJeuService {
	/**
	 * Observators
	 */
	int LargeurTerrain();
	int HauteurTerrain();
	int MaxPasJeu();	 
	int pasJeuCourant();
	boolean estFini();
	EResultat resultatFinal();

	List<IVillageoisService> numerosVillageois();
	/*  pre  
	 *		 getVillageois(M, n) require n in [0, numerosVillageois(M).size()]	 
	 */
	IVillageoisService getVillageois(IVillageoisService v);
	/*  pre  
	 *		 positionVillageoisX(M, v) require v in numerosVillageois(M)
	 */
	int positionVillageoisX(IVillageoisService v);
	/*  pre  
	 *		 positionVillageoisY(M, v) require v in numerosVillageois(M)
	 */
	int positionVillageoisY(int n);

	List<IMineService> numerosMine();
	/*  pre  
	 *		 getMine(M, n) require n in [0, numerosMine(M).size()[
	 */
	IMineService getMine(int n);
	/*  pre  
	 *		 positionMineX(M, m) require m in numerosMine(M)
	 */
	int positionMineX(IMineService m);
	/*  pre  
	 *		 positionMineY(M, m) require m in numerosMine(M)
	 */
	int positionMineY(IMineService m);
	/*
	 * pre 
	 * 		peutEntrer(M, numVillageois, numMine) require 
	 *       numVillageois in [0, numerosVillageois(M).size()[
	 *     ^ numMine in [0, numerosMine(M).size[	
	 */
	boolean peutEntrer(int numVillageois, int numMine); 

	/*
	 * pre 
	 * 		HotelDeVille(M, n) require 
	 *      n in [1,2]
	 */
	IHotelVilleService HotelDeVille(int n);
	/*  pre  
	 *		 positionHotelVilleX(M, n) require n in [1,2]
	 */
	int positionHotelVilleX(int n);
	/*  pre  
	 *		 positionHotelVilleY(M, n) require n in [1,2]
	 */
	int positionHotelVilleY(int n);
	/*
	 * pre 
	 * 		peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require 
	 *      numVillageois in [0, numerosVillageois(M).size()[
	 *      numHotelDeVille in [1,2]
	 */
	boolean peutEntrerHotelVille(int numVillageois, int numHotelDeVille);

	List<IRouteService> numerosRoute();

	/*  pre  
	 *		 getRoute(M, r) require  r in [0, numerosRoute(M).size()[
	 */
	IRouteService getRoute(int r);
	/*  pre  
	 *		 positionRouteX(M, r) require r in numerosRoute(M)
	 */
	int positionRouteX(IRouteService r);
	/*  pre  
	 *		 positionRouteY(M, r) require r in numerosRoute(M)
	 */
	int positionRouteY(IRouteService r);
	/*
	 * pre 
	 * 		estSurRoute(M, numVillageois) require 
	 *      numVillageois in [0, numerosVillageois(M).size()[
	 */
	boolean estSurRoute(int numVillageois);

	List<IMurailleService>numerosMuraille();
	/*  pre  
	 *		getMuraille(M, n) require n in [0, numerosMuraille(M).size()[
	 */
	IMurailleService getMuraille(int n);
	/*  pre  
	 *		 positionMurailleX(M, m) require m in [0, numerosMuraille(M).size()[
	 */
	int positionMurailleX(IMurailleService m);
	/*  pre  
	 *		 positionMurailleY(M, m) require m in [0, numerosMuraille(M).size()[
	 */
	int positionMurailleY(IMurailleService m);
	boolean estSurMuraille(Point p);

	List<Integer> VillageoisAttente();
	List<Integer> MineMinee();
	HashMap<IVillageoisService, Point> positionsVillageois();
	HashMap<Object, Point> positions();

	/**
	 * Constructors
	 */
	/*
	 * pre 
	 * 		init(MaxPasJeu,l,h) require maxPasJeu >= 0 ^ l>=600 ^ h>=400
	 * post 
				getMaxPasJeu(init(p,l,h)) = p
				getPasJeuCourant(init(p,l,h)) = 0
				LargeurTerrain(init(p,l,h)) = l
				HauteurTerrain(init(p,l,h)) = h

				Pour tout n in [0, numerosVillageois(init(p,l,h)).size()[   
		   			Villageois::getLargeur(getVillageois(init(p,l,h), n)) = 10
		   			Villageois::getHauteur(getVillageois(init(p,l,h), n)) = 10

				Pour tout Villageois V ayant ERace = HUMAIN && in numerosVillageois(init(p,l,h))
					Villageois::getForce(V) = 3
					Villageois::getVitesseinit(V) = 4
					distance(positionVillageoisX(M, V), positionVillageois(M, V), positionHotelVilleX(M, 1), positionHotelVilleY(M, 1)) <= 51


				Pour tout Villageois V ayant ERace = ORC && in numerosVillageois(init(p,l,h))
					Villageois::getForce(V) = 4
					Villageois::getVitesse(V) = 3
					distance(positionVillageoisX(M, V), positionVillageois(M, V), positionHotelVilleX(M, 2), positionHotelVilleY(M, 2)) <= 51

				Pour toute mine m in numerosMine(init(p,l,h))
					Mine::getLargeur(m) = 50
					Mine::getHauteur(m) = 50

				Pour tout HotelVille hv in HotelVille(init(p,l,h), 1) V HotelVille(init(p,l,h), 2)
					HotelVille::getLargeur(hv) = 50
					HotelVille::getHauteur(hv) = 50
					HotelVille(1)::appartenance(hv) = HUMAIN
					HotelVille(2)::appartenance(hv) = ORC

				MineMinee(init(p,l,h)).size() = numerosVillageois(init(p,l,h)).size()
				VillageoisAttente(init(p,l,h)).size() = numerosVillageois(init(p,l,h)).size()

				Pour tout x in [0, MineMinee(init(p,l,h)).size()[
					MineMinee(init(p,l,h)).get(x) = -1

				Pour tout x in [0, VillageoisAttente(init(p,l,h)).size()[
					VillageoisAttente(init(p,l,h)).get(x) = -1

		pasJeuCourant(pasJeu(M ,c, c2, n, n2, a, a2)) = pasJeuCourant(M) + 1
	 */
	IMoteurJeuService init(int maxPasJeu,int l,int h);

	/**
	 * Operators
	 */

	/*
	 * pre 
		 		pasJeu(M ,c, c2, n, n2, a, a2)  require not estFini(M) 
			^ n in [0, numerosVillageois.size[ ^ getVillageois(M, n)::getRace() == HUMAIN ^ VillageoisAttente(M, n) = 0
		   	^ n2 in [0, numerosVillageois.size[ ^ getVillageois(M, n)::getRace() == ORC ^ VillageoisAttente(M, n2) = 0
		 	  c = DEPLACER => 0 <= a <= 360 ^ c = ENTRERMINE => a in numerosMine(M) ^ 
		      peutEntrer(M, n, a)  ^ Commande = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n)
		    ^ c2 = DEPLACER => 0 <= a2 <= 360 ^ c2 = ENTRERMINE => a2 in numerosMine(M)
		    ^ peutEntrer(M, n2, a2)  ^ c2 = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n2)
	 * post 
				let deplacer(M, n, a) =
			 1. On recupere la vitesse du villageois
			 soit vitesse = Villageois::getVitesse(getVillageois(M, n))
			 soit p un point vide

			 2. On verifie si le villageois est sur une route. Si oui, on double la vitesse.
			 if(estSurRoute(M, n)) then
		  	 vitesse = 2*vitesse

		     3. On determine sur quel case on va aller a l'aide de l'argument a et de la vitesse.
		     // Pour les diagonales, on a decider de diviser la vitesse par 2 et d'ajouter 1 
		     //pour equilibrer les deplacements (l'ajout de 1 traite le cas ou la vitesse initiale etait de 1)
		     if(a < 46) then // on va en haut a droite.
		     	p = Point(PositionVillageoisX(M, n) + vitesse/2 + 1, PositionVillageoisY(M, n) - vitesse/2 + 1)
		     if(a < 91) then // on va en haut  
		        p = Point(PositionVillageoisX(M, n) , PositionVillageoisY(M, n) - vitesse
		     if(a < 136) then // on va en haut a gauche
		        p = Point(PositionVillageoisX(M, n) - vitesse/2 + 1, PositionVillageoisY(M, n) - vitesse/2 + 1)
		     if(a < 181) then // on va a gauche
		        p = Point(PositionVillageoisX(M, n) - vitesse, PositionVillageoisY(M, n))
		     if(a < 226) then // on va en bas a gauche
		        p = Point(PositionVillageoisX(M, n) - vitesse/2 + 1, PositionVillageoisY(M, n) + vitesse/2 + 1)
		     if(a < 271) then // on va en bas
		        p = Point(PositionVillageoisX(M, n), PositionVillageoisY(M, n) + vitesse)
		     if(a < 316) then // on va en bas a droite
		        p = Point(PositionVillageoisX(M, n) + vitesse/2 + 1, PositionVillageoisY(M, n) + vitesse/2 + 1)
		     if(a < 361) then // on va a droite
		        p = Point(PositionVillageoisX(M, n) + vitesse, PositionVillageoisY(M, n)) 

		     4. On determine si on peut aller sur la case ou non. Si oui on modifie les coordonnees du villageois
		     if(!(estSurMuraille(M, p) || p.x < 0 || p.x >= LargeurTerrain(M) || p.y < 0 || p.y >= HauteurTerrain(M)) then
		        positions.put(getVillageois(n), p)
		        //POSTCONDITIONS
		        positionVillageoisX(pasJeu(M ,c, c2, n, n2, a, a2), getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = p.x
		        positionVillageoisY(pasJeu(M ,c, c2, n, n2, a, a2), getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = p.y


		     Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getQtor(M, n) 
		     Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getPdv(M, n) 

	     	let entrerMine(M, n, a) =  
		    //POSTCONDITIONS     
		        VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = PasJeuCourant(M) + 16
		        MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = a
		        Mine::accueil(getMine(pasJeu(M ,c, c2, n, n2, a, a2)), a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2), a)

	    	let entrerHotelVille(M, n, a) =
		        HotelVille::orRestant(getHotelVille(pasJeu(M ,c, c2, n, n2, a, a2)), 2) = HotelVille::orRestant(getHotelVille(M, 2) + Villageois::getQtor(getVillageois(M, n2)
		     	Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n2) = 0	   
		     	HotelVille::accueil(getMine(pasJeu(M ,c, c2, n, n2, a, a2)), a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), a)

		    0. On incremente le nombre de pas de jeu
		    PasJeuCourant(pasJeu(pasJeu(M ,c, c2, n, n2, a, a2))) = PasJeuCourant(M) + 1         

		    1. Chaque type de commandes a des PostConditions Specifiques. 
		        if(c == ECommande.DEPLACER)
		          deplacer(M, n, a)

		        if(c == ECommande.ENTRERMINE)
		          entrerMine(M, n, a)

		        if(c == ECommmande.ENTRERHOTELVILLE)
		          entrerHotelVille(M, n, a)

		        if(c2 == ECommande.DEPLACER)
		          deplacer(M, n2, a2)	   

		        if(c2 == ECommande.ENTRERMINE)
		          entrerMine(M, n2, a2)

		        if(c == ECommmande.ENTRERHOTELVILLE)
		          entrerHotelVille(M, n2, a2)

		     2. PositionsVillageois
		        Pour tout Villageois V in numerosVillageois(M) ^ si c = DEPLACER, V != getVillageois(M, n) ^ si c2 = DEPLACER V != getVillageois(M, n2) 
		          positionVillageoisX(pasJeu(M ,c, c2, n, n2, a, a2), V) = positionVillageoisX(M, V)
		          positionVillageoisY(pasJeu(M, c, c2, n, n2, a, a2), V) = positionVillageoisY(M, V)

	         3. Villageois ayant fini la corvee
		        Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M)
		          Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), x) =  Villageois::getQtor(M, x) + 1
		          VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(n2) = -1
		        Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), MineMinee(M).get(x)) = orRestant(getMine(M, MineMinee(M).get(x)) - 1
		        MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1

		     4.Gestion AbandonCompteur et appartenance 
		        Pour tout m in [0, numerosMine.size()[   
		          Si pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)), MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) != m ^ Mine::abandonCompteur(getMine(M, m)) != 51
		          Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Mine::abandonCompteur(getMine(M, m)) + 1

		  	    Si Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) == 51
		  		  Mine::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2))
		  	    Sinon
		  	      Mine::appartenance(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = Mine::appartenance(getMine(M, m)) 

		        Pour tout h in [1,2] ^ si c = ENTRERHOTELVILLE, h != a ^ si c2 = ENTRERHOTELVILLE, h!= a2 ^ HotelVille::abandonCompteur(HotelDeVille(M, h)) != 51
		          HotelVille::abandonCompteur(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) =  HotelVille::abandonCompteur(HotelDeVille(M, h)) + 1

		        Si HotelVille::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) == 51
		  	      HotelVille::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2))    

		        Sinon
		  	      HotelVille::appartenance(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelVille::appartenance(HotelDeVille(M, h))

	         5. Gestion OrRestant et mine laminee
		        Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size()[ 
		          Soit x = 0
		          Pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2))
		     	    Si MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) != m || (MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) == m && VillageoisAttente(M).get(i) != PasJeuCourant(M))
		     	      Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Mine::orRestant(getMine(M, m))
		     	    Si MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) = m
		     	      x = x + 1

		     	  Si Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = 0 || Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) - x <= 0
		            Mine::estLaminee(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = true

		          Pour tout h in [1, 2[ 
		            Si !((c = ENTRERHOTELVILLE ^ h = a) || (c2 = ENTRERHOTELVILLE ^ h = a2))
		              HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  HotelVille::orRestant(HotelDeVille(M, m))   

	   	      6. Gestion Point de vie des murailles et Villageois (pas de gestion combat pour l'instant)
		         Pour tout n in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		         Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) =  Villageois::getPdv(getVillageois(M, n))

		         Pour tout m in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		            Muraille::getPdv(getMuraille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Muraille::getMuraille(getVillageois(M, m))

	 */
	IMoteurJeuService pasJeu(ECommande Commande,ECommande Commande2, int numVillageois, int numVillageois2,
			int argument, int argument2);

	/**
	 * Invariants
	 */
	/*
	 * 0 <= getPasJeuCourant(M) <= getMaxPasJeu(M)
	 * estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M)) >= 1664 V pasJeuCourant(M) = maxPasJeu(M))
	 * resultatFinal(M) = GAGNE <=> HotelVille:orRestant(hotelDeville(M)) >= 1664
	 * peutEntrer(M, numVillageois, numMine) =(min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionMineX(M, numMine), positionMineY(M, numMine)) <= 51
	 * peutEntrerHotelVille(M, numVillageois) = (min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionHotelVilleX(M), positionHotelVilleY(M)) <= 51
	 */
}



