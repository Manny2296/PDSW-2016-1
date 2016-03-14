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
import java.awt.event.ActionEvent;
import java.io.Serializable;
import static java.lang.Math.log;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;

import javax.faces.context.FacesContext;
import org.apache.log4j.Level;
import static org.apache.log4j.LogMF.log;
import static org.apache.log4j.LogSF.log;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


/**
 *
 * @author hcadavid
 */

@ManagedBean (name="RegistroConsultaBean")
@SessionScoped

public class RegistroConsultaBean implements Serializable{

    
    private Paciente selectedPaciente;

    
    ServiciosPacientes sp=ServiciosPacientes.getInstance();
    
    
    public Paciente getSelectedPaciente() {
        return selectedPaciente;
    }

    public void setSelectedPaciente(Paciente selectedPaciente) {
        this.selectedPaciente = selectedPaciente;
    }

    public ServiciosPacientes getSp() {
        return sp;
    }

    public void setSp(ServiciosPacientes sp) {
        this.sp = sp;
    }
    
  
    
    
    
    
    
}
