package org.sakaiproject.contentreview.impl.entity;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.contentreview.exception.SubmissionException;
import org.sakaiproject.contentreview.exception.TransientSubmissionException;
import org.sakaiproject.contentreview.impl.turnitin.TurnitinAPIUtil;
import org.sakaiproject.contentreview.impl.turnitin.TurnitinUtil;
import org.sakaiproject.entitybroker.EntityReference;
import org.sakaiproject.entitybroker.entityprovider.CoreEntityProvider;
import org.sakaiproject.entitybroker.entityprovider.capabilities.CRUDable;
import org.sakaiproject.entitybroker.entityprovider.capabilities.RESTful;
import org.sakaiproject.entitybroker.entityprovider.capabilities.Updateable;
import org.sakaiproject.entitybroker.entityprovider.search.Search;
import org.sakaiproject.entitybroker.util.AbstractEntityProvider;

public class TurnitinClassEntityProvider extends AbstractEntityProvider
implements Updateable {
    public static String PREFIX = "turnitin-class";
    
    

    public String getEntityPrefix() {
        return PREFIX;
    }

    public void updateEntity(EntityReference ref, Object entity,
            Map<String, Object> params) {
        
        List<String> extra = new ArrayList<String>();
        for (String key: params.keySet()) {
            extra.add(key); extra.add(params.get(key).toString());
        }
        
        try {
            TurnitinAPIUtil.createAssignment(
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_CID), // cid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_CTL), // ctl 
                    ref.getId(), // assignid
                    ref.getId(), // assignTitle
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_EMAIL), // uem
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_FIRST_NAME), //ufn
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_LAST_NAME),  //uln
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_PASSWORD), // upw
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_ID), // uid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_AID), // aid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_SECRET_KEY), // shared secret
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_SAID), //sub account id
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_API_URL), // api url
                    TurnitinUtil.TEST_PROXY, 
                    extra.toArray(new String[] {}));
        } catch (SubmissionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransientSubmissionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public Object getEntity(EntityReference ref) {
        String assignid = ref.getId();
        Map togo = null;
        try {
            Map retdata = TurnitinAPIUtil.getAssignment(
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_CID), // cid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_CTL), // ctl 
                    ref.getId(), // assignid
                    ref.getId(), // assignTitle
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_EMAIL), // uem
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_FIRST_NAME), //ufn
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_LAST_NAME),  //uln
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_PASSWORD), // upw
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_DEFAULT_INSTRUCTOR_ID), // uid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_AID), // aid
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_SECRET_KEY), // shared secret
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_SAID), //sub account id
                    serverConfigurationService.getString(TurnitinUtil.PROP_TURNITIN_API_URL), // api url
                    TurnitinUtil.TEST_PROXY // proxy
            );
            if (retdata.containsKey("object")) {
                togo = (Map) retdata.get("object");
            }
        } catch (TransientSubmissionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SubmissionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return togo;
    }
    
    private ServerConfigurationService serverConfigurationService;
    public void setServerConfigurationService(ServerConfigurationService serverConfigurationService) {
        this.serverConfigurationService = serverConfigurationService;
    }

}
