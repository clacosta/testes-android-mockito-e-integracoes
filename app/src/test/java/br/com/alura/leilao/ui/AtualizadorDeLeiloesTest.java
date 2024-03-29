package br.com.alura.leilao.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeiloesTest {

    @Mock
    private ListaLeilaoAdapter adapter;
    @Mock
    private LeilaoWebClient client;
    @Mock
    private AtualizadorDeLeiloes.ErroCarregaLeiloesListener erroCarregaLeiloesListener;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        final AtualizadorDeLeiloes atualizadorDeLeiloes = new AtualizadorDeLeiloes();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                final RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro")
                )));
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));
        atualizadorDeLeiloes.buscaLeiloes(adapter, client, erroCarregaLeiloesListener);
        verify(client).todos(any(RespostaListener.class));
        verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        )));
    }

    @Test
    public void deve_ApresentaMensagemDeFalha_QuandoFalharABuscaDeLeiloes() {
        final AtualizadorDeLeiloes atualizadorDeLeiloes = new AtualizadorDeLeiloes();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                final RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.falha(anyString());
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));
        atualizadorDeLeiloes.buscaLeiloes(adapter, client, erroCarregaLeiloesListener);
        verify(erroCarregaLeiloesListener).erroAoCarregar(anyString());
    }
}