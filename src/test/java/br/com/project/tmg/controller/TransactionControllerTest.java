package br.com.project.tmg.controller;

import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.project.tmg.service.impl.TransactionServiceImpl;


@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionServiceImpl service;
    
    @Test
    void deveRetornarStatusOKQuandoBuscarGerarListarTransacoes() throws Exception {
        var uri = new URI("/99999/transactions/2019/10");
        
        when(service.validParameters(99999, 2019, 10)).thenReturn(true);
     
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deveRetornarStatusBadRequestQuandoInputInvalido() throws Exception {
        var uri = new URI("/99999/transactions/2019/30");
        
        when(service.validParameters(99999, 2019, 30)).thenReturn(false);
        
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
