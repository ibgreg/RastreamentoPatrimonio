package com.trabltp3.rastreamentopatrimonio;

/**
 * Created by Ítalo on 02/05/2017.
 */

public class InfoUsuario {

    public String quantidade;
    public String descricao;
    public String localizacao;
    public String qualidade;
    public String custo;

    public InfoUsuario(){

    }

    public InfoUsuario(String quantidade, String descricao, String localizacao, String qualidade, String custo) {
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.qualidade = qualidade;
        this.custo = custo;
    }


}
