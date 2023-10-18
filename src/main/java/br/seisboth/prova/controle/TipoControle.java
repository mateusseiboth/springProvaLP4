package br.seisboth.prova.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.seisboth.prova.excecao.RepetilNotFoundException;
import br.seisboth.prova.modelo.Tipo;
import br.seisboth.prova.modelo.Repetil;
import br.seisboth.prova.servico.TipoServico;
import br.seisboth.prova.servico.RepetilServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipo")
public class TipoControle {

	@Autowired
	private RepetilServico repetilServico;

	@Autowired
	private TipoServico tipoServico;

	
	@GetMapping("/buscar-tipo/{id}")
	public String novoTipo(@PathVariable("id") long id, Model model) {
		String pagina = "";
		try {
			Repetil repetil = repetilServico.buscarRepetilPorId(id);
			if (repetil.getTipo() == null) {
				Tipo tipo = new Tipo();
				tipo.setRepetil(repetil);
				model.addAttribute("item", tipo);
				pagina = "/novo-tipo";
			} else {
				return "redirect:/lista";
			}
		} catch (RepetilNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/lista";
		}
		return pagina;
	}

	@PostMapping("/gravar-tipo/{idRepetil}")
	public String gravarTipo(@PathVariable("idRepetil") long idRepetil,
			@ModelAttribute("item") @Valid Tipo tipo, BindingResult result, RedirectAttributes attributes) {
		try {
			Repetil repetil = repetilServico.buscarRepetilPorId(idRepetil);
			tipo.setRepetil(repetil);
		} catch (RepetilNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {
			return "/novo-tipo";
		}
		tipoServico.criarTipo(tipo);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/lista";
	}
	
}
