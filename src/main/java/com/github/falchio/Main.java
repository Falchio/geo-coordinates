package com.github.falchio;

public class Main {
    public static void main(String[] args) {
        final Location locA = new Location(3.227511, 101.724318);
        final Location locB = new Location(3.222895, 101.719751);
        final Line line = new Line(locA, locB);
        var midPoint = new Location(3.224972, 101.722932);
        final double minimalDistance = minDistanceInMeters(line, midPoint);

        System.out.println("min distance: " + minimalDistance);
    }

    private static double minDistanceInMeters(Line line, Location midPoint) {

        final double bearingAC = line.start().bearingTo(midPoint);
        final double bearingAB = line.start().bearingTo(line.end());

        double aLatRadians = Math.toRadians(line.start().lat());
        double midLatRadians = Math.toRadians(midPoint.lat());
        double lonRadians = Math.toRadians(midPoint.lon() - line.start().lon());

        double radiusOfEarth = 6371;
        double distanceAC = Math.acos(Math.sin(aLatRadians) * Math.sin(midLatRadians) + Math.cos(aLatRadians) * Math.cos(midLatRadians) * Math.cos(lonRadians)) * radiusOfEarth;
        double minDistance = Math.abs(Math.asin(Math.sin(distanceAC / radiusOfEarth) * Math.sin(Math.toRadians(bearingAC) - Math.toRadians(bearingAB))) * radiusOfEarth);

        return minDistance * 1_000;
    }

}