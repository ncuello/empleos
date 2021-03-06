package com.ncuello.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ncuello.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacantesService {
	
	private List<Vacante> lista = null;

	public VacantesServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 lista = new LinkedList<Vacante>();
		
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero de Comunicaciones");
			vacante1.setDescripcion("Se solicita ingeniero para dar soporte.");
			vacante1.setFecha(sdf.parse("06-03-2020"));
			vacante1.setSalario(14700.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ingeniero Civil");
			vacante2.setDescripcion("Se solicita ingeniero civil para diseñar puente.");
			vacante2.setFecha(sdf.parse("06-03-2020"));
			vacante2.setSalario(14500.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Contador Público");
			vacante3.setDescripcion("Se solicita contador con 5 años de experiencia.");
			vacante3.setFecha(sdf.parse("05-02-2020"));
			vacante3.setSalario(8500.0);
			vacante3.setDestacado(1);
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	@Override
	public List<Vacante> buscarTodas() {
		return lista;
	}


	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> vacante = lista.stream().filter(v -> v.getId() == idVacante).findFirst();
		if (vacante.isPresent())
			return vacante.get();
		return null;
	}

}
