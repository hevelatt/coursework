#include <stdio.h>
#include <stdlib.h>

#define NOSHIP '.'
#define SHIP '+'
#define SUNKEN '#'

struct field_st {
    char ship;
    char vis;
};

extern const unsigned int xsize;
extern const unsigned int ysize;
extern const unsigned int shiplen;

struct field_st **field;


/**
 *  Creates an empty game field that is <xsize> wide and <ysize> tall.
 *  By default <xsize> and <ysize> are 10 squares.
 *  Optimistically assumes that the function always succeeds.
 */
void create_field(void)
{
    field = malloc(ysize * sizeof(*field));
    for (unsigned int i = 0; i < ysize; i++) {
        field[i] = malloc(xsize * sizeof(**field));
        for (unsigned int j = 0; j < xsize; j++) {
            field[i][j].ship = NOSHIP;
            field[i][j].vis = 0;
        }
    }
}


/**
 * Places a single ship on the game field. Each ship is 3 squares tall.
 * The ship is placed on location <xp>, <yp>.
 * <dir> determines whether the ship is horizontally or vertically aligned:
 * value 0 means horizontal ship, value 1 means vertical ship.
 * @param xp ship x-coordinate
 * @param yp ship y-koordinate
 * @return 0 if creation failed and 1 if creation of ship was succesful.
 */
int place_ship(unsigned int xp, unsigned int yp, int dir)
{
    if (dir != 0 && dir != 1)
        return 0;

    // horizontal ship
    if (dir == 0) {
        if (yp >= ysize || xp >= xsize || xp + shiplen >= xsize) // out of bounds?
            return 0;

        // check that the position is free
        for (unsigned int x = xp; x < xp + shiplen; x++) {
            if (field[yp][x].ship == SHIP)
                return 0;
        }
        // set ship
        for (unsigned int x = xp; x < xp + shiplen; x++) {
            field[yp][x].ship = SHIP;
        }
    }

    // vertical ship
    if (dir == 1) {
        // out of bounds?
        if (xp >= xsize || yp >= ysize || yp + shiplen >= ysize)
            return 0;

        // check that the position is free
        for (unsigned int y = yp; y < yp + shiplen; y++) {
            if (field[y][xp].ship == SHIP)
                return 0;
        }
        // set ship
        for (unsigned int y = yp; y < yp + shiplen; y++) {
            field[y][xp].ship = SHIP;
        }
    }
    return 1;
}

/**
 * Returns 1 if location <x>,<y> is visible to player (i.e., it has been tried
 * at least once before). Returns 0 if location is not visible.
 * @param x x-coordinate to look
 * @param y y-coordinate to look
 * @return 1 if visible, 0 not visible
 */
int is_visible(unsigned int x, unsigned int y)
{
    if (x >= xsize || y >= ysize)
        return 0;

    return field[y][x].vis;
}


/**
 * Returns the whether there is a ship on given location <x>,<y>.
 * The function returns a character that can be used in printout, if sector
 * is visible. Following characters are used:
 * '.' -- location does not have a ship.
 * '+' -- location has (part of) a ship that is not yet hit.
 * '#' -- location has (part of) a ship that has been hit.
 * @param x x-coordinate to look
 * @param y y-coordinate to look
 * @return . if no ship, # hit
 */
char is_ship(unsigned int x, unsigned int y)
{
    if (x >= xsize || y >= ysize)
        return '.';

    return field[y][x].ship;
}

/**
 * Marks ship at location <x>,<y> as hit.
 * You must check before this call that the location indeed has a ship
 * @param x x-coordinate to set
 * @param y y-coordinate to set
 */
void hit_ship(unsigned int x, unsigned int y)
{
    if (x >= xsize || y >= ysize)
        return;

    field[y][x].ship = SUNKEN;
}

/**
 * Marks location <x>,<y> as checked, i.e., it becomes visible to the player.
 * @param x x-coordinate to set
 * @param y y-coordinate to set
 */
void checked(unsigned int x, unsigned int y)
{
    if (x >= xsize || y >= ysize)
        return;

    field[y][x].vis = 1;
}

// BYCODEBEGIN
#include <time.h>
#define UNKNOWN '?'
const unsigned int xsize = 10;
const unsigned int ysize = 10;
const unsigned int shiplen = 3;

void set_ships(unsigned int num)
{
    srand(time(NULL));
    for (int i = 0; i < num; i++) {
        while (place_ship(rand() % xsize, rand() % ysize, rand() % 2) == 0);
    }
}

void print_field(void)
{
    for (unsigned int i = 0; i < xsize; i++) {
        for (unsigned int j = 0; j < ysize; j++) {
            if (is_visible(j,i)) {
                printf("%c", is_ship(j,i));
            } else {
                printf("%c", UNKNOWN);
            }
        }
        printf("\n");
    }
}

int shoot(void)
{
    unsigned int x, y;
    if (scanf("%u %u", &x, &y) != 2 || x >= xsize || y >= ysize) return -1;
    if (is_ship(x,y) == SHIP) {
        checked(x, y);
        hit_ship(x,y);
        return 1;
    } else {
        checked(x, y);
        return 0;
    }
}

int game_over(unsigned int num)
{
    unsigned int sunken = 0;
    for (unsigned int i = 0; i < xsize; i++)
        for (unsigned int j = 0; j < ysize; j++)
            if (is_ship(j,i) == SUNKEN) sunken++;
    if (sunken == num*shiplen) return 1;
    return 0;
}

// BYCODEEND

/**
 * Main funktion to loop until no input or game over
 * @return 0
 */
int main(void)
{
    // Re-seed random number generator using clock
    srand(1); // (unsigned) time(NULL));

    // We play with 5 ships
    unsigned int num = 5;
    create_field();
    set_ships(num);
    printf("ships set\n");
    char *last = "";

    // Repeat until are ships are destroyed
    while(!game_over(num)) {
        // printf("new turn\n");
        // printf("Enter coordinates: ");
        int i = shoot();
        if (i < 0) break; // TIM ei saa uusia syötteitä
        if (i < 0) {
            printf("Invalid coordinates\n");
        } else if (i == 0) {
            last = "Miss";
        } else {
            last = "Hit!";
        }
    }
    printf("%s\n",last);
    print_field();
    return 0;
}


