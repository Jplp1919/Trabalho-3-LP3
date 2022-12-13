package trab03;

import java.util.logging.Level;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import trab03.doc.DocumentReader;
import trab03.doc.DocumentWriter;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class BookFrame extends javax.swing.JFrame {

    static Logger logger = Logger.getLogger(BookFrame.class.getName());
    static FileHandler fh;

    public void addBookRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[6];

        ResultSet rs;
        String sql = "SELECT IDLIVROS, TITULO, GENERO, ISBN, PRECO, IDESCRITOR FROM LIVROS";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                rowData[0] = rs.getInt(1);
                rowData[1] = rs.getString(2);
                rowData[2] = rs.getString(3);
                rowData[3] = rs.getString(4);
                rowData[4] = rs.getDouble(5);
                int id = rs.getInt(6);
                List<String> nome = dao.getEscritorById(id);
                rowData[5] = nome.get(0);
                dtm.addRow(rowData);
            }

        }

    }

    public void addEscritorRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[3];
        ResultSet rs;
        String sql = "SELECT * FROM ESCRITOR";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                rowData[0] = rs.getInt(1);
                rowData[1] = rs.getString(2);
                rowData[2] = rs.getString(3);
                dtm.addRow(rowData);
            }

        }
    }

    public void addEstoqueRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) jTableEstoque.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[5];
        ResultSet rs;
        String sql = "SELECT TITULO, PRECO, IDESCRITOR, IDEDITORA, IDLIVROS FROM LIVROS";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {

                rowData[0] = rs.getString(1);
                //System.out.println(rowData[0]);
                rowData[1] = dao.getEstoquePorId(Integer.toString(rs.getInt(5)));
                rowData[2] = rs.getDouble(2);

                List<String> nome = dao.getEscritorById(rs.getInt(3));

                rowData[3] = nome.get(0);

                rowData[4] = dao.getEditoraPorId(Integer.toString(rs.getInt(4)));
                dtm.addRow(rowData);
            }

        }
    }

    public void clearBooks() {
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        dtm.setRowCount(0);
    }

    public void clearEsotque() {
        DefaultTableModel dtm = (DefaultTableModel) jTableEstoque.getModel();
        dtm.setRowCount(0);
    }

    public void clearEscritores() {
        DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
        dtm.setRowCount(0);
    }

    public BookFrame() throws SQLException, IOException {
        initComponents();

        Connection con = new ConnectionFactory().establishConnection();
        addBookRows(con);
        addEscritorRows(con);
        addEstoqueRows(con);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEstoque = new javax.swing.JTable();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jButtonPlus = new javax.swing.JButton();
        jButtonMinus = new javax.swing.JButton();
        jButtonAtualizarQuantidade = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldEstoqueTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonCadastroBasico = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        escritorTable = new javax.swing.JTable();
        jButtonUpdateEscritor = new javax.swing.JButton();
        jTextFieldNomeAuthor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldSobrenomeAuthor = new javax.swing.JTextField();
        jButtonInserirEscritor = new javax.swing.JButton();
        jTextFieldIdEscritor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButtonDeleteEscritor = new javax.swing.JButton();
        xmlButtonLoadWriter = new javax.swing.JButton();
        xmlButtonExportWriter = new javax.swing.JButton();
        jButtonWriterRegister = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jTextFieldIdBook = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonDeleteBook = new javax.swing.JButton();
        xmlButtonBookLoad = new javax.swing.JButton();
        xmlButtonExportBook = new javax.swing.JButton();
        jButtonInserirLivro = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonCadastrarEditora = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Livro", "Estoque", "Preço", "Escritor", "Editora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEstoqueMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableEstoque);
        if (jTableEstoque.getColumnModel().getColumnCount() > 0) {
            jTableEstoque.getColumnModel().getColumn(0).setResizable(false);
            jTableEstoque.getColumnModel().getColumn(1).setResizable(false);
            jTableEstoque.getColumnModel().getColumn(2).setResizable(false);
            jTableEstoque.getColumnModel().getColumn(3).setResizable(false);
        }

        jTextFieldQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeActionPerformed(evt);
            }
        });

        jButtonPlus.setText("+");
        jButtonPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButtonMinus.setText("-");
        jButtonMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButtonAtualizarQuantidade.setText("Atualizar Quantidade");
        jButtonAtualizarQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarQuantidadeActionPerformed(evt);
            }
        });

        jLabel2.setText("Livro");

        jLabel4.setText("Quantidade");

        jButtonCadastroBasico.setText("Carregar Dados Basicos do Castro");
        jButtonCadastroBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastroBasicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jButtonAtualizarQuantidade))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextFieldEstoqueTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(175, 175, 175)
                                        .addComponent(jLabel4))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(jButtonCadastroBasico))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jButtonMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButtonPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastroBasico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEstoqueTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMinus)
                    .addComponent(jButtonPlus))
                .addGap(47, 47, 47)
                .addComponent(jButtonAtualizarQuantidade)
                .addContainerGap(313, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Estoque", jPanel3);

        jPanel2.setPreferredSize(new java.awt.Dimension(600, 629));

        escritorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sobrenome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        escritorTable.setFocusable(false);
        escritorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                escritorTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(escritorTable);

        jButtonUpdateEscritor.setText("Atualizar");
        jButtonUpdateEscritor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateEscritorActionPerformed(evt);
            }
        });

        jTextFieldNomeAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeAuthorActionPerformed(evt);
            }
        });

        jLabel19.setText("Nome");

        jLabel20.setText("Sobrenome");

        jTextFieldSobrenomeAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSobrenomeAuthorActionPerformed(evt);
            }
        });

        jButtonInserirEscritor.setText("Inserir");
        jButtonInserirEscritor.setToolTipText("");
        jButtonInserirEscritor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirEscritorActionPerformed(evt);
            }
        });

        jTextFieldIdEscritor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdEscritorActionPerformed(evt);
            }
        });

        jLabel22.setText("ID");

        jButtonDeleteEscritor.setText("Deletar");
        jButtonDeleteEscritor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEscritorActionPerformed(evt);
            }
        });

        xmlButtonLoadWriter.setForeground(new java.awt.Color(0, 204, 51));
        xmlButtonLoadWriter.setText("Carregar Xml");
        xmlButtonLoadWriter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlButtonLoadWriterActionPerformed(evt);
            }
        });

        xmlButtonExportWriter.setForeground(new java.awt.Color(255, 51, 51));
        xmlButtonExportWriter.setText("Exportar Xml");
        xmlButtonExportWriter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlButtonExportWriterActionPerformed(evt);
            }
        });

        jButtonWriterRegister.setText("Registrar Escritor");
        jButtonWriterRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriterRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(xmlButtonExportWriter)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonUpdateEscritor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonInserirEscritor))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonWriterRegister)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(31, 31, 31))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jTextFieldIdEscritor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jTextFieldNomeAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jTextFieldSobrenomeAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDeleteEscritor)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(xmlButtonLoadWriter))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(xmlButtonLoadWriter))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButtonWriterRegister)))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSobrenomeAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIdEscritor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeleteEscritor)
                    .addComponent(jButtonInserirEscritor)
                    .addComponent(jButtonUpdateEscritor))
                .addGap(27, 27, 27)
                .addComponent(xmlButtonExportWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Escritores", jPanel2);

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Titulo", "Genero", "ISBN", "Valor", "Escritor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookTable.setFocusable(false);
        bookTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bookTableFocusGained(evt);
            }
        });
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);
        if (bookTable.getColumnModel().getColumnCount() > 0) {
            bookTable.getColumnModel().getColumn(0).setResizable(false);
            bookTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            bookTable.getColumnModel().getColumn(1).setResizable(false);
            bookTable.getColumnModel().getColumn(2).setResizable(false);
            bookTable.getColumnModel().getColumn(3).setResizable(false);
            bookTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            bookTable.getColumnModel().getColumn(4).setResizable(false);
            bookTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jTextFieldIdBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdBookActionPerformed(evt);
            }
        });

        jLabel3.setText("ID");

        jButtonDeleteBook.setText("Deletar");
        jButtonDeleteBook.setToolTipText("");
        jButtonDeleteBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteBookActionPerformed(evt);
            }
        });

        xmlButtonBookLoad.setForeground(new java.awt.Color(0, 153, 51));
        xmlButtonBookLoad.setText("Carregar Xml");
        xmlButtonBookLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlButtonBookLoadActionPerformed(evt);
            }
        });

        xmlButtonExportBook.setForeground(new java.awt.Color(255, 0, 0));
        xmlButtonExportBook.setText("Exportar Xml");
        xmlButtonExportBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlButtonExportBookActionPerformed(evt);
            }
        });

        jButtonInserirLivro.setText("Inserir Livro");
        jButtonInserirLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirLivroActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jButtonCadastrarEditora.setText("Cadastrar Editora");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonCadastrarEditora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonInserirLivro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(213, 213, 213))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 469, Short.MAX_VALUE)
                .addComponent(xmlButtonExportBook)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldIdBook, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDeleteBook)))))
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(xmlButtonBookLoad)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(xmlButtonBookLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInserirLivro)
                    .addComponent(jButtonAtualizar))
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrarEditora)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeleteBook)
                    .addComponent(jTextFieldIdBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(xmlButtonExportBook)
                .addGap(30, 30, 30))
        );

        jTabbedPane2.addTab("Livros", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdBookActionPerformed

    private void jButtonDeleteBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteBookActionPerformed

        String StrId = jTextFieldIdBook.getText();
        int id = Integer.parseInt(StrId);
        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            dao.deleteLivro(id);
            DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
            clearBooks();
            clearEsotque();
            this.addBookRows(con);
            this.addEstoqueRows(con);
            logger.info("Livro Deletado");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        jTextFieldIdBook.setText("");

    }//GEN-LAST:event_jButtonDeleteBookActionPerformed

    private void jTextFieldNomeAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeAuthorActionPerformed

    private void jTextFieldSobrenomeAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSobrenomeAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSobrenomeAuthorActionPerformed

    private void jTextFieldIdEscritorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdEscritorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdEscritorActionPerformed

    private void jButtonDeleteEscritorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEscritorActionPerformed
        String Strid = jTextFieldIdEscritor.getText();
        int id = Integer.parseInt(Strid);

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            dao.deleteEscritor(id);
            DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
            dtm.setRowCount(0);
            this.addEscritorRows(con);
            logger.info("Escritor Deletado");
        } catch (SQLException ex) {

            logger.log(Level.SEVERE, null, ex);
        }
        jTextFieldIdEscritor.setText("");
        jTextFieldNomeAuthor.setText("");
        jTextFieldSobrenomeAuthor.setText("");
    }//GEN-LAST:event_jButtonDeleteEscritorActionPerformed

    private void jButtonInserirEscritorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirEscritorActionPerformed

        String nome = jTextFieldNomeAuthor.getText();
        String sobrenome = jTextFieldSobrenomeAuthor.getText();
        Escritor escritor = new Escritor(nome, sobrenome);
        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            dao.saveEscritor(escritor);
            DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
            dtm.setRowCount(0);
            this.addEscritorRows(con);
            logger.info("Escritor Adicionado");
        } catch (SQLException ex) {

            logger.log(Level.SEVERE, null, ex);
        }

        jTextFieldIdEscritor.setText("");
        jTextFieldNomeAuthor.setText("");
        jTextFieldSobrenomeAuthor.setText("");

    }//GEN-LAST:event_jButtonInserirEscritorActionPerformed

    private void jButtonUpdateEscritorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateEscritorActionPerformed

        String id = jTextFieldIdEscritor.getText();
        String nome = jTextFieldNomeAuthor.getText();
        String sobrenome = jTextFieldSobrenomeAuthor.getText();

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            dao.updateEscritor(id, nome, sobrenome);
            DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
            dtm.setRowCount(0);
            this.addEscritorRows(con);
            logger.info("Escritor Atualizado");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        jTextFieldIdEscritor.setText("");
        jTextFieldNomeAuthor.setText("");
        jTextFieldSobrenomeAuthor.setText("");


    }//GEN-LAST:event_jButtonUpdateEscritorActionPerformed

    private void xmlButtonBookLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlButtonBookLoadActionPerformed

        // String path = xmlLocationTextFieldBook.getText();
        DocumentReader dr = new DocumentReader();
        String pathLivros = "./livros.xml";
        //  if (path == null || path == "") {
        //  path = "./livros.xml";
        // }

        try {

            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            List<Livro> livros = dr.readLivros(pathLivros);

            dao.cleanTableBook();
            for (int i = 0; i < livros.size(); i++) {
                dao.saveLivro(livros.get(i));
            }
            // dao.saveLivro(livros.get());

            DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
            clearBooks();
            clearEsotque();
            this.addBookRows(con);
            this.addEstoqueRows(con);
            logger.info("Xml de Livro Carregado");
        } catch (XPathExpressionException | SAXException | ParserConfigurationException | IOException ex) {
            logger.log(Level.WARNING, null, ex);
        } catch (SQLException ex) {
            // new Message("Escritores nao cadastrados");
            logger.log(Level.WARNING, "Escritor nao cadastrado");

        }

    }//GEN-LAST:event_xmlButtonBookLoadActionPerformed

    private void xmlButtonLoadWriterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlButtonLoadWriterActionPerformed

        DocumentReader dr = new DocumentReader();
        String pathEscritores = "./escritores.xml";

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            List<Escritor> escritores = dr.readEscritores(pathEscritores);

            for (int i = 0; i < escritores.size(); i++) {
                dao.saveEscritorId(escritores.get(i));
            }

            DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
            dtm.setRowCount(0);
            this.addEscritorRows(con);
            logger.info("Xml de Escritores Carregado com Sucesso");
        } catch (XPathExpressionException | SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            logger.log(Level.WARNING, "Escritor(es) ja cadastrado(s)");
            // new Message("Escritores ja cadastrados");

        }


    }//GEN-LAST:event_xmlButtonLoadWriterActionPerformed

    private void xmlButtonExportWriterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlButtonExportWriterActionPerformed

        try {
            Connection con = new ConnectionFactory().establishConnection();
            DocumentWriter dw = new DocumentWriter(con);
            dw.writeEscritores();
            logger.info("Xml de Escritores Exportado com Sucesso");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_xmlButtonExportWriterActionPerformed

    private void xmlButtonExportBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlButtonExportBookActionPerformed

        try {
            Connection con = new ConnectionFactory().establishConnection();
            DocumentWriter dw = new DocumentWriter(con);
            dw.writeLivros();
            logger.info("Xml de Livro Exportado");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_xmlButtonExportBookActionPerformed

    private void bookTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bookTableFocusGained


    }//GEN-LAST:event_bookTableFocusGained

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);

            int index = bookTable.getSelectedRow();

            Object id = bookTable.getValueAt(index, 0);
            Object titulo = bookTable.getValueAt(index, 1);
            Object genero = bookTable.getValueAt(index, 2);
            Object isbn = bookTable.getValueAt(index, 3);
            Object valor = bookTable.getValueAt(index, 4);
            String nomeEscritor = bookTable.getValueAt(index, 5).toString();

            String[] partes = nomeEscritor.split(" ", 2);

            String nome = partes[0];
            String sobrenome = partes[1];
            int idEscritor = dao.getEscritorByNome(nome, sobrenome);
            jTextFieldIdBook.setText(id.toString());

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_bookTableMouseClicked

    private void escritorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escritorTableMouseClicked
        int index = escritorTable.getSelectedRow();

        Object id = escritorTable.getValueAt(index, 0);
        Object nome = escritorTable.getValueAt(index, 1);
        Object sobrenome = escritorTable.getValueAt(index, 2);

        jTextFieldIdEscritor.setText(id.toString());
        jTextFieldNomeAuthor.setText(nome.toString());
        jTextFieldSobrenomeAuthor.setText(sobrenome.toString());

    }//GEN-LAST:event_escritorTableMouseClicked

    private void jButtonInserirLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirLivroActionPerformed

        try {
            BookRegister br = new BookRegister(this);
            br.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonInserirLivroActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        try {
            BookUpdate bu = new BookUpdate(this);
            bu.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jTextFieldQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeActionPerformed

    }//GEN-LAST:event_jTextFieldQuantidadeActionPerformed

    private void jTableEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEstoqueMouseClicked
        int index = jTableEstoque.getSelectedRow();

        Object quantidade = jTableEstoque.getValueAt(index, 1);
        Object titulo = jTableEstoque.getValueAt(index, 0);
        jTextFieldQuantidade.setText(quantidade.toString());
        jTextFieldEstoqueTitulo.setText(titulo.toString());


    }//GEN-LAST:event_jTableEstoqueMouseClicked

    private void jButtonPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlusActionPerformed

        int quantidade = Integer.parseInt(jTextFieldQuantidade.getText());
        String titulo = jTextFieldEstoqueTitulo.getText();
        quantidade++;
        Connection con;
        try {
            con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            Livro livro = dao.getLivroByTitulo(titulo);
            // System.out.println(titulo + " " + livro.getId() + " " + quantidade);
            dao.updateEstoque(livro.getId(), quantidade);

            jTextFieldQuantidade.setText(Integer.toString(quantidade));
            clearEsotque();
            addEstoqueRows(con);
        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonPlusActionPerformed

    private void jButtonMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinusActionPerformed
        int quantidade = Integer.parseInt(jTextFieldQuantidade.getText());
        String titulo = jTextFieldEstoqueTitulo.getText();
        quantidade--;
        Connection con;
        jTextFieldQuantidade.setText(Integer.toString(quantidade));
        try {
            con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            Livro livro = dao.getLivroByTitulo(titulo);
            // System.out.println(titulo + " " + livro.getId() + " " + quantidade);
            if (quantidade >= 0) {
                dao.updateEstoque(livro.getId(), quantidade);
            } else {
                Message m = new Message("Estoque nao pode ser negativo");

            }

            clearEsotque();
            addEstoqueRows(con);
        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonMinusActionPerformed

    private void jButtonAtualizarQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarQuantidadeActionPerformed
        int quantidade = Integer.parseInt(jTextFieldQuantidade.getText());
        String titulo = jTextFieldEstoqueTitulo.getText();
        Connection con;
        try {
            con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            Livro livro = dao.getLivroByTitulo(titulo);
            // System.out.println(titulo + " " + livro.getId() + " " + quantidade);

            if (quantidade >= 0) {
                dao.updateEstoque(livro.getId(), quantidade);
            } else {
                Message m = new Message("Estoque nao pode ser negativo");

            }

            jTextFieldQuantidade.setText(Integer.toString(quantidade));
            clearEsotque();
            addEstoqueRows(con);
        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAtualizarQuantidadeActionPerformed

    private void jButtonCadastroBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastroBasicoActionPerformed
        DocumentReader dr = new DocumentReader();
        String pathEscritores = "./escritores.xml";

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            List<Escritor> escritores = dr.readEscritores(pathEscritores);

            for (int i = 0; i < escritores.size(); i++) {
                dao.saveEscritorId(escritores.get(i));
            }

            DefaultTableModel dtm = (DefaultTableModel) escritorTable.getModel();
            dtm.setRowCount(0);
            this.addEscritorRows(con);
            logger.info("Xml de Escritores Carregado com Sucesso");
        } catch (XPathExpressionException | SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            logger.log(Level.WARNING, "Escritor(es) ja cadastrado(s)");
            // new Message("Escritores ja cadastrados");

        }

        //DocumentReader dr = new DocumentReader();
        String pathLivros = "./livros.xml";
        //  if (path == null || path == "") {
        //  path = "./livros.xml";
        // }

        try {

            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            List<Livro> livros = dr.readLivros(pathLivros);

            dao.cleanTableBook();
            for (int i = 0; i < livros.size(); i++) {
                dao.saveLivro(livros.get(i));
            }
            // dao.saveLivro(livros.get());

            DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
            dtm.setRowCount(0);
            clearEsotque();
            this.addEstoqueRows(con);
            this.addBookRows(con);
            logger.info("Xml de Livro Carregado");
        } catch (XPathExpressionException | SAXException | ParserConfigurationException | IOException ex) {
            logger.log(Level.WARNING, null, ex);
        } catch (SQLException ex) {
            // new Message("Escritores nao cadastrados");
            logger.log(Level.WARNING, "Escritor nao cadastrado");

        }


    }//GEN-LAST:event_jButtonCadastroBasicoActionPerformed

    private void jButtonWriterRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriterRegisterActionPerformed

        try {
            WriterRegister wr = new WriterRegister(this);
            wr.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(BookFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButtonWriterRegisterActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fh = new FileHandler("BookFrame.log", true);
                    logger.addHandler(fh);
                    new BookFrame().setVisible(true);

                } catch (SQLException | IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookTable;
    private javax.swing.JTable escritorTable;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonAtualizarQuantidade;
    private javax.swing.JButton jButtonCadastrarEditora;
    private javax.swing.JButton jButtonCadastroBasico;
    private javax.swing.JButton jButtonDeleteBook;
    private javax.swing.JButton jButtonDeleteEscritor;
    private javax.swing.JButton jButtonInserirEscritor;
    private javax.swing.JButton jButtonInserirLivro;
    private javax.swing.JButton jButtonMinus;
    private javax.swing.JButton jButtonPlus;
    private javax.swing.JButton jButtonUpdateEscritor;
    private javax.swing.JButton jButtonWriterRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableEstoque;
    private javax.swing.JTextField jTextFieldEstoqueTitulo;
    private javax.swing.JTextField jTextFieldIdBook;
    private javax.swing.JTextField jTextFieldIdEscritor;
    private javax.swing.JTextField jTextFieldNomeAuthor;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldSobrenomeAuthor;
    private javax.swing.JButton xmlButtonBookLoad;
    private javax.swing.JButton xmlButtonExportBook;
    private javax.swing.JButton xmlButtonExportWriter;
    private javax.swing.JButton xmlButtonLoadWriter;
    // End of variables declaration//GEN-END:variables

}