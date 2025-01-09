package org.example;

import java.util.Scanner;

public class Main {

    public static void exibeMatriz(int[][] matriz){
        for (int linha = 0; linha < matriz.length; linha++) {
            System.out.println("+----+----+----+----+");
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.printf("| %d ", matriz[linha][coluna]);
            }
            System.out.print("|\n");
        }
        System.out.println("+----+----+----+----+");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Criando um vetor de tamanho 4:
        int[] vetor = new int[4];

        // Criando uma matriz de tamanho 3X4 (3 linhas e 4 colunas)
        int[][] matriz01 = new int[3][4];

        for (int linha = 0; linha < matriz01.length; linha++) {
            for (int coluna = 0; coluna < matriz01[0].length; coluna++) {
                System.out.printf("Digite o valor de [%d][%d]: ", linha, coluna);
                matriz01[linha][coluna] = sc.nextInt();
            }
            System.out.println();
        }
        System.out.println("Exibindo dados da matriz01: ");
        exibeMatriz(matriz01);
    }
}