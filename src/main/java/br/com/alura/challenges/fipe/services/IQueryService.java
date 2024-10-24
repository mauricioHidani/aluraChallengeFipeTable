package br.com.alura.challenges.fipe.services;

import java.util.List;
import java.util.Optional;

public interface IQueryService {
	<T> Optional<T> find(String query, Class<T> clazz);
	<T> List<T> findAll(String query, Class<T> clazz);
}
