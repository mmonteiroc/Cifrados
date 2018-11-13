import org.junit.Test;

import static org.junit.Assert.*;

public class TranspositionTest {
    @Test
    public void cypherTest() throws Exception {
        assertEquals("ACEGBDF", Transposition.cypher("ABCDEFG", 2));
        assertEquals("AFBGCDE", Transposition.cypher("ABCDEFG", 5));
        assertEquals("AEBFCGDH", Transposition.cypher("ABCDEFGH", 4));

        assertEquals("Latsmxeehnttieeeti eel a secnrseqnnn h m msts muot,qisomà i.màe i u olatmd",
                Transposition.cypher("Les matemàtiques no mentixen, el que hi ha son molts matemàtics mentiders.", 5));

        assertEquals("Leods só  rqr'lseelt ofeuiests aaa esaxn rgs nldr,nevharest e  tneò ane qiem .jmqctuauxlosaeuieateess' naan e n taesnt,cnf arvn ts ocadlao m  ininet r",
                Transposition.cypher("L'enveja en els hòmens mostra quant desgraciats se senten, i la seua constant atenció al que fan o deixen de fer els altres, mostra quant s'avorrixen.", 12));
    }

    @Test
    public void decypherTest() throws Exception {
        assertEquals("ABCDEFG",
                Transposition.decypher("ACEGBDF", 2));

        assertEquals("ABCDEFG",
                Transposition.decypher("ADGBECF", 3));

        assertEquals("La saviesa és filla de l'experiència.",
                Transposition.decypher("Lefeeaasi r. allis l'èaéaenvs xci dpi", 7));

        assertEquals("L'èxit polític és per aquells que perceben les necessitats públiques i saben satisfer-les.",
                Transposition.decypher("Llplrstias'íesc aqbfètr entueexi qbesenricauec s -t qenep sl éu  súiaepseplsb tso leeilsi.", 9));

    }

    @Test
    public void cypherTest2() throws Exception {
        assertEquals("BEADGCF", Transposition.cypher("ABCDEFG", "BAC"));
        assertEquals("LUAOQTH  AEL", Transposition.cypher("HOLA QUE TAL", "JCAO"));
        assertEquals("RCDEN IRL EDEFTASEEOEO. CW V AE", Transposition.cypher("WE ARE DISCOVERED. FLEE AT ONCE", "ZEBRAS"));
        assertEquals("Lnu    mçisreadsoa rrròi è el,e l i 'merlsacndapfoocébrg tdpaaaarut.", Transposition.cypher("La paciència és un arbre d'arrel amarga, però de fruits molt dolços.", "Proverbi"));

    }

    @Test
    public void decypherTest2() throws Exception {
        assertEquals("ABCDEFG", Transposition.decypher("BEADGCF", "BAC"));
        assertEquals("HOLA QUE TAL", Transposition.decypher("LUAOQTH  AEL", "JCAO"));
        assertEquals("WE ARE DISCOVERED. FLEE AT ONCE",
                Transposition.decypher("RCDEN IRL EDEFTASEEOEO. CW V AE", "ZEBRAS"));
        assertEquals("Espaiet i bona lletra, que el fer les coses bé, importa més que el fer-les.",
                Transposition.decypher("sil  s péeliotero,tqf.p lqf bosleenr  s aueabeuecér  sE  ,lesmm -taaelei er", "machado"));
    }
}