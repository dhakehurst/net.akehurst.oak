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
package net.akehurst.oak.computational.core;

import net.akehurst.application.framework.common.annotations.instance.ServiceReference;
import net.akehurst.application.framework.common.property.Property;
import net.akehurst.application.framework.realisation.AbstractActiveObject;
import net.akehurst.application.framework.technology.interfaceFilesystem.IDirectory;
import net.akehurst.application.framework.technology.interfaceFilesystem.IFilesystem;
import net.akehurst.application.framework.technology.interfaceLogging.LogLevel;
import net.akehurst.oak.computational.interfaceUser.IUserNotification;
import net.akehurst.oak.computational.interfaceUser.IUserRequest;
import net.akehurst.oak.computational.interfaceUser.WorkspaceDetails;

public class ComandExecutor extends AbstractActiveObject implements ICommandExecutor, IUserRequest {

	public ComandExecutor(final String id) {
		super(id);
	}

	WorkspaceDetails currentWorkspace;

	@Override
	public void execute(final CommandIdentity command) {

		super.logger.log(LogLevel.INFO, "Executing command " + command.asPrimitive() + " with arguments ");

	}

	@ServiceReference
	IFilesystem fs;

	@Override
	public void afRun() {

	}

	@Override
	public void afTerminate() {
		// TODO Auto-generated method stub

	}

	public final Property<IUserNotification> userNotification = new Property<>();

	// --- IUserRequest ---

	@Override
	public void start() {
		this.requestSwitchWorkspace(this.fs.directory(System.getProperty("user.dir")));
	}

	@Override
	public void requestSwitchWorkspace(final IDirectory workspaceDirectory) {
		this.currentWorkspace = new WorkspaceDetails(workspaceDirectory);
		this.userNotification.get().notifyWorkspace(this.currentWorkspace);
	}

	@Override
	public void requestExecuteCommand() {
		// TODO Auto-generated method stub

	}

}
