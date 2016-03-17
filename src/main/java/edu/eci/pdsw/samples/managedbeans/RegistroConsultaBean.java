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
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.io.Serializable;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;


/**
 *
 * @author hcadavid
 */

@ManagedBean (name="RegistroConsultaBean")
@SessionScoped

public class RegistroConsultaBean implements Serializable{
    
    ServiciosPacientes sp=ServiciosPacientes.getInstance();
    private Paciente Paciente;
    

    public ServiciosPacientes getSp() {
        return sp;
    }

    public void setSp(ServiciosPacientes sp) {
        this.sp = sp;
    }
    
    public Paciente getPaciente(){
        return Paciente;
    }
    
    
    
    
}
