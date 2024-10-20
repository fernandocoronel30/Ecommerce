package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/categorias/") // http://localhost:8080/api/categorias/
public class CategoriaController {
	private final CategoriaService categoriaService;

	@Autowired // sirve para inyectar automáticamente una instancia de CategoriaService
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}// constructor

	@GetMapping // asocia un método con una URL y el método será ejecutado cuando alguien acceda
				// a esa URL a través del método GET
	public List<Categoria> getCategorias() {
		return categoriaService.getAllCategorias();
	}// getCategorias

	@GetMapping(path = "{id}") // http://localhost:8080/api/categorias/1
	public Categoria getCategoria(@PathVariable("id") Long id) {
		return categoriaService.getCategoria(id);
	}// getCategorias

	@PostMapping // se utiliza para manejar solicitudes HTTP POST. Permite definir un método
					// dentro de un controlador que se invoca automáticamente cuando el servidor
					// recibe una petición POST
	public Categoria addCategoria(@RequestBody Categoria categoria) {
		// @RequestBody:permite que los datos enviados en el cuerpo de una solicitud
		// HTTP, como JSON o XML, sean mapeados automáticamente a un objeto Java dentro
		// del controlador
		return categoriaService.addCategoria(categoria);
	}// addCategoria

	@DeleteMapping(path = "{id}")
	public Categoria deleteCategoria(@PathVariable("id") Long id) {
		return categoriaService.deleteCategoria(id);
	}// deleteCategoria

	@PutMapping(path = "{id}")
	public Categoria updateCategoria(@PathVariable("id") Long id, 
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String descripcion){
		return categoriaService.updateCategoria(id, nombre, descripcion);
	}// updateCategoria

}// class CategoriaController
