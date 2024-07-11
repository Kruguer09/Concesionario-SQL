/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import Modelo.LineaFactura;
import Modelo.Neumatico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Acceso {

    private final String bd = "neumaticos";
    private final String url = "jdbc:mysql://localhost/";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String timeZone = "?serverTimeZone=UTC";
    private final String login = "root";
    private final String password = "";
    private static Connection conn = null;

    //Listas para consultas
    private List<Neumatico> myResultListNeu;
    private List<Factura> myResultListInv;
    private List<LineaFactura> myResultListInvLine;
    private List<String> myClientsNIF;

    public Acceso() {
    }

    /**
     * Método para acceder al objeto Connection
     *
     * @return Objeto Connectio
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Método para establecer la conexión con la base de datos
     *
     * @return <ul>
     * <li> true: Conexión establecida correctamente.</li>
     * <li> false: No se ha podido establecer la conexión.</li>
     * </ul>
     *
     */
    public boolean connect() {
        try {
            //Obtenemos el driver de mysql
            //Class.forName(driver);
            //Establecemos conexión
            conn = DriverManager.getConnection(url + bd + timeZone, login, password);

            if (conn != null) {
                System.out.println("Conexión a " + bd + " establecida correctamente.");
                return true;
            }
        //} catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Método para insertar neumáticos en nuestra base de datos
     * 
     * @param neu Objeto neumático a insertar en la base de datos
     * @return <ul>
     * <li> true: Operación de inserción realizada con éxito.</li>
     * <li> false: No se ha podido insertar el neumático en la Base de Datos.</li>
     * </ul>
     */
    public boolean saveNeu(Neumatico neu) {
        PreparedStatement myPrepSt = null;
        int rows = 0;
        //Preparamos el statement
        String myQuery = "INSERT INTO neumatico(cod, marca, modelo, ancho, perfil, diametro, IC, IV, precio) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            myPrepSt = conn.prepareStatement(myQuery);

            //Seteamos las incógnitas
            myPrepSt.setInt(1, neu.getCod());
            myPrepSt.setString(2, neu.getBrand());
            myPrepSt.setString(3, neu.getModel());
            myPrepSt.setInt(4, neu.getWidth());
            myPrepSt.setString(5, neu.getProfile());
            myPrepSt.setDouble(6, neu.getDiameter());
            myPrepSt.setInt(7, neu.getIc());
            myPrepSt.setString(8, neu.getIv());
            myPrepSt.setDouble(9, neu.getPrice());

            //Ejecutamos
            rows = myPrepSt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(null, null, myPrepSt);
        }

        if (rows != 0) {//Se ha insertado una row
            return true;
        } else {
            return false;
        }
    }
    
     /**
     * Método para insertar facturas en nuestra base de datos
     * 
     * @param neu Objeto factura a insertar en la base de datos
     * @return <ul>
     * <li> true: Operación de inserción realizada con éxito.</li>
     * <li> false: No se ha podido insertar la factura en la Base de Datos.</li>
     * </ul>
     */
    public boolean saveInvoice(Factura inv) {
        PreparedStatement myPrepSt = null;
        int rows = 0;
        //Preparamos el statement
        String myQuery = "INSERT INTO factura(nifcliente, nifemisor, fechasistema, base, iva, total, pagada, numcuenta) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            myPrepSt = conn.prepareStatement(myQuery);

            //Seteamos las incógnitas
            myPrepSt.setString(1, inv.getNifClient());
            myPrepSt.setString(2, inv.getNifCompany());
            myPrepSt.setString(3, inv.getCurrentDate());
            myPrepSt.setDouble(4, inv.getBase());
            myPrepSt.setDouble(5, inv.getTaxAmount());
            myPrepSt.setDouble(6, inv.getTotal());
            myPrepSt.setBoolean(7, inv.isIsPaidOrNot());
            myPrepSt.setString(8, inv.getBankAccount());

            //Ejecutamos
            rows = myPrepSt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(null, null, myPrepSt);
        }

        if (rows != 0) {//Se ha insertado una row
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para insertar líneas de factura en nuestra base de datos
     * 
     * @param neu Objeto LineaFactura a insertar en la base de datos
     * @return <ul>
     * <li> true: Operación de inserción realizada con éxito.</li>
     * <li> false: No se ha podido insertar la factura en la Base de Datos.</li>
     * </ul>
     */
    public boolean saveLinesInvoice(LineaFactura line) {
        PreparedStatement myPrepSt = null;
        int rows = 0;
        //Preparamos el statement
        String myQuery = "INSERT INTO linea_factura( numfactura, codneumatico, concepto, numneumaticos, precio) values(?, ?, ?, ?, ?)";
        try {
            myPrepSt = conn.prepareStatement(myQuery);

            //Seteamos las incógnitas
            myPrepSt.setInt(1, line.getNumfactura());
            myPrepSt.setInt(2, line.getCodNeu());
            myPrepSt.setString(3, line.getConcept());
            myPrepSt.setInt(4, line.getNumNeu());
            myPrepSt.setDouble(5, line.getPrice());

            //Ejecutamos
            rows = myPrepSt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(null, null, myPrepSt);
        }

        if (rows != 0) {//Se ha insertado una row
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateNeu(Neumatico neu) {
        PreparedStatement myPrepSt = null;
        int rows = 0;
        //Preparamos el statement
        String myQuery = "UPDATE neumatico SET marca=?, modelo=?, ancho=?, perfil=?, diametro=?, IC=?, IV=?, precio=? "
                + "WHERE cod=?";
        try {
            myPrepSt = conn.prepareStatement(myQuery);

            //Seteamos las incógnitas            
            myPrepSt.setString(1, neu.getBrand());
            myPrepSt.setString(2, neu.getModel());
            myPrepSt.setInt(3, neu.getWidth());
            myPrepSt.setString(4, neu.getProfile());
            myPrepSt.setDouble(5, neu.getDiameter());
            myPrepSt.setInt(6, neu.getIc());
            myPrepSt.setString(7, neu.getIv());
            myPrepSt.setDouble(8, neu.getPrice());
            myPrepSt.setInt(9, neu.getCod());

            //Ejecutamos
            rows = myPrepSt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(null, null, myPrepSt);
        }

        if (rows != 0) {//Se ha insertado una row
            return true;
        } else {
            return false;
        }
    }

    public List<Neumatico> readTableNeu() {
        Statement mySt = null;
        ResultSet myRSet = null;
        myResultListNeu = new ArrayList<>();
        try {
            mySt = conn.createStatement();
            String myQuery = "SELECT * FROM neumatico";
            myRSet = mySt.executeQuery(myQuery);

            while (myRSet.next()) {
                myResultListNeu.add(new Neumatico(myRSet.getInt(1), myRSet.getString(2), myRSet.getString(3), myRSet.getInt(4), myRSet.getString(5), myRSet.getDouble(6), myRSet.getInt(7), myRSet.getString(8), myRSet.getDouble(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, mySt, null);
        }

        return myResultListNeu;
    }

    public List<Factura> readTableInv() {
        Statement mySt = null;
        ResultSet myRSet = null;
        myResultListInv = new ArrayList<>();
        try {
            mySt = conn.createStatement();
            String myQuery = "SELECT * FROM factura";
            myRSet = mySt.executeQuery(myQuery);

            while (myRSet.next()) {
                myResultListInv.add(new Factura(myRSet.getInt(1), myRSet.getString(2), myRSet.getString(4), myRSet.getDouble(5), myRSet.getDouble(6), myRSet.getDouble(7), myRSet.getBoolean(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, mySt, null);
        }

        return myResultListInv;
    }

    public List<LineaFactura> readTableInvLine(int numInv) {
        PreparedStatement myPrepSt = null;
        ResultSet myRSet = null;
        //Preparamos el statement
        String myQuery = "SELECT * FROM linea_factura WHERE numfactura=?";
        myResultListInvLine = new ArrayList<>();
        try {
            myPrepSt = conn.prepareStatement(myQuery);            
            //Seteamos las incógnitas            
            myPrepSt.setString(1, String.valueOf(numInv));
            //Ejecutamos
            myRSet = myPrepSt.executeQuery();

            while (myRSet.next()) {
                myResultListInvLine.add(new LineaFactura(myRSet.getInt(1), myRSet.getInt(2), myRSet.getInt(3), myRSet.getString(4), myRSet.getInt(5), myRSet.getDouble(6)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, null, myPrepSt);
        }

        return myResultListInvLine;
    }
    
    public boolean verifyNeuCod(int cod){
        //Sacamos los datos de los neumáticos a una lista temporal
        List<Neumatico> tempList = readTableNeu();
        //Verificamos si el código existe 
        for (Neumatico neu : tempList) {
            if(neu.getCod() == cod){
                return true;//Ya existe el código
            }
        }
        return false;//No se ha encontrado por lo que el código está disponible        
    }
    
    /**
     * Método para extraer el siguiente número de factura disponible
     * 
     * @return int de número de factura disponible 
     */
    public int newInvNumber(){
        int numInvAvailable = 0;
        Statement mySt = null;
        ResultSet myRSet = null;
        try {
            mySt = conn.createStatement();
            //String myQuery = "SELECT numfactura FROM factura";
            String myQuery = "SELECT count(*) FROM factura";
            myRSet = mySt.executeQuery(myQuery);
            
            if(myRSet.next()){
                //myRSet.last();
                numInvAvailable = myRSet.getInt(1);
            }           

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, mySt, null);
        }
        
        //System.out.println("NUMERO FACTURA SACADO BBDD: " + numInvAvailable);//Salida para pruebas

        return numInvAvailable + 1;
    }
    
    public int verifyNumberOfRows(String tableName){
        Statement mySt = null;
        ResultSet myRSet = null;
        int numberRows = 0;
        try {
            mySt = conn.createStatement();
            String myQuery = "SELECT COUNT(1) FROM " + tableName;
            //Cuento el número de rows de la primera columna
            myRSet = mySt.executeQuery(myQuery);
            //Lo avanzo un paso para poder coger el dato
            myRSet.next();
            numberRows = myRSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, mySt, null);
        }
        return numberRows;
    }
    
    public List<String> clientsNIFList(){
        Statement mySt = null;
        ResultSet myRSet = null;
        myClientsNIF = new ArrayList<>();
        try {
            mySt = conn.createStatement();
            String myQuery = "SELECT nif FROM cliente";
            myRSet = mySt.executeQuery(myQuery);

            while (myRSet.next()) {
                myClientsNIF.add(myRSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeTools(myRSet, mySt, null);
        }

        return myClientsNIF;
    }

    public void closeTools(ResultSet myRset, Statement mySt, PreparedStatement myPreSt) {
        try {
            if (myRset != null) {
                myRset.close();
            }
            if (mySt != null) {
                mySt.close();
            }
            if (myPreSt != null) {
                myPreSt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
