package com.eazytec.bpm.runtime.task.service;

import javax.jws.WebService;

import com.eazytec.model.OperatingFunctionAudit;


/**
 * It interacts with OperatingFunctionAuditDao to perfomr DB operation
 * @author revathi
 *
 */
@WebService
public interface OperatingFunctionAuditService {
	
	/**
	 * Get last operation performed for given task from audit log
	 * @param taskId
	 * @param processInstanceId 
	 * @return
	 */
	OperatingFunctionAudit getLastOperatinPerformedToTask(String processInstanceId);

}
