import java.util.*;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //Creates the sudoku board
        int[][] board = { {0, 0, 0, 0, 0, 0, 0, 6, 0},
                          {8, 0, 2, 0, 0, 0, 0, 4, 0},
                          {0, 0, 0, 0, 7, 0, 8, 0, 9},
                          {0, 0, 0, 0, 9, 0, 0, 0, 0},
                          {0, 5, 0, 0, 0, 0, 1, 3, 0},
                          {0, 0, 0, 3, 2, 0, 0, 0, 8},
                          {5, 0, 1, 6, 0, 3, 4, 0, 0},
                          {9, 6, 0, 4, 0, 0, 0, 0, 0},
                          {0, 7, 0, 0, 0, 0, 0, 5, 0} };


        //Creates a 2D array of lists for the possibilities
        List[][] possibilities = new List[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
                possibilities[i][j] = list;
            }
        }

        //Declares openSpaces list
        List<List> openSpaces = new ArrayList<>();
        findOpenSpaces(board, openSpaces);


        //Loops through the board until the board is solved
        while (openSpaces.size() != 0) {
            boolean madeProgress = false;
            for (int i = 0; i < openSpaces.size(); i++) {
                int x = (int) openSpaces.get(i).get(0);
                int y = (int) openSpaces.get(i).get(1);
                findPossibilities(board, possibilities, x, y);
                if (setValue(board, possibilities, x, y)) {
                    openSpaces.remove(i);
                    i--;
                    madeProgress = true;
                }
            }
            if (!madeProgress) {
                System.out.println("Can't solve puzzle with current input");
                System.out.println();
                break;
            }
        }


        //Prints out the solved board
        printBoard(board);

       //Calculates time elapsed and prints it to the console
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println();
        System.out.println(elapsedTime + " Milliseconds");
    }

    //Finds the different possibilities for the specified square and sets the value if
    //there is only one possibility
    static void findPossibilities(int[][] board, List[][] possibilities, int x, int y) {
        checkRowCol(board, possibilities, x, y);
        checkBox(board, possibilities, x, y);
    }

    //Checks the row and column for all numbers already being used and removes them from the possibilities list
    static void checkRowCol(int[][] board, List[][] possibilities, int x, int y) {
       for (int i = 0; i < 9; i++) {
           if (board[x][i] != 0) {
               possibilities[x][y].remove(Integer.valueOf(board[x][i]));
           }
           if (board[i][y] != 0) {
               possibilities[x][y].remove(Integer.valueOf(board[i][y]));
           }
       }


    }


    //Checks the 3x3 box for all numbers already being used and removes them from the possibilities list
    static void checkBox(int[][] board, List[][] possibilities, int x, int y) {
        if ((x >= 0 && x <= 2) && (y >= 0 && y <= 2)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 3 && x <= 5) && (y >= 0 && y <= 2)) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 6 && x <= 8) && (y >= 0 && y <= 2)) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 0 && x <= 2) && (y >= 3 && y <= 5)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 3 && x <= 5) && (y >= 3 && y <= 5)) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 6 && x <= 8) && (y >= 3 && y <= 5)) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 0 && x <= 2) && (y >= 6 && y <= 8)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 3 && x <= 5) && (y >= 6 && y <= 8)) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
        else if ((x >= 6 && x <= 8) && (y >= 6 && y <= 8)) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] != 0) {
                        possibilities[x][y].remove(Integer.valueOf(board[i][j]));
                    }
                }
            }
        }
    }

    //Sets the value of the square if there is only one possible number
    static boolean setValue(int[][] board, List[][] possibilities, int x, int y) {
        if (board[x][y] == 0 && possibilities[x][y].size() == 1) {
            board[x][y] = (int) possibilities[x][y].get(0);
            return true;
        }
        return false;
    }

    //Finds all open spaces on the board
    static void findOpenSpaces(int[][] board, List openSpaces) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    List<Integer> location = new ArrayList<>();
                    location.add(i);
                    location.add(j);
                    openSpaces.add(location);
                }
            }
        }
    }

   //Prints the board to the console
    static void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 3; j < 6; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 6; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.print("-----------");
        System.out.println();

        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 3; j < 6; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 6; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.print("-----------");
        System.out.println();

        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 3; j < 6; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("|");
            for (int j = 6; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

}