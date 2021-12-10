package demo.d3.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d3.Esiintymat2.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.10 13:00:23 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class Esiintymat2Test {


  // Generated by ComTest BEGIN
  /** testPoistaEsiintymat24 */
  @Test
  public void testPoistaEsiintymat24() {    // Esiintymat2: 24
    assertEquals("From: Esiintymat2 line: 25", "Cc", poistaEsiintymat("Catcat", "at")); 
    assertEquals("From: Esiintymat2 line: 26", "Pi", poistaEsiintymat("Paatti", "at")); 
    assertEquals("From: Esiintymat2 line: 27", "Puit", poistaEsiintymat("Puatit", "at")); 
    assertEquals("From: Esiintymat2 line: 28", "", poistaEsiintymat("aaattt", "at")); 
    assertEquals("From: Esiintymat2 line: 29", "ei", poistaEsiintymat("okei", "ok")); 
    assertEquals("From: Esiintymat2 line: 30", "ok", poistaEsiintymat("ok", "okei")); 
    assertEquals("From: Esiintymat2 line: 31", "", poistaEsiintymat("okei", "okei")); 
    assertEquals("From: Esiintymat2 line: 32", "", poistaEsiintymat("", "")); 
    assertEquals("From: Esiintymat2 line: 33", "okei", poistaEsiintymat("okei", "")); 
    assertEquals("From: Esiintymat2 line: 34", "", poistaEsiintymat(null, "")); 
    assertEquals("From: Esiintymat2 line: 35", "", poistaEsiintymat(null, "k"));  // # THROWS NullPointerException
    assertEquals("From: Esiintymat2 line: 36", "Kissa", poistaEsiintymat("Kissa", null)); 
  } // Generated by ComTest END
}