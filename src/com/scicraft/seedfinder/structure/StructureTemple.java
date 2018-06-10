package com.scicraft.seedfinder;

import java.util.Random;

abstract public class StructureTemple extends Structure {
	private Random rnd = new Random();

	/*
	 * return the chunk position in the region of the possible structure
	 */
	public XZPair structurePosInRegion(long x, long z, long seed) {
		rnd.setSeed((long) x * 341873128712L + (long)z * 132897987541L + seed + structureSeed());
		return new XZPair(rnd.nextInt(24), rnd.nextInt(24));
	}

	/*
	 * first check if the x pos is valid else return null
	 */
	public XZPair structurePosInRegionFast(long xPart, long zPart, long seed, int lowerThen, int higherThen) {
		rnd.setSeed(xPart + zPart + seed + structureSeed());
		int xRand = rnd.nextInt(24);
		if(xRand <= lowerThen || xRand >= higherThen)
			return new XZPair(xRand, rnd.nextInt(24));
		else
			return null;
	}
	/*
	 * checks if it will spawn
	 * @see com.scicraft.seedfinder.structure#structureWillSpawn(int xRegion, int zRegion, int xRandom, int zRandom, com.scicraft.seedfinder.BiomeGenerator)
	 */
	public boolean structureWillSpawn(int xRegion, int zRegion, int xRandom, int zRandom, BiomeGenerator generator) {
		int biomeAt = generator.getBiomeAt(xRegion*512 + xRandom*16 + 8, zRegion*512 + zRandom*16 + 8);
		for (int biome : validBiomes()) {
			if (biomeAt == biome) {
				return true;
			}
		}
		return false;
	}

	// Minecraft 1.13 changed structure generation (see MC-124960).
	// Previously, all structure types used 14357617 for this value, now
	// that value is only used by desert temples.
	abstract protected long structureSeed();
	abstract protected int[] validBiomes();
}
