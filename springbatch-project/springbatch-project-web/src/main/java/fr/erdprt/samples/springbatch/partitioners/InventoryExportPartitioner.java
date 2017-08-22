package fr.erdprt.samples.springbatch.partitioners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class InventoryExportPartitioner implements Partitioner, InitializingBean {

	private static final Logger LOGGER	=	LoggerFactory.getLogger(InventoryExportPartitioner.class);
			
	public static final String ID_DECL_DSN_MIN = "ID_DECL_DSN_MIN";

	public static final String ID_DECL_DSN_MAX = "ID_DECL_DSN_MAX";

	/** Pattern de la requête SQL */
	private String sqlPartitionnement = null;

	/** JdbcTemplate permettant de requéter la BDD */
	private transient NamedParameterJdbcTemplate jdbcTemplate = null;

	/** Nb Threads */
	private int nbThreads = 1;

	public InventoryExportPartitioner() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		final Map<String, ExecutionContext> map = new HashMap<String, ExecutionContext>();

		// Récupération idMin et idMax de l'ensemble des éléments à traiter
		final IntervalleBean intervalleBean = recupererIntervalleIdentifiants();

		if (intervalleBean.getMinId() != null && intervalleBean.getMaxId() != null) {

			Integer minId = intervalleBean.getMinId().intValue();
			Integer maxId = intervalleBean.getMaxId().intValue();
			LOGGER.trace("Partitionnement sur l'intervalle (minId,maxId)=({},{})", new Object[] {minId, maxId});

			if (maxId - minId + 1 < nbThreads) {
				nbThreads = maxId - minId + 1;
			}

			LOGGER.trace("Nombre de partitions à créer {} : ", nbThreads);

			int targetSize = (maxId - minId + 1) / nbThreads;

			int number = 0;
			int start = minId;
			int end = start + targetSize - 1;

			while (start <= maxId) {

				ExecutionContext value = new ExecutionContext();
				map.put("partition" + number, value);
				if (end >= maxId) {
					end = maxId;
				}
				LOGGER.trace("Creation d'une partition pour ({},{})", new Object[] {start, end});

				value.putLong(ID_DECL_DSN_MIN, Long.valueOf(start));
				value.putLong(ID_DECL_DSN_MAX, Long.valueOf(end));
				start += targetSize;
				end += targetSize;
				number++;
			}
			LOGGER.trace("Nombre de partitions crées {} : ", number);
			return map;
		}

		return map;
	}

	/**
	 * Récupération de l'idMin et de l'idMax des éléments à traiter
	 * @return IntervalBean contenant idMin et idMax
	 */
	private IntervalleBean recupererIntervalleIdentifiants() {
		// Exécution de la requête
		Map<String, Object> namedParameters = new HashMap<String, Object>();

		return jdbcTemplate.queryForObject(sqlPartitionnement, namedParameters, new RowMapper<IntervalleBean>() {

			@Override
			public IntervalleBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				IntervalleBean intervalBean = new IntervalleBean();
				intervalBean.setMinId(resultSet.getLong(1));
				intervalBean.setMaxId(resultSet.getLong(2));
				return intervalBean;
			}

		});
	}

	/**
	 * Création du JdbcTemplate à partir de la DS
	 * @param pDataSource the dataSource to set
	 */
	public void setDataSource(final DataSource pDataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(pDataSource);
	}

	public String getSqlPartitionnement() {
		return sqlPartitionnement;
	}

	/**
	 * @param sqlPartitionnement the sqlPartitionnement to set
	 */
	public void setSqlPartitionnement(String sqlPartitionnement) {
		this.sqlPartitionnement = sqlPartitionnement;
	}

	/**
	 * Permet de renseigner la valeur de l'attribut {@link #nbThreads}.
	 * @param nbThreads La valeur de l'attribut nbThreads
	 */
	public void setNbThreads(final int nbThreads) {
		this.nbThreads = nbThreads;
	}

	/**
	 * Classe pour stocker les identifiants min et max du partitionnement
	 * @author GIRC
	 */
	protected class IntervalleBean {

		private Long minId;

		private Long maxId;

		public Long getMinId() {
			return minId;
		}

		public void setMinId(Long minId) {
			this.minId = minId;
		}

		public Long getMaxId() {
			return maxId;
		}

		public void setMaxId(Long maxId) {
			this.maxId = maxId;
		}
	}

}
