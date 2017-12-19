package dev.paie.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO GRADE (CODE, nbHeuresBase, tauxBase) VALUES(?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase());
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE GRADE SET CODE = ?, nbHeuresBase = ?, tauxBase = ?  WHERE ID = ? ";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
	}

	@Override
	public List<Grade> lister() {
		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}
	
	private class GradeMapper implements RowMapper<Grade> {
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
			Grade g = new Grade();
			g.setId(rs.getInt("ID"));
			g.setCode(rs.getString("CODE"));
			g.setNbHeuresBase(new BigDecimal(rs.getString("nbHeuresBase")));
			g.setTauxBase(new BigDecimal(rs.getString("tauxBase")));
			return g;
		}
	}
	
}


