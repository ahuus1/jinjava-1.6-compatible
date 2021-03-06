package com.hubspot.jinjava.lib.exptest;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class IsNumberExpTest implements ExpTest {

  @Override
  public String getName() {
    return "number";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter,
      Object... args) {
    return var != null && Number.class.isAssignableFrom(var.getClass());
  }

}
