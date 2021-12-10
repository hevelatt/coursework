package demo.d6.test;
// Generated by ComTest BEGIN
import demo.d6.PvmMalli;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d6.PvmMalli.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.08 16:20:20 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PvmMalliTest {

  // Generated by ComTest BEGIN  // PvmMalli: 17
   private static final String s22_2     = "22.2.2012",
                               s23_2     = "23.2.2012"; 
   private final PvmMalli    tammi2012 = new PvmMalli(1,1),
                        helmi2012 = new PvmMalli(1),
                        tanaan    = new PvmMalli(),
                        maalis97  = new PvmMalli(1,3,97),
                        p22_2     = new PvmMalli(s22_2),
                        p23_2     = new PvmMalli(s23_2); 
  // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAlusta56 */
  @Test
  public void testAlusta56() {    // PvmMalli: 56
    PvmMalli PvmMalli = new PvmMalli(20,2,2012); 
    assertEquals("From: PvmMalli line: 58", true, PvmMalli.alusta(1,3,0)); assertEquals("From: PvmMalli line: 58", "1.3.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 59", false, PvmMalli.alusta(2,13,2012)); assertEquals("From: PvmMalli line: 59", "1.3.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 60", true, PvmMalli.alusta(28,2,2012)); assertEquals("From: PvmMalli line: 60", "28.2.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 61", false, PvmMalli.alusta(29,2,2011)); assertEquals("From: PvmMalli line: 61", "28.2.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 62", true, PvmMalli.alusta(29,2,2012)); assertEquals("From: PvmMalli line: 62", "29.2.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 63", true, PvmMalli.alusta(31,3,2012)); assertEquals("From: PvmMalli line: 63", "31.3.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 64", false, PvmMalli.alusta(31,4,2012)); assertEquals("From: PvmMalli line: 64", "31.3.2012", PvmMalli.toString()); 
    assertEquals("From: PvmMalli line: 65", false, PvmMalli.alusta( 0,2,2012)); assertEquals("From: PvmMalli line: 65", "31.3.2012", PvmMalli.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPvmMalli134 */
  @Test
  public void testPvmMalli134() {    // PvmMalli: 134
    PvmMalli PvmMalli1 = new PvmMalli("12.3.2008"); assertEquals("From: PvmMalli line: 135", "12.3.2008", PvmMalli1.toString()); 
    PvmMalli PvmMalli2 = new PvmMalli("12.13.2008"); assertEquals("From: PvmMalli line: 136", tanaan.toString(), PvmMalli2.toString()); 
    PvmMalli PvmMalli3 = new PvmMalli("32.1.2008"); assertEquals("From: PvmMalli line: 137", tanaan.toString(), PvmMalli3.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString150 */
  @Test
  public void testToString150() {    // PvmMalli: 150
    assertEquals("From: PvmMalli line: 151", "1.1.2012", tammi2012.toString());
    assertEquals("From: PvmMalli line: 152", "1.2.2012", helmi2012.toString());
    assertEquals("From: PvmMalli line: 153", "20.2.2012", tanaan.toString());
    assertEquals("From: PvmMalli line: 154", "1.3.1997", maalis97.toString());
    assertEquals("From: PvmMalli line: 155", s23_2, p23_2.toString());
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse194 */
  @Test
  public void testParse194() {    // PvmMalli: 194
    PvmMalli PvmMalli = new PvmMalli(11,3,2003); 
    PvmMalli.parse("12"); assertEquals("From: PvmMalli line: 196", "12.3.2003", PvmMalli.toString()); 
    PvmMalli.parse("..2001"); assertEquals("From: PvmMalli line: 197", "12.3.2001", PvmMalli.toString()); 
    PvmMalli.parse("..2009 14:30"); assertEquals("From: PvmMalli line: 198", "12.3.2009", PvmMalli.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse213 */
  @Test
  public void testParse213() {    // PvmMalli: 213
    PvmMalli PvmMalli = new PvmMalli(11,3,2003); 
    StringBuilder jono = new StringBuilder("12"); 
    PvmMalli.parse(jono); assertEquals("From: PvmMalli line: 216", "12.3.2003", PvmMalli.toString()); assertEquals("From: PvmMalli line: 216", "", jono.toString()); 
    jono = new StringBuilder("..2001"); 
    PvmMalli.parse(jono); assertEquals("From: PvmMalli line: 218", "12.3.2001", PvmMalli.toString()); assertEquals("From: PvmMalli line: 218", "", jono.toString()); 
    jono = new StringBuilder("..2009 14:30"); 
    PvmMalli.parse(jono); assertEquals("From: PvmMalli line: 220", "12.3.2009", PvmMalli.toString()); assertEquals("From: PvmMalli line: 220", "14:30", jono.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPv233 */
  @Test
  public void testGetPv233() {    // PvmMalli: 233
    PvmMalli pv = new PvmMalli("22.2.2010"); 
    assertEquals("From: PvmMalli line: 235", 22, pv.getPv()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKk246 */
  @Test
  public void testGetKk246() {    // PvmMalli: 246
    PvmMalli pv = new PvmMalli("22.2.2010"); 
    assertEquals("From: PvmMalli line: 248", 2, pv.getKk()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetVv259 */
  @Test
  public void testGetVv259() {    // PvmMalli: 259
    PvmMalli pv = new PvmMalli("22.2.2010"); 
    assertEquals("From: PvmMalli line: 261", 2010, pv.getVv()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testCompareTo276 */
  @Test
  public void testCompareTo276() {    // PvmMalli: 276
    PvmMalli PvmMalli = new PvmMalli(12,3,2012); 
    try {
    assertEquals("From: PvmMalli line: 278", 0, PvmMalli.compareTo(new Double(2))); 
    fail("PvmMalli: 278 Did not throw ClassCastException");
    } catch(ClassCastException _e_){ _e_.getMessage(); }
    assertEquals("From: PvmMalli line: 279", 0, PvmMalli.compareTo("12.3.2012")); 
    assertEquals("From: PvmMalli line: 280", 0, PvmMalli.compareTo(new StringBuilder("12.3.2012"))); 
    assertEquals("From: PvmMalli line: 281", 0, PvmMalli.compareTo(new StringBuffer("12.3.2012"))); 
    assertEquals("From: PvmMalli line: 282", 1, PvmMalli.compareTo(tanaan)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testCompareTo306 */
  @Test
  public void testCompareTo306() {    // PvmMalli: 306
    assertEquals("From: PvmMalli line: 307", -1, maalis97.compareTo(tammi2012));  // ero vuodessa
    assertEquals("From: PvmMalli line: 308", 1, tammi2012.compareTo(maalis97)); 
    assertEquals("From: PvmMalli line: 309", -1, tammi2012.compareTo(tanaan));  // ero kuukaudessa
    assertEquals("From: PvmMalli line: 310", 1, tanaan.compareTo(tammi2012)); 
    assertEquals("From: PvmMalli line: 311", -1, helmi2012.compareTo(tanaan));  // ero päivässä
    assertEquals("From: PvmMalli line: 312", 1, tanaan.compareTo(helmi2012)); 
    assertEquals("From: PvmMalli line: 313", 0, tanaan.compareTo(tanaan)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testCompareTo336 */
  @Test
  public void testCompareTo336() {    // PvmMalli: 336
    assertEquals("From: PvmMalli line: 338", -1, compareTo(maalis97,tammi2012));  // ero vuodessa
    assertEquals("From: PvmMalli line: 339", 1, compareTo(tammi2012,maalis97)); 
    assertEquals("From: PvmMalli line: 340", -1, compareTo(tammi2012,tanaan));  // ero kuukaudessa
    assertEquals("From: PvmMalli line: 341", 1, compareTo(tanaan,tammi2012)); 
    assertEquals("From: PvmMalli line: 342", -1, compareTo(helmi2012,tanaan));  // ero päivässä
    assertEquals("From: PvmMalli line: 343", 1, compareTo(tanaan,helmi2012)); 
    assertEquals("From: PvmMalli line: 344", 0, compareTo(tanaan,tanaan)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testCompareTo347 */
  @Test
  public void testCompareTo347() {    // PvmMalli: 347
    PvmMalli pv1 = new PvmMalli(15,6,2013); 
    PvmMalli pv2 = new PvmMalli(14,5,2014); 
    PvmMalli pv3 = new PvmMalli(15,7,2014); 
    PvmMalli pv4 = new PvmMalli(16,7,2014); 
    PvmMalli pv5 = new PvmMalli(16,7,2014); 
    PvmMalli pv6 = new PvmMalli(16,7,2012); 
    assertEquals("From: PvmMalli line: 355", -1, compareTo(pv1,pv2));  // ero vuodessa
    assertEquals("From: PvmMalli line: 356", 1, compareTo(pv2,pv1)); 
    assertEquals("From: PvmMalli line: 357", -1, compareTo(pv2,pv3));  // ero kuukaudessa
    assertEquals("From: PvmMalli line: 358", 1, compareTo(pv3,pv2)); 
    assertEquals("From: PvmMalli line: 359", -1, compareTo(pv3,pv4));  // ero päivässä
    assertEquals("From: PvmMalli line: 360", 1, compareTo(pv4,pv3)); 
    assertEquals("From: PvmMalli line: 361", 0, compareTo(pv4,pv5));  // kaikki samoja
    assertEquals("From: PvmMalli line: 362", -1, compareTo(pv6,pv2));  // ero kuukaudessa, mutta vuodessa toisinpäin
    assertEquals("From: PvmMalli line: 363", 1, compareTo(pv2,pv6)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEquals384 */
  @Test
  public void testEquals384() {    // PvmMalli: 384
    assertEquals("From: PvmMalli line: 385", false, maalis97.equals(tammi2012)); 
    assertEquals("From: PvmMalli line: 386", false, tammi2012.equals(maalis97)); 
    assertEquals("From: PvmMalli line: 387", true, tanaan.equals(tanaan)); 
    assertEquals("From: PvmMalli line: 388", true, p23_2.equals(new PvmMalli("23.2.2012"))); 
    assertEquals("From: PvmMalli line: 389", false, p23_2.equals(p22_2)); 
    assertEquals("From: PvmMalli line: 390", true, p23_2.equals(s23_2)); 
    assertEquals("From: PvmMalli line: 391", false, p23_2.equals(s22_2)); 
    assertEquals("From: PvmMalli line: 392", false, s23_2.equals(p23_2));  // String ei osaa verrata PvmMalli:ään
    assertEquals("From: PvmMalli line: 393", false, s23_2.equals(p22_2));  // String ei osaa verrata PvmMalli:ään
    assertEquals("From: PvmMalli line: 394", true, s23_2.equals(p23_2.toString()));  // mutta osaa merkkijonoon
    assertEquals("From: PvmMalli line: 395", false, tanaan.equals(new Double(2)));  // PvmMalli ei osaa verrata muihin tyyppeihin
    assertEquals("From: PvmMalli line: 396", true, p23_2.equals(new StringBuilder("23.2.2012"))); 
    assertEquals("From: PvmMalli line: 397", true, p23_2.equals(new StringBuffer("23.2.2012"))); 
    assertEquals("From: PvmMalli line: 398", false, p23_2.equals(new StringBuilder("22.2.2012"))); 
    assertEquals("From: PvmMalli line: 399", false, p23_2.equals(new StringBuffer("22.2.2012"))); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHashCode415 */
  @Test
  public void testHashCode415() {    // PvmMalli: 415
    PvmMalli pv1 = new PvmMalli(1,1,2012); 
    PvmMalli pv2 = new PvmMalli(2,1,2012); 
    assertEquals("From: PvmMalli line: 418", 2012*10000 + 100 + 1, pv1.hashCode()); 
    assertEquals("From: PvmMalli line: 419", 20120101, pv1.hashCode()); 
    assertEquals("From: PvmMalli line: 420", pv1.hashCode() + 1, pv2.hashCode()); 
    PvmMalli pv3 = new PvmMalli(31,12,2011); 
    assertEquals("From: PvmMalli line: 422", 20111231, pv3.hashCode()); 
  } // Generated by ComTest END
}