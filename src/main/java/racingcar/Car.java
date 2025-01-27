package racingcar;

public class Car {
    private final String name;
    private int position;

    public Car(final String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    public void move(final MovingStrategy movingStrategy) {
        if (movingStrategy.movable()) { // 테스트하기 쉬운 부분
            position++;
        }
    }

    public int getPosition() {
        return position;
    }
}
