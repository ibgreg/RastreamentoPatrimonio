package com.trabltp3.rastreamentopatrimonio;

/**
 * Created by Ítalo on 02/05/2017.
 */

public class InfoUsuario {

    public String id;
    public String quantidade;
    public String descricao;
    public String localizacao;
    public String qualidade;
    public String custo;

    public InfoUsuario(){

    }

    public InfoUsuario(String id, String quantidade, String descricao, String localizacao, String qualidade, String custo) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.qualidade = qualidade;
        this.custo = custo;
    }

    public String getId() {
        return id;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getQualidade() {
        return qualidade;
    }

    public String getCusto() {
        return custo;
    }
}
