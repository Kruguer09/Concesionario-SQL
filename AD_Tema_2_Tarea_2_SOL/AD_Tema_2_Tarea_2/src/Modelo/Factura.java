/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Factura {
    //numfactura: El número de factura es autoincremental, no lo seteamos
    //nifemisor: fijo = 99999999Z nifCompany
    //numcuenta: fijo = ES99-9999-9999-99-999999999 - bankAccount
    private static String NIF_COMPANY = "99999999Z";
    private static String COM_BAK_ACCOUNT = "ES99-9999-9999-99-999999999";
    
    //Campos
    private int numFactura;
    private String nifClient;
    private final String nifCompany;
    private String currentDate;
    private double base;
    private double taxAmount;
    private double total;
    private boolean isPaidOrNot;
    private final String bankAccount;

    public Factura() {
        this.nifCompany = NIF_COMPANY;
        this.bankAccount = COM_BAK_ACCOUNT;
    }

    public Factura(int numFactura, String nifClient, String currentDate, double base, double taxAmount, double total, boolean isPaidOrNot) {
        this.numFactura = numFactura;
        this.nifClient = nifClient;
        this.nifCompany = NIF_COMPANY;
        this.currentDate = currentDate;
        this.base = base;
        this.taxAmount = taxAmount;
        this.total = total;
        this.isPaidOrNot = isPaidOrNot;
        this.bankAccount = COM_BAK_ACCOUNT;
    }

    public Factura(String nifClient, String nifCompany, String currentDate, double base, double taxAmount, double total, boolean isPaidOrNot, String bankAccount) {
        this.nifClient = nifClient;
        this.nifCompany = nifCompany;
        this.currentDate = currentDate;
        this.base = base;
        this.taxAmount = taxAmount;
        this.total = total;
        this.isPaidOrNot = isPaidOrNot;
        this.bankAccount = bankAccount;
    }   
    

    public String getNifCompany() {
        return nifCompany;
    }

    public String getBankAccount() {
        return bankAccount;
    }    
    

    public String getNifClient() {
        return nifClient;
    }

    public void setNifClient(String nifClient) {
        this.nifClient = nifClient;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isIsPaidOrNot() {
        return isPaidOrNot;
    }

    public void setIsPaidOrNot(boolean isPaidOrNot) {
        this.isPaidOrNot = isPaidOrNot;
    }    

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public static String getNIF_COMPANY() {
        return NIF_COMPANY;
    }

    public static String getCOM_BAK_ACCOUNT() {
        return COM_BAK_ACCOUNT;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.numFactura;
        return hash;
    }

    //Dos facturas son iguales si sus números de factura son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (this.numFactura != other.numFactura) {
            return false;
        }
        return true;
    }
    
    
    
    
}
