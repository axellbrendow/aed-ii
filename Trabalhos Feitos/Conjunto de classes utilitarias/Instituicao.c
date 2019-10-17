#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*

PUC Minas - Ciencia da Computacao     Nome: Instituicao

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 13/09/2018

*/

// ----------------------- INICIO DA CLASSE INSTITUICAO

typedef struct Instituicao Instituicao;

void lerDados(char *dadosInstituicao, Instituicao *instituicao);
void updateDadosInstituicao(Instituicao *instituicao);

struct Instituicao
{
    int codigo;
    char nome[100];
    char sigla[20];
    int codigoMantenedora;
    char mantenedora[100];
    int categoria;
    int organizacao;
    int codigoMunicipio;
    char municipio[100];
    char uf[10];
    char regiao[20];
    int tecnico;
    int periodico;
    int livro;
    double receita;
    double transferencia;
    double outraReceita;
    double despesaDocente;
    double despesaTecnico;
    double despesaEncargo;
    double despesaCusteio;
    double despesaInvestimento;
    double despesaPesquisa;
    double despesaOutras;
    char dadosInstituicao[350];
    char dadosInstituicaoTabulado[350];
};

// ----------------------- FUNCOES DE MANIPULACAO DA STRUCT INSTITUICAO

// ----------------------- SETTERS AND GETTERS

int getCodigo(Instituicao *instituicao) {
    return instituicao->codigo;
}

void setCodigo(int codigo, Instituicao *instituicao) {
    instituicao->codigo = codigo;
}

char *getNome(Instituicao *instituicao) {
    return instituicao->nome;
}

void setNome(String *nome, Instituicao *instituicao) {
    *copyStringWithoutEndChar( nome, getNome(instituicao) ) = '\0';
}

char *getSigla(Instituicao *instituicao) {
    return instituicao->sigla;
}

void setSigla(String *sigla, Instituicao *instituicao) {
    *copyStringWithoutEndChar( sigla, getSigla(instituicao) ) = '\0';
}

int getCodigoMantenedora(Instituicao *instituicao) {
    return instituicao->codigoMantenedora;
}

void setCodigoMantenedora(int codigoMantenedora, Instituicao *instituicao) {
    instituicao->codigoMantenedora = codigoMantenedora;
}

char *getMantenedora(Instituicao *instituicao) {
    return instituicao->mantenedora;
}

void setMantenedora(String *mantenedora, Instituicao *instituicao) {
    *copyStringWithoutEndChar( mantenedora, getMantenedora(instituicao) ) = '\0';
}

int getCategoria(Instituicao *instituicao) {
    return instituicao->categoria;
}

void setCategoria(int categoria, Instituicao *instituicao) {
    instituicao->categoria = categoria;
}

int getOrganizacao(Instituicao *instituicao) {
    return instituicao->organizacao;
}

void setOrganizacao(int organizacao, Instituicao *instituicao) {
    instituicao->organizacao = organizacao;
}

int getCodigoMunicipio(Instituicao *instituicao) {
    return instituicao->codigoMunicipio;
}

void setCodigoMunicipio(int codigoMunicipio, Instituicao *instituicao) {
    instituicao->codigoMunicipio = codigoMunicipio;
}

char *getMunicipio(Instituicao *instituicao) {
    return instituicao->municipio;
}

void setMunicipio(String *municipio, Instituicao *instituicao) {
    *copyStringWithoutEndChar( municipio, getMunicipio(instituicao) ) = '\0';
}

char *getUf(Instituicao *instituicao) {
    return instituicao->uf;
}

void setUf(String *uf, Instituicao *instituicao) {
    *copyStringWithoutEndChar( uf, getUf(instituicao) ) = '\0';
}

char *getRegiao(Instituicao *instituicao) {
    return instituicao->regiao;
}

void setRegiao(String *regiao, Instituicao *instituicao) {
    *copyStringWithoutEndChar( regiao, getRegiao(instituicao) ) = '\0';
}

int getTecnico(Instituicao *instituicao) {
    return instituicao->tecnico;
}

void setTecnico(int tecnico, Instituicao *instituicao) {
    instituicao->tecnico = tecnico;
}

int getPeriodico(Instituicao *instituicao) {
    return instituicao->periodico;
}

void setPeriodico(int periodico, Instituicao *instituicao) {
    instituicao->periodico = periodico;
}

int getLivro(Instituicao *instituicao) {
    return instituicao->livro;
}

void setLivro(int livro, Instituicao *instituicao) {
    instituicao->livro = livro;
}

double getReceita(Instituicao *instituicao) {
    return instituicao->receita;
}

void setReceita(double receita, Instituicao *instituicao) {
    instituicao->receita = receita;
}

double getTransferencia(Instituicao *instituicao) {
    return instituicao->transferencia;
}

void setTransferencia(double transferencia, Instituicao *instituicao) {
    instituicao->transferencia = transferencia;
}

double getOutraReceita(Instituicao *instituicao) {
    return instituicao->outraReceita;
}

void setOutraReceita(double outraReceita, Instituicao *instituicao) {
    instituicao->outraReceita = outraReceita;
}

double getDespesaDocente(Instituicao *instituicao) {
    return instituicao->despesaDocente;
}

void setDespesaDocente(double despesaDocente, Instituicao *instituicao) {
    instituicao->despesaDocente = despesaDocente;
}

double getDespesaTecnico(Instituicao *instituicao) {
    return instituicao->despesaTecnico;
}

void setDespesaTecnico(double despesaTecnico, Instituicao *instituicao) {
    instituicao->despesaTecnico = despesaTecnico;
}

double getDespesaEncargo(Instituicao *instituicao) {
    return instituicao->despesaEncargo;
}

void setDespesaEncargo(double despesaEncargo, Instituicao *instituicao) {
    instituicao->despesaEncargo = despesaEncargo;
}

double getDespesaCusteio(Instituicao *instituicao) {
    return instituicao->despesaCusteio;
}

void setDespesaCusteio(double despesaCusteio, Instituicao *instituicao) {
    instituicao->despesaCusteio = despesaCusteio;
}

double getDespesaInvestimento(Instituicao *instituicao) {
    return instituicao->despesaInvestimento;
}

void setDespesaInvestimento(double despesaInvestimento, Instituicao *instituicao) {
    instituicao->despesaInvestimento = despesaInvestimento;
}

double getDespesaPesquisa(Instituicao *instituicao) {
    return instituicao->despesaPesquisa;
}

void setDespesaPesquisa(double despesaPesquisa, Instituicao *instituicao) {
    instituicao->despesaPesquisa = despesaPesquisa;
}

double getDespesaOutras(Instituicao *instituicao) {
    return instituicao->despesaOutras;
}

void setDespesaOutras(double despesaOutras, Instituicao *instituicao) {
    instituicao->despesaOutras = despesaOutras;
}

char *getDadosInstituicao(Instituicao *instituicao) {
    return instituicao->dadosInstituicao;
}

void setDadosInstituicao(char *dadosInstituicao, Instituicao *instituicao) {
    strcpy(instituicao->dadosInstituicao, dadosInstituicao);
}

char *getDadosInstituicaoTabulado(Instituicao *instituicao) {
    return instituicao->dadosInstituicaoTabulado;
}

void setDadosInstituicaoTabulado(char *dadosInstituicaoTabulado, Instituicao *instituicao) {
    strcpy(instituicao->dadosInstituicaoTabulado, dadosInstituicaoTabulado);
}

// ----------------------- CONSTRUTORES DA CLASSE

void iniciarInstituicao(Instituicao *instituicao)
{
    instituicao->codigo = -1;
    instituicao->nome[0] = '\0';
    instituicao->sigla[0] = '\0';
    instituicao->codigoMantenedora = -1;
    instituicao->mantenedora[0] = '\0';
    instituicao->categoria = -1;
    instituicao->organizacao = -1;
    instituicao->codigoMunicipio = -1;
    instituicao->municipio[0] = '\0';
    instituicao->uf[0] = '\0';
    instituicao->regiao[0] = '\0';
    instituicao->tecnico = -1;
    instituicao->periodico = -1;
    instituicao->livro = -1;
    instituicao->receita = -1;
    instituicao->transferencia = -1;
    instituicao->outraReceita = -1;
    instituicao->despesaDocente = -1;
    instituicao->despesaTecnico = -1;
    instituicao->despesaEncargo = -1;
    instituicao->despesaCusteio = -1;
    instituicao->despesaInvestimento = -1;
    instituicao->despesaPesquisa = -1;
    instituicao->despesaOutras = -1;
    instituicao->dadosInstituicao[0] = '\0';
    instituicao->dadosInstituicaoTabulado[0] = '\0';
}

Instituicao *criarInstituicaoVazia()
{
    Instituicao *novaInstituicao = (Instituicao *) malloc( sizeof(Instituicao) * 1 );
    
    iniciarInstituicao(novaInstituicao);
    
    return novaInstituicao;
}

Instituicao *criarInstituicao(char *dadosInstituicao)
{
    Instituicao *novaInstituicao = criarInstituicaoVazia();
    
    lerDados(dadosInstituicao, novaInstituicao);
    
    return novaInstituicao;
}

// ----------------------- OUTROS FUNCOES DA CLASSE

/**
 * Recebe uma string que contem os dados da instituicao separados por tabulacoes
 * e, de acordo com esses dados, define os campos da classe
 * 
 * @param dadosInstituicao string com os dados da instituicao
 */

void lerDados(char *dadosInstituicao, Instituicao *instituicao)
{
    if (dadosInstituicao == NULL)
    {
        //printf("[Instituicao]: Parametro dadosInstituicao nulo - funcao lerDados(char *)\n");
    }

    else
    {
        StringArray *splitResult = split(dadosInstituicao, "\t");
        String *splitedData = getFirstString(splitResult);
        String *emptyString = createString("", 0, NULL);
        int cursor = 0;
        
        setCodigo( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setNome( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setSigla( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setCodigoMantenedora( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setMantenedora( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setCategoria( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setOrganizacao( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setCodigoMunicipio( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setMunicipio( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setUf( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setRegiao( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setTecnico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setPeriodico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setLivro( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setReceita( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setTransferencia( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setOutraReceita( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaDocente( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaTecnico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaEncargo( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaCusteio( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaInvestimento( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaPesquisa( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaOutras( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );

        setDadosInstituicaoTabulado( dadosInstituicao, instituicao );
        updateDadosInstituicao(instituicao);
        
        freeStrings(emptyString);
        freeStringArray(splitResult);
    }
}

/**
 * "Percorre" os campos da classe concatenando os seus valores espacadamente.
 * Campos que tenham valores invalidos nao sao considerados.
 */

void updateDadosInstituicao(Instituicao *instituicao)
{
    int intChecker;
    char *strChecker;
    double doubleChecker;
    char *lastPos = instituicao->dadosInstituicao;
    
    if ( ( intChecker = getCodigo(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getNome(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getSigla(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getCodigoMantenedora(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( strcmp( strChecker = getMantenedora(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getCategoria(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getOrganizacao(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getCodigoMunicipio(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( strcmp( strChecker = getMunicipio(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getUf(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getRegiao(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getTecnico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getPeriodico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getLivro(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getReceita(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getTransferencia(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getOutraReceita(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaDocente(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaTecnico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaEncargo(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaCusteio(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaInvestimento(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaPesquisa(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaOutras(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    
    *(--lastPos) = '\0';
}

/**
 * Le, da entrada padrao da classe AxellIO, uma string que representa textualmente
 * a instituicao e ja' define os campos da classe de acordo com os dados
 */

void ler(Instituicao *instituicao)
{
    char *line = readLine(400);
    
    lerDados( line, instituicao );
    
    free(line);
}

/**
 * Imprime uma string que representa textualmente a instituicao da classe
 */

void imprimir(Instituicao *instituicao)
{
    printf("%s", getDadosInstituicao(instituicao));
}

Instituicao *clone(Instituicao *instituicao)
{
    Instituicao *clon = (Instituicao *) malloc( sizeof(Instituicao) * 1 );
    
    *clon = *instituicao;
    
    return clon;
}

char *toString(Instituicao *instituicao)
{
    return getDadosInstituicao(instituicao);
}

// ----------------------- FIM DA CLASSE INSTITUICAO