import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static Scanner scan = new Scanner(System.in);                // ввод данных
    public static Random rand = new Random();               // генератор чисел

    public static int size = 3;             // размер поля
    public static char field[][];             // создание игрового поля
    public static char empty = '_';             // пустое поле
    public static char motionX = 'X';               // крестик игрока
    public static char motionO = 'O';               // нолик интелекта


    public static void main(String[] args) {
        createPlayField();
        showPlayField();
        while (true) {
            manMove();
            showPlayField();
            if (checkWin(motionX)) {
                System.out.println("Вы выиграли!");
                break;
            } else if (fullField()) {
                System.out.println("Ничья!");
                break;
            }
            aiMove();
            showPlayField();
            if (checkWin(motionO)) {
                System.out.println("Вы проиграли!");
                break;
            } else if (fullField()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    public static void createPlayField() {
        field = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = empty;
            }
        }
    }              // создание пустого игрового поля
    public static void showPlayField() {
        for (int x = 0; x < size; x++) {
            System.out.print("|");
            for (int y = 0; y < size; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        System.out.println("***********");
    }               // вывод игрового поля нв консоль
    public static void manMove() {
        int x, y;
        do {
            System.out.println("Введите координату X:  ");
            x = scan.nextInt() - 1;
            System.out.println("Введите координату Y:  ");
            y = scan.nextInt() - 1;
        } while (!checkValidMove(x, y));
            field[x][y] = motionX;

    }               // ход человека
    public static void aiMove() {
        int x, y;

        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!checkValidMove(x, y));
        field[x][y] = motionO;

    }               // ход ai
    public static boolean checkValidMove(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        } else if (field[x][y] == empty) {
            return true;
        } else {
            return false;
        }
    }               // проверка возможности хода
    public static boolean checkWin (char symb) {
        if(field[0][0] == symb && field[0][1] == symb && field[0][2] == symb) return true;
        if(field[1][0] == symb && field[1][1] == symb && field[1][2] == symb) return true;
        if(field[2][0] == symb && field[2][1] == symb && field[2][2] == symb) return true;
        if(field[0][0] == symb && field[1][0] == symb && field[2][0] == symb) return true;
        if(field[0][1] == symb && field[1][1] == symb && field[2][1] == symb) return true;
        if(field[0][2] == symb && field[1][2] == symb && field[2][2] == symb) return true;
        if(field[0][0] == symb && field[1][1] == symb && field[2][2] == symb) return true;
        if(field[2][0] == symb && field[1][1] == symb && field[0][2] == symb) return true;
        return false;
    }               // проверка выигрыша
    public static boolean fullField () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == empty)
                    return false;
            }
        }
        return true;
    }               // проверка, что все поля заполнены
}