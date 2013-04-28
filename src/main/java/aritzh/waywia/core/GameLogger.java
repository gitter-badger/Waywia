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

import aritzh.waywia.i18n.I18N;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GameLogger {
	private static Logger logger = Logger.getLogger("GameLogger");
	private static final Level DEFAULT_LOG_LEVEL = Level.INFO;

	public static void initLogger() {
		logger.setParent(Logger.getGlobal());
	}

	public static void log(String s) {
		GameLogger.logger.log(DEFAULT_LOG_LEVEL, s);
	}

	public static void log(Level level, String message) {
		GameLogger.log(level, message);
	}

	public static void log(String format, Object... params) {
		GameLogger.log(String.format(format, params));
	}

	public static void log(Level level, String format, Object... params) {
		GameLogger.log(level, String.format(format, params));
	}

	public static void warning(String message) {
		GameLogger.logger.warning(message);
	}

	public static void warning(String format, Object... args) {
		GameLogger.warning(String.format(format, args));
	}

	public static void debug(String message) {
		GameLogger.logger.fine(message);
	}

	public static void debug(String format, Object... args) {
		GameLogger.debug(String.format(format, args));
	}

	public static void logTranslated(Level level, String message) {
		GameLogger.log(level, I18N.translate(message));
	}

	public static void logTranslated(Level level, String format, Object... args) {
		GameLogger.logTranslated(level, String.format(format, args));
	}

	public static void logTranslated(String message) {
		GameLogger.logTranslated(GameLogger.DEFAULT_LOG_LEVEL, message);
	}

	public static void logTranslated(String format, Object... args) {
		GameLogger.logTranslated(GameLogger.DEFAULT_LOG_LEVEL, format, args);
	}

	public static void warnTranslated(String message) {
		GameLogger.warning(I18N.translate(message));
	}

	public static void warnTranslated(String format, Object... args) {
		GameLogger.warnTranslated(String.format(format, args));
	}
}