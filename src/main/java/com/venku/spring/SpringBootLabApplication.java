package com.venku.spring;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

@SpringBootApplication
public class SpringBootLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLabApplication.class, args);
    }

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        objectMapper.registerModule(module());
        return objectMapper;
    }

    // @Bean
    public Module module() {
        // SimpleModule module = new SimpleModule();

        SimpleModule module = new SimpleModule("JSONModule", new Version(2, 0, 0, null, null, null));

        module.addDeserializer(String.class, new StringCustomDeserializer());

        System.out.println("module");
        return module;
    }

    public static class StringCustomDeserializer extends JsonDeserializer<String> {

        private static final String INT_STRING_ERROR_MSG =
                "Integer to string parsing is disabled for json string values";

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {

            System.out.println("StringCustomDeserializer : ");

            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.VALUE_STRING,
                        INT_STRING_ERROR_MSG);
            }

            return jsonParser.getValueAsString();
        }

    }
}
