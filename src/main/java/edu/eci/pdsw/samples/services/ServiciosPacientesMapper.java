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
import java.sql.Date;
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
      Paciente tmp2;
      Paciente seleccion1;
SqlSessionFactory sqlSessionFactory ;
    
    public ServiciosPacientesMapper() {
        sqlSessionFactory= getSqlSessionFactory();
   tmp2 = this.tmp;
   seleccion1 = this.seleccion;
       
    }

 
    
    
          public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }




 
  



          @Override
    public void registrarNuevoPaciente( Paciente p) throws ExcepcionServiciosPacientes {
     
                
       

        SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        pmap.insertPaciente(p);
     sqlss.commit();//o change body of generated methods, choose Tools | Templates.
    }



          @Override
    public void agregarConsulta() {
        System.out.println("LUego");
    }


          @Override
    public List<Consulta> getConsultaLista() {
              System.out.println("Entrre");
          Paciente seleccion2 = getSeleccion();
    SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
          List<Consulta> obtenerconsultas = pmap.obtenerconsultas(seleccion2.getId(), seleccion2.getTipo_id());
          if(obtenerconsultas.isEmpty() ){
              obtenerconsultas.add(new Consulta(new Date(0), "Manuel"));
          }
        return  obtenerconsultas;
    }





    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
                      
 System.out.println("Seleccion"+seleccion.getNombre());
   SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        pmap.insertConsulta(c, idPaciente, tipoid);
             sqlss.commit();
    }

    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
              
  SqlSession sqlss = sqlSessionFactory.openSession();
        

        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
              Paciente loadPacienteById = pmap.loadPacienteById(idPaciente, tipoid);
              return loadPacienteById;
    }

    @Override
    public List<Paciente> listPaciente() {
             
      SqlSession sqlss = sqlSessionFactory.openSession();

        
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        List<Paciente> pacientes = pmap.obtenerpacientes();
        return pacientes;
    }

    @Override
    public List<Paciente> obtenerpacientes() {
              

         
  SqlSession sqlss = sqlSessionFactory.openSession();
        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);
        List<Paciente> pacientes = pmap.obtenerpacientes();
        return pacientes;
    }
   
  
    
}
