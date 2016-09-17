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

package org.activiti.engine.impl.persistence.entity;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.task.Attachment;


/**
 * @author Tom Baeyens
 */
public class AttachmentEntityManager extends AbstractManager {

  @SuppressWarnings("unchecked")
  public List<Attachment> findAttachmentsByProcessInstanceId(String processInstanceId) {
    checkHistoryEnabled();
    return getDbSqlSession().selectList("selectAttachmentsByProcessInstanceId", processInstanceId);
  }

  @SuppressWarnings("unchecked")
  public List<Attachment> findAttachmentsByTaskId(String taskId) {
    checkHistoryEnabled();
    return getDbSqlSession().selectList("selectAttachmentsByTaskId", taskId);
  }

  @SuppressWarnings("unchecked")
  public void deleteAttachmentsByTaskId(String taskId) {
    checkHistoryEnabled();
    List<AttachmentEntity> attachments = getDbSqlSession().selectList("selectAttachmentsByTaskId", taskId);
    for (AttachmentEntity attachment: attachments) {
      String contentId = attachment.getContentId();
      if (contentId!=null) {
        getByteArrayManager().deleteByteArrayById(contentId);
      }
      getDbSqlSession().delete(attachment);
    }
  }
  
  public void insertAttachments(List<AttachmentEntity> attachments,String taskId){
	  for(AttachmentEntity entity : attachments){
		  entity.setTaskId(taskId);
		  entity.setId(null);
		  getDbSqlSession().insert(entity);
	  }
  }
  
  protected void checkHistoryEnabled() {
    if(!getHistoryManager().isHistoryEnabled()) {
      throw new ActivitiException("In order to use attachments, history should be enabled");
    }
  }
  
  public List<AttachmentEntity> findAttachmentEntitiesByTaskId(String taskId) {
	    checkHistoryEnabled();
	    return getDbSqlSession().selectList("selectAttachmentsByTaskId", taskId);
	  }
  
  public List<AttachmentEntity> findAttachmentEntitiesByIds(List<String> ids) {
	    checkHistoryEnabled();
	    return getDbSqlSession().selectList("selectAttachmentListByIds", ids); 
	  }
  
  public List<AttachmentEntity> selectAttachmentListByTaskIds(List<String> taskIds) {
	    checkHistoryEnabled();
	    return getDbSqlSession().selectList("selectAttachmentListByTaskIds", taskIds); 
	  }
  public List<AttachmentEntity> selectAttachmentListByInsId(String insId) {
	    checkHistoryEnabled();
	    return getDbSqlSession().selectList("selectAttachmentListByInsId", insId); 
	  }
  
  public List<AttachmentEntity> selectAttachmentEntityByProcessInstanceId(String processInstanceId) {
	    checkHistoryEnabled();
	    return getDbSqlSession().selectList("selectAttachmentEntityByProcessInstanceId", processInstanceId);
	  }

}
