package fr.erdprt.samples.persistance.model.products.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.erdprt.samples.persistance.model.products.RefProduct;

public interface RefProductDao extends JpaRepository<RefProduct, Long> {

	
	@Query("select prod from refProduct prod where prod.refProductStatus.code=:productStatus")
	public List<RefProduct> findByStatus(@Param("productStatus") final String status);
	
	@Query("select prod from refProduct as prod LEFT JOIN FETCH prod.refInventory where prod.code=:productCode")
	public RefProduct findByCode(@Param("productCode") final String productCode);
	
}
