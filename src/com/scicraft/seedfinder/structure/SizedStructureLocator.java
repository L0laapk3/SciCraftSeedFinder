package com.scicraft.seedfinder;

import java.util.Arrays;
import java.util.HashSet;

abstract public class SizedStructureLocator extends StructureLocator {
	private final int structureSize;

	public SizedStructureLocator(
			long structureSeed, int structurePosRange, int structureRegionSize,
			Integer[] validBiomes, int structureSize) {
		super(structureSeed, structurePosRange, structureRegionSize, validBiomes);
		this.structureSize = structureSize;
	}

	// Ocean monuments and Woodland Mansions average two randInts, other
	// structures just use one.
	protected int getChunkInRegion() {
		return (rnd.nextInt(structurePosRange) + rnd.nextInt(structurePosRange)) / 2;
	}

	protected boolean areaHasValidBiomes(
			BiomeGenerator generator, XZPair location, int size, HashSet<Integer> checkBiomes) {
		int centerX = location.getX();
		int centerZ = location.getZ();
		int left = (centerX - size) >> 2;
		int top = (centerZ - size) >> 2;
		int right = (centerX + size) >> 2;
		int bottom = (centerZ + size) >> 2;
		int width = right - left + 1;
		int height = bottom - top + 1;

		int[] biomes = generator.getBiomeData(left, top, width, height, true);
		for (int i=0; i<width*height; i++) {
			if (!checkBiomes.contains(biomes[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean structureWillSpawn(XZPair location, BiomeGenerator generator) {
		int biomeAt = generator.getBiomeAt(location.getX(), location.getZ());
		if (!validBiomes.contains(biomeAt)) {
			return false;
		}
		return areaHasValidBiomes(generator, location, structureSize, validBiomes);
	}
}
