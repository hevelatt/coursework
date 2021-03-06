package demo.d4.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import demo.d4.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.13 17:10:56 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HenkiloTest {



  // Generated by ComTest BEGIN
  /** testToString70 */
  @Test
  public void testToString70() {    // Henkilo: 70
    Henkilo henkilo = new Henkilo("Matti", "Meikäläinen", 2000); 
    assertEquals("From: Henkilo line: 72", "Matti|Meikäläinen|2000", henkilo.toString()); 
    Henkilo henkilo2 = new Henkilo("Matti", "Meikäläinen"); 
    assertEquals("From: Henkilo line: 74", "Matti|Meikäläinen|0", henkilo2.toString()); 
    Henkilo henkilo3 = new Henkilo("Matti"); 
    assertEquals("From: Henkilo line: 76", "Matti||0", henkilo3.toString()); 
    Henkilo henkilo4 = new Henkilo(); 
    assertEquals("From: Henkilo line: 78", "||0", henkilo4.toString()); 
  } // Generated by ComTest END
}