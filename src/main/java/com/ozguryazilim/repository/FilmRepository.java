package com.ozguryazilim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ozguryazilim.model.Film;

public interface FilmRepository extends JpaRepository<Film,Long> {

	
	//boolean update(Film film);
	
	Optional<Film> findById(Long id);
	
	void deleteById(Long id);
	
	List<Film> findByNameOrderByPublicationDate(String name);
	
	List<Film> findByGenreOrderByPublicationDate(String genre);
	
	@Query(value="select * from film f "
			+ " join film_actor fa on fa.film_id=f.id  "
			+ " join actor ac on ac.id=fa.actor_id where ac.name=:actor order by f.publication_date desc",nativeQuery=true)
	List<Film> findByActor(@Param(value = "actor") String actor);
}
