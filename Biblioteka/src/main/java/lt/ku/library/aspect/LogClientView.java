package lt.ku.library.aspect;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lt.ku.library.entities.Client;


@Aspect
@Component
public class LogClientView {

	@AfterReturning(pointcut = "execution(* lt.ku.library.services.ClientService.getClients(..))",returning = "result")
	public void afterGetClients(List<Client> result) {
		Authentication a=SecurityContextHolder.getContext().getAuthentication();
		if(a.getName()=="anonymousUser") {
			for(Client c:result) {
			c.setEmail("***");
			c.setPhone("***");
			}
		}
	}
	
}
