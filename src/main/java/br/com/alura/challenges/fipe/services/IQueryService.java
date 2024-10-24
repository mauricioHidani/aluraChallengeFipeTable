package br.com.alura.challenges.fipe.services;

import java.util.List;

public interface IQueryService {
	<T> T find(String query, Class<T> clazz);
	<T> List<T> findAll(String query, Class<T> clazz);
}
