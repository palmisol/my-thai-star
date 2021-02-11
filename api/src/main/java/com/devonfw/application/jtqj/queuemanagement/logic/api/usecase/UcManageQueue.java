package com.devonfw.application.jtqj.queuemanagement.logic.api.usecase;

import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueEto;

/**
 * Interface of UcManageQueue to centralize documentation and signatures of
 * methods.
 */
public interface UcManageQueue {

	/**
	 * Deletes a queue from the database by its id 'queueId'.
	 *
	 * @param queueId Id of the queue to delete
	 * @return boolean <code>true</code> if the queue can be deleted,
	 *         <code>false</code> otherwise
	 */
	boolean deleteQueue(long queueId);

	/**
	 * Saves a queue and store it in the database.
	 *
	 * @param queue the {@link QueueEto} to create.
	 * @return the new {@link QueueEto} that has been saved with ID and version.
	 */
	QueueEto saveQueue(QueueEto queue);
	
	
	/**
	 * Decrease number of customer of the queue and update the queue.
	 * 
	 * @param queueId id of the queue to decrease customer. 
	 */
	void decreaseQueueCustomer(long queueId);
	
	
	/**
	 * Increase number of customers of the queue and update the queue. 
	 * 
	 * @param queueId of the queue to increase customer.
	 */
	void increaseQueueCustomer(long queueId);
	
	

}