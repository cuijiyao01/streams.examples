package edu.princeton.cs.algs4;

/**
 * @Author i531869
 * @Date 2022/3/20 11:34
 * @Version 1.0
 */
public class HeapSort {
  // This class should not be instantiated.
  private HeapSort() { }

  /**
   * Rearranges the array in ascending order, using the natural order.
   * @param pq the array to be sorted
   */
  public static void sort(Comparable[] pq) {
    int n = pq.length;

    // heapify phase
    for (int k = n/2; k >= 1; k--)
      sink(pq, k, n);

    // sortdown phase
    int k = n;
    while (k > 1) {
      exch(pq, 1, k--);
      sink(pq, 1, k);
    }
  }

  private static void sink(Comparable[] pq, int k, int n) {
    while (2*k <= n) {
      int j = 2*k;
      if (j < n && less(pq, j, j+1)) j++;
      if (!less(pq, k, j)) break;
      exch(pq, k, j);
      k = j;
    }
  }

  private static boolean less(Comparable[] pq, int i, int j) {
    return pq[i-1].compareTo(pq[j-1]) < 0;
  }

  private static void exch(Object[] pq, int i, int j) {
    Object swap = pq[i-1];
    pq[i-1] = pq[j-1];
    pq[j-1] = swap;
  }

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Comparable[] a = new Comparable[10];
    HeapSort.sort(a);
    show(a);
  }
}
