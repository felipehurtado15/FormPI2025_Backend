package com.siscomputo.FormPI2025.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siscomputo.FormPI2025.config.Dto.PostulacionesDTO;
import com.siscomputo.FormPI2025.entity.Postulacion;
import com.siscomputo.FormPI2025.repository.PostulacionRepository;

@Service
public class PostulacionesService {
	
	@Autowired
	PostulacionRepository postulacionRepository;
	
	public Postulacion crear (PostulacionesDTO data) {
		Postulacion p = new Postulacion();
		p.setNombre(data.getNombre());
		p.setDireccion(data.getDireccion());
		return postulacionRepository.save(p);
	}
	
	public List<Postulacion> listar () {
	   return	postulacionRepository.findAll();
	}
	
}
