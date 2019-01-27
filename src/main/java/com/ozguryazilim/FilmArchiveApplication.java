package com.ozguryazilim;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FilmArchiveApplication{

	
	@Autowired
	DataSource datasource;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FilmArchiveApplication.class, args);
	}



	// bu metot geçici olarak yazılmıştır.
	// veritabanına role ekleniyor.
	@PostConstruct
	public void init() throws SQLException
	{
		datasource.getConnection().prepareStatement("delete from role").execute();
		datasource.getConnection().prepareStatement("insert into role value(1,\"ROLE_ADMIN\"),(2,\"ROLE_USER\")").execute();
		
	//	datasource.getConnection().commit();
	}
	
	

}

