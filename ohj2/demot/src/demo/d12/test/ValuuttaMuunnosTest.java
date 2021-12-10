package demo.d12.test;
// Generated by ComTest BEGIN
import demo.d12.ValuuttaMuunnos.*;
import fi.jyu.mit.ohj2.Suuntaaja;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.26 18:41:02 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ValuuttaMuunnosTest {



  // Generated by ComTest BEGIN
  /** testLisaa61 */
  @Test
  public void testLisaa61() {    // ValuuttaMuunnos: 61
    Valuutat valuutat = new Valuutat(); 
    valuutat.lisaa(1,"e"); 
    valuutat.lisaa(10,"SKr"); 
    valuutat.lisaa(1.6,"$"); 
    assertEquals("From: ValuuttaMuunnos line: 66", 1.6, valuutat.getKerroin("$"), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKerroin79 */
  @Test
  public void testGetKerroin79() {    // ValuuttaMuunnos: 79
    Valuutat valuutat = new Valuutat(); 
    valuutat.lisaa(1,"e"); 
    valuutat.lisaa(10,"SKr"); 
    valuutat.lisaa(1.6,"$"); 
    assertEquals("From: ValuuttaMuunnos line: 84", 1.6, valuutat.getKerroin("$"), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetValuutannimi97 */
  @Test
  public void testGetValuutannimi97() {    // ValuuttaMuunnos: 97
    Valuutat valuutat = new Valuutat(); 
    valuutat.lisaa(1,"e"); 
    valuutat.lisaa(10,"SKr"); 
    valuutat.lisaa(1.6,"$"); 
    assertEquals("From: ValuuttaMuunnos line: 102", "e", valuutat.getValuutannimi("e")); 
    assertEquals("From: ValuuttaMuunnos line: 103", "$", valuutat.getValuutannimi("$")); 
    assertEquals("From: ValuuttaMuunnos line: 104", "", valuutat.getValuutannimi("s")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetValuuttojenNimet196 */
  @Test
  public void testGetValuuttojenNimet196() {    // ValuuttaMuunnos: 196
    Valuutat valuutat = new Valuutat(); 
    valuutat.lisaa(1,"e"); 
    valuutat.lisaa(1.6,"$"); 
    valuutat.lisaa(10,"SKr"); 
    String nimet[] = valuutat.getValuuttojenNimet(); 
    assertEquals("From: ValuuttaMuunnos line: 202", 3, nimet.length); 
    assertEquals("From: ValuuttaMuunnos line: 203", "$", nimet[0]); 
    assertEquals("From: ValuuttaMuunnos line: 204", "SKr", nimet[1]); 
    assertEquals("From: ValuuttaMuunnos line: 205", "e", nimet[2]); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testValuuttaNaytto222 */
  @Test
  public void testValuuttaNaytto222() {    // ValuuttaMuunnos: 222
    Valuutat valuutat = new Valuutat(); 
    valuutat.lisaa(1,"e"); 
    valuutat.lisaa(10,"SKr"); 
    valuutat.lisaa(1.6,"$"); 
    ValuuttaNaytto naytto = new ValuuttaNaytto(valuutat); 
    Suuntaaja.StringInput si = new Suuntaaja.StringInput(""); 
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    si.input(""); assertEquals("From: ValuuttaMuunnos line: 234", false, naytto.kysy()); 
    si.input("loppu"); assertEquals("From: ValuuttaMuunnos line: 235", false, naytto.kysy()); 
    so.reset(); 
    si.input("3 SKr"); assertEquals("From: ValuuttaMuunnos line: 237", true, naytto.kysy()); 
    so.reset(); naytto.tulosta(); assertEquals("From: ValuuttaMuunnos line: 238", null, so.ero("3,00 SKr on 30,00 e\n")); 
    si.input("3 $"); assertEquals("From: ValuuttaMuunnos line: 239", true, naytto.kysy()); 
    so.reset(); naytto.tulosta(); assertEquals("From: ValuuttaMuunnos line: 240", null, so.ero("3,00 $ on 4,80 e\n")); 
    si.input("2 $"); assertEquals("From: ValuuttaMuunnos line: 241", true, naytto.kysy()); 
    so.reset(); naytto.tulosta(); assertEquals("From: ValuuttaMuunnos line: 242", null, so.ero("2,00 $ on 3,20 e\n")); 
    si.input("2 k lati"); assertEquals("From: ValuuttaMuunnos line: 243", true, naytto.kysy());  // Yksikköä ei löydy
    so.reset(); naytto.tulosta(); assertEquals("From: ValuuttaMuunnos line: 244", null, so.ero("2,00  on 2,00 e\n")); 
    si.input(""); assertEquals("From: ValuuttaMuunnos line: 245", false, naytto.kysy()); 
    si.palauta(); so.palauta(); 
  } // Generated by ComTest END
}