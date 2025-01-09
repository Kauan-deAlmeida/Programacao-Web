package org.example;

import java.util.Scanner;

public class Main {
    // Troque o nome do exercicio para "main"

    public static void questao02(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matriz01 = new int[2][3];
        int[][] matriz02 = new int[2][3];
        int[][] soma = new int[2][3];

        for (int linha = 0; linha < matriz01.length; linha++) {
            for (int coluna = 0; coluna < matriz01[0].length; coluna++) {
                System.out.printf("Digite o valor de [%d][%d] da matriz01: ", linha, coluna);
                matriz01[linha][coluna] = sc.nextInt();
            }
            for (int coluna = 0; coluna < matriz02[0].length; coluna++) {
                System.out.printf("Digite o valor de [%d][%d] da matriz02: ", linha, coluna);
                matriz02[linha][coluna] = sc.nextInt();
            }
            System.out.println();
        }

        exibeMatriz(matriz01);
        exibeMatriz(matriz02);
        exibeMatriz(somaMatriz(soma, matriz01, matriz02));
    }

    public static void questao03(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matriz01 = new int[3][3];
        int[] vetor = new int[3];

        for (int linha = 0; linha < matriz01.length; linha++) {
            for (int coluna = 0; coluna < matriz01[0].length; coluna++) {
                System.out.printf("Digite o valor de [%d][%d]", linha, coluna);
                matriz01[linha][coluna] = sc.nextInt();
            }
            System.out.println();
        }

        exibeSoma(somaDasColunas(vetor, matriz01));
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o tamanho da matriz");
        int n = sc.nextInt();
        int[][] matriz = new int[n][n];
        int somaPrimaria = 0;
        int somaSegundaria = 0;

        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[0].length; coluna++) {
                System.out.printf("Digite o valor de [%d][%d]", linha, coluna);
                matriz[linha][coluna] = sc.nextInt();
            }
            somaPrimaria += matriz[linha][linha];
            somaSegundaria += matriz[linha][matriz.length - 1 - linha];

            System.out.println();
        }
        exibeMatriz(matriz);
        System.out.println("Soma da diagonal primaria: " + somaPrimaria);
        System.out.println("Soma da diagonal segundaria: " + somaSegundaria);
    }

    public static void exibeMatriz(int[][] matriz){
        System.out.println("Exibindo dados da matriz: ");
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.printf("%d ", matriz[linha][coluna]);
            }
            System.out.println();
        }
    }

    public static void exibeSoma(int[] vetor){
        System.out.println("Exibindo dados da matriz: ");
        for (int linha = 0; linha < vetor.length; linha++) {
            System.out.printf("%d ", vetor[linha]);
            System.out.println();
        }
    }

    public static int[][] somaMatriz(int[][] soma, int[][] matriz01, int[][] matriz02){
        for (int linha = 0; linha < matriz01.length; linha++) {
            for (int i = 0; i < matriz01.length; i++) {
                for (int j = 0; j < matriz01[0].length; j++) {
                    soma[i][j] = matriz01[i][j] + matriz02[i][j];
                }
            }
        }
        System.out.println("Soma das duas matrizes");
        return soma;
    }

    public static int[] somaDasColunas(int[] soma, int[][] matriz){
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int i = 0; i < matriz.length; i++) {
                soma[linha] += matriz[i][linha];
            }
        }
        System.out.println("Soma das duas matrizes");
        return soma;
    }
}