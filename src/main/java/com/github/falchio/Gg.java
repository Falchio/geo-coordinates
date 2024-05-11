package com.github.falchio;

/**
 * <a href="https://stackoverflow.com/a/35587935/12980819">source</a>
 */
public class Gg {
    public static void main(String[] args) {
        double lat1 = Math.toRadians(3.227511);
        double lon1 = Math.toRadians(101.724318);
        double lat2 = Math.toRadians(3.222895);
        double lon2 = Math.toRadians(101.719751);
        double lat3 = Math.toRadians(3.224972);
        double lon3 = Math.toRadians(101.722932);

        double R = 6371000; // Earth's radius in meters

        double bear12 = bear(lat1, lon1, lat2, lon2);
        double bear13 = bear(lat1, lon1, lat3, lon3);
        double dis13 = dis(lat1, lon1, lat3, lon3);

        double diff = Math.abs(bear13 - bear12);
        if (diff > Math.PI) {
            diff = 2 * Math.PI - diff;
        }

        double dxa;
        if (diff > (Math.PI / 2)) {
            dxa = dis13;
        } else {
            double dxt = Math.asin(Math.sin(dis13 / R) * Math.sin(bear13 - bear12)) * R;
            double dis12 = dis(lat1, lon1, lat2, lon2);
            double dis14 = Math.acos(Math.cos(dis13 / R) / Math.cos(dxt / R)) * R;
            if (dis14 > dis12) {
                dxa = dis(lat2, lon2, lat3, lon3);
            } else {
                dxa = Math.abs(dxt);
            }
        }

        System.out.println("The shortest distance in meters is: " + dxa);
    }

    public static double dis(double latA, double lonA, double latB, double lonB) {
        double R = 6371000;
        return Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(lonB - lonA)) * R;
    }

    public static double bear(double latA, double lonA, double latB, double lonB) {
        return Math.atan2(Math.sin(lonB - lonA) * Math.cos(latB), Math.cos(latA) * Math.sin(latB) - Math.sin(latA) * Math.cos(latB) * Math.cos(lonB - lonA));
    }
}

