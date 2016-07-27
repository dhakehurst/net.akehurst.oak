package net.akehurst.oak.engineering.gui.ide;

import java.net.URL;

import org.dockfx.DockPane;

import javafx.application.Platform;
import net.akehurst.application.framework.common.annotations.instance.ConfiguredValue;
import net.akehurst.application.framework.technology.gui.common.AbstractGuiHandler;
import net.akehurst.application.framework.technology.guiInterface.GuiEvent;
import net.akehurst.application.framework.technology.guiInterface.IGuiScene;
import net.akehurst.application.framework.technology.guiInterface.SceneIdentity;
import net.akehurst.application.framework.technology.guiInterface.StageIdentity;
import net.akehurst.application.framework.technology.interfaceLogging.LogLevel;

public class GuiHandler extends AbstractGuiHandler {

	public GuiHandler(String id) {
		super(id);
	}

	@ConfiguredValue(defaultValue = "/jfx/develop.fxml")
	String stageUrl;

	@ConfiguredValue(defaultValue = "/oak")
	StageIdentity stageId;

	@ConfiguredValue(defaultValue = "develop")
	SceneIdentity sceneId;
	
	IIdeScene scene;
	
	// --------- AbstractGuiHandler ---------
	@Override
	protected void onStageCreated(GuiEvent event) {
		URL content = this.getClass().getResource(this.stageUrl);
		this.scene = this.guiRequest.createScene(this.stageId, this.sceneId, IIdeScene.class, content);
		
//		Platform.runLater(() -> {
//			DockPane.initializeDefaultUserAgentStylesheet();
//		});
	}

	@Override
	protected void onSceneLoaded(GuiEvent event) {
		this.scene.getSwitch().onSelected(()-> {
			this.logger.log(LogLevel.INFO, "Switch Workspace");
		});

	}

	@Override
	protected IGuiScene getScene(SceneIdentity sceneId) {
		return this.scene;
	}

	@Override
	public void notifyReady() {
		try {
			URL content = this.getClass().getResource("/jfx");
			this.getGuiRequest().createStage(stageId, false, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
