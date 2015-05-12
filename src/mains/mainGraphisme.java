package mains;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;

import enums.ECommande;
import enums.ERace;

import implementations.HotelVilleImplem;
import implementations.MineImplem;
import implementations.MoteurJeuImplem;
import implementations.MurailleImplem;
import implementations.RouteImplem;
import implementations.VillageoisImplem;

public class mainGraphisme{

	public static void main(String[] args) {


		IMoteurJeuService moteurJeu = new MoteurJeuImplem();


		IHotelVilleService hotelDeVille = new HotelVilleImplem();
		IHotelVilleService hotelDeVille2 = new HotelVilleImplem();

		hotelDeVille.init(50, 50, ERace.HUMAIN);
		hotelDeVille2.init(50, 50, ERace.ORC);

		ArrayList<IVillageoisService> villageois= new ArrayList<IVillageoisService>();
		ArrayList<Point> positionsVillageois = new ArrayList<Point>();
		IVillageoisService v = new VillageoisImplem();
		IVillageoisService v1 = new VillageoisImplem();
		IVillageoisService v2 = new VillageoisImplem();
		IVillageoisService v3 = new VillageoisImplem();

		v.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v1.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v2.init(ERace.ORC, 10, 10, 4, 3, 60);
		v3.init(ERace.ORC, 10, 10, 4, 3, 60);
		//
		//moteurJeu.positions
		positionsVillageois.add( new Point(500-60,60));
		positionsVillageois.add( new Point(500+60,60));
		positionsVillageois.add( new Point(500-60, 940));
		positionsVillageois.add(new Point(500+60, 940));

		villageois.add(v);
		villageois.add(v1);
		villageois.add(v2);
		villageois.add(v3);


		ArrayList<IMineService> mines=new ArrayList<IMineService>();
		ArrayList<Point> positionsMines = new ArrayList<Point>();
		IMineService m = new MineImplem();
		IMineService m1 = new MineImplem();
		IMineService m2 = new MineImplem();
		IMineService m3 = new MineImplem();
		m.init(50, 50);
		m1.init(50, 50);
		m2.init(50, 50);
		m3.init(50, 50);
		//
		positionsMines.add( new Point(10, 10));
		positionsMines.add( new Point(1000-60, 10));
		positionsMines.add( new Point(1000-60, 1000-60));
		positionsMines.add( new Point(10,1000-60));

		mines.add(m);
		mines.add(m1);
		mines.add(m2);
		mines.add(m3);


		ArrayList<IRouteService> routes=new ArrayList<IRouteService>();
		ArrayList<Point> positionsRoutes = new ArrayList<Point>();
		IRouteService r = new RouteImplem();
		IRouteService r1 = new RouteImplem();

		r.init(1000, 50);
		r1.init(50 , 1000 - 150);
		//
		positionsRoutes.add( new Point(0, 1000/2));
		positionsRoutes.add( new Point(1000/2, 80));
		//
		routes.add(r);
		routes.add(r1);

		ArrayList<IMurailleService> murailles=new ArrayList<IMurailleService>();
		IMurailleService mu = new MurailleImplem();
		//
		mu.init(50, 50, 100);
		//
		ArrayList<Point> positionsMurailles = new ArrayList<Point>();
		positionsMurailles.add( new Point(1000/2, 1000/2));
		//
		murailles.add(mu);




		moteurJeu.init(1664,1000,1000);

		moteurJeu.bindMine(mines, positionsMines);
		moteurJeu.bindMuraille(murailles, positionsMurailles);
		moteurJeu.bindRoute(routes, positionsRoutes);
		moteurJeu.bindVillageois(villageois, positionsVillageois);


		moteurJeu.bindHotelVille(hotelDeVille, hotelDeVille2, new Point(500,60), new Point(500,940));


		ThreadListener t = new ThreadListener(moteurJeu);
		t.start();
		System.out.println("Début de la simulation");
		System.out.println("Commandes disponibles : ");
		System.out.println("DEPLACER/numVillageois/angle");
		System.out.println("ENTRERHOTELVILLE/numVillageois/numHotelVille");
		System.out.println("ENTRERMINE/numVillageois/numMine");
		System.out.println("Pour quitter, ecrivez QUIT");
		System.out.println("Informations : \nCarte 1000x1000. Nbr MaxPasJeu 1664 \nNbr de villageois : 4, "
				+ "2 humains avec pour indice 0 et 1 (a utiliser pour numVillageois) et 2 orcs avec pour indice 2 et 3 \n"
				+ "4 mines, chacunes placées aux 4 extrémités de la carte. 2 Hotel de Ville, le 1er humain, place entre les 2 mines en haut de la carte."
				+ "\nle deuxieme, orc,  en bas de la carte entre les 2 mines aussi. \n"
				+ "2 routes et une muraille");
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		while (!moteurJeu.estFini())
		{
			synchronized (moteurJeu) {
				System.out.println("PasJeu Courant : " + moteurJeu.pasJeuCourant());
				//Gestion 1ere commande
				System.out.println("Entrer une commande pour le joueur 1. La commande doit etre de type ECommande/numVillageois/argument");
				String commande = s.nextLine();
				String [] param = commande.split("/");
				if(commande.equals("QUIT")){
					System.out.println("Fin de la partie");
					return;
				}
				while(param.length != 3){
					System.out.println("Commande Errone, Veuillez retaper la commande sous forme ECommande/numVillageois/argument");
					s = new Scanner(System.in);
					commande = s.nextLine();
					if(commande.equals("QUIT")){
						System.out.println("Fin de la partie");
						return;
					}
					param = commande.split("/");
				}

				//Gestion 2eme commande
				System.out.println("Entrer une commande pour le joueur 2. La commande doit etre de type ECommande/numVillageois/argument");
				String commande1 = s.nextLine();
				String [] param1 = commande.split("/");
				if(commande1.equals("QUIT")){
					System.out.println("Fin de la partie");
					return;
				}
				while(param1.length != 3){
					System.out.println("Commande Errone, Veuillez retaper la commande sous forme ECommande/numVillageois/argument");
					s1 = new Scanner(System.in);
					commande1 = s1.nextLine();
					if(commande1.equals("QUIT")){
						System.out.println("Fin de la partie");
						return;
					}
					param1 = commande.split("/");
				}

				ECommande c1;
				ECommande c2;
				Point oldPosition = new Point();
				Point oldPosition2 = new Point();
				switch(param[0]){
				case "DEPLACER":
					if(Integer.parseInt(param[1]) < 0 || Integer.parseInt(param[1]) > 1 ){
						System.out.println("J1 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c1 = ECommande.RIEN;
						break;
					}
					else if(Integer.parseInt(param[2]) < 0 || Integer.parseInt(param[2]) > 360){
						System.out.println("J1 : angle incorrecte. Doit etre compris entre 0 et 360. Commande remplace en ECommande.RIEN.");
						c1 = ECommande.RIEN;
						break;
					}
					else{
						c1 = ECommande.DEPLACER;
						oldPosition = moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param[1])));
					}
					break;

				case "ENTRERMINE":
					if(Integer.parseInt(param[1]) < 0 || Integer.parseInt(param[1]) > 1 ){
						System.out.println("J1 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c1 = ECommande.RIEN;
						break;
					}
					
					else if(Integer.parseInt(param[2]) < 0 || Integer.parseInt(param[2]) > 3){
						System.out.println("J1 : numero mine incorrecte. Doit etre compris entre 0 et 3. Commande remplace en ECommande.RIEN.");
						c1 = ECommande.RIEN;
						break;
					}
					
					else{
						if(!moteurJeu.peutEntrer(Integer.parseInt(param[1]), Integer.parseInt(param[2]))){
							System.out.println("J1 Malheuresement, le villageois n'est pas assez proche pour entrer dans la mine. Commande remplace en ECommande.RIEN");
							c1 = ECommande.RIEN;
						}
						else{
							c1 = ECommande.ENTRERMINE;
						}
					}
					
				case "ENTRERHOTELVILLE":
					if(Integer.parseInt(param[1]) < 0 || Integer.parseInt(param[1]) > 1 ){
						System.out.println("J1 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c1 = ECommande.RIEN;
						break;
					}
					
					else if(Integer.parseInt(param[2]) < 1 || Integer.parseInt(param[2]) > 2){
						System.out.println("J1 : numero hotelville incorrecte. Doit etre compris entre 1 et 2. Commande remplace en ECommande.RIEN.");
						c1 = ECommande.RIEN;
						break;
					}
					
					else{
						if(!moteurJeu.peutEntrer(Integer.parseInt(param[1]), Integer.parseInt(param[2]))){
							System.out.println("J1 Malheuresement, le villageois n'est pas assez proche pour entrer dans l'hotel de ville ou a les poches vides. Commande remplace en ECommande.RIEN");
							c1 = ECommande.RIEN;
							break;
						}
						else{
							c1 = ECommande.ENTRERHOTELVILLE;
							break;
						}
					}
					
					default:
						c1 = ECommande.RIEN;
						break;
				}
				
				switch(param1[0]){
				case "DEPLACER":
					if(Integer.parseInt(param1[1]) < 0 || Integer.parseInt(param1[1]) > 1 ){
						System.out.println("J2 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c2 = ECommande.RIEN;
						break;
					}
					else if(Integer.parseInt(param1[2]) < 0 || Integer.parseInt(param1[2]) > 360){
						System.out.println("J2 : angle incorrecte. Doit etre compris entre 0 et 360. Commande remplace en ECommande.RIEN.");
						c2 = ECommande.RIEN;
						break;
					}
					else{
						c2 = ECommande.DEPLACER;
						oldPosition2 = moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param[1])));
					}
					break;

				case "ENTRERMINE":
					if(Integer.parseInt(param1[1]) < 0 || Integer.parseInt(param1[1]) > 1 ){
						System.out.println("J2 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c2 = ECommande.RIEN;
						break;
					}
					
					else if(Integer.parseInt(param1[2]) < 0 || Integer.parseInt(param1[2]) > 3){
						System.out.println("J2 : numero mine incorrecte. Doit etre compris entre 0 et 3. Commande remplace en ECommande.RIEN.");
						c2 = ECommande.RIEN;
						break;
					}
					
					else{
						if(!moteurJeu.peutEntrer(Integer.parseInt(param1[1]), Integer.parseInt(param1[2]))){
							System.out.println("J2 Malheuresement, le villageois n'est pas assez proche pour entrer dans la mine. Commande remplace en ECommande.RIEN");
							c2 = ECommande.RIEN;
						}
						else{
							c2 = ECommande.ENTRERMINE;
						}
					}
					
				case "ENTRERHOTELVILLE":
					if(Integer.parseInt(param1[1]) < 0 || Integer.parseInt(param1[1]) > 1 ){
						System.out.println("J2 : numVillageois incorrecte. Commande remplacer en RIEN. Veuillez ecrire un numvillageois = a 0 ou 1");
						c2 = ECommande.RIEN;
						break;
					}
					
					else if(Integer.parseInt(param1[2]) < 1 || Integer.parseInt(param1[2]) > 2){
						System.out.println("J2 : numero hotelville incorrecte. Doit etre compris entre 1 et 2. Commande remplace en ECommande.RIEN.");
						c2 = ECommande.RIEN;
						break;
					}
					
					else{
						if(!moteurJeu.peutEntrer(Integer.parseInt(param1[1]), Integer.parseInt(param1[2]))){
							System.out.println("J2 Malheuresement, le villageois n'est pas assez proche pour entrer dans l'hotel de ville ou a les poches vides. Commande remplace en ECommande.RIEN");
							c2 = ECommande.RIEN;
							break;
						}
						else{
							c2 = ECommande.ENTRERHOTELVILLE;
							break;
						}
					}
					
					default:
						System.out.println("Commande inexistante. Remplace par rien");
						c2 = ECommande.RIEN;
						break;
				}
				
				System.out.println("Debut du pasJeu. Commande Joueur 1 : " + c1 + " Commande Joueur 2 : " + c2 + "");
				moteurJeu.pasJeu(c1, c2, Integer.parseInt(param[1]), Integer.parseInt(param1[1]), 
						Integer.parseInt(param[2]), Integer.parseInt(param1[2]));
				
				if(c1 == ECommande.DEPLACER){
					if(moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param[1]))).equals(oldPosition)){
						System.out.println("Deplacement rate. Panne, Bout du monde, Rocher tombe du ciel, Developpeur incompetent? Villageois " + Integer.parseInt(param[1]) 
								+ " reste au point : " + moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param[1]))));
					}
					else{
						System.out.println("Deplacement reussi (ou teleportation?). Villageois " + Integer.parseInt(param[1]) 
								+ " se deplace du point " + oldPosition + " au point " + moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param[1])))); 
					}
				}
				
				if(c1 == ECommande.ENTRERMINE){
					System.out.println("EntrerMine reussi ! Le villageois " + Integer.parseInt(param[1]) + " entre dans la mine "
							+ Integer.parseInt(param[2]) + ". Debut de la corvee\nFin de la corvee au pasJeu " + (moteurJeu.pasJeuCourant()-1+16) + ".");
		
				}
				
				if(c1 == ECommande.ENTRERHOTELVILLE){
					System.out.println("EntrerHotelVille reussi ! Le villageois " + Integer.parseInt(param[1]) + " entre dans l'hotel de ville "
							+ Integer.parseInt(param[2]) + ". Paiement d'impot en cours...\nL'hotel de ville a maintenant une quantite d'or egale a  " + (moteurJeu.HotelDeVille(Integer.parseInt(param[2])).orRestant()) + " or.");
				}
				
				if(c1 == ECommande.RIEN){
					System.out.println("J1 -> J2 HAHA");
				}
				
				if(c2 == ECommande.DEPLACER){
					if(moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param1[1]))).equals(oldPosition2)){
						System.out.println("Deplacement rate. Panne, Bout du monde, Rocher tombe du ciel, Developpeur incompetent? Villageois " + Integer.parseInt(param1[1]) 
								+ " reste au point : " + moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param1[1]))));
					}
					else{
						System.out.println("Deplacement reussi (ou teleportation?). Villageois " + Integer.parseInt(param1[1]) 
								+ " se deplace du point " + oldPosition2 + " au point " + moteurJeu.positionsVillageois().get(moteurJeu.getVillageois(Integer.parseInt(param1[1])))); 
					}
				}
				
				if(c2 == ECommande.ENTRERMINE){
					System.out.println("EntrerMine reussi ! Le villageois " + Integer.parseInt(param1[1]) + " entre dans la mine "
							+ Integer.parseInt(param1[2]) + ". Debut de la corvee\nFin de la corvee au pasJeu " + (moteurJeu.pasJeuCourant()-1+16) + ".");
		
				}
				
				if(c2 == ECommande.ENTRERHOTELVILLE){
					System.out.println("EntrerHotelVille reussi ! Le villageois " + Integer.parseInt(param1[1]) + " entre dans l'hotel de ville "
							+ Integer.parseInt(param1[2]) + ". Paiement d'impot en cours...\nL'hotel de ville a maintenant une quantite d'or egale a  " + (moteurJeu.HotelDeVille(Integer.parseInt(param1[2])).orRestant()) + " or.");
				}
				
				if(c2 == ECommande.RIEN){
					System.out.println("J2 -> J1 HAHA");
				}
				
				if(c1 == ECommande.RIEN && c2 == ECommande.RIEN){
					System.out.println("MJ -> J2 & J1 HAHA");
				}
				
				System.out.println("Fin du pasjeu");
			}

			//		moteurJeu.showMap();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(moteurJeu.resultatFinal());
	}





}
