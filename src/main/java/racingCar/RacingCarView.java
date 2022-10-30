package racingCar;

public class RacingCarView {
    public static final String TRACE_SYMBOL = "-";
    private final RacingCarName carName;
    private final Position carPosition;

    public RacingCarView(final RacingCarName carName, final Position carPosition) {
        this.carName = carName;
        this.carPosition = carPosition;
    }

    public String toString() {
        StringBuilder trace = new StringBuilder();
        Position i = new Position(0);

        while (i.compareTo(carPosition) < 0) {
            trace.append(TRACE_SYMBOL);
            i = i.moveForward();
        }

        return carName + " : " + trace;
    }
}
