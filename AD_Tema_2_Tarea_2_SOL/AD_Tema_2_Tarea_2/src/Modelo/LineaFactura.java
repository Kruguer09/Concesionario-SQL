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
public class LineaFactura {
    //numlinea: El número de línea de factura es autoincremental, no lo seteamos
    private int numlinea;
    private int numfactura;//Varias por cada factura
    private int codNeu;
    private String concept;//Será la marca del neumático
    private int numNeu;//Cantidad
    private double price;

    public LineaFactura(int numfactura, int codNeu, String concept, int numNeu, double price) {
        this.numfactura = numfactura;
        this.codNeu = codNeu;
        this.concept = concept;
        this.numNeu = numNeu;
        this.price = price;
    }

    public LineaFactura(int numlinea, int numfactura, int codNeu, String concept, int numNeu, double price) {
        this.numlinea = numlinea;
        this.numfactura = numfactura;
        this.codNeu = codNeu;
        this.concept = concept;
        this.numNeu = numNeu;
        this.price = price;
    }
    
    

    public int getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(int numfactura) {
        this.numfactura = numfactura;
    }

    public int getCodNeu() {
        return codNeu;
    }

    public void setCodNeu(int codNeu) {
        this.codNeu = codNeu;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getNumNeu() {
        return numNeu;
    }

    public void setNumNeu(int numNeu) {
        this.numNeu = numNeu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LineaFactura{" + "numlinea=" + numlinea + ", numfactura=" + numfactura + ", codNeu=" + codNeu + ", concept=" + concept + ", numNeu=" + numNeu + ", price=" + price + '}';
    }
    
    
    
    
}
