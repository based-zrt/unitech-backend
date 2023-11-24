package tech.unideb.backend.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@JsonComponent
public class BackendExceptionSerializer extends JsonSerializer<BackendApiException> {

    @Override
    public void serialize(BackendApiException exception, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeStringField("message", exception.getReason());
        gen.writeArrayFieldStart("details");
        for (var arg : exception.getArgs()) {
            serializers.defaultSerializeValue(arg, gen);
        }
        gen.writeEndArray();
        gen.writeObjectFieldStart("error");
        gen.writeNumberField("statusCode", exception.getStatusCode().value());
        if (exception.getStatusCode() instanceof HttpStatus s)
            gen.writeStringField("statusPhrase", s.getReasonPhrase());
        gen.writeEndObject();
    }
}
