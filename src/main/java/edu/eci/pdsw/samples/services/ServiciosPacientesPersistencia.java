/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;


import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.jdbcimpl.JDBCDaoFactory;
import edu.eci.pdsw.samples.persistence.jdbcimpl.JDBCDaoPaciente;
import edu.eci.pdsw.samples.persistence.jdbcimpl.MyBatisDaoFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */

public class ServiciosPacientesPersistencia extends ServiciosPacientes{
     DaoFactory dafo;
     
    
    public ServiciosPacientesPersistencia() throws IOException{
        InputStream input = null;
        input = ServiciosPacientes.class.getClassLoader().getResource("applicationconfig.properties").openStream();
        Properties properties=new Properties();
        properties.load(input);
        dafo = new MyBatisDaoFactory();
        
    }
    
    
  
    

    
    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
        Paciente p= null;
        try {
            dafo.beginSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Algo paso en la conexion", e);
        }
        try {
            p= dafo.getDaoPaciente().load(idPaciente, tipoid);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dafo.endSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Error al cerrar la conexion", e);
        }
        return p;
    }

    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
        try {
            dafo.beginSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Algo paso en la conexion", e);
        }
        try {
            dafo.getDaoPaciente().save(p);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dafo.endSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Error al cerrar la conexion", e);
        }
    }

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
        try {
            dafo.beginSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Algo paso en la conexion", e);
        }
        try {
            Paciente p = dafo.getDaoPaciente().load(idPaciente, tipoid);
            p.getConsultas().add(c);
            dafo.getDaoPaciente().update(p);
            
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dafo.endSession();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosPacientes("Error al cerrar la conexion", e);
        }
    }

    
    @Override
    public List<Paciente> obtenerpacientes(){
        try {
            dafo.beginSession();
        } catch (PersistenceException ex) {
            try {
                throw new ExcepcionServiciosPacientes("Algo paso en la conexion", ex);
            } catch (ExcepcionServiciosPacientes ex1) {
                Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        List<Paciente> list = null;
        try {
            list = dafo.getDaoPaciente().loadall();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dafo.endSession();
        } catch (PersistenceException ex) {
            try {
                throw new ExcepcionServiciosPacientes("No termino conexion", ex);
            } catch (ExcepcionServiciosPacientes ex1) {
                Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return list;
    }

   /* @Override
    public void agregarConsulta() {
        fecha_total = Date.valueOf(fechayhora);
        Consulta c = new Consulta(fecha_total, Descripcion);
        try {
            agregarConsultaAPaciente(getSeleccion().getId(), getSeleccion().getTipo_id(),cons_temp);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesStub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    @Override
    public List<Consulta> getConsultaLista() {
        try {
            dafo.beginSession();
        } catch (PersistenceException ex) {
            try {
                throw new ExcepcionServiciosPacientes("Algo paso en la conexion", ex);
            } catch (ExcepcionServiciosPacientes ex1) {
                Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        Set<Consulta> consultas = new HashSet<>();
        List<Consulta> conste=new ArrayList<>();
        Paciente p=null;
        try {
            
            p = dafo.getDaoPaciente().load(564,"CC");
            consultas=p.getConsultas();
            
        Iterator<Consulta> i=consultas.iterator();
        while(i.hasNext()){
            Consulta c = i.next();
            conste.add(c);
        }
            
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dafo.endSession();
        } catch (PersistenceException ex) {
            try {
                throw new ExcepcionServiciosPacientes("No termino conexion", ex);
            } catch (ExcepcionServiciosPacientes ex1) {
                Logger.getLogger(ServiciosPacientesPersistencia.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
        return conste;
    }

    public List<Paciente> cargarpacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> listPaciente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  
}
