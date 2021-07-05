package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.service.CredenzialiService;

@Controller
public class AuthController 
{
	@Autowired
    CredenzialiService credentialsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login.html";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET) 
    public String logout(Model model) {
        return "home.html";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali credentials = credentialsService.getCredenziali(userDetails.getUsername());
        if (credentials.getRole().equals(Credenziali.ADMIN_ROLE)) {
            return "/admin/adminHome.html";
        }
        return "home.html";
    }
}