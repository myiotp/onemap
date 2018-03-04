package com.onemap;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date> {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		try {
			System.out.println("jsonParser.getText():" + jsonParser.getText());
			//Date date = format.parse(jsonParser.getText());
			Date date = Date.valueOf(jsonParser.getText());
			System.out.println(date);
			return date;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
