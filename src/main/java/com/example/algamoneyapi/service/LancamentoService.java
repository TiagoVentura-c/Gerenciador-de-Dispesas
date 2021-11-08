package com.example.algamoneyapi.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.pessoaInativaOuInexistenteException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento save(@Valid Lancamento lacamento) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(lacamento.getPessoa().getCodigo());
		
		if(pessoa.isEmpty() || !pessoa.get().getAtivo()) {
			throw new pessoaInativaOuInexistenteException();
		}
		
		return lancamentoRepository.save(lacamento);
	}

}
