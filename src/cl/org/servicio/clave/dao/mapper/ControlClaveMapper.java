package cl.org.servicio.clave.dao.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cl.org.servicio.clave.dao.modelo.ControlClave;


public interface ControlClaveMapper {
	final String SELECT_BY_ID = "SELECT * FROM CONTROL_CLAVES WHERE ID = #{id}";
	final String UPDATE_BY_ID = "UPDATE CONTROL_CLAVES SET NUMERO_INTENTOS=NUMERO_INTENTOS-1, FECHA_ACTUALIZACION = #{fecha} WHERE ID = #{id}";
	final String INSERT_INTO  = "INSERT INTO CONTROL_CLAVES (ID, RUT, NUMERO_INTENTOS) VALUES (#{id}, #{rut}, #{numeroIntentos})";

	@Select(SELECT_BY_ID)
	@Results(value = {
		@Result(property="id"),
		@Result(property="rut", column="RUT"),
		@Result(property="numeroIntentos", column="NUMERO_INTENTOS"),
		@Result(property="fechaActualizacion", column="FECHA_ACTUALIZACION"),
		@Result(property="estado", column="ESTADO")
	})
	public ControlClave selectById(int id);

	@Update(UPDATE_BY_ID)
	public int updateById(@Param("id") int id, @Param("fecha") Date fecha);

	@Insert(INSERT_INTO)
    @Options(useGeneratedKeys = false, keyProperty = "id")
    public int insert(ControlClave contact);

}
