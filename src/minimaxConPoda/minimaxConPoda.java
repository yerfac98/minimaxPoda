/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxConPoda;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Gerardo Fac
 */
public class minimaxConPoda {
    public static int tablerofinal = 5;
    public int Matriz[][];
    int Depth = 3;//Profundidad
    boolean change;//cambio
    JButton board[][], reset;//tablero,Reiniciar
    JFrame window;//ventana
    JLabel message;//mensaje

    public minimaxConPoda(int[][] Matriz1) {
        Matriz = Matriz1;
        change = false;
        window = new JFrame("Minimax con poda");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(null);
        window.setResizable(false);
        window.setLocation(200, 100);

        board = new JButton[tablerofinal][tablerofinal];

        for (int i = 0, y = 0; i < tablerofinal; i++, y += 50) {
            for (int j = 0, x = 0; j < tablerofinal; j++, x += 50) {
                board[j][i] = new JButton();
                board[j][i].setBackground(Color.black);
                board[j][i].setBounds(x, y, 50, 50);
                window.add(board[j][i]);
            }
        }
        message = new JLabel("init");
        message.setBounds(50, 300, 100, 100);
        window.add(message);
        reset = new JButton("Reset");
        reset.setBounds(50, 450, 100, 100);
        reset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InitComponent();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        window.add(reset);
        InitComponent();
        window.repaint();

    }

    public void InitComponent() {
        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                board[j][i].setBackground(Color.black);
                Matriz[j][i] = 0;
                message.setText("Iniciar");
                board[j][i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        for (int i = 0, y = 0; i < tablerofinal; i++, y += 100) {
                            for (int j = 0, x = 0; j < tablerofinal; j++, x += 100) {
                                if (e.getSource() == board[j][i] && Matriz[j][i] == 0) {
                                    board[j][i].setBackground(Color.blue);
                                    Matriz[j][i] = 1;
                                    board[j][i].removeMouseListener(this);

                                    if (!estaLLeno(Matriz) && (esganar(Matriz) != 1 && esganar(Matriz) != 2)) {
                                        miniMax(Matriz);
                                        window.repaint();

                                    }
                                    ImprimirEnTablero(Matriz);
                                }
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }

    }

    public boolean estaLLeno(int m[][]) {
        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                if (m[j][i] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int Matriz[][];
        Matriz = new int[tablerofinal][tablerofinal];

        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                Matriz[i][j] = 0;

            }
        }
        new minimaxConPoda(Matriz);
    }

    private void readNUmbers() {
        Scanner reader = new Scanner(System.in);
        int i = 0, j = 0;
        boolean ver = true;

        System.out.println("Introduce  1 número y pulsa enter, luego repite, si la pos esta vacia continua");

        do {
            j = reader.nextInt();
            i = reader.nextInt();
            if (i < 3 && j < 3) {
                if (this.Matriz[j][i] == 0) {
                    this.Matriz[j][i] = 1;
                    ver = false;
                }
            }
        } while (ver);
        change = true;
    }

    public int esganar(int m[][]) {

        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                if (j + 3 < tablerofinal) {
                    if (m[j][i] == 1 && m[j + 1][i] == 1 && m[j + 2][i] == 1 && m[j + 3][i] == 1) {
                        return 1;
                    }
                }
                if (i + 3 < tablerofinal) {
                    if (m[j][i] == 1 && m[j][i + 1] == 1 && m[j][i + 2] == 1 && m[j][i + 3] == 1) {
                        return 1;
                    }
                }

                if (i + 3 < tablerofinal && j + 3 < tablerofinal) {
                    if (m[j][i] == 1 && m[j + 1][i + 1] == 1 && m[j + 2][i + 2] == 1 && m[j + 3][i + 3] == 1) {
                        return 1;
                    }
                }
                if (i - 3 > -1 && j + 3 < tablerofinal) {
                    if (m[j][i] == 1 && m[j + 1][i - 1] == 1 && m[j + 2][i - 2] == 1 && m[j + 3][i - 3] == 1) {
                        return 1;
                    }
                }
                if (i + 3 < tablerofinal && j - 3 > -1) {
                    if (m[j][i] == 1 && m[j - 1][i + 1] == 1 && m[j - 2][i + 2] == 1 && m[j - 3][i + 3] == 1) {
                        return 1;
                    }
                }

                if (j + 3 < tablerofinal) {
                    if (m[j][i] == 2 && m[j + 1][i] == 2 && m[j + 2][i] == 2 && m[j + 3][i] == 2) {
                        return 2;
                    }
                }
                if (i + 3 < tablerofinal) {
                    if (m[j][i] == 2 && m[j][i + 1] == 2 && m[j][i + 2] == 2 && m[j][i + 3] == 2) {
                        return 2;
                    }
                }

                if (i + 3 < tablerofinal && j + 3 < tablerofinal) {
                    if (m[j][i] == 2 && m[j + 1][i + 1] == 2 && m[j + 2][i + 2] == 2 && m[j + 3][i + 3] == 2) {
                        return 2;
                    }
                }
                if (i - 3 > -1 && j + 3 < tablerofinal) {
                    if (m[j][i] == 2 && m[j + 1][i - 1] == 2 && m[j + 2][i - 2] == 2 & m[j + 3][i - 3] == 2) {
                        return 2;
                    }
                }
                if (i + 3 < tablerofinal && j - 3 > -1) {
                    if (m[j][i] == 2 && m[j - 1][i + 1] == 2 && m[j - 2][i + 2] == 2 && m[j - 3][i + 3] == 2) {
                        return 2;
                    }
                }

            }
        }
        return 0;
    }

    private void ImprimirEnTablero(int m[][]) {
        System.out.println("");
        System.out.println("");
        if (esganar(m) == 1) {
            System.out.println("Ganaste Humano");
            message.setText("Ganaste Humano");
        }
        if (esganar(m) == 2) {
            System.out.println("Gano IA");
            message.setText("Gano IA");
        }
        System.out.println("");
        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                System.out.print(m[j][i] + "\t");
            }
            System.out.println("");
        }
    }

    private void miniMax(int[][] m) {
        int mejorfila = -1, MejorColumna = -1;
        int max, maxActual;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {
                if (m[j][i] == 0) {
                    int tempfila, tempc;
                    m[j][i] = 2;
                    tempfila = i;
                    tempc = j;

                    maxActual = ValorMin(m, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    m[tempc][tempfila] = 0;

                    if (max < maxActual) {
                        max = maxActual;
                        mejorfila = tempfila;
                        MejorColumna = tempc;
                    }

                }
            }
        }
    Matriz[MejorColumna][mejorfila] = 2;
        board[MejorColumna][mejorfila].setBackground(Color.red);

        message.setText("Play Human");

        change = false;
    }

    public int ValorMin(int[][] m, int deph, int alfa, int beta) {

        if (esganar(m) == 1 || esganar(m) == 2) {
            return Heuristica(m);
        } else if (estaLLeno(m)) {
            return Heuristica(m);
        } else if (Depth < deph) {
            return Heuristica(m);
        } else {
            for (int i = 0; i < tablerofinal; i++) {
                for (int j = 0; j < tablerofinal; j++) {
                    if (m[j][i] == 0) {
                        int tempfila, tempc;
                        m[j][i] = 1;
                        tempfila = i;
                        tempc = j;

                        beta = min(beta, ValorMax(m, deph + 1, alfa, beta));

                        m[tempc][tempfila] = 0;

                        if (alfa >= beta) {
                            return alfa;
                        }

                    }
                }
            }
            return beta;
        }

    }

    public int ValorMax(int[][] m, int deph, int alfa, int beta) {
        if (esganar(m) == 1 || esganar(m) == 2) {
            return Heuristica(m);
        } else if (estaLLeno(m)) {
            return Heuristica(m);
        } else {
            for (int i = 0; i < tablerofinal; i++) {
                for (int j = 0; j < tablerofinal; j++) {
                    if (m[j][i] == 0) {
                        int tempfila, tempc;
                        m[j][i] = 2;
                        tempfila = i;
                        tempc = j;

                        alfa = max(alfa, ValorMin(m, deph + 1, alfa, beta));

                        m[tempc][tempfila] = 0;

                        if (alfa >= beta) {
                            return beta;
                        }

                    }
                }
            }
            return alfa;
        }

    }

    public int Heuristica(int m[][]) {
        int costo = 0;
        costo = Costo(m, 2) - Costo(m, 1);
        return costo;
    }

    public int Costo(int m[][], int jugador) {
        int value = 0;

        for (int i = 0; i < tablerofinal; i++) {
            for (int j = 0; j < tablerofinal; j++) {

                //para 4 
                if (j + 3 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i] == jugador && m[j + 2][i] == jugador && m[j + 3][i] == jugador) {
                        return 10000;
                    }
                }
                if (i + 3 < tablerofinal) {
                    if (m[j][i] == jugador && m[j][i + 1] == jugador && m[j][i + 2] == jugador && m[j][i + 3] == jugador) {
                        return 10000;
                    }
                }

                if (i + 3 < tablerofinal && j + 3 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i + 1] == jugador && m[j + 2][i + 2] == jugador && m[j + 3][i + 3] == jugador) {
                        return 10000;
                    }
                }
                if (i - 3 > -1 && j + 3 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i - 1] == jugador && m[j + 2][i - 2] == jugador && m[j + 3][i - 3] == jugador) {
                        return 10000;
                    }
                }
                if (i + 3 < tablerofinal && j - 3 > -1) {
                    if (m[j][i] == jugador && m[j - 1][i + 1] == jugador && m[j - 2][i + 2] == jugador && m[j - 3][i + 3] == jugador) {
                        return 10000;
                    }
                }

                //Para 3
                if (j + 2 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i] == jugador && m[j + 2][i] == jugador) {
                        value = 1000;
                    }
                }
                if (i + 2 < tablerofinal) {
                    if (m[j][i] == jugador && m[j][i + 1] == jugador && m[j][i + 2] == jugador) {
                        value = 1000;
                    }
                }

                if (i + 2 < tablerofinal && j + 2 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i + 1] == jugador && m[j + 2][i + 2] == jugador) {
                        value = 1000;
                    }
                }
                if (i - 2 > -1 && j + 2 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i - 1] == jugador && m[j + 2][i - 2] == jugador) {
                        value = 1000;
                    }
                }
                if (i + 2 < tablerofinal && j - 2 > -1) {
                    if (m[j][i] == jugador && m[j - 1][i + 1] == jugador && m[j - 2][i + 2] == jugador) {
                        value = 1000;
                    }
                }

                if (j + 1 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i] == jugador) {
                        if (value < 300) {
                            value = 300;
                        }
                    }
                }
                if (i + 1 < tablerofinal) {
                    if (m[j][i] == jugador && m[j][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300;
                        }
                    }
                }

                if (i + 1 < tablerofinal && j + 1 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300;
                        }
                    }
                }
                if (i - 1 > -1 && j + 1 < tablerofinal) {
                    if (m[j][i] == jugador && m[j + 1][i - 1] == jugador) {
                        if (value < 300) {
                            value = 300;
                        }
                    }
                }
                if (i + 1 < tablerofinal && j - 1 > -1) {
                    if (m[j][i] == jugador && m[j - 1][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300;
                        }
                    }
                }

            }
        }

        return value;

    }

}
