package com.github.falchio;

public record Location(double lat, double lon) {
    public double bearingTo(Location location) {
        final double y = Math.sin(location.lon - lon) * Math.cos(location.lat);
        final double x = Math.cos(lat) * Math.sin(location.lat) - Math.sin(lat) * Math.cos(location.lat) * Math.cos(location.lat - lat);

        final double bearing = Math.toDegrees(Math.atan2(y, x));
        final double normalizedBearing = 360D - ((bearing + 360D) % 360D);
        return normalizedBearing;
    }
}