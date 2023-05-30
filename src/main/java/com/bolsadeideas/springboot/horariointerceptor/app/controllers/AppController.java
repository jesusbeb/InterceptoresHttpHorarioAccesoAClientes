package com.bolsadeideas.springboot.horariointerceptor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Anotamos para Spring
@Controller
public class AppController {

	//Metodo handler
	@GetMapping({"/", "/index"}) //mapeamos a estas rutas
	public String index(Model model) {
		//mandamos un mensaje a la vista
		model.addAttribute("titulo", "Bienvenido al horario de atencion a clientes");
		return "index";
	}
}
