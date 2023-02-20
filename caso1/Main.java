package caso1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int NUM_PRODUCTS = 5;
        final int BUFFER_SIZE = 4;

        Semaphore[] buffers1 = new Semaphore[BUFFER_SIZE];
        Semaphore[] buffers2 = new Semaphore[BUFFER_SIZE];
        Semaphore[] buffers3 = new Semaphore[BUFFER_SIZE];

        for (int i = 0; i < BUFFER_SIZE; i++) {
            buffers1[i] = new Semaphore(1);
            buffers2[i] = new Semaphore(1);
            buffers3[i] = new Semaphore(1);
        }

        Semaphore finalBuffer = new Semaphore(2);

        IDGenerator idGenerator = new IDGenerator();

        CyclicBarrier barrier1 = new CyclicBarrier(NUM_PRODUCTS);
        CyclicBarrier barrier2 = new CyclicBarrier(NUM_PRODUCTS);
        CyclicBarrier barrier3 = new CyclicBarrier(NUM_PRODUCTS);

        for (int i = 0; i < NUM_PRODUCTS; i++) {
            Etapa1 etapa1 = new Etapa1(i, NUM_PRODUCTS, idGenerator, buffers1, BUFFER_SIZE, barrier1);
            Etapa2 etapa2 = new Etapa2(i, NUM_PRODUCTS, buffers1, BUFFER_SIZE, buffers2, BUFFER_SIZE, barrier1, barrier2);
            Etapa3 etapa3 = new Etapa3(i, NUM_PRODUCTS, buffers2, BUFFER_SIZE, finalBuffer, barrier2);
            EtapaFinal etapaFinal = new EtapaFinal(i, NUM_PRODUCTS, buffers3, barrier3);

            etapa1.start();
            etapa2.start();
            etapa3.start();
            etapaFinal.start();
        }
    }
}


