package tilaajat.test;
// Generated by ComTest BEGIN
import kanta.SailoException;
import java.io.File;
import java.io.IOException;
import fi.jyu.mit.ohj2.VertaaTiedosto;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;
import tilaajat.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.24 18:06:42 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PostiosoitteetTest {



  // Generated by ComTest BEGIN
  /** testLisaa43 */
  @Test
  public void testLisaa43() {    // Postiosoitteet: 43
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    assertEquals("From: Postiosoitteet line: 46", 0, postiosoitteet.getLkm()); 
    postiosoitteet.lisaa(helsinki1); assertEquals("From: Postiosoitteet line: 47", 1, postiosoitteet.getLkm()); 
    postiosoitteet.lisaa(helsinki2); assertEquals("From: Postiosoitteet line: 48", 2, postiosoitteet.getLkm()); 
    postiosoitteet.lisaa(helsinki1); assertEquals("From: Postiosoitteet line: 49", 2, postiosoitteet.getLkm());  // Vain yksi samaa postinumeroa
    assertEquals("From: Postiosoitteet line: 50", helsinki1, postiosoitteet.anna(0)); 
    assertEquals("From: Postiosoitteet line: 51", helsinki2, postiosoitteet.anna(1)); 
    try {
    assertEquals("From: Postiosoitteet line: 52", helsinki1, postiosoitteet.anna(2)); 
    fail("Postiosoitteet: 52 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    assertEquals("From: Postiosoitteet line: 53", false, postiosoitteet.anna(1) == helsinki1); 
    assertEquals("From: Postiosoitteet line: 54", true, postiosoitteet.anna(1) == helsinki2); 
    postiosoitteet.lisaa(new Postiosoite(30)); assertEquals("From: Postiosoitteet line: 55", 3, postiosoitteet.getLkm()); 
    assertEquals("From: Postiosoitteet line: 56", "", postiosoitteet.anna(0).getPostitoimipaikka()); 
    postiosoitteet.lisaa(new Postiosoite(10, "HELSINKI")); assertEquals("From: Postiosoitteet line: 57", 3, postiosoitteet.getLkm()); 
    assertEquals("From: Postiosoitteet line: 58", "HELSINKI", postiosoitteet.anna(0).getPostitoimipaikka()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista81 */
  @Test
  public void testPoista81() {    // Postiosoitteet: 81
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    Postiosoite helsinki3 = new Postiosoite(30); 
    assertEquals("From: Postiosoitteet line: 85", 0, postiosoitteet.getLkm()); 
    postiosoitteet.lisaa(helsinki1); postiosoitteet.lisaa(helsinki2); postiosoitteet.lisaa(helsinki3); 
    assertEquals("From: Postiosoitteet line: 87", 3, postiosoitteet.getLkm()); 
    assertEquals("From: Postiosoitteet line: 88", helsinki3, postiosoitteet.anna(2)); 
    postiosoitteet.poista(helsinki2); 
    assertEquals("From: Postiosoitteet line: 90", 2, postiosoitteet.getLkm()); 
    try {
    assertEquals("From: Postiosoitteet line: 91", helsinki3, postiosoitteet.anna(2)); 
    fail("Postiosoitteet: 91 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    assertEquals("From: Postiosoitteet line: 92", null, postiosoitteet.annaPostinumeronPerusteella(20)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna106 */
  @Test
  public void testAnna106() {    // Postiosoitteet: 106
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    postiosoitteet.lisaa(helsinki1); 
    assertEquals("From: Postiosoitteet line: 110", helsinki1, postiosoitteet.anna(0)); 
    try {
    assertEquals("From: Postiosoitteet line: 111", helsinki2, postiosoitteet.anna(1)); 
    fail("Postiosoitteet: 111 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    postiosoitteet.lisaa(helsinki2); 
    assertEquals("From: Postiosoitteet line: 113", helsinki2, postiosoitteet.anna(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaPostinumeronPerusteella127 */
  @Test
  public void testAnnaPostinumeronPerusteella127() {    // Postiosoitteet: 127
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    postiosoitteet.lisaa(helsinki1); 
    postiosoitteet.lisaa(helsinki2); 
    assertEquals("From: Postiosoitteet line: 132", null, postiosoitteet.annaPostinumeronPerusteella(0)); 
    assertEquals("From: Postiosoitteet line: 133", helsinki1, postiosoitteet.annaPostinumeronPerusteella(10)); 
    Postiosoite helsinki3 = new Postiosoite(10); postiosoitteet.lisaa(helsinki3); 
    assertEquals("From: Postiosoitteet line: 135", helsinki1, postiosoitteet.annaPostinumeronPerusteella(10)); 
    assertEquals("From: Postiosoitteet line: 136", helsinki2, postiosoitteet.annaPostinumeronPerusteella(20)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta150 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta150() throws SailoException {    // Postiosoitteet: 150
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    Postiosoite helsinki3 = new Postiosoite(30); 
    helsinki1.taytaHelsinkiTiedoilla(); 
    helsinki2.taytaHelsinkiTiedoilla(); 
    helsinki3.taytaHelsinkiTiedoilla(); 
    String hakemisto = "testiakuankka"; 
    String tiedNimi = hakemisto+"/postiosoitteet"; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    try {
    postiosoitteet.lueTiedostosta(tiedNimi); 
    fail("Postiosoitteet: 167 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    postiosoitteet.lisaa(helsinki1); 
    postiosoitteet.lisaa(helsinki2); 
    assertEquals("From: Postiosoitteet line: 170", helsinki1.toString(), postiosoitteet.anna(0).toString()); 
    assertEquals("From: Postiosoitteet line: 171", helsinki2.toString(), postiosoitteet.anna(1).toString()); 
    try {
    assertEquals("From: Postiosoitteet line: 172", helsinki3.toString(), postiosoitteet.anna(2).toString()); 
    fail("Postiosoitteet: 172 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    postiosoitteet.tallenna(); 
    postiosoitteet = new Postiosoitteet(); 
    try {
    assertEquals("From: Postiosoitteet line: 177", helsinki1.toString(), postiosoitteet.anna(0).toString()); 
    fail("Postiosoitteet: 177 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    postiosoitteet.lueTiedostosta(tiedNimi); 
    assertEquals("From: Postiosoitteet line: 179", helsinki1.toString(), postiosoitteet.anna(0).toString()); 
    assertEquals("From: Postiosoitteet line: 180", helsinki2.toString(), postiosoitteet.anna(1).toString()); 
    try {
    assertEquals("From: Postiosoitteet line: 181", helsinki3.toString(), postiosoitteet.anna(2).toString()); 
    fail("Postiosoitteet: 181 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    postiosoitteet.lisaa(helsinki3); 
    assertEquals("From: Postiosoitteet line: 184", helsinki3.toString(), postiosoitteet.anna(2).toString()); 
    postiosoitteet.tallenna(); 
    assertEquals("From: Postiosoitteet line: 187", true, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Postiosoitteet line: 189", true, fbak.delete()); 
    assertEquals("From: Postiosoitteet line: 190", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testTallenna228 
   * @throws SailoException when error
   * @throws IOException when error
   */
  @Test
  public void testTallenna228() throws SailoException, IOException {    // Postiosoitteet: 228
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite helsinki1 = new Postiosoite(10), helsinki2 = new Postiosoite(20); 
    Postiosoite helsinki3 = new Postiosoite(30); 
    helsinki1.taytaHelsinkiTiedoilla(); 
    helsinki2.taytaHelsinkiTiedoilla(); 
    helsinki3.taytaHelsinkiTiedoilla(); 
    String hakemisto = "testiakuankka"; 
    String tiedNimi = hakemisto+"/postiosoitteet"; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    try {
    postiosoitteet.lueTiedostosta(tiedNimi); 
    fail("Postiosoitteet: 247 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    postiosoitteet.lisaa(helsinki1); 
    postiosoitteet.lisaa(helsinki2); 
    assertEquals("From: Postiosoitteet line: 250", helsinki1.toString(), postiosoitteet.anna(0).toString()); 
    assertEquals("From: Postiosoitteet line: 251", helsinki2.toString(), postiosoitteet.anna(1).toString()); 
    postiosoitteet.tallenna(); 
    String tulos = "postinro|postitoimipaikka\n" +
    helsinki1.toString() + "\n" +
    helsinki2.toString(); 
    assertEquals("From: Postiosoitteet line: 258", null, VertaaTiedosto.vertaaFileString(tiedNimi+".dat", tulos)); 
    postiosoitteet.lisaa(helsinki3); 
    assertEquals("From: Postiosoitteet line: 261", helsinki3.toString(), postiosoitteet.anna(2).toString()); 
    postiosoitteet.tallenna(); 
    tulos = "postinro|postitoimipaikka\n" +
    helsinki1.toString() + "\n" +
    helsinki2.toString() + "\n" +
    helsinki3.toString(); 
    assertEquals("From: Postiosoitteet line: 269", null, VertaaTiedosto.vertaaFileString(tiedNimi+".dat", tulos)); 
    assertEquals("From: Postiosoitteet line: 271", true, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Postiosoitteet line: 273", true, fbak.delete()); 
    assertEquals("From: Postiosoitteet line: 274", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLkm302 */
  @Test
  public void testGetLkm302() {    // Postiosoitteet: 302
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    assertEquals("From: Postiosoitteet line: 304", 0, postiosoitteet.getLkm()); 
    Postiosoite helsinki = new Postiosoite(); 
    postiosoitteet.lisaa(helsinki); 
    assertEquals("From: Postiosoitteet line: 307", 1, postiosoitteet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTiedostonPerusNimi318 */
  @Test
  public void testGetTiedostonPerusNimi318() {    // Postiosoitteet: 318
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    assertEquals("From: Postiosoitteet line: 320", "postiosoitteet", postiosoitteet.getTiedostonPerusNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTiedostonPerusNimi331 */
  @Test
  public void testSetTiedostonPerusNimi331() {    // Postiosoitteet: 331
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    assertEquals("From: Postiosoitteet line: 333", "postiosoitteet", postiosoitteet.getTiedostonPerusNimi()); 
    postiosoitteet.setTiedostonPerusNimi("testi"); 
    assertEquals("From: Postiosoitteet line: 335", "testi", postiosoitteet.getTiedostonPerusNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTiedostonNimi346 */
  @Test
  public void testGetTiedostonNimi346() {    // Postiosoitteet: 346
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    assertEquals("From: Postiosoitteet line: 348", "postiosoitteet.dat", postiosoitteet.getTiedostonNimi()); 
    postiosoitteet.setTiedostonPerusNimi("testi"); 
    assertEquals("From: Postiosoitteet line: 350", "testi.dat", postiosoitteet.getTiedostonNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetBakNimi362 */
  @Test
  public void testGetBakNimi362() {    // Postiosoitteet: 362
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    assertEquals("From: Postiosoitteet line: 364", "postiosoitteet.bak", postiosoitteet.getBakNimi()); 
    postiosoitteet.setTiedostonPerusNimi("testi"); 
    assertEquals("From: Postiosoitteet line: 366", "testi.bak", postiosoitteet.getBakNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtsiPostitoimipaikka379 */
  @Test
  public void testEtsiPostitoimipaikka379() {    // Postiosoitteet: 379
    Postiosoitteet postiosoitteet = new Postiosoitteet(); 
    Postiosoite postiosoite1 = new Postiosoite(); postiosoite1.parse("00010|HELSINKI "); 
    Postiosoite postiosoite2 = new Postiosoite(); postiosoite2.parse("40000|JYVÄSKYLÄ"); 
    Postiosoite postiosoite3 = new Postiosoite(); postiosoite3.parse("00020|HELSINKI "); 
    Postiosoite postiosoite4 = new Postiosoite(); postiosoite4.parse("00030|HELSINKI "); 
    Postiosoite postiosoite5 = new Postiosoite(); postiosoite5.parse("99998|NUORGAM  "); 
    postiosoitteet.lisaa(postiosoite1); postiosoitteet.lisaa(postiosoite2); 
    postiosoitteet.lisaa(postiosoite3); postiosoitteet.lisaa(postiosoite4); 
    postiosoitteet.lisaa(postiosoite5); 
    List<Postiosoite> loytyneet; 
    loytyneet = postiosoitteet.etsiPostitoimipaikka("*k*"); 
    assertEquals("From: Postiosoitteet line: 393", 4, loytyneet.size()); 
    assertEquals("From: Postiosoitteet line: 394", false, loytyneet.contains(postiosoite5)); 
    loytyneet = postiosoitteet.etsiPostitoimipaikka("H*"); 
    assertEquals("From: Postiosoitteet line: 397", 3, loytyneet.size()); 
    assertEquals("From: Postiosoitteet line: 398", true, loytyneet.get(0) == postiosoite1); 
    assertEquals("From: Postiosoitteet line: 399", true, loytyneet.get(1) == postiosoite3); 
    assertEquals("From: Postiosoitteet line: 400", true, loytyneet.get(2) == postiosoite4); 
    loytyneet = postiosoitteet.etsiPostitoimipaikka(null); 
    assertEquals("From: Postiosoitteet line: 403", 5, loytyneet.size()); 
  } // Generated by ComTest END
}