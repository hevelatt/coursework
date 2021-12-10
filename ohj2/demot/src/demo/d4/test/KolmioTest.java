package demo.d4.test;

import static org.junit.Assert.*;
import org.junit.Test;
import demo.d4.Kolmio;

/**
 * Testataan Kolmio-luokan funktioita
 * @author Herman
 * @version 13.6.2020
 *
 */
public class KolmioTest {

    /** Testataan kolmion alan laskemista */
    @Test public void testKolmionAla() {
        assertEquals("Normaali tapaus", 4, Kolmio.kolmionAla(4, 2), 0.001);
        assertEquals("Negatiivinen tulos", 0, Kolmio.kolmionAla(4, -1), 0.001);
        assertEquals("Jos molemmat negatiivisia tulee validi tulos", 2, Kolmio.kolmionAla(-4, -1), 0.001);
        assertEquals("Toinen nolla", 0, Kolmio.kolmionAla(0, 2), 0.001);
    }


    /** Testataan hypotenuusan laskemista */
    @Test public void testHypotenuusa() {
        assertEquals("Normaali tapaus", 5, Kolmio.hypotenuusa(3, 4), 0.001);
        assertEquals("Negatiivinen kateetti", 5, Kolmio.hypotenuusa(3, -4), 0.001);
        assertEquals("Toinen nolla", 0, Kolmio.hypotenuusa(0, 2), 0.001);
    }

}
