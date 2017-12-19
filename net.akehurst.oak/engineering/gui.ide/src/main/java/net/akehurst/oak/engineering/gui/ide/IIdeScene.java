package net.akehurst.oak.engineering.gui.ide;

import net.akehurst.application.framework.technology.interfaceGui.IGuiScene;
import net.akehurst.application.framework.technology.interfaceGui.data.tree.IGuiTreeView;
import net.akehurst.application.framework.technology.interfaceGui.elements.IGuiMenuItem;

public interface IIdeScene extends IGuiScene {

	// --- Context Menu ---
	IGuiMenuItem getMenuWorkspaceSwitch();

	IGuiMenuItem getMenuWorkspaceNewProject();

	IGuiMenuItem getMenuProject();

	IGuiMenuItem getMenuModule();

	// --- ---

	IGuiTreeView<Object> getTreeViewBrowser();
}
