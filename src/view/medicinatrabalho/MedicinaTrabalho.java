/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.medicinatrabalho;

import dao.DaoFornecedorCana;
import dao.DaoPaciente;
import dao.medicinatrabalho.DaoGuiMedicinaTrabalho;
import dao.medicinatrabalho.DaoTipoMedicinaTrabalho;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.FornecedorCana;
import model.Paciente;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import model.medicinatrabalho.TipoMedicinaTrabalho;
import view.DadosGlobais;

/**
 *
 * @author Ronaldo
 */
public class MedicinaTrabalho extends javax.swing.JFrame {

    /**
     * Creates new form MedicinaTrabalho
     */
    
    public void modoNaoEdicao(){
        TextID.setEnabled(true);
        TextData.setEnabled(false);
        TextAMBULATORIO.setEnabled(false);
        TextTipoMedicina.setEnabled(false);
        TextIDPACIENTE.setEnabled(false);
        
        btNovo.setEnabled(true);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(true);
        btExcluir.setEnabled(true);
        btEditar.setEnabled(true);
        
        TextPaciente.setEnabled(false);
        TextNasc.setEnabled(false);
        TextRG.setEnabled(false);
        TextCARTEIRADETRABPACIETE.setEnabled(false);
        TextSCartProfiss.setEnabled(false);
        TextIDFORNECEDOR.setEnabled(false);
        TextFornecedor.setEnabled(false);
        
        
    }
    
    public void modoEdicao(){
        TextID.setEnabled(false);
        TextData.setEnabled(true);
        TextAMBULATORIO.setEnabled(false);
        TextTipoMedicina.setEnabled(true);
        TextIDPACIENTE.setEnabled(true);
        
        btNovo.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        btExcluir.setEnabled(true);
        btEditar.setEnabled(false);
        
        TextPaciente.setEnabled(false);
        TextNasc.setEnabled(false);
        TextRG.setEnabled(false);
        TextCARTEIRADETRABPACIETE.setEnabled(false);
        TextSCartProfiss.setEnabled(false);
        TextIDFORNECEDOR.setEnabled(false);
        TextFornecedor.setEnabled(false);
    }
    
    public void limpar(){
        //TextID.setText("");
        TextData.setDate(null);
        TextAMBULATORIO.setText("");
        TextTipoMedicina.setSelectedIndex(0);
        limparPaciente();
        TextIDPACIENTE.setText(""); 
    }
    
    public void limparPaciente(){ 
        TextPaciente.setText("");
        TextNasc.setText("");
        TextRG.setText("");
        TextCARTEIRADETRABPACIETE.setText("");
        TextSCartProfiss.setText("");
        TextIDFORNECEDOR.setText("");
        TextFornecedor.setText("");
    }
    
    public void preencheTela(GuiaMedicinaTrabalho guiaMedicinaTrabalho){
        TextID.setText(guiaMedicinaTrabalho.getId().toString());
        TextData.setDate(guiaMedicinaTrabalho.getData());
        TextAMBULATORIO.setText(guiaMedicinaTrabalho.getAmbulatorio().getDescricao());
        TextTipoMedicina.setSelectedItem(guiaMedicinaTrabalho.getTipoMedicinaTrabalho());
        TextIDPACIENTE.setText(guiaMedicinaTrabalho.getPaciente().getId().toString());
        TextPaciente.setText(guiaMedicinaTrabalho.getPaciente().getNome());
        TextNasc.setText(guiaMedicinaTrabalho.getPaciente().getDataNacimento().toString());
        TextRG.setText(guiaMedicinaTrabalho.getPaciente().getRg());
        TextCARTEIRADETRABPACIETE.setText(guiaMedicinaTrabalho.getPaciente().getNumeroCarteiraTrabalho());
        TextSCartProfiss.setText(guiaMedicinaTrabalho.getPaciente().getSerieCateiraTrabalho());
        TextIDFORNECEDOR.setText(guiaMedicinaTrabalho.getFornecedorCana().getIDFornecedor().toString());
        TextFornecedor.setText(guiaMedicinaTrabalho.getFornecedorCana().getNome());
    }
    
    public void pesquisar(){
        if (!TextPesquisa.getText().equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DaoGuiMedicinaTrabalho daoGuiMedicinaTrabalho = new DaoGuiMedicinaTrabalho();
            List<GuiaMedicinaTrabalho> lista = new ArrayList<>();
            if (radioNumeroGuia.isSelected()){
                lista = daoGuiMedicinaTrabalho.listarPorNumero(Long.parseLong(TextPesquisa.getText()));
            }else if (RadioPaciente.isSelected()){
                lista = daoGuiMedicinaTrabalho.listarPorPacienteNome(TextPesquisa.getText());
            }else if (radioData.isSelected()){
                try {
                    lista = daoGuiMedicinaTrabalho.listarPorData(sdf.parse(TextPesquisa.getText()));
                } catch (ParseException ex) {
                    Logger.getLogger(MedicinaTrabalho.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Collections.sort(lista);
            DefaultTableModel tableModel = (DefaultTableModel) tableBusca.getModel();
            tableModel.setNumRows(0);
            for(GuiaMedicinaTrabalho m: lista){
                 tableModel.addRow(new String[]{
                     m.getId().toString(),
                     m.getData()!=null?sdf.format(m.getData()):"",
                     m.getAmbulatorio().getDescricao()!=null?m.getAmbulatorio().getDescricao():"", 
                     m.getPaciente()!=null && m.getPaciente().getNome()!=null?m.getPaciente().getNome():""});
             }
             //tableBusca = new JTable(tableModel);
        }
    }
    
    public MedicinaTrabalho() {
        initComponents();
        modoNaoEdicao();
        
        //Carrega a lista de tipo de medicina do trabalho
        DaoTipoMedicinaTrabalho daoTipoMedicinaTrabalho = new DaoTipoMedicinaTrabalho();
        List<TipoMedicinaTrabalho> tipos = daoTipoMedicinaTrabalho.listar();
        //Ordena em Ordem Alfabética
        Collections.sort(tipos);
        tipos.add(0, null);
        TextTipoMedicina.setModel(new DefaultComboBoxModel(tipos.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadioPesquisa = new javax.swing.ButtonGroup();
        panelTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TextID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TextTipoMedicina = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TextIDPACIENTE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TextCARTEIRADETRABPACIETE = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TextIDFORNECEDOR = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        TextNasc = new javax.swing.JTextField();
        TextRG = new javax.swing.JTextField();
        TextPaciente = new javax.swing.JTextField();
        TextSCartProfiss = new javax.swing.JTextField();
        TextFornecedor = new javax.swing.JTextField();
        TextAMBULATORIO = new javax.swing.JTextField();
        btNovo = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        btEmitirRelatório = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        TextData = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        RadioPaciente = new javax.swing.JRadioButton();
        radioNumeroGuia = new javax.swing.JRadioButton();
        radioData = new javax.swing.JRadioButton();
        TextPesquisa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBusca = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Aharoni", 0, 10)); // NOI18N

        panelTab.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(206, 233, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID");

        TextID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextIDKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("DATA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("AMBULATÓRIO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("TIPO MEDICINA DO TRABALHO");

        TextTipoMedicina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextTipoMedicinaActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("ID");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("PACIENTE");

        TextIDPACIENTE.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                TextIDPACIENTEInputMethodTextChanged(evt);
            }
        });
        TextIDPACIENTE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextIDPACIENTEKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("NASCIMENTO");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("RG");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("CARTEIRA TRABALHO");

        TextCARTEIRADETRABPACIETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextCARTEIRADETRABPACIETEActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("S. CART. PROFISSIONAL");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("ID");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("FORNECEDOR");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("DADOS PACIENTE");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("FORNECEDOR");

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setForeground(new java.awt.Color(0, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton1.setBorderPainted(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(TextNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(TextRG, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addComponent(TextCARTEIRADETRABPACIETE, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(247, 247, 247))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TextSCartProfiss)
                                .addGap(238, 238, 238))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextIDFORNECEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(TextFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextIDPACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextIDPACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextCARTEIRADETRABPACIETE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSCartProfiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextIDFORNECEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TextAMBULATORIO.setEnabled(false);
        TextAMBULATORIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextAMBULATORIOActionPerformed(evt);
            }
        });

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        btNovo.setToolTipText("NOVO");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        btSalvar.setToolTipText("SALVAR");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        btCancelar.setToolTipText("CANCELAR");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        btEditar.setToolTipText("EDITAR");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/imprimir.png"))); // NOI18N
        btImprimir.setToolTipText("IMPRIMIR RELATÓRIO");

        btEmitirRelatório.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluirr.png"))); // NOI18N
        btEmitirRelatório.setToolTipText("EMITIR RELATÓRIO ALTERANDO NOME");

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/170317123944_64.png"))); // NOI18N
        btExcluir.setToolTipText("EXCLUIR");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(TextData, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(TextAMBULATORIO, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextTipoMedicina, javax.swing.GroupLayout.Alignment.LEADING, 0, 377, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEmitirRelatório, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextAMBULATORIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextTipoMedicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btEmitirRelatório, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelTab.addTab("Guias", jPanel1);

        jPanel4.setBackground(new java.awt.Color(206, 234, 255));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Campo de Pesquisa");

        RadioPaciente.setBackground(new java.awt.Color(0, 204, 204));
        grupoRadioPesquisa.add(RadioPaciente);
        RadioPaciente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        RadioPaciente.setText("Paciente (Nome)");

        radioNumeroGuia.setBackground(new java.awt.Color(0, 204, 204));
        grupoRadioPesquisa.add(radioNumeroGuia);
        radioNumeroGuia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        radioNumeroGuia.setText("Número Guia");

        radioData.setBackground(new java.awt.Color(0, 204, 204));
        grupoRadioPesquisa.add(radioData);
        radioData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        radioData.setText("Data (Dia/Mês/Ano)");

        TextPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPesquisaActionPerformed(evt);
            }
        });
        TextPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextPesquisaKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Pesquisar");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(TextPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(radioNumeroGuia)
                                .addGap(18, 18, 18)
                                .addComponent(RadioPaciente)
                                .addGap(18, 18, 18)
                                .addComponent(radioData))
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadioPaciente)
                    .addComponent(radioNumeroGuia)
                    .addComponent(radioData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextPesquisa))
                .addGap(19, 19, 19))
        );

        tableBusca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Ambulatório", "Paciente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBuscaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBusca);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTab.addTab("Pesquisa", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTab)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTab)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (TextData.getDate()  == null){
            JOptionPane.showMessageDialog(null, "Informe a Data");
        }else if (TextTipoMedicina.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Informe o Tipo de Medicina do Trabalho");
        }else if (TextPaciente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Informe o Paciente");
        }else{
            DaoFornecedorCana daoFornecedorCana = new DaoFornecedorCana();
            FornecedorCana fornecedorCana = daoFornecedorCana.getById(Long.parseLong(TextIDFORNECEDOR.getText()));
            DaoPaciente daoPaciente = new DaoPaciente();
            Paciente paciente = daoPaciente.getById(Long.parseLong(TextIDPACIENTE.getText()));
            
            GuiaMedicinaTrabalho guiaMedicinaTrabalho = new GuiaMedicinaTrabalho();
            guiaMedicinaTrabalho.setAmbulatorio(DadosGlobais.ambulatorio);
            guiaMedicinaTrabalho.setUsuarioCadastro(DadosGlobais.usuarioLogado);
            guiaMedicinaTrabalho.setFornecedorCana(fornecedorCana);
            guiaMedicinaTrabalho.setPaciente(paciente);
            guiaMedicinaTrabalho.setTipoMedicinaTrabalho((TipoMedicinaTrabalho)TextTipoMedicina.getSelectedItem());
            guiaMedicinaTrabalho.setData(TextData.getDate());
            
            DaoGuiMedicinaTrabalho daoGuiMedicinaTrabalho = new DaoGuiMedicinaTrabalho();
            daoGuiMedicinaTrabalho.insert(guiaMedicinaTrabalho);
            
            modoNaoEdicao();
            limpar();
            
            if (guiaMedicinaTrabalho.getId()!=null){
                JOptionPane.showMessageDialog(null, "Guia registrada com sucesso!");
                preencheTela(guiaMedicinaTrabalho);
            }else{
                JOptionPane.showMessageDialog(null, "Problema ao inserir registro!");
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void TextAMBULATORIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextAMBULATORIOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextAMBULATORIOActionPerformed

    private void TextCARTEIRADETRABPACIETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextCARTEIRADETRABPACIETEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextCARTEIRADETRABPACIETEActionPerformed

    private void TextTipoMedicinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextTipoMedicinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTipoMedicinaActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
       modoEdicao();
       TextData.setDate(new Date());
       TextID.setText("NOVO");
       TextAMBULATORIO.setText(DadosGlobais.ambulatorio.getDescricao());
       limparPaciente();
       TextIDPACIENTE.setText("");
    }//GEN-LAST:event_btNovoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        modoNaoEdicao();
        limpar();
        TextID.setText("");
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        modoEdicao();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (!TextID.getText().equals("NOVO") && TextData.getDate()!=null){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Deseja excluir?","Atenção!",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                DaoGuiMedicinaTrabalho daoGuiMedicinaTrabalho = new DaoGuiMedicinaTrabalho();
                GuiaMedicinaTrabalho guiaMedicinaTrabalho = daoGuiMedicinaTrabalho.getById(Long.parseLong(TextID.getText()));
                daoGuiMedicinaTrabalho.delete(guiaMedicinaTrabalho);
                limpar();
                TextID.setText("");
                JOptionPane.showMessageDialog(null, "Guia excluída com sucesso!");
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void TextIDPACIENTEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextIDPACIENTEKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER) {
            if (!TextIDPACIENTE.equals("")){
                DaoPaciente daoPaciente = new DaoPaciente();
                Paciente p = daoPaciente.getById(Long.parseLong(TextIDPACIENTE.getText()));
                if (p!=null){
                    TextPaciente.setText(p.getNome());
                    TextRG.setText(p.getRg());
                    TextFornecedor.setText(p.getFornecedorCana().getNome());
                    TextIDFORNECEDOR.setText(p.getFornecedorCana().getIDFornecedor()+"");
                    TextNasc.setText(new SimpleDateFormat("dd/mm/yyyy").format(p.getDataNacimento()));
                    TextCARTEIRADETRABPACIETE.setText(p.getNumeroCarteiraTrabalho());
                    TextSCartProfiss.setText(p.getSerieCateiraTrabalho());
                }else{
                    JOptionPane.showMessageDialog(null, "Paciente não encontrado");
                }
                
            }
        }else if(evt.getKeyCode() == evt.VK_F2) {
            JOptionPane.showMessageDialog(null, "Teste");
        }else{
            limparPaciente();
        }
    }//GEN-LAST:event_TextIDPACIENTEKeyPressed

    private void TextIDPACIENTEInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_TextIDPACIENTEInputMethodTextChanged
        
    }//GEN-LAST:event_TextIDPACIENTEInputMethodTextChanged

    private void TextIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextIDKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER && !TextID.getText().equals("")){
            DaoGuiMedicinaTrabalho daoGuiMedicinaTrabalho = new DaoGuiMedicinaTrabalho();
            GuiaMedicinaTrabalho guiaMedicinaTrabalho = daoGuiMedicinaTrabalho.getById(Long.parseLong(TextID.getText()));
            if (guiaMedicinaTrabalho!=null){
                preencheTela(guiaMedicinaTrabalho);
            }else{
                JOptionPane.showMessageDialog(null, "Guia não encontrada!");
            }
        }else{
            limpar();
        }
    }//GEN-LAST:event_TextIDKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pesquisar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TextPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPesquisaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            pesquisar();
        }
    }//GEN-LAST:event_TextPesquisaKeyPressed

    private void TextPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPesquisaActionPerformed

    private void tableBuscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBuscaMouseClicked
       if (evt.getClickCount() == 2) {
          if (tableBusca.getRowCount() > 0){
              Long idGuia = Long.parseLong(tableBusca.getValueAt(tableBusca.getSelectedRow(), 0).toString());
              DaoGuiMedicinaTrabalho daoGuiMedicinaTrabalho = new DaoGuiMedicinaTrabalho();
              GuiaMedicinaTrabalho guiaMedicinaTrabalho = daoGuiMedicinaTrabalho.getById(idGuia);
              preencheTela(guiaMedicinaTrabalho);
              panelTab.setSelectedIndex(0);
          }
       }
    }//GEN-LAST:event_tableBuscaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioPaciente;
    private javax.swing.JTextField TextAMBULATORIO;
    private javax.swing.JTextField TextCARTEIRADETRABPACIETE;
    private com.toedter.calendar.JDateChooser TextData;
    private javax.swing.JTextField TextFornecedor;
    private javax.swing.JTextField TextID;
    private javax.swing.JTextField TextIDFORNECEDOR;
    private javax.swing.JTextField TextIDPACIENTE;
    private javax.swing.JTextField TextNasc;
    private javax.swing.JTextField TextPaciente;
    private javax.swing.JTextField TextPesquisa;
    private javax.swing.JTextField TextRG;
    private javax.swing.JTextField TextSCartProfiss;
    private javax.swing.JComboBox<String> TextTipoMedicina;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEmitirRelatório;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.ButtonGroup grupoRadioPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JRadioButton radioData;
    private javax.swing.JRadioButton radioNumeroGuia;
    private javax.swing.JTable tableBusca;
    // End of variables declaration//GEN-END:variables
}
