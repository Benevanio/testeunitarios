package br.ce.bene.build;

import br.ce.bene.entities.Filme;

public class BuilderFilme {
    private Filme filme;

    private BuilderFilme() {
    }

    public static BuilderFilme umFilme() {
        BuilderFilme builder = new BuilderFilme();
        builder.filme = new Filme("Filme 1", 2, 4.0);
        return builder;
    }

    public Filme agora() {
        return filme;
    }
}
