package fr.erdprt.samples.persistance.model.products.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.erdprt.samples.persistance.model.products.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
