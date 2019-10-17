/*

PUC Minas - Ciencia da Computacao     Nome: Instituicao

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 01/09/2018

*/

class Instituicao
{
    // ----------------------- ATRIBUTOS DA CLASSE
    
    private Integer codigo;
    private String nome;
    private String sigla;
    private Integer codigoMantenedora;
    private String mantenedora;
    private Integer categoria;
    private Integer organizacao;
    private Integer codigoMunicipio;
    private String municipio;
    private String uf;
    private String regiao;
    private Integer tecnico;
    private Integer periodico;
    private Integer livro;
    private Double receita;
    private Double transferencia;
    private Double outraReceita;
    private Double despesaDocente;
    private Double despesaTecnico;
    private Double despesaEncargo;
    private Double despesaCusteio;
    private Double despesaInvestimento;
    private Double despesaPesquisa;
    private Double despesaOutras;
    private String dadosInstituicao;
    private String dadosInstituicaoTabulado;
    
    // ----------------------- CONSTRUTORES
    
    public Instituicao()
    {
        setCodigo(0);
        setNome("");
        setSigla("");
        setCodigoMantenedora(0);
        setMantenedora("");
        setCategoria(0);
        setOrganizacao(0);
        setCodigoMunicipio(0);
        setMunicipio("");
        setUf("");
        setRegiao("");
        setTecnico(0);
        setPeriodico(0);
        setLivro(0);
        setReceita(0.0);
        setTransferencia(0.0);
        setOutraReceita(0.0);
        setDespesaDocente(0.0);
        setDespesaTecnico(0.0);
        setDespesaEncargo(0.0);
        setDespesaCusteio(0.0);
        setDespesaInvestimento(0.0);
        setDespesaPesquisa(0.0);
        setDespesaOutras(0.0);
    }
    
    public Instituicao(String dadosInstituicao)
    {
        lerDados(dadosInstituicao);
    }
    
    // ----------------------- SETTERS AND GETTERS
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCodigoMantenedora() {
        return codigoMantenedora;
    }

    public void setCodigoMantenedora(Integer codigoMantenedora) {
        this.codigoMantenedora = codigoMantenedora;
    }

    public String getMantenedora() {
        return mantenedora;
    }

    public void setMantenedora(String mantenedora) {
        this.mantenedora = mantenedora;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Integer organizacao) {
        this.organizacao = organizacao;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Integer periodico) {
        this.periodico = periodico;
    }

    public Integer getLivro() {
        return livro;
    }

    public void setLivro(Integer livro) {
        this.livro = livro;
    }

    public Double getReceita() {
        return receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public Double getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Double transferencia) {
        this.transferencia = transferencia;
    }

    public Double getOutraReceita() {
        return outraReceita;
    }

    public void setOutraReceita(Double outraReceita) {
        this.outraReceita = outraReceita;
    }

    public Double getDespesaDocente() {
        return despesaDocente;
    }

    public void setDespesaDocente(Double despesaDocente) {
        this.despesaDocente = despesaDocente;
    }

    public Double getDespesaTecnico() {
        return despesaTecnico;
    }

    public void setDespesaTecnico(Double despesaTecnico) {
        this.despesaTecnico = despesaTecnico;
    }

    public Double getDespesaEncargo() {
        return despesaEncargo;
    }

    public void setDespesaEncargo(Double despesaEncargo) {
        this.despesaEncargo = despesaEncargo;
    }

    public Double getDespesaCusteio() {
        return despesaCusteio;
    }

    public void setDespesaCusteio(Double despesaCusteio) {
        this.despesaCusteio = despesaCusteio;
    }

    public Double getDespesaInvestimento() {
        return despesaInvestimento;
    }

    public void setDespesaInvestimento(Double despesaInvestimento) {
        this.despesaInvestimento = despesaInvestimento;
    }

    public Double getDespesaPesquisa() {
        return despesaPesquisa;
    }

    public void setDespesaPesquisa(Double despesaPesquisa) {
        this.despesaPesquisa = despesaPesquisa;
    }

    public Double getDespesaOutras() {
        return despesaOutras;
    }

    public void setDespesaOutras(Double despesaOutras) {
        this.despesaOutras = despesaOutras;
    }

    public String getDadosInstituicao() {
        return dadosInstituicao;
    }

    public void setDadosInstituicao(String dadosInstituicao) {
        this.dadosInstituicao = dadosInstituicao;
    }

    public String getDadosInstituicaoTabulado() {
        return dadosInstituicaoTabulado;
    }

    public void setDadosInstituicaoTabulado(String dadosInstituicaoTabulado) {
        this.dadosInstituicaoTabulado = dadosInstituicaoTabulado;
    }
    
    // ----------------------- METODOS
    
    /**
     * Recebe uma string que contem os dados da instituicao separados por tabulacoes
     * e, de acordo com esses dados, define os campos da classe
     * @param dadosInstituicao string com os dados da instituicao
     */
    
    public void lerDados(String dadosInstituicao)
    {
        if (dadosInstituicao == null)
        {
            //AxellIO.println("[Instituicao]: Parametro dadosInstituicao nulo - funcao lerDados(String)");
        }
        
        else
        {
            MyString dados = new MyString(dadosInstituicao);
            String[] splitedData = dados.split('\t');
            int cursor = 0;
            
            try
            {
                setCodigo( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setNome( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setSigla( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setCodigoMantenedora( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setMantenedora( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setCategoria( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setOrganizacao( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setCodigoMunicipio( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setMunicipio( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setUf( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setRegiao( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setTecnico( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setPeriodico( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setLivro( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setReceita( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setTransferencia( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setOutraReceita( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaDocente( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaTecnico( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaEncargo( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaCusteio( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaInvestimento( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaPesquisa( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaOutras( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
            }

            catch (ArrayIndexOutOfBoundsException exception)
            {
                //AxellIO.println("[Instituicao]: Dados da instituicao incompletos - funcao lerDados(String)");
            }
            
            setDadosInstituicaoTabulado( dados.toString() );
            setDadosInstituicao( concatFields() );
        }
    }
    
    /**
     * "Percorre" os campos da classe concatenando os seus valores espacadamente.
     * Campos que tenham valores nulos nao sao considerados.
     * @return string com a representacao textual dos dados da instituicao
     */
    
    private String concatFields()
    {
        String concatedFields = "";
        Integer intChecker;
        String strChecker;
        Double doubleChecker;
        
        concatedFields += ( (intChecker = getCodigo()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getNome()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getSigla()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getCodigoMantenedora()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getMantenedora()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getCategoria()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getOrganizacao()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getCodigoMunicipio()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getMunicipio()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getUf()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getRegiao()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getTecnico()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getPeriodico()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getLivro()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (doubleChecker = getReceita()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getTransferencia()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getOutraReceita()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaDocente()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaTecnico()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaEncargo()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaCusteio()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaInvestimento()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaPesquisa()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaOutras()) == null ? "" : doubleChecker ) + " ";
        
        return new MyString(concatedFields).removeLastChar().toString();
    }
    
    /**
     * Le, da entrada padrao da classe AxellIO, uma string que representa textualmente
     * a instituicao e ja' define os campos da classe de acordo com os dados
     */
    
    public void ler()
    {
        lerDados( AxellIO.readLine("") );
    }
    
    /**
     * Imprime uma string que representa textualmente a instituicao da classe
     */
    
    public void imprimir()
    {
        AxellIO.println("" + this);
    }
    
    @Override
    public Instituicao clone()
    {
        return new Instituicao( getDadosInstituicaoTabulado() );
    }
    
    @Override
    public String toString()
    {
        return getDadosInstituicao();
    }
}