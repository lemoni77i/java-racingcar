package racingCar;


@FunctionalInterface
public interface MoveStrategy {
    boolean canMove(int number);
}
