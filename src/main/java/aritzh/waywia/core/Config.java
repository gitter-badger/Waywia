/*
 * Copyright (c) 2013 Aritzh (Aritz Lopez)
 *
 * This game is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This game is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this
 * game. If not, see http://www.gnu.org/licenses/.
 */

package aritzh.waywia.core;

import aritzh.waywia.lib.GameLib;
import aritzh.waywia.util.Configuration;

import java.io.File;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Config {

	public static final Configuration GAME;

	static {
		File configFile = new File("config.cfg");
		boolean init = !configFile.exists();
		GAME = Configuration.loadConfig(configFile);
		if (init) {
			Config.GAME.setProperty("Main", "Version", GameLib.VERSION);
		}
		Config.GAME.save();
	}

	// For the static initializer to run
	public static void init() {
	}
}
