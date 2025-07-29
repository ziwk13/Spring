package org.shining.ioc.chap01_xml;

public class Adder {

  public int add(int... args) {
    if(args == null) {
      return 0;
    }
    int total = 0;
    for(int i = 0; i < args.length; i++) {
      total += args[i];
    }
    return total;
  }
}
