package myapps;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author i531869
 * @Date 2021/12/29 10:54
 * @Version 1.0
 */
public class Test2 {
  public static ListNode merge(ListNode head1, ListNode head2) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while (head1 != null || head2 != null) {
      if (head1 == null) {
        cur.next = head2;
        break;
      } else if (head2 == null) {
        cur.next = head1;
        break;
      } else if (head1.val < head2.val) {
        cur.next = head1;
        head1 = head1.next;
      } else {
        cur.next = head2;
        head2 = head2.next;
      }
      cur = cur.next;
    }
    return dummy.next;
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;
    int[] arr = new int[n1 + n2];
    int lo = 0, hi = 0;
    //利用归并排序将nums1和nums2归并到arr数组中，且保持升序
    for (int i = 0; i < n1 + n2; i++) {
      if (lo >= n1)
        arr[i] = nums2[hi++];
      else if (hi >= n2)
        arr[i] = nums1[lo++];
      else if (nums1[lo] < nums2[hi])
        arr[i] = nums1[lo++];
      else
        arr[i] = nums2[hi];
    }
    int mid = (n1 + n2) / 2;
    if (arr.length % 2 == 1) {
      return arr[mid];
    }

    return (arr[mid - 1] + arr[mid]) / 2.0;
  }

  public int lengthOfLIS(int[] nums) {

    //    dp[i]:前i个数单调递增的最大长度;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int res = 1;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      res = Math.max(res, dp[i]);
    }
    return res;
  }

  public static void main(String[] args) {
    StringBuffer sb = new StringBuffer();
    new Test2().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    new Test2().findMedianSortedArrays(new int[]{1, 2}, new int[]{3});
    int[] cost = {1, 5, 11, 5};
    int a = 12;
    System.out.println(12 / 5.0);
    new Test2().canPartition(cost);
    new Test2().combinationSum4(new int[]{1, 2, 3}, 4);
    ListNode L1 = new ListNode(9);
    ListNode L2 = new ListNode(1);
    ListNode temp = L2;
    for (int i = 0; i < 9; i++) {
      temp.next = new ListNode(9);
      temp = temp.next;
    }

    StringBuilder s = new StringBuilder();
    s.reverse();
    addTwoNumbers(L1, L2);

    System.out.println(Integer.MAX_VALUE);

    lengthOfLongestSubstring("pwwkew");

    int[] arr = {10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3};
    int t = 19;
    System.out.println(new Test2().numSubarrayProductLessThanK(arr, t));
  }

  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int res = 0;
    if (k == 0)
      return res;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i; j < nums.length; j++) {
        if (lessK(nums, i, j, k)) {
          res++;
        } else
          break;
      }
    }
    return res;
  }

  private boolean lessK(int[] nums, int lo, int hi, int target) {
    int prod = 1;
    for (int i = lo; i <= hi; i++) {
      prod *= nums[i];
    }
    if (prod < target) {
      return true;
    }
    return false;
  }

  public static int lengthOfLongestSubstring(String s) {
    int lo = 0, hi = 0;
    //         滑动窗口
    Set<Character> win = new HashSet();
    int res = 0;
    while (lo < s.length()) {
      if (lo != 0) {
        win.remove(s.charAt(lo - 1));
      }
      while (hi < s.length() && !win.contains(s.charAt(hi))) {
        win.add(s.charAt(hi));
        hi++;
      }
      res = Math.max(res, hi - lo);
      lo++;
    }
    return res;
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1.val == 0 && l2.val == 0) {
      return l1;
    }
    //逆序问题,首先想到用堆栈
    Deque<Integer> stack1 = new ArrayDeque<>();
    Deque<Integer> stack2 = new ArrayDeque<>();
    while (l1 != null) {
      stack1.push(l1.val);
      l1 = l1.next;
    }
    int sum1 = 0;
    while (!stack1.isEmpty()) {
      sum1 = sum1 * 10 + stack1.pop();
    }
    while (l2 != null) {
      stack2.push(l2.val);
      l2 = l2.next;
    }
    int sum2 = 0;
    while (!stack2.isEmpty()) {
      sum2 = sum2 * 10 + stack2.pop();
    }
    int sum3 = sum1 + sum2;
    Deque<Integer> deque = new ArrayDeque<>();
    while (sum3 != 0) {
      deque.offer(sum3 % 10);
      sum3 /= 10;
    }
    ListNode head = new ListNode(-1);
    ListNode cur = head;
    while (!deque.isEmpty()) {
      cur.next = new ListNode(deque.poll());
      cur = cur.next;
    }
    // int carry =0;
    // while (stack1 != null || stack2 != null || carry != 0) {
    //     int x1 = stack1.pop() + stack2.pop() + carry;
    //     x1 = x1 % 10;
    //     carry = x1 / 10;
    //     cur.next = new ListNode(x1);
    //     cur = cur.next;
    // }
    return head.next;

  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public int combinationSum4(int[] nums, int target) {
    // dp[i]: 和为target元素组合个数,dp[i]=dp[i-num]
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i <= target; i++) {
      for (int num : nums) {
        if (i >= num) {
          dp[i] += dp[i - num];
        }
      }
    }
    return dp[target];
  }

  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum % 2 == 1) {
      return false;
    }
    boolean[] dp = new boolean[sum / 2 + 1];
    dp[0] = true;
    for (int i = 1; i <= nums.length; i++) {
      for (int j = sum / 2; j > 0; j--) {
        if (!dp[j] && j >= nums[i - 1]) {
          dp[j] = dp[j - nums[i - 1]];
        }
      }
    }
    return dp[sum / 2];
  }
}
