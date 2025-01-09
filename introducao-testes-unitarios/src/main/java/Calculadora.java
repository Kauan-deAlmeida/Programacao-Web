public class Calculadora {

    public Double somar(Double n1, Double n2) {

        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("Não vale null");
        }

        return n1+n2;
    }
}
