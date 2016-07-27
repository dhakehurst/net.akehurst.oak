package net.akehurst.oak.computational.core;

import net.akehurst.application.framework.common.AbstractDataType;

public class CommandCall extends AbstractDataType {
	
	public CommandCall(CommandIdentity id) {
		super(id);
		this.id = id;
	}

	CommandIdentity id;
	public CommandIdentity getIdentity() {
		return this.id;
	}

}
