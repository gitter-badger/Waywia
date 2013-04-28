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

package aritzh.waywia.bds;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.util.Arrays;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BDSByte implements IBDS {

	private final byte data;
	private final String name;

	public BDSByte(byte data, String name) {
		this.data = data;
		this.name = name;
	}

	public BDSByte(byte[] data) {
		this(ByteStreams.newDataInput(Arrays.copyOfRange(data, 1, data.length)));
	}

	public BDSByte(ByteArrayDataInput input) {
		if (input == null) {
			this.data = 0;
			this.name = "";
			return;
		}
		try {
			this.name = input.readUTF();
			this.data = input.readByte();
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not parse BDSByte\n" + e.getStackTrace());
		}
	}

	@Override
	public byte[] getBytes() {
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeByte(this.getType().toByte());
		output.writeUTF(this.name);
		output.writeByte(data);
		return output.toByteArray();
	}

	@Override
	public String getName() {
		return name;
	}

	public byte getData() {
		return data;
	}

	@Override
	public BDSType getType() {
		return BDSType.BDS_BYTE;
	}
}