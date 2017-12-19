/**
 * Copyright (C) 2018 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
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
package net.akehurst.oak.application.console;

import java.util.List;

import net.akehurst.application.framework.common.annotations.instance.CommandLineArgument;
import net.akehurst.application.framework.common.annotations.instance.CommandLineGroup;
import net.akehurst.application.framework.realisation.AbstractActiveObject;
import net.akehurst.oak.computational.core.CommandIdentity;
import net.akehurst.oak.computational.core.ICommandExecutor;

public class ComandLineInterpreter extends AbstractActiveObject {

	public ComandLineInterpreter(final String id) {
		super(id);
	}

	@CommandLineGroup(name = "build", description = "")
	@CommandLineGroup(name = "install", description = "")
	@CommandLineGroup(name = "publish", description = "")
	List<String> commands;

	@CommandLineArgument(group = "build", name = "target", required = true, description = "The target to build for.")
	String build_target;

	@Override
	public void afRun() {
		for (final String command : this.commands) {
			this.executor.execute(new CommandIdentity(command));
		}
	}

	ICommandExecutor executor;

	@Override
	public void afTerminate() {
		// TODO Auto-generated method stub

	}

}
