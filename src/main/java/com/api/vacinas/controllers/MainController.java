package com.api.vacinas.controllers;

import java.util.Date;
import java.util.Optional;

import com.api.vacinas.modelos.Usuario;
import com.api.vacinas.modelos.Vacinas;
import com.api.vacinas.repositories.UsuarioRepository;
import com.api.vacinas.repositories.VacinasRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Indica que a classe é um controller
@CrossOrigin
@RequestMapping(path = "/cadastros")
public class MainController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private VacinasRepository vacinasRepository;

	@PostMapping(path = "/usuario") // Map ONLY POST Requests
	public ResponseEntity<String> addUsuario(@RequestParam String nome, @RequestParam String email,
			@RequestParam String cpf,
			@RequestParam("dataNascimento") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataNascimento) {
		
		// @RequestParam significa que o metodo recebe parâmetros para
		//ter uma requisição bem sucedida
		try {
			Usuario user = new Usuario();
			user.setNome(nome);
			user.setEmail(email);
			user.setCPF(cpf);
			user.setDataNascimento(dataNascimento);
			usuarioRepository.save(user);
			return new ResponseEntity<>("Cadastro de usuário salvo", HttpStatus.OK);
			//retorna o status code 200 caso 
			//o cadastro seja bem sucedido
		
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Falha no cadastro de usuário", HttpStatus.BAD_REQUEST);
			//retorna o status code 400 caso 
			//o cadastro falhe por algum motivo
		}

	}


	@PostMapping(path = "/vacinas") // Map ONLY POST Requests
	public ResponseEntity<String> addVacinas(@RequestParam String nomeVacinas, @RequestParam String emailVacinado,
			@RequestParam("dataAplicacao") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataAplicacao) {
				
				// @RequestParam significa que o metodo recebe parâmetros para
				//ter uma requisição bem sucedida
				try {
					Vacinas vac = new Vacinas();
					Optional<Usuario> obj;
					
					
					obj = usuarioRepository.findByEmail(emailVacinado); /*	metodo na classe de repository para verificar 
																		*   se o email existe no banco de usuários e depois 
																		*   ir para o banco de vacinas
																		*/
					if(obj.get()!=null){												 

					vac.setNomeVacina(nomeVacinas);
					vac.setEmailVacinado(emailVacinado);
					vac.setDataAplicacao(dataAplicacao);
					vacinasRepository.save(vac);
					return new ResponseEntity<>("Cadastro de vacina salvo", HttpStatus.OK); //retorna o status code 200 caso 
					}else{																	//o cadastro seja bem sucedido
						return new ResponseEntity<>("Email de usuário não encontrado", HttpStatus.BAD_REQUEST); 
						//retorna o status code 400 caso o email de usuario nao seja encontrado
					}
				} catch (Exception e) {
					System.out.println(e);
					return new ResponseEntity<>("Falha no cadastro de vacinas", HttpStatus.BAD_REQUEST);
					//retorna o status code 400 caso alguma outra coisa no cadastro tenha dado errado
				}
			}
		

	@GetMapping(path = "/usuario")
	public @ResponseBody Iterable<Usuario> getAllUsuarios() {
		//retorna os usuarios
		return usuarioRepository.findAll();

	}



	@GetMapping(path = "/vacinas")
	public @ResponseBody Iterable<Vacinas> getAllVacinas() {
		//retorna as vacinas
		return vacinasRepository.findAll();

	}


}
