package com.ncuello.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncuello.model.Vacante;
import com.ncuello.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacantesService service;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Vacante> vacantes = service.buscarTodas();
		
		System.out.println("Vacantes: " + vacantes);
		model.addAttribute("vacantes", vacantes);
		
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante) {
		return "vacantes/formVacante";
	}
	
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			return "vacantes/formVacante";
		}
		
		System.out.println("Vacante " + vacante);
		service.guardar(vacante);
		
		attributes.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/vacantes/index";
	}
	
//	@PostMapping("/save")
//	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, 
//			@RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha, 
//			@RequestParam("destacado") int destacado, @RequestParam("salario") double salario, 
//			@RequestParam("detalles") String detalles) {
//		
//		System.out.println("Nombre Vacante " + nombre);
//		System.out.println("Descripción " + descripcion);
//		System.out.println("Estatus " + estatus);
//		System.out.println("Fecha Publicación " + fecha);
//		System.out.println("Destacado " + destacado);
//		System.out.println("Salario ofrecido " + salario);
//		System.out.println("Detalles " + detalles);
//		
//		return "vacantes/listVacantes";
//	}
	
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante );
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		System.out.println("IdVacante: " + idVacante);
		
		Vacante vacante = service.buscarPorId(idVacante);

		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacantes", vacante);
		
//		Buscar los detalles de la vacante en la BD
		
		return "detalle";
	}

	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
