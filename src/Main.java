import java.util.*;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //Creates the sudoku board
        int[][] board = { {7, 6, 0, 8, 4, 0, 0, 0, 3},
                          {0, 0, 1, 0, 0, 0, 7, 0, 0},
                          {0, 2, 0, 0, 0, 0, 0, 5, 0},
                          {0, 0, 0, 0, 9, 0, 1, 0, 0},
                          {0, 0, 0, 7, 6, 0, 0, 0, 2},
                          {2, 0, 0, 0, 0, 0, 4, 3, 0},
                          {0, 5, 0, 0, 0, 0, 3, 0, 0},
                          {3, 1, 0, 0, 8, 0, 2, 9, 0},
                          {0, 4, 0, 3, 1, 9, 6, 7, 0} };


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

        //List<List> openSpaces = new ArrayList<>();

        //Sets is solved to false
        boolean isSolved = false;

        //Loops through the board until the board is solved
        while (!isSolved) {
            if (contains(board, 0) == false) {
                isSolved = true;
                break;
            }
            else {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (board[i][j] == 0) {
                            findPossibilities(board, possibilities, i, j);
                            setValue(board, possibilities, i, j);
                        }
                    }
                }
            }
        }


        //Prints out the sovled board
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
        setValue(board, possibilities, x, y);
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
    static void setValue(int[][] board, List[][] possibilities, int x, int y) {
        if (board[x][y] == 0 && possibilities[x][y].size() == 1) {
            board[x][y] = (int) possibilities[x][y].get(0);
        }
    }

    //Checks the whole board for a specified value
    static boolean contains(int[][] board, int value) {
        for (int[] row : board) {
            for (int element : row) {
                if (element == value) {
                    return true;
                }
            }
        }
        return false;
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