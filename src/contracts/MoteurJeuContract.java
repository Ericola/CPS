package contracts;

import implementations.HotelVilleImplem;
import implementations.MineImplem;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;
import decorators.MoteurJeuDecorator;
import enums.ECommande;
import enums.ERace;
import enums.EResultat;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class MoteurJeuContract extends MoteurJeuDecorator{

	public MoteurJeuContract(IMoteurJeuService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	private void checkInvariants() {

		/* 0 <= PasJeuCourant(M) <= MaxPasJeu(M) */
		if(!(0<=pasJeuCourant()&&pasJeuCourant()<= MaxPasJeu())){
			throw new InvariantError("0 <= PasJeuCourant(M) <= MaxPasJeu(M) incorrecte");
		}

		/*estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M,1)) >= 1664 || HotelVille::orRestant(hotelDeVille(M,2)) >= 1664|| pasJeuCourant(M) = maxPasJeu(M))*/
		if(!((estFini() == (HotelDeVille(1).orRestant() >= 1664 && HotelDeVille(2).orRestant() >= 1664)||  pasJeuCourant() == MaxPasJeu()))){
			throw new InvariantError("estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M,1)) >= 1664 && HotelVille::orRestant(hotelDeVille(M,2)) >= 1664|| pasJeuCourant(M) = maxPasJeu(M)) incorrect");
		}

		/* 	resultatFinal(M) = EResultat.HUMAINGAGNE =(min) (HotelVille:orRestant(hotelDeville(M, 1)) >= 1664 && HotelVille::appartenance(M,1) = ERace.HUMAIN)||(HotelVille:orRestant(hotelDeville(M, 2)) >= 1664 && HotelVille::appartenance(M,2)) = ERace.HUMAIN) ||(HotelVille:appartenance(M,1) = ERace.HUMAIN && HotelVille::appartenance(M,2) = ERace.HUMAIN) */
		if(!(resultatFinal() == EResultat.HUMAINGAGNE == ((HotelDeVille(1).orRestant() >= 1664 && HotelDeVille(1).appartenance() == ERace.HUMAIN)||(HotelDeVille(2).orRestant() >= 1664 && HotelDeVille(2).appartenance() == ERace.HUMAIN) ||(HotelDeVille(1).appartenance() == ERace.HUMAIN && HotelDeVille(2).appartenance() == ERace.HUMAIN) ))){
			throw new InvariantError("resultatFinal(M) = EResultat.HUMAINGAGNE =(min) (HotelVille:orRestant(hotelDeville(M, 1)) >= 1664 && HotelVille::appartenance(M,1) = ERace.HUMAIN)||(HotelVille:orRestant(hotelDeville(M, 2)) >= 1664 && HotelVille::appartenance(M,2)) = ERace.HUMAIN) ||(HotelVille:appartenance(M,1) = ERace.HUMAIN && HotelVille::appartenance(M,2) = ERace.HUMAIN incorrecte");
		}

		/* 	resultatFinal(M) = EResultat.ORCGAGNE =(min) (HotelVille:orRestant(hotelDeville(M, 1)) >= 1664 && HotelVille::appartenance(M,1) = ERace.ORC)||(HotelVille:orRestant(hotelDeville(M, 2)) >= 1664 && HotelVille::appartenance(M,2)) = ERace.ORC) ||(HotelVille:appartenance(M,1) = ERace.ORC && HotelVille::appartenance(M,2) = ERace.ORC) */
		if(!(resultatFinal() == EResultat.ORCGAGNE == ((HotelDeVille(1).orRestant() >= 1664 && HotelDeVille(1).appartenance() == ERace.ORC)||(HotelDeVille(2).orRestant() >= 1664 && HotelDeVille(2).appartenance() == ERace.ORC) ||(HotelDeVille(1).appartenance() == ERace.ORC && HotelDeVille(2).appartenance() == ERace.ORC)))){
			throw new InvariantError("resultatFinal(M) = EResultat.ORCGAGNE =(min) (HotelVille:orRestant(hotelDeville(M, 1)) >= 1664 && HotelVille::appartenance(M,1) = ERace.ORC)||(HotelVille:orRestant(hotelDeville(M, 2)) >= 1664 && HotelVille::appartenance(M,2)) = ERace.ORC) ||(HotelVille:appartenance(M,1) = ERace.ORC && HotelVille::appartenance(M,2) = ERace.ORC) incorrecte");
		}

		/* resultatFinal(M) = EResultat.DRAW =(min) pasJeuCourant(M)=MaxPasJeu(M) */
		if(!(resultatFinal() == EResultat.DRAW == (pasJeuCourant() ==  MaxPasJeu()))){
			throw new InvariantError("resultatFinal(M) = EResultat.DRAW =(min) pasJeuCourant(M)=MaxPasJeu(M) incorrecte");
		}

		/* peutEntrer(M, numVillageois, numMine) =(min) soit r = Rectangle(positionMineX(M, getMine(M, numMine)), positionMineY(M, getMine(M, numMine)), Mine::getLargeur(getMine(M, numMine)), Mine::getHauteur(getMine(M, numMine))),
		 * r1 = Rectangle(positionVillageoisX(M, getVillageois(M, numVillageois)) + Villageois::getLargeur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getLargeur(getVillageois(M, numVillageois))/2, positionVillageoisY(M, getVillageois(M, numVillageois)) + Villageois::getHauteur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getHauteur(getVillageois(M, numVillageois))/2, Villageois::getLargeur(getVillageois(M, numVillageois))/2 + 51, Villageois::getHauteur(getVillageois(M, numVillageois))/2 + 51),
		 * r.intersects(r1)
		&& (!Mine::estLaminee(getMine(M, numMine))) && (Mine::appartenance(getMine(M, numMine)) == ERace.RIEN || Mine::appartenance(getMine(M, numMine)) == Villageois::getRace(getVillageois(M, numVillageois))
		&& soit nbVillageoisDansMine = nbr(i in [0, MineMinee(M).size()[ tel que MineMinee(M).get(i) = numMine, Mine::orRestant(getMine(M, numMine)) - nbVillageoisDansMine > 0*/
		for(int i = 0; i < numerosVillageois().size(); i++){
			Rectangle r1 = new Rectangle(positionVillageoisX(getVillageois(i)) + getVillageois(i).getLargeur()/2 - 23 - getVillageois(i).getLargeur()/2, positionVillageoisY(getVillageois(i)) + getVillageois(i).getHauteur()/2 - 23 - getVillageois(i).getHauteur()/2, 51 + getVillageois(i).getHauteur()/2, 51 + getVillageois(i).getLargeur()/2);
			for(int j = 0; j < numerosMine().size(); j++){
				Rectangle r = new Rectangle(positionMineX(getMine(j)), positionMineY(getMine(j)), getMine(j).getLargeur(), getMine(j).getHauteur());
				if(!(peutEntrer(i, j) == (r.intersects(r1) && (getMine(j).appartenance() == ERace.RIEN || getMine(j).appartenance() == getVillageois(i).getRace())))){
					throw new InvariantError("peutEntrer(M, numVillageois, numMine) =(min) distance(numVillageois, numMine) < 51" +  
							"&& (!Mine::estLaminee(getMine(M, numMine))) && (Mine::appartenance(getMine(M, numMine)) == ERace.RIEN || Mine::appartenance(getMine(M, numMine)) == Villageois::getRace(getVillageois(M, numVillageois))"
							+ "&& soit nbVillageoisDansMine = nbr(i in [0, MineMinee(M).size()[ tel que MineMinee(M).get(i) = numMine, Mine::orRestant(getMine(M, numMine)) - nbVillageoisDansMine > 0 incorrecte");	
				}
				else{
					int nbVillageoisDansMine = 0;
					for(int v = 0; v < MineMinee().size(); v++){
						if(MineMinee().get(v) == j){
							nbVillageoisDansMine++;
						}
					}
					if(!(peutEntrer(i,j) == (getMine(j).orRestant() - nbVillageoisDansMine > 0))){
						throw new InvariantError("peutEntrer(M, numVillageois, numMine) =(min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionMineX(M, numMine), positionMineY(M, numMine)) <= 51" +  
								"&& (!Mine::estLaminee(getMine(M, numMine))) && (Mine::appartenance(getMine(M, numMine)) == ERace.RIEN || Mine::appartenance(getMine(M, numMine)) == Villageois::getRace(getVillageois(M, numVillageois))"
								+ "&& soit nbVillageoisDansMine = nbr(i in [0, MineMinee(M).size()[ tel que MineMinee(M).get(i) = numMine, Mine::orRestant(getMine(M, numMine)) - nbVillageoisDansMine > 0 incorrecte");		
					}
				}
			}	
		}

		/* peutEntrerHotelVille(M, numVillageois, numHotelVille) = (min) soit r = Rectangle(positionHotelVilleX(M, HotelDeVille(M, numHotelVille)), positionHotelVilleY(M, HotelDeVille(M, numHotelVille)), HotelVille::getLargeur(HotelDeVille(M, numHotelVille)), HotelVille::getHauteur(HotelDeVille(M, numHotelVille))),
		 * r1 = Rectangle(positionVillageoisX(M, getVillageois(M, numVillageois)) + Villageois::getLargeur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getLargeur(getVillageois(M, numVillageois))/2, positionVillageoisY(M, getVillageois(M, numVillageois)) + Villageois::getHauteur(getVillageois(M, numVillageois))/2 - 23 - Villageois::getHauteur(getVillageois(M, numVillageois))/2, Villageois::getLargeur(getVillageois(M, numVillageois))/2 + 51, Villageois::getHauteur(getVillageois(M, numVillageois)) /2),
		 * r.intersects(r1)
		&& (Villageois::getRace(getVillageois(numVillageois)) == HotelVille::appartenance(HotelDeVille(numHotelVille)) || HotelVille::appartenance(HotelDeVille(M, numHotelVille)) == ERace.RIEN) */
		Rectangle r = new Rectangle(positionHotelVilleX(1), positionHotelVilleY(1), HotelDeVille(1).getLargeur(), HotelDeVille(1).getHauteur());
		Rectangle r2 = new Rectangle(positionHotelVilleX(2), positionHotelVilleY(2), HotelDeVille(2).getLargeur(), HotelDeVille(2).getHauteur());
		for(int i = 0; i < numerosVillageois().size(); i++){
			Rectangle r1 = new Rectangle(positionVillageoisX(getVillageois(i)) + getVillageois(i).getLargeur()/2 - 23 - getVillageois(i).getLargeur()/2, positionVillageoisY(getVillageois(i)) + getVillageois(i).getHauteur()/2 - 23 - getVillageois(i).getHauteur()/2, 51 + getVillageois(i).getHauteur()/2, 51 + getVillageois(i).getLargeur()/2);
			if(!(peutEntrerHotelVille(i, 1) && r.intersects(r1)
					&& (getVillageois(i).getRace() == HotelDeVille(1).appartenance() || HotelDeVille(1).appartenance() == ERace.RIEN))){
				throw new InvariantError("peutEntrerHotelVille(M, numVillageois, numHotelVille) = (min) distance(numVillageois, HotelVille(1)) <= 51"
						+"&& (Villageois::getRace(getVillageois(numVillageois)) == HotelVille::appartenance(HotelDeVille(numHotelVille)) || HotelVille::appartenance(HotelDeVille(M, numHotelVille)) == ERace.RIEN) incorrecte");	
			}	

			if(!(peutEntrerHotelVille(i, 2) && r2.intersects(r1)
					&& (getVillageois(i).getRace() == HotelDeVille(2).appartenance() || HotelDeVille(2).appartenance() == ERace.RIEN))){
				throw new InvariantError("peutEntrerHotelVille(M, numVillageois, numHotelVille) = (min) distance(numVillageois, HotelVille(2)) <= 51"
						+"&& (Villageois::getRace(getVillageois(numVillageois)) == HotelVille::appartenance(HotelDeVille(numHotelVille)) || HotelVille::appartenance(HotelDeVille(M, numHotelVille)) == ERace.RIEN) incorrecte");	
			}	
		}

		//estSurRoute(M, numVillageois) = (min) Rectangle(positionRouteX(R, n), positionRouteY(R, n), Route::getLargeur(R), Route::getHauteur(R)).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un n in [0, numeroRoutes(M).size() - 1]
		for(int i = 0; i < numerosVillageois().size(); i++){
			boolean test = false;
			for(int numRoute = 0; numRoute < numerosRoute().size(); numRoute++){
				Rectangle rect = new Rectangle(positionRouteX(getRoute(numRoute)), positionRouteY(getRoute(numRoute)),
						getRoute(numRoute).getLargeur(), getRoute(numRoute).getHauteur());
				if(rect.contains(positionsVillageois().get(getVillageois(i)))){
					test = true;
					break;
				}
			}
			if(!(estSurRoute(i) && test)){
				throw new InvariantError("estSurRoute(M, numVillageois) = (min) Rectangle(positionRouteX(R, n), positionRouteY(R, n), Route::getLargeur(R), Route::getHauteur(R)).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un n in [0, numeroRoutes(M).size() - 1] incorrecte");
			}
		}

		//    estSurMuraille(M, Point p) = (min) Rectangle(positionMurailleX(M, m), positionMurailleY(M, m), Muraille::getLargeur(getMuraille(M, m)), Muraille::getHauteur((getMuraille(M, m))).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un m in [0, numeroMuraille(M).size() - 1]
		for(int i = 0; i < numerosVillageois().size(); i++){
			boolean test = false;
			for(int numMuraille = 0; numMuraille < numerosMuraille().size(); numMuraille++){
				//Ajouter invariants
				Rectangle rect = new Rectangle(positionMurailleX(getMuraille(numMuraille)), positionMurailleY(getMuraille(numMuraille)),
						getMuraille(numMuraille).getLargeur(), 	getMuraille(numMuraille).getHauteur());
				if(rect.contains(positionsVillageois().get(getVillageois(i)))){
					test = true;
					break;
				}
			}
			if(!(estSurMuraille(positionsVillageois().get(getVillageois(i))) && test)){
				throw new InvariantError("estSurMuraille(M, Point p) = (min) Rectangle(positionMurailleX(M, m), positionMurailleY(M, m), Muraille::getLargeur(getMuraille(M, m)), Muraille::getHauteur((getMuraille(M, m))).contains(Point(PositionVillageoisX(M, numVillageois), PositionVillageoisY(M, numVillageois))) pour au moins un m in [0, numeroMuraille(M).size() - 1] incorrecte");
			}
		}

		/*  Soit Rectangle r = Rectangle(0,0, l, h)
			Pour chaque Villageois V in numerosVillageois(M)
		   		Soit px = positionsVillageois(M).get(V).x
		   		Soit py = positionsVillageois(M).get(V).y
		   		r.contains(Point(px,py)) = true
		   		r.contains(Point(px + 10,py)) = true
		   		r.contains(Point(px + 10,py + 10)) = true
		   		r.contains(Point(px,py + 10)) = true  */
		Rectangle rect = new Rectangle(0, 0, LargeurTerrain(), HauteurTerrain());
		for(int i = 0; i < numerosVillageois().size(); i++){
			int px = positionsVillageois().get(getVillageois(i)).x;
			int py = positionsVillageois().get(getVillageois(i)).y;
			if(!(rect.contains(new Point(px, py)) && rect.contains(new Point(px + 10, py))) && rect.contains(new Point(px + 10, py + 10)) && rect.contains(new Point(px, py + 10))){
				throw new InvariantError("Tout les villageois sont sur le terrain incorrecte");
			}
		}
	}

	public int LargeurTerrain() {
		// aucun pre 

		// run
		return super.LargeurTerrain();
	}

	@Override
	public int HauteurTerrain() {
		// aucun pre 

		// run
		return super.HauteurTerrain();
	}

	@Override
	public int MaxPasJeu() {
		// aucun pre 

		// run
		return super.MaxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		// aucun pre 

		// run
		return super.pasJeuCourant();
	}



	@Override
	public List<IVillageoisService> numerosVillageois() {
		// aucun pre

		// run
		return super.numerosVillageois();

	}

	@Override
	public IVillageoisService getVillageois(int n) {
		//pre getVillageois(M, n) require n in [0, numerosVillageois(M).size()]
		if(!(n <= 0 && n < numerosVillageois().size())){
			throw new PreconditionError("getVillageois(M, n) require n in [0, numerosVillageois(M).size()] incorrecte");
		}
		return super.getVillageois(n);
	}

	@Override
	public int positionVillageoisX(IVillageoisService v) {
		//pre positionVillageoisX(M, v) require v in numerosVillageois(M)
		if(!(numerosVillageois().contains(v))){
			throw new PreconditionError("positionVillageoisX(M, v) require v in numerosVillageois(M) incorrecte");
		}
		return super.positionVillageoisX(v);
	}

	@Override
	public int positionVillageoisY(IVillageoisService v) {
		//pre positionVillageoisY(M, v) require v in numerosVillageois(M)
		if(!(numerosVillageois().contains(v))){
			throw new PreconditionError("positionVillageoisY(M, v) require v in numerosVillageois(M) incorrecte");
		}
		return super.positionVillageoisY(v);
	}

	@Override
	public List<IMineService> numerosMine() {
		// aucun pre

		// run
		return super.numerosMine();
	}

	@Override
	public IMineService getMine(int n) {
		//pre getMine(M, n) require n in [0, numerosMine(M).size()[
		if(!(n <= 0 && n < numerosMine().size())){
			throw new PreconditionError("getMine(M, n) require n in [0, numerosMine(M).size()[ incorrecte");
		}
		return super.getMine(n);
	}

	@Override
	public int positionMineX(IMineService m) {
		//pre positionMineX(M, m) require m in numerosMine(M)
		if(!(numerosMine().contains(m))){
			throw new PreconditionError("positionMineX(M, m) require m in numerosMine(M) incorrecte");
		}
		return super.positionMineX(m);
	}

	@Override
	public int positionMineY(IMineService m) {
		//pre positionMineY(M, m) require m in numerosMine(M)
		if(!(numerosMine().contains(m))){
			throw new PreconditionError("positionMineY(M, m) require m in numerosMine(M) incorrecte");
		}
		return super.positionMineY(m);
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		/*pre peutEntrer(M, numVillageois, numMine) require numVillageois in [0, numerosVillageois(M).size()[*/
		if(!(numVillageois <= 0 && numVillageois < numerosVillageois().size())){
			throw new PreconditionError("peutEntrer(M, numVillageois, numMine) require numVillageois in [0, numerosVillageois(M).size()[ incorrecte");
		}

		/*pre peutEntrer(M, numVillageois, numMine) require numMine in [0, numerosMine(M).size()[*/
		if(!(numMine <= 0 && numMine < numerosMine().size())){
			throw new PreconditionError("peutEntrer(M, numVillageois, numMine) require numVillageois in [0, numerosVillageois(M).size()[ incorrecte");
		}
		return super.peutEntrer(numVillageois, numMine);
	}

	@Override
	public IHotelVilleService HotelDeVille(int n) {
		// pre HotelVille(M, n) require  n in [1,2]
		if(!(n== 1 || n == 2)){
			throw new PreconditionError("HotelVille(M, n) require  n in [1,2] incorrecte");
		}
		return super.HotelDeVille(n);
	}

	@Override
	public int positionHotelVilleX(int n) {
		// pre positionHotelVilleX(M, n) require  n in [1,2]
		if(!(n== 1 || n == 2)){
			throw new PreconditionError("positionHotelVilleX(M, n) require n in [1,2] incorrecte");
		}
		return super.positionHotelVilleX(n);
	}

	@Override
	public int positionHotelVilleY(int n) {
		// pre positionHotelVilleY(M, n) require  n in [1,2]
		if(!(n== 1 || n == 2)){
			throw new PreconditionError("positionHotelVilleY(M, n) require n in [1,2] incorrecte");
		}
		return super.positionHotelVilleY(n);
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int numHotelDeVille) {
		// pre peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require 
		//numVillageois in [0, numerosVillageois(M).size()[ 
		if(!(numVillageois <= 0 && numVillageois < numerosVillageois().size())){
			throw new PreconditionError("peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require numVillageois in [0, numerosVillageois(M).size()[ incorrecte");
		}

		/* pre peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require 
		     numHotelDeVille in [1,2]*/
		if(!(numHotelDeVille== 1 || numHotelDeVille == 2)){
			throw new PreconditionError("peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require numHotelDeVille in [1,2] incorrecte");
		}

		/* pre peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require 
		       Villageois::getQtor(getVillageois(M, numVillageois)) > 0 */
		if(!(getVillageois(numVillageois).getQtor() > 0)){
			throw new PreconditionError("peutEntrerHotelVille(M, numVillageois, numHotelDeVille) require numHotelDeVille in [1,2] incorrecte");
		}

		return super.peutEntrerHotelVille(numVillageois, numHotelDeVille);
	}

	@Override
	public List<IRouteService> numerosRoute() {
		// aucun pre

		// run
		return super.numerosRoute();
	}

	@Override
	public IRouteService getRoute(int r) {
		//pre getRoute(M, r) require  r in [0, numerosRoute(M).size()[
		if(!(0 <= r && r < numerosRoute().size())){
			throw new PreconditionError("getRoute(M, r) require  r in [0, numerosRoute(M).size()[ incorrecte");
		}
		return super.getRoute(r);
	}

	@Override
	public int positionRouteX(IRouteService r) {
		//pre positionRouteX(M, r) require r in numerosRoute(M)
		if(!(numerosRoute().contains(r))){
			throw new PreconditionError("positionRouteX(M, r) require r in numerosRoute(M) incorrecte");
		}
		return super.positionRouteX(r);
	}

	@Override
	public int positionRouteY(IRouteService r) {
		//pre positionRouteY(M, r) require r in numerosRoute(M)
		if(!(numerosRoute().contains(r))){
			throw new PreconditionError("positionRouteY(M, r) require r in numerosRoute(M) incorrecte");
		}
		return super.positionRouteY(r);
	}

	@Override
	public boolean estSurRoute(int numVillageois) {
		//pre estSurRoute(M, numVillageois) require numVillageois in [0, numerosVillageois.size()[ 
		if(!(numVillageois <= 0 && numVillageois < numerosVillageois().size())){
			throw new PreconditionError("estSurRoute(M, numVillageois) require numVillageois in [0, numerosVillageois(M).size()[ incorrecte");
		}
		return super.estSurRoute(numVillageois);
	}


	@Override
	public List<IMurailleService> numerosMuraille() {
		// aucun pre

		// run
		return super.numerosMuraille();
	}

	@Override
	public IMurailleService getMuraille(int n) {
		//pre getMuraille(M, n) require  n in [0, numerosMuraille(M).size()[
		if(!(0 <= n && n < numerosMuraille().size())){
			throw new PreconditionError("getMuraille(M, n) require  n in [0, numerosMuraille(M).size()[ incorrecte");
		}
		return super.getMuraille(n);
	}

	@Override
	public int positionMurailleX(IMurailleService m) {
		//pre positionMurailleX(M, m) require m in numerosMuraille(M)
		if(!(numerosMuraille().contains(m))){
			throw new PreconditionError("positionMurailleX(M, m) require m in numerosMuraille(M) incorrecte");
		}
		return  super.positionMurailleX(m);
	}

	@Override
	public int positionMurailleY(IMurailleService m) {
		//pre positionMurailleY(M, m) require m in numerosMuraille(M)
		if(!(numerosMuraille().contains(m))){
			throw new PreconditionError("positionMurailleY(M, m) require m in numerosMuraille(M) incorrecte");
		}
		return super.positionMurailleY(m);
	}

	public boolean estSurMuraille(Point p) {
		// aucun pre

		// run
		return super.estSurMuraille(p);
	}

	@Override
	public boolean estFini() {
		// aucun pre

		// run
		return super.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		// aucun pre

		// run
		return super.resultatFinal();
	}

	@Override
	public List<Integer> VillageoisAttente() {
		// aucun pre

		// run
		return super.VillageoisAttente();
	}

	@Override
	public List<Integer> MineMinee() {
		// aucun pre

		// run
		return super.MineMinee();
	}

	@Override
	public HashMap<IVillageoisService, Point> positionsVillageois() {
		// aucun pre

		// run
		return super.positionsVillageois();
	}

	@Override
	public HashMap<Object, Point> positions() {
		// aucun pre

		// run
		return super.positions();
	}

	@Override
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		//pre init(MaxPasJeu) require maxPasJeu >= 0 
		if(!(maxPasJeu >= 0)){
			throw new PreconditionError("init(MaxPasJeu) require maxPasJeu >= 0  incorrecte");
		}

		//pre init(MaxPasJeu) require l>=600
		if(!(l >= 600)){
			throw new PreconditionError("init(MaxPasJeu) require l>=600 incorrecte");
		}

		//pre init(MaxPasJeu) require h>=400
		if(!(h >= 400)){
			throw new PreconditionError("init(MaxPasJeu) require h>=400 incorrecte");
		}

		//inv avant
		checkInvariants();

		//run
		super.init(maxPasJeu, l, h);

		//inv apres
		checkInvariants();

		//post MaxPasJeu(init(p,l,h)) = p
		if(!(MaxPasJeu() == maxPasJeu)){
			throw new PreconditionError("MaxPasJeu(init(p,l,h)) = p incorrecte");
		}

		//post pasJeuCourant(init(p,l,h)) = 0
		if(!(pasJeuCourant() == 0)){
			throw new PreconditionError("pasJeuCourant(init(p,l,h)) = 0 incorrecte");
		}
		//post LargeurTerrain(init(p,l,h)) = l
		if(!(LargeurTerrain() == l)){
			throw new PreconditionError("LargeurTerrain(init(p,l,h)) = l incorrecte");
		}
		//post HauteurTerrain(init(p,l,h)) = h
		if(!(HauteurTerrain() == h)){
			throw new PreconditionError("HauteurTerrain(init(p,l,h)) = h incorrecte");
		}

		return this;
	}

	public IMoteurJeuService pasJeu(ECommande Commande, ECommande Commande2, int numVillageois, int numVillageois2, int argument, int argument2) {
		//pre pasJeu(M ,c, c2, n, n2, a, a2) require not estFini(M) 
		if(!(estFini()==true) ){
			throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require not estFini(M) incorrecte");
		}
		//pre pasJeu(M ,c, c2, n, n2, a, a2) require numVillageois in [0, numerosVillageois.size[ ^ Villageois::getRace(getVillageois(M, numVillageois)) == ERace.HUMAIN ^ VillageoisAttente(M, numVillageois) = -1
		if(!(numVillageois >= 0 && numVillageois < numerosVillageois().size() && getVillageois(numVillageois).getRace() == ERace.HUMAIN && VillageoisAttente().get(numVillageois) == -1)){
			throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require numVillageois in [0, numerosVillageois.size[ ^ Villageois::getRace(getVillageois(M, numVillageois)) == ERace.HUMAIN ^ VillageoisAttente(M, numVillageois) = -1 incorrecte");
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require n2 in [0, numerosVillageois.size[ ^ Villageois::getRace(getVillageois(M, n)) == ORC ^ VillageoisAttente(M, n2) = -1
		if(!(numVillageois2 >= 0 && numVillageois2 < numerosVillageois().size() && getVillageois(numVillageois2).getRace() == ERace.ORC && VillageoisAttente().get(numVillageois2) == -1)){
			throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require n2 in [0, numerosVillageois.size[ ^ Villageois::getRace(getVillageois(M, n)) == ORC ^ VillageoisAttente(M, n2) = -1 incorrecte");
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require c = DEPLACER => 0 <= a <= 360 
		if(Commande == ECommande.DEPLACER){
			if(!(argument >= 0 && argument <= 360)){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require c = DEPLACER => 0 <= a <= 360 incorrecte");
			}
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require c = ENTRERMINE => a in [0, numerosMine.size[ ^ peutEntrer(M, n, a)
		if(Commande == ECommande.ENTRERMINE){
			if(!(argument >= 0 && argument <= numerosMine().size() && peutEntrer(numVillageois, argument))){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require c = ENTRERMINE => a in [0, numerosMine.size[ ^ peutEntrer(M, n, a) incorrecte");
			}
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require Commande = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n) && a = 1 || a = 2
		if(Commande == ECommande.ENTRERHOTELVILLE){
			if(!((argument == 1 || argument == 2) && peutEntrerHotelVille(numVillageois, argument))){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require Commande = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n) && a = 1 || a = 2 incorrecte");
			}
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require c2 = DEPLACER => 0 <= a2 <= 360 
		if(Commande2 == ECommande.DEPLACER){
			if(!(argument2 >= 0 && argument2 <= 360)){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require c2 = DEPLACER => 0 <= a2 <= 360 incorrecte");
			}
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require c = ENTRERMINE => a in [0, numerosMine.size[ ^ peutEntrer(M, n, a)
		if(Commande2 == ECommande.ENTRERMINE){
			if(!(argument2 >= 0 && argument2 <= numerosMine().size() && peutEntrer(numVillageois2, argument2))){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require c2 = ENTRERMINE => a2 in [0, numerosMine.size[ ^ peutEntrer(M, n2, a2) incorrecte");
			}
		}

		//pre pasJeu(M ,c, c2, n, n2, a, a2) require Commande2 = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n2) && a2 = 1 || a2 = 2
		if(Commande2 == ECommande.ENTRERHOTELVILLE){
			if(!((argument2 == 1 || argument2 == 2) && peutEntrerHotelVille(numVillageois2, argument2))){
				throw new PreconditionError("pasJeu(M ,c, c2, n, n2, a, a2) require Commande2 = ENTRERHOTELVILLE => peutEntrerHotelVille(M, n2) && a2 = 1 || a2 = 2 incorrecte");
			}
		}

		//capture
		int oldQtor = getVillageois(numVillageois).getQtor();
		int oldPdv = getVillageois(numVillageois).getPdv();
		int oldQtor2 = getVillageois(numVillageois2).getQtor();
		int oldPdv2 = getVillageois(numVillageois2).getPdv();
		ArrayList<Integer> oldQtorListVillageois = new ArrayList<>();
		ArrayList<Integer> oldOrRestantMine = new ArrayList<>();
		ArrayList<Integer> oldOrRestantHotelVille = new ArrayList<>();
		ArrayList<Integer> oldAbandonCompteurMine = new ArrayList<>();
		ArrayList<Integer> oldAbandonCompteurHotelVille = new ArrayList<>();
		ArrayList<Integer> oldPdvVillageois = new ArrayList<>();
		ArrayList<Integer> oldPdvMuraille = new ArrayList<>();
		ArrayList<ERace> oldMineRace = new ArrayList<>();
		ArrayList<ERace> oldHotelVilleRace = new ArrayList<>();
		int oldOrRestantH = 0;
		int oldOrRestantH2 = 0;
		int oldPasJeuCourant = pasJeuCourant();
		HashMap<IVillageoisService, Point> oldPositionsVillageois = new HashMap<>();
		ArrayList<Integer> oldVillageoisDansMineI = new ArrayList<>();
		ArrayList<Integer> oldVillageoisAttente = new ArrayList<>();
		oldVillageoisAttente.addAll(VillageoisAttente());
		ArrayList<Integer> oldMineMinee = new ArrayList<>();
		oldMineMinee.addAll(MineMinee());
		IHotelVilleService oldHv = null;
		IHotelVilleService oldHv2 = null;
		IMineService oldM = null;
		IMineService oldM2 = null;
		
		for(int i = 0; i < numerosVillageois().size(); i++){
			oldQtorListVillageois.add(getVillageois(i).getQtor());
			oldPositionsVillageois.put(getVillageois(i), positionsVillageois().get(getVillageois(i)));
			oldPdvVillageois.add(getVillageois(i).getPdv());
		}
		
		for(int i = 0; i < numerosMine().size(); i++){
			oldOrRestantMine.add(getMine(i).orRestant());
			oldAbandonCompteurMine.add(getMine(i).abandonCompteur());
			oldMineRace.add(getMine(i).appartenance());
			
			int nbVillageoisDansMine = 0;
			oldVillageoisDansMineI.add(i, 0);
			for(int j = 0; j < MineMinee().size(); j++){
				if(oldPasJeuCourant == VillageoisAttente().get(j)){
					oldVillageoisDansMineI.add(MineMinee().get(j), oldVillageoisDansMineI.get(MineMinee().get(j)) + 1);
				}
			}
		}
		
		for(int i = 1; i < 3; i++){
			oldAbandonCompteurHotelVille.add(HotelDeVille(i).abandonCompteur());
			oldHotelVilleRace.add(HotelDeVille(i).appartenance());
			oldOrRestantHotelVille.add(HotelDeVille(i).orRestant());
		}
		
		for(int i = 0; i < numerosMuraille().size(); i++){
			oldPdvMuraille.add(getMuraille(i).getPdv());
		}

		if(Commande == ECommande.ENTRERHOTELVILLE){
			oldHv = new HotelVilleImplem();
			oldHv.init(HotelDeVille(argument).getLargeur(), HotelDeVille(argument).getHauteur(), HotelDeVille(argument).appartenance());
			oldHv.setAbandonCompteur(HotelDeVille(argument).abandonCompteur());
			oldHv.setOrRestant(HotelDeVille(argument).orRestant());
			oldOrRestantH = HotelDeVille(argument).orRestant();
		}

		if(Commande2 == ECommande.ENTRERHOTELVILLE){
			oldHv2 = new HotelVilleImplem();
			oldHv2.init(HotelDeVille(argument2).getLargeur(), HotelDeVille(argument2).getHauteur(), HotelDeVille(argument2).appartenance());
			oldHv2.setAbandonCompteur(HotelDeVille(argument2).abandonCompteur());
			oldHv2.setOrRestant(HotelDeVille(argument2).orRestant());
			oldOrRestantH2 = HotelDeVille(argument2).orRestant();
		}

		if(Commande == ECommande.ENTRERMINE){
			oldM = new MineImplem();
			oldM.init(getMine(argument).getLargeur(), getMine(argument).getHauteur());
			oldM.setAbandonCompteur(getMine(argument).abandonCompteur());
			oldM.setOrRestant(getMine(argument).orRestant());
			oldM.setAppartenance(getMine(argument).appartenance());
		}
		if(Commande2 == ECommande.ENTRERMINE){
			oldM2 = new MineImplem();
			oldM2.init(getMine(argument2).getLargeur(), getMine(argument2).getHauteur());
			oldM2.setAbandonCompteur(getMine(argument2).abandonCompteur());
			oldM2.setOrRestant(getMine(argument2).orRestant());
			oldM2.setAppartenance(getMine(argument2).appartenance());
		}
		//inv avant
		checkInvariants();

		super.pasJeu(Commande, Commande2, numVillageois, numVillageois2, argument, argument2);

		//inv apres
		checkInvariants();

		if(Commande == ECommande.DEPLACER){
			checkDeplacer(numVillageois, argument, oldQtor, oldPdv);
		}

		if(Commande2 == ECommande.DEPLACER){
			checkDeplacer(numVillageois2, argument2, oldQtor2, oldPdv2);
		}

		if(Commande == ECommande.ENTRERMINE){
			checkEntrerMine(numVillageois, argument, oldPasJeuCourant, oldM);
		}

		if(Commande2 == ECommande.ENTRERMINE){
			checkEntrerMine(numVillageois2, argument2, oldPasJeuCourant, oldM2);
		}

		if(Commande == ECommande.ENTRERHOTELVILLE){
			checkEntrerHotelVille(numVillageois, argument, oldQtor, oldOrRestantH, oldHv);
		}

		if(Commande2 == ECommande.ENTRERHOTELVILLE){
			checkEntrerHotelVille(numVillageois2, argument2, oldQtor2, oldOrRestantH, oldHv2);
		}

		//post PasJeuCourant(pasJeu(pasJeu(M ,c, c2, n, n2, a, a2))) = PasJeuCourant(M) + 1
		if(!(pasJeuCourant() == oldPasJeuCourant + 1)){
			throw new PostconditionError("PasJeuCourant(pasJeu(pasJeu(M ,c, c2, n, n2, a, a2))) = PasJeuCourant(M) + 1 incorrecte");
		}

		//post  Pour tout Villageois V in numerosVillageois(M) ^ si c = DEPLACER, V != getVillageois(M, n) ^ si c2 = DEPLACER V != getVillageois(M, n2) 
		//positionVillageoisX(pasJeu(M ,c, c2, n, n2, a, a2), V) = positionVillageoisX(M, V)
		//positionVillageoisY(pasJeu(M, c, c2, n, n2, a, a2), V) = positionVillageoisY(M, V)

		for(int i = 0; i < numerosVillageois().size(); i++){
			if(Commande == ECommande.DEPLACER){
				if(i == argument){
					continue;
				}
			}

			if(Commande2 == ECommande.DEPLACER){
				if(i == argument2){
					continue;
				}
			}

			if(!(oldPositionsVillageois.get(getVillageois(i)).x == positionVillageoisX(getVillageois(i)))){
				throw new PostconditionError("Pour tout Villageois V in numerosVillageois(M) ^ si c = DEPLACER, V != getVillageois(M, n) ^ si c2 = DEPLACER V != getVillageois(M, n2)" + 
						"positionVillageoisX(pasJeu(M ,c, c2, n, n2, a, a2), V) = positionVillageoisX(M, V)"
						+ "positionVillageoisY(pasJeu(M, c, c2, n, n2, a, a2), V) = positionVillageoisY(M, V)");
			}

			if(!(oldPositionsVillageois.get(getVillageois(i)).y == positionVillageoisY(getVillageois(i)))){
				throw new PostconditionError("Pour tout Villageois V in numerosVillageois(M) ^ si c = DEPLACER, V != getVillageois(M, n) ^ si c2 = DEPLACER V != getVillageois(M, n2)" + 
						"positionVillageoisX(pasJeu(M ,c, c2, n, n2, a, a2), V) = positionVillageoisX(M, V)"
						+ "positionVillageoisY(pasJeu(M, c, c2, n, n2, a, a2), V) = positionVillageoisY(M, V)");
			}
		}


		//post Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M)
		//Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), x) =  Villageois::getQtor(M, x) + 1
		//VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1
		//MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1
		for(int i = 0; i < numerosVillageois().size(); i++){
			if(oldVillageoisAttente.get(i) == oldPasJeuCourant){
				if(!(getVillageois(i).getQtor() == oldQtorListVillageois.get(i) + 1)){
					throw new PostconditionError("Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M)"
							+ "Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), x) =  Villageois::getQtor(M, x) + 1 incorrecte");
				}

				if(!(VillageoisAttente().get(i) == -1)){
					throw new PostconditionError("Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M), VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1 incorrecte");
				}

				if(!(MineMinee().get(i) == -1)){
					throw new PostconditionError("Pour tout x in [0, numerosVillageois(M).size()[ ^ VillageoisAttente(M).get(x) == pasJeuCourant(M), MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(x) = -1 incorrecte");
				}
			}	
		}

		//post  Soit nbVillageoisDansMineI = nombre de n tel que getVillageois(M, n) dans la mine m avec VillageoisAttente(M).get(n) == pasJeuCourant(M);
		//      Pour tout m in [0, numerosMine(M).size()[
		//Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m) = orRestant(getMine(M, m) - nbVillageoisDansMineI(m)
		for(int m = 0; m < numerosMine().size(); m++){
			if(!(getMine(m).orRestant() == oldOrRestantMine.get(m) - oldVillageoisDansMineI.get(m))){
				throw new PostconditionError("Soit nbVillageoisDansMineI = nombre de n tel que getVillageois(M, n) dans la mine m avec VillageoisAttente(M).get(n) == pasJeuCourant(M)"
						+  "Pour tout m in [0, numerosMine(M).size()["
						+    "Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m) = orRestant(getMine(M, m) - nbVillageoisDansMineI(m) incorrecte");
			}
		}

		/*post   Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size() ^ !(Mine::estLaminee(getMine(M, m))
		     Si pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)), MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) != m ^ Mine::abandonCompteur(getMine(M, m)) != 51
		        Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Mine::abandonCompteur(getMine(M, m)) + 1

		  	 Si Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) == 51
		  		Mine::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2))
		  	 Sinon
		  	    Mine::appartenance(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = Mine::appartenance(getMine(M, m)) */

		for(int m = 0; m < numerosMine().size(); m++){
			int nbVillageoisDansMineMApresPasJeu = 0;
			for(int v = 0; v < MineMinee().size(); v++){
				if(MineMinee().get(v) == m){
					nbVillageoisDansMineMApresPasJeu++;
				}
			}
			if(!getMine(m).estLaminee()){

				if(nbVillageoisDansMineMApresPasJeu == 0 && oldAbandonCompteurMine.get(m) != 51){
					if(!(oldAbandonCompteurMine.get(m) +1 == getMine(m).abandonCompteur())){
						throw new PostconditionError("Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size() ^ !(Mine::estLaminee(getMine(M, m))" 
								+ "Si pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)), MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) != m ^ Mine::abandonCompteur(getMine(M, m)) != 51"
								+ "  Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Mine::abandonCompteur(getMine(M, m)) + 1 incorrecte");
					}
				}

				if(getMine(m).abandonCompteur() == 51){
					if(!(getMine(m).estAbandonnee() && getMine(m).appartenance() == ERace.RIEN)){
						throw new PostconditionError("Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size() ^ !(Mine::estLaminee(getMine(M, m))" 
								+ " Si Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) == 51 "
								+ "Mine::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2)) incorrecte");
					}
				}
				else{
					if(!(getMine(m).appartenance() == oldMineRace.get(m))){
						throw new PostconditionError("Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size() ^ !(Mine::estLaminee(getMine(M, m)) ^ Mine::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) != 51"
								+ " Mine::appartenance(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = Mine::appartenance(getMine(M, m)) incorrecte");
					}
				}
			}
		}
		
		/*post   Pour tout h in [1,2] ^ si c = ENTRERHOTELVILLE, h != a ^ si c2 = ENTRERHOTELVILLE, h!= a2 
		     Si HotelVille::abandonCompteur(HotelDeVille(M, h)) != 51
		     	HotelVille::abandonCompteur(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) =  HotelVille::abandonCompteur(HotelDeVille(M, h)) + 1
		  
		     Si HotelVille::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) == 51
		  	   HotelVille::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2))    
		     
		     Sinon
		  	   HotelVille::appartenance(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelVille::appartenance(HotelDeVille(M, h)) */
		for(int i = 1; i < 3; i++){
			if(Commande == ECommande.ENTRERHOTELVILLE){
				if(i == argument)
					continue;
			}
			if(Commande2 == ECommande.ENTRERHOTELVILLE){
				if(i == argument2)
					continue;
			}
			
			if(oldAbandonCompteurHotelVille.get(i) != 51){
				if(!(oldAbandonCompteurHotelVille.get(i) + 1 == HotelDeVille(i).abandonCompteur())){
					throw new PostconditionError("Pour tout h in [1,2] ^ si c = ENTRERHOTELVILLE, h != a ^ si c2 = ENTRERHOTELVILLE, h!= a2"
							+ "Si HotelVille::abandonCompteur(HotelDeVille(M, h)) != 51"
							+ "HotelVille::abandonCompteur(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) =  HotelVille::abandonCompteur(HotelDeVille(M, h)) + 1 incorrecte");
				}
			}
			
			if(HotelDeVille(i).abandonCompteur() == 51){
				if(!(HotelDeVille(i).estAbandonnee() && HotelDeVille(i).appartenance() == ERace.RIEN)){
					throw new PostconditionError("Pour tout h in [1,2] ^ si c = ENTRERHOTELVILLE, h != a ^ si c2 = ENTRERHOTELVILLE, h!= a2"
							+ "Si HotelVille::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) == 51"
							+ "HotelVille::abandoned(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2))");
				}
			}
			else{
				if(!(HotelDeVille(i).appartenance() == oldHotelVilleRace.get(i))){
					throw new PostconditionError("Pour tout h in [1,2] ^ si c = ENTRERHOTELVILLE, h != a ^ si c2 = ENTRERHOTELVILLE, h!= a2"
				            + "Si HotelVille::abandonCompteur(getMine(pasJeu(M ,c, c2, n, n2, a, a2), h)) != 51"
							+ "HotelVille::appartenance(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), h)) = HotelVille::appartenance(HotelDeVille(M, h)) incorrecte");
				}
			}
			
		}
		
		/*post Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size()[ 
		       Soit x = 0
		       Pour tout i in MineMinee(pasJeu(M ,c, c2, n, n2, a, a2))
		     	 Si MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(i) = m
		     	   x = x + 1
		       Si Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = 0 || Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) - x <= 0
		         Mine::estLaminee(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = true */
		for(int m = 0; m < numerosMine().size(); m++){
			int nbVillageoisDansMineMApresPasJeu = 0;
			for(int v = 0; v < MineMinee().size(); v++){
				if(MineMinee().get(v) == m){
					nbVillageoisDansMineMApresPasJeu++;
				}
			}
			if(getMine(m).orRestant() == 0 || getMine(m).orRestant() - nbVillageoisDansMineMApresPasJeu <= 0){
				if(!(getMine(m).estLaminee())){
					throw new PostconditionError("Pour tout m in [0, numerosMine(pasJeu(M ,c, c2, n, n2, a, a2)).size()["
							+ "Si Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = 0 || Mine::orRestant(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) - x <= 0"
							+ "Mine::estLaminee(getMine(pasJeu(M ,c, c2, n, n2, a, a2), m)) = true incorrecte");
				}
			}
		}
		
		/*post
		 Pour tout h in [1, 2[ 
		         Si !((c = ENTRERHOTELVILLE ^ h = a) || (c2 = ENTRERHOTELVILLE ^ h = a2))
		           HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  HotelVille::orRestant(HotelDeVille(M, m))
		 */
		for(int h = 1; h < 3; h++){
			if(Commande == ECommande.ENTRERHOTELVILLE){
				if(h == argument)
					continue;
			}
			if(Commande2 == ECommande.ENTRERHOTELVILLE){
				if(h == argument2)
					continue;
			}
			if(!(HotelDeVille(h).orRestant() == oldOrRestantHotelVille.get(h))){
				throw new PostconditionError("Pour tout h in [1, 2["
						+ "Si !((c = ENTRERHOTELVILLE ^ h = a) || (c2 = ENTRERHOTELVILLE ^ h = a2))"
						+ "HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  HotelVille::orRestant(HotelDeVille(M, m)) incorrecte");
			}
		}
		
		/*post Pour tout n in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		      Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) =  Villageois::getPdv(getVillageois(M, n)) */
		for(int v = 0; v < numerosVillageois().size(); v++){
			if(!(getVillageois(v).getPdv() == oldPdvVillageois.get(v))){
				throw new PostconditionError("Pour tout n in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()["
						+ "Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) =  Villageois::getPdv(getVillageois(M, n)) incorrecte");
			}
		}
		
		/*post
		 Pour tout m in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()[   
		      Muraille::getPdv(getMuraille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Muraille::getMuraille(getMuraille(M, m))
		 */
		for(int m = 0; m < numerosMuraille().size(); m++){
			if(!(getMuraille(m).getPdv() == oldPdvMuraille.get(m))){
				throw new PostconditionError("Pour tout m in [0, numerosVillageois(pasJeu(M ,c, c2, n, n2, a, a2)).size()["
						+"Muraille::getPdv(getMuraille(pasJeu(M ,c, c2, n, n2, a, a2), m)) =  Muraille::getMuraille(getMuraille(M, m)) incorrecte");
			}
		}
		return this;
	}

	private void checkDeplacer(int numVillageois, int argument, int oldQtor, int oldPdv){
		//post Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getQtor(M, n) 
		if(!(getVillageois(numVillageois).getQtor() == oldQtor)){
			throw new PostconditionError("Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getQtor(M, n) incorrecte");
		}

		//post Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getPdv(M, n)
		if(!(getVillageois(numVillageois).getPdv() == oldPdv)){
			throw new PostconditionError("Villageois::getPdv(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) =  Villageois::getPdv(M, n) incorrecte");
		}
	}

	private void checkEntrerMine(int numVillageois, int numMine, int oldPasJeuCourant, IMineService oldM){
		//pre VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = PasJeuCourant(M) + 16
		if(!(VillageoisAttente().get(numVillageois) == oldPasJeuCourant + 16)){
			throw new PostconditionError("VillageoisAttente(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = PasJeuCourant(M) + 16 incorrecte");
		}

		//pre MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = a
		if(!(MineMinee().get(numVillageois) == numMine)){
			throw new PostconditionError("MineMinee(pasJeu(M ,c, c2, n, n2, a, a2)).get(n) = a");
		}

		//pre Mine::accueil(getMine(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2), a)
		oldM.accueil(getVillageois(numVillageois).getRace());
		if(!(oldM.estAbandonnee() == getMine(numMine).estAbandonnee() && oldM.abandonCompteur() == getMine(numMine).abandonCompteur() && getMine(numMine).appartenance() == oldM.appartenance())){
			throw new PostconditionError("Mine::accueil(getMine(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = getMine(pasJeu(M ,c, c2, n, n2, a, a2), a) incorrecte");
		}
	}

	private void checkEntrerHotelVille(int numVillageois, int numHotelVille, int oldQtor, int oldOrRestant, IHotelVilleService oldHv){
		//pre HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2)), 2) = HotelVille::orRestant(getHotelVille(M, 2) + Villageois::getQtor(getVillageois(M, n2)
		if(!(HotelDeVille(numHotelVille).orRestant() == oldQtor + oldOrRestant)){
			throw new PostconditionError("HotelVille::orRestant(HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2)), 2) = HotelVille::orRestant(getHotelVille(M, 2) + Villageois::getQtor(getVillageois(M, n2) incorrecte");
		}

		//pre Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) = 0	
		if(!(getVillageois(numVillageois).getQtor() == 0)){
			throw new PostconditionError("Villageois::getQtor(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n) = 0 incorrecte");
		}

		//pre HotelVille::accueil(HotelDeVille(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), a)
		oldHv.accueil(getVillageois(numVillageois).getRace());
		if(!(oldHv.estAbandonnee() == HotelDeVille(numHotelVille).estAbandonnee() && oldHv.abandonCompteur() == HotelDeVille(numHotelVille).abandonCompteur() && HotelDeVille(numHotelVille).appartenance() == oldHv.appartenance())){
			throw new PostconditionError("HotelVille::accueil(HotelDeVille(M, a), Villageois::getRace(getVillageois(pasJeu(M ,c, c2, n, n2, a, a2), n)) = HotelDeVille(pasJeu(M ,c, c2, n, n2, a, a2), a)");
		}
	}


}
