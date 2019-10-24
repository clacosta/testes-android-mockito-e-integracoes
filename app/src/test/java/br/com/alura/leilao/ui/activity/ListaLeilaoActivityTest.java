package br.com.alura.leilao.ui.activity;

import org.junit.Test;

import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoActivityTest {

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        final ListaLeilaoActivity activity = new ListaLeilaoActivity();
        activity.configuraAdapter();
        activity.buscaLeiloes();
        Thread.sleep(2000);
        final ListaLeilaoAdapter adapter = activity.getAdapter();
        final int quantidadeLeiloesDevolvida = adapter.getItemCount();
        assertThat(quantidadeLeiloesDevolvida, is(2));
    }

}