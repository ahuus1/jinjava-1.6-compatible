package com.hubspot.jinjava.lib.filter;

import com.hubspot.jinjava.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class FileSizeFormatFilter implements Filter {

  @Override
  public String getName() {
    return "filesizeformat";
  }

  @Override
  public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
    float bytes = NumberUtils.toFloat(Objects.toString(var), 0.0f);

    if(bytes == 1) {
      return "1 Byte";
    }
    
    boolean binary = false;
    if(args.length > 0) {
      binary = BooleanUtils.toBoolean(args[0]);
    }
    
    int base = binary ? 1024 : 1000;
    if(bytes < base) {
      return (int) bytes + " Bytes";
    }
    
    String[] sizes = binary ? BINARY_SIZES : DECIMAL_SIZES;
    int unit = 1;
    String prefix = "";
    
    for(int i = 0; i < sizes.length; i++) {
      unit = (int) Math.pow(base, i + 2);
      prefix = sizes[i];
      
      if(bytes < unit) {
        return String.format("%.1f %s", (base * bytes / unit), prefix);
      }
    }
    
    return String.format("%.1f %s", (base * bytes / unit), prefix);
  }

  private static final String[] BINARY_SIZES = {"KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"};
  private static final String[] DECIMAL_SIZES = {"KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
}
