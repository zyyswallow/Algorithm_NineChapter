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

public class Solution {
    public int uniquePaths(int m, int n) {
        // write your code here 
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}


4. Unique Paths II
Follow up for "Unique Paths":
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
http://www.lintcode.com/en/problem/unique-paths-ii/

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < col; i++){
            if (obstacleGrid[0][i] == 0){
                dp[0][i] = 1;
            } else {
                break;  // 利用dp[][]默认初始值为0的特性，当遇到一个1时就跳出循环
            }
        }
        for (int i = 0; i < row; i++){  // 这里也必须从0开始，防止数组左上值为1的情况
            if (obstacleGrid[i][0] == 0){
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}


5. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
http://www.lintcode.com/en/problem/climbing-stairs/

public class Solution {
    public int climbStairs(int n) {
        // write your code here
        if (n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}


6. Jump Game
http://www.lintcode.com/en/problem/jump-game/





