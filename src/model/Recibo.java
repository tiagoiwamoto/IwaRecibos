package model;

public class Recibo {
	private String id;
	private String tipoRecibo;
	private double valor;
	private String qFp;
	private String cpf;
	private String valorEscrito;
	private String referente;
	private String obs;
	private String dataEmissao;
	private String local;
	private String dataEscrita;
	private String quemRecebe;
	private String cpfQuemRecebe;

	public Recibo() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoRecibo() {
		return tipoRecibo;
	}

	public void setTipoRecibo(String tipoRecibo) {
		this.tipoRecibo = tipoRecibo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getqFp() {
		return qFp;
	}

	public void setqFp(String qFp) {
		this.qFp = qFp;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getValorEscrito() {
		return valorEscrito;
	}

	public void setValorEscrito(String valorEscrito) {
		this.valorEscrito = valorEscrito;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDataEscrita() {
		return dataEscrita;
	}

	public void setDataEscrita(String dataEscrita) {
		this.dataEscrita = dataEscrita;
	}

	public String getQuemRecebe() {
		return quemRecebe;
	}

	public void setQuemRecebe(String quemRecebe) {
		this.quemRecebe = quemRecebe;
	}

	public String getCpfQuemRecebe() {
		return cpfQuemRecebe;
	}

	public void setCpfQuemRecebe(String cpfQuemRecebe) {
		this.cpfQuemRecebe = cpfQuemRecebe;
	}

}
