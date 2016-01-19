1. Triangle
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
http://www.lintcode.com/en/problem/triangle/

public class Solution {
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0){
            return 0;
        }
        int len = triangle.length;
        int[][] sum = new int[len][len];
        sum[0][0] = triangle[0][0];
        for (int i = 1; i < len; i++){
            sum[i][0] = sum[i - 1][0] + triangle[i][0];
            for(int j = 1; j < i; j++){
                sum[i][j] = Math.min(sum[i - 1][j - 1], sum[i - 1][j]) + triangle[i][j];
            }
            sum[i][i] = sum[i - 1][i - 1] + triangle[i][i];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++){
            min = Math.min(min, sum[len - 1][i]);
        }
        return min;
    }
}


2. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
which minimizes the sum of all numbers along its path.
http://www.lintcode.com/en/problem/minimum-path-sum/

public class Solution {
    public int minPathSum(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] sum = new int[row][col];
        sum[0][0] = grid[0][0];
        for (int i = 1; i < col; i++){
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++){
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[row - 1][col - 1];
    }
}


3. Unique Paths
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
http://www.lintcode.com/en/problem/unique-paths/


