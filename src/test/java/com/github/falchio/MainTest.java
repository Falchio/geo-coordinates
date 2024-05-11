package com.github.falchio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    public void test() {
        final Location start = new Location(3.227511, 101.724318);
        final Location end = new Location(3.222895, 101.719751);
        final Line line = new Line(start, end);
        var midPoint = new Location(3.224972, 101.722932);
        final double minimalDistance = line.minDistanceInMeters(midPoint);
        final double expected = 88.93321238604187;
        assertEquals(expected, minimalDistance);
    }
}