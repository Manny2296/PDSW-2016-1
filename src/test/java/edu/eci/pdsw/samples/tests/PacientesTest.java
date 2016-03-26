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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.validation.constraints.AssertFalse;
import javax.xml.crypto.Data;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientesTest {
    
    public PacientesTest() {
    }
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
     /**
     * Registra un nuevo paciente y sus respectivas consultas (si existiesen).
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public void registrarNuevoPaciente(PacienteMapper pmap, Paciente p){
    


        pmap.insertPaciente(p);
        System.out.println("Paciente ");
         
        
    }
    public void registrarConsultaaPaciente(PacienteMapper pmap,Consulta con,int id,String tipoid){
        pmap.insertConsulta(con, id, tipoid);
    }
    @Before
    public void setUp() {
    }
    
    /**
     * pre:El paciente existe.
     * pos:el paciente queda registrado en serviciosPacientesStub
     * objetivo:Registrar a un paciente.
     * @throws ExcepcionServiciosPacientes
     */
    
    @Test
    public void registroPacienteTest() throws ExcepcionServiciosPacientes{
         SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        PacienteMapper pmap=sqlss.getMapper(PacienteMapper.class);

     
       //System.out.println(pmap.loadPacienteById(2,"cc"));
      
        //Aca se agrega un paciente coon 0 consultas.
        Paciente tmp = new Paciente(101011, "CC", "Manuel Chiviado R,", new java.sql.Date(0));
      Consulta c = new Consulta(new java.sql.Date(0),"Chuck Norris");
      //La consulta c es agregada  con su foranea como el id del Paciente tmp
     // registrarConsultaaPaciente(pmap, c,1998, "CC");

      
      //Aca se agrega el paciente 
       registrarNuevoPaciente(pmap,tmp);
        
        
       //Cargamos el paciente para verificar sus consultas
        System.out.println("Paciente cargado :" + pmap.loadPacienteById(1018484513,"CC"));
       sqlss.commit();
        
        
        sqlss.close();
              
    }
    
    
}
