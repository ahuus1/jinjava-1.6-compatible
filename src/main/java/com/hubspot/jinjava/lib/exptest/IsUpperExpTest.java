package com.hubspot.jinjava.lib.exptest;

import org.apache.commons.lang3.StringUtils;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class IsUpperExpTest implements ExpTest {

  @Override
  public String getName() {
    return "upper";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter,
      Object... args) {
    if(var == null || !(var instanceof String)) {
      return false;
    }
    
    return StringUtils.isAllUpperCase((String) var);
  }

}
