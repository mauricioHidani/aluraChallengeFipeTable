package br.com.alura.challenges.fipe.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record History(
		@JsonProperty("statisticalHistory")
		@JsonFormat(shape = JsonFormat.Shape.OBJECT)
		VehicleStatistical statisticalHistory,

		@JsonProperty("brandHistory")
		List<VehicleBrand> brandHistory,

		@JsonProperty("modelHistory")
		List<VehicleModel> modelHistory
) {
}
