package demo.d3;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class JarjestysTest {

    @Test
    void testJarjesta1ja2() {
        assertEquals("Selkeä tapaus, kaksi sanaa", "asia esine", Jarjestys.jarjesta1ja2("asia esine"));
        assertEquals("Selkeä tapaus, kaksi sanaa", "asia esine", Jarjestys.jarjesta1ja2("esine asia"));
        assertEquals("Enemmän kuin kaksi sanaa", "asia esine", Jarjestys.jarjesta1ja2("esine asia aasia"));
        assertEquals("Yksi sana", "asia", Jarjestys.jarjesta1ja2("asia"));
        assertEquals("Ei mitään", "", Jarjestys.jarjesta1ja2(""));
        assertEquals("Välilyönti", "", Jarjestys.jarjesta1ja2(" "));
    }

}
