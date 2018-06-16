package com.scicraft.seedfinder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

abstract public class StructureLocator {
	protected Random rnd = new Random();

	private static final long MAGIC_X = 341873128712L;
	private static final long MAGIC_Z = 132897987541L;

	private final long structureSeed;
	protected final int structurePosRange;
	private final int structureRegionSize;
	protected final HashSet<Integer> validBiomes;

	public StructureLocator(long structureSeed, int structurePosRange, int structureRegionSize, Integer[] validBiomes) {
		this.structureSeed = structureSeed;
		this.structurePosRange = structurePosRange;
		this.structureRegionSize = structureRegionSize;
		this.validBiomes = new HashSet<Integer>(Arrays.asList(validBiomes));
	}

	private void setSeed(long regionX, long regionZ, long worldSeed) {
		rnd.setSeed(regionX * MAGIC_X + regionZ * MAGIC_Z + structureSeed + worldSeed);
	}

	// Ocean monuments and Woodland Mansions average two randInts, other
	// structures just use one.
	protected int getChunkInRegion() {
		return rnd.nextInt(structurePosRange);
	}

	public XZPair structurePosInRegion(long regionX, long regionZ, long worldSeed) {
		setSeed(regionX, regionZ, worldSeed);
		int x = getChunkInRegion();
		if (x < 2 && regionX < 0) { // Emulating 1.13 bug MC-131462.
			return null;
		}
		int z = getChunkInRegion();
		if (z < 2 && regionZ < 0) { // Emulating 1.13 bug MC-131462.
			return null;
		}
		return new XZPair(x, z);
	}

	public XZPair structurePosInRegionEdge(long regionX, long regionZ, long worldSeed, int edgeSize) {
		setSeed(regionX, regionZ, worldSeed);
		int x = getChunkInRegion();
		if (x < 2 && regionX < 0) { // Emulating 1.13 bug MC-131462.
			return null;
		}
		if (x >= edgeSize && x < structurePosRange - edgeSize) {
			return null;
		}
		int z = getChunkInRegion();
		if (z < 2 && regionZ < 0) { // Emulating 1.13 bug MC-131462.
			return null;
		}
		if (z >= edgeSize && z < structurePosRange - edgeSize) {
			return null;
		}
		return new XZPair(x, z);
	}

	public XZPair fullCoordinates(int regionX, int regionZ, XZPair chunk) {
		int x = (regionX * structureRegionSize + chunk.getX()) * 16 + 8;
		int z = (regionZ * structureRegionSize + chunk.getZ()) * 16 + 8;
		return new XZPair(x, z);
	}

	public XZPair locationForRegion(int regionX, int regionZ, long worldSeed) {
		XZPair chunkLocation = structurePosInRegion(regionX, regionZ, worldSeed);
		if (chunkLocation == null) {
			return null;
		}
		return fullCoordinates(regionX, regionZ, chunkLocation);
	}

	public boolean structureWillSpawn(XZPair location, BiomeGenerator generator) {
		int biomeAt = generator.getBiomeAt(location.getX(), location.getZ());
		return validBiomes.contains(biomeAt);
	}

	public boolean structureWillSpawn(int regionX, int regionZ, long worldSeed, BiomeGenerator generator) {
		XZPair location = locationForRegion(regionX, regionZ, worldSeed);
		if (location == null) {
			return false;
		}
		return structureWillSpawn(location, generator);
	}
}
