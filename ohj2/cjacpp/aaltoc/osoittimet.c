#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>

void number_swap(int *a, int *b)
{
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

int array_sum(int *array, int count)
{
    int sum = 0;
    for (int i = 0; i < count; i++)
        sum += *(array + i);
    return sum;
}

int array_reader(int *vals, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        int a;
        if (!scanf("%d", &a)) break;
        vals[i] = a;
    }
    return i;
}

void sort(int *start, int size)
{
    int min = *start;
    int i_min = 0;
    for (int i = 1; i < size; i++)
    {
        if (*(start + i) < min) 
        {
            min = *(start + i);
            i_min = i;
        }
    }
    int tmp = *start;
    *start = min;
    *(start + i_min) = tmp;
    if (size > 2) sort(start + 1, size - 1);
}

int count_alpha(const char *str)
{
    size_t count = 0;
    while (*str)
        if (isalpha(*str++)) count++;
    return count;
}

int count_substr(const char *str, const char *sub)
{
    int i = 0;
    while(str = strstr(str, sub)) {
        const size_t sublen = strlen(sub);
        i++;
        str += sublen;
    }
    return i;
}

char *replace_all(char *dest, const char *src, const char *s1, const char *s2)
{
    size_t copy_index = 0;
    size_t copylen = 0;
    size_t srclen = strlen(src);
    size_t s1_len = strlen(s1);
    const char *copystr;

    dest[0] = '\0';

    while (copystr = strstr(src + copy_index, s1))
    {
        copylen = srclen - copy_index - strlen(copystr);
        strncat(dest, src + copy_index, copylen);
        copy_index += s1_len + copylen;
        strcat(dest, s2);
    }
    strcat(dest, src + copy_index);
    return dest;
}

void korsoroi(char *dest, const char *src)
{
    char src_xz[200];
    replace_all(src_xz, replace_all(dest, src, "ks", "x"), "ts", "z");
    
    size_t copy_index = 0;
    size_t copylen = 0;
    size_t srclen = strlen(src_xz);
    const char *copystr;
    int spaces = 0;

    dest[0] = '\0';

    while (copystr = strstr(src_xz + copy_index, " "))
    {
        spaces++;
        copylen = srclen - copy_index - strlen(copystr) + 1;
        strncat(dest, src_xz + copy_index, copylen);
        copy_index += copylen;
        if (spaces % 3 == 0) strcat(dest, "niinku ");
        if (spaces % 4 == 0) strcat(dest, "totanoin ");
    }
    strcat(dest, src_xz + copy_index);
}

void str_to_strarray(char* string, char** arr)
{
    size_t copy_index = 0;
    size_t copylen = 0;
    size_t stringlen = strlen(string);
    char *copystr;
    while (copystr = strstr(string + copy_index, " "))
    {
        for (int i = 0; i < 3; i++)
            (*arr)[i] = '\0';
        copylen = stringlen - copy_index - strlen(copystr);
        strncpy(*arr++, string + copy_index, copylen);
        copy_index += 1 + copylen;
    }
    strcpy(*arr++, string + copy_index);
    *arr = NULL;
}

void print_strarray(char *array[])
{
    while(*array)
        printf("%s\n", *array++);
}

/*
void print_strarray(char *array[])
{
    for (int i = 0; array[i]; i++)
        printf("%s\n", array[i]); // printf("%s\n", *(array + i));
}
*/


int main(void)
{
    char string[] = "Kissa ja koira, kettu ja kana";
    char **arr = malloc(sizeof(char*)*7); //6 sanaa, plus tyhjä/NULL

    //Osoittimet talteen, tarvitsemme niitä vapauttamiseen myöhemmin.
    char **save = arr, **begin = arr;

    //Varataan tilaa merkkijonoille ja asetetaan viimeinen paikka NULLiksi (tiedetään että tulee olemaan tyhjä)
    for(int i=0; i<6; i++)
        arr[i] = malloc(10);
    arr[6]=NULL;

    //Haetaan ja tulostetaan
    str_to_strarray(string, arr);
    print_strarray(arr);

    //Vapautetaan varattu muisti; ekaksi alkiopaikat jotka osoittavat johonkin merkkijonoon,
    //ja viimeisenä koko osoittimille varattu tila.
    while(*save){
        free(*save);
        save++;
    }
    free(begin);

    return 0;
}