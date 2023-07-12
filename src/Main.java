public class Main {
    private static final int MAX_ITERATIONS = 100;
    private static final double EPSILON = 0.0001;

    public static void main(String[] args) {
        // Matriz de coeficientes del sistema de ecuaciones
        double[][] coefficients = {
                { 4, -1, 1 },
                { 1, 3, -2 },
                { 2, -1, -3 }
        };

        // Términos constantes del sistema de ecuaciones
        double[] constants = { 5, -1, -7 };

        // Resolver el sistema de ecuaciones utilizando el método de Jacobi
        double[] solution = solve(coefficients, constants);

        // Imprimir la solución
        System.out.println("Solution:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }

    public static double[] solve(double[][] coefficients, double[] constants) {
        int n = coefficients.length;
        double[] previousSolution = new double[n];
        double[] currentSolution = new double[n];
        int iterations = 0;

        while (iterations < MAX_ITERATIONS) {
            // Calcular la solución en la iteración actual
            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= coefficients[i][j] * previousSolution[j];
                    }
                }
                currentSolution[i] = sum / coefficients[i][i];
            }

            // Verificar si la solución ha convergido
            if (converged(previousSolution, currentSolution)) {
                break;
            }

            // Actualizar la solución anterior con la solución actual
            System.arraycopy(currentSolution, 0, previousSolution, 0, n);

            iterations++;
        }

        return currentSolution;
    }

    private static boolean converged(double[] previous, double[] current) {
        // Verificar si todas las variables han convergido dentro de la tolerancia EPSILON
        for (int i = 0; i < previous.length; i++) {
            if (Math.abs(current[i] - previous[i]) > EPSILON) {
                return false;
            }
        }
        return true;
    }
}