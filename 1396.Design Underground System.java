import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    private static class CheckInInfo {
        String stationName;
        int time;

        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    private static class RouteInfo {
        double totalTime;
        int tripCount;

        RouteInfo(double totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }
    }

    private Map<Integer, CheckInInfo> checkInMap;
    private Map<String, RouteInfo> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInInfo startInfo = checkInMap.remove(id);

        String routeKey = startInfo.stationName + "->" + stationName;
        int duration = t - startInfo.time;

        RouteInfo route = routeMap.getOrDefault(routeKey, new RouteInfo(0, 0));
        route.totalTime += duration;
        route.tripCount += 1;

        routeMap.put(routeKey, route);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteInfo route = routeMap.get(routeKey);

        return route.totalTime / route.tripCount;
    }
}

