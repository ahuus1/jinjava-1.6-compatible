package com.hubspot.jinjava.lib.exptest;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class IsEvenExpTest implements ExpTest {

  @Override
  public String getName() {
    return "even";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter,
      Object... args) {
    if(var == null || !Number.class.isAssignableFrom(var.getClass())) {
      return false;
    }
    
    return ((Number) var).intValue() % 2 == 0;
  }

}
