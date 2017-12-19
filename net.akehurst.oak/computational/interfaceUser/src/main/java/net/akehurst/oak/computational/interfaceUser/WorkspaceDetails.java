package net.akehurst.oak.computational.interfaceUser;

import net.akehurst.application.framework.common.annotations.declaration.DataType;
import net.akehurst.application.framework.technology.interfaceFilesystem.IDirectory;

@DataType
public class WorkspaceDetails {

	public WorkspaceDetails(final IDirectory workspaceDirectory) {
		this.workspaceDirectory = workspaceDirectory;
	}

	final IDirectory workspaceDirectory;

	public IDirectory getDirectory() {
		return this.workspaceDirectory;
	}
}
