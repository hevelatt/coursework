package demo.d7.test;
// Generated by ComTest BEGIN
import fi.jyu.mit.ohj2.Suuntaaja;
import static org.junit.Assert.*;
import org.junit.*;
import demo.d7.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.24 16:23:46 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class AstiaPeliTest {



  // Generated by ComTest BEGIN
  /** testAstiaPeli57 */
  @Test
  public void testAstiaPeli57() {    // AstiaPeli: 57
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 59", 100.0, peli.etsi("ä").getTilavuus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetAmpari72 */
  @Test
  public void testGetAmpari72() {    // AstiaPeli: 72
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 74", 100.0, peli.getAmpari().getTilavuus(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLkm86 */
  @Test
  public void testGetLkm86() {    // AstiaPeli: 86
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 88", 1, peli.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaAstia102 */
  @Test
  public void testLisaaAstia102() {    // AstiaPeli: 102
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 104", 1, peli.getLkm()); 
    peli.lisaaAstia("5",5); 
    assertEquals("From: AstiaPeli line: 106", 2, peli.getLkm()); 
    peli.lisaaAstia("8",8); 
    assertEquals("From: AstiaPeli line: 108", 3, peli.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna123 */
  @Test
  public void testAnna123() {    // AstiaPeli: 123
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 125", "ä", peli.anna(0).getNimi()); 
    peli.lisaaAstia("5",5); 
    assertEquals("From: AstiaPeli line: 127", "5", peli.anna(1).getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTulostaOhje139 */
  @Test
  public void testTulostaOhje139() {    // AstiaPeli: 139
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    AstiaPeli peli = new AstiaPeli(); peli.lisaaAstia("5",5); peli.lisaaAstia("8",8); 
    peli.tulostaOhje(); 
    assertEquals("From: AstiaPeli line: 143", null, so.ero("Käytössäsi on 5.0 sekä 8.0 litran astiat ja Ämpari (100.0 l)\n")); 
    so.palauta(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTulostaMaarat163 */
  @Test
  public void testTulostaMaarat163() {    // AstiaPeli: 163
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    String tulos =
    "5.0 litran astiassa on 5.0 litraa nestettä\n"       +
    "8.0 litran astiassa on 3.0 litraa nestettä\n"; 
    AstiaPeli peli = new AstiaPeli(); 
    Astia ampari = peli.getAmpari(); 
    Astia a5 = peli.lisaaAstia("5",5); 
    Astia a8 = peli.lisaaAstia("8",8); 
    ampari.kaada(a8); 
    a8.kaada(a5); 
    peli.tulostaMaarat(); 
    assertEquals("From: AstiaPeli line: 178", null, so.ero(tulos)); 
    so.palauta(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtsi195 */
  @Test
  public void testEtsi195() {    // AstiaPeli: 195
    AstiaPeli peli = new AstiaPeli(); peli.lisaaAstia("5",5); peli.lisaaAstia("8",8); 
    assertEquals("From: AstiaPeli line: 198", true, peli.etsi("ä").oletko("ä")); 
    assertEquals("From: AstiaPeli line: 199", true, peli.etsi("Ä").oletko("ä")); 
    assertEquals("From: AstiaPeli line: 200", 5.0, peli.etsi("5").getTilavuus(), 0.000001); 
    assertEquals("From: AstiaPeli line: 201", 8.0, peli.etsi("8").getTilavuus(), 0.000001); 
    assertEquals("From: AstiaPeli line: 202", null, peli.etsi("9")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testNimiOhje218 */
  @Test
  public void testNimiOhje218() {    // AstiaPeli: 218
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    AstiaPeli peli = new AstiaPeli(); peli.lisaaAstia("5",5); peli.lisaaAstia("8",8); 
    peli.nimiOhje("4","6"); 
    String tulos = "Nimeä ei tunneta: 4 tai 6\n" +
    "Tunnetaan nimet: \n"         +
    "ä 5 8 \n"; 
    assertEquals("From: AstiaPeli line: 225", null, so.ero(tulos)); 
    peli.nimiOhje("?","6"); 
    tulos = "Tunnetaan nimet: \nä 5 8 \n"; 
    assertEquals("From: AstiaPeli line: 228", null, so.ero(tulos)); 
    so.palauta(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPelaa245 */
  @Test
  public void testPelaa245() {    // AstiaPeli: 245
    Suuntaaja.StringInput si = new Suuntaaja.StringInput(""); 
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    si.input("ä 8\n8 5\n4 5\n\n"); 
    String tulos =
    "Käytössäsi on 5.0 sekä 8.0 litran astiat ja Ämpari (100.0 l)\n"+
    "5.0 litran astiassa on 0.0 litraa nestettä\n" +
    "8.0 litran astiassa on 0.0 litraa nestettä\n" +
    "Mistä kaadetaan ja mihin >"                   +
    "5.0 litran astiassa on 0.0 litraa nestettä\n" +
    "8.0 litran astiassa on 8.0 litraa nestettä\n" +
    "Mistä kaadetaan ja mihin >"                   +
    "5.0 litran astiassa on 5.0 litraa nestettä\n" +
    "8.0 litran astiassa on 3.0 litraa nestettä\n" +
    "Mistä kaadetaan ja mihin >"                   +
    "Nimeä ei tunneta: 4 tai 5\n"                  +
    "Tunnetaan nimet: \n"                          +
    "ä 5 8 \n"                                     +
    "5.0 litran astiassa on 5.0 litraa nestettä\n" +
    "8.0 litran astiassa on 3.0 litraa nestettä\n" +
    "Mistä kaadetaan ja mihin >"; 
    AstiaPeli peli = new AstiaPeli(); 
    peli.lisaaAstia("5",5); 
    peli.lisaaAstia("8",8); 
    peli.tulostaOhje(); 
    peli.pelaa(); 
    assertEquals("From: AstiaPeli line: 274", null, so.ero(tulos)); 
    si.palauta(); so.palauta(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetMaara302 */
  @Test
  public void testGetMaara302() {    // AstiaPeli: 302
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 304", 100, peli.getMaara(0), 0.000001); 
    Astia a5 = peli.lisaaAstia("5",5); 
    assertEquals("From: AstiaPeli line: 306", 0, peli.getMaara(1), 0.000001); 
    peli.getAmpari().kaada(a5); assertEquals("From: AstiaPeli line: 307", 5, peli.getMaara(1), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTilavuus319 */
  @Test
  public void testGetTilavuus319() {    // AstiaPeli: 319
    AstiaPeli peli = new AstiaPeli(); 
    assertEquals("From: AstiaPeli line: 321", 100, peli.getTilavuus(0), 0.000001); 
    peli.lisaaAstia("5",5); 
    assertEquals("From: AstiaPeli line: 323", 5, peli.getTilavuus(1), 0.000001); 
  } // Generated by ComTest END
}