package require;

import java.awt.Point;

import services.IHotelVilleService;

public interface IRequireHotelVille {
	void bindHotelVille(IHotelVilleService h1 , IHotelVilleService h2, Point positionHotelVille1,
			Point positionHotelVille2);
}
