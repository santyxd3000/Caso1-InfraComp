package caso1;

import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Etapa2 extends Thread{
	
	private final int id;
	private final int numProducts;
	private final Semaphore[] buffers1;
	private final int bufferSize1;
	private final Semaphore[] buffers2;
	private final int bufferSize2;
	private final CyclicBarrier barrier1;
	private final CyclicBarrier barrier2;
	
	public Etapa2(int id, int numProducts, Semaphore[] buffers1,int bufferSize1,Semaphore[] buffers2, int bufferSize2, CyclicBarrier barrier1,CyclicBarrier barrier2) {
		this.id = id;
		this.numProducts = numProducts;
		this.buffers1 = buffers1;
		this.buffers2 = buffers2;
		this.barrier1 = barrier1;
		this.barrier2 = barrier2;
		this.bufferSize1 = bufferSize1;
		this.bufferSize2 = bufferSize2;
	}

	public void run() {
		try {
			for (int i = 0; i < numProducts; i++) {
				barrier1.await();
				Semaphore buffer1 = buffers1[id];
				Semaphore buffer2 = buffers2[id];
				buffer1.acquire();
				int productId = buffer1.availablePermits();
				buffer2.acquire();
				System.out.println("El proceso en la etapa 2 con id "+id+" está procesando el producto con id "+productId);
				buffer1.release();
				buffer2.release();
				barrier2.await();
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
	
	
	


	
	
	

	
}