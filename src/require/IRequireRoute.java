package require;

import java.awt.Point;
import java.util.List;

import services.IRouteService;

public interface IRequireRoute {
	void bindRoute(List<IRouteService> routes, List<Point> positionsRoutes);
}
