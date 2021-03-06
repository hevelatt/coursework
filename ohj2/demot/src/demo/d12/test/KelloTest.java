package demo.d12.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d12.Kello.*;
import demo.d12.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.26 18:20:47 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KelloTest {



  // Generated by ComTest BEGIN
  /** testKello19 */
  @Test
  public void testKello19() {    // Kello: 19
    Kello kello = new Kello(); 
    assertEquals("From: Kello line: 21", "00:00", kello.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse44 */
  @Test
  public void testParse44() {    // Kello: 44
    Kello kello = new Kello(); 
    assertEquals("From: Kello line: 46", "00:00", kello.toString()); 
    kello.parse("12.00"); 
    assertEquals("From: Kello line: 48", "12:00", kello.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString70 */
  @Test
  public void testToString70() {    // Kello: 70
    Kello kello = new Kello("0202"); 
    assertEquals("From: Kello line: 72", "02:02", kello.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkista85 */
  @Test
  public void testTarkista85() {    // Kello: 85
    assertEquals("From: Kello line: 86", null, tarkista("12:00")); 
    assertEquals("From: Kello line: 87", null, tarkista("12.00")); 
    assertEquals("From: Kello line: 88", null, tarkista("1200")); 
    assertEquals("From: Kello line: 89", null, tarkista("12")); 
    assertEquals("From: Kello line: 90", null, tarkista("1:00")); 
    assertEquals("From: Kello line: 91", null, tarkista("1.0")); 
    assertEquals("From: Kello line: 92", null, tarkista("12")); 
    assertEquals("From: Kello line: 93", "Anna aika muodossa hh:mm", tarkista("130:00")); 
    assertEquals("From: Kello line: 94", "Anna aika muodossa hh.mm", tarkista("12.001")); 
    assertEquals("From: Kello line: 95", "Anna aika muodossa hhmm", tarkista("12001")); 
    assertEquals("From: Kello line: 96", "Anna aika muodossa hh", tarkista("120")); 
    assertEquals("From: Kello line: 97", "Ajassa ei saa esiinty?? kirjaimia tai muita erikoismerkkej?? kuin . ja :", tarkista("k????k")); 
    assertEquals("From: Kello line: 98", "Tunnit t??ytyy olla v??lill?? 0-24", tarkista("25:60")); 
    assertEquals("From: Kello line: 99", "Minuutit t??ytyy olla v??lill?? 0-59", tarkista("2460")); 
  } // Generated by ComTest END
}