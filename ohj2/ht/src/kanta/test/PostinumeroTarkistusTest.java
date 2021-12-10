package kanta.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static kanta.PostinumeroTarkistus.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.24 18:07:29 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PostinumeroTarkistusTest {



  // Generated by ComTest BEGIN
  /** testTarkista21 */
  @Test
  public void testTarkista21() {    // PostinumeroTarkistus: 21
    assertEquals("From: PostinumeroTarkistus line: 22", null, tarkista("00010")); 
    assertEquals("From: PostinumeroTarkistus line: 23", "liian pitkä", tarkista("000010")); 
    assertEquals("From: PostinumeroTarkistus line: 24", "liian lyhyt", tarkista("0010")); 
    assertEquals("From: PostinumeroTarkistus line: 25", "vain numeroita", tarkista("a0010")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParsePostinumero42 */
  @Test
  public void testParsePostinumero42() {    // PostinumeroTarkistus: 42
    assertEquals("From: PostinumeroTarkistus line: 43", 10, parsePostinumero("00010"));  // etunollat: ei saa muuttua binääriluvuksi
    assertEquals("From: PostinumeroTarkistus line: 44", 10000, parsePostinumero("10000")); 
    assertEquals("From: PostinumeroTarkistus line: 45", 89342, parsePostinumero("89342")); 
    assertEquals("From: PostinumeroTarkistus line: 46", 10, parsePostinumero("10k")); 
    assertEquals("From: PostinumeroTarkistus line: 47", -1, parsePostinumero("k"));  // virheellinen syöte
    assertEquals("From: PostinumeroTarkistus line: 48", -1, parsePostinumero(""));  // tyhjä postinumero
    assertEquals("From: PostinumeroTarkistus line: 49", 0, parsePostinumero("00000")); 
    assertEquals("From: PostinumeroTarkistus line: 50", 0, parsePostinumero("0")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPostinumeroJonona62 */
  @Test
  public void testPostinumeroJonona62() {    // PostinumeroTarkistus: 62
    assertEquals("From: PostinumeroTarkistus line: 63", "00010", postinumeroJonona(10)); 
    assertEquals("From: PostinumeroTarkistus line: 64", "10000", postinumeroJonona(10000)); 
    assertEquals("From: PostinumeroTarkistus line: 65", "00008", postinumeroJonona(00010));  // ei oteta kantaa binäärisyötteisiin
    assertEquals("From: PostinumeroTarkistus line: 66", "00123", postinumeroJonona(123)); 
    assertEquals("From: PostinumeroTarkistus line: 67", "12345", postinumeroJonona(12345)); 
    assertEquals("From: PostinumeroTarkistus line: 68", "1234567", postinumeroJonona(1234567));  // ei oteta kantaa liian pitkiin
    assertEquals("From: PostinumeroTarkistus line: 69", "", postinumeroJonona(-1));  // negatiivinen postinumero merkki virheestä, palautetaan tyhjä merkkijono
  } // Generated by ComTest END
}