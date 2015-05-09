package require;

import java.awt.Point;
import java.util.List;

import services.IMurailleService;

public interface IRequireMuraille {
	void bindMuraille(List<IMurailleService> murailles, List<Point> positionsMurailles);
}
