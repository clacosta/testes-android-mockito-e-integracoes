package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;
    private Usuario alex;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuarios() {
        final AtualizadorDeUsuario atualizadorDeUsuario = new AtualizadorDeUsuario(dao, adapter, recyclerView);
        alex = new Usuario("Alexa");
        atualizadorDeUsuario.salva(alex);
        verify(dao).salva(alex);
        verify(adapter).adiciona(alex);
        verify(recyclerView).smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}