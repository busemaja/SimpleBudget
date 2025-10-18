package com.simplebudget;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
  private BudgetTrackerAdapter adapter;

  @Before
  public void setUp() {
    adapter = new BudgetTrackerAdapter();
  }

  @Test
  public void testAddValidTransaction() {
    boolean result = adapter.addTransaction(1, "Coffee", 5.50, "FOOD");
    assertTrue(result);
    assertEquals(5.50, adapter.getTotalSum(), 0.01);
  }

  @Test
  public void testAddTransactionInvalidCategory() {
    boolean result = adapter.addTransaction(1, "Coffee", 5.50, "INVALID");
    assertFalse(result);
  }

  @Test
  public void testGetTotalSumInitiallyZero() {
    assertEquals(0.00, adapter.getTotalSum(), 0.01);
  }

  @Test
  public void testGetCategories() {
    var categories = adapter.getCategories();
    assertNotNull(categories);
    assertTrue(categories.contains("FOOD"));
    assertTrue(categories.contains("TRANSPORTATION"));
  }

  @Test
  public void testMultipleTransactionsSum() {
    adapter.addTransaction(1, "Coffee", 5.50, "FOOD");
    adapter.addTransaction(2, "Bus", 3.00, "TRANSPORTATION");
    adapter.addTransaction(3, "Movie", 12.00, "ENTERTAINMENT");
    assertEquals(20.50, adapter.getTotalSum(), 0.01);
  }

  @Test
  public void testGetTransactionList() {
    adapter.addTransaction(1, "Coffee", 5.50, "FOOD");
    var transactions = adapter.getTransactionList();
    assertEquals(1, transactions.size());
    assertTrue(transactions.get(0).contains("Coffee"));
  }
}
