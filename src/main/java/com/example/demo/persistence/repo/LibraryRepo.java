package com.example.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.persistence.domain.Library;

	@Repository
	public interface LibraryRepo extends JpaRepository<Library, Long> {

		// it allows us to implement
		// create
		// read
		// update
		// delete

		// custom sql statements e.g. find by make or model .........

	}
