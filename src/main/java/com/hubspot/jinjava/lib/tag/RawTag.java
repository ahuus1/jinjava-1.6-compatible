package com.hubspot.jinjava.lib.tag;

import org.apache.commons.lang3.StringUtils;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.tree.Node;
import com.hubspot.jinjava.tree.TagNode;

public class RawTag implements Tag {

  @Override
  public String getName() {
    return "raw";
  }

  @Override
  public String getEndTagName() {
    return "endraw";
  }
  
  @Override
  public String interpret(TagNode tagNode, JinjavaInterpreter interpreter) {
    StringBuilder result = new StringBuilder();

    for(Node n : tagNode.getChildren())  {
      result.append(renderNodeRaw(n));
    }
    
    return result.toString();
  }

  private String renderNodeRaw(Node n) {
    StringBuilder result = new StringBuilder(n.getMaster().getImage());

    for(Node child : n.getChildren()) {
      result.append(renderNodeRaw(child));
    }
    
    if(TagNode.class.isAssignableFrom(n.getClass())) {
      TagNode t = (TagNode) n;
      if(StringUtils.isNotBlank(t.getEndName())) {
        result.append("{% ").append(t.getEndName()).append(" %}");
      }
    }
    
    return result.toString();
  }
  
}
