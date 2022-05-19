package myapps;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @Author i531869
 * @Date 2022/1/7 16:35
 * @Version 1.0
 */
public class Test3 {

  static RequestComeFromEnum requestComeFromEnum;
  List<List<Integer>> res = new ArrayList<>();
  int[] nums;

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    this.nums = nums;
    // Arrays.sort(nums);
    helper(0, new ArrayList<>());
    System.out.println(Arrays.toString(res.toArray()));
    return res;
  }

  private void helper(int index, List<Integer> list){
    if(index==nums.length){
      Collections.sort(list);
      if(!res.contains(list)){
        res.add(new ArrayList<>(list));
      }
      return;
    }
    helper(index+1, list);

    list.add(nums[index]);
    helper(index+1, list);
    list.remove(list.size()-1);
  }
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] visited = new boolean[nums.length];
    backtrack(res, nums, new ArrayList<Integer>(), visited);
    return res;

  }

  private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, boolean[] visited) {
    if (tmp.size() == nums.length) {
      res.add(new ArrayList<>(tmp));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      tmp.add(nums[i]);
      backtrack(res, nums, tmp, visited);
      visited[i] = false;
      tmp.remove(tmp.size() - 1);
    }
  }

  public static void main(String[] args) {
    new Test3().permute(new int[]{1,2,3});
    if (RequestComeFromEnum.ODATA != requestComeFromEnum) {
      System.out.println("yes");
    }
  }
}
