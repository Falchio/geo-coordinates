package com.github.falchio;

public record Line(Location start, Location end) {
    /**
     * <a href="https://stackoverflow.com/a/20369652/12980819">source</a>
     */
    public double minDistanceInMeters(Location midPoint) {
        final double bearingAC = start.bearingTo(midPoint);
        final double bearingAB = start.bearingTo(end);

        double aLatRadians = Math.toRadians(start.lat());
        double midLatRadians = Math.toRadians(midPoint.lat());
        double lonRadians = Math.toRadians(midPoint.lon() - start.lon());

        double radiusOfEarth = 6371;
        double distanceAC = Math.acos(Math.sin(aLatRadians) * Math.sin(midLatRadians) + Math.cos(aLatRadians) * Math.cos(midLatRadians) * Math.cos(lonRadians)) * radiusOfEarth;
        double minDistance = Math.abs(Math.asin(Math.sin(distanceAC / radiusOfEarth) * Math.sin(Math.toRadians(bearingAC) - Math.toRadians(bearingAB))) * radiusOfEarth);

        return minDistance * 1_000;
    }

    private static final double RADIUS_OF_EARTH_IN_METERS = 6_371_000;

    /**
     * <a href="https://stackoverflow.com/a/35587935/12980819">source</a>
     */
    public double calculateMinDistanceInMeters(Location midPoint) {

        double lat1 = Math.toRadians(start.lat());
        double lon1 = Math.toRadians(start.lon());
        double lat2 = Math.toRadians(end.lat());
        double lon2 = Math.toRadians(end.lon());
        double lat3 = Math.toRadians(midPoint.lat());
        double lon3 = Math.toRadians(midPoint.lon());


        double bear12 = bearing(lat1, lon1, lat2, lon2);
        double bear13 = bearing(lat1, lon1, lat3, lon3);
        double dis13 = distance(lat1, lon1, lat3, lon3);

        double diff = Math.abs(bear13 - bear12);
        if (diff > Math.PI) {
            diff = 2 * Math.PI - diff;
        }

        double dxa;
        if (diff > (Math.PI / 2.0)) {
            dxa = dis13;
        } else {
            double dxt = Math.asin(Math.sin(dis13 / RADIUS_OF_EARTH_IN_METERS) * Math.sin(bear13 - bear12)) * RADIUS_OF_EARTH_IN_METERS;
            double dis12 = distance(lat1, lon1, lat2, lon2);
            double dis14 = Math.acos(Math.cos(dis13 / RADIUS_OF_EARTH_IN_METERS) / Math.cos(dxt / RADIUS_OF_EARTH_IN_METERS)) * RADIUS_OF_EARTH_IN_METERS;
            if (dis14 > dis12) {
                dxa = distance(lat2, lon2, lat3, lon3);
            } else {
                dxa = Math.abs(dxt);
            }
        }

        return dxa;
    }

    public static double distance(double latA, double lonA, double latB, double lonB) {
        return Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(lonB - lonA)) * RADIUS_OF_EARTH_IN_METERS;
    }

    public static double bearing(double latA, double lonA, double latB, double lonB) {
        return Math.atan2(Math.sin(lonB - lonA) * Math.cos(latB), Math.cos(latA) * Math.sin(latB) - Math.sin(latA) * Math.cos(latB) * Math.cos(lonB - lonA));
    }
}