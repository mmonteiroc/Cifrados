import org.junit.Test;

import static org.junit.Assert.*;

public class VigenereTest {
    @Test
    public void doTests() {
        assertEquals("BBB", Vigenere.encode("AAA", "AAA"));
        assertEquals("ZZZ", Vigenere.encode("YYY", "AAA"));
        assertEquals("BCD", Vigenere.encode("ABC", "AA"));
        assertEquals("CPM VFE", Vigenere.encode("BEN FET", "AKYP"));
        assertEquals("YQ USNCT WK WYWEWMF", Vigenere.encode("El Camió és vermell", "terra"));
        assertEquals("DESBC", Vigenere.encode("CDRAB", "AAA"));
        assertEquals("VOUMYSLVQVY", Vigenere.encode("JFRHDGCSLAM", "LICEU"));
        assertEquals("AAA", Vigenere.encode("ZZZ", "AAA"));
        assertEquals("NWGU US EPA EUM", Vigenere.encode("AVUI FA BON DIA", "MALLORCA"));
        assertEquals("YM SUGLBOSF PJBQOYSZ E'IU SZUOPFIFBN NJXUYT QQHUEZE", Vigenere.encode("El xifratge Vigenere s'ha reinventat moltes vegades", "taula"));
        assertEquals("XZFNPC PB RW AXOCLU ARCRDRE JIB FBRJCRP MP UX ETZRRANARL XK ND YLC EALKLA FWQXOVLLFX CNIJERSJ L UX LZVRWTCXC NXJ, YPA BGPVMUP, NIB OJOAPAP JYDKLTB, IND YORYLFYLUP YZUFCTZRND, NIB AALSPLQND LLU·WJYXCJQRFB NDP B'BBEJK MFNKC L CBAXN F UPB QJDZRND YBWONKCD MB APBLUOAB NY UBB BDXUD YLMPD XSFMXA",
                Vigenere.encode("Aquest és un portal dirigit als usuaris de la Viquipèdia on es pot trobar informació relativa a la comunitat com, per exemple, els darrers anuncis, les principals polítiques, els projectes col·laboratius que s'estan duent a terme i les tasques pendents de resoldre en les quals podeu ajudar", "wiki"));


        assertEquals("PJ DGWWCBLJGRP VNP OFL", Vigenere.encode("La constatació del fet", "diari"));
        assertEquals("UA PNVJW, RKT OXHCLOKPDJ YU SSEEQA XRD 16", Vigenere.encode("El debat, que continuarà el proper dia 16", "política"));

        assertEquals("QAFJE C DUWYSXBZU UJIUMITFCULGS WFT BQHK YORTTFNBYGSLM", Vigenere.encode("Podem i Ciutadans protagonitzen els pocs enfrontaments", "albert"));
        assertEquals("RMW IDM UBR UXMADMVRX RTTWLMNMQWWX", Vigenere.encode("Els qui han coincidit especialment", "Madrid"));
        assertEquals("LA INFWZINJ LOTQA MS FLRKSWUBI", Vigenere.encode("El ministre també ha reconegut", "govern"));
        assertEquals("(PP QXUWF RFD F BKLCKAX) J ZXO EQ USWLVBD (FUWGEJA FMK TRSQSFFMU)", Vigenere.encode("(no estem per a assajos) i hem de prestar (atenció als aprenents)", "balears"));
        assertEquals("MEXN KXXX", Vigenere.encode("AVUI PLOU", "LICEU"));
    }

    @Test
    public void reverseTests() {
        assertEquals("AAA", Vigenere.decode("BBB", "AAA"));
        assertEquals("ABC", Vigenere.decode("BCD", "AA"));
        assertEquals("BEN FET", Vigenere.decode("CPM VFE", "AKYP"));
        assertEquals("AVUI FA BON DIA", Vigenere.decode("NWGU US EPA EUM", "MALLORCA"));
        assertEquals("(NO ESTEM PER A ASSAJOS) I HEM DE PRESTAR (ATENCIO ALS APRENENTS)", Vigenere.decode("(PP QXUWF RFD F BKLCKAX) J ZXO EQ USWLVBD (FUWGEJA FMK TRSQSFFMU)", "balears"));
        assertEquals("EL MINISTRE TAMBE HA RECONEGUT", Vigenere.decode("LA INFWZINJ LOTQA MS FLRKSWUBI", "govern"));
    }

}