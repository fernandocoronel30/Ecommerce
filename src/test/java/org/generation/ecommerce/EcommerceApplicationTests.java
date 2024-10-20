package org.generation.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommerce.model.Producto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	private final String token = "Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcnVlYmFwb3N0MkBlbWFpbC5jb20iLCJyb2xlIjoidXNlciIsImlhdCI6MTcyODkyNzEwMiwiZXhwIjoxNzI4OTcwMzAyfQ.JB42lZPwGt8PAK1NXBTkwR0phecEi9MSje1gN27tVn4";

	@Test
	@DisplayName("Se prueba el endpoint http://localhost:8080/api/productos/3")
	void pruebaGET() throws Exception {
		this.mockMvc.perform(get("/api/productos/2") )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("norma1.gif"))
				);
	}//pruebaGET
	
	@Test
	@DisplayName("Se prueba borrar el producto con el id 3 http://localhost:8080/api/productos/6")
	void pruebaDelete() throws Exception {
		this.mockMvc.perform( delete("/api/productos/6").header("Authorization", token) )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("Plumas Bic Cristal Dura Más combobox mas"))
				);
	}//pruebaDelete
	
	@Test
	@DisplayName("Se actualiza el producto con el id 1 http://localhost:8080/api/productos/1")
	void pruebaPut() throws Exception {
		this.mockMvc.perform( 
		put("/api/productos/7?precio=55.90&nombre=Plumas Bic Cristal Dura Más combobox box box")
		.header("Authorization", token) )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("Grip Silver"))
				);
	}//pruebaPut
	
	@Test
	@DisplayName("Se crea un producto http://localhost:8080/api/productos/")
	void pruebaPost() throws Exception {
		Producto prod = new Producto();
		prod.setNombre("Pluma y Lapicero Zebra Z-Grip Silver azulito y bonito y finito");
		prod.setDescripcion("Pluma y Lapicero Zebra Z-Grip Silver Azul");
		prod.setImagen("100161506.jpeg");
		prod.setPrecio(79);
		this.mockMvc.perform( 
				post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(prod))
				.header("Authorization", token) )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("Z-Grip Silver"))
				);
	}//pruebaPost
	
	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString
	
	

}//class EcommerceApplicationTests
