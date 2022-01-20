package skorupinski.aurora.map;

import java.util.HashMap;

import skorupinski.aurora.math.Range;

public enum Direction16 {
    NORTH,
    NORTH_NORTH_WEST,
    NORTH_WEST,
    NORTH_WEST_WEST,
    WEST,
    SOUTH_WEST_WEST,
    SOUTH_WEST,
    SOUTH_SOUTH_WEST,
    SOUTH,
    SOUTH_SOUTH_EAST,
    SOUTH_EAST,
    SOUTH_EAST_EAST,
    EAST,
    NORTH_EAST_EAST,
    NORTH_EAST,
    NORTH_NORTH_EAST;

    private static HashMap<Direction16, Range> angleRange = new HashMap<>() {{
        put(EAST, new Range(33.75f, 56.25f));
        put(NORTH_EAST_EAST, new Range(56.25f, 78.75f));
        put(NORTH_EAST, new Range(78.75f, 101.25f));
        put(NORTH_NORTH_EAST, new Range(101.25f, 123.75f));
        put(NORTH, new Range(123.75f, 146.25f));
        put(NORTH_NORTH_WEST, new Range(146.25f, 168.75f));
        put(NORTH_WEST, new Range(168.75f, -168.75f));
        put(NORTH_WEST_WEST, new Range(-168.75f, -146.25f));
        put(WEST, new Range(-146.25f, -123.75f));
        put(SOUTH_WEST_WEST, new Range(-123.75f, -101.25f));
        put(SOUTH_WEST, new Range(-101.25f, -78.75f));
        put(SOUTH_SOUTH_WEST, new Range(-78.75f, -56.25f));
        put(SOUTH, new Range(-56.25f, -33.75f));
        put(SOUTH_SOUTH_EAST, new Range(-33.75f, -11.25f));
        put(SOUTH_EAST, new Range(-11.25f, 11.25f));
        put(SOUTH_EAST_EAST, new Range(11.25f, 33.75f));
    }}; 

    public static Direction16 getDirection(float angle) {
        Direction16 direction = null;

        for (HashMap.Entry<Direction16, Range> entry : angleRange.entrySet()) {
            Direction16 dir = entry.getKey();
            Range range = entry.getValue();
            
            if(range.contains(angle)) {
                direction = dir;
            }
        }
        return direction;
    }

    public static Range getAngleRange(Direction16 direction) {
        return angleRange.get(direction);
    }

    public static float getDirectionMid(Direction16 direction) {
        return angleRange.get(direction).getMid();
    }
}