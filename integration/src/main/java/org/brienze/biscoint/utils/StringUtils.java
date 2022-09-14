package org.brienze.biscoint.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.brienze.biscoint.adapter.StringUtilsAdapter;
import org.brienze.biscoint.exception.ValidationException;
import org.brienze.biscoint.validator.Validators;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class StringUtils implements StringUtilsAdapter {

    private static final String OBJECT_MAPPER_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String OBJECT_MAPPER_TIME_ZONE = "America/Sao_Paulo";

    private final DateFormat df = new SimpleDateFormat(OBJECT_MAPPER_FORMAT_DATE);
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setDateFormat(df)
            .setTimeZone(TimeZone.getTimeZone(OBJECT_MAPPER_TIME_ZONE));

    @Override
    public String parseToString(Object object) {
        Validators.validateNotNull(object, "Could not parse object to string. Object is null.");

        return mapper.valueToTree(object).toString();
    }

    @Override
    public JsonNode parseToJson(String body) {
        Validators.validateNotEmpty(body, "Could not parse string to json. String is empty.");

        try {
            return mapper.readValue(body, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new ValidationException("Could not parse string to json. ".concat(e.getMessage()));
        }
    }

}
