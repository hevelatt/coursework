package demo.d9.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d9.Pnouseva.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.04 13:52:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PnousevaTest {



  // Generated by ComTest BEGIN
  /** testPisinNouseva29 */
  @Test
  public void testPisinNouseva29() {    // Pnouseva: 29
    assertEquals("From: Pnouseva line: 30", 3, pisinNouseva("abajiuxac")); 
    assertEquals("From: Pnouseva line: 31", 3, pisinNouseva("kissa")); 
    assertEquals("From: Pnouseva line: 32", 0, pisinNouseva("")); 
    assertEquals("From: Pnouseva line: 33", 1, pisinNouseva("a")); 
    assertEquals("From: Pnouseva line: 34", 2, pisinNouseva("ab")); 
    assertEquals("From: Pnouseva line: 35", 1, pisinNouseva("ba")); 
  } // Generated by ComTest END
}