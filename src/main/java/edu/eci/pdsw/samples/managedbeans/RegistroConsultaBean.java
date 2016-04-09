/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;


/**
 *
 * @author hcadavid
 */

@ManagedBean (name="RegistroConsultaBean")
@SessionScoped

public class RegistroConsultaBean implements Serializable{
    
    //ServiciosPacientes sp=ServiciosPacientes.getInstance();
   
    private static ServiciosPacientes sp; 
    public RegistroConsultaBean() throws IOException, ExcepcionServiciosPacientes{
        
        sp=ServiciosPacientes.getInstance();
        
    }
    private Paciente tmp;
    private int id; 
    private String tipo_id;
    private String nombre; 
    private Date fecha_actual;
    private String consultacad;
    
    private static Paciente seleccion;
    
    private String fechayhora;
    private Date fecha_total;
    private String Descripcion;
    private Consulta cons_temp;

    
    
    public void registrarPac() throws ExcepcionServiciosPacientes{
       fecha_actual = Date.valueOf(consultacad);
        tmp = new Paciente(id, tipo_id, nombre, fecha_actual);
         setId(0);
         setNombre("");
         setTipo_id("");
         setConsultacad(""); 
         sp.registrarNuevoPaciente(tmp);
    }
    public void agregarconsulta() throws ExcepcionServiciosPacientes{
        System.out.println("Entramos");
        seleccion = loadbyid(seleccion.getId(), seleccion.getTipo_id());
        sp.agregarConsultaAPaciente(seleccion.getId(), seleccion.getTipo_id(), cons_temp);
        
    }
    public List<Paciente> obtenerpacs(){
         List<Paciente> obtenerpacientes = sp.obtenerpacientes();return obtenerpacientes;
    }
    public Paciente getTmp() {
     
        return tmp;
    }
    public Paciente loadbyid(int id,String tipo){
      Paciente cargado=  sp.loadPaciente(id, tipo);
        return cargado;
    }

    public void setTmp(Paciente tmp) {
        this.tmp = tmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getConsultacad() {
        return consultacad;
    }

    public void setConsultacad(String consultacad) {
        this.consultacad = consultacad;
    }

    public Paciente getSeleccion() {
        if(seleccion==null){
            seleccion = new Paciente();
               return seleccion;
        }
        else{
        System.out.println("Seleccion:"+ seleccion.getNombre());
         seleccion = loadbyid(seleccion.getId(), seleccion.getTipo_id());
        return seleccion;}
    }

    public void setSeleccion(Paciente seleccion) {
        this.seleccion = seleccion;
    }

    public String getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(String fechayhora) {
        this.fechayhora = fechayhora;
    }

    public Date getFecha_total() {
        return fecha_total;
    }

    public void setFecha_total(Date fecha_total) {
        this.fecha_total = fecha_total;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Consulta getCons_temp() {
         fecha_total=Date.valueOf(fechayhora);
        cons_temp=new Consulta(fecha_total,Descripcion);
        setFechayhora("");
        setDescripcion("");
       
        return cons_temp;
    }

    public void setCons_temp(Consulta cons_temp) {
        this.cons_temp = cons_temp;
    }
    

    public ServiciosPacientes getSp() {
        return sp;
    }

    public void setSp(ServiciosPacientes sp) {
        this.sp = sp;
    }
    
    
    
    
    
}
