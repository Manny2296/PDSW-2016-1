/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.jdbcimpl;

import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Felipe
 */
public class MyBatisDaoFactory extends DaoFactory{
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlss ;
    public MyBatisDaoFactory() {
       sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }
    
    

    @Override
    public void beginSession() throws PersistenceException {
        
                
       sqlss   = sqlSessionFactory.openSession();
            }

    @Override
    public DaoPaciente getDaoPaciente() {

        return new MyBatisDaoPaciente(sqlss);
    }

    @Override
    public void commitTransaction() throws PersistenceException {
        sqlss.commit();
    }

    @Override
    public void rollbackTransaction() throws PersistenceException {
        sqlss.rollback();
    }

    @Override
    public void endSession() throws PersistenceException {
      sqlss.close();
    }
    
}
