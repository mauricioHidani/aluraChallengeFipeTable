package br.com.alura.challenges.fipe.services;

public interface IVehicleStatisticalService {
	<T> T find(String type, String brandCode, String modelCode);
}
