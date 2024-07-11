/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Acceso;
import Modelo.Factura;
import Modelo.LineaFactura;
import Modelo.Neumatico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class AltaFac extends javax.swing.JDialog {

    //IVA
    private static int IVA = 21;

    //Variable bandera para saber si han seleccionado una línea de la tabla
    private boolean lineIsSelected;

    //Objeto de acceso a la base de datos
    private Acceso con;

    //Objeto para acceso a variables fijas de factura
    private Factura invData;
    //Objeto para guardar los datos de la factura
    private Factura invoice;

    //Modelo para combobox
    DefaultComboBoxModel<String> dcbModelNif;//Modelo para NIF clientes
    DefaultComboBoxModel<String> dcbModelNeu;//Modelo para códigos neumático

    //Modelo para la JTable
    private DefaultTableModel jTableModel;

    //Listados
    List<String> myClientsNIF;
    List<Neumatico> myNeus;
    List<LineaFactura> invoiceLines;

    /**
     * Creates new form AltaFac
     */
    public AltaFac(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Inicializamos el controlador
        con = new Acceso();
        //Inicializamos los combobox
        initClientsComboBox();
        initNeuComboBox();
        //Inicializamos la variable bandera
        lineIsSelected = false;
        //Mensaje explicativo de uso
        JOptionPane.showMessageDialog(this, "Para dar de alta una factura debe seleccionar el NIF del cliente e ir añadiendo líneas a la misma. \nPara eliminar líneas seleccione la deseada antes en la tabla de la derecha. \nFinalmente pulse guardar para registrarla. Se vaciarán los campos y podrá\nañadir una nueva seleccionando los campos requeridos.");
    }

    /**
     * Método auxiliar para componer el combobox con los NIF de los clientes
     */
    public void initClientsComboBox() {
        //Inicializamos el modelo para el combobox
        dcbModelNif = new DefaultComboBoxModel<>();
        //Sacamos los datos
        myClientsNIF = con.clientsNIFList();
        for (String nif : myClientsNIF) {
            dcbModelNif.addElement(nif);
        }
        jCBNifClient.setModel(dcbModelNif);
    }

    /**
     * Método auxiliar para componer el combobox con los NIF de los clientes
     */
    public void initNeuComboBox() {
        //Inicializamos el modelo para el combobox
        dcbModelNeu = new DefaultComboBoxModel<>();
        //Sacamos los datos
        myNeus = con.readTableNeu();
        if (!myNeus.isEmpty()) {
            for (Neumatico neu : myNeus) {
                dcbModelNeu.addElement(String.valueOf(neu.getCod()));
            }
            jCBCodNeuNewInv.setModel(dcbModelNeu);
        } else {
            JOptionPane.showMessageDialog(this, "Lo sentimos, no existen neumáticos en la Base de Datos. \n¡Inserte su primer neumático en Menu > Alta Neumático.");
        }

    }

    public void initTable() {
        //Inicializamos modelo
        jTableModel = new DefaultTableModel();
        //Nombramos las columnas
        String[] columnNames = {"Cód-Neumático", "Concepto", "Cantidad", "Precio"};
        jTableModel.setColumnIdentifiers(columnNames);
        jTableModel.setRowCount(0);

        //Accedemos a los datos y construimos nuestro modelo
        if (invoiceLines != null && invoiceLines.size() != 0) {
            for (LineaFactura line : invoiceLines) {
                Object[] rowData = new Object[columnNames.length];
                rowData[0] = line.getCodNeu();
                rowData[1] = line.getConcept();
                rowData[2] = line.getNumNeu();
                rowData[3] = line.getPrice();
                jTableModel.addRow(rowData);
            }
        }

        this.jTNewInvLines.setModel(jTableModel);
    }

    public void invoiceTotalsCalculator() {
        double totalInv = 0;
        double taxAmount;
        double totalPlusTax;

        if (!invoiceLines.isEmpty()) {
            for (LineaFactura line : invoiceLines) {
                double totalPerLine = line.getNumNeu() * line.getPrice();
                totalInv += totalPerLine;
            }

            taxAmount = totalInv * ((double) IVA / 100.0);
            totalPlusTax = totalInv + taxAmount;

            jLBase.setText(String.valueOf(totalInv));
            jLTax.setText(String.valueOf(taxAmount));
            jLTotalAmountInv.setText(String.valueOf(totalPlusTax));
        } else {
            jLBase.setText("00.00");
            jLTax.setText("00.00");
            jLTotalAmountInv.setText("00.00");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCBNifClient = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTFNumNewInv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFDateNewInv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFNIF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFYearNewInv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCBCodNeuNewInv = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTFBrandNewInv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTFPriceNewInv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFIVANewInv = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFUNewInv = new javax.swing.JTextField();
        jBAddLine = new javax.swing.JButton();
        jBRemoveLine = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNewInvLines = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLBase = new javax.swing.JLabel();
        jLTax = new javax.swing.JLabel();
        jLTotalAmountInv = new javax.swing.JLabel();
        jBSaveInv = new javax.swing.JButton();
        jBCancelNewInv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("NUEVA FACTURA");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(102, 102, 255))); // NOI18N

        jLabel2.setText("NIF:");

        jCBNifClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBNifClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIF Cliente" }));
        jCBNifClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBNifClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jCBNifClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBNifClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 255))); // NOI18N

        jLabel3.setText("Núm-Factura:");

        jTFNumNewInv.setEditable(false);

        jLabel4.setText("Fecha-Actual:");

        jTFDateNewInv.setEditable(false);

        jLabel5.setText("NIF Emisor:");

        jTFNIF.setEditable(false);

        jLabel6.setText("Año:");

        jTFYearNewInv.setEditable(false);

        jLabel7.setText("Cód-Neumático:");

        jCBCodNeuNewInv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBCodNeuNewInv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código Neumático" }));
        jCBCodNeuNewInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBCodNeuNewInvActionPerformed(evt);
            }
        });

        jLabel8.setText("Marca:");

        jTFBrandNewInv.setEditable(false);

        jLabel9.setText("Precio Ud.:");

        jTFPriceNewInv.setEditable(false);

        jLabel10.setText("% IVA:");

        jTFIVANewInv.setEditable(false);

        jLabel11.setText("UNIDADES:");

        jTFUNewInv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFUNewInvKeyTyped(evt);
            }
        });

        jBAddLine.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBAddLine.setForeground(new java.awt.Color(0, 102, 0));
        jBAddLine.setText("AÑADIR LÍNEA");
        jBAddLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddLineActionPerformed(evt);
            }
        });

        jBRemoveLine.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBRemoveLine.setForeground(new java.awt.Color(204, 0, 0));
        jBRemoveLine.setText("ELIMINAR LÍNEA");
        jBRemoveLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoveLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jCBCodNeuNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(17, 17, 17)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTFDateNewInv, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jTFNumNewInv)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFPriceNewInv)
                                    .addComponent(jTFBrandNewInv)
                                    .addComponent(jTFUNewInv, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFNIF, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jTFYearNewInv)
                            .addComponent(jTFIVANewInv))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jBAddLine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jBRemoveLine)
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFNumNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTFNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFDateNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTFYearNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCBCodNeuNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jTFBrandNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTFPriceNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTFIVANewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFUNewInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAddLine)
                    .addComponent(jBRemoveLine))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LÍNEAS FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 255))); // NOI18N

        jTNewInvLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cód-Neumático", "Concepto", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTNewInvLines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTNewInvLinesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTNewInvLines);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("SUBTOTAL");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("IVA");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("TOTAL");

        jLBase.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLBase.setText("00.00");

        jLTax.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLTax.setText("00.00");

        jLTotalAmountInv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLTotalAmountInv.setText("00.00");

        jBSaveInv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBSaveInv.setForeground(new java.awt.Color(0, 102, 0));
        jBSaveInv.setText("GUARDAR");
        jBSaveInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSaveInvActionPerformed(evt);
            }
        });

        jBCancelNewInv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBCancelNewInv.setForeground(new java.awt.Color(204, 0, 0));
        jBCancelNewInv.setText("CANCELAR");
        jBCancelNewInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelNewInvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jBSaveInv)
                        .addGap(240, 240, 240)
                        .addComponent(jBCancelNewInv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBase)
                    .addComponent(jLTax)
                    .addComponent(jLTotalAmountInv))
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLBase))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLTax)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBSaveInv)
                            .addComponent(jBCancelNewInv))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLTotalAmountInv))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBCodNeuNewInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBCodNeuNewInvActionPerformed
        // Lo que ocurre cuando el usuario selecciona un código de neumático del combobox de códigos
        //Sacammos los datos del neumático seleccionado
        Neumatico tempNeu = myNeus.get(jCBCodNeuNewInv.getSelectedIndex());
        //Seteams los campos
        jTFBrandNewInv.setText(tempNeu.getBrand());
        jTFPriceNewInv.setText(String.valueOf(tempNeu.getPrice()));
        jTFIVANewInv.setText(String.valueOf(IVA));

        //Vaciamos cantidad
        jTFUNewInv.setText("");
    }//GEN-LAST:event_jCBCodNeuNewInvActionPerformed

    private void jCBNifClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBNifClientActionPerformed
        // Lo que ocurre cuando el usuario selecciona un nif de cliente
        // Se rellenan los datos de factura
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());//Sacamos la fecha actual
        int numInv = con.newInvNumber();//Extraemos el siguiente número disponible de factura
        if (numInv != 0) {//Si ha podido extraerlo será distinto de 0
            //Seteamos los campos de la vista
            jTFNumNewInv.setText(String.valueOf(numInv));
            invData = new Factura();
            jTFNIF.setText(invData.getNifCompany());
            jTFDateNewInv.setText(date);
            jTFYearNewInv.setText(date.substring(6));

            //Inicializamos un objeto factura con campos pendientes (se le meterán cuando el usuario termine de editar las líneas de factura)
            invoice = new Factura(myClientsNIF.get(jCBNifClient.getSelectedIndex()), jTFNIF.getText(), jTFDateNewInv.getText(), 0, 0, 0, false, invData.getBankAccount());

        } else if (numInv == 0) {
            //Seteamos los campos de la vista
            jTFNumNewInv.setText(String.valueOf(1));
            invData = new Factura();
            jTFNIF.setText(invData.getNifCompany());
            jTFDateNewInv.setText(date);
            jTFYearNewInv.setText(date.substring(6));

            //Inicializamos un objeto factura con campos pendientes (se le meterán cuando el usuario termine de editar las líneas de factura)
            invoice = new Factura(myClientsNIF.get(jCBNifClient.getSelectedIndex()), jTFNIF.getText(), jTFDateNewInv.getText(), 0, 0, 0, false, invData.getBankAccount());

        } else {
            JOptionPane.showMessageDialog(this, "Lo sentimos, error accediendo a la base de datos.");
        }
    }//GEN-LAST:event_jCBNifClientActionPerformed

    private void jBAddLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddLineActionPerformed
        // Cuando el usuario pulsa en el botón Añadir línea
        if (invoiceLines == null) {//Primera ejecución del botón, por si no ha sido inicializado
            invoiceLines = new ArrayList<>();
        }
        //Sacamos los datos de la nueva línea
        //Algunos los saco de la vista y otros del objeto neumático que está seleccionado
        String invNumTemp = jTFNumNewInv.getText();
        int invNum;
        try {
            invNum = Integer.parseInt(invNumTemp);
        } catch (NumberFormatException e) {
            //Encapsulamos el parseo para que no pete
            //Si meten letras, dado que lo igualamos a 0
            invNum = 0;
        }
        //Sacammos los datos del neumático seleccionado
        Neumatico tempNeu = myNeus.get(jCBCodNeuNewInv.getSelectedIndex());
        int codNeu = tempNeu.getCod();
        String conceptLine = tempNeu.getBrand();

        String tempQuantity = jTFUNewInv.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(tempQuantity);
        } catch (NumberFormatException e) {
            quantity = 0;
        }

        double price = tempNeu.getPrice();

        if ((invNum != 0) && (con.verifyNeuCod(codNeu)) && !(conceptLine.isEmpty()) && (quantity > 0) && (price != 0)) {
            //Si el número de factura no es cero, el código de neumático existe en la base de datos, el concepto no está vacío, la cantidad no es cero y el precio no es cero
            //Añadimos la línea a la lista
            invoiceLines.add(new LineaFactura(invNum, codNeu, conceptLine, quantity, price));
            //Actualizamos la tabla
            initTable();
            //Actualizamos los datos de la factura (esquina inferior derecha)
            invoiceTotalsCalculator();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el nif del cliente, el código de factura y la cantidad a comprar para añadir la línea. "
                    + "\nLa cantidad de neumáticos debe ser un número positivo distinto de cero."
                    + "\nPara salir sin guardar pulse CANCELAR.");
        }
    }//GEN-LAST:event_jBAddLineActionPerformed

    private void jBCancelNewInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelNewInvActionPerformed
        //Si la lista de líneas de factura no está vacía significa que el usuario no ha guardado los datos
        //Le preguntamos entonces si quiere salir sin guardar
        if (invoiceLines != null && !invoiceLines.isEmpty()) {
            //Tenemos que evaluar primero que no sea nulo para que si no ha sido inicializado y el usuario cierra la ventana no nos de un null pointer
            //Después evaluará la siguiente condición, si no es null
            int confirm = JOptionPane.showConfirmDialog(this, "Los datos introducidos no guardados se borrarán. ¿Desea salir sin guardar?", "Cierre de Alta Nueva Factura", JOptionPane.OK_CANCEL_OPTION);
            //Si acepta
            if (confirm == JOptionPane.OK_OPTION) {
                // Cerramos la ventana
                dispose();
            }
        } else {
            //En caso contrario cerramos directamente la ventana
            dispose();
        }

    }//GEN-LAST:event_jBCancelNewInvActionPerformed

    private void jBRemoveLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoveLineActionPerformed
        if (lineIsSelected && !jTFUNewInv.getText().isEmpty()) {//Si es true significa que el usuario ha seleccionado una línea
            //La segunda es para asegurar que el usuario no borre el número de neumáticos
            //Borramos de nuestra lista la línea con el mismo index de la seleccionada en la tabla
            invoiceLines.remove(jTNewInvLines.getSelectedRow());
            //Cargamos la tabla de nuevo
            initTable();
            //Actualizamos los datos de la factura (esquina inferior derecha)
            invoiceTotalsCalculator();
            //Volvemos a poner la variable bandera a false
            lineIsSelected = false;

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una línea de la tabla para ser eliminada.");
        }
    }//GEN-LAST:event_jBRemoveLineActionPerformed

    private void jTNewInvLinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTNewInvLinesMouseClicked
        // Cambiamos el valor de la variable bandera
        lineIsSelected = true;
        //Seteamos los campos relacionados con la línea de factura
        // Cuando hacemos click con el ratón en alguna de las filas
        int row = jTNewInvLines.rowAtPoint(evt.getPoint());//Cogemos el número de fila
        //Sacamos el código de neumático de la línea 
        int codNeu = Integer.parseInt(jTNewInvLines.getValueAt(row, 0).toString());
        //Recorremos la lista de objetos línea de factura y sacamos el resto de datos de la misma (son los mismos que la tabla)
        for (LineaFactura line : invoiceLines) {
            if (line.getCodNeu() == codNeu) {//La encuentra                
                int indexCodNeuComboBox = myNeus.indexOf(new Neumatico(codNeu));
                //Seteamos
                jCBCodNeuNewInv.setSelectedIndex(indexCodNeuComboBox);
                jTFBrandNewInv.setText(line.getConcept());
                jTFPriceNewInv.setText(String.valueOf(line.getPrice()));
                jTFIVANewInv.setText(String.valueOf(IVA));
                jTFUNewInv.setText(String.valueOf(line.getNumNeu()));
            }
        }

    }//GEN-LAST:event_jTNewInvLinesMouseClicked

    private void jBSaveInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSaveInvActionPerformed
        // Tenemos que guardar los datos de la factura y de las líneas que contiene
        //Variable para saber si todo bien
        boolean isInvInsertOk = false;
        //boolean isLinesInsertOk = false;
        if (invoiceLines != null && invoice != null && verifyAllIntroduced()) {
            //Si se han añadido líneas de factura y se ha inicializado el objeto factura
            //Procedemos a setear los campos de factura que nos faltan
            double baseInv = 0;
            for (LineaFactura line : invoiceLines) {
                baseInv += line.getPrice() * line.getNumNeu();
            }
            invoice.setBase(baseInv);
            double taxAmountInv = baseInv * ((double) IVA / 100.0);
            invoice.setTaxAmount(taxAmountInv);
            invoice.setTotal(baseInv + taxAmountInv);
            //Guardamos la factura en la base de datos

            isInvInsertOk = con.saveInvoice(invoice);

            if (isInvInsertOk) {//Si la factura se ha insertado correctamente, introducimos las líneas
                //Guardamos las líneas de factura en la base de datos
                for (LineaFactura line : invoiceLines) {
                    //isLinesInsertOk = con.saveLinesInvoice(line);
                    con.saveLinesInvoice(line);
                }
                //Reseteamos todo para introducir una nueva factura
                clearView();
            } else {
                JOptionPane.showMessageDialog(this, "Lo sentimos, no se ha podido guardar la factura en la Base de Datos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se han introducido los datos necesarios, confeccione la factura antes de guardar.");
        }

    }//GEN-LAST:event_jBSaveInvActionPerformed

    private void jTFUNewInvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFUNewInvKeyTyped
        // Limitar caracteres numéricos de entrada
        String udsNeuString = jTFUNewInv.getText().trim();
        for (int i = 0; i < udsNeuString.length(); i++) {
            if (Character.isLetter(udsNeuString.charAt(i))) {
                //Si es letra avisa y no deja escribir
                getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Cantidad incorrecto. Solamente se aceptan valores numéricos.");
                //Vaciamos el campo dado que es erróneo
                jTFUNewInv.setText("");
            }
        }
    }//GEN-LAST:event_jTFUNewInvKeyTyped

    public boolean verifyAllIntroduced() {
        //He cogido un par de text y la cantidad que debe introducir el usuario
        String numInvTemp = jTFNumNewInv.getText().trim();
        String dateTemp = jTFDateNewInv.getText().trim();
        String brandTemp = jTFBrandNewInv.getText().trim();
        String udsTemp = jTFUNewInv.getText().trim();

        if (numInvTemp.isEmpty() || dateTemp.isEmpty() || brandTemp.isEmpty() || udsTemp.isEmpty()) {
            //Si alguno está vacío significa que no ha seleccionado nada y devuelve falso
            return false;
        }
        return true;
    }

    /**
     * Método auxiliar para limpiar la vista tras guardar la factura
     */
    public void clearView() {
        //Recargamos la vista para introducir una nueva factura
        //Vaciamos la lista
        invoiceLines.clear();
        //Seteamos los campos de totales
        invoiceTotalsCalculator();
        //Inicializamos la tabla de líneas de factura      
        initTable();
        //Inicializamos los combobox
        initClientsComboBox();
        initNeuComboBox();
        //Vaciamos campos de texto
        jTFBrandNewInv.setText("");
        jTFDateNewInv.setText("");
        jTFIVANewInv.setText("");
        jTFNIF.setText("");
        jTFNumNewInv.setText("");
        jTFPriceNewInv.setText("");
        jTFUNewInv.setText("");
        jTFYearNewInv.setText("");
        //Inicializamos la variable bandera
        lineIsSelected = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AltaFac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaFac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaFac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaFac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AltaFac dialog = new AltaFac(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAddLine;
    private javax.swing.JButton jBCancelNewInv;
    private javax.swing.JButton jBRemoveLine;
    private javax.swing.JButton jBSaveInv;
    private javax.swing.JComboBox<String> jCBCodNeuNewInv;
    private javax.swing.JComboBox<String> jCBNifClient;
    private javax.swing.JLabel jLBase;
    private javax.swing.JLabel jLTax;
    private javax.swing.JLabel jLTotalAmountInv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFBrandNewInv;
    private javax.swing.JTextField jTFDateNewInv;
    private javax.swing.JTextField jTFIVANewInv;
    private javax.swing.JTextField jTFNIF;
    private javax.swing.JTextField jTFNumNewInv;
    private javax.swing.JTextField jTFPriceNewInv;
    private javax.swing.JTextField jTFUNewInv;
    private javax.swing.JTextField jTFYearNewInv;
    private javax.swing.JTable jTNewInvLines;
    // End of variables declaration//GEN-END:variables
}
