public class HouseRobber {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // dp[i] represents the maximum amount of money that can be robbed up to house i
        int[] dp = new int[n];

        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill the dp array
        for (int i = 2; i < n; i++) {
            // At each house, we have two choices:
            // 1. Rob the current house and add its value to the amount robbed two houses
            // back
            // 2. Skip the current house and take the amount from the previous house
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test cases
        int[] test1 = { 1, 2, 3, 1 };
        System.out.println("Test 1: " + rob(test1)); // Expected: 4

        int[] test2 = { 2, 7, 9, 3, 1 };
        System.out.println("Test 2: " + rob(test2)); // Expected: 12

        int[] test3 = { 2, 1, 1, 2 };
        System.out.println("Test 3: " + rob(test3)); // Expected: 4
    }
}