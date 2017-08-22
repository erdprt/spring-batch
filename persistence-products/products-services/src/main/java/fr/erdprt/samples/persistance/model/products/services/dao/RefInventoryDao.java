package fr.erdprt.samples.persistance.model.products.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.erdprt.samples.persistance.model.products.RefInventory;

public interface RefInventoryDao extends JpaRepository<RefInventory, Long> {

}
