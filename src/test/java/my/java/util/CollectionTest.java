package my.java.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollectionTest {
  private final int NUMBER = 5;
  private final Collection<Collection<Integer>> colls = new ArrayList<>();

  public CollectionTest() {
    this.colls.add(new ArrayBlockingQueue<Integer>(NUMBER));
    this.colls.add(new ArrayDeque<Integer>());
    this.colls.add(new ArrayList<Integer>());
    this.colls.add(new ConcurrentLinkedDeque<Integer>());
    this.colls.add(new ConcurrentLinkedQueue<Integer>());
    this.colls.add(new ConcurrentSkipListSet<Integer>());
    this.colls.add(new CopyOnWriteArrayList<Integer>());
    this.colls.add(new CopyOnWriteArraySet<Integer>());

    this.colls.add(new HashSet<Integer>());
    this.colls.add(new LinkedBlockingDeque<Integer>());
    this.colls.add(new LinkedBlockingQueue<Integer>());
    this.colls.add(new LinkedHashSet<Integer>());
    this.colls.add(new LinkedList<Integer>());
    this.colls.add(new LinkedTransferQueue<Integer>());

    this.colls.add(new PriorityBlockingQueue<Integer>());
    this.colls.add(new PriorityQueue<Integer>());
    this.colls.add(new Stack<Integer>());
    this.colls.add(new TreeSet<Integer>());
    this.colls.add(new Vector<Integer>());
  }

  private void clearCollection() {
    for (Collection<Integer> coll : colls) {
      coll.clear();
      Assert.assertEquals(0, coll.size());
      Assert.assertTrue(coll.isEmpty());
    }
  }

  @Before
  public void initCase() {
    this.clearCollection();
  }

  @After
  public void cleanCase() {
    this.clearCollection();
  }

  @Test
  public void testAdd() {
    for (Collection<Integer> coll : colls) {
      this.testAdd(coll);
    }
  }

  private void testAdd(Collection<Integer> coll) {
    Assert.assertEquals(0, coll.size());
    for (int i = 0; i < NUMBER; i++) {
      coll.add(i);
    }
    Assert.assertEquals(NUMBER, coll.size());
  }

  @Test
  public void testAddAll() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      Assert.assertEquals(0, coll.size());
      coll.addAll(sourceColl);
      Assert.assertEquals(sourceColl.size(), coll.size());
    }
  }

  @Test
  public void testContains() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    for (Collection<Integer> coll : colls) {
      for (int i = 0; i < NUMBER; i++) {
        Assert.assertTrue(coll.contains(i));
        Assert.assertFalse(coll.contains(NUMBER + 1));
      }
    }
  }

  @Test
  public void testContainsAll() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    final Collection<Integer> subColl = CollectionUtil.buildCollection(NUMBER / 2);
    final Collection<Integer> otherColl = CollectionUtil.buildCollection(NUMBER + 1);
    for (Collection<Integer> coll : colls) {
      Assert.assertTrue(coll.containsAll(sourceColl));
      Assert.assertTrue(coll.containsAll(subColl));
      Assert.assertFalse(coll.containsAll(otherColl));
    }
  }

  @Test
  public void testIterator() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    for (Collection<Integer> coll : colls) {
      Iterator<Integer> it1 = sourceColl.iterator();
      Iterator<Integer> it2 = coll.iterator();

      Assert.assertTrue(it1.hasNext());
      Assert.assertEquals(it1.hasNext(), it2.hasNext());

      while (it1.hasNext() && it2.hasNext()) {
        Assert.assertEquals(it1.next(), it2.next());
      }
      Assert.assertFalse(it1.hasNext());
      Assert.assertEquals(it1.hasNext(), it2.hasNext());
    }
  }

  @Test
  public void testRemove() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    for (Collection<Integer> coll : colls) {
      Assert.assertEquals(NUMBER, coll.size());
      for (int i = NUMBER - 1; i >= 0; i--) {
        coll.remove(i);
      }
      Assert.assertEquals(0, coll.size());
    }
  }

  @Test
  public void testRemoveAll() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    final Collection<Integer> subColl = CollectionUtil.buildCollection(NUMBER / 2);
    for (Collection<Integer> coll : colls) {
      coll.removeAll(subColl);

      Assert.assertTrue(coll.size() > 0);
      Assert.assertEquals(sourceColl.size() - subColl.size(), coll.size());
      for (int i : coll) {
        Assert.assertFalse(subColl.contains(i));
      }
    }
  }

  @Test
  public void testRetainAll() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
    }

    final Collection<Integer> subColl = CollectionUtil.buildCollection(NUMBER / 2);
    for (Collection<Integer> coll : colls) {
      coll.retainAll(subColl);

      Assert.assertTrue(coll.size() > 0);
      Assert.assertEquals(subColl.size(), coll.size());
      for (int i : coll) {
        Assert.assertTrue(subColl.contains(i));
      }
    }
  }

  @Test
  public void testToArray() {
    final Collection<Integer> sourceColl = CollectionUtil.buildCollection(NUMBER);
    for (Collection<Integer> coll : colls) {
      coll.addAll(sourceColl);
      Object[] objArr = coll.toArray();
      Integer[] intArr = coll.toArray(new Integer[0]);

      Assert.assertEquals(sourceColl.size(), objArr.length);
      Assert.assertEquals(sourceColl.size(), intArr.length);
      for (Integer i : sourceColl) {
        Assert.assertEquals(i, objArr[i]);
        Assert.assertEquals(i, intArr[i]);
      }
    }
  }
}
