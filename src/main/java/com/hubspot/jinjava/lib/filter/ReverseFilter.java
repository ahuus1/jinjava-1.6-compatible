/**********************************************************************
Copyright (c) 2014 HubSpot Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **********************************************************************/
package com.hubspot.jinjava.lib.filter;

import static com.hubspot.jinjava.util.Logging.ENGINE_LOG;

import java.lang.reflect.Array;
import java.util.Collection;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class ReverseFilter implements Filter {

  @Override
  public Object filter(Object object, JinjavaInterpreter interpreter, String... arg) {
    if (object == null) {
      return null;
    }
    // collection
    if (object instanceof Collection) {
      Object[] origin = ((Collection<?>) object).toArray();
      int length = origin.length;
      Object[] res = new Object[length];
      length--;
      for (int i = 0; i <= length; i++) {
        res[i] = origin[length - i];
      }
      return res;
    }
    // array
    if (object.getClass().isArray()) {
      int length = Array.getLength(object);
      Object[] res = new Object[length];
      length--;
      for (int i = 0; i <= length; i++) {
        res[i] = Array.get(object, length - i);
      }
      return res;
    }
    // string
    if (object instanceof String) {
      String origin = (String) object;
      int length = origin.length();
      char[] res = new char[length];
      length--;
      for (int i = 0; i <= length; i++) {
        res[i] = origin.charAt(length - i);
      }
      return String.valueOf(res);
    }
    ENGINE_LOG.warn("filter contain can't be applied to >>> " + object.getClass().getName());
    return object;
  }

  @Override
  public String getName() {
    return "reverse";
  }

}
