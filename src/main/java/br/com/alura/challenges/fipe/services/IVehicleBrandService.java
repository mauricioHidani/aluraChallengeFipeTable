package br.com.alura.challenges.fipe.services;

import java.util.List;

public interface IVehicleBrandService {
	<T> List<T> find(String query);
}
