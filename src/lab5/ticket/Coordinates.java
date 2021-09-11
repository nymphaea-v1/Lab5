package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.List;
import java.util.Scanner;

class Coordinates {
    private final Long x;
    private final Integer y;

    public Coordinates(List<Object> fields) {
        x = (Long) fields.get(0);
        y = (Integer) fields.get(1);
    }

    protected Long getX() {
        return x;
    }

    protected Integer getY() {
        return y;
    }

    public static Long readX(Scanner scanner) throws IncorrectFieldException {
        try {
            String xStr = scanner.next();
            Long x = Long.parseLong(xStr);

            if (x > 0) return x;

            throw new IncorrectFieldException(x);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("x coordinate");
        }
    }

    public static Integer readY(Scanner scanner) throws IncorrectFieldException {
        try {
            String yStr = scanner.next();
            Integer y = Integer.parseInt(yStr);

            if (y > 0) return y;

            throw new IncorrectFieldException(y);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("y coordinate");
        }
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}