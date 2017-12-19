package net.akehurst.oak.engineering.gui.ide;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import net.akehurst.application.framework.common.annotations.declaration.ExternalConnection;
import net.akehurst.application.framework.common.annotations.instance.ConfiguredValue;
import net.akehurst.application.framework.technology.gui.common.AbstractGuiHandler;
import net.akehurst.application.framework.technology.interfaceFilesystem.FilesystemException;
import net.akehurst.application.framework.technology.interfaceFilesystem.IDirectory;
import net.akehurst.application.framework.technology.interfaceFilesystem.IFile;
import net.akehurst.application.framework.technology.interfaceGui.GuiEvent;
import net.akehurst.application.framework.technology.interfaceGui.IGuiScene;
import net.akehurst.application.framework.technology.interfaceGui.SceneIdentity;
import net.akehurst.application.framework.technology.interfaceGui.StageIdentity;
import net.akehurst.application.framework.technology.interfaceGui.data.tree.IGuiTreeData;
import net.akehurst.application.framework.technology.interfaceLogging.LogLevel;
import net.akehurst.oak.computational.interfaceUser.IUserNotification;
import net.akehurst.oak.computational.interfaceUser.IUserRequest;
import net.akehurst.oak.computational.interfaceUser.WorkspaceDetails;

public class GuiHandler extends AbstractGuiHandler implements IUserNotification {

	public GuiHandler(final String id) {
		super(id);
	}

	@ConfiguredValue(defaultValue = "/jfx/develop.fxml")
	String stageUrl;

	@ConfiguredValue(defaultValue = "oak")
	StageIdentity stageId;

	@ConfiguredValue(defaultValue = "develop")
	SceneIdentity sceneId;

	IIdeScene scene;

	@ExternalConnection
	IUserRequest userRequest;

	// --------- AbstractGuiHandler ---------
	@Override
	protected void onStageCreated(final GuiEvent event) {
		final URL content = this.getClass().getResource(this.stageUrl);
		this.scene = this.guiRequest.createScene(this.stageId, this.sceneId, IIdeScene.class, content);

		// Platform.runLater(() -> {
		// DockPane.initializeDefaultUserAgentStylesheet();
		// });
	}

	@Override
	protected void onSceneLoaded(final GuiEvent event) {
		this.scene.getMenuWorkspaceSwitch().onSelected(() -> {
			this.logger.log(LogLevel.INFO, "Switch Workspace");
		});
		this.scene.getMenuWorkspaceNewProject().onSelected(() -> {
			this.logger.log(LogLevel.INFO, "New Project");
		});

		this.scene.getMenuProject().visibleWhen((context) -> {
			return true;
		});

		this.scene.getMenuModule().visibleWhen((context) -> {
			return true;
		});

		this.userRequest.start();
	}

	@Override
	public IGuiScene getScene(final SceneIdentity sceneId) {
		return this.scene;
	}

	@Override
	public void notifyReady() {
		try {
			this.getGuiRequest().createStage(this.stageId, "/jfx", null, null);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void notifyWorkspace(final WorkspaceDetails workspace) {
		this.scene.getTreeViewBrowser().setData(new IGuiTreeData<Object>() {
			@Override
			public Object root() {
				return workspace;
			}

			@Override
			public String label(final Object item) {
				if (item instanceof WorkspaceDetails) {
					return ((WorkspaceDetails) item).getDirectory().getName();
				}
				return item.toString();
			}

			@Override
			public String style(final Object item) {
				return "";
			}

			@Override
			public boolean isLeaf(final Object item) {
				return item instanceof IFile;
			}

			@Override
			public List<Object> children(final Object item) {
				if (item instanceof IDirectory) {
					final IDirectory d = (IDirectory) item;
					try {
						return d.getEntries().stream().map(el -> (Object) el).collect(Collectors.toList());
					} catch (final FilesystemException e) {
						GuiHandler.this.logger.log(LogLevel.ERROR, e.getMessage(), e);
						return null;
					}
				} else {
					return null;
				}
			}
		});
	}

}
