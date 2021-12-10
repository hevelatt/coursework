#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <string.h>


void three_lines(void)
{
    printf("January\nFebruary\nMarch\n");
}

void fix_types(void)
{
    double a1 = 2.1;
    double a2 = 3.2;
    double result_a = a1 + a2;

    int b1 = 100;
    int b2 = 80000;
    int result_b = b1 * b2;

    int c1 = 100000;
    double c2 = 1.5;
    int result_c = c1 / c2;

    // Do not change the following line
    printf("%.1f  %d  %d\n", result_a, result_b, result_c);
}

double vectorlength(double x, double y, double z)
{
    return sqrt(x*x + y*y + z*z);
}

void simple_sum(void)
{
    int a, b;
    scanf("%d%d", &a, &b);
    printf("%d + %d = %d\n", a, b, a + b);
}

void simple_math(void)
{
    char operaattori;
    double a, b;
    int res = scanf("%lf %c %lf", &a, &operaattori, &b);
    if (res == 3) {
        switch(operaattori) {
        case '+' :
            printf("%.1f", a + b);
            break;
        case '-' :
            printf("%.1f", a - b);
            break;
        case '*' :
            printf("%.1f", a * b);
            break;
        case '/' :
            printf("%.1f", a / b);
            break;
        default:
            printf("ERR");
        }
    } else {
        printf("ERR");
    }
}

void multi_table(int xsize, int ysize)
{
    for (int j = 1; j <= ysize; j++)
    {
        for (int i = 1; i <= xsize; i++)
            printf("%4d", i * j);
        printf("\n");
    }
}

void  draw_triangle(int size)
{
    for (int j = size; j > 0; j--)
    {
        for (int i = 1; i <= size; i++)
        {
            if (i >= j) {
                printf("#");
            } else {
                printf(".");
            }
        }
        printf("\n");
    }
}

double distance(int x, int y)
{
    return sqrt(x * x + y * y);
}

void draw_ball(unsigned int radius)
{
    int b = radius;
    int a = -b;
    for (int x = a; x <= b; x++)
    {
        for (int y = a; y <= b; y++)
        {
            if (distance(x,y) <= radius) {
                printf("*");
            } else {
                printf(".");
            }
        }
        printf("\n");
    }
}

void ascii_chart(char min, char max)
{
    for (int i = min, j = 1; i <= max; i++, j++)
    {
        printf("%3d 0x%02x %c", i, i, isprint(i) == 0 ? '?' : i);
        if (j % 4 == 0) printf("\n");
        else printf("\t");
    }
}

char *msgs[10] = {
    "'6=*w+~)._",
    "J65+~5+~=0/*69,~+9;,9*~19++=79"
};

char get_character(int msg, unsigned int cc) {
    if (msg >= 10 || !msgs[msg])
        return 0;

    if (strlen(msgs[msg]) <= cc)
        return 0;

    return msgs[msg][cc];
}

void secret_msg(int msg)
{
    int i = 0;
    char m;
    while (m = get_character(msg, i++)) {
        printf("%c", 158 - m);
    }
}

int main(void)
{
    printf("--- Secret message ---\n");
    secret_msg(0);  printf("\n");
    secret_msg(1);  printf("\n");
    return 0;
}