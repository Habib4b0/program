package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCRValueChangeActionTest {


	GtnFrameworkCRValueChangeAction ins=new GtnFrameworkCRValueChangeAction();


	@Test
	public void testDoAction() throws Exception {
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8 );
        
        resetActionParams.set(1, "val1");
        resetActionParams.set(2, "val1");
        resetActionParams.set(5, "val1");
        resetActionParams.set(6, "val2");
        gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
        
        GtnUIFrameworkBaseComponent treeTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        treeTable.addItemToDataTable(new Object());
        treeTable.addItemToDataTable(new Object());
        ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	

}
