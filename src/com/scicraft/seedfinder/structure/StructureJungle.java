package com.scicraft.seedfinder;

public class StructureJungle extends StructureTemple {
	protected long structureSeed() {
		return 14357619;
	}

	protected int[] validBiomes() {
		return new int[]{21, 22};  // Jungle, jungle hills
	}
}
