package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	public final ProductoRepository productoRepository; 
	
	@Autowired	
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}// getAllProductos

	public Producto getProducto(Long prodId) {
		return productoRepository.findById(prodId).orElseThrow(
				() -> new IllegalArgumentException("El producto con el id [" + prodId + "] no existe")
				);
	}// getProducto

	public Producto deleteProducto(Long prodId) {
		Producto prod = null;
		if(productoRepository.existsById(prodId)) {
			prod= productoRepository.findById(prodId).get();
			productoRepository.deleteById(prodId);
		}//if exist
		return prod;
	}// deleteProducto

	public Producto updateProducto(Long prodId, String nombre, String descripcion, String imagen, Double precio, Long categoria_id) {
		Producto prod = null;
		
			if (productoRepository.existsById(prodId)) {
				Producto producto = productoRepository.findById(prodId).get();
				// cuando es una linea no llevan corchetes los if
				if (nombre != null) producto.setNombre(nombre);
				if (descripcion != null) producto.setDescripcion(descripcion);
				if (imagen != null)	producto.setImagen(imagen);
				if (precio != null)	producto.setPrecio(precio.doubleValue());
				if (categoria_id != null) producto.setCategoria_id(categoria_id);
				productoRepository.save(producto);
				prod = producto;
			} // if exist
		return prod;
	}// updateProducto
	
	public Producto addProducto(Producto producto) {
		Optional<Producto> prod = productoRepository.findByNombre(producto.getNombre());
		if(prod.isEmpty()) {//sino existe o esta vacío el nombre
			productoRepository.save(producto);			
		} else{
			System.out.println("El producto [" + producto.getNombre() + "] ya se encuentra registrado");		
		}//if isEmpty
		return producto;
	}// addProducto

}// class ProductoService
