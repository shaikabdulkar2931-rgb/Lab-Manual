public class Main {
    public static void main(String[] args) {
        PiCalculator piCalc = new PiCalculator();
        System.out.println("Calculated Pi: " + piCalc.getPiValue());
        System.out.println("Iterations: " + piCalc.getIterationsUsed());

        piCalc.calculate(10_000_000);
        System.out.println("Higher Precision Pi: " + piCalc.getPiValue());
    }

    
    static class PiCalculator {
        private double piValue;
        private long iterationsUsed;

        public PiCalculator() {
            
            calculate(1_000_000);
        }

        public void calculate(long iterations) {
            this.iterationsUsed = iterations;
            double sum = 0.0;

            for (long i = 0; i < iterations; i++) {
                if (i % 2 == 0) {
                    sum += 1.0 / (2 * i + 1);
                } else {
                    sum -= 1.0 / (2 * i + 1);
                }
            }

            this.piValue = 4.0 * sum;
        }

        public double getPiValue() {
            return piValue;
        }

        public long getIterationsUsed() {
            return iterationsUsed;
        }
    }
}
