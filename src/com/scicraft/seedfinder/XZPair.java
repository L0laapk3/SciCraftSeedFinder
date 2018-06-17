package com.scicraft.seedfinder;

public class XZPair {
	private final int x, z;

	public XZPair(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public String asString() {
		return String.format("%4d, %4d", x, z);
	}
}
