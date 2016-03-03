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

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.util.Date;
import javax.validation.constraints.AssertFalse;
import javax.xml.crypto.Data;
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
    
    @Before
    public void setUp() {
    }
    
    /**
     * 
     */
    @Test
    public void registroPacienteTest() throws ExcepcionServiciosPacientes{
        Date fecha=new Date(2016, 03, 03);
        Paciente paciente=new Paciente(1, "Medico", "Jose Suarez", (java.sql.Date) fecha);
        ServiciosPacientesStub isp = null;
        
        boolean Band=false;
        try{
            isp.registrarNuevoPaciente(paciente);
            Band=true;
        }
        catch(ExcepcionServiciosPacientes e){
            Band=false;
        }
        assertTrue(Band);
        
        
       
    }
    
    
}
