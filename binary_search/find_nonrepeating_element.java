
public class FindNonRepeatingElement {

    // Function to find the non-repeating element in a sorted array
    public static int findNonRepeatingElement(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];
        if (n % 2 == 0) return 0; // Invalid input as per problem statement

        int low = 0, high = n - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid is even, the pair should be at mid and mid+1
            if (mid % 2 == 0) {
                if (arr[mid] == arr[mid + 1]) {
                    low = mid + 2;
                } else {
                    high = mid;
                }
            } else {
                // If mid is odd, the pair should be at mid-1 and mid
                if (arr[mid - 1] == arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return arr[low];
    }

    // Main method to run some test cases
    public static void main(String[] args) {
        int[][] testCases = {
            {1, 1, 2, 3, 3, 4, 4},
            {5, 5, 6, 6, 7, 8, 8},
            {9},
            {10, 10, 11, 11, 12, 12, 13},
            {21, 22, 22, 23, 23, 24, 24}
        };

        for (int[] test : testCases) {
            System.out.println("Unique element in array: " + findNonRepeatingElement(test));
        }
    }
}


/*
Walkthrough and Observations

1. Problem Understanding
- You're given a sorted array where every element appears exactly twice except one element that appears once.
- Goal: Find the single element in O(log n) time.
- Observation: The array is sorted, which hints at binary search.

2. Initial Thought Process
- At first glance, a linear scan would work, but it's O(n), which doesn’t meet the time constraint.
- Since the array is sorted and only one element breaks the "pair" pattern, binary search seems like the right tool.

3. Key Insight
- In a valid pairing structure (before the unique element), each pair starts at an even index:
  - Index 0 and 1 → same
  - Index 2 and 3 → same
  - And so on...
- Once the unique element appears, the pairing shifts — breaking this pattern.

4. Common Pitfall and Debugging Insight
- Initially thought: since array length is odd, mid might always be odd.
  - This turned out to be incorrect.
  - Wrote out index patterns for arrays of size 3, 5, 7, 9, etc.
  - Verified that mid can be even or odd, so we need to handle both cases.

5. Core Logic Based on Index Parity
- If mid is even:
  - If arr[mid] == arr[mid + 1] → the unique element lies to the right
    - Set low = mid + 2
  - Else → unique element lies at or before mid
    - Set high = mid

- If mid is odd:
  - If arr[mid] == arr[mid - 1] → the unique element lies to the right
    - Set low = mid + 1
  - Else → unique element lies before mid
    - Set high = mid - 1

6. Final Result
- This binary search approach keeps halving the search space and guarantees O(log n) time.
- Once low == high, we've found the single element.

7. Why This Works
- It smartly leverages the index parity and sorted structure.
- The unique element is the exact point where the even-indexed pairing breaks.
- Every move in the binary search keeps the invariant intact.

This file is self-contained and can be compiled and run directly.
*/
