package com.scicraft.seedfinder;

import java.util.Arrays;
import java.util.HashSet;

abstract public class SizedStructureLocator extends StructureLocator {
	private final int structureSize;
	protected final HashSet<Integer> validBiomesForStructure;

	public SizedStructureLocator(
			long structureSeed, int structurePosRange, int structureRegionSize,
			Integer[] validBiomes, int structureSize) {
		super(structureSeed, structurePosRange, structureRegionSize, validBiomes);
		this.validBiomesForStructure = this.validBiomes;
		this.structureSize = structureSize;
	}

	public SizedStructureLocator(
			long structureSeed, int structurePosRange, int structureRegionSize,
			Integer[] validBiomes, Integer[] validBiomesForStructure, int structureSize) {
		super(structureSeed, structurePosRange, structureRegionSize, validBiomes);
		this.validBiomesForStructure = new HashSet<Integer>(Arrays.asList(validBiomesForStructure));
		this.structureSize = structureSize;
	}

	// Ocean monuments and Woodland Mansions average two randInts, other
	// structures just use one.
	protected int getChunkInRegion() {
		return (rnd.nextInt(structurePosRange) + rnd.nextInt(structurePosRange)) / 2;
	}

	public boolean structureWillSpawn(XZPair location, BiomeGenerator generator) {
		int biomeAt = generator.getBiomeAt(location.getX(), location.getZ());
		if (!validBiomes.contains(biomeAt)) {
			return false;
		}

		int centerX = location.getX();
		int centerZ = location.getZ();
		int left = (centerX - structureSize) >> 2;
		int top = (centerZ - structureSize) >> 2;
		int right = (centerX + structureSize) >> 2;
		int bottom = (centerZ + structureSize) >> 2;
		int width = right - left + 1;
		int height = bottom - top + 1;

		int[] biomes = generator.getBiomeData(left, top, width, height, true);
		for (int i=0; i<width*height; i++) {
			if (!validBiomesForStructure.contains(biomes[i])) {
				return false;
			}
		}
		return true;
	}
}
