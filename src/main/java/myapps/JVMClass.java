package myapps;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author i531869
 * @Date 2022/2/19 18:52
 * @Version 1.0
 */
public class JVMClass {
  public static void main(String[] args) {
    long max=  Runtime.getRuntime().maxMemory()/1024/1024;
    long total = Runtime.getRuntime().totalMemory()/1024/1024;
    System.out.println(max);
    System.out.println(total);
    // List<Object> list = new ArrayList<>();
    // while (true) {
    //   // byte[] bytes = new byte[30 * 1024 * 1024];
    //   list.add(new Object());
    // }
  }
}
