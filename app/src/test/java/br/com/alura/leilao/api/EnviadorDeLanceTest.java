package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private LeilaoWebClient client;
    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private Context context;
    @Mock
    private AvisoDialogManager manager;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueUltimoLance() {
        final EnviadorDeLance enviadorDeLance = new EnviadorDeLance(client, listener, context, manager);
        final Leilao computador = new Leilao("Computador");
        final Usuario usuarioAlex = new Usuario("Alex");
        final Lance lanceAlex200 = new Lance(usuarioAlex, 200.0);

        computador.propoe(lanceAlex200);

        final Usuario usuarioFran = new Usuario("Fran");
        final Lance lanceFran100 = new Lance(usuarioFran, 100.0);

        enviadorDeLance.envia(computador, lanceFran100);

        Mockito.verify(manager).mostraAvisoLanceMenorQueUltimoLance(context);
    }
}