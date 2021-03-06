package demo.d9.test;
// Generated by ComTest BEGIN
import java.io.IOException;
import fi.jyu.mit.ohj2.VertaaTiedosto;
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d9.TiedostostaKopiointi.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.04 14:38:20 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TiedostostaKopiointiTest {



  // Generated by ComTest BEGIN
  /** 
   * testMain25 
   * @throws IOException when error
   */
  @Test
  public void testMain25() throws IOException {    // TiedostostaKopiointi: 25
    String mista = "testi1.txt"; 
    VertaaTiedosto.kirjoitaTiedosto(mista,"33 hiljaa 1 hiipii\nhyvä 33 tulee\n25 jep\n36 1 3 5 55\nnyt 33 riittää"); 
    String mihin = "testi2"; 
    VertaaTiedosto.tuhoaTiedosto(mihin); 
    main(new String[]{mista, mihin}); 
    String tulos = "33 hiljaa 1 hiipii\n36 1 3 5 55"; 
    assertEquals("From: TiedostostaKopiointi line: 35", null, VertaaTiedosto.vertaaFileString(mihin,tulos)); 
    VertaaTiedosto.tuhoaTiedosto(mihin); 
    VertaaTiedosto.tuhoaTiedosto(mista); 
  } // Generated by ComTest END
}