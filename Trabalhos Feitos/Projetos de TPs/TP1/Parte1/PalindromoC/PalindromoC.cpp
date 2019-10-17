/*

PUC Minas - Ciência da Computação     Nome: PalindromoC

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 26/08/2018

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void copyString(const char *string, char **destination)
{
	free(*destination); // caso haja algo no endereco de destino, libera-o
	
	// aloca espaco para guardar o tamanho da string, incluindo o '\0'
	*destination = (char *) malloc( (strlen(string) + 1) * sizeof(char) );
	
	if (*destination == NULL) // checa se a alocacao falhou
	{
		fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao copyString(const char *, char **)");
	}
	
	else
	{
		strcpy(*destination, string); // copia a string para o destino
	}
}

void replace(char find, char replace, const char *string)
{
	char *searchedChar = strchr(string, find); // obtem um ponteiro para a primeira ocorrencia de "find"
	
	while (searchedChar != NULL) // checa se realmente existe o caractere
	{
		*searchedChar = replace; // se existir, substitui-o pelo "replace"
		
		searchedChar = strchr(string, find); // obtem um ponteiro para a primeira ocorrencia de "find"
	}
}

char *readLine(int maximumOfCharacters)
{
	char *inputBuffer = (char *) malloc( maximumOfCharacters * sizeof(char) ); // cria um buffer para a entrada com o tamanho maximo especificado
	char *line = NULL; // guardara' a cadeia que for lida
	
	if (inputBuffer == NULL)
	{
		fprintf(stderr, "Nao foi possivel alocar espaco para o buffer. Tente diminuir a quantidade maxima de caracteres. - funcao *readLine(int)");
	}
	
	else
	{
		fgets(inputBuffer, maximumOfCharacters, stdin); // le, da entrada padrao, uma cadeia com o tamanho maximo especificado
		
		replace('\n', '\0', inputBuffer); // como a funcao fgets() deixa o '\n' na string, vou substitui-lo por um '\0'
		
		copyString(inputBuffer, &line); // copia, dinamicamente, a string de inputBuffer para line
		
		free(inputBuffer); // libera o espaco do buffer
	}
	
	return line; // retorna a cadeia
}

int isPalindrome(char* string)
{
	int isPalindrome = 1;
	int length = strlen(string), midLength = length / 2;
	int i, j;

	if (length == 0) isPalindrome = 0; // vou considerar que a string vazia nao e' um palindromo

	else if (length == 2) isPalindrome = string[0] == string[1];

	else
	{
		// as variaveis i e j do for serao "ponteiros" que comecaram no inicio e do fim da string
		// respectivamente e irao trabalhar juntas para saber se a string e' ou nao um palindromo
		for (i = 0, j = length - 1; i < midLength && j >= midLength && isPalindrome; i++, j--)
		{
			//while (string.charAt(i) == ' ') i++;
			//while (string.charAt(j) == ' ') j--;
			
			isPalindrome = string[i] == string[j];
		}
	}

	return isPalindrome;
}

int main()
{
	FILE *systemInBackup = stdin;
	FILE *systemOutBackup = stdout;
	
	stdin = fopen("pub.in", "r");
	stdout = fopen("myOut.out", "w");
	
	char *input = readLine(400); // le uma linha com no maximo 400 caracteres
	
	while(input != NULL && strcmp(input, "FIM") != 0)
	{
		if (isPalindrome(input))
		{
			printf("SIM\n");
		}

		else
		{
			printf("NAO\n");
		}
		
		free(input);
		
		input = readLine(400);
	}
	
	free(input);
	
	stdin = systemInBackup;
	stdout = systemOutBackup;
	
	return EXIT_SUCCESS;
}
