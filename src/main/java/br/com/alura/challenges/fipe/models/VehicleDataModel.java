package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.models.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleDataModel(
		@JsonProperty("tablePrice")
		@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
		BigDecimal tablePrice,

		@JsonProperty("type")
		@JsonFormat(shape = JsonFormat.Shape.STRING)
		VehicleType type,

		@JsonProperty("modelName")
		String modelName,

		@JsonProperty("modelYear")
		@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
		Integer modelYear,

		@JsonProperty("updateInfoRef")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM yyyy")
		@JsonSerialize(using = LocalDateSerializer.class)
		@JsonDeserialize(using = LocalDateDeserializer.class)
		LocalDate updateInfoRef,

		@JsonProperty("fuelType")
		String fuelType,

		@JsonProperty("fipeCode")
		String fipeCode
) {
}
