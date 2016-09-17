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
package org.activiti.engine.impl.cmd;

import java.util.Map;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.eazytec.bpm.common.util.I18nUtil;
import com.eazytec.exceptions.DataSourceException;


/**
 * @author Joram Barrez
 */
public class CompleteTaskCmd extends NeedsActiveTaskCmd<Void> {
      
  private static final long serialVersionUID = 1L;
  protected Map<String, Object> variables;
  
  public CompleteTaskCmd(String taskId, Map<String, Object> variables) {
    super(taskId);
    this.variables = variables;
  }
  
  protected Void execute(CommandContext commandContext, TaskEntity task)throws DataSourceException {
    if (variables!=null) {
      task.setExecutionVariables(variables);
    }
    
    //task.complete();
    task.signOff();
    return null;
  }
  
  @Override
  protected String getSuspendedTaskException() {
	return I18nUtil.getMessageProperty("error.operateSuspendTask");
  }

}
