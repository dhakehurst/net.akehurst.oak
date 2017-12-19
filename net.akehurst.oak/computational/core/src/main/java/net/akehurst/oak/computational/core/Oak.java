package net.akehurst.oak.computational.core;

import net.akehurst.application.framework.common.annotations.declaration.Component;
import net.akehurst.application.framework.common.annotations.declaration.ProvidesInterfaceForPort;
import net.akehurst.application.framework.common.annotations.instance.ActiveObjectInstance;
import net.akehurst.application.framework.common.annotations.instance.PortContract;
import net.akehurst.application.framework.common.annotations.instance.PortInstance;
import net.akehurst.application.framework.realisation.AbstractComponent;
import net.akehurst.application.framework.realisation.Port;
import net.akehurst.oak.computational.interfaceUser.IUserNotification;
import net.akehurst.oak.computational.interfaceUser.IUserRequest;

@Component
public class Oak extends AbstractComponent {

	public Oak(final String id) {
		super(id);
	}

	@ActiveObjectInstance
	@ProvidesInterfaceForPort(portId = "portUser", provides = IUserRequest.class)
	ComandExecutor executor;

	@Override
	public void afConnectParts() {
		this.executor.userNotification.set(this.portUser().out(IUserNotification.class));
	}

	@PortInstance
	@PortContract(provides = IUserRequest.class, requires = IUserNotification.class)
	Port portUser;

	public Port portUser() {
		return this.portUser;
	}

}
