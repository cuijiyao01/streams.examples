package myapps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author i531869
 * @Date 2022/2/10 10:10
 * @Version 1.0
 */
public class Test6 {
  class A{
    public void printA(){
      System.out.println("A======");
    }
    public void printA2(){
      System.out.println("A2======");
    }
  }

  class B extends A{
    public void printA(){
      System.out.println("1======");
    }
    public void printB(){
      System.out.println("B======");
    }
  }


  public void test() {
    B b = new B();
    A a = b;

    A a1 = new B();
    B b1 = (B) a1;
    b1.printB();
    // a.printA();
    // b.printA();
    // b.printB();
  }
  public int maxProduct(int[] nums) {
    int maxF = nums[0], minF = nums[0], ans = nums[0];
    int length = nums.length;
    for (int i = 1; i < length; ++i) {
      // int mx = maxF, mn = minF;//只用两个变量来维护i−1时刻的状态,优化空间
      maxF = Math.max(maxF * nums[i], Math.max(nums[i], minF * nums[i]));
      minF = Math.min(minF * nums[i], Math.min(nums[i], maxF * nums[i]));
      ans = Math.max(maxF, ans);
    }
    return ans;
  }
  public static void main(String[] args) {
    // byte[] sysBlob = new byte[]{85, 115, 101, 114, 32, 91, 91, 85, 83, 69, 82, 93, 93, 32,
    //     106, 117, 115, 116, 32, 99, 104, 97, 110, 103, 101, 100, 32, 116, 104, 101, 32,
    //     82, 66, 80, 32, 110, 111, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 32, 115,
    //     101, 116, 116, 105, 110, 103, 115, 32, 97, 116, 32, 91, 91, 84, 73, 77, 69, 83, 84,
    //     65, 77, 80, 93, 93, 46, -30, -128, -117, 32, 10, 91, 91, 67, 72, 65, 78, 71, 69, 95,
    //     68, 69, 84, 65, 73, 76, 93, 93, 10};
    // String str = new String(sysBlob);
    // int x=Integer.MAX_VALUE/2*3;
    // long y=12;
    // long z = x*y;
    // System.out.println((long)x*x);
    // System.out.println(Integer.MAX_VALUE);
    // System.out.println(Integer.MIN_VALUE);
    // System.out.println(str);
    // new Test6().maxProduct(new int[]{-4,-3,-2});
    List<Object> list = new ArrayList<>();
    long max=  Runtime.getRuntime().maxMemory()/1024/1024;
    long total = Runtime.getRuntime().totalMemory()/1024/1024;
    System.out.println(max);
    System.out.println(total);
    // while (true) {
    //   list.add(new Object());
    // }
  }
}
