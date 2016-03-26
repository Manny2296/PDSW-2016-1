/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
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
package edu.eci.pdsw.samples.persistence.jdbcimpl;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCDaoPaciente implements DaoPaciente {

    Connection con;

    public JDBCDaoPaciente(Connection con) {
        this.con = con;
    }
        

    @Override
    public Paciente load(int idpaciente, String tipoid) throws PersistenceException {
         Set<Consulta> consultas = new HashSet<Consulta>();
        Paciente p = null;
        try {
            PreparedStatement loading = null;
            String cargarPaciente = "select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS consultaid, con.fecha_y_hora, con.resumen from PACIENTES as pac left join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id where pac.id=? and pac.tipo_id=?";
            con.setAutoCommit(false);
            loading = con.prepareStatement(cargarPaciente);
            loading.setInt(1, idpaciente);
            loading.setString(2, tipoid);
            ResultSet resp = loading.executeQuery();
            con.commit();
            if(resp.next()){
                
                p = new Paciente(idpaciente, tipoid,resp.getString("nombre"),resp.getDate("fecha_nacimiento"));
                
                if (resp.getString("consultaid")!=null){
                    
                    Consulta c = new Consulta(resp.getDate("fecha_y_hora"), resp.getString("resumen"));
                    consultas.add(c);
                    c.setId(1);
                }
            }
            while(resp.next()){
                p = new Paciente(idpaciente, tipoid,resp.getString("nombre"),resp.getDate("fecha_nacimiento"));
                Consulta c = new Consulta(resp.getDate("fecha_y_hora"), resp.getString("resumen"));
                consultas.add(c);
                c.setId(1);
            }
            p.setConsultas(consultas);
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading "+idpaciente,ex);
        }
        return p;
    }
    
    @Override
    public List<Paciente> loadall() throws PersistenceException{
         List<Paciente> list = new LinkedList<Paciente>();
        PreparedStatement ps=null;
        try{
            String consulta = "select * from PACIENTES";
            con.setAutoCommit(false);
            ps=con.prepareStatement(consulta);
            ResultSet executeQuery = ps.executeQuery();
            Paciente paci = null;
            while(executeQuery.next()){
                paci = new Paciente (executeQuery.getInt("id"), executeQuery.getString("tipo_id"),executeQuery.getString("nombre"),executeQuery.getDate("fecha_nacimiento"));
                list.add(paci);
            }
            con.commit();
        }catch (SQLException ex){
            throw new PersistenceException("An error ocurred while loading a list.",ex); 
        }
        return list;
    }
    

    @Override
    public void save(Paciente p) throws PersistenceException {
        try {
            PreparedStatement saved = null;
            String insertPaciente = "INSERT INTO PACIENTES VALUES (?,?,?,?)";
            con.setAutoCommit(false);
            saved = con.prepareStatement(insertPaciente);
            saved.setInt(1, p.getId());
            saved.setString(2, p.getTipo_id());
            saved.setString(3, p.getNombre());
            saved.setDate(4, p.getFechaNacimiento());
            saved.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while saving a paciente.",ex);
        }
        
        try{
            Set<Consulta> consultas = p.getConsultas();
            for (Consulta consul : consultas){
                if(consul.getId() == -1){
                    PreparedStatement guarde = null;
                    String insertConsulta = "INSERT INTO CONSULTAS (fecha_y_hora,resumen,PACIENTES_id, PACIENTES_tipo_id) VALUES (?,?,?,?)";
                    con.setAutoCommit(false);
                    guarde = con.prepareStatement(insertConsulta);
                    guarde.setDate(1, consul.getFechayHora());
                    guarde.setString(2, consul.getResumen());
                    guarde.setInt(3, p.getId());
                    guarde.setString(4, p.getTipo_id());
                    guarde.executeUpdate();
                    consul.setId(1);
                    con.commit();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Paciente p) throws PersistenceException {
     PreparedStatement updates;
        try{
            String actualice = "INSERT INTO CONSULTAS (fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id) values (?,?,?,?)";
            con.setAutoCommit(false);
            updates = con.prepareStatement(actualice);
            for (Consulta c : p.getConsultas()){
                if (c.getId()==-1){
                    updates.setDate(1, c.getFechayHora());
                    updates.setString(2, c.getResumen());
                    updates.setInt(3, p.getId());
                    updates.setString(4, p.getTipo_id());
                    updates.executeUpdate();
                    con.commit();
                    c.setId(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
