/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Felipe
 */
public class ServiciosPacientesMapper extends ServiciosPacientes{
          public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }




 
  



          @Override
    public void registrarNuevoPaciente( Paciente p) throws ExcepcionServiciosPacientes {
     
                 SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        

        SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
 pmap.insertPaciente(p);//o change body of generated methods, choose Tools | Templates.
    }



          @Override
    public void agregarConsulta() {
        System.out.println("LUego");
    }


          @Override
    public List<Consulta> getConsultaLista() {
        System.out.println("Luego");
        return new ArrayList<>();
    }


    public List<Paciente> obtenerPacientes() {
                   SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
        System.out.println("Ya hicimos la sesion");
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
       
            List<Paciente> pacientes = pmap.obtenerPacientes();
            System.out.println("Lista:" + pacientes.size());
        return pacientes;
    }



    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
                      SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();    

        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        pmap.insertConsulta(c, idPaciente, tipoid);
    }

    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
                   SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
              Paciente loadPacienteById = pmap.loadPacienteById(idPaciente, tipoid);
              return loadPacienteById;
    }

    @Override
    public List<Paciente> listPaciente() {
                            SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        

        SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        List<Paciente> pacientes = pmap.obtenerPacientes();
        return pacientes;
    }

    @Override
    public List<Paciente> obtenerpacientes() {
                    SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        List<Paciente> pacientes = pmap.obtenerPacientes();
        return pacientes;
    }
   
  
    
}
