package com.tilaka.test.exception.handler;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tilaka.test.exception.CustomOauthException;

import java.io.IOException;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("responseCode", value.getHttpErrorCode());
        jsonGenerator.writeStringField("responseMessage", value.getMessage());
        jsonGenerator.writeObjectField("data", null);
        jsonGenerator.writeEndObject();
    }
}
