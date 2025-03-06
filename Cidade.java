package br.com.crudcidades;

// Classe modelo
public class Cidade {
    private int codigoIbge;
    private String nome;
    private double latitude;
    private double longitude;
    private boolean capital;
    private int codigoUf;
    private String siafiId;
    private int ddd;
    private String fusoHorario;
    private String nomeNormalizado;

    // Construtor vazio
    public Cidade() {}
    
    // Construtor completo
    public Cidade(int codigoIbge, String nome, double latitude, double longitude, boolean capital, int codigoUf,
                  String siafiId, int ddd, String fusoHorario, String nomeNormalizado) {
        this.codigoIbge = codigoIbge;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capital = capital;
        this.codigoUf = codigoUf;
        this.siafiId = siafiId;
        this.ddd = ddd;
        this.fusoHorario = fusoHorario;
        this.nomeNormalizado = nomeNormalizado;
    }

    // Getters
    public int getCodigoIbge() {
        return codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isCapital() {
        return capital;
    }

    public int getCodigoUf() {
        return codigoUf;
    }

    public String getSiafiId() {
        return siafiId;
    }

    public int getDdd() {
        return ddd;
    }

    public String getFusoHorario() {
        return fusoHorario;
    }

    public String getNomeNormalizado() {
        return nomeNormalizado;
    }

    // Setters
    public void setCodigoIbge(int codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public void setCodigoUf(int codigoUf) {
        this.codigoUf = codigoUf;
    }

    public void setSiafiId(String siafiId) {
        this.siafiId = siafiId;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public void setFusoHorario(String fusoHorario) {
        this.fusoHorario = fusoHorario;
    }

    public void setNomeNormalizado(String nomeNormalizado) {
        this.nomeNormalizado = nomeNormalizado;
    }
}
