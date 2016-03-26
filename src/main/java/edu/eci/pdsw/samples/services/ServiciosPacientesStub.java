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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */

public class ServiciosPacientesStub extends ServiciosPacientes{

    public final Map<Tupla<Integer,String>,Paciente> pacientes;
    //id.
    @Override
    public int getId() {
        return id;
    }

     @Override
    public void setId(int id) {
        this.id = id;
    }
    //tipo_id.
     @Override
    public String getTipo_id() {
        return tipo_id;
    } 
    
    @Override
    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    //nombre.
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public ServiciosPacientesStub() {
        this.pacientes = new LinkedHashMap<>();
        cargarDatosEstaticos(pacientes);
    }   
    //tmp.
    @Override
    public Paciente getTmp() {
        fecha_actual = Date.valueOf(consultacad);
        tmp = new Paciente(id, tipo_id, nombre, fecha_actual);
         setId(0);
         setNombre("");
         setTipo_id("");
         setConsultacad("");
        return tmp;
    }

    @Override
    public void setTmp(Paciente tmp) {
        this.tmp = tmp;
    }
    //cons_temp

    @Override
    public void setCons_temp(Consulta cons_temp) {
        super.setCons_temp(cons_temp);
    }

    @Override
    public Consulta getCons_temp() {
        fecha_total=Date.valueOf(fechayhora);
        cons_temp=new Consulta(fecha_total,Descripcion);
        setFechayhora("");
        setDescripcion("");
        return cons_temp;
    }
    
    
    
    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
        Paciente p=pacientes.get(new Tupla<>(idPaciente,tipoid));
        if (p==null){
            throw new ExcepcionServiciosPacientes("Paciente "+idPaciente+" no esta registrado");
        }
        else{
            return p;
        }
        
    }



    /**
     * Clases de Equivalencia
     * clase de equivalencia1: Que el paciente p sea null,en caso de null lanzar excepcion.
     * clase de equivalencia2: Que el paciente p exista,efectuado.
     */
    
    
    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
      
        pacientes.put(new Tupla<>(p.getId(),p.getTipo_id()), p);
        
    }
    
    
    
    
    /*
    Clases de Equivalencia:
    CE1: EL id del Paciente no existe , deberia lanzar excepcion.
    CE2: el id del Paciente existe, funcion OK.
    
    */

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
        Paciente p=pacientes.get(new Tupla<>(idPaciente,tipoid));
        if (p==null){
            throw new ExcepcionServiciosPacientes("Paciente "+idPaciente+" no esta registrado");
        }
        else{
            p.getConsultas().add(c);
        }
    }
    

    private void cargarDatosEstaticos(Map<Tupla<Integer,String>,Paciente> pacientes){        
        try {
            registrarNuevoPaciente(new Paciente(123, "CC", "Juan Perez", java.sql.Date.valueOf("2000-01-01")));
            registrarNuevoPaciente(new Paciente(321, "CC", "Maria Rodriguez", java.sql.Date.valueOf("2000-01-01")));
            registrarNuevoPaciente(new Paciente(875, "CC", "Pedro Martinez", java.sql.Date.valueOf("1956-05-01")));
            
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesStub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
    
        @Override
    public List<Paciente> listPaciente(){
        List<Paciente> list = new ArrayList<>(pacientes.values());
        /*
        try {
            registrarNuevoPaciente(new Paciente(123, "CC", "Juan Perez", java.sql.Date.valueOf("2000-01-01")));
            registrarNuevoPaciente(new Paciente(321, "CC", "Maria Rodriguez", java.sql.Date.valueOf("2000-01-01")));
            registrarNuevoPaciente(new Paciente(875, "CC", "Pedro Martinez", java.sql.Date.valueOf("1956-05-01")));
            
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesStub.class.getName()).log(Level.SEVERE, null, ex);
        }
        */        
       return list;
    }

   
    
     @Override
    public void agregarConsulta() {
        fecha_total = Date.valueOf(fechayhora);
        Consulta c = new Consulta(fecha_total, Descripcion);
        try {
            agregarConsultaAPaciente(getSeleccion().getId(), getSeleccion().getTipo_id(),cons_temp);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesStub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Consulta> getConsultaLista() {
        List<Consulta> consultas = new ArrayList<>();
        
        List<Paciente> lista = new ArrayList<>(pacientes.values());
        Set<Consulta> list=null;
        
        for (int i = 0; i < lista.size(); i++) {
            if(seleccion.getId()==lista.get(i).getId()){
                list=lista.get(i).getConsultas();
            }
        }
        
        Iterator<Consulta> i=list.iterator();
        while(i.hasNext()){
            Consulta c = i.next();
            consultas.add(c);
        }
        
    
            
        
      return consultas;
        
    }  

    @Override
    public List<Paciente> obtenerpacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}


class Tupla<A,B>{
    
    A a;
    B b;

    public Tupla(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }


    public B getB() {
        return b;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.a);
        hash = 71 * hash + Objects.hashCode(this.b);        
        return hash;
    }

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
        final Tupla<?, ?> other = (Tupla<?, ?>) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
    }
    
    
    
}
