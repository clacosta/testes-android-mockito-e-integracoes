package br.com.alura.leilao.ui.recyclerview.adapter;

import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoAdapterTest {

    @Test
    public void deve_AtualizaListaDeLeiloes_QuandoReceberListaDeLeiloes() {
        final ListaLeilaoAdapter adapter = Mockito.spy(new ListaLeilaoAdapter(null));
        Mockito.doNothing().when(adapter).atualizaLista();
        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador")
        )));
        final int quantidadeLeiloesDevolvidos = adapter.getItemCount();
        assertThat(quantidadeLeiloesDevolvidos, is(2));
    }

}