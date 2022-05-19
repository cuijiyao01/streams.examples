package myapps;

/**
 * @Author i531869
 * @Date 2022/1/7 16:36
 * @Version 1.0
 */
public enum RequestComeFromEnum {
  ODATA("ODATA");

  private String from;

  private RequestComeFromEnum(String from) {
    this.from = from;
  }
}
