package my.java.util;

import java.util.ArrayDeque;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

public class ArrayDequeTest {
  private final int NUMBER = 5;

  @Test
  public void testAdd() {
    Collection<Integer> coll = new ArrayDeque<Integer>();
    for (int i = 0; i < NUMBER; i++) {
      coll.add(i);
    }
    Assert.assertEquals(NUMBER, coll.size());
  }

  @Test
  public void testAddAll() {
    Collection<Integer> coll = CollectionUtil.buildCollection(NUMBER);

    Collection<Number> coll2 = new ArrayDeque<Number>();
    coll2.addAll(coll);
    Assert.assertEquals(NUMBER, coll2.size());

    Collection<Integer> coll3 = new ArrayDeque<Integer>();
    coll3.addAll(coll);
    Assert.assertEquals(NUMBER, coll3.size());
  }
}
