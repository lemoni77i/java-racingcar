package racingCar.domain;


@FunctionalInterface
public interface MoveStrategy {
    boolean canMove();
}
