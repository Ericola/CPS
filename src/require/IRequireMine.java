package require;

import java.awt.Point;
import java.util.List;

import services.IMineService;

public interface IRequireMine {
	void bindMine(List<IMineService> mines, List<Point> positionsMines);
}
