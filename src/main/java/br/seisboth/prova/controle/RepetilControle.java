package br.seisboth.prova.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.seisboth.prova.excecao.RepetilNotFoundException;
import br.seisboth.prova.modelo.Repetil;
import br.seisboth.prova.servico.RepetilServico;
import jakarta.validation.Valid;

@Controller
public class RepetilControle {

	@Autowired
	private RepetilServico repetilServico;

	
	@GetMapping("/lista")
	public String listarRepetil(Model model) {
		List<Repetil> repeteis = repetilServico.buscarTodosRepetils();
		model.addAttribute("itens", repeteis);
		return "/lista-repeteis";
	}

	@GetMapping("/")
	public String novoRepetil(Model model) {
		model.addAttribute("item", new Repetil());
		return "/novo-repetil";
	}

	@PostMapping("/gravar")
	public String gravarRepetil(@ModelAttribute("item") @Valid Repetil repetil, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/novo-repetil";
		}
		repetilServico.criarRepetil(repetil);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/lista";
	}

	@GetMapping("/apagar/{id}")
	public String apagarRepetil(@PathVariable("id") long id, RedirectAttributes attributes) {
		try {
			repetilServico.apagarRepetil(id);
		} catch (RepetilNotFoundException e) {
			attributes.addFlashAttribute("mensagem", e.getMessage());
		}
		return "redirect:/lista";
	}
}
