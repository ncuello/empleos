package com.ncuello.service;

import java.util.List;

import com.ncuello.model.Vacante;

public interface IVacantesService {
	
	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(Integer idVacante);
	
	void guardar(Vacante vacante);

}
