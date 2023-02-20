package caso1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Etapa3 extends Thread {
	private final int id;
    private final int numProducts;
    private final Semaphore[] buffers;
    private final Semaphore finalBuffer;
    private final CyclicBarrier barrier;
    
    public Etapa3(int id, int numProducts, Semaphore[] buffers, int bufferSize, Semaphore finalBuffer, CyclicBarrier barrier) {
    	this.id = id;
    	this.numProducts = numProducts;
    	this.buffers = buffers;
    	this.finalBuffer = finalBuffer;
    	this.barrier = barrier;
    }
    
    public void run() {
    	try {
    		barrier.await();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    		return;
    	} catch (BrokenBarrierException e) {
    		e.printStackTrace();
    		return;
    	}
    	
    	for (int i = 0; i < numProducts; i++) {
    		try {
    			buffers[id].acquire();
    			System.out.println(" El Proceso en la etapa 2 con "+id+" está procesando el producto con id "+i);
    			finalBuffer.acquire();
    			System.out.println("El proceso de la etapa 3 con "+id+" se encuentra poniendo el producto con id "+i+" en el buffer final");
    			finalBuffer.release();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    			return;
    		} finally {
    			buffers[(id + 1) % buffers.length].release();
    		}
    	}
    	
    	
    }

    


}
