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
http://www.lintcode.com/en/problem/search-a-2d-matrix/


4. Search a 2D Matrix II  
http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/


5. First Bad Version   
http://www.lintcode.com/en/problem/first-bad-version/


6. Find Peak Element   
http://www.lintcode.com/en/problem/find-peak-element/

