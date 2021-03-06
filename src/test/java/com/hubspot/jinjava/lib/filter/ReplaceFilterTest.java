package com.hubspot.jinjava.lib.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.Context;
import com.hubspot.jinjava.interpret.InterpretException;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;


public class ReplaceFilterTest {

  JinjavaInterpreter interpreter;
  ReplaceFilter filter;
  
  @Before
  public void setup() {
    Jinjava jinjava = new Jinjava();
    Context context = new Context();
    interpreter = new JinjavaInterpreter(jinjava, context, jinjava.getGlobalConfig());
    filter = new ReplaceFilter();
  }
  
  @Test(expected=InterpretException.class)
  public void expectsAtLeast2Args() {
    filter.filter("foo", interpreter);
  }

  @Test(expected=InterpretException.class)
  public void expectsFilterVar() {
    filter.filter(null, interpreter, "foo", "bar");
  }
  
  @Test
  public void replaceString() {
    assertThat(filter.filter("hello world", interpreter, "hello", "goodbye")).isEqualTo("goodbye world");
  }
  
  @Test
  public void replaceWithCount() {
    assertThat(filter.filter("aaaaargh", interpreter, "a", "d'oh, ", "2")).isEqualTo("d'oh, d'oh, aaargh");
  }
  
}
