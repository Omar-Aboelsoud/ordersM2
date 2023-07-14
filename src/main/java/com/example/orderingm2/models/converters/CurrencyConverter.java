package com.example.orderingm2.models.converters;

import com.example.orderingm2.models.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Currency currency) {
        String databaseJsonValue = null;
        try {
            databaseJsonValue = objectMapper.writeValueAsString(currency);
        } catch (final JsonProcessingException e) {
            logger.error()
                    .exception("JSON writing error", e)
                    .log();
        }

        return databaseJsonValue;
    }

    @Override
    public Currency convertToEntityAttribute(String databaseJsonValue) {
        Currency jsonObj = null;
        try {
            jsonObj = objectMapper.readValue(databaseJsonValue, Currency.class);
        } catch (final IOException e) {
            logger.error()
                    .exception("JSON writing error", e)
                    .log();
        }

        return jsonObj;
    }
}
