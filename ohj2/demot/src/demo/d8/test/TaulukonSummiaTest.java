package demo.d8.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d8.TaulukonSummia.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.27 09:45:21 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TaulukonSummiaTest {



  // Generated by ComTest BEGIN
  /** testLisaaArvoihin33 */
  @Test
  public void testLisaaArvoihin33() {    // TaulukonSummia: 33
    int[] t = { 1,2,3,4,5,6} ; 
    lisaaArvoihin(t,5); assertEquals("From: TaulukonSummia line: 36", "[6, 7, 8, 9, 10, 11]", Arrays.toString(t)); 
    lisaaArvoihin(t,0); assertEquals("From: TaulukonSummia line: 37", "[6, 7, 8, 9, 10, 11]", Arrays.toString(t)); 
    lisaaArvoihin(t,-1); assertEquals("From: TaulukonSummia line: 38", "[5, 6, 7, 8, 9, 10]", Arrays.toString(t)); 
    int[] t2 = { 1} ; 
    int[] t3 = { } ; 
    lisaaArvoihin(t2,-1); assertEquals("From: TaulukonSummia line: 41", "[0]", Arrays.toString(t2)); 
    lisaaArvoihin(t3,-1); assertEquals("From: TaulukonSummia line: 42", "[]", Arrays.toString(t3)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaskePerakkaiset60 */
  @Test
  public void testLaskePerakkaiset60() {    // TaulukonSummia: 60
    int[] t = { 1,2,3,4,5,6} ; 
    laskePerakkaiset(t); 
    assertEquals("From: TaulukonSummia line: 64", "[3, 0, 7, 0, 11, 0]", Arrays.toString(t)); 
    int[] t2 = { 1,2,3,4,5,6,7} ; 
    laskePerakkaiset(t2); 
    assertEquals("From: TaulukonSummia line: 67", "[3, 0, 7, 0, 11, 0, 7]", Arrays.toString(t2)); 
    int[] t3 = { } ; 
    laskePerakkaiset(t3); 
    assertEquals("From: TaulukonSummia line: 70", "[]", Arrays.toString(t3)); 
  } // Generated by ComTest END
}