package demo.d6.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d6.KasitteleReturn.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.18 21:43:39 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KasitteleReturnTest {



  // Generated by ComTest BEGIN
  /** testSummaaAlkiot36 */
  @Test
  public void testSummaaAlkiot36() {    // KasitteleReturn: 36
    int[] t; int tulos; 
    t = new int[]{2, 3, 1, 4, 6, 5}; tulos=summaaAlkiot(t); 
    assertEquals("From: KasitteleReturn line: 43", "[2, 3, 1, 4, 6, 5]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 43", 21, tulos);  
    t = new int[]{10, 5, 1, 9, 4, 2}; tulos=summaaAlkiot(t); 
    assertEquals("From: KasitteleReturn line: 44", "[10, 5, 1, 9, 4, 2]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 44", 31, tulos);  
    t = new int[]{1, 2, 3, 4, 5}; tulos=summaaAlkiot(t); 
    assertEquals("From: KasitteleReturn line: 45", "[1, 2, 3, 4, 5]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 45", 15, tulos);  
    t = new int[]{0}; tulos=summaaAlkiot(t); 
    assertEquals("From: KasitteleReturn line: 46", "[0]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 46", 0, tulos);  
    t = new int[]{1, 0}; tulos=summaaAlkiot(t); 
    assertEquals("From: KasitteleReturn line: 47", "[1, 0]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 47", 1, tulos);  
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSummaaParilliset64 */
  @Test
  public void testSummaaParilliset64() {    // KasitteleReturn: 64
    int[] t; int tulos; 
    t = new int[]{2, 3, 1, 4, 6, 5}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 71", "[2, 3, 1, 4, 6, 5]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 71", 9, tulos);  
    t = new int[]{10, 5, 1, 9, 4, 2}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 72", "[10, 5, 1, 9, 4, 2]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 72", 15, tulos);  
    t = new int[]{1, 2, 3, 4, 5}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 73", "[1, 2, 3, 4, 5]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 73", 9, tulos);  
    t = new int[]{0}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 74", "[0]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 74", 0, tulos);  
    t = new int[]{1}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 75", "[1]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 75", 1, tulos);  
    t = new int[]{1, 0}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 76", "[1, 0]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 76", 1, tulos);  
    t = new int[]{0, 1}; tulos=summaaParilliset(t); 
    assertEquals("From: KasitteleReturn line: 77", "[0, 1]", Arrays.toString(t)); assertEquals("From: KasitteleReturn line: 77", 0, tulos);  
  } // Generated by ComTest END
}