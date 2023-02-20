package caso1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Etapa1 extends Thread{
	private final int id;
	private final int numProducts;
	private final IDGenerator idGenerator;
	private final Semaphore[] buffers1;
	private final int bufferSize1;
	private final CyclicBarrier barrier1;
	
	public Etapa1(int id, int numProducts, IDGenerator idGenerator, Semaphore[] buffers1, int bufferSize1, CyclicBarrier barrier1) {
		this.id = id;
		this.numProducts = numProducts;
		this.idGenerator = idGenerator;
		this.buffers1 = buffers1;
		this.bufferSize1 = bufferSize1;
		this.barrier1 = barrier1;
	}
	
	public void run() {
		for (int i = 0; i < numProducts;i++) {
			try {
				barrier1.await();
				int id = idGenerator.getNextId();
				buffers1[id%buffers1.length].acquire();
				System.out.println("Proceso en la etapa 1 con id "+id+" está procesando el producto con id "+id);
				buffers1[id%buffers1.length].release();
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			
		}
	}
	

}
