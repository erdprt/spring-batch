package fr.erdprt.samples.persistance.model.products.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.erdprt.samples.persistance.model.products.RefCategory;

@Service
public interface RefCategoryDao extends JpaRepository<RefCategory, String> {

}
