package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

public final CategoriaRepository categoriaRepository; 
	
	@Autowired	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}// getAllCategorias

	public Categoria getCategoria(Long id) {
		return categoriaRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("La categoria con el id [" + id + "] no existe")
				);
	}// getCategoria

	public Categoria deleteCategoria(Long id) {
		Categoria cat = null;
		if(categoriaRepository.existsById(id)) {
			cat= categoriaRepository.findById(id).get();
			categoriaRepository.deleteById(id);
		}//if exist
		return cat;
	}// deleteCategoria

	public Categoria updateCategoria(Long id, String nombre, String descripcion) {
		Categoria cat = null;
		
			if (categoriaRepository.existsById(id)) {
				Categoria categoria = categoriaRepository.findById(id).get();
				// cuando es una linea no llevan corchetes los if
				if (nombre != null) categoria.setNombre(nombre);
				if (descripcion != null) categoria.setDescripcion(descripcion);
				cat = categoria;
			} // if exist
		return cat;
	}// updateCategoria
	
	public Categoria addCategoria(Categoria categoria) {
		Optional<Categoria> prod = categoriaRepository.findByNombre(categoria.getNombre());
		if(prod.isEmpty()) {//sino existe o esta vacío el nombre
			categoriaRepository.save(categoria);			
		} else{
			System.out.println("El Categoria [" + categoria.getNombre() + "] ya se encuentra registrado");		
		}//if isEmpty
		return categoria;
	}// addCategoria
	
}//class CategoriaService
