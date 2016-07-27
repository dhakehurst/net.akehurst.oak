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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.akehurst.application.framework.common.annotations.instance.CommandLineArgument;
import net.akehurst.application.framework.common.annotations.instance.CommandLineGroup;
import net.akehurst.application.framework.realisation.AbstractActiveObject;
import net.akehurst.application.framework.realisation.AbstractComponent;
import net.akehurst.application.framework.realisation.AbstractIdentifiableObject;
import net.akehurst.application.framework.technology.interfaceLogging.LogLevel;

public class ComandExecutor extends AbstractActiveObject implements ICommandExecutor {

	public ComandExecutor(String id) {
		super(id);
	}

	
	public void execute(CommandIdentity command) {
		
		super.logger.log(LogLevel.INFO, "Executing command "+command.asPrimitive()+" with arguments ");
		
	}


	@Override
	public void afRun() {
		// TODO Auto-generated method stub
		
	}
	
}
