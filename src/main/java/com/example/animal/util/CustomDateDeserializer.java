package com.example.animal.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends JsonDeserializer<String> {
  private static final SimpleDateFormat originalFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
  private static final SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

  @Override
  public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    String date = p.getText();
    try {
      Date originalDate = originalFormat.parse(date);
      return targetFormat.format(originalDate);
    } catch (ParseException e) {
      throw new IOException(e);
    }
  }
}
