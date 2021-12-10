package demo.d9.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d9.PoistaTaulukosta.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.04 16:08:17 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PoistaTaulukostaTest {



  // Generated by ComTest BEGIN
  /** testPoista18 */
  @Test
  public void testPoista18() {    // PoistaTaulukosta: 18
    int[] t = { 4,7,9,3,9,2} ; 
    assertEquals("From: PoistaTaulukosta line: 21", 4, poista(t, 6, 9)); { String _l_=Arrays.toString(t),_r_="\\[4, 7, 3, 2, ., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 21" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
    assertEquals("From: PoistaTaulukosta line: 22", 3, poista(t, 4, 2)); { String _l_=Arrays.toString(t),_r_="\\[4, 7, 3, ., ., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 22" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
    t = new int[]{0,0}; 
    assertEquals("From: PoistaTaulukosta line: 24", -1, poista(t, 4, 0)); assertEquals("From: PoistaTaulukosta line: 24", "[0, 0]", Arrays.toString(t)); 
    assertEquals("From: PoistaTaulukosta line: 25", 0, poista(t, 2, 0)); { String _l_=Arrays.toString(t),_r_="\\[., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 25" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
    t = new int[]{9,2,9,9,9,9,1,2,3}; 
    assertEquals("From: PoistaTaulukosta line: 27", 4, poista(t, 9, 9)); { String _l_=Arrays.toString(t),_r_="\\[2, 1, 2, 3, ., ., ., ., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 27" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
    assertEquals("From: PoistaTaulukosta line: 28", 4, poista(t, 4, 9)); { String _l_=Arrays.toString(t),_r_="\\[2, 1, 2, 3, ., ., ., ., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 28" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
    assertEquals("From: PoistaTaulukosta line: 29", 2, poista(t, 4, 2)); { String _l_=Arrays.toString(t),_r_="\\[1, 3, ., ., ., ., ., ., .\\]"; if ( !_l_.matches(_r_) ) fail("From: PoistaTaulukosta line: 29" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END
}