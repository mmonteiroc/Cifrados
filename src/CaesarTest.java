import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarTest {
    @Test
    public void cypher() throws Exception {
        assertEquals("BCD", Caesar.cypher("ABC", 1));
        assertEquals("BCD", Caesar.cypher("abc", 1));
        assertEquals("YZA", Caesar.cypher("XYZ", 1));
        assertEquals("Z", Caesar.cypher("A", 25));
        assertEquals("KLM", Caesar.cypher("ABC", 10));
        assertEquals("WXY", Caesar.cypher("ABC", 100));
        assertEquals("QXE BMFDAZE PQ OAZEGY P’MNMZE PQ XM ODUEU DQZQUJQZ, EQSAZE QXE QJBQDFE, U QX ZMPMX QZ EQDÀ XM BDAHM, MYN X’UZSDQPUQZF MRQSUF PQX NAAY PQX OAYQDÇ QXQOFDÒZUO",
                Caesar.cypher("Els patrons de consum d’abans de la crisi reneixen, segons els experts, i el Nadal en serà la prova, amb l’ingredient afegit del boom del comerç electrònic", 12));
        assertEquals("LZW USWKSJ UAHZWJ, SDKG CFGOF SK S KZAXL UAHZWJ, AK GFW GX LZW KAEHDWKL XGJEK GX WFUJQHLAGF. AL AK S KMTKLALMLAGF UAHZWJ OZWJW WSUZ DWLLWJ AF LZW GJAYAFSD EWKKSYW (USDDWV LZW HDSAFLWPL) AK JWHDSUWV OALZ S DWLLWJ UGJJWKHGFVAFY LG S UWJLSAF FMETWJ GX DWLLWJK MH GJ VGOF AF LZW SDHZSTWL.",
                Caesar.cypher("The Caesar cipher, also known as a shift cipher, is one of the simplest forms of encryption. It is a substitution cipher where each letter in the original message (called the plaintext) is replaced with a letter corresponding to a certain number of letters up or down in the alphabet.", 1500));

        assertEquals("Z'OAWQ VO RS GSF QCA SZG RWBSFG, EIS OPOBG RS BSQSGGWHOF-ZC, GS GOD SZ JOZCF EIS HÉ.", Caesar.cypher("L'amic ha de ser com els diners, que abans de necessitar-lo, se sap el valor que té.", 66));
    }

    @Test
    public void decypher() throws Exception {
        assertEquals("ABC", Caesar.decypher("BCD", 1));
        assertEquals("ZAB", Caesar.decypher("ABC", 1));
        assertEquals("A", Caesar.decypher("Z", 25));
        assertEquals("ELS PATRONS DE CONSUM D’ABANS DE LA CRISI RENEIXEN, SEGONS ELS EXPERTS, I EL NADAL EN SERÀ LA PROVA, AMB L’INGREDIENT AFEGIT DEL BOOM DEL COMERÇ ELECTRÒNIC",
                Caesar.decypher("QXE BMFDAZE PQ OAZEGY P’MNMZE PQ XM ODUEU DQZQUJQZ, EQSAZE QXE QJBQDFE, U QX ZMPMX QZ EQDÀ XM BDAHM, MYN X’UZSDQPUQZF MRQSUF PQX NAAY PQX OAYQDÇ QXQOFDÒZUO", 12));

        assertEquals("L'AMIC HA DE SER COM ELS DINERS, QUE ABANS DE NECESSITAR-LO, SE SAP EL VALOR QUE TÉ.",
                Caesar.decypher("Z'OAWQ VO RS GSF QCA SZG RWBSFG, EIS OPOBG RS BSQSGGWHOF-ZC, GS GOD SZ JOZCF EIS HÉ.", 66));

    }

    @Test
    public void magic() throws Exception {
        assertEquals("EL SECTOR SECUNDARI REPRESENTA EL 2,8% DE LA DEMANDA, MENTRE QUE EL 4,1% DEL TOTAL REGA ELS CAMPS",
                Caesar.magic("NU BNLCXA BNLDWMJAR ANYANBNWCJ NU 2,8% MN UJ MNVJWMJ, VNWCAN ZDN NU 4,1% MNU CXCJU ANPJ NUB LJVYB",
                        "El clima en la costa i el centre de Carolina del Nord és similar al clima de Geòrgia i Carolina del Sud, mentre que el clima en les muntanyes de l'oest és similar que el de Nova Anglaterra."));

        assertEquals("À MEDIDA QUE SE VIAJA, AS CADEIAS MONTANHOSAS E OS VALES DÃO LUGAR GRADUALMENTE A TERRENOS RELATIVAMENTE PLANO. A REGIÃO DA CAROLINA DO NORTE PRÓXIMA AO OCEANO ATLÂNTICO É COBERTA POR DIVERSOS PÂNTANOS E LAGOS",
                Caesar.magic("À ASRWRO EIS GS JWOXO, OG QORSWOG ACBHOBVCGOG S CG JOZSG RÃC ZIUOF UFORIOZASBHS O HSFFSBCG FSZOHWJOASBHS DZOBC. O FSUWÃC RO QOFCZWBO RC BCFHS DFÓLWAO OC CQSOBC OHZÂBHWQC É QCPSFHO DCF RWJSFGCG DÂBHOBCG S ZOUCG",
                        "A geografia da Carolina do Norte é variada. A região oeste do estado possui um terreno altamente acidentado, coberto por diversas altas cadeias montanhosas e vales profundos"));

        assertEquals("AQUESTA SETMANA SE CELEBRA LA SETMANA EUROPA DE LA PREVENCIÓ DE RESIDUS",
                Caesar.magic("RHLVJKR JVKDRER JV TVCVSIR CR JVKDRER VLIFGR UV CR GIVMVETZÓ UV IVJZULJ",
                        "El còrtex motor comprèn les àrees del còrtex cerebral(o escorça cerebral) responsables dels processos de planificació, control i execució de les funcions motores voluntàries."));
    }

}