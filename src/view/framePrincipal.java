
package view;

import controller.CtrlAlumno;
import model.Alumno;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel
 */
public class framePrincipal extends javax.swing.JFrame {
    // Declaracion e inicializacion de variables globales
    // Variables de clase
    // Una lista que guarda objetos de la clase alumno   
    public static ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    // Una instancia de un alumno    
    public static Alumno alumno;
    // Una instancia del contralador de alumnos    
    public static CtrlAlumno ca = new CtrlAlumno();   
    // Modelo de tabla
    public static DefaultTableModel modeloTabla;
    /* Tamano del registro del RAF de cada alumno que se compone de 
        4 bytes por la matricula
        200 bytes por un String de 100 caracteres
        20 bytes por un String de fecha de 10 caracteres
        8 bytes por un double de la nota media 
        4 bytes por la edad
        236 bytes en total por cada registro
    */
    public static final int TAMANO = 236;
    
    // Variables de objeto
    // Un número de matrícula de un alumno
    private int matricula;       
    // Fichero de acceso aleatorio
    private RandomAccessFile raf = null;    
    // Filtro de tabla
    private TableRowSorter<TableModel> filtroTabla;
        
    public framePrincipal() {
        initComponents();
        // Modificacion de las propiedades del JFrame
        // Cambiamos el titulo
        setTitle("Acceso a datos - Tema 01.Practicas");
        // No dejamos que cambie el tamano
        setResizable(false);
        // Centramos la ventana
        setLocationRelativeTo(null);        
        
        // Inicializacion de variables de la instancia       
        // Inicializamos el modeloTabla con la tabla
        modeloTabla = (DefaultTableModel) tablaAlumnos.getModel();
        // Inicializamos el filtroTabla con el modelo de la tabla
        filtroTabla = new TableRowSorter<>(modeloTabla);
        // Asignamos a la tabla el rowSorter
        tablaAlumnos.setRowSorter(filtroTabla);
                
        // Modificamos el modelo de seleccion de la tabla para poder modificar
        // a traves de una unica fila elegida
        tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Definicion de los adaptadores y oyentes
        // Anadimos un adaptador a la tabla que le asigna a la matricula 
        // el número del alumno de la fila elegida
        tablaAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaAlumnos.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    matricula = (int) tablaAlumnos.getValueAt(row, 0);                
                }
            }
        });
        
        // Solo permitimos al usuario que se cierre la aplicación cuando 
        // confirma que sabe que se pueden perder los datos introducidos
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){            
            @Override
            public void windowClosing(WindowEvent we) {
                if(listaAlumnos.size() > 0){
                    int respuesta = JOptionPane.showConfirmDialog(rootPane, "¿Está "
                        + "seguro que quiere cerrar la aplicación? Si no ha "
                        + "exportado la lista a un fichero se pueden perder "
                        + "datos.");
                    if(respuesta == 0){
                        System.exit(0);
                    }
                }else{
                    System.exit(0);
                }                
            }
        });                  
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnFiltro = new javax.swing.JToggleButton();
        btnEliminarFiltro = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox<>();
        txtNombreExport = new javax.swing.JTextField();
        btnElegirFicheroExport = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnFicheroExport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbOrigen = new javax.swing.JComboBox<>();
        txtFicheroImport = new javax.swing.JTextField();
        btnElegirFicheroImport = new javax.swing.JButton();
        btnFicheroImport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Alta.png"))); // NOI18N
        btnAlta.setToolTipText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Baja.png"))); // NOI18N
        btnBaja.setToolTipText("Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Modificar.png"))); // NOI18N
        btnModificar.setToolTipText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Modificar.png"))); // NOI18N
        btnFiltro.setSelected(true);
        btnFiltro.setToolTipText("Aplicar filtro");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        btnEliminarFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Sinfiltro.png"))); // NOI18N
        btnEliminarFiltro.setToolTipText("Sin filtrar");
        btnEliminarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFiltroActionPerformed(evt);
            }
        });

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nombre Alumno", "Fecha nacimiento", "Nota media", "Edad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        jPanel1.setName("Exportar a"); // NOI18N

        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Destino");

        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Nombre");

        cbDestino.setForeground(new java.awt.Color(153, 0, 0));
        cbDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fichero Objeto Serializable", "Fichero JSON (de texto)", "Fichero XML", "Fichero Directo" }));

        btnElegirFicheroExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ElegirFichero.png"))); // NOI18N
        btnElegirFicheroExport.setToolTipText("Elegir fichero");
        btnElegirFicheroExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirFicheroExportActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Exportar a");

        btnFicheroExport.setText("Exportar");
        btnFicheroExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicheroExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFicheroExport)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNombreExport)
                                .addGap(18, 18, 18)
                                .addComponent(btnElegirFicheroExport, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnElegirFicheroExport, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNombreExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnFicheroExport)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("Importar desde");

        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("Origen");

        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Fichero");

        cbOrigen.setForeground(new java.awt.Color(153, 0, 0));
        cbOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fichero Objeto Serializable", "Fichero JSON (de texto)", "Fichero XML", "Fichero Directo" }));

        txtFicheroImport.setEditable(false);

        btnElegirFicheroImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ElegirFichero.png"))); // NOI18N
        btnElegirFicheroImport.setToolTipText("Elegir fichero");
        btnElegirFicheroImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirFicheroImportActionPerformed(evt);
            }
        });

        btnFicheroImport.setText("Importar");
        btnFicheroImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicheroImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFicheroImport)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtFicheroImport)
                        .addGap(18, 18, 18)
                        .addComponent(btnElegirFicheroImport, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnElegirFicheroImport, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtFicheroImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnFicheroImport)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBaja, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModificar)
                    .addComponent(btnFiltro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarFiltro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlta))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Cuando se pulse el botón de Alta se abrirá un JDialog para pedir los 
     * datos del nuevo alumno que se añadirá tanto al ArrayList como al JTable.    
     * @param evt 
     */    
    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        // Instanciamos la vista 
        AltaAlumno alta = new AltaAlumno(this, true);
        // Modificamos sus propiedades
        alta.setTitle("Alta del alumnado");
        alta.setLocationRelativeTo(null);
        alta.setVisible(true);        
             
        // Rellenamos la tabla
        rellenarTabla(listaAlumnos);    
    }//GEN-LAST:event_btnAltaActionPerformed

    /** Cuando se pulse el botón de Baja se eliminará tanto del ArrayList 
     * como del JTable aquel registro que esté seleccionado en el JTable. Si no 
     * hay ninguno seleccionado, no se hace nada.     
     * @param evt 
     */
    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // Obtenemos la fila elegida por el usuario
        if(tablaAlumnos.getSelectedRow() != -1){
            // Comfirmamos que el usuario quiere eliminar el alumno de la lista
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que"
                        + " quiere dar de baja el alumno con el numero de expediente " 
                        + matricula + "?", "Dar de baja", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE,null);
            // Si ha dicho que si damos el alumno de baja
            if(opcion == 0){
                ca.bajaAlumno(matricula);
            }              
            // Rellenamos la tabla
            rellenarTabla(listaAlumnos); 
        }else{
            JOptionPane.showMessageDialog(this,"Debe elegir la fila de la tabla"
                    + " que desee dar de baja.");
        }       
    }//GEN-LAST:event_btnBajaActionPerformed
    
    /**
     * Cuando se pulse el botón de Modificar se abrirá un JDialog con los 
     * datos del alumno que esté seleccionado en el JTable (si no hay ninguno 
     * seleccionado, no se hace nada) y sobre estos se realizan las 
     * modificaciones. Al salir de este JDialog, tanto el ArrayList como el 
     * JTable deben estar actualizados.     
     * @param evt 
     */
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // Obtenemos la fila elegida por el usuario
        if(tablaAlumnos.getSelectedRow() != -1){
            int fila = tablaAlumnos.getSelectedRow();
            // Nos creamos un alumno auxiliar para poder rellenar los campos del
            // JDialog que se llamara posteriormente
            alumno = new Alumno(
                    Integer.parseInt(String.valueOf(tablaAlumnos.getValueAt(fila, 0))),
                    String.valueOf(tablaAlumnos.getValueAt(fila, 1)),                    
                    String.valueOf(tablaAlumnos.getValueAt(fila, 2)),
                    Double.parseDouble(String.valueOf(tablaAlumnos.getValueAt(fila, 3))),
                    Integer.parseInt(String.valueOf(tablaAlumnos.getValueAt(fila, 4))));            
            // Instanciamos una vista para la modificacion de los datos del 
            // alumno elegido 
            ModificacionAlumno modificacion = new ModificacionAlumno(this,true);
            modificacion.setTitle("Modificacion de los datos del alumno con la "
                    + "matricula " + matricula);
            // Modificamos sus propiedades 
            modificacion.setLocationRelativeTo(null);
            modificacion.setResizable(false);
            modificacion.setVisible(true);            

            // Rellenamos la tabla
            rellenarTabla(listaAlumnos);             
        }else{
            JOptionPane.showMessageDialog(this,"Debe elegir la fila de la tabla"
                    + " que desee modificar.");
        }    
    }//GEN-LAST:event_btnModificarActionPerformed
    
    /**
     * Filtrar o quitar el filtro. Cuando se pulse el botón de Filtrar se 
     * abrirá un JDialog en el que se pida algún criterio para filtrar los 
     * alumnos que aparecen en el JTable (en el ArrayList se siguen guardando 
     * todos los alumnos).
     * @param evt 
     */
    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        // Le pedimos al usuario el filtro
        String strFiltro = (String) JOptionPane.showInputDialog(this, "¿Cómo desea filtrar?");
        // Controlamos que se haya introducido un filtro
        if(strFiltro.length() == 0){
            filtroTabla.setRowFilter(null);
            JOptionPane.showMessageDialog(this,"Debe introducir primero una "
                    + "extension antes de poder filtrar",
                    "Instrucciones",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                // Aplicamos el filtro
                filtroTabla.setRowFilter(RowFilter.regexFilter(strFiltro));
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Se ha producido un error",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }
            // Modificamos el boton de eliminarFiltro
            btnEliminarFiltro.setSelected(false);
        }   
    }//GEN-LAST:event_btnFiltroActionPerformed

    /**
     * Cuando se pulse el botón de quitar el filtro en el JTable aparecerán 
     * todos los alumnos que hay en el ArrayList.
     * @param evt 
     */ 
    private void btnEliminarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFiltroActionPerformed
        // Eliminamos el filtro
        filtroTabla.setRowFilter(null);
        // Modificamos el boton de crearFiltro
        btnEliminarFiltro.setSelected(false);
    }//GEN-LAST:event_btnEliminarFiltroActionPerformed

    /**
     * Exportar. En cualquier momento se pueden guardar los datos de los alumnos 
     * que están en el JTABLE a un fichero de tipo plano (Fichero de Objetos 
     * Serializable o un fichero de texto). Este fichero se selecciona mediante 
     * un JFileChooser.     
     * @param evt 
     */
    private void btnElegirFicheroExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirFicheroExportActionPerformed
        // Nos creamos un JFC
        JFileChooser jfc = new JFileChooser();
        // Solo elige directorios
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            txtNombreExport.setText(jfc.getSelectedFile().getAbsolutePath());            
        }else{
            JOptionPane.showMessageDialog(this,"No ha seleccionado fichero");
            return;
        }                
    }//GEN-LAST:event_btnElegirFicheroExportActionPerformed

    /**
     * Importar. En cualquier momento se pueden añadir alumnos al ArrayList 
     * importándolos desde un fichero de tipo plano (Fichero de Objetos 
     * Serializable o un fichero de texto). Este fichero se selecciona mediante 
     * un JFileChooser y en el JTextField (no editable) de fichero se muestra el 
     * nombre/ruta del fichero seleccionado. 
     * @param evt 
     */
    private void btnElegirFicheroImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirFicheroImportActionPerformed
        // Nos creamos un JFC
        JFileChooser jfc = new JFileChooser();
        // Solo elegimos ficheros
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            txtFicheroImport.setText(jfc.getSelectedFile().getAbsolutePath());            
        }else{
            JOptionPane.showMessageDialog(this,"No ha seleccionado fichero");
            return;
        }          
    }//GEN-LAST:event_btnElegirFicheroImportActionPerformed

    /**
     * Al pulsar sobre el botón Importar (habiendo seleccionado un fichero) se 
     * traspasarán todos los alumnos almacenados en el fichero al ArraList de 
     * Alumnos (actualizando el JTable) cuyo número de matrícula no exista en 
     * el ArrayList.
     * @param evt 
     */
    private void btnFicheroImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicheroImportActionPerformed
        // Declaracion e inicializacion de variables
        File ficheroImport = null;         
        
        // Apuntamos al fichero elegido
        if(txtFicheroImport.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Debe introducir un fichero "
                    + "antes de poder importarlo.");
            return;
        } 
        
        // Asignamos el puntero que apunta a un fichero que queremos importar
        ficheroImport = new File(txtFicheroImport.getText());
        
        // En funcion del tipo de fichero elegido        
        switch(cbOrigen.getSelectedIndex()){
            // Si se elige un fichero de 8 bytes
            case 0:   
                importarSerializable(ficheroImport);
                break;
            // Si se elige un fichero de 16 bytes
            case 1:
                importarFicheroTexto(ficheroImport);                 
                break;
            // Si se elige un fichero de xml
            case 2:
                importarXML(ficheroImport);
                break;
            // Si se elige un fichero de acceso directo
            case 3:                
                importarRAF(ficheroImport);
                break;
        }           
        
        // Rellenamos la tabla
        rellenarTabla(listaAlumnos);
    }//GEN-LAST:event_btnFicheroImportActionPerformed
    
    /**
     * En el JTextField de fichero se muestra el nombre/ruta del fichero 
     * seleccionado pudiendo ser modificado. En caso de seleccionar un fichero 
     * de texto, cada registro se guardará en una línea del fichero de texto, 
     * separando cada campo mediante el carácter *.
     * @param evt 
     */
    private void btnFicheroExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicheroExportActionPerformed
        // Declaracion de variables
        File ficheroExport = null;
                
        if(txtNombreExport.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Debe introducir una ruta valida "
                    + "antes de poder exportar.");
            return;
        } 
        
        ficheroExport = new File(txtNombreExport.getText());
        
        // Comprobamos la seleccion elegido a traves del combobox
        switch(cbDestino.getSelectedIndex()){
            // Si se trata de un fichero de 8 bytes
            case 0:
                // Guardamos la lista en un fichero de 8 bytes
                exportarSerializable(ficheroExport);
                break;      
            // Si se trata de un fichero de 16 bytes
            case 1:                 
                exportarFicheroTexto(ficheroExport);
                break;  
            case 2:
                exportarXML(ficheroExport);                
                break;
            case 3:                
                //ficheroExport = new File(txtNombreExport.getText() + "\\AlumnosRAF.txt");
                exportarRAF(ficheroExport);
                break;
        }
    }//GEN-LAST:event_btnFicheroExportActionPerformed

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
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new framePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnElegirFicheroExport;
    private javax.swing.JButton btnElegirFicheroImport;
    private javax.swing.JToggleButton btnEliminarFiltro;
    private javax.swing.JButton btnFicheroExport;
    private javax.swing.JButton btnFicheroImport;
    private javax.swing.JToggleButton btnFiltro;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbDestino;
    private javax.swing.JComboBox<String> cbOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTextField txtFicheroImport;
    private javax.swing.JTextField txtNombreExport;
    // End of variables declaration//GEN-END:variables

    /* Metodos auxiliares
    Metodos de clase necesarios en diferentes vistas para comprobar la validez 
    de los datos introducidos
    */
    /**
     * Metodo que comprueba que se trata de un numero entero
     * @param campoTexto String que se comprueba
     * @return Devuelve un numero entero, -1 por defecto
     * @throws NullPointerException Se lanza si no se introducen datos
     * @throws NumberFormatException Se lanza si el formato es incorrecto
     */
    public static int comprobarEntero(String campoTexto) throws 
            NullPointerException,NumberFormatException{
        int entero = -1;        
        entero = Integer.parseInt(campoTexto);        
        return entero;
    }   
    
    /**
     * Metodo que comprueba que se trata de un numero con decimales
     * @param campoTexto String que se comprueba
     * @return Devuelve un numero con decimales, -1 por defecto
     * @throws NullPointerException Se lanza si no se introducen datos
     * @throws NumberFormatException Se lanza si el formato es incorrecto
     */
    public static double comprobarDouble(String campoTexto) throws 
            NullPointerException,NumberFormatException{
        double doble = -1;        
        doble = Double.parseDouble(campoTexto);        
        return doble;
    } 
    
    /**
     * Metodo que comprueba que se trata de una fecha con el formato dd/MM/yyyy
     * @param stringFecha String que se comprueba
     * @return Devuelve un String
     * @throws ParseException Se lanza si el formato es incorrecto
     */
    public static String comprobarFecha(String stringFecha) throws ParseException{
        Date fecha = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        fecha = formato.parse(stringFecha);           
        stringFecha = formato.format(fecha);
        
        return stringFecha;
    }     
    
    /* He decidido subidividir los metodos de tal forma que se puede comprobar
    la validez de un dato por un lado e informar al usuario del campo de texto
    texto erroneo por otro lado*/
    /**
     * Metodo que avisa al usuario de que la fecha introducida no es valida
     * @param campoTexto String del campo de texto que se desea comprobar
     * @return Devuelve 1 si el contneido del campo de texto es valido y -1 si 
     * no lo es 
     */
    public static int mensajeFecha(String campoTexto){
        int resultado = 1;
        try {
            comprobarFecha(campoTexto);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"Debe introducir una fecha "
                    + "válida con el siguiente formato dd/MM/yyyy"); 
            resultado = -1;
        }
        return resultado;
    }
    
    /**
     * Metodo que avisa al usuario de que la nota introducida no es valida
     * @param campoTexto String del campo de texto que se desea comprobar
     * @return Devuelve 1 si el contenido del campo de texto es valido y -1 si 
     * no lo es 
     */
    public static int mensajeNota(String campoTexto){
        int resultado = 1;
        double nota = 0;
        
        try{
            nota = comprobarDouble(campoTexto);
            if(nota < 1 || nota > 10){
                JOptionPane.showMessageDialog(null,"Debe introducir una nota "
                        + "superior a 0 y no mayor a 10"); 
                resultado = -1;
            }
        }catch(NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null,"Debe introducir una nota válida"); 
            resultado = -1;
        }
        return resultado;
    }    
    
    /**
     * Metodo que avisa al usuario de que la edad introducida no es valida
     * @param campoTexto String del campo de texto que se desea comprobar
     * @return Devuelve 1 si el contenido del campo de texto es valido y -1 si 
     * no lo es
     */
    public static int mensajeEdad(String campoTexto){
        int resultado = 1;
        int edad = -1;
        try{
            edad = comprobarEntero(campoTexto);
            if(edad <= 0){
                JOptionPane.showMessageDialog(null,"Debe introducir una edad "
                        + "superior a 0"); 
                resultado = -1;
            }
        }catch(NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null,"Debe introducir una edad válida"); 
            resultado = -1;
        }
        
        return resultado;
    }
    
    /**
     * Metodo que rellena la tabla con una lista de alumnos
     * @param listaAlumnos Lista de alumnos que se mostrara en la tabla
     */ 
    private void rellenarTabla(ArrayList<Alumno> listaAlumnos) {
        // Ordenamos la lista en funcion del numero de matricula
        Collections.sort(listaAlumnos);
        // Vaciamos la tabla
        modeloTabla.setRowCount(0);
        // Por cada alumno de la lista rellenamos una fila de la tabla
        for (Alumno a : listaAlumnos) {
            modeloTabla.addRow(new Object[]{a.getMatricula(),a.getNombre(),
            a.getFechaNacimiento(),a.getNotaMedia(),a.getEdad()});
        }
    }
       
    /**
     * Metodo que lee los datos de un fichero de 8 bytes y los introduce en la 
     * lista de alumnos
     * @param ficheroImport Fichero de Objeto Serialiazable que guarda la informacion de 
     * una lista de alumnos
     */ 
    private void importarSerializable(File ficheroImport) {
        // Flujo de entrada de fichero
        FileInputStream fis = null;
        // Flujo de entrada de objeto
        ObjectInputStream ois = null;               
        
        try {
            // Asignamos el fichero a un un FIS y a un OIS respectivamente
            fis = new FileInputStream(ficheroImport);
            ois = new ObjectInputStream(fis);
            
            try {
                // Asignamos la lista a un lista auxiliar 
                ArrayList<Alumno> aux = (ArrayList<Alumno>) ois.readObject();                
                // Nos quedamos con los elementos de la lista que NO se repiten
                // REQUIERE DE LA IMPLEMENTACION DE EQUALS
                aux.removeAll(listaAlumnos);
                // Añadimos a la lista original los elementos únicos
                listaAlumnos.addAll(aux);                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this,"No se ha encontrado la clase");
            }            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error de entrada/salida");
        }finally{            
            try {
                // Cerramos los ficheros
                fis.close();
                ois.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de cierre");
            }
        }                 
    }
    
    /**
     * Metodo que lee los datos de un fichero de texto de 16 bytes y los 
     * introduce en la lista de alumnos
     * @param ficheroImport Fichero de texto que guarda la informacion de 
     * una lista de alumnos
     */   
    private void importarFicheroTexto(File ficheroImport) {
        // Declaracion e inicializacion de variables
        BufferedReader br = null;
        int auxMatricula = 0;
        int auxEdad = 0;
        double auxNotaMedia = 0;
        String auxFecha = "";
        String auxNombre = "";
        
        try {
            // Inicializamos un BufferedReader
            br = new BufferedReader(new FileReader(ficheroImport));            
            // Leemos la primera linea
            String linea = br.readLine();
            // Mientras que linea no sea nulo
            while(linea != null){
                // Asignamos a las variables los datos del fichero ya que 
                // se encuentran separadas por astericos y recortamos la 
                // linea con cada variable leida hasta llegar al fin
                auxMatricula = Integer.parseInt(linea.substring(0, linea.indexOf('*'))); 
                linea = linea.substring(linea.indexOf('*') + 1,linea.length());
                
                auxNombre = linea.substring(0, linea.indexOf('*'));    
                linea = linea.substring(linea.indexOf('*') + 1,linea.length());
                
                auxFecha = linea.substring(0, linea.indexOf('*'));
                linea = linea.substring(linea.indexOf('*') + 1,linea.length());

                auxNotaMedia = Double.parseDouble(linea.substring(0, linea.indexOf('*')));
                linea = linea.substring(linea.indexOf('*') + 1,linea.length());

                auxEdad = Integer.parseInt(linea);                    
                linea = "";
                // Si el alumno no existe ya se dara de alta
                if(ca.existeAlumno(auxMatricula) == false){
                    ca.altaAlumno(auxMatricula, auxNombre, auxFecha, auxNotaMedia, auxEdad);
                }
                linea = br.readLine();   
            }                
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"No se ha encontrado el fichero");
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de I/O");
        }finally{
            try {
                // Cerramos el fichero
                br.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de cierre");
            }
        } 
    }
    
    /**
     * Metodo que lee un fichero de acceso directo e introduce los datos en la
     * lista de alumnos
     * @param ficheroImport Fichero de acceso directo que guarda la informacion de 
     * una lista de alumnos
     */
    private void importarRAF(File ficheroImport) {
        //Declaracion de variables
        long pos;
        String nombre;
        String fecha;
        double nota;
        int edad;
        
        try {
            // Instanciamos una variable que guarda el fichero directo
            raf = new RandomAccessFile(ficheroImport, "r");
            // Obtenemos el numero de registros dentro del fichero directo
            int numeroRegistros = (int) (raf.length() / TAMANO);
            // Recorremos el fichero directo            
            for(int i = 1; i <= numeroRegistros; i++){
                // Calculamos la posicion 
                pos = (i - 1) * TAMANO;
                // Colocamos el puntero
                raf.seek(pos);
                // Leemos la matricula, es decir, el identificador
                matricula = raf.readInt();
                if(matricula != 0){
                    // Leemos el nombre
                    byte[] auxNombre = new byte[200];
                    raf.readFully(auxNombre);
                    nombre = new String(auxNombre);
                    // Leemos la fecha de nacimiento
                    byte[] auxFecha = new byte[20];
                    raf.readFully(auxFecha);
                    fecha = new String(auxFecha);
                    // Leemos la nota
                    nota = raf.readDouble();
                    // Leemos la edad
                    edad = raf.readInt();
                    // Comprobamos que no exista ya el alumno
                    if(ca.existeAlumno(matricula) == false){
                        // Lo damos de alta
                        ca.altaAlumno(matricula, nombre, fecha, nota, edad);    
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"No se ha encontrado el fichero");                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error de entrada/salida...");
        } finally{
            try {
                // Cerramos el fichero
                raf.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de entrada/salida");
            }  
        }
    }     
    
    /**
     * Metodo que lee un fichero xml e introduce los datos en la lista de alumnos 
     * @param ficheroImport Fichero xml que guarda la informacion de una lista
     * de alumnos
     */
    private void importarXML(File ficheroImport) {
        //Declaracion e inicializacion de variables
        DocumentBuilder builder = null;
        Document document = null;
        Element nodoraiz = null;        
        int auxMatricula = 0;
        int auxEdad = 0;
        double auxNotaMedia = 0;
        String auxFecha = "";
        String auxNombre = "";
        
        try {
            // Obtenemos una instancia de un documentbuilder para poder parsear 
            // el documento xml
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Procesamos el fichero XML y obtenemos el arbol DOM
            document = builder.parse(ficheroImport); 
            // Obtenemos el nodo raiz del arbol DOM
            nodoraiz = document.getDocumentElement();
            // Obtenemos una lista de nodos con todos los hijos nodos del nodo 
            // raiz con la etiqueta alumno 
            NodeList alumnos = nodoraiz.getElementsByTagName("alumno");            
            // y recorremos sus hijos
            for(int i = 0; i < alumnos.getLength(); i++){   
                // Obtenemos todos los elementos de cada nodo alumno                      
                NodeList elementosAlumno = alumnos.item(i).getChildNodes();  
                    for(int j = 0; j < elementosAlumno.getLength(); j++){                        
                        if(elementosAlumno.item(j) instanceof Element){
                            // Obtenemos los datos de cada alumno en funcion del nodo
                            switch(elementosAlumno.item(j).getNodeName()){
                                case "matricula":
                                    auxMatricula = Integer.parseInt(elementosAlumno.item(j).getTextContent());
                                    break;
                                case "nombre":   
                                    auxNombre = elementosAlumno.item(j).getTextContent();
                                    break;
                                case "fecha_de_nacimiento":
                                    auxFecha = elementosAlumno.item(j).getTextContent();
                                    break;
                                case "nota_media":
                                    auxNotaMedia = Double.parseDouble(elementosAlumno.item(j).getTextContent());
                                    break;
                                case "edad":
                                    auxEdad = Integer.parseInt(elementosAlumno.item(j).getTextContent());
                                    break;
                            }
                        }                    
                    }
                // Anadimos cada alumno que no se repita a la lista de alumnos
                if(ca.existeAlumno(auxMatricula) == false){
                    ca.altaAlumno(auxMatricula, auxNombre, auxFecha, auxNotaMedia, auxEdad);
                }
            }
        } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(this,"Error de configuración de parseo");                     
        } catch (SAXException ex) {
            JOptionPane.showMessageDialog(this,"Error de creacion de arbol DOM");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error de entrada/salida");
        }
    }
    
    /**
     * Metodo que escribe los datos en un fichero de 8 bytes
     * @param ficheroExport Fichero de Objeto Serialiazable que guardara la informacion de 
     * la lista de alumnos
     */ 
    private void exportarSerializable(File ficheroExport) {
        // Flujo de salida de fichero
        FileOutputStream fos = null;        
        // Flujo de salida de objeto
        ObjectOutputStream oos = null;
        
        try {
            // Inicializamos el FOS y el OOS
            fos = new FileOutputStream(ficheroExport);
            oos = new ObjectOutputStream(fos);            
            // Escribimos la lista de alumnos
            oos.writeObject(listaAlumnos);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"No se ha encontrado el fichero");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error de entrada/salida");
        } finally {        
            try {
                // Cerramos el FOS y el OOS
                fos.close();
                oos.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de entrada/salida");
            }
        }        
    }     
    
    /**
     * Metodo que escribe un fichero de texto de 16 bytes a partir de los datos
     * de la lista de alumnos 
     * @param ficheroExport Fichero de texto que guardara la informacion de la lista de 
     * alumnos
     */
    private void exportarFicheroTexto(File ficheroExport) {
        FileWriter fw = null;        
        
        try {
            // Inicializamos un FileWriter
            fw = new FileWriter(ficheroExport);
            // Por cada alumno en la lista
            for(Alumno alumnoLista : listaAlumnos){
                // Escribimos un alumno en el fichero
                fw.write(String.valueOf(alumnoLista));                    
            }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de entrada/salida");
            }finally{
                try {
                    // Cerramos el fichero
                    fw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,"Error de cierre");
            }
        }
    }
    
    /**
     * Metodo que escribe un fichero de acceso directo a partir de los datos
     * de la lista de alumnos
     * @param ficheroExport Fichero de acceso directo que guardara la informacion 
     */
    private void exportarRAF(File ficheroExport) {
        //Declaracion de variables
        StringBuilder nombre;
        StringBuilder fecha;        
        
        // Inicializamos la variable que guarda el fichero de acceso directo
        try {
            // Inicializamos el fichero de acceso directo
            raf = new RandomAccessFile(ficheroExport, "rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"No se ha encontrado el fichero");
            return;
        }
        
        for(Alumno alumno : listaAlumnos){            
            try {
                // Escribimos la matricula como identificador del registro
                raf.writeInt(alumno.getMatricula());     
                // Escribimos el String del nombre 
                nombre = new StringBuilder(alumno.getNombre());
                nombre.setLength(100);
                raf.writeChars(nombre.toString());
                // Escribimos el String de la fecha
                fecha = new StringBuilder(alumno.getFechaNacimiento());
                fecha.setLength(10);
                raf.writeChars(fecha.toString());
                // Escribimos la nota media 
                raf.writeDouble(alumno.getNotaMedia());
                // Escribimos la edad
                raf.writeInt(alumno.getEdad());                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error de entrada/salida");
            }
        }
        
        try {
            // Cerramos el fichero
            raf.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error de entrada/salida");
        }
    }       

    /**
     * Metodo que escribe un fichero xml que guardara la informacion de la lista
     * de alumnos
     * @param ficheroExport Fichero xml que guardara la informacion 
     */
    private void exportarXML(File ficheroExport) {
        // Declaracion e inicializacion de variables
        DocumentBuilder documentBuilder = null;
        Transformer xformer = null;
        Source source;
        Result result;
        
        try {
            // Obtenemos una instancia de un documentbuilder para poder parsear 
            // el documento xml
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();                    
        } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(this,"Error de configuracion de parseo");            
        } 
        // Nos creamos un arbol DOM
        Document doc = documentBuilder.newDocument();
        // Nos creamos el nodo raiz alumnos
        Element alumnos = doc.createElement("alumnos");
        // Anadimos el nodo raiz alumnos a la estructura del DOM
        doc.appendChild(alumnos);
        // Por cada alumno dentro de la lista 
        for(Alumno alumnoLista : listaAlumnos){
            // Nos creamos un elemento para cada alumno y lo anadimos a la 
            // estructura del elemento alumnos. Posteriormente le anadimos a 
            // cada alumno sus datos, es decir, el nombre del elemento y su texto.             
            Element elementoAlumno = doc.createElement("alumno");
            alumnos.appendChild(elementoAlumno);
                    
            Element nMatricula = doc.createElement("matricula");
            nMatricula.setTextContent(String.valueOf(alumnoLista.getMatricula()));
            elementoAlumno.appendChild(nMatricula);
                    
            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(String.valueOf(alumnoLista.getNombre()));
            elementoAlumno.appendChild(nombre);
                    
            Element fecha = doc.createElement("fecha_de_nacimiento");
            fecha.setTextContent(String.valueOf(alumnoLista.getFechaNacimiento()));
            elementoAlumno.appendChild(fecha);
                    
            Element nota = doc.createElement("nota_media");
            nota.setTextContent(String.valueOf(alumnoLista.getNotaMedia()));
            elementoAlumno.appendChild(nota);
                    
            Element edad = doc.createElement("edad");
            edad.setTextContent(String.valueOf(alumnoLista.getEdad()));                                    
            elementoAlumno.appendChild(edad);
        }                
        try {
            // Obtenemos una instancia de una transformador para poder convertir
            // el documento en un fichero xml
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            JOptionPane.showMessageDialog(this,"Error de configuracion de transformacion");            
        }
        // Propiedades del fichero XML de salida
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
        // Definimos la Entrada y Salida de la Transformacion
        source = new DOMSource(doc);
        result = new StreamResult(ficheroExport);            
        try {
            // Realizamos la transformacion mediante el metodo transform()
            xformer.transform(source,result);
        } catch (TransformerException ex) {
            JOptionPane.showMessageDialog(this,"Error de transformacion");
        }
    }
}
