package com.scicraft.seedfinder;

import com.scicraft.seedfinder.*;

public class QuadHutFinder extends StructureAndBiomeFinder {
	public final static int TOPRIGHT = 0;
	public final static int BOTTOMRIGHT = 1;
	public final static int BOTTOMLEFT = 2;
	public final static int TOPLEFT = 3;
	public static int[] xpos = new int[4];
	public static int[] zpos = new int[4];
	public static int xmon, zmon;
	public static StructureHut hut;
	public static BitIterator bitIt;

	QuadHutFinder() {
		hut = new StructureHut();
	}

	protected XZPair[] seedPotential(long baseSeed, int radius) {
		for (int regionX=-radius; regionX < radius; regionX++) {
			long xPart = hut.xPart(regionX);

			for (int regionZ=-radius; regionZ < radius; regionZ++) {
				long zPart = hut.zPart(regionZ);

				XZPair topLeft = hut.structurePosInRegionFast(xPart, zPart, baseSeed);
				if (topLeft.getX() < 22 || topLeft.getZ() < 22) {
					continue;
				}

				XZPair topRight = hut.structurePosInRegion(regionX+1, regionZ, baseSeed);
				if (topRight.getX() > 1 || topRight.getZ() < 22) {
					continue;
				}

				XZPair bottomLeft = hut.structurePosInRegion(regionX, regionZ+1, baseSeed);
				if (bottomLeft.getX() < 22 || bottomLeft.getZ() > 1) {
					continue;
				}

				XZPair bottomRight = hut.structurePosInRegion(regionX+1, regionZ+1, baseSeed);
				if (bottomRight.getX() > 1 || bottomRight.getZ() > 1) {
					continue;
				}

				return new XZPair[]{topLeft, topRight, bottomLeft, bottomRight};
			}
		}

		return null;
	}

	protected boolean fullSeedWorks(long seed, int radius, XZPair[] locations) {
		BiomeGenerator generator = new BiomeGenerator(seed, 2);
		for (XZPair location : locations) {
			if (!hut.structureWillSpawn(location.getX(), location.getZ(), generator)) {
				return false;
			}
		}
		return true;
	}
}
