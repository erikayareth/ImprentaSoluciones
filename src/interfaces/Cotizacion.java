/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dao.CotizacionesDAO;
import dao.ProductosDAO;
import dao.VentasDAO;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pojo.Cotizaciones;
import pojo.Productos;
import pojo.Ventas;

/**
 *
 * @author Avril
 */
public class Cotizacion extends javax.swing.JPanel {

    int idp;
    int cant;
    double pre;
    double tpagar;
    JTableHeader th;
    TableRowSorter<TableModel> sorter;
    TableRowSorter<TableModel> sorter2;
    
    public Cotizacion() {
        initComponents();
        this.setBackground(Color.WHITE);
        Color fondo = new Color(24, 192, 221);
        jPanel3.setBackground(fondo);
        jPanel1.setBackground(Color.white);
        jPanel5.setBackground(Color.white);
        jPanel6.setBackground(Color.white);
        jPanel11.setBackground(Color.white);
        jPanel12.setBackground(Color.white);
        jPanel9.setBackground(Color.white);
        cargarModelo();
        jScrollPane1.getViewport().setBackground(Color.white);
        cargarModeloCotizacion();
        configureTable();
         th = jTable1.getTableHeader();
        Font fuente = new Font("TimesNewRoman", Font.PLAIN, 15);
        th.setFont(fuente);
        // cambia el fondo del encabezado de la tabla
        jTable1.getTableHeader().setBackground(fondo);
        // cambia el color de la letra del encabezado de la tabla
        jTable1.getTableHeader().setForeground(Color.BLACK);
    }

    public void cargarModelo() {
        ProductosDAO productosDAO = new ProductosDAO();
        DefaultTableModel dt = productosDAO.cargarModelo2();
        jTable2.setModel(dt);
        jTable2.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(dt);
        jTable2.setRowSorter(sorter);
    }
    
    void configureTable(){
        jTable1.setDefaultRenderer(Object.class, new MyJTableCellRenderer());
        jTable1.setRowHeight(20);
    }
    
    void cargarDialogo(JDialog dialogo, String nombre) {
        dialogo.setVisible(true);
        dialogo.setTitle(nombre);
        dialogo.setIconImage(new ImageIcon(this.getClass().getResource("/img/logovintage.png")).getImage());
        dialogo.setSize(496, 590);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
    }

    void cargarDialogo2(JDialog dialogo, String nombre) {
        dialogo.setVisible(true);
        dialogo.setTitle(nombre);
        dialogo.setIconImage(new ImageIcon(this.getClass().getResource("/img/logovintage.png")).getImage());
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
    }
    
    public void agregarBuscarProducto() {
        DefaultTableModel tabla2 = (DefaultTableModel) jTable1.getModel();
        int row = jTable2.getSelectedRow();
        String id = jTable2.getValueAt(row, 0).toString();
        String nombre = jTable2.getValueAt(row, 1).toString();
        String descripcion = jTable2.getValueAt(row, 2).toString();
        double precio = Double.parseDouble(jTable2.getValueAt(row, 3).toString());
        String tipov = jTable2.getValueAt(row, 4).toString();
        double cantidad = Double.parseDouble(jTable2.getValueAt(row, 5).toString());
        cant = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos desea llevar?"));
        Object object[] = {id, nombre, descripcion, precio, tipov, cant};
        tabla2.addRow(object);
        calcular();
    }
    
    public void consultarProducto(int id) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        ProductosDAO productosDAO = new ProductosDAO();
        Productos productos = productosDAO.seleccionar_producto(id);
        String nombre = productos.getNombre();
        String descripcion = productos.getDescripcion();
        String tipo = productos.getTipoDeVenta();
        double precio = productos.getPrecio();
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos desea llevar?"));
        Object f[] = {id, nombre, descripcion, precio, tipo, cantidad};
        String encabezados[] = {"ID", "Nombre", "Descripción", "Precio", "Tipo de venta", "Cantidad"};
        dtm.setColumnIdentifiers(encabezados);
        jTable1.setModel(dtm);
        dtm.addRow(f);
        calcular();
        System.out.println("entro");
    }

    void crearcomun() {
        DefaultTableModel dtm = new DefaultTableModel();
        String id = " ";
        String descripcion = "  ";
        String tipoVenta = "  ";
        String nombre = jTextField12.getText();
        int cantidad = Integer.parseInt(jTextField13.getText());
        double precio = Double.parseDouble(jTextField14.getText());

        Object f[] = {id, nombre, descripcion, precio, tipoVenta, cantidad};

        String encabezados[] = {"ID", "Nombre", "Descripcion", "precio", "Tipo Venta", "Cantidad"};
        dtm.setColumnIdentifiers(encabezados);
        jTable1.setModel(dtm);
        dtm.addRow(f);
        calcular();
        System.out.println("entro");
    }

    void calcular() {
        tpagar = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            cant = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
            pre = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            tpagar = tpagar + (cant * pre);
        }
        jLabel9.setText("" + tpagar + "0");
    }

    int crear() throws SQLException {
        CotizacionesDAO cd = new CotizacionesDAO();
        double total = Double.parseDouble(jLabel10.getText());
        String nombre = jTextField4.getText();
        double descuento = Double.parseDouble(jTextField3.getText());
        double subtotal = Double.parseDouble(jLabel9.getText());
        String telefono = jFormattedTextField1.getText();
//      double folio = 
        Cotizaciones c = new Cotizaciones(nombre, telefono, descuento, total, subtotal);
        int id = cd.insertar(c);
        return id;
    }
    
    public void vaciar4(){
        jTextField12.setText("Producto común");
        jTextField13.setText("");
        jTextField14.setText("");
    }
    
    public void limpiar() {
        jLabel10.setText("-----");
        jLabel9.setText("------");
        jTextField4.setText("");
        jTextField1.setText("");
        jFormattedTextField1.setText("");
        jTextField3.setText("0");
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel(); 
            int filas = jTable1.getRowCount();
            for(int i = 0; filas>i; i++){
                modelo.removeRow(0);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla");
        }
    }

    public void cargarModeloCotizacion() {
        CotizacionesDAO cotizacionesDAO = new CotizacionesDAO();
        DefaultTableModel dt = cotizacionesDAO.cargarModelo();
        jTable3.setModel(dt);
        jTable3.setAutoCreateRowSorter(true);
        sorter2 = new TableRowSorter<>(dt);
        jTable3.setRowSorter(sorter2);
    }

    public void filter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(jTextField6.getText().toUpperCase(), jComboBox2.getSelectedIndex()));
        } catch (Exception e) {
            System.out.println("Texto vacío " + e);
        }
    }
    
    public void filter2() {
        try {
            sorter2.setRowFilter(RowFilter.regexFilter(jTextField7.getText().toUpperCase(), jComboBox3.getSelectedIndex()));
        } catch (Exception e) {
            System.out.println("Texto vacío " + e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProductoComun = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        Buscar = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        VerCotizaciones = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(24, 192, 221));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PRODUCTOCOMUN2.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Descripción del producto: ");

        jTextField12.setText("Producto común");

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Cantidad");

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton17.setText("GUARDAR");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton18.setText("CANCELAR");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("x");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Precio");

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton18))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel17)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout ProductoComunLayout = new javax.swing.GroupLayout(ProductoComun.getContentPane());
        ProductoComun.getContentPane().setLayout(ProductoComunLayout);
        ProductoComunLayout.setHorizontalGroup(
            ProductoComunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ProductoComunLayout.setVerticalGroup(
            ProductoComunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(24, 192, 221));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARPRODUCTO2.png"))); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRE" }));

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("AGREGAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("CANCELAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCIÓN", "COSTO"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField6))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel7)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout BuscarLayout = new javax.swing.GroupLayout(Buscar.getContentPane());
        Buscar.getContentPane().setLayout(BuscarLayout);
        BuscarLayout.setHorizontalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(24, 192, 221));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/VERCOTIZACIONES2.png"))); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRE" }));

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton11.setText("CANCELAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre cliente", "Teléfono", "Descuento", "Total", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel11))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton11)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout VerCotizacionesLayout = new javax.swing.GroupLayout(VerCotizaciones.getContentPane());
        VerCotizaciones.getContentPane().setLayout(VerCotizacionesLayout);
        VerCotizacionesLayout.setHorizontalGroup(
            VerCotizacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        VerCotizacionesLayout.setVerticalGroup(
            VerCotizacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(24, 192, 221));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/COTIZACIONESr.png"))); // NOI18N
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("CÓDIGO DEL PRODUCTO");
        jPanel3.add(jLabel23);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1);

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton24.setText("AGREGAR PRODUCTO");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton24);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("BUSCAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("MAYOREO");
        jPanel3.add(jButton2);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("COMÚN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Precio", "Tipo de venta", "Cantidad"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("EXPORTAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("VER COTIZACIONES");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addGap(36, 36, 36))
        );

        jPanel6.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("TELÉFONO:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("NOMBRE DE CLIENTE:");

        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jFormattedTextField1)))
                .addContainerGap())
        );

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_END);

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("SUBTOTAL:");
        jPanel12.add(jLabel2);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel9.setText("--------");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel9KeyReleased(evt);
            }
        });
        jPanel12.add(jLabel9);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("DESCUENTO:");
        jPanel12.add(jLabel3);

        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel12.add(jTextField3);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("TOTAL:");
        jPanel12.add(jLabel8);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel10.setText("--------");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel10);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarDialogo(Buscar, "Buscar producto");
        Buscar.setDefaultCloseOperation(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarDialogo2(ProductoComun, "Producto Común");
          ProductoComun.setDefaultCloseOperation(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        ProductoComun.dispose();
        vaciar4();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Buscar.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String a = jTextField4.getText();
        int uno = a.length();
        String c = jFormattedTextField1.getText();
        int tres = c.length();
        String d = jLabel9.getText();
        int cuatro = d.length();
        String e = jLabel10.getText();
        int cinco = e.length();

        if (uno == 0  || tres == 0 || cuatro == 0|| cinco==0) {
            JOptionPane.showMessageDialog(null, "¡UY! Debes rellenar todos los campos");
//           limpiar();

        } else {
            
        int option = JOptionPane.showConfirmDialog(null, "¿Desea exportar la cotización?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == 0) {
            try {
                System.out.println("Ok");
                int id = crear();
                if (id != 0) {
                    JOptionPane.showMessageDialog(this, "Éxito al guardar la cotización");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Error");
                }
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this,"Error "+ ex);
            }
        } else {
            try {
                System.out.println("Ok");
                int id = crear();
                if (id != 0) {
                    JOptionPane.showMessageDialog(this, "Éxito al guardar la cotización");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Error");
                }
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this,"Error "+ ex);
            }
        }
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        String a = jTextField1.getText();
        int uno = a.length();
        if (uno == 0) {
            JOptionPane.showMessageDialog(this, "¡UY! Debes colocar el código del producto");
        } else {

            int id = Integer.parseInt(jTextField1.getText().toString());
            ProductosDAO productosDAO = new ProductosDAO();
            Productos productos = productosDAO.seleccionar_producto(id);
            int numero = productos.getIdProducto();
            if (id == productos.getIdProducto() && id >= 1) {
                consultarProducto(id);
                jTextField1.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No existe el id del producto");
            }

        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        double descuento = Double.parseDouble(jTextField3.getText());
        double tot = Double.parseDouble(jLabel9.getText());
        double sub = tot - descuento;
        jLabel10.setText(" " + sub);
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        cargarDialogo(VerCotizaciones, "Ver cotizaciones");
        VerCotizaciones.setDefaultCloseOperation(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        VerCotizaciones.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        String a = jTextField12.getText();
        int uno = a.length();

        String c = jTextField14.getText();
        int tres = c.length();
        String d = jTextField13.getText();
        int cuatro = d.length();

        if (uno == 0 || tres == 0 || cuatro == 0) {
            JOptionPane.showMessageDialog(null, "¡UY! Debes rellenar todos los campos");
//           limpiar();

        } else {
             crearcomun();
            ProductoComun.dispose();
            vaciar4();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
          char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo números");

        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jFormattedTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyTyped
        // TODO add your handling code here:
          char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo números");

        }
    }//GEN-LAST:event_jFormattedTextField1KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
     try{
         int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
         agregarBuscarProducto();
         Buscar.dispose();
      }catch(Exception e){
          JOptionPane.showMessageDialog(this, "¡UPS! Debes seleccionar un producto de la Tabla");
      }   
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jLabel9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(ProductoComun, "Ingresar solo números");
        }
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(ProductoComun, "Ingresar solo números");
        }
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        filter();
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        filter2();
    }//GEN-LAST:event_jTextField7KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Buscar;
    private javax.swing.JDialog ProductoComun;
    private javax.swing.JDialog VerCotizaciones;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
