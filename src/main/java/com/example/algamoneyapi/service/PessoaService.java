package com.example.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public ResponseEntity<Pessoa> pessoaAtualizar(Long codigo, Pessoa pessoa) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		
		if(!pessoaRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo");
		
		return ResponseEntity.ok(pessoaRepository.save(pessoaSalva.get()));
	}

	public ResponseEntity atualizarPessoaAtivo(Long codigo, Boolean ativo) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		
		if(!pessoaRepository.existsById(codigo)) {
			 return ResponseEntity.notFound().build();
		}
		
		pessoaSalva.get().setAtivo(ativo);
		pessoaRepository.save(pessoaSalva.get());
		return ResponseEntity.noContent().build();
	}

}
