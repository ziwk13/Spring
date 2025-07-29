package org.shining.ioc.chap01_xml;

public class Multiplier {

  public int multiply(int... args) {
    if(args == null) {
      return 0;
    }
    int product = 1;
    for(int i = 0; i < args.length; i++) {
      product *= args[i];
    }
    return product;
  }
}
