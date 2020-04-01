package com.ncuello.service;

import java.util.List;

import com.ncuello.model.Categoria;

public interface ICategoriasService {
	
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
}
