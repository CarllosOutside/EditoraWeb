package dw.editora.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Controller for the home page.
 */
@SessionAttributes("profile")
@Controller
public class HomeController {

    @GetMapping("/") //redireciona para a pagina de autenticacao
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
    	if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }
}