package com.scicraft.seedfinder;

public abstract class Structure {
	/*
	 * generate the xpart of the equation
	 */
	public long xPart(int x){
		return (long) x * 341873128712L;
	}

	/*
	 * generate the zpart of the equation
	 */
	public long zPart(int z){
		return (long) z * 132897987541L;
	}

	public abstract XZPair structurePosInRegion(long x, long z, long seed);
	public abstract XZPair structurePosInRegionFast(long xPart, long zPart, long seed, int lowerThen, int higherThen);
	public abstract boolean structureWillSpawn(int xRegion, int zRegion, int xRandom, int zRandom, BiomeGenerator generator);
}
