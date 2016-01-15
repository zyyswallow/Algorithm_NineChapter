1. Search for a range  
Given a sorted array of n integers, find the starting and ending position of a given target value.
If the target is not found in the array, return [-1, -1].
http://www.lintcode.com/en/problem/search-for-a-range/

public class Solution {
    /** 
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        int[] res = new int[]{-1, -1};   // 注意写法！
        if (A == null || A.length == 0){
            return res;
        }
        
        // find left range
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] == target){
            res[0] = start;
        } else if (A[end] == target){
            res[0] = end;
        } else {
            return res;
        }
        
        // find right range
        start = 0;
        end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] > target){
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[end] == target){
            res[1] = end;
        } else if (A[start] == target){
            res[1] = start;
        } else {
            return res;
        }
        
        return res;
        
    }
}


2. Search Insert Position  
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.
You may assume NO duplicates in the array.
http://www.lintcode.com/en/problem/search-insert-position/

public class Solution {
    /** 
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0){  // 必须要加上
            return 0;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                return mid;
            } else if (A[mid] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        if (target <= A[start]){
            return start;
        } else if (target <= A[end]){
            return end;
        } else {
            return end + 1;
        }
    }
}


3. Search a 2D Matrix  
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:
  1.Integers in each row are sorted from left to right.
  2.The first integer of each row is greater than the last integer of the previous row.
http://www.lintcode.com/en/problem/search-a-2d-matrix/

public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (matrix[mid / col][mid % col] == target){   // 注意是用col，不是row
                return true;
            } else if (matrix[mid / col][mid % col] < target){
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[start / col][start % col] == target || matrix[end / col][end % col] == target){
            return true;
        } else {
            return false;
        }
    }
}


4. Search a 2D Matrix II  
Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.
This matrix has the following properties:
  1.Integers in each row are sorted from left to right.
  2.Integers in each column are sorted from up to bottom.
  3.No duplicate integers in each row or column.
http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/

public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < col){
            if (matrix[i][j] == target){
                count++;
            }
            if (matrix[i][j] < target){
                j++;
            } else {
                i--;
            }
        }
        return count;
    }
}


5. First Bad Version   
http://www.lintcode.com/en/problem/first-bad-version/

class Solution {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int start = 1;
        int end = n;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (SVNRepo.isBadVersion(mid)){
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (SVNRepo.isBadVersion(start)){
            return start;
        } else {
            return end;
        }
    }
}

6. Find Peak Element  

There is an integer array which has the following features:
   1.The numbers in adjacent positions are different.
   2.A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
We define a position P is a peek if: A[P] > A[P-1] && A[P] > A[P+1]
Find a peak element in this array. Return the index of the peak.

http://www.lintcode.com/en/problem/find-peak-element/

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if (A == null || A.length == 0){
            return -1;
        } else if (A.length == 1){
            return 0;
        }
        int start = 1;
        int end = A.length - 2;  // A.length == 2的情况 也包含在内，无需单独讨论
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]){
                return mid;
            } else if (A[mid] > A[mid - 1]){
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] > A[end]){
            return start;
        } else {
            return end;
        }
    }
}


