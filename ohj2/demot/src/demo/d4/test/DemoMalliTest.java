package demo.d4.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d4.DemoMalli.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.06.17 19:51:16 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class DemoMalliTest {



  // Generated by ComTest BEGIN
  /** testDemohyvitys17 */
  @Test
  public void testDemohyvitys17() {    // DemoMalli: 17
    int[][] pistetaulu = { { 40, 50, 60, 70, 80, 90} ,{ 1,2,3,4,5,6} } ; 
    assertEquals("From: DemoMalli line: 19", 5, demohyvitys(pistetaulu, 5, 6));  //Normaali tapaus
    assertEquals("From: DemoMalli line: 20", 2, demohyvitys(pistetaulu, 3, 6));  //Tasan sama prosentti
    assertEquals("From: DemoMalli line: 21", 6, demohyvitys(pistetaulu, 50, 50));  //Kaikki tehty
    assertEquals("From: DemoMalli line: 22", 6, demohyvitys(pistetaulu, 51, 50));  //On tehty ylimääräistä
    assertEquals("From: DemoMalli line: 23", 6, demohyvitys(pistetaulu, 49, 50));  //On tehty melkein kaikki
    assertEquals("From: DemoMalli line: 24", 6, demohyvitys(pistetaulu, 9, 10));  //On tehty 90%
    assertEquals("From: DemoMalli line: 25", 0, demohyvitys(pistetaulu, 0, 10));  //Ei ole tehty mitään
    assertEquals("From: DemoMalli line: 26", 0, demohyvitys(pistetaulu, 1, 10));  //On tehty hyvin vähän
    assertEquals("From: DemoMalli line: 27", 6, demohyvitys(pistetaulu, -1, 0));  //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
    assertEquals("From: DemoMalli line: 28", 6, demohyvitys(pistetaulu, 2, -1));  //Maksimipistemäärää ei ole joten saadaan maxhyvitys automaattisesti
    assertEquals("From: DemoMalli line: 29", 0, demohyvitys(pistetaulu, -1, 10));  //Negatiiviset pisteet
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtumerkki52 */
  @Test
  public void testEtumerkki52() {    // DemoMalli: 52
    assertEquals("From: DemoMalli line: 53", 0, etumerkki(37-36.8, 0.3)); 
    assertEquals("From: DemoMalli line: 54", 1, etumerkki(1, 0.1)); 
    assertEquals("From: DemoMalli line: 55", -1, etumerkki(-1, 0.1)); 
    assertEquals("From: DemoMalli line: 56", 1, etumerkki(1, -0.1)); 
    assertEquals("From: DemoMalli line: 57", 1, etumerkki(1, 0)); 
    assertEquals("From: DemoMalli line: 58", 0, etumerkki(0, 0)); 
    assertEquals("From: DemoMalli line: 59", 0, etumerkki(0, 1)); 
  } // Generated by ComTest END
}