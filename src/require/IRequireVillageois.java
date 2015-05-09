package require;

import java.awt.Point;
import java.util.List;

import services.IVillageoisService;

public interface IRequireVillageois {
	void bindVillageois(List<IVillageoisService> villageois, List<Point> positionsVillageois);
}
