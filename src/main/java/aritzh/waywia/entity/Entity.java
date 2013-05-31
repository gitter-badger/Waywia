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

package aritzh.waywia.entity;

import aritzh.waywia.bds.BDSCompound;
import aritzh.waywia.bds.BDSStorable;
import aritzh.waywia.bds.BDSString;
import aritzh.waywia.core.GameLogger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class Entity implements BDSStorable {

	float posX, posY;
	float velX = 0, velY = 0;
	int health;
	private BDSCompound customData = new BDSCompound("CustomData");
	private static Map<String, Class<? extends Entity>> stringToEntity = new HashMap<>();

	public Entity() {
		this.posX = 0;
		this.posY = 0;
		this.health = this.getMaxHealth();
	}

	public Entity(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		this.health = this.getMaxHealth();
	}

	public abstract int getMaxHealth();

	public void update(int delta) {
		this.posX += this.velX * delta;
		this.posY += this.velY * delta;

		// TODO Collision
	}

	public abstract Shape getBoundingShape();

	public BDSCompound getCustomData() {
		return this.customData;
	}

	public abstract void render(Graphics g);

	public abstract String getName();

	public BDSCompound toBDS() {
		return new BDSCompound("Entity")
				.add(new BDSString(this.getName(), "EntityName"))
				.add(new BDSString(Float.toString(posX), "posX"))
				.add(new BDSString(Float.toString(posY), "posY"))
				.add(new BDSString(Float.toString(velX), "velX"))
				.add(new BDSString(Float.toString(velY), "velY"))
				.add(this.customData);
	}

	public static void registerEntity(Class<? extends Entity> clazz) {
		if (clazz == null) throw new IllegalArgumentException("Null entity class cannot be registered");
		try {
			Entity.stringToEntity.put(clazz.newInstance().getName(), clazz);
		} catch (InstantiationException | IllegalAccessException | ExceptionInInitializerError ex) {
			InstantiationException e = new InstantiationException("Could not register entity");
			e.initCause(ex.getCause());
			GameLogger.exception("Failed to register entity class " + clazz, e);
		}
	}

	public static Entity newEntityFromName(String name) {
		try {
			return Entity.stringToEntity.get(name).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			GameLogger.exception("Could not instantiate entity with name \"" + name + "\"", e);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Entity type \"" + name + "\" not registered. This is a bug!");
		}
		return null;
	}
}
