package caso1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class EtapaFinal extends Thread {
	private int id;
	private int numProducts;
	private Semaphore[] buffers3;
	private CyclicBarrier barrier3;
	
	public EtapaFinal(int id, int numProducts, Semaphore[] buffers3, CyclicBarrier barrier3) {
		this.id = id;
		this.numProducts = numProducts;
		this.buffers3 = buffers3;
		this.barrier3 = barrier3;
	}
	
	public void run() {
		for (int i = 0; i < numProducts;i++) {
			try {
				barrier3.await();
				buffers3[0].acquire();
				buffers3[1].acquire();
				buffers3[0].release();
				buffers3[1].release();
				
				System.out.println("El proceso con id "+ id+" en el buffer final, procesó un objeto azul y naranja");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
