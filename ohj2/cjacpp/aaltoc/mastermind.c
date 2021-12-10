#include <stdio.h>

void mastermind(const int *solution, const int *guess, char *result, unsigned int len)
{
    for (int i = 0; i < len; i++)
    {
        if (guess[i] == solution[i])
        {
            result[i] = '+';
            continue;
        }
        unsigned short contains = 0;
        for (int j = 0; j < len; j++)
        {
            if (guess[i] == solution[j]) 
            {
                result[i] = '*';
                contains = 1;
                break;
            }
        }
        if (!contains) result[i] = '-';
    }
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
    if (size > 1) sort(start + 1, size - 1);
}

int main(void)
{
    int solution[] = {2, 6, 6, 3, 5, 3};
    int    guess[] = {4, 5, 6, 1, 8, 9};
    //             = {-, *, +, -, -, -}
    char result[6];
    mastermind(solution, guess, result, 6);
    printf("%s", result);
    return 0;
}