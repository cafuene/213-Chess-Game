package chess;

public class Square {
    public int rank;
    public int file;

    public Square(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isValid() {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }

    public boolean equals(Square other) {
        return rank == other.rank && file == other.file;
    }

    public Square copy() {
        return new Square(rank, file);
    }

    public String toString() {
        return (char)('a' + file) + Integer.toString(8 - rank);
    }

    public static Square fromString(String s) {
        int file = s.charAt(0) - 'a';
        int rank = 8 - Character.getNumericValue(s.charAt(1));
        return new Square(rank, file);
    }

    public static boolean isOnBoard(int rank, int file) {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }

    public static boolean isOnBoard(Square square) {
        return isOnBoard(square.rank, square.file);
    }

    public static Square getDirection(Square from, Square to) {
        return new Square(to.rank - from.rank, to.file - from.file);
    }

    public static Square add(Square a, Square b) {
        return new Square(a.rank + b.rank, a.file + b.file);
    }

    public static Square multiply(Square a, int k) {
        return new Square(a.rank * k, a.file * k);
    }

    public static Square divide(Square a, int k) {
        return new Square(a.rank / k, a.file / k);
    }

  


    
}
