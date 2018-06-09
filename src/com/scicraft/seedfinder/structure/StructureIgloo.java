package com.scicraft.seedfinder;

public class StructureIgloo extends StructureTemple {
	protected long structureSeed() {
		return 14357618;
	}

	protected int[] validBiomes() {
		return new int[]{12, 30};  // Ice plains, cold taiga
	}
}
