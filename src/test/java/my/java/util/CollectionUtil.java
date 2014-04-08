package my.java.util;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtil {
  public static Collection<Integer> buildCollection(int number) {
    Collection<Integer> coll = new ArrayList<Integer>();
    for (int i = 0; i < number; i++) {
      coll.add(i);
    }
    return coll;
  }
}