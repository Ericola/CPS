package services;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;

import require.IRequireHotelVille;
import require.IRequireMine;
import require.IRequireMuraille;
import require.IRequireRoute;
import require.IRequireVillageois;
import enums.ECommande;
import enums.EResultat;

public interface IMoteurJeuService extends IRequireHotelVille, IRequireMine, IRequireRoute, IRequireVillageois, IRequireMuraille {
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
	IVillageoisService getVillageois(int n);
	/*  pre  
	 *		 positionVillageoisX(M, v) require v in numerosVillageois(M)
	 */
	int positionVillageoisX(IVillageoisService v);
	/*  pre  
	 *		 positionVillageoisY(M, v) require v in numerosVillageois(M)
	 */
	int positionVillageoisY(IVillageoisService v);

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
		     	Soit Rectangle r = Rectangle(0, 0, LargeurTerrain(M), HauteurTerrain(M))  
		     if(!(estSurMuraille(M, p) &&  r.contains(Point(yo.x, yo.y)) && r.contains(Point(yo.x + 10, yo.y))
					&& r.contains(Point(yo.x +10, yo.y + 10)) && r.contains(Point(yo.x, yo.y+10)))
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
		    Mine::accueil(getMine(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2), a)
		    
		let entrerHotelVille(M, n, a) =
		    HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2)), a) = HotelVille::orRestant(getHotelVille(M, a) + Villageois::getQtor(getVillageois(M, n)
		   	Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) = 0	   
			HotelVille::accueil(HotelDeVille(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), a)
			
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
		   
	    3. Villageois ayant fini la corvee et Gestion orRestant des mines
		Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M)
		       Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), x) =  Villageois::getQtor(M, x) + 1
		       VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1	
		       MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1
		
		 Soit nbVillageoisDansMine = nombre de n tel que getVillageois(M, n) dans la mine m avec VillageoisAttente(M).get(n) == pasJeuCourant(M);
		Pour tout m in [0, numerosMine(M).size()[
	         Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m) = orRestant(getMine(M, m) - nbVillageoisDansMine(m)
		      
		4.Gestion AbandonCompteur et appartenance 
		  Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size() ^ !(Mine::estLaminee(getMine(M, m))
		     Si pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)), MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) != m ^ Mine::abandonCompteur(getMine(M, m)) != 51
		        Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Mine::abandonCompteur(getMine(M, m)) + 1
		  	 
		  	 Si Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) == 51
		  		Mine::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2))
		  	 Sinon
		  	    Mine::appartenance(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = Mine::appartenance(getMine(M, m)) 

		  Pour tout h in [1,2] ^ si c = ECommande.ENTRERHOTELVILLE, h != a ^ si c2 = ECommande.ENTRERHOTELVILLE, h!= a2 
		     Si HotelVille::abandonCompteur(HotelDeVille(M, h)) != 51
		     HotelVille::abandonCompteur(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) =  HotelVille::abandonCompteur(HotelDeVille(M, h)) + 1
		  
		     Si HotelVille::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) == 51
		  	   HotelVille::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2))    
		     
		     Sinon
		  	   HotelVille::appartenance(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelVille::appartenance(HotelDeVille(M, h))
	         
	    5. Gestion OrRestant HotelVille et mine laminee
		     Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size()[ 
		       Soit x = 0
		       Pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2))
		     	 Si MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) = m
		     	   x = x + 1
		     	 Si Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = 0 || Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) - x <= 0
		           Mine::estLaminee(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = true
		           
		     Pour tout h in [1, 2[ 
		         Si !((c = ECommande.ENTRERHOTELVILLE ^ h = a) || (c2 = ECommande.ENTRERHOTELVILLE ^ h = a2))
		           HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  HotelVille::orRestant(HotelDeVille(M, m))   
		 
	   	6. Gestion Point de vie des murailles et Villageois (pas de gestion combat pour l'instant)
		    Pour tout n in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		      Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) =  Villageois::getPdv(getVillageois(M, n))
		     
		    Pour tout m in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		      Muraille::getPdv(getMuraille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Muraille::getMuraille(getMuraille(M, m))
	 */
	IMoteurJeuService pasJeu(ECommande Commande,ECommande Commande2, int numVillageois, int numVillageois2,
			int argument, int argument2);

	/**
	 * Invariants
	 */
	/*
	0 <= getPasJeuCourant(M) <= getMaxPasJeu(M)
		estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M,1)) >= 1664 || HotelVille::orRestant(hotelDeVille(M,2)) >= 1664|| pasJeuCourant(M) = maxPasJeu(M))
		
		resultatFinal(M) = EResultat.HUMAINGAGNE =(min) HotelVille:orRestant(hotelDeville(M, 1)) >= 1664 ||(HotelVille:appartenance(M,1) = ERace.HUMAIN && HotelVille::appartenance(M,2) = ERace.HUMAIN)
		resultatFinal(M) = EResultat.ORCGAGNE =(min) HotelVille:orRestant(hotelDeville(M, 2)) >= 1664 ||(HotelVille:appartenance(M,1) = ERace.ORC && HotelVille::appartenance(M,2) = ERace.ORC)
		resultatFinal(M) = EResultat.DRAW =(min) PasJeuCourant(M)=MaxPasJeu(M) 
		
		peutEntrer(M, numVillageois, numMine) =(min) soit r = Rectangle(positionMineX(M, getMine(M, numMine)), positionMineY(M, getMine(M, numMine)), Mine::getLargeur(getMine(M, numMine)), Mine::getLargeur(getMine(M, numMine))),
		r1 = Rectangle(positionVillageoisX(M, getVillageois(M, numVillageois)) + Villageois::getLargeur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getLargeur(getVillageois(M, numVillageois))/2, positionVillageoisY(M, getVillageois(M, numVillageois)) + Villageois::getHauteur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getHauteur(getVillageois(M, numVillageois))/2, Villageois::getLargeur(getVillageois(M, numVillageois)) + 51, Villageois::getHauteur(getVillageois(M, numVillageois)) + 51),
		alors r.intersects(r1) && (!Mine::estLaminee(getMine(M, numMine))) && (Mine::appartenance(getMine(M, numMine)) == ERace.RIEN || Mine::appartenance(getMine(M, numMine)) == Villageois::getRace(getVillageois(M, numVillageois))
		&& soit nbVillageoisDansMine = nbr(i in [0, MineMinee(M).size()[ tel que MineMinee(M).get(i) = numMine, Mine::orRestant(getMine(M, numMine)) - nbVillageoisDansMine > 0
		
		peutEntrerHotelVille(M, numVillageois, numHotelVille) = (min) soit r = Rectangle(positionHotelVilleX(M, HotelDeVille(M, numHotelVille)), positionHotelVilleY(M, HotelDeVille(M, numHotelVille)), HotelVille::getLargeur(HotelDeVille(M, numHotelVille)), HotelVille::getHauteur(HotelDeVille(M, numHotelVille))),
		r1 = Rectangle(positionVillageoisX(M, getVillageois(M, numVillageois)) + Villageois::getLargeur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getLargeur(getVillageois(M, numVillageois))/2, positionVillageoisY(M, getVillageois(M, numVillageois)) + Villageois::getHauteur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getHauteur(getVillageois(M, numVillageois))/2, Villageois::getLargeur(getVillageois(M, numVillageois))/2 + 51, Villageois::getHauteur(getVillageois(M, numVillageois))/2 + 51),
		alors r.intersects(r1) && (Villageois::getRace(getVillageois(numVillageois)) == HotelVille::appartenance(HotelDeVille(numHotelVille)) || HotelVille::appartenance(HotelDeVille(M, numHotelVille)) == ERace.RIEN) 
     
        estSurRoute(M, numVillageois) = (min) Rectangle(positionRouteX(R, n), positionRouteY(R, n), Route::getLargeur(R), Route::getHauteur(R)).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un n in [0, numeroRoutes(M).size() - 1]
      
        estSurMuraille(M, Point p) = (min) Rectangle(positionMurailleX(M, m), positionMurailleY(M, m), Muraille::getLargeur(getMuraille(M, m)), Muraille::getHauteur((getMuraille(M, m))).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un m in [0, numeroMuraille(M).size() - 1]
	 
	    Soit Rectangle r = Rectangle(0,0, l, h)
			Pour chaque Villageois V in numerosVillageois(M)
		   		Soit px = positionsVillageois(M).get(V).x
		   		Soit py = positionsVillageois(M).get(V).y
		   		r.contains(Point(px,py)) = true
		   		r.contains(Point(px + 10,py)) = true
		   		r.contains(Point(px + 10,py + 10)) = true
		   		r.contains(Point(px,py + 10)) = true  
	 */
}



