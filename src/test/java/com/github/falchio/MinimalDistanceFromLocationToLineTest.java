package com.github.falchio;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimalDistanceFromLocationToLineTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void calculateMinimalDistance(Line line, Location midPoint, double expected) {
        assertEquals(expected, line.minDistanceInMeters(midPoint));
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Line(new Location(-10.1, -55.5), new Location(-15.2, -45.1)), new Location(-10.5, -62.5), 767_100),
                Arguments.of(new Line(new Location(40.5, 60.5), new Location(50.5, 80.5)), new Location(51, 69), 479_610),
                Arguments.of(new Line(new Location(21.72, 35.61), new Location(23.65, 40.7)), new Location(25, 42), 19_971_000)
        );
    }

}
