package fr.erdprt.samples.persistance.model.products.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.erdprt.samples.persistance.model.products.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

	@Query("select pers from person as pers LEFT JOIN FETCH pers.address where pers.code=:personCode")
	public Person findByCode(@Param("personCode") final String personCode);

}
