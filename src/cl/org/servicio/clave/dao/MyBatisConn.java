package cl.org.servicio.clave.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cl.org.servicio.clave.dao.mapper.ControlClaveMapper;

import cl.org.servicio.clave.dao.MyBatisConn;

public class MyBatisConn {

	private static final MyBatisConn self = new MyBatisConn();
	private SqlSessionFactory factory;

	private MyBatisConn() {
		reload();
	}

	public static MyBatisConn getInstance() {
		return self;
	}


	public SqlSessionFactory getFactory() {
		return factory;
	}

	public void reload() {
		String resource = "resources/db-config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
			factory.getConfiguration().addMapper(ControlClaveMapper.class);
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo de configuración");
			e.printStackTrace();
		}
	}

}
