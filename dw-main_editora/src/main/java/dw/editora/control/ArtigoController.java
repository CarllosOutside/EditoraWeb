package dw.editora.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import dw.editora.ListaArtigos;
import dw.editora.model.Artigo;
import dw.editora.repository.ArtigoRepository;

@Controller
@RequestMapping("/api")
public class ArtigoController {

    @Autowired
    ArtigoRepository rep;
    
    /*
     * Atributo de Model -> Uma lista de Artigos
     */
    @ModelAttribute 
	public void listaPecas(Model model) {
		
		List<Artigo> artigosList = new ArrayList<>(); 
		rep.findAll().forEach(i -> artigosList.add(i)); 
		//lista de artigos existentes
		model.addAttribute("ArticleList", 
		artigosList);
		//lista que sera preenchida para delecao
		ListaArtigos listaDel = new ListaArtigos();
		model.addAttribute("listaDelecao",listaDel);
		
		Artigo novo = new Artigo();
		model.addAttribute("novoArtigo", novo);
	}  
    
    
    /*
     * GET /api/artigos :  pagina que lista todos os artigos
     */
    @GetMapping("/artigos")
    public  String getAllArtigos(Model model)
    {
    	//System.out.println("GET chamado");
        return "artigosForm";

    }
    /*
     * GET /api/artigos/novo :  pagina que Cria novo artigo
     */
    @GetMapping("/artigos/novo")
    public  String createNovo(Model model)
    {
    	//System.out.println("GET chamado");
        return "newArtigoForm";

    }
    /*
     * GET /api/artigos/update: pagina que Atualiza artigo
     */
    @GetMapping("/artigos/update/{id}")
    public  String updateArtigo(Model model, @PathVariable("id") long id)
    {
    	//artigo clicado
    	Optional<Artigo> data = rep.findById(id);
    	if (data.isPresent())
        {
            Artigo ar = data.get();
            //coloca os dados do artigo a ser modificado na model
            model.addAttribute("ArtigoUpdate", ar);

        }
    	//chama form de update com o artigo na model
        return "updateArtigoForm";

    }
    /*
     * POST /api/artigos : criar artigo
     */
    @PostMapping("/artigos")
    public String createArtigo(Model model, @ModelAttribute("novoArtigo") Artigo novoArtigo )
    { 
    	//System.out.println("Post chamado");
        rep.save(new Artigo(novoArtigo.getTitulo(), novoArtigo.getResumo(), novoArtigo.isPublicado()));
    	return "redirect:/api/artigos"; //realiza um novo get e lista artigos
    }

    /*
     * PUT /api/artigos/:id : atualizar artigo dado um id
     */
    @PutMapping("/artigos")
    public String updateArtigo(Model model, @ModelAttribute("ArtigoUpdate") Artigo modArtigo)
    {
    	//System.out.println(modArtigo.getId());
    	rep.save(modArtigo); //salva o artigo(sobrescreve um antigo)
    	return "redirect:/api/artigos";
    }

    /*
     * DEL /api/artigos : remover todos os artigos
     */
    @DeleteMapping("/artigos")
    public String deleteAllArtigo(Model model, @ModelAttribute("listaDelecao") ListaArtigos listaDel) //recebe objeto ListaArtigos
    {
    	System.out.println("Delete chamado");
        try { //recebe lista de deleção e deeta por id cada artigo
            for(Long articleid: listaDel.getArtigos()) { //pega o vetor preenchido(na view) com ids dos artigos que serao deletados
            	rep.deleteById(articleid);     	
            }
            //return "artigosForm";
            return "redirect:/api/artigos"; //realiza um get ao inves de retornar um form antigo, listando artigos no banco
            
        } catch (Exception e) {
            return "/api";
        }
        
    }


}
