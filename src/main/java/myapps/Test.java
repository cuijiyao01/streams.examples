package myapps;
//
// 运用所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制 。
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Author i531869
 * @Date 11/18/21 5:04 PM
 * @Version 1.0
 */
public class Test {
  //自顶向下
  public static void mergeSort(int[] arrM, int lo, int hi){
    if (lo < hi) {
      int mid = lo + (hi-lo)/2;
      mergeSort(arrM, lo, mid);
      mergeSort(arrM, mid + 1, hi);
      mergeAll(arrM, lo, mid, hi);
    }
  }

  private static void mergeAll(int[] arrM, int lo, int mid, int hi) {
    int[] aux = new int[arrM.length];
    int left = lo, right=mid+1;
    for (int k = lo; k <= hi; k++) {
      aux[k] = arrM[k];
    }
    for (int k = lo; k <= hi; k++) {
      if(right>hi)  arrM[k] = aux[left++];
      else if(left>mid) arrM[k] = aux[right++];
      else if(aux[left]<aux[right]) arrM[k] = aux[left++];
      else arrM[k] = aux[right++];
    }

  }
  List<List<String>> res = new ArrayList<>();
  public void partition(String s) {
    helper(s, 0, new ArrayList<String>());
    String[][] strings = new String[res.size()][];
    int i=0;
    for (List<String> subList : res) {
      String[] s2 = new String[subList.size()];
      for (int j = 0; j < subList.size(); j++) {
        s2[j] = subList.get(j);
      }
      strings[i++]=s2;
    }
  }

  private void helper(String s, int index, List<String> subList){
    if(index == s.length()){
      res.add(new ArrayList<>(subList));
    }
    for(int i=index;i<s.length();i++){
      String subStr = s.substring(index,i+1);
      if(isPan(subStr)){
        subList.add(subStr);
        helper(s, i+1, subList);
        subList.remove(subList.size()-1);
      }
    }
  }

  private boolean isPan(String str){
    for(int i=0,j=str.length()-1;i<j;i++,j--){
      if(str.charAt(i)!=str.charAt(j)){
        return false;
      }
    }
    return true;
  }

  public int minCostClimbingStairs(int[] cost) {
    //dp[i]表示爬到第i层花费的最小体力值,dp[n]表示顶层
    int n = cost.length;
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 0;
    //    状态转移方程dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1])
    for (int i = 2; i <= n; i++) {
      dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
    }
    return dp[n];
  }

  public int rob(int[] nums) {
    //dp[i]表示偷窃第i号房间的最高金额,
    //dp[0]=nums[0], dp[1]=Math.max(nums[0],nums[1]),
    //dp[2] = Math.max(dp[0]+nums[2],dp[1])
    //dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
    int len = nums.length;
    if(len==1) return nums[0];
    int[] dp = new int[len];
    dp[0] = nums[0];
    dp[1] = Math.max(dp[0],nums[1]);
    for (int i = 2; i < len; i++) {
      dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
    }
    return dp[len-1];

  }

  public int lenLongestFibSubseq(int[] arr) {
    //    dp[i][j]: arr[i]和arr[j]结尾的最长斐波那契子序列的长度
    int n = arr.length;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      map.put(arr[i],i);
    }
    int[][] dp = new int[n][n];
    int res =0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (map.containsKey(arr[j] - arr[i]) && map.get(arr[j] - arr[i]) < i) {
          // dp[i][j] = Math.max(dp[i][j], dp[k][i] + 1);
          res = Math.max(res,dp[i][j]+2);
        }
      }
    }
    return res;
  }
  public int minimumTotal(List<List<Integer>> triangle) {
    int n = triangle.size();
    int[] dp = new int[n+1];
    for(int i=n-1;i>=0;i--){
      for(int j=0;j<=i;j++){
        dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> sub = new ArrayList<>();
    sub.add(2);

    List<Integer> sub2 = new ArrayList<>();
    sub2.add(3);
    sub2.add(4);

    List<Integer> sub3 = new ArrayList<>();
    sub3.add(6);
    sub3.add(5);
    sub3.add(7);


    List<Integer> sub4 = new ArrayList<>();
    sub4.add(4);
    sub4.add(1);
    sub4.add(8);
    sub4.add(3);

    list.add(sub);
    list.add(sub2);
    list.add(sub3);
    list.add(sub4);

    new Test().minimumTotal(list);

    System.out.println(1 + (2 == 1 ? 1 : 0));
    int[] cost = {1,3,7,11,12,14,18};
    new Test().lenLongestFibSubseq(cost);
    String[][] strings = new String[4][];
    Arrays.fill(cost, 1);

    new Test().partition("aab");
    Integer[] arrM = {2,6,3,5,1,19,7,12,8,4};
    new ArrayList<Integer>(Arrays.asList(arrM));
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> ss= Arrays.asList(arrM);
    // mergeSort(arrM, 0, arrM.length - 1);
    // System.out.println(Arrays.toString(arrM));
    // System.out.println("=========");
    int max = Integer.MIN_VALUE;
    for (int x : arrM) {
      max = Math.max(max, x);
    }
    int[] arrCal = new int[max+1];
    for (int i = 0; i < arrM.length; i++) {
      arrCal[arrM[i]]++;
    }

    int j=0;
    for (int i = 0; i < arrCal.length; i++) {
      while (arrCal[i]-->0){
        arrM[j++] = i;
      }
    }
    System.out.println(Arrays.toString(arrM));

    // calcSort();


    StringBuffer sb = new StringBuffer();
    sb.append("hello");
    sb.deleteCharAt(4);
    System.out.println(sb);
    int[] arr = new int[]{39, 28, 5, 87};
    for (int num : arr) {
      System.out.println(num);
    }
    new ArrayList<>(new ArrayList<>());
    int[] arr1 = {2,6,3,5,1,19,7,12,8,4};
    int[] arr2 = {2,1,4,3,9,6};

    Arrays.stream(arr1).sorted();
    Integer[] arr3 = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
  }

  public int findKthLargest(int[] nums, int k) {
    int len = nums.length;
    quickSort2(nums, 0, len-1);
    return nums[len - k];
  }

  private void quickSort2(int[] nums, int lo, int hi) {
    if (lo < hi) {
      int mid = partition(nums, lo, hi);
      quickSort2(nums, lo, mid-1);
      quickSort2(nums, mid+1, hi);
    }
  }

  private int partition2(int[] nums, int lo, int hi) {
    int pivot = nums[lo];
    while (lo < hi) {
      while (nums[hi]>pivot);
      nums[lo] = nums[hi];
      while (nums[lo++]<=pivot);
      nums[hi] = nums[lo];
    }
    nums[lo] = pivot;
    return lo;
  }


  public static int[][] merge(int[][] intervals) {

    Arrays.sort(intervals, (o1, o2) ->
        o1[0] - o2[0]);
    List<int[]> merged = new ArrayList<>();
    for (int[] arr : intervals) {
      int[] tail = merged.get(merged.size()-1);
      if(merged.isEmpty() || tail[1]<arr[0]){
        merged.add(arr);
      }else{
        tail[1]=Math.max(tail[1],arr[1]);
      }
    }
    int[][] res = new int[merged.size()][2];
    int i=0;
    for (int[] arr : merged) {
      res[i++] = arr;
    }
    return res;
  }

  public static int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num,map.getOrDefault(num,0)+1);
    }
    List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>() {
      @Override
      public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        return o1.getValue() - o2.getValue();
      }
    });
    int[] res = new int[k];
    int j=0;
    for (int i = map.size() - k; i < map.size(); i++) {
      res[j++] = list.get(i).getKey();
    }
    return res;

  }

  private static void quickSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = partition(arr, left, right);
    quickSort(arr, left, mid - 1);
    quickSort(arr, mid + 1, right);
  }

  private static int partition(int[] arr, int left, int right) {
    int pivot = arr[left];
    while (left < right) {
      while (left<right && arr[right]>pivot) right--;
      arr[left] = arr[right];
      while (left<right && arr[left]<=pivot) left++;
      arr[right] = arr[left];
    }
    arr[left] = pivot;
    return left;
  }

  public void bubbleSort(int[] arr) {
    int len = arr.length;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuffer sb = new StringBuffer();
    serialize(root, sb);
    return sb.toString();
  }

  private void serialize(TreeNode root, StringBuffer sb) {
    if (root == null) {
      sb.append("null,");
    } else {
      sb.append(root.val + ",");
      serialize(root.left, sb);
      serialize(root.right, sb);
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null)
      return null;
    String[] strs = data.split(",");
    Deque<String> queue = new ArrayDeque<String>(Arrays.asList(strs));
    queue.removeLast();
    return deserialize(queue);
  }

  private TreeNode deserialize(Deque<String> queue) {
    String str = queue.poll();
    if (str.equals("null")) {
      return null;
    } else {
      TreeNode node = new TreeNode(Integer.valueOf(str));
      node.left = deserialize(queue);
      node.right = deserialize(queue);
      return node;
    }
  }

  public int maximalRectangle(String[] matrix) {
    if (matrix == null || matrix.length == 0)
      return 0;
    int rows = matrix.length;
    int cols = matrix[0].length();
    int[] heights = new int[cols + 2];
    int res = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        heights[j + 1] = matrix[i].charAt(j) == '1' ? ++heights[j + 1] : 0;
      }
      res = Math.max(res, largestRect(heights));
    }
    return res;
  }

  private int largestRect(int[] heights) {

    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(0);
    int area = 0;
    for (int i = 1; i < heights.length; i++) {
      while (heights[i] < heights[stack.peek()]) {
        int height = heights[stack.pop()];
        int width = i - stack.peek() - 1;
        area = Math.max(area, height * width);
      }
      stack.push(i);
    }
    return area;
  }

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> s = new Stack<>();
    int p = 0;
    while (p < asteroids.length) {
      if (s.empty() || s.peek() < 0 || asteroids[p] > 0) {
        s.push(asteroids[p]);
      } else if (s.peek() <= -asteroids[p]) {
        if (s.pop() < -asteroids[p]) {
          continue;
        }
      }
      p++;
    }
    int[] ret = new int[s.size()];
    for (int i = ret.length - 1; i >= 0; i--) {
      ret[i] = s.pop();
    }
    return ret;
  }

  public String minWindow(String s, String t) {
    int m = s.length();
    int n = t.length();
    if (m < n)
      return "";
    int[] tArr = new int[128];
    for (int i = 0; i < n; i++) {
      tArr[t.charAt(i)]++;
    }
    int[] win = new int[128];
    int left = 0;
    int minLen = Integer.MAX_VALUE;
    int ansL = -1;
    for (int right = 0; right < m; right++) {
      char ch = s.charAt(right);
      if (tArr[ch] > 0) {
        win[ch]++;
      }
      while (left <= right && include(win, tArr)) {
        if (right - left + 1 < minLen) {
          minLen = right - left + 1;
          ansL = left;
        }
        // if (win[s.charAt(left)] > 0) {
        win[s.charAt(left)]--;
        // }
        left++;

      }
    }
    return ansL == -1 ? "" : s.substring(ansL, ansL + minLen);
  }

  private boolean part2(int[] nums){
    int sum =0;
    for (int num : nums) {
      sum += num;
    }
    if ((sum & 1) == 1) {
      return false;
    }
    //dp[j]:在nums中是否存在和为j的若干元素
    //dp[j]: dp[j-1](不放入当前商品) || dp[j-nums[i]](放入当前商品)
    boolean[] dp = new boolean[sum / 2 + 1];
    dp[0] = true;
    for (int i = 1; i < nums.length; i++) {
      for (int j = sum / 2; j > 0; j--) {
        if (j < nums[i]) {
          //不能放入
          dp[j] = dp[j - 1];
        }else{
          //可以放也可以不放入
          dp[j] = dp[j - 1] || dp[j - nums[i]];
        }
      }
    }
    return dp[sum / 2];
  }

  private boolean include(int[] win, int[] tArr) {
    for (int i = 0; i < tArr.length; i++) {
      if (win[i] < tArr[i]) {
        return false;
      }
    }
    return true;
  }

  public class LRUCache {
    class DLinkedNode {
      int key;
      int value;
      DLinkedNode prev;
      DLinkedNode next;

      public DLinkedNode() {
      }

      public DLinkedNode(int _key, int _value) {
        key = _key;
        value = _value;
      }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
      this.size = 0;
      this.capacity = capacity;
      // 使用伪头部和伪尾部节点
      head = new DLinkedNode();
      tail = new DLinkedNode();
      head.next = tail;
      tail.prev = head;
    }

    public int get(int key) {
      DLinkedNode node = cache.get(key);
      if (node == null) {
        return -1;
      }
      // 如果 key 存在，先通过哈希表定位，再移到头部
      moveToHead(node);
      return node.value;
    }

    public void put(int key, int value) {
      DLinkedNode node = cache.get(key);
      if (node == null) {
        // 如果 key 不存在，创建一个新的节点
        DLinkedNode newNode = new DLinkedNode(key, value);
        // 添加进哈希表
        cache.put(key, newNode);
        // 添加至双向链表的头部
        addToHead(newNode);
        ++size;
        if (size > capacity) {
          // 如果超出容量，删除双向链表的尾部节点
          DLinkedNode tail = removeTail();
          // 删除哈希表中对应的项
          cache.remove(tail.key);
          --size;
        }
      } else {
        // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
        node.value = value;
        moveToHead(node);
      }
    }

    private void addToHead(DLinkedNode node) {
      node.prev = head;
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
    }

    private void removeNode(DLinkedNode node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
      removeNode(node);
      addToHead(node);
    }

    private DLinkedNode removeTail() {
      DLinkedNode res = tail.prev;
      removeNode(res);
      return res;
    }
  }
}
