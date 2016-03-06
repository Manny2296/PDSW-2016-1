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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class ConsultasTest {
    Paciente pa  = new Paciente(001,"CC","Manuel Felipe", java.sql.Date.valueOf("2016-03-03"));
    ServiciosPacientesStub sps=new ServiciosPacientesStub();
    
  
    public ConsultasTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroConsultasPacientesTest() throws ExcepcionServiciosPacientes{
        boolean bald=false;
        try{
        sps.agregarConsultaAPaciente(pa.getId(),"CC", new Consulta(new Date(2016,04,04),"Consulta  para medico"));
        bald=true;
        }
        catch(ExcepcionServiciosPacientes e){
            e.printStackTrace();
            
        }
        assertFalse(bald);
    }
    
    
}
