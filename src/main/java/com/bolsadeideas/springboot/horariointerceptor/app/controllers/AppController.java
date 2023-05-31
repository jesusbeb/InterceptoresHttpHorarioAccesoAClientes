package com.bolsadeideas.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Anotamos para Spring
@Controller
public class AppController {
	
	//Atributos para asignar los valores de application.properties
	//usamos @Value para poder inyectar el nombre del valor que esta en application.properties
	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;

	
	//Metodo handler
	@GetMapping({"/", "/index"}) //mapeamos a estas rutas
	public String index(Model model) {
		//mandamos un mensaje a la vista "/index"
		model.addAttribute("titulo", "Bienvenido al horario de atencion a clientes");
		return "index";
	}
	
	
	@GetMapping("/cerrado")
	public String cerrado(Model model ) {
		
		//Creamos mensaje con StrinBuilder
		StringBuilder mensaje = new StringBuilder("Cerrado, por favor visitenos desde las ");
		mensaje.append(apertura);
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append(" hrs. Gracias.");
		
		//Pasamos a la vista "/cerrado"
		model.addAttribute("titulo", "Fuera del horario de atencion");
		model.addAttribute("mensaje", mensaje);
		//retornamos el nombre de la vista
		return "cerrado";
	}
}
