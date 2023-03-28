import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    char[][] box = new char[4][4];
    for (int row = 1; row < box.length; row++) {
        for (int col = 1; col < box[row].length; col++) {
            box[row][col] = ' ';
        }
    }

    char player = 'X';
    boolean gameOver = false;
    try (Scanner scanner = new Scanner(System.in)) {
        while (!gameOver) {
          printBox(box);
          System.out.print("Player " + player + " Enter: ");
          int row = scanner.nextInt();
          int col = scanner.nextInt();
          System.out.println();

          if (box[row][col] == ' ') {
            box[row][col] = player; // place the element
            gameOver = haveWon(box, player);
            if (gameOver) {
              System.out.println("Player " + player + " has won: ");
            } else {
              player = (player == 'X') ? 'O' : 'X';
            }
          } else {
            System.out.println("Invalid move. Try again!");
          }
        }
    }
    printBox(box);
  }

  public static boolean haveWon(char[][] box, char player) {
    // check the rows
    for (int row = 1; row < box.length; row++) {
      if (box[row][1] == player && box[row][2] == player && box[row][3] == player) {
        return true;
      }
    }

    // check for col
    for (int col = 1; col < box[1].length; col++) {
      if (box[1][col] == player && box[2][col] == player && box[3][col] == player) {
        return true;
      }
    }

    // diagonal
    if (box[1][1] == player && box[2][2] == player && box[3][3] == player) {
      return true;
    }

    if (box[1][3] == player && box[2][2] == player && box[3][1] == player) {
      return true;
    }
    return false;
  }

  public static void printBox(char[][] box) {
    for (int row = 1; row < box.length; row++) {
      for (int col = 1; col < box[row].length; col++) {
        System.out.print(box[row][col] + " | ");
      }
      System.out.println();
    }
  }
}