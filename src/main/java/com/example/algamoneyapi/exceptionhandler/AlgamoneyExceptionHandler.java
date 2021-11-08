package com.example.algamoneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private static MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvol = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvol), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvol;

		public Erro(String s1, String s2) {
			this.mensagemUsuario = s1;
			this.mensagemDesenvol = s2;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvol() {
			return mensagemDesenvol;
		}
/*
		@ExceptionHandler({ org.springframework.dao.EmptyResultDataAccessException.class })
		public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
				WebRequest request) {
			String mensagemUsuario = messageSource.getMessage("recurso.nao-encotrado", null,
					LocaleContextHolder.getLocale());
			String mensagemDesenvol = ex.toString();
			
			List<Erro> erros = new ArrayList<>();
			erros.add(new Erro(mensagemUsuario, mensagemUsuario));
			

			return handleExceptionInternal(ex, erros, new HttpHeaders(),
					HttpStatus.NOT_FOUND, request);

		}*/

	}

}
