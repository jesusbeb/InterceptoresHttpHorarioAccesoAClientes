package com.bolsadeideas.springboot.horariointerceptor.app.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Registramos como componente Spring para inyectar posteriormente. Le damos nombre
@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {
	
	//Atributos para asignar los valores de application.properties
	//usamos @Value para poder inyectar el nombre del valor que esta en application.properties
	@Value("${config.horario.apertura}")
	private Integer apertura;
	
	@Value("${config.horario.cierre}")
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//Obtenemos la hora actual, obtenemos una instancia de calendario
		Calendar calendar = Calendar.getInstance();
		//obtenemos la hora
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		//verificamos que la hora este dentro de la apertura y el cierre
		if(hora >= apertura && hora < cierre) {
			//StringBuilder es un objeto de java para crear string mutables, es decir que podamos ir cambiando la instancia
			//e ir concatenando sin crear nuevos objetos. Asi mostraremos un mensaje			
			StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atencion a clientes");
			mensaje.append(", atendemos desde las ");
			mensaje.append(apertura);
			mensaje.append(" hasta las: ");
			mensaje.append(cierre);
			mensaje.append(" hrs.");
			mensaje.append(" Gracias por su visita.");
			//Pasamos el mensaje a los atributos del request. "mensaje" puede ser cualquier nombre
			//mensaje.toString convierte el StringBuilder a un String
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		//si es falso redirige a otra página "/cerrado"
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//obtenemos el mensaje
		String mensaje = (String) request.getAttribute("mensaje");
		
		//Validamos que modelAndView sea distinto de null y handler sea instancia de HandlerMethod
		//con esto evitamos el error NullPointerException
		if(modelAndView != null && handler instanceof HandlerMethod) {
			//pasamos el mensaje a la vista, al modelAndView
			//horario es el nombre con el que lo pasamos y lo capturaremos en la vista
			modelAndView.addObject("horario", mensaje);
		}
		
	}

}
 