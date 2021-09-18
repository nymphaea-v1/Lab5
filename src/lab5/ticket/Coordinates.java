package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.Scanner;

class Coordinates {
    private final Long x;
    private final Integer y;

    public Coordinates(Long x, Integer y) {
        this.x = x;
        this.y = y;
    }

    protected Long getX() {
        return x;
    }

    protected Integer getY() {
        return y;
    }

    public static Long readX(Scanner scanner) throws IncorrectFieldException {
        String xString = scanner.next().trim();
        long x;

        try {
            x = Long.parseLong(xString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(xString);
        }

        if (x > 565) throw new IncorrectFieldException(x);

        return x;
    }

    public static Integer readY(Scanner scanner) throws IncorrectFieldException {
        String yString = scanner.next().trim();
        int y;

        try {
            y = Integer.parseInt(yString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(yString);
        }

        if (y > 907) throw new IncorrectFieldException(y);

        return y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}