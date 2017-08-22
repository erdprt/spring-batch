package fr.erdprt.samples.persistance.model.products.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.erdprt.samples.persistance.model.products.Person;
import fr.erdprt.samples.persistance.model.products.services.dao.PersonDao;

public class PersonServiceImpl {

	@Autowired
	private PersonDao personDao;
	
	@Transactional
	public Person saveOrUpdate(Person person) {
		this.personDao.save(person);
		return person;
	}
}
