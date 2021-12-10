package live19.test;
// Generated by ComTest BEGIN
import java.io.ByteArrayOutputStream;
import static fi.jyu.mit.ohj2.VertaaTiedosto.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import live19.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.03 13:37:57 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SanatTest {



  // Generated by ComTest BEGIN
  /** testLisaa29 */
  @Test
  public void testLisaa29() {    // Sanat: 29
    ByteArrayOutputStream bs = new ByteArrayOutputStream(); 
    Sanat sanat = new Sanat(); 
    sanat.lisaa("kissa"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 35", null, vertaaString(bs, "kissa: 1\n")); 
    sanat.lisaa("kissa"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 37", null, vertaaString(bs, "kissa: 2\n")); 
    sanat.lisaa("kana"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 39", null, vertaaString(bs, "kissa: 2\nkana: 1\n")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTulosta58 */
  @Test
  public void testTulosta58() {    // Sanat: 58
    ByteArrayOutputStream bs = new ByteArrayOutputStream(); 
    Sanat sanat = new Sanat(); 
    sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 62", "", bs.toString()); 
    sanat.lisaa("kissa"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 64", null, vertaaString(bs, "kissa: 1\n")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKasitteleRivi78 */
  @Test
  public void testKasitteleRivi78() {    // Sanat: 78
    ByteArrayOutputStream bs  = new ByteArrayOutputStream(); 
    Sanat sanat = new Sanat(); 
    sanat.kasitteleRivi("kissa, kana kissa"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 82", null, vertaaString(bs,"kissa: 2\nkana: 1\n")); 
    sanat.kasitteleRivi("kissa istuu kana"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 84", null, vertaaString(bs,"kissa: 3\nkana: 2\nistuu: 1\n")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostoJaLaske98 
   * @throws IOException when error
   */
  @Test
  public void testLueTiedostoJaLaske98() throws IOException {    // Sanat: 98
    kirjoitaTiedosto("sanatLueKoe.txt","kissa kana kissa\nkissa istuu kana"); 
    ByteArrayOutputStream bs  = new ByteArrayOutputStream(); 
    Sanat sanat = new Sanat(); 
    sanat.lueTiedostoJaLaske("sanatLueKoe.txt"); sanat.tulosta(bs); 
    assertEquals("From: Sanat line: 105", null, vertaaString(bs,"kissa: 3\nkana: 2\nistuu: 1\n")); 
    tuhoaTiedosto("sanatLueKoe.txt"); 
  } // Generated by ComTest END
}