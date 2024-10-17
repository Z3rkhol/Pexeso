import java.util.*;

public class Main {
    private static final Scanner scn = new Scanner(System.in);
    private static final int BOARD_SIZE = 4;
    private static final String EMPTY_CELL = "*";
    private static final String[] CARD_SYMBOLS = {"₳", "■", "▲", "●", "₼", "⨝", "Ω", "Ξ"};

    public static void main(String[] args) throws InterruptedException {
        printIntroduction(); // Printne úvodní text hry
        while (true) {
            String start = scn.nextLine();
            if (start.equalsIgnoreCase("start")) {
                System.out.println("Hra začíná!");
                do {
                    playGame(isTwoPlayerMode()); // Spustí hru v režimu jednoho nebo dvou hráčů
                } while (playAgain()); // Zkontroluje, zda chce hráč hrát znovu
                break;
            } else {
                System.out.println("Nerozumím, zkus to znovu.");
            }
        }
    }

    private static void printIntroduction() throws InterruptedException {
        // Úvodní text hry
        System.out.println("Vítejte ve hře pexeso!");
        Thread.sleep(3000);
        System.out.println("Cílem hry je najít všechny dvojice stejných karet.");
        Thread.sleep(3000);
        System.out.println("Karty budete postupně otáčet a pokoušet se je zapamatovat.");
        Thread.sleep(3000);
        System.out.println("Vybírat budete stylu šachovnice. Souřadnice X bude značena jako A, B, C, D.");
        Thread.sleep(3000);
        System.out.println("Souřadnice Y bude značena 1, 2, 3, 4.");
        Thread.sleep(3000);
        System.out.println("Takže například můžete vybrat souřadnici 'A3' a vyberete první pole ve třetím řádku");
        Thread.sleep(3000);
        System.out.println("Pokud jste připraveni napiště 'start'.");
    }

    private static boolean isTwoPlayerMode() {
        // Rozhodnutí hry pro jednoho nebo dva hráče
        System.out.println("Chcete hrát ve dvou hráčích? (ano/ne)");
        while (true) {
            String input = scn.nextLine().toLowerCase();
            if (input.equals("ano")) {
                return true;
            } else if (input.equals("ne")) {
                return false;
            } else {
                System.out.println("Neplatný vstup, zadejte 'ano' nebo 'ne'.");
            }
        }
    }

    private static void playGame(boolean isTwoPlayer) throws InterruptedException {
        clearScreen(); // Vyčistí obrazovku
        String[][] cards = initializeCards(); // Inicializuje karty
        String[][] board = initializeBoard(); // Inicializuje herní desku
        int[] scores = {0, 0}; // Skóre pro dva hráče
        int currentPlayer = 0; // Aktuální hráč

        render(board); // Render hrací plochy
        if (isTwoPlayer) {
            playRoundsTwoPlayer(cards, board, scores, currentPlayer); // Spustí hru pro dva hráče
            announceWinner(scores); // Oznámení vítěze
        } else {
            playRoundsSolo(cards, board); // Spustí hru pro jednoho hráče
        }
    }

    private static void clearScreen() throws InterruptedException {
        // Vyčistí obrazovku
        Thread.sleep(1500);
        System.out.println("\n".repeat(50));
    }

    private static String[][] initializeCards() {
        // Inicializace karet
        List<String> cardList = new ArrayList<>(Arrays.asList(CARD_SYMBOLS));
        cardList.addAll(Arrays.asList(CARD_SYMBOLS));
        Collections.shuffle(cardList);

        String[][] cards = new String[BOARD_SIZE][BOARD_SIZE];
        int index = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cards[i][j] = cardList.get(index++);
            }
        }
        return cards;
    }

    private static String[][] initializeBoard() {
        // Inicializace herní desky
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], EMPTY_CELL);
        }
        return board;
    }

    private static void render(String[][] board) {
        // Render hrací desky
        for (String[] row : board) {
            System.out.println(String.join(" ", row));
        }
    }

    private static void playRoundsSolo(String[][] cards, String[][] board) throws InterruptedException {
        int pairsFound = 0;
        int totalPairs = (BOARD_SIZE * BOARD_SIZE) / 2;

        // Loop hry pro jednoho hráče
        while (pairsFound < totalPairs) {
            String select1 = getValidInput(board);
            int[] coords1 = parseCoordinates(select1);
            board[coords1[1]][coords1[0]] = cards[coords1[1]][coords1[0]];
            render(board);

            String select2 = getValidInput(board);
            int[] coords2 = parseCoordinates(select2);
            board[coords2[1]][coords2[0]] = cards[coords2[1]][coords2[0]];
            render(board);

            if (cards[coords1[1]][coords1[0]].equals(cards[coords2[1]][coords2[0]])) {
                clearScreen();
                pairsFound++;
            } else {
                clearScreen();
                board[coords1[1]][coords1[0]] = EMPTY_CELL;
                board[coords2[1]][coords2[0]] = EMPTY_CELL;
            }
            render(board);
        }
    }

    private static void playRoundsTwoPlayer(String[][] cards, String[][] board, int[] scores, int currentPlayer) throws InterruptedException {
        int pairsFound = 0;
        int totalPairs = (BOARD_SIZE * BOARD_SIZE) / 2;

        // Loop hry pro dva hráče
        while (pairsFound < totalPairs) {
            System.out.println("Hráč " + (currentPlayer + 1) + " je na řadě.");
            String select1 = getValidInput(board);
            int[] coords1 = parseCoordinates(select1);
            board[coords1[1]][coords1[0]] = cards[coords1[1]][coords1[0]];
            render(board);

            String select2 = getValidInput(board);
            int[] coords2 = parseCoordinates(select2);
            board[coords2[1]][coords2[0]] = cards[coords2[1]][coords2[0]];
            render(board);

            if (cards[coords1[1]][coords1[0]].equals(cards[coords2[1]][coords2[0]])) {
                clearScreen();
                scores[currentPlayer]++;
                pairsFound++;
            } else {
                clearScreen();
                board[coords1[1]][coords1[0]] = EMPTY_CELL;
                board[coords2[1]][coords2[0]] = EMPTY_CELL;
                currentPlayer = 1 - currentPlayer; // Přepne hráče
            }
            render(board);
        }
    }

    private static String getValidInput(String[][] board) {
        // Kontroluje vstup od hráče
        while (true) {
            String input = scn.nextLine().toUpperCase();
            if (isValidInput(input)) {
                int[] coords = parseCoordinates(input);
                if (board[coords[1]][coords[0]].equals(EMPTY_CELL)) {
                    return input;
                } else {
                    System.out.println("Tato karta už je odhalena. Vyberte jinou.");
                }
            } else {
                System.out.println("Neplatný vstup, zadejte znovu.");
            }
        }
    }

    private static boolean isValidInput(String input) {
        // Zkontroluje, zda je vstup platný
        return input.length() == 2 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D' && input.charAt(1) >= '1' && input.charAt(1) <= '4';
    }

    private static int[] parseCoordinates(String input) {
        // Převede vstup na souřadnice
        return new int[]{input.charAt(0) - 'A', input.charAt(1) - '1'};
    }

    private static boolean playAgain() {
        // Zjistí, zda chce hráč hrát znovu
        System.out.println("Chcete hrát znovu? (ano/ne)");
        while (true) {
            String input = scn.nextLine().toLowerCase();
            if (input.equals("ano")) {
                return true;
            } else if (input.equals("ne")) {
                System.out.println("Sbohem!");
                return false;
            } else {
                System.out.println("Neplatný vstup, zadejte 'ano' nebo 'ne'.");
            }
        }
    }

    private static void announceWinner(int[] scores) {
        // Oznámení vítěze
        System.out.println("Hra skončila!");
        System.out.println("Hráč 1 má " + scores[0] + " párů.");
        System.out.println("Hráč 2 má " + scores[1] + " párů.");
        if (scores[0] > scores[1]) {
            System.out.println("Hráč 1 vyhrál!");
        } else if (scores[1] > scores[0]) {
            System.out.println("Hráč 2 vyhrál!");
        } else {
            System.out.println("Je to remíza!");
        }
    }
}