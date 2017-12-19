/**
 * Copyright (C) 2016 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.akehurst.oak.engineering.gui.ide;

import net.akehurst.application.framework.common.IPort;
import net.akehurst.application.framework.common.annotations.instance.ActiveObjectInstance;
import net.akehurst.application.framework.common.annotations.instance.PortContract;
import net.akehurst.application.framework.common.annotations.instance.PortInstance;
import net.akehurst.application.framework.realisation.AbstractComponent;
import net.akehurst.application.framework.technology.interfaceGui.IGuiNotification;
import net.akehurst.application.framework.technology.interfaceGui.IGuiRequest;
import net.akehurst.oak.computational.interfaceUser.IUserNotification;
import net.akehurst.oak.computational.interfaceUser.IUserRequest;

public class GuiToTech extends AbstractComponent {

	public GuiToTech(final String id) {
		super(id);
	}

	@ActiveObjectInstance
	// @ProvidesInterfaceForPort(portId = "portTech", provides = IGuiNotification.class)
	// @ProvidesInterfaceForPort(portId = "portUser", provides = IUserNotification.class)
	GuiHandler handler;

	@Override
	public void afConnectParts() {
		this.portTech().connectInternal(this.handler);
		this.portUser().connectInternal(this.handler);
		// this.handler.setGuiRequest(this.portTech().out(IGuiRequest.class));
		// this.handler.userRequest.set(this.portUser().out(IUserRequest.class));
	}

	@PortInstance
	@PortContract(provides = IUserNotification.class, requires = IUserRequest.class)
	IPort portUser;

	public IPort portUser() {
		return this.portUser;
	}

	@PortInstance
	@PortContract(provides = IGuiNotification.class, requires = IGuiRequest.class)
	IPort portTech;

	public IPort portTech() {
		return this.portTech;
	}

}
