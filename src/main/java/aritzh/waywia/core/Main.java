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

import aritzh.waywia.util.Util;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.IOException;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Main {

	public static void main(String[] args) throws SlickException {
		try {
			File root = Util.getAppDir("waywia");
			Game g;
			if (args.length < 2) g = new Game(root, false);
			else if (Login.isCorrect(args[0], args[1])) {
				g = new Game(root, true);
			} else {
				GameLogger.severe("Incorrect log-in information!");
				g = new Game(root, false);
			}
			AppGameContainer gc = new AppGameContainer(g, 800, 600, false);
			gc.setShowFPS(false);
			gc.start();
		} catch (IOException e) {
			if (GameLogger.isInit()) GameLogger.logAndThrowAsRuntime("Could not initialize Waywia", e);
			else {
				System.err.println("Could not initialize Waywia");
				throw new RuntimeException(e);
			}
			System.exit(1);
		}
	}

}
