import com.scicraft.seedfinder.*;

public class QuadHutFinder extends StructureAndBiomeFinder {
	public static StructureHut hut;

	QuadHutFinder() {
		hut = new StructureHut();
	}

	// This starts by looking for the top-left witch hut, because it would be
	// the first of the four encountered in the loop. The loop starts at
	// -radius-1 so that it includes situations where the bottom-left extends
	// into the searched region.
	protected XZPair[] seedPotential(long baseSeed, int radius) {
		for (int regionX=-radius-1; regionX < radius; regionX++) {
			long xPart = hut.xPart(regionX);

			for (int regionZ=-radius-1; regionZ < radius; regionZ++) {
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

				return new XZPair[]{
					getFullChunk(regionX, regionZ, topLeft),
					getFullChunk(regionX+1, regionZ, topRight),
					getFullChunk(regionX, regionZ+1, bottomLeft),
					getFullChunk(regionX+1, regionZ+1, bottomRight)
				};
			}
		}

		return null;
	}

	protected boolean fullSeedWorks(long seed, int radius, XZPair[] chunkLocations) {
		BiomeGenerator generator = new BiomeGenerator(seed, 2);
		for (XZPair location : chunkLocations) {
			if (!hut.structureWillSpawn(location.getX(), location.getZ(), generator)) {
				return false;
			}
		}
		return true;
	}
}
