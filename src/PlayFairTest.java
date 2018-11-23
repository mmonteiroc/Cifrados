import org.junit.Test;

import static org.junit.Assert.*;

public class PlayFairTest {
    @Test
    public void test1() {
        assertEquals("HD IU PG", PlayFair.encrypt("MALETA", "LICEU"));
        assertEquals("PS IP IB CP QY", PlayFair.encrypt("ORDINADOR", "TAULA"));
        assertEquals("ID NU KC", PlayFair.encrypt("HOUSE", "DOCTOR"));
        assertEquals("AR HD KB RA", PlayFair.encrypt("REGISTER", "COMPUTER"));
        assertEquals("LG SD NS ZL", PlayFair.encrypt("KEYBOARD", "FAST TYPIST"));
    }

    @Test
    public void test2() {
        assertEquals("BM OD ZB XD NA BE KU DM UI XM MO UV IF", PlayFair.encrypt("Hide the gold in the tree stump", "playfair example"));
        assertEquals("RK PV FI GL PY HR PB HP RK DB RK HL UD QS", PlayFair.encrypt("The pen is mightier than the sword", "old saying"));
        assertEquals("KV SB HF YB HF RM VM ED", PlayFair.encrypt("No man is an island", "good advice"));
        assertEquals("QF HN NQ OD EY BQ CN YA OE HE RD", PlayFair.encrypt("Fortune favours the brave", "rich people"));
    }

    @Test
    public void test3() {
        assertEquals("RY AZ UA TE QC GP SG BR BG QA IN SY", PlayFair.encrypt("Two wrongs don't make a right.", "$iamgod$"));
        assertEquals("ZD NM BA LT AN CI KI BG TY CO KM FK", PlayFair.encrypt("When in Rome, do as the Romans.", "make no mistake!"));
        assertEquals("MV KH PY KG OP BQ NP", PlayFair.encrypt("I intend to do it", "really"));
    }

    @Test
    public void test4() {
        assertEquals("LP SR TU LB GI HK DX NI GQ BM XA GU MB EV",
                PlayFair.encrypt("Aquesta cançó és molt divertida", "alegre"));
        assertEquals("MD RN GA MW GA AM MK FX RL FM AT WF MF MU MK EA QB BR QN DV SF QI IF QI AT OB WM XS PE ME FT",
                PlayFair.encrypt("Les matemàtiques no mentixen, el que hi ha son molts matemàtics mentiders.", "pitágoras"));
        assertEquals("GH CO SH FQ HB OF SI GH KI AR NU GH BT IW KZ LF QF NF FH LF PZ QF DX CW FT KW",
                PlayFair.encrypt("La riquesa és com l'aigua salada, com més es beu, més set produïx.", "arthur"));
    }
    

    @Test
    public void rtest1() {
        assertEquals("MA LE TA", PlayFair.decrypt("HD IU PG", "LICEU"));
        assertEquals("LE XI TD EP EN DE LE SF OR CX",
                PlayFair.decrypt("SG WK YM HV GH GA SG OC FQ FY", "sofocles"));
        assertEquals("EL SA CR IF IC ID AV UI ES LE XI TD ED EM AX",
                PlayFair.decrypt("DM OU OQ KH QT BC NZ TH SC MD YH NC SE GW UZ", "desconegut"));
    }

    @Test
    public void rtest2() {
        assertEquals("QU IV OL FE RU NA CO SA TR OB AS EM PR EU NM IT IA QU IN OV OL FE RX RE ST RO BA SE MP RE UN AE XC US AX",
                PlayFair.decrypt("MX CR EH LP PW QI AV QC NE PA CQ PT RO PZ QN DN AC MX GW VE EH LP OW OP TM OV IC TV UB OP WM DO YA YM HO", "proverbi"));
        assertEquals("LA VI DA PO TS ER AV OR XR ID AS IN OP OS ES UN AM IC AD ES FO RC EN EL XL AX",
                PlayFair.decrypt("BM XQ BW QP ZO WS XU PS WQ KC EP KG PQ PO FZ ZH XA QI WB FZ LS QD FS MF MC XW", "maxwell"));

    }


}