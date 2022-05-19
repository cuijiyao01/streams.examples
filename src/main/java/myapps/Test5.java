package myapps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;

/**
 * @Author i531869
 * @Date 2022/2/1 12:18
 * @Version 1.0
 */

public class Test5 {
  static class Solution {
    public String longestCommonPrefix(String[] strs) {
      int min = Integer.MAX_VALUE;
      String shortStr = "";
      for (String s : strs) {
        if (s.length() < min) {
          min = s.length();
          shortStr = s;
        }
      }
      int i = 0;
      for (; i < min; i++) {
        for (String s : strs) {
          if (s.charAt(i) != shortStr.charAt(i)) {
            break;
          }
        }
      }
      return shortStr.substring(0, i);
    }
  }


  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
    System.out.println(ForkJoinPool.commonPool().getPoolSize());
    System.out.println(ForkJoinPool.getCommonPoolParallelism());
  }

  public TreeNode deserialize(String data) {
    List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
    return deserialize2(list);
  }

  public TreeNode deserialize2(List<String> list) {
    if (list.get(0).equals("X")) {
      list.remove(0);
      return null;
    }
    TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
    list.remove(0);
    node.left = deserialize2(list);
    node.right = deserialize2(list);
    return node;
  }

  private Map<Integer, Integer> indexMap;

  public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int[] inorder,
      int inorder_left, int inorder_right) {
    if (preorder_left > preorder_right || inorder_left > inorder_right) {
      return null;
    }
    // 前序遍历中的第一个节点就是根节点
    int preorder_root = preorder_left;
    // 在中序遍历中定位根节点
    int inorder_root = indexMap.get(preorder[preorder_root]);

    // 先把根节点建立出来
    TreeNode root = new TreeNode(preorder[preorder_root]);
    // 得到左子树中的节点数目
    int size_left_subtree = inorder_root - inorder_left;
    // 递归地构造左子树，并连接到根节点
    // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
    root.left = myBuildTree(preorder, preorder_left + 1, preorder_left + size_left_subtree, inorder, inorder_left,
        inorder_root - 1);
    // 递归地构造右子树，并连接到根节点
    // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
    root.right = myBuildTree(preorder, preorder_left + size_left_subtree + 1, preorder_right, inorder, inorder_root + 1,
        inorder_right);
    return root;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    int n = preorder.length;

    // 构造哈希映射，帮助我们快速定位根节点
    indexMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < n; i++) {
      indexMap.put(inorder[i], i);
    }
    return myBuildTree(preorder, 0, n - 1, inorder, 0, n - 1);
  }

}
