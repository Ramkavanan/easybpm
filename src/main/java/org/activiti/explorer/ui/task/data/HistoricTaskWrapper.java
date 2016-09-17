/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.explorer.ui.task.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.OperatingFunction;
import org.activiti.engine.task.Task;

import com.eazytec.bpm.engine.TaskRole;
import com.eazytec.model.User;

/**
 * TODO: This class is a hack, to quickly convert a {@link HistoricTaskInstance}
 * to a Task, so we can reuse the existing components for a task. Obviously,
 * this is not a good approach in the long run.
 * 
 * @author Joram Barrez
 */
public class HistoricTaskWrapper implements Task {

  protected String id;
  protected String name;
  protected String description;
  protected int priority;
  protected String owner;
  protected String assignee;
  protected Date dueDate;
  protected String parentTaskId;
  
  protected int maxDays;
  
  protected int warningDays;

  protected String dateType;
  
  protected int urgeTimes;
  
  protected int frequenceInterval;
  
  protected String undealOperation;
  
  protected Date lastUrgedTime; 
  
  protected String notificationType;

  public HistoricTaskWrapper(HistoricTaskInstance historicTaskInstance) {
    this.id = historicTaskInstance.getId();
    setName(historicTaskInstance.getName());
    setDescription(historicTaskInstance.getDescription());
    setDueDate(historicTaskInstance.getDueDate());
    setPriority(historicTaskInstance.getPriority());
    setOwner(historicTaskInstance.getOwner());
    setAssignee(historicTaskInstance.getAssignee());
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public DelegationState getDelegationState() {
    return null;
  }

  public void setDelegationState(DelegationState delegationState) {
  }

  public String getProcessInstanceId() {
    return null;
  }

  public String getExecutionId() {
    return null;
  }

  public String getProcessDefinitionId() {
    return null;
  }

  public Date getCreateTime() {
    return null;
  }

  public String getTaskDefinitionKey() {
    return null;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public void delegate(String userId) {
  }

  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public String getParentTaskId() {
    return parentTaskId;
  }
  
  public boolean isSuspended() {
    return false;
  }

  public Map<TaskRole, OperatingFunction> getOperatingFunctions() {
		return null;
	}

	public void setOperatingFunctions(
			Map<TaskRole, OperatingFunction> operatingFunctions) {
		
	}
	 public TaskDefinition getTaskDefinition() {
		   
		    return null;
		  }
	 public void setTaskDefinition(TaskDefinition taskDefinition){
		 
	 }
	 public List<IdentityLink>getIdentityLinksApplicableForUser(User user){
		 return null;
	 }
	 
	public int getMaxDays() {
		return maxDays;
	}
	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}
	public int getWarningDays() {
		return warningDays;
	}
	public void setWarningDays(int warningDays) {
		this.warningDays = warningDays;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public int getUrgeTimes() {
		return urgeTimes;
	}
	public void setUrgeTimes(int urgeTimes) {
		this.urgeTimes = urgeTimes;
	}

	public int getFrequenceInterval() {
		return frequenceInterval;
	}
	public void setFrequenceInterval(int frequenceInterval) {
		this.frequenceInterval = frequenceInterval;
	}
	
	public String getUndealOperation() {
		return undealOperation;
	}
	public void setUndealOperation(String undealOperation) {
		this.undealOperation = undealOperation;
	}
	
	public Date getLastUrgedTime() {
		return lastUrgedTime;
	}
	public void setLastUrgedTime(Date lastUrgedTime) {
		this.lastUrgedTime = lastUrgedTime;
	}
	
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}	
}
