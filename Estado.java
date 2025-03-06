package br.com.crudcidades;

public class Estado {
    private int codigoUf;
    private String nome;
    private String uf; // Padronizar como "uf"

    public Estado() {}

    public Estado(int codigoUf, String nome, String uf) {
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.uf = uf;
    }

    public int getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(int codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
