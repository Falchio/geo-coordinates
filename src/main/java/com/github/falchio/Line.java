package com.github.falchio;

public record Line(Location start, Location end) {

    private static final double RADIUS_OF_EARTH_IN_METERS = 6_371_000;

    /**
     * <a href="https://stackoverflow.com/a/35587935/12980819">source</a>
     */
    public double calculateMinDistanceInMeters(Location midPoint) {
        double bear12 = start.bearingTo(end);
        double bear13 = start.bearingTo(midPoint);
        double dis13 = start.distanceTo(midPoint);

        double diff = Math.abs(bear13 - bear12);
        if (diff > Math.PI) {
            diff = 2 * Math.PI - diff;
        }

        double dxa;
        if (diff > (Math.PI / 2.0)) {
            dxa = dis13;
        } else {
            double dxt = Math.asin(Math.sin(dis13 / RADIUS_OF_EARTH_IN_METERS) * Math.sin(bear13 - bear12)) * RADIUS_OF_EARTH_IN_METERS;
            double dis12 = start.distanceTo(end);
            double dis14 = Math.acos(Math.cos(dis13 / RADIUS_OF_EARTH_IN_METERS) / Math.cos(dxt / RADIUS_OF_EARTH_IN_METERS)) * RADIUS_OF_EARTH_IN_METERS;
            if (dis14 > dis12) {
                dxa = end.distanceTo(midPoint);
            } else {
                dxa = Math.abs(dxt);
            }
        }

        return dxa;
    }
}