package br.com.alura.challenges.fipe.services;

import java.util.List;

public interface IVehicleStepQueryService {
	<T> List<T> find(String query);
}
