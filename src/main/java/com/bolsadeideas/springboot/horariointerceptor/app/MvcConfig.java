package com.bolsadeideas.springboot.horariointerceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Anotamos para Spring
@Configuration
public class MvcConfig implements WebMvcConfigurer{ //heredamos

	//inyectamos la interface genérica que creamos "horarioInterceptor"
	@Autowired
	@Qualifier("horarioInterceptor")
	private HandlerInterceptor horario;
	
	//sobreescribimos
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//este interceptor se aplica a todos los metodos handler. Con excludePathPatterns, excluimos a que metodo handler no aplicara
		registry.addInterceptor(horario).excludePathPatterns("/cerrado");
	}
	
	 

}
