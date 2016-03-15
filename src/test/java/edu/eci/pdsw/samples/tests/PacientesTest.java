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
     * pre:El paciente existe.
     * pos:el paciente queda registrado en serviciosPacientesStub
     * objetivo:Registrar a un paciente.
     * @throws ExcepcionServiciosPacientes
     */
    @Test
    public void registroPacienteTest() throws ExcepcionServiciosPacientes{
        boolean Band = false;
        try{
        Paciente paciente=new Paciente(123, "CC", "Sergio Aponte", java.sql.Date.valueOf("2016-03-03"));      
        ServiciosPacientesStub isp =new ServiciosPacientesStub();
        isp.registrarNuevoPaciente(paciente);
        Band=true;
        }
        catch(ExcepcionServiciosPacientes e){
            e.getStackTrace();
        }     
        assertTrue(Band);
              
    }
    
    
}
