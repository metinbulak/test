package com.ozguryazilim;

import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozguryazilim.model.Film;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "username", roles={"ADMIN"})
@Transactional
public class HomeControllerTest {

	
	
	  @Autowired
	  private MockMvc mockMvc;
	
	  
	  
	  @Test
		public void insert() throws Exception
		{
		  
		  Film film = new Film();
		  film.setId(86L);
		  film.setDescription("description insert test");
		  
		  ObjectMapper mapper=new ObjectMapper();
		  String json = mapper.writeValueAsString(film);
		
		  
		  
			MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/add");
		
			post.param("description", "description");
			post.param("genre", "genre");
			post.param("language", "language");
			post.param("publicationDate", "2019-01-22");
			
			MultiValueMap<String,String> map = new LinkedMultiValueMap();
			map.add("actorid", "1");
			map.add("actorid", "2");
			map.add("actorid", "3");
			post.params(map);
			
			
			
			
			mockMvc.perform(post)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(302));
		}
	  
	    @Test
		public void update() throws Exception
		{
		  
		  Film film = new Film();
		  film.setId(93L);
		  film.setDescription("description update test");
		  
		  ObjectMapper mapper=new ObjectMapper();
		  String json = mapper.writeValueAsString(film);
		  System.out.println(json);
		  
		  MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		  
			MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/update");
			post.content(json)
			.contentType(APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON);
			
			mockMvc.perform(post)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(302));
		}
	  
	  
	    @Test
		public void deleteAdmin() throws Exception
		{
			MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/delete/1");
			
			mockMvc.perform(get).andExpect(MockMvcResultMatchers.status().is(302));
		}
	  
	  
	  
	@Test
	@WithMockUser(username = "username", roles={"USER"})
	public void delete() throws Exception
	{
		MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/delete/1");
		
		mockMvc.perform(get).andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
	
}
