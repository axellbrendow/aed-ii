/*

PUC Minas - Ciencia da Computacao     Nome: OBJECT_H

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 10/10/2018

*/

#ifndef OBJECT_H
#define OBJECT_H

void *cnew(const void *_type, ...);
void cdelete(void *_objectPtr);
size_t sizeOf(void *_objectPtr);
const void *classOf(void *_objectPtr);
const void *superOf(const void *_objectPtr);
char *Object_toString(void *_objectPtr);

/*typedef struct Class Class;
typedef struct Object Object;*/

struct Object
{
    const struct Class *myClass; // descricao do objeto
};

struct Class
{
    const struct Object _; // descricao da classe
    const char *name; // nome da classe
    const struct Class *superClass; // classe pai
    size_t size; // gasto em bytes da classe na memoria
    void *(*construct) (void *_ptrToMemBlock, va_list *args);
    void *(*destruct) (void *_objectPtr);
    char *(*toString) (void *_objectPtr);
};

#endif /* OBJECT_H */

