/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public abstract class ServiciosPacientes {
    private static ServiciosPacientes instance;
    
    public static ServiciosPacientes getInstance() throws RuntimeException, IOException{
        instance = new ServiciosPacientesMapper();
        return instance;
    }
    
    Paciente tmp;
    int id; 
    String tipo_id;
    String nombre; 
    Date fecha_actual;
    String consultacad;
    
    Paciente seleccion;
    
    String fechayhora;
    Date fecha_total;
    String Descripcion;
    Consulta cons_temp;

   
    //Getters y setters del registro de un paciente.
    //tmp.
    public Paciente getTmp() {
        return tmp;
    }

    public void setTmp(Paciente tmp) {
        this.tmp = tmp;
    }
    //id.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //tipo_id.
    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    //nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //fecha_actual.
    public Date getFecha_actual() {
        return fecha_actual;
    }
    
    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }
    //consultacad.
    public String getConsultacad() {
        
        return consultacad;
    }

    public void setConsultacad(String consultacad) {
        this.consultacad = consultacad;
    }
    
    //Getters y setters de las consultas del paciente.
    //seleccion.
    public Paciente getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Paciente seleccion) {
        this.seleccion = seleccion;
    }
    //fechayhora.
    public String getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(String fechayhora) {
        this.fechayhora = fechayhora;
    }
    //descripcion.
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }    
    //cons_temp.
      public Consulta getCons_temp() {
        return cons_temp;
    }

    public void setCons_temp(Consulta cons_temp) {
        this.cons_temp = cons_temp;
    }
    //fecha_total
    public Date getFecha_total() {
        return fecha_total;
    }

    public void setFecha_total(Date fecha_total) {
        this.fecha_total = fecha_total;
    }
    
        

    protected ServiciosPacientes(){        

    }
    
    
    
    /*
    public static ServiciosPacientes getInstance() throws RuntimeException{        
        return instance;
    }
 */
    public abstract List<Paciente> listPaciente();
    
    
    /**
     * Consultar un paciente dado su identificador.
     * @param idPaciente identificador del paciente
     * @param tipoid tipo de identificación del paciente
     * @return el paciente con el identificador dado
     * @throws ExcepcionPacienteinexistente  si el paciente no existe
     */
    public abstract Paciente consultarPaciente(int idPaciente,String tipoid) throws ExcepcionServiciosPacientes;
    
    
    /**
     * Registra un nuevo PACIENTE en el sistema
     * @param p El nuevo paciente
     * @throws ExceptionPacienteyaexistente si se presenta algún error lógico
     * o de persistencia (por ejemplo, si el paciente ya existe).
     */
    public abstract void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes;
    
    /**
     * Agrega una consulta a un paciente ya registrado
     * @param idPaciente el identificador del paciente
     * @param tipoid el tipo de identificación
     * @param c la consulta a ser agregada
     * @throws Excepcionconsultaapacienteinexistente si se presenta algún error de persistencia o si el paciente no existe.
     */
    public abstract void agregarConsultaAPaciente(int idPaciente,String tipoid,Consulta c) throws ExcepcionServiciosPacientes;

    public abstract void agregarConsulta();
    public abstract List<Consulta> getConsultaLista();
    
    public abstract List<Paciente> obtenerpacientes();
    
    }
