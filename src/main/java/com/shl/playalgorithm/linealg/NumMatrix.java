package com.shl.playalgorithm.linealg;

/**
 * 二维数组前缀和
 */
public class NumMatrix {

    private int[][] prematrix;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        prematrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prematrix[i][j] = prematrix[i - 1][j] + prematrix[i][j - 1] + matrix[i - 1][j - 1]
                        - prematrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int x1, int y1, int x2, int y2) {
        return prematrix[x2 + 1][y2 + 1]
                - prematrix[x1][y2 + 1]
                - prematrix[x2 + 1][y1]
                + prematrix[x1][y1];
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
