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
		 * 		pasJeu(c, c2, n, n2, a, a2) 
		 * require !estFini(M) ^ c = DEPLACER => 0 <= a <= 360
		 * ^ c = ENTRERMINE => a in numerosMine(M) ^ peutEntrer(M, n, a) 
		 * ^ Commande = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n)
		 * 	 ^ c2 = DEPLACER => 0 <= a2 <= 360
		 * ^ c2 = ENTRERMINE => a2 in numerosMine(M) ^ peutEntrer(M, n2, a2)  
		 * ^ c2 = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n2) ^
		 * ^ n != n2
		 * post 
				pasJeuCourant(pasJeu(M, c, numVillageois, arg)) = pasJeuCourant(M) + 1
				getMine(pasJeu(M, c, numVillageois, arg), numMine) = Mine::abandoned(getMine(M,numMine)) si 
				c != ENTRERMINE || arg != numMine
			    ^
			    Mine::accueil(getMine(M, numMine)) sinon
			    A FINIR PARALOS VILLAGOS
				
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
	 
	 
	 
