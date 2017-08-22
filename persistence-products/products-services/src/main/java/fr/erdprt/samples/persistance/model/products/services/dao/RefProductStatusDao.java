package fr.erdprt.samples.persistance.model.products.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.erdprt.samples.persistance.model.products.RefProductStatus;

@Service
public interface RefProductStatusDao extends JpaRepository<RefProductStatus, Long> {

	
	@Query("select prodStatus from refProductStatus prodStatus where prodStatus.code=:statusCode")
	public RefProductStatus findByCode(@Param("statusCode") final String statusCode);

}
