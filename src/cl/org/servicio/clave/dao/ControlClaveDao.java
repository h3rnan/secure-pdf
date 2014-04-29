package cl.org.servicio.clave.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cl.org.servicio.clave.dao.mapper.ControlClaveMapper;
import cl.org.servicio.clave.dao.modelo.ControlClave;

import cl.org.servicio.clave.dao.MyBatisConn;

public class ControlClaveDao {

	private SqlSessionFactory sqlSessionFactory;

	public ControlClaveDao() {
		sqlSessionFactory = MyBatisConn.getInstance().getFactory();
	}

	public ControlClave selectById(int id){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ControlClaveMapper mapper = session.getMapper(ControlClaveMapper.class);
			ControlClave cc = mapper.selectById(id);
			return cc;
		} catch (Exception e) {
			System.out.println("Error obteniendo datos ");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public int updateById(int id, Date fecha){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ControlClaveMapper mapper = session.getMapper(ControlClaveMapper.class);
			int r = mapper.updateById(id, fecha);
			session.commit();
			return r;
		} catch (Exception e) {
			System.out.println("Error actualizando datos ");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return -1;
	}

    public int insert(ControlClave contact){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	ControlClaveMapper mapper = session.getMapper(ControlClaveMapper.class);
            int r = mapper.insert(contact);
            session.commit();
            return r;
        } catch (Exception e){
			System.out.println("Error insertando datos ");
			e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

}
