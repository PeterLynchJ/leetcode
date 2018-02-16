package LeetCodeRepo.leetcode.LeetCode;

public class SolveSudoKu {
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    boolean solve(char[][] board, int row, int col) {
        for(int i = row; i < board.length; i++) {
            col = 0;
            for(int j = col; j < board[0].length; j++) {
                if(board[i][j] == '.') {
                    for(char k = '1'; k <= '9'; k++) {
                        // k not used
                        if(isValid(board, i, j, k)) {
                            board[i][j] = k;
                            /*for(int a = 0; a < board.length; a++) {
                                for(int b = 0; b < board[0].length; b++) {
                                    System.out.print(board[a][b] + " ");
                                }
                                System.out.print("  ");
                            }*/
                            //System.out.println("Attention: " + i + ", " + j);
                            if(j + 1 < board[0].length) {
                                if(solve(board, i, j + 1)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            } else if(i + 1 < board.length) {
                                if(solve(board, i + 1, 0)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            } else {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValid(char[][] board, int i, int j, char k) {
        // check row
        for(int col = 0; col < board[i].length; col++) {
            if(board[i][col] == k) {
                return false;
            }
        }
        // check colum
        for(int row = 0; row < board.length; row++) {
            if(board[row][j] == k) {
                return false;
            }
        }
        // check square
        for(int square = 0; square < board[i].length; square++) {
            if(board[square / 3 + i / 3 * 3][square % 3 + j / 3 * 3] == k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
        SolveSudoKu soduku = new SolveSudoKu();
        soduku.solveSudoku(board);
        for(int a = 0; a < board.length; a++) {
            for (int b = 0; b < board[0].length; b++) {
                System.out.print(board[a][b] + " ");
            }
            System.out.println("  ");
        }
    }
}

