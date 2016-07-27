package net.akehurst.oak.engineering.gui.ide;

import net.akehurst.application.framework.technology.guiInterface.IGuiScene;
import net.akehurst.application.framework.technology.guiInterface.elements.IChart;
import net.akehurst.application.framework.technology.guiInterface.elements.IGuiElement;
import net.akehurst.application.framework.technology.guiInterface.elements.IGuiMenuItem;

public interface IIdeScene extends IGuiScene {

	IGuiMenuItem getSwitch();
	
}
