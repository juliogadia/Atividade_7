package br.edu.ifgoias.academico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.CursoRepository;

public class AlunoService {

	@Autowired
	private CursoRepository repository;

	public List<Curso> findAll() {
		return repository.findAll();
	}

	public Curso findById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}

	public Aluno insert(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public void delete(Integer id) {
		repository.findById(id).map(aluno -> {
			repository.delete(aluno);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}

	public void update(Integer id, Aluno obj) {
		repository.findById(id).map(aluno -> {
			aluno.setNomecurso(obj.getNome());
			return repository.save(aluno);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}
}
