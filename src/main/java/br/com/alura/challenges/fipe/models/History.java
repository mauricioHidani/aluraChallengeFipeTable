package br.com.alura.challenges.fipe.models;

import java.util.List;

public record History(
		VehicleStatistical statisticalHistory,
		List<VehicleBrand> brandHistory,
		List<VehicleModel> modelHistory
) {
}
