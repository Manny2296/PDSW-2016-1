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
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import edu.edi.pdsw.peristence.DaoFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
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
     * @throws java.io.IOException
     */
    @Test
    public void registroPacienteTest() throws ExcepcionServiciosPacientes, IOException{

          
   
        
     
        //assert que verifica la carga de un usuario por medio del nombre obtenido.
        //En este caso se verifica el usuario 33 .
   

              
    }
    
    
}
