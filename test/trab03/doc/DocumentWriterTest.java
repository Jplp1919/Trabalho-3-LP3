package trab03.doc;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import trab03.ConnectionFactory;
import trab03.Escritor;
import trab03.PersistDAO;

public class DocumentWriterTest {
   Connection con;
   DocumentWriter dw;
    public DocumentWriterTest() {
    }

        @Before
    public void setUp() throws SQLException {
        con = new ConnectionFactory().establishConnection();
        con.setAutoCommit(false);
        dw = new DocumentWriter(con);
    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }
    
    @Test
    public void deveEscreverParaXmlCorretamente() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        dw.writeEscritores("./testexml/escritores.xml");
        File f = new File("./testexml/escritores.xml");
        assertNotNull(f);
    }

}
