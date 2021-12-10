package astia.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import astia.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.17 22:04:33 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class AstiaTest {



  // Generated by ComTest BEGIN
  /** testAstia55 */
  @Test
  public void testAstia55() {    // Astia: 55
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 57", "5l", astia5l.getNimi()); 
    assertEquals("From: Astia line: 58", 0, astia5l.getMaara(), 0.000001); 
    assertEquals("From: Astia line: 59", 5, astia5l.getTilavuus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTayta72 */
  @Test
  public void testTayta72() {    // Astia: 72
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 74", 0, astia5l.getMaara(), 0.000001); 
    astia5l.tayta(); 
    assertEquals("From: Astia line: 76", 5, astia5l.getMaara(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKaada90 */
  @Test
  public void testKaada90() {    // Astia: 90
    Astia a5 = new Astia("5",5); 
    assertEquals("From: Astia line: 92", 0.0, a5.kaada(3), 0.000001); assertEquals("From: Astia line: 92", 3.0, a5.getMaara(), 0.000001); 
    assertEquals("From: Astia line: 93", 3.0, a5.kaada(5), 0.000001); assertEquals("From: Astia line: 93", 5.0, a5.getMaara(), 0.000001); 
    assertEquals("From: Astia line: 94", 0.0, a5.kaada(-3), 0.000001); assertEquals("From: Astia line: 94", 2.0, a5.getMaara(), 0.000001); 
    assertEquals("From: Astia line: 95", -1.0, a5.kaada(-3), 0.000001); assertEquals("From: Astia line: 95", 0.0, a5.getMaara(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKaada120 */
  @Test
  public void testKaada120() {    // Astia: 120
    Astia ampari = new Astia("ä",100); 
    Astia a8     = new Astia("8",8); 
    Astia a5     = new Astia("5",5); 
    ampari.kaada(a8); assertEquals("From: Astia line: 125", 0, a8.getMaara(), 0.000001); assertEquals("From: Astia line: 125", 0.0, ampari.getMaara(), 0.000001); 
    ampari.tayta(); 
    ampari.kaada(a8); assertEquals("From: Astia line: 127", 8.0, a8.getMaara(), 0.000001); assertEquals("From: Astia line: 127", 92.0, ampari.getMaara(), 0.000001); 
    a8.kaada(a5); assertEquals("From: Astia line: 129", 3.0, a8.getMaara(), 0.000001); assertEquals("From: Astia line: 129", 5.0, a5.getMaara(), 0.000001); 
    a5.kaada(ampari); assertEquals("From: Astia line: 130", 0.0, a5.getMaara(), 0.000001); assertEquals("From: Astia line: 130", 97.0, ampari.getMaara(), 0.000001); 
    a8.kaada(a5); assertEquals("From: Astia line: 131", 0.0, a8.getMaara(), 0.000001); assertEquals("From: Astia line: 131", 3.0, a5.getMaara(), 0.000001); 
    ampari.kaada(a8); 
    a8.kaada(a5); assertEquals("From: Astia line: 133", 6.0, a8.getMaara(), 0.000001); assertEquals("From: Astia line: 133", 5.0, a5.getMaara(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetMaara148 */
  @Test
  public void testGetMaara148() {    // Astia: 148
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 150", 0, astia5l.getMaara(), 0.000001); 
    astia5l.tayta(); 
    assertEquals("From: Astia line: 152", 5, astia5l.getMaara(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTilavuus164 */
  @Test
  public void testGetTilavuus164() {    // Astia: 164
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 166", 5, astia5l.getTilavuus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTyhjaa178 */
  @Test
  public void testGetTyhjaa178() {    // Astia: 178
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 180", 5, astia5l.getTyhjaa(), 0.000001); 
    astia5l.tayta(); 
    assertEquals("From: Astia line: 182", 0, astia5l.getTyhjaa(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetNimi194 */
  @Test
  public void testGetNimi194() {    // Astia: 194
    Astia astia5l = new Astia("5l",5); 
    assertEquals("From: Astia line: 196", "5l", astia5l.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOletko210 */
  @Test
  public void testOletko210() {    // Astia: 210
    Astia ampari  = new Astia("Ämpäri",100); 
    assertEquals("From: Astia line: 212", false, ampari.oletko("Ä")); 
    assertEquals("From: Astia line: 213", true, ampari.oletko("ämpäri")); 
    assertEquals("From: Astia line: 214", true, ampari.oletko("ÄMPÄRI")); 
  } // Generated by ComTest END
}