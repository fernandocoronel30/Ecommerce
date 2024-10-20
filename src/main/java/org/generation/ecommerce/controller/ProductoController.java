package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.service.ProductoService;
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
@RequestMapping(path = "/api/productos/") // http://localhost:8080/api/productos/
public class ProductoController {
	private final ProductoService productoService;

	@Autowired // sirve para inyectar automáticamente una instancia de ProductoService
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}// constructor

	@GetMapping // asocia un método con una URL y el método será ejecutado cuando alguien acceda
				// a esa URL a través del método GET
	public List<Producto> getProductos() {
		return productoService.getAllProductos();
	}// getProductos

	@GetMapping(path = "{prodId}") // http://localhost:8080/api/productos/1
	public Producto getProducto(@PathVariable("prodId") Long prodId) {
		return productoService.getProducto(prodId);
	}// getProductos

	@PostMapping // se utiliza para manejar solicitudes HTTP POST. Permite definir un método
					// dentro de un controlador que se invoca automáticamente cuando el servidor
					// recibe una petición POST
	public Producto addProducto(@RequestBody Producto producto) {
		// @RequestBody:permite que los datos enviados en el cuerpo de una solicitud
		// HTTP, como JSON o XML, sean mapeados automáticamente a un objeto Java dentro
		// del controlador
		return productoService.addProducto(producto);
	}// addProducto

	@DeleteMapping(path = "{prodId}")
	public Producto deleteProducto(@PathVariable("prodId") Long prodId) {
		return productoService.deleteProducto(prodId);
	}// deleteProducto

	@PutMapping(path = "{prodId}")
	public Producto updateProducto(@PathVariable("prodId") Long prodId, 
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String descripcion, 
			@RequestParam(required = false) String imagen,
			@RequestParam(required = false) Double precio,
			@RequestParam(required = false) Long categoria_id) {
		return productoService.updateProducto(prodId, nombre, descripcion, imagen, precio, categoria_id);
	}// updateProducto

}// class ProductoController
