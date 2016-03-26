package edu.eci.pdsw.samples.tests;

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

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientePersistenceTest {
    
    public PacientePersistenceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
     @Test
    public void pacienteConMasDeUnaConsulta() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        daof.beginSession();
        
        
        DaoPaciente daoPaciente = daof.getDaoPaciente();
        
        Paciente p = new Paciente(1019129303, "CC", "Sergio", java.sql.Date.valueOf("1995-06-11"));
        Consulta c1 = new Consulta(java.sql.Date.valueOf("2012-03-05"), "Consulta medica");
        Consulta c2 = new Consulta(java.sql.Date.valueOf("2013-04-10"), "Presenta una anomalia");
        p.getConsultas().add(c2);
        p.getConsultas().add(c1);
        
        
        daoPaciente.save(p);
        
        Paciente p2 = daoPaciente.load(1019129303, "CC");
        
         //System.out.println("prueba1: "+p.toString());
         //System.out.println("prueba1: "+p2.toString());
               
        assertTrue("Los pacientes no son iguales",p.getId()==p2.getId());
                
        daof.commitTransaction();
        daof.endSession();
    }
    

    
    
    
    @Test
    public void soloUnaConsulta()  throws IOException, PersistenceException{
        InputStream input=null;
        input=ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        daof.beginSession();
        
        DaoPaciente daoPaciente=daof.getDaoPaciente();
        
        Paciente p1=new Paciente(1234567890, "CC","Manuel Felipe" , java.sql.Date.valueOf("1990-03-21"));
        
        Consulta c1=new Consulta(java.sql.Date.valueOf("2005-01-01"),"medicina general");
        p1.getConsultas().add(c1);
        
        daoPaciente.save(p1);
        
        Paciente resp=daoPaciente.load(1234567890, "CC");
        //System.out.println("prueba3: "+p1.toString());
        //System.out.println("prueba3: "+resp.toString());
        
        assertTrue("El paciente no quedo guardado",p1.getId()==resp.getId());
        
        daof.commitTransaction();
        daof.endSession();
    }
    
    @Test
    public void pacienteYaExistente()throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        daof.beginSession();
        
        DaoPaciente nDao = daof.getDaoPaciente();
        Paciente paciente = new Paciente(1014266592, "CC", "Sergio", java.sql.Date.valueOf("1995-06-11"));
        
        Consulta consulta1 = new Consulta(java.sql.Date.valueOf("2016-03-12"), "Incapacidad");
        Consulta consulta2 = new Consulta(java.sql.Date.valueOf("2016-04-12"), "Retirar cita");
        
        paciente.getConsultas().add(consulta1);
        paciente.getConsultas().add(consulta2);
        
        nDao.save(paciente);
        
        boolean band = false;
        
        try{
            nDao.save(paciente);
        }catch (Exception e){
            
            band = true;
        }
        
        assertTrue("Guardo un paciente que ya estaba guaardado", band);
        
        daof.commitTransaction();
        daof.endSession();
        
        
    }
    
    @Test
    public void sinConsultas()throws IOException,PersistenceException{
        InputStream input=null;
        input=ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties proper=new Properties();
        proper.load(input);
        DaoFactory dafo=DaoFactory.getInstance(proper);
        
        dafo.beginSession();
        DaoPaciente dao=dafo.getDaoPaciente();
        
        Paciente paz=new Paciente(123456,"CC","Sergio",java.sql.Date.valueOf("1995-06-06") );
        dao.save(paz);
        
        Paciente res=dao.load(123456, "CC");
        
        assertTrue("no se pudo guardar el paciente",paz.getId()==res.getId());
        
        
        dafo.commitTransaction();
        dafo.endSession();
    
    
    
    
    }
    
    
    
    

   
    
}
