#include <stdio.h>
#include <stddef.h>
#define END '#'

char *es_token(char *s, char c)
{
    while (*s != c)
        if (*s++ == END) return NULL;
    *s = END;
    return s + 1;
}

int es_copy(char *dst, const char *src)
{
    int i = 0;
    while ((*dst++ = *src++) != END)
        i++;
    return i;
}

unsigned int es_length(const char *s)
{
    unsigned int i = 0;
    while(*s++ != END)
        i++;
    return i;
}

void es_print(const char *s)
{
    while(*s != END)
        printf("%c", *s++);
}

int main(void)
{
	char str[] = "yksi,kaksi,kymmenen#nelja,viisi";
    es_print(str);
    printf("\n");
    printf("length: %d\n", es_length(str));
    printf("\n");
    char buf[80] = { 0 };
    int ret = es_copy(buf, str);
    es_print(buf);
    printf("\n");
    printf("after copy: '%s' (ret: %d)\n", buf, ret);
    char *strptr = str;
    do {
        strptr = es_token(strptr, ',');
    } while (strptr);
    printf("after token: '%s'\n", str);
    return 0;
}


