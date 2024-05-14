package com.github.falchio;

import java.util.Objects;

public final class Location {
    public final double lat;
    public final double lon;
    public final double latInRads;
    public final double lonInRads;

    private static final double RADIUS_OF_EARTH_IN_METERS = 6_371_000;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.latInRads = Math.toRadians(lat);
        this.lonInRads = Math.toRadians(lon);
    }

    public double bearingTo(Location location) {
        final double diff = location.lonInRads - this.lonInRads;
        return Math.atan2(
                Math.sin(diff) * Math.cos(location.latInRads),
                Math.cos(this.latInRads) * Math.sin(location.latInRads) - Math.sin(this.latInRads) * Math.cos(location.latInRads) * Math.cos(diff)
        );
    }

    public double distanceTo(Location location) {
        return Math.acos(Math.sin(this.latInRads) * Math.sin(location.latInRads) +
                Math.cos(this.latInRads) * Math.cos(location.latInRads) * Math.cos(location.lonInRads - this.lonInRads)
        ) * RADIUS_OF_EARTH_IN_METERS;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Location) obj;
        return Double.doubleToLongBits(this.lat) == Double.doubleToLongBits(that.lat) &&
                Double.doubleToLongBits(this.lon) == Double.doubleToLongBits(that.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public String toString() {
        return "Location[" +
                "lat=" + lat + ", " +
                "lon=" + lon + ']';
    }
}