import java.util.Scanner;

public class Spot {
    int x, y;
    Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        System.out.println("=== Chess Game ===");
        board.display();

        while (true) {
            System.out.println((whiteTurn ? "White" : "Black") + "'s turn. Enter move (e.g., e2 e4) or 'exit':");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid format! Try: e2 e4");
                continue;
            }

            int startX = 8 - (parts[0].charAt(1) - '0');
            int startY = parts[0].charAt(0) - 'a';
            int endX = 8 - (parts[1].charAt(1) - '0');
            int endY = parts[1].charAt(0) - 'a';

            Spot start = board.getBox(startX, startY);
            Spot end = board.getBox(endX, endY);

            if (start == null || end == null || start.piece == null || start.piece.white != whiteTurn) {
                System.out.println("Invalid selection! Try again.");
                continue;
            }

            end.piece = start.piece;
            start.piece = null;
            whiteTurn = !whiteTurn;

            board.display();
        }
        scanner.close();
    }
}

class Piece {
    boolean white;
    String symbol;

    public Piece(boolean white, String symbol) {
        this.white = white;
        this.symbol = white ? symbol.toUpperCase() : symbol.toLowerCase();
    }
}

class Board {
    Spot[][] grid = new Spot[8][8];

    public Board() {
        String[] setup = {"r", "n", "b", "q", "k", "b", "n", "r"};
        for (int j = 0; j < 8; j++) {
            grid[0][j] = new Spot(0, j, new Piece(false, setup[j]));
            grid[1][j] = new Spot(1, j, new Piece(false, "p"));
            grid[6][j] = new Spot(6, j, new Piece(true, "p"));
            grid[7][j] = new Spot(7, j, new Piece(true, setup[j]));
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Spot(i, j, null);
            }
        }
    }

    public Spot getBox(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8) ? grid[x][y] : null;
    }

    public void display() {
        System.out.println("\n  a b c d e f g h\n  ----------------");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "|");
            for (int j = 0; j < 8; j++) {
                System.out.print((grid[i][j].piece == null ? "." : grid[i][j].piece.symbol) + " ");
            }
            System.out.println("|" + (8 - i));
        }
        System.out.println("  ----------------\n  a b c d e f g h\n");
    }
}
