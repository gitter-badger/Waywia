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

import io.github.aritzhack.util.IOUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.IOException;

/**
 * @author Aritz Lopez
 */
public class Main {

    public static void main(String[] args) throws SlickException {
        try {
            File root = IOUtil.getAppDir("waywia");

            if (args.length >= 3) {
                root = new File(args[2]);
            } else if (args.length == 1) {
                root = new File(args[0]);
            }

            String username = "", password = "";
            if (args.length >= 2) {
                username = args[0];
                password = args[1];
            }

            Game g = new Game(root, username, password);

            AppGameContainer gc = new AppGameContainer(g, 800, 600, false);
            gc.setShowFPS(false);
            gc.start();
        } catch (IOException e) {
            if (Game.logger != null) Game.logger.e("Could not initialize Waywia", e);
            else {
                System.err.println("Could not initialize Waywia");
            }
            e.printStackTrace();
            System.exit(1);
        }
    }

}
