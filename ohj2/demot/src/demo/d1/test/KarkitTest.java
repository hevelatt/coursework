package demo.d1.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static demo.d1.Karkit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.05.30 20:07:26 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KarkitTest {


  // Generated by ComTest BEGIN
  /** testKeskiarvo35 */
  @Test
  public void testKeskiarvo35() {    // Karkit: 35
    assertEquals("From: Karkit line: 36", 22.666666, keskiarvo(new int[]{ 12, 0, 42, 14, 99, 12, 55 }, 0, 99 ), 0.000001); 
    assertEquals("From: Karkit line: 37", -5, keskiarvo(new int[]{ -5 }, -5, 99 ), 0.000001); 
  } // Generated by ComTest END
}