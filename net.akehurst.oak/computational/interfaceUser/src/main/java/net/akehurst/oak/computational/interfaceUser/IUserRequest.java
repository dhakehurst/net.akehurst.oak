package net.akehurst.oak.computational.interfaceUser;

import net.akehurst.application.framework.technology.interfaceFilesystem.IDirectory;

public interface IUserRequest {

	void start();

	void requestSwitchWorkspace(IDirectory workspaceDirectory);

	void requestExecuteCommand();

}
