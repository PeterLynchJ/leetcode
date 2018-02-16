package LeetCodeRepo.leetcode.LeetCode;

import java.util.*;

public class LeetCode36_ValidSudoKu {
    /*private boolean isValidSudoku(char[][] board) {
        if (board == null) {
            return false;
        }
        HashSet<Character>[] rowMap = new HashSet[board.length], colMap = new HashSet[board[0].length], squareMap = new HashSet[board.length * board[0].length / 9];
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                int squareNum = row / 3 + col / 3 * 3;
                if (rowMap[col] == null) {
                    rowMap[col] = new HashSet<>();
                }
                if (colMap[row] == null) {
                    colMap[row] = new HashSet<>();
                }
                if (squareMap[squareNum] == null) {
                    squareMap[squareNum] = new HashSet<>();
                }
                if (board[col][row] == '.') {
                    continue;
                }
                if (squareMap[squareNum].contains(board[col][row]) || rowMap[col].contains(board[col][row]) || colMap[row].contains(board[col][row])) {
                    return false;
                }
                squareMap[squareNum].add(board[col][row]);
                rowMap[col].add(board[col][row]);
                colMap[row].add(board[col][row]);
            }
        }
        return true;
    }*/
    boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean [] rowMap = new boolean [9];
            boolean [] colMap = new boolean [9];
            boolean [] squareMap = new boolean [9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int rowIndex = board[i][j] - '1';
                    if(rowMap[rowIndex]) {
                        return false;
                    } else {
                        rowMap[rowIndex] = true;
                    }
                }
                if(board[j][i] != '.') {
                    int colIndex = board[j][i] - '1';
                    if(colMap[colIndex]) {
                        return false;
                    } else {
                        colMap[colIndex] = true;
                    }
                }
                if(board[i / 3 * 3 + j / 3][j % 3 + i % 3 * 3] != '.') {
                    int squareIndex = board[i / 3 * 3 + j / 3][j % 3 + i % 3 * 3] - '1';
                    if(squareMap[squareIndex]) {
                        return false;
                    } else {
                        squareMap[squareIndex] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode36_ValidSudoKu test = new LeetCode36_ValidSudoKu();
        System.out.println(test.isValidSudoku(new char[][]{{'.', '8', '7', '6', '5', '4', '3', '2', '1'}, {'2', '.', '.', '.', '.', '.', '.', '.', '.'}, {'3', '.', '.', '.', '.', '.', '.', '.', '.'}, {'4', '.', '.', '.', '.', '.', '.', '.', '.'}, {'5', '.', '.', '.', '.', '.', '.', '.', '.'}, {'6', '.', '.', '.', '.', '.', '.', '.', '.'}, {'7', '.', '.', '.', '.', '.', '.', '.', '.'}, {'8', '.', '.', '.', '.', '.', '.', '.', '.'}, {'9', '.', '.', '.', '.', '.', '.', '.', '.'}}));
    }
}
