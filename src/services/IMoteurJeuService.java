package services;

import java.util.List;
import java.util.Set;

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
	 IVillageoisService getVillageois(int n);
	 int positionVillageoisX(int n);
	 int positionVillageoisY(int n);
	 
	 List<IMineService> numerosMine();
	 IMineService getMine(int n);
	 int positionMineX(int n);
	 int positionMineY(int n);
	 /*
		 * pre 
		 * 		peutEntrer(M, numVillageois, numMine) require 
		 *      numVillageois in numerosVillageois(M, numVillageois) ^
		 *      numMine in numerosMine(M, numMine);
		 */
	 boolean peutEntrer(int numVillageois, int numMine);
	 
	 
	 /*
		 * pre 
		 * 		HotelVille(M, n) require 
		 *      n in [1,2]
		 */
	 IHotelVilleService HotelDeVille(int n);
	 int positionHotelVilleX(int n);
	 int positionHotelVilleY(int n);
	 /*
		 * pre 
		 * 		peutEntrerHotelVille(M, numVillageois, numMine) require 
		 *      numVillageois in numerosVillageois(M, numVillageois)
		 *      numHotelDeVille in [1,2]
		 */
	 boolean peutEntrerHotelVille(int numVillageois, int numHotelDeVille);
	 
	 List<IRouteService> numerosRoute();
	 IRouteService getRoute(int n);
	 int positionRouteX(int n);
	 int positionRouteY(int n);
	 /*
		 * pre 
		 * 		estSurRoute(M, numVillageois) require 
		 *      numVillageois in numerosVillageois(M, numVillageois) 
		 */
	 boolean estSurRoute(int numVillageois);
	 
	 List<IMurailleService>numerosMuraille();
	 IMurailleService getMuraille(int n);
	 int positionMurailleX(int n);
	 int positionMurailleY(int n);
	 
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
		 * ^ c2 = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n2)
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
		 * estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M)) >= 1664 || pasJeuCourant(M) = maxPasJeu(M))
		 * resultatFinal(M) = GAGNE <=> HotelVille:orRestant(hotelDeville(M)) >= 1664
		 * peutEntrer(M, numVillageois, numMine) =(min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionMineX(M, numMine), positionMineY(M, numMine)) <= 51
		 * peutEntrerHotelVille(M, numVillageois) = (min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionHotelVilleX(M), positionHotelVilleY(M)) <= 51
		 */
}
	 
	 
	 
