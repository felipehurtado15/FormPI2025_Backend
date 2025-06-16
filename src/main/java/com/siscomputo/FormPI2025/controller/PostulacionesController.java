package com.siscomputo.FormPI2025.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siscomputo.FormPI2025.config.Dto.PostulacionesDTO;
import com.siscomputo.FormPI2025.entity.Postulacion;
import com.siscomputo.FormPI2025.services.PostulacionesService;

@RestController
@RequestMapping("/api/public/postulaciones")
public class PostulacionesController {
	
	@Autowired
	PostulacionesService postulacionesService;
	
	@GetMapping
	public List<Postulacion> listar () {
		return postulacionesService.listar();
	}
	
	@GetMapping("/uuid")
	public String get () {
		return "Hola mundo";
	}
	
	@PostMapping()
	public Postulacion crear( @RequestBody PostulacionesDTO dto )  {
		return postulacionesService.crear(dto);
	}

}
