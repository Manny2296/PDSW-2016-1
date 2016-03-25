/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.peristence.jdbc;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.edi.pdsw.peristence.DaoPaciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *
 * @author 2093130
 */

    public class JDBCDaoPaciente implements DaoPaciente {

    Connection con;
    boolean validador;
     List<Paciente> list_resp = new ArrayList<>();
    public JDBCDaoPaciente(Connection con) {
        this.con = con;
        
        validador = false;
    }
        

    @Override
    public Paciente load(int idpaciente, String tipoid) throws PersistenceException {
          PreparedStatement ps;
        Paciente p = null;
        Set<Consulta> cnsltas = new LinkedHashSet<>();
       // String query_loadpac="select nombre, fecha_nacimiento  from PACIENTES where id=?";
        String query_loadpac ="select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS, con.fecha_y_hora, con.resumen from PACIENTES as pac left join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id where pac.id=? and pac.tipo_id=?";
       try {
          con.setAutoCommit(false);
          ps= con.prepareStatement(query_loadpac);
          ps.setInt(1,idpaciente);
          ps.setString(2, tipoid);
  
            ResultSet executeQuery = ps.executeQuery();
           
            if(executeQuery.next()){
                
            
              String string = executeQuery.getString(1);
              p = new Paciente(idpaciente, tipoid, string, executeQuery.getDate(2));
      cnsltas.add(new Consulta(executeQuery.getDate(4), executeQuery.getString(5)));
                while (executeQuery.next()) {
                    cnsltas.add(new Consulta(executeQuery.getDate(4), executeQuery.getString(5)));   
                    
                }
                p.setConsultas(cnsltas);
                return p;
               // System.out.println("Carga exitosa Paciente:" + aInt + "String:"+string  );
             
            }
            
        } catch (SQLException ex) {
            throw new PersistenceException("Se cayo la transa "+idpaciente,ex);
        }
       return p;
     
    }

 
    @Override
      
    public void save(Paciente p) throws PersistenceException {
         if(p!=null){
             list_resp.add(p);
         }
        PreparedStatement ps,ps2;
        String query_addpaciente = "INSERT INTO PACIENTES(id,tipo_id,nombre,fecha_nacimiento) values(?,?,?,?)";
        String query_addconsulta = "INSERT INTO CONSULTAS(fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id)values(?,?,?,?)";
        try {
        con.setAutoCommit(false);
        ps = con.prepareStatement(query_addpaciente);
        ps.setString(1,""+p.getId());
        ps.setString(2,p.getTipo_id());
        ps.setString(3,p.getNombre());
        ps.setString(4,""+p.getFechaNacimiento());
        ps.execute();
        Iterator<Consulta> i = p.getConsultas().iterator();
        while(i.hasNext()){
            
            Consulta tmp = i.next();
            ps2 = con.prepareStatement(query_addconsulta);
            ps2.setDate(1,tmp.getFechayHora());
            ps2.setString(2, tmp.getResumen());
            ps2.setInt(3, p.getId());
            ps2.setString(4, p.getTipo_id());
            ps2.execute();
        }
   
        

         
            
      
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading a product. Agregado raro",ex);
        }
        
      

    }

    @Override
    public void update(Paciente p) throws PersistenceException {
        PreparedStatement ps;
        /*try {
            
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading a product.",ex);
        } */
        throw new RuntimeException("No se ha implementado el metodo 'load' del DAOPAcienteJDBC");
    }

    @Override
    public boolean isValidador() {
      return validador;
    }

    @Override
    public List<Paciente> pacientes() {
      
        try {
         
            PreparedStatement ps1;
            String query_selectall ="SELECT * FROM PACIENTES";
            con.setAutoCommit(false);
            ps1 = con.prepareStatement(query_selectall);
            ResultSet executeQuery = ps1.executeQuery();
            while(executeQuery.next()){
                Paciente tmp = new Paciente(executeQuery.getInt(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getDate(4));
             list_resp.add(tmp);
                
                
            }
            return list_resp;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_resp;
    }
    }
