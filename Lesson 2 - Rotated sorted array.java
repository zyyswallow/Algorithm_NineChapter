1. Find minimum in rotate sorted array  
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element. 
http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num[0] < num[num.length - 1]){   // 此题的特殊情况就是类似[1,2,3]
            return num[0];
        }
        int start = 0;
        int end = num.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (num[mid] > num[0]){
                start = mid;
            }
            if (num[mid] < num[num.length - 1]){
                end = mid;
            }
        }
        return Math.min(num[start], num[end]);
    }
}


2. Find minimum in rotate sorted array II    
http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array-ii/




3. Search in rotated sorted array   
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/



4. Search in rotated sorted array II   
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array-ii/



5. Median of two sorted arrays   
http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/


6. Recover rotated sorted array    
http://www.lintcode.com/en/problem/recover-rotated-sorted-array/



7. Rotate string    
http://www.lintcode.com/en/problem/rotate-string/



8. Reverse words in a string   
http://www.lintcode.com/en/problem/reverse-words-in-a-string/




