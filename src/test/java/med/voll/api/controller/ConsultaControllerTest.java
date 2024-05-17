package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendamentoService;
import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.domain.consulta.DTO.DetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamento> dadosAgendamentoJson;
    @Autowired
    private JacksonTester<DetalhamentoConsulta> detalhamentoConsultaJson;
    @MockBean
    private AgendamentoService agendamentoService;
    @Test
    @DisplayName("deveria devolver codigo http 400 quando informaçãoes estão invalidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("deveria devolver codigo http 200 quando informaçãoes estão validas")
    @WithMockUser
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var dadosDetalhamento = new DetalhamentoConsulta(null,2L,2L,data);

        when(agendamentoService.agendarConsulta(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoJson.write(
                                new DadosAgendamento(2L,2L,Especialidade.CARDILOGIA,data)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);



    }

}