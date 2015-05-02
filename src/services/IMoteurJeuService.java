package services;

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
	 
	 Set<Integer> numerosVillageois();
	 IVillageoisService getVillageois(int n);
	 int positionVillageoisX(int n);
	 int positionVillageoisY(int n);
	 
	 Set<Integer> numerosMine();
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
	 
	 
	 
	 IHotelVilleService HotelDeVille();
	 int positionHotelVilleX(int n);
	 int positionHotelVilleY(int n);
	 /*
		 * pre 
		 * 		peutEntrerHotelVille(M, numVillageois, numMine) require 
		 *      numVillageois in numerosVillageois(M, numVillageois)
		 */
	 boolean peutEntrerHotelVille(int numVillageois);
	 
	 Set<Integer> numerosRoute();
	 IRouteService getRoute(int n);
	 int positionRouteX(int n);
	 int positionRouteY(int n);
	 /*
		 * pre 
		 * 		estSurRoute(M, numVillageois, numRoute) require 
		 *      numVillageois in numerosVillageois(M, numVillageois) ^
		 *      numRoute in numerosRoute(M, numRoute);
		 */
	 boolean estSurRoute(int numVillageois, int numRoute);
	 
	 Set<Integer> numerosMuraille();
	 IRouteService getMuraille(int n);
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
				
		 */
		IMoteurJeuService init(int maxPasJeu,int l,int h);
		
		/**
		 * Operators
		 */
		
		/*
		 * pre 
		 * 		pasJeu(ECommande Commande,int numVillageois,int argument) 
		 * require !estFini(M) ^ Commande = DEPLACER => 0 <= argument <= 360
		 * ^ Commande = ENTRERMINE => argument in numerosMine(M) ^ peutEntrer(M, numVillageois, argument) 
		 * ^ Commande = ENTRERHOTELVILLE => peutEntrerHotelVille(M, numVillageois)	
		 * post 
				pasJeuCourant(pasJeu(M, c, numVillageois, arg)) = pasJeuCourant(M) + 1
				getMine(pasJeu(M, c, numVillageois, arg), numMine) = Mine::abandoned(getMine(M,numMine)) si 
				c != ENTRERMINE || arg != numMine
			    ^
			    Mine::accueil(getMine(M, numMine)) sinon
			    A FINIR PARALOS VILLAGOS
				
		 */
		IMoteurJeuService pasJeu(ECommande Commande,int numVillageois,int argument);
		
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
	 
	 
	 
