package contracts;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;
import decorators.MoteurJeuDecorator;
import enums.ECommande;
import enums.EResultat;
import exceptions.InvariantError;

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
		if(!((estFini() && HotelDeVille(1).orRestant() >= 1664 && HotelDeVille(2).orRestant() >= 1664)||  pasJeuCourant() == MaxPasJeu())){
			throw new InvariantError("estFini(M) =(min) HotelVille::orRestant(hotelDeVille(M,1)) >= 1664 && HotelVille::orRestant(hotelDeVille(M,2)) >= 1664|| pasJeuCourant(M) = maxPasJeu(M)) incorrect");
		}

		/* 	resultatFinal(M) = HUMAINGAGNE <=> HotelVille:orRestant(HotelDeville(M, 1)) >= 1664 => */
		if(!(resultatFinal() == EResultat.HUMAINGAGNE && HotelDeVille(1).orRestant() >= 1664)){
			throw new InvariantError("resultatFinal(M) = HUMAINGAGNE <=> HotelVille:orRestant(HotelDeville(M, 1)) >= 1664 incorrecte");
		}

		/* 	resultatFinal(M) = ORCGAGNE <=> HotelVille:orRestant(HotelDeville(M, 2)) >= 1664 */
		if(!(resultatFinal() == EResultat.ORCGAGNE && HotelDeVille(1).orRestant() >= 1664)){
			throw new InvariantError("resultatFinal(M) = HUMAINGAGNE <=> HotelVille:orRestant(HotelDeville(M, 2)) >= 1664 incorrecte");
		}

		/*	peutEntrer(M, numVillageois, numMine) =(min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionMineX(M, numMine), positionMineY(M, numMine)) <= 51*/
		for(int i = 0; i < numerosVillageois().size(); i++){
			for(int j = 0; j < numerosMine().size(); j++){
				if(!(peutEntrer(i, j) && Point.distance(positionVillageoisX(i), positionVillageoisY(i), positionMineX(j), positionMineY(j)) <= 51)){
					throw new InvariantError("peutEntrer(M, numVillageois, numMine) =(min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionMineX(M, numMine), positionMineY(M, numMine)) <= 51 incorrecte");	
				}
			}	
		}

		/* peutEntrerHotelVille(M, numVillageois, numHotelVille) = (min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionHotelVilleX(M, numHotelVille), positionHotelVilleY(M, numHotelVille)) <= 51 */
		for(int i = 0; i < numerosVillageois().size(); i++){
			if(!(peutEntrerHotelVille(i, 1) && Point.distance(positionVillageoisX(i), positionVillageoisY(i), positionHotelVilleX(1), positionHotelVilleY(1)) <= 51)){
				throw new InvariantError("peutEntrerHotelVille(M, numVillageois, 1) = (min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionHotelVilleX(M, 1), positionHotelVilleY(M, 1)) <= 51 incorrecte");	
			}	
			
			if(!(peutEntrerHotelVille(i, 2) && Point.distance(positionVillageoisX(i), positionVillageoisY(i), positionHotelVilleX(2), positionHotelVilleY(2)) <= 51)){
				throw new InvariantError("peutEntrerHotelVille(M, numVillageois, 2) = (min) distance(positionVillageoisX(M, numVillageois), positionVillageoisY(M, numVillageois), positionHotelVilleX(M, 2), positionHotelVilleY(M, 2)) <= 51 incorrecte");	
			}	
		}
		
		//Ajouter invariants
	}
	
	
	public int LargeurTerrain() {
		// TODO Auto-generated method stub

		return super.LargeurTerrain();
	}

	@Override
	public int HauteurTerrain() {
		// TODO Auto-generated method stub
		return super.HauteurTerrain();
	}

	@Override
	public int MaxPasJeu() {
		// TODO Auto-generated method stub
		return super.MaxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		// TODO Auto-generated method stub
		return super.pasJeuCourant();
	}

	@Override
	public boolean estFini() {
		// TODO Auto-generated method stub
		return super.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		// TODO Auto-generated method stub
		return super.resultatFinal();
	}

	@Override
	public List<IVillageoisService> numerosVillageois() {
		// TODO Auto-generated method stub
		return super.numerosVillageois();

	}

	@Override
	public IVillageoisService getVillageois(int n) {
		// TODO Auto-generated method stub
		return super.getVillageois(n);
	}

	@Override
	public int positionVillageoisX(int n) {
		// TODO Auto-generated method stub
		return super.positionVillageoisX(n);
	}

	@Override
	public int positionVillageoisY(int n) {
		// TODO Auto-generated method stub
		return super.positionVillageoisY(n);
	}

	@Override
	public List<IMineService> numerosMine() {
		// TODO Auto-generated method stub
		return super.numerosMine();
	}

	@Override
	public IMineService getMine(int n) {
		// TODO Auto-generated method stub
		return super.getMine(n);
	}

	@Override
	public int positionMineX(int n) {
		// TODO Auto-generated method stub
		return super.positionHotelVilleY(n);
	}

	@Override
	public int positionMineY(int n) {
		// TODO Auto-generated method stub
		return super.positionMineY(n);
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		//double distance=positions.get(mines.get(numMine)).distance(positions.get(villageois.get(numVillageois)));
		return super.peutEntrer(numVillageois, numMine);
	}

	@Override
	public IHotelVilleService HotelDeVille(int n) {
		// TODO Auto-generated method stub
		return super.HotelDeVille(n);
	}

	@Override
	public int positionHotelVilleX(int n) {
		// TODO Auto-generated method stub
		return super.positionHotelVilleX(n);
	}

	@Override
	public int positionHotelVilleY(int n) {
		// TODO Auto-generated method stub
		
		return super.positionHotelVilleY(n);
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int hv) {
		// TODO Auto-generated method stub
		/*double distance = 0.0;
		if(hv == 1)
			distance=positions.get(hotelDeVille).distance(positions.get(hotelDeVille));
		else{
			distance=positions.get(hotelDeVille2).distance(positions.get(hotelDeVille));
		}*/
		return super.peutEntrerHotelVille(numVillageois, hv);
	}

	@Override
	public List<IRouteService> numerosRoute() {
		// TODO Auto-generated method stub
		return super.numerosRoute();
	}

	@Override
	public IRouteService getRoute(int n) {
		// TODO Auto-generated method stub
		return super.getRoute(n);
	}

	@Override
	public int positionRouteX(int n) {
		// TODO Auto-generated method stub
		return super.positionRouteX(n);
	}

	@Override
	public int positionRouteY(int n) {
		// TODO Auto-generated method stub
		return super.positionRouteY(n);
	}

	@Override
	public boolean estSurRoute(int numVillageois) {
		// TODO Auto-generated method stub
		/*for(int numRoute = 0; numRoute < routes.size(); numRoute++){
			Rectangle rect = new Rectangle(positionRouteX(numRoute), positionRouteY(numRoute), routes.get(numRoute).getLargeur(), routes.get(numRoute).getHauteur());
			if(rect.contains(positions.get(getVillageois(numVillageois))))
				return true;
		}*/
		return super.estSurRoute(numVillageois);
	}

	public boolean estSurMuraille(Point p) {
		// TODO Auto-generated method stub
	/*	for(int i = 0; i < murailles.size(); i++){
			Rectangle rect = new Rectangle(positionMurailleX(i) 
					,positionMurailleY(i)
					,routes.get(i).getLargeur()
					,routes.get(i).getHauteur());
			if(rect.contains(p))
				return false;
		}*/
		return super.estSurMuraille(p);
	}
	@Override
	public List<IMurailleService> numerosMuraille() {
		// TODO Auto-generated method stub
		return super.numerosMuraille();
	}

	@Override
	public IMurailleService getMuraille(int n) {
		// TODO Auto-generated method stub
		return super.getMuraille(n);
	}

	@Override
	public int positionMurailleX(int n) {
		// TODO Auto-generated method stub
		return  super.positionMurailleX(n);
	}

	@Override
	public int positionMurailleY(int n) {
		// TODO Auto-generated method stub
		return super.positionMineY(n);
	}

	public IMoteurJeuService pasJeu(ECommande Commande, ECommande Commande2, int numVillageois, int numVillageois2, int argument, int argument2) {
		return super.pasJeu(Commande, Commande2, numVillageois, numVillageois2, argument, argument2);
	}
	

}
