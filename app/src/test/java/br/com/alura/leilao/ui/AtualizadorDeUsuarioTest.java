package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuarios() {
        final AtualizadorDeUsuario atualizadorDeUsuario = new AtualizadorDeUsuario(dao, adapter, recyclerView);
        Usuario usuarioSemId = new Usuario("Alex");
        Usuario usuarioComId = new Usuario(1, "Alex");

        when(dao.salva(usuarioSemId)).thenReturn(usuarioComId);
        when(adapter.getItemCount()).thenReturn(1);

        atualizadorDeUsuario.salva(usuarioSemId);

        verify(dao).salva(usuarioSemId);
        verify(adapter).adiciona(usuarioComId);
        verify(recyclerView).smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}