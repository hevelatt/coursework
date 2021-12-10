package demo.d4.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d4.TaulukonMuuttaminen.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.13 11:08:53 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TaulukonMuuttaminenTest {



  // Generated by ComTest BEGIN
  /** testLisaaArvoihin632 */
  @Test
  public void testLisaaArvoihin632() {    // TaulukonMuuttaminen: 32
    int[] t = { 1,2,3,4,5,6} ; 
    lisaaArvoihin6(t,5); 
    assertEquals("From: TaulukonMuuttaminen line: 36", "[6, 7, 8, 9, 10, 11]", Arrays.toString(t)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLaskePerkakkaiset653 */
  @Test
  public void testLaskePerkakkaiset653() {    // TaulukonMuuttaminen: 53
    int[] t = { 1,2,3,4,5,6} ; 
    laskePerkakkaiset6(t); 
    assertEquals("From: TaulukonMuuttaminen line: 57", "[3, 0, 7, 0, 11, 0]", Arrays.toString(t)); 
    int[] t2 = { 1,2,3,4,5,6,7} ; 
    laskePerkakkaiset6(t2); 
    assertEquals("From: TaulukonMuuttaminen line: 60", "[3, 0, 7, 0, 11, 0, 7]", Arrays.toString(t2)); 
  } // Generated by ComTest END
}