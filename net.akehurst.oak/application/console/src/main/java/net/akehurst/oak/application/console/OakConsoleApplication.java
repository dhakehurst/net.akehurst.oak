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
package net.akehurst.oak.application.console;

import net.akehurst.application.framework.common.annotations.declaration.Application;
import net.akehurst.application.framework.common.annotations.instance.ActiveObjectInstance;
import net.akehurst.application.framework.common.annotations.instance.ComponentInstance;
import net.akehurst.application.framework.common.annotations.instance.ServiceInstance;
import net.akehurst.application.framework.realisation.AbstractApplication;
import net.akehurst.application.framework.technology.filesystem.StandardFilesystem;
import net.akehurst.application.framework.technology.log4j.Log4JLogger;
import net.akehurst.application.framework.technology.persistence.filesystem.HJsonFile;
import net.akehurst.oak.computational.core.ComandExecutor;

@Application
public class OakConsoleApplication extends AbstractApplication {

	public OakConsoleApplication(String id, String[] args) {
		super(id, args);
	}

	@ServiceInstance
	Log4JLogger logger;
	
	@ServiceInstance
	StandardFilesystem fs;
	
	@ServiceInstance
	HJsonFile configuration;
	
	
	@ActiveObjectInstance
	ComandLineInterpreter clInterpreter;
	
	@ActiveObjectInstance
	ComandExecutor executor;
	
	@Override
	public void connectComputationalToEngineering() {
		this.clInterpreter.executor = this.executor;
	}
}
