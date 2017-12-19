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
package net.akehurst.oak.application.desktop;

import net.akehurst.application.framework.common.annotations.instance.ComponentInstance;
import net.akehurst.application.framework.common.annotations.instance.ServiceInstance;
import net.akehurst.application.framework.realisation.AbstractApplication;
import net.akehurst.application.framework.service.configuration.file.HJsonConfigurationService;
import net.akehurst.application.framework.technology.filesystem.StandardFilesystem;
import net.akehurst.application.framework.technology.gui.jfx.JfxWindow;
import net.akehurst.application.framework.technology.log4j.Log4JLogger;
import net.akehurst.oak.computational.core.Oak;
import net.akehurst.oak.engineering.gui.ide.GuiToTech;

public class OakDesktopApplication extends AbstractApplication {

	public OakDesktopApplication(final String id) {
		super(id);
	}

	// --- Services ---
	@ServiceInstance
	Log4JLogger logger;

	@ServiceInstance
	StandardFilesystem fs;

	@ServiceInstance
	HJsonConfigurationService configuration;

	// --- Components ---
	@ComponentInstance
	Oak oak;

	@ComponentInstance
	GuiToTech gui;

	@ComponentInstance
	JfxWindow jfx;

	@Override
	public void afConnectParts() {
		this.oak.portUser().connect(this.gui.portUser());

		this.gui.portTech().connect(this.jfx.portGui());
	}
}
