public class Corredor implements Runnable {
    private static String nomeVencedor = null;
    private static double distanciaTotalDaCorrida = 500;
    private double distanciaTotalPercorrida = 0;
    private Thread threadMain;

    public String getNomeVencedor() {
        return nomeVencedor;
    }

    public Corredor (Thread threadMain){
        this.threadMain = threadMain;
    }
    @Override
    public void run() {
        synchronized (Corredor.class){
            try {
                threadMain.join();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            while (distanciaTotalPercorrida < distanciaTotalDaCorrida){
                distanciaTotalPercorrida += Math.random() * 50;
                System.out.printf (
                    "%s percorreu %.2fm\n",
                    Thread.currentThread().getName(),
                    distanciaTotalPercorrida
                );
                try{
                    Thread.sleep(2000 + (int)(Math.random() * 3000));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (nomeVencedor == null){
                nomeVencedor = Thread.currentThread().getName();
                System.out.println ("Vencedor: " + nomeVencedor);
            }
        }

        System.out.printf("%s acabou!!!", Thread.currentThread().getName());
    }
}