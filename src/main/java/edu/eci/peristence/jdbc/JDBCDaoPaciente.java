/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.peristence.jdbc;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.edi.pdsw.peristence.DaoPaciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import javax.persistence.PersistenceException;

/**
 *
 * @author 2093130
 */

    public class JDBCDaoPaciente implements DaoPaciente {

    Connection con;
    boolean validador;

    public JDBCDaoPaciente(Connection con) {
        this.con = con;
        
        validador = false;
    }
        

    @Override
    public Paciente load(int idpaciente, String tipoid) throws PersistenceException {
        PreparedStatement ps;
        Paciente p = null;
       // String query_loadpac="select nombre, fecha_nacimiento  from PACIENTES where id=?";
        String query_loadpac ="select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS, con.fecha_y_hora, con.resumen from PACIENTES as pac inner join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id where pac.id=? and pac.tipo_id='cc'";
       try {
          con.setAutoCommit(false);
          ps= con.prepareStatement(query_loadpac);
          ps.setInt(1,idpaciente);
  
            ResultSet executeQuery = ps.executeQuery();
        

                
            
              String string = executeQuery.getString(1);
             return new Paciente(idpaciente, tipoid, string, executeQuery.getDate(2));
               // System.out.println("Carga exitosa Paciente:" + aInt + "String:"+string  );
             
            
            
        } catch (SQLException ex) {
            throw new PersistenceException("Se cayo la transa "+idpaciente,ex);
        }
     
    }

 
    @Override
    public void save(Paciente p) throws PersistenceException {
        PreparedStatement ps,ps2;
        String query_addpaciente = "INSERT INTO PACIENTES(id,tipo_id,nombre,fecha_nacimiento) values(?,?,?,?)";
        String query_addconsulta = "INSERT INTO CONSULTAS(idCONSULTAS,fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id)values(?,?,?,?,'cc')";
        try {
        con.setAutoCommit(false);
        
        ps = con.prepareStatement(query_addpaciente);
        ps.setString(1,""+p.getId());
        ps.setString(2,p.getTipo_id());
        ps.setString(3,p.getNombre());
        ps.setString(4,""+p.getFechaNacimiento());
        
        
        ps2= con.prepareStatement(query_addconsulta);
        
        Iterator it = p.getConsultas().iterator();
        while(it.hasNext()){
        ps2.setString(1,"01");
        ps2.setDate(2, new Date(0));
        ps2.setString(3, "primera consulta");
        ps2.setString(4,""+p.getId());
        }
            int executeQuery = ps.executeUpdate();
            int executeUpdate = ps2.executeUpdate();
            if(executeQuery ==1)
            {System.out.println("Registro agregado satisfactoriamoente" + executeQuery);}
            if(executeUpdate ==1){
                System.out.println("Consulta agregada");
            }
           con.commit();
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
    }
