/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.edi.pdsw.peristence.DaoFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author 2093130
 */

public class ServiciosPacienteDAO extends ServiciosPacientes{
  DaoFactory daof;
    public ServiciosPacienteDAO() {
      try {
          InputStream input = null;
          input = ClassLoader.getSystemResourceAsStream("applicationconfig.properties");
          Properties properties=new Properties();
          properties.load(input);
          
          daof =DaoFactory.getInstance(properties);     
      } catch (IOException ex) {
          Logger.getLogger(ServiciosPacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
        
    @Override
    public List<Paciente> listPaciente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
           daof.beginSession();
      Paciente load = daof.getDaoPaciente().load(idPaciente, tipoid);return load;
    }

    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
       daof.beginSession();
       daof.getDaoPaciente().save(p);
    }

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
