package demo.d4.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d4.Ika.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.13 12:29:52 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class IkaTest {



  // Generated by ComTest BEGIN
  /** testLaskeIka24 */
  @Test
  public void testLaskeIka24() {    // Ika: 24
    assertEquals("From: Ika line: 25", 30, laskeIka(1990)); 
    assertEquals("From: Ika line: 26", 0, laskeIka(2020)); 
    assertEquals("From: Ika line: 27", 2020, laskeIka(0)); 
    assertEquals("From: Ika line: 28", 2030, laskeIka(-10)); 
    assertEquals("From: Ika line: 29", -10, laskeIka(2030)); 
  } // Generated by ComTest END
}