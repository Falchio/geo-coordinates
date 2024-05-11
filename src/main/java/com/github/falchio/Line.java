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
}