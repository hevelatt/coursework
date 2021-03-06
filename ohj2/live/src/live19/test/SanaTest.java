package live19.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import live19.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.03 12:53:40 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SanaTest {



  // Generated by ComTest BEGIN
  /** testSana20 */
  @Test
  public void testSana20() {    // Sana: 20
    Sana sana = new Sana("kissa"); 
    assertEquals("From: Sana line: 22", "kissa: 0", sana.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa33 */
  @Test
  public void testLisaa33() {    // Sana: 33
    Sana sana = new Sana("kissa"); 
    assertEquals("From: Sana line: 35", "kissa: 0", sana.toString()); 
    sana.lisaa(); 
    assertEquals("From: Sana line: 37", "kissa: 1", sana.toString()); 
    sana.lisaa(); 
    assertEquals("From: Sana line: 39", "kissa: 2", sana.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString49 */
  @Test
  public void testToString49() {    // Sana: 49
    Sana sana = new Sana("kissa"); 
    assertEquals("From: Sana line: 51", "kissa: 0", sana.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOletko63 */
  @Test
  public void testOletko63() {    // Sana: 63
    Sana sana = new Sana("kissa"); 
    assertEquals("From: Sana line: 65", true, sana.oletko("kissa")); 
    assertEquals("From: Sana line: 66", true, sana.oletko("kISSA")); 
    assertEquals("From: Sana line: 67", false, sana.oletko("kiss")); 
  } // Generated by ComTest END
}