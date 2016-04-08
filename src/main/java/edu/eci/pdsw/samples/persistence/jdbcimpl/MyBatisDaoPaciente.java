/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.jdbcimpl;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Felipe
 */
public class MyBatisDaoPaciente implements DaoPaciente{
    SqlSession session;
    public MyBatisDaoPaciente(SqlSession sq) {
        this.session = sq;
        
    }

    @Override
    public Paciente load(int id, String tipoid) throws PersistenceException {
          PacienteMapper pmap=session.getMapper(PacienteMapper.class);
     Paciente loadPacienteById = pmap.loadPacienteById(id, tipoid);
     session.commit();return loadPacienteById;
     
    }

    @Override
    public void save(Paciente p) throws PersistenceException {
          PacienteMapper pmap=session.getMapper(PacienteMapper.class);
       pmap.insertPaciente(p);
       session.commit();
    }

    @Override
    public void update(Paciente p) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> loadall() throws PersistenceException {
          PacienteMapper pmap=session.getMapper(PacienteMapper.class);
     List<Paciente> obtenerpacientes = pmap.obtenerpacientes();
     session.commit();
     return obtenerpacientes;
    }

    @Override
    public Paciente loadByid(int id, String tipoid) {
        PacienteMapper pmap = session.getMapper(PacienteMapper.class);
        Paciente loadPacienteById = pmap.loadPacienteById(id, tipoid); session.commit();return loadPacienteById;
    }
    
}
