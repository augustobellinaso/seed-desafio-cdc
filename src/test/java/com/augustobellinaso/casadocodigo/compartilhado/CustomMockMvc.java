package com.augustobellinaso.casadocodigo.compartilhado;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;
import java.util.Map;

@Component
public class CustomMockMvc {

    @Autowired
    private MockMvc mockMvc;

    public ResultActions post(String url, Map<String, Object> params) {
        try {
            String payload = new ObjectMapper()
                    .writeValueAsString(params);

            MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(payload);

            return mockMvc.perform(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultActions get(String url) {
        try {

            MockHttpServletRequestBuilder content = MockMvcRequestBuilders.get(url)
                    .accept(MediaType.APPLICATION_JSON_VALUE);

            return mockMvc.perform(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
