package com.hubspot.jinjava.lib.tag;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import com.hubspot.jinjava.util.StandardCharsets;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Throwables;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.Context;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.loader.ResourceLocator;


public class FromTagTest {

  private Context context;
  private JinjavaInterpreter interpreter;
  
  @Before
  public void setup() {
    Jinjava jinjava = new Jinjava();
    jinjava.setResourceLocator(new ResourceLocator() {
      @Override
      public String getString(String fullName, Charset encoding,
          JinjavaInterpreter interpreter) throws IOException {
        return Resources.toString(
            Resources.getResource(String.format("tags/macrotag/%s", fullName)), StandardCharsets.UTF_8);
      }
    });
    
    context = new Context();
    interpreter = new JinjavaInterpreter(jinjava, context, jinjava.getGlobalConfig());
    JinjavaInterpreter.pushCurrent(interpreter);
    
    context.put("padding", 42);
  }
  
  @After
  public void cleanup() {
    JinjavaInterpreter.popCurrent();
  }
  
  @Test
  public void importedContextExposesVars() {
    assertThat(fixture("from"))
      .contains("wrap-spacer:")
      .contains("<td height=\"42\">")
      .contains("wrap-padding: padding-left:42px;padding-right:42px");
  }
  
  private String fixture(String name) {
    try {
      return interpreter.renderString(Resources.toString(
              Resources.getResource(String.format("tags/macrotag/%s.jinja", name)), StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }
  
}
