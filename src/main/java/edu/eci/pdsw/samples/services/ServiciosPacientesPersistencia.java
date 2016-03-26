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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.Properties;
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
        dafo = new JDBCDaoFactory(properties);
        
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

    @Override
    public void agregarConsulta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consulta> getConsultaLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Paciente> cargarpacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> listPaciente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
