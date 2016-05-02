package net.akehurst.oak.engineering.gui.ide;

import java.net.URL;

import net.akehurst.application.framework.common.annotations.instance.ConfiguredValue;
import net.akehurst.application.framework.technology.gui.common.AbstractGuiHandler;
import net.akehurst.application.framework.technology.guiInterface.GuiEvent;
import net.akehurst.application.framework.technology.guiInterface.IGuiScene;
import net.akehurst.application.framework.technology.guiInterface.SceneIdentity;
import net.akehurst.application.framework.technology.guiInterface.StageIdentity;

public class GuiHandler extends AbstractGuiHandler {

	public GuiHandler(String id) {
		super(id);
	}

	@ConfiguredValue(defaultValue = "/webservice")
	String stageUrl;

	IHomeScene home;

	@ConfiguredValue(defaultValue = "/oak")
	StageIdentity stageId;

	@Override
	protected void onStageCreated(GuiEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSceneLoaded(GuiEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IGuiScene getScene(SceneIdentity sceneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyReady() {
		try {
			URL content = this.getClass().getResource(this.stageUrl);
			this.getGuiRequest().createStage(stageId, false, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
