import com.scicraft.seedfinder.*;

public class QuadHutFinder extends StructureAndBiomeFinder {
	private final StructureHut hut;

	public QuadHutFinder() {
		hut = new StructureHut();
	}

	protected XZPair[] seedPotential(long baseSeed, int radius) {
		// Skips by two to find any potential hut that could be a member of
		// a quad hut group.
		for (int regionX=-radius; regionX < radius; regionX+=2) {
			long xPart = hut.xPart(regionX);

			for (int regionZ=-radius; regionZ < radius; regionZ+=2) {
				long zPart = hut.zPart(regionZ);

				XZPair check = hut.structurePosInRegionFast(xPart, zPart, baseSeed, 1, 22);
				if (check == null) {
					continue;
				}

				// Start checking with what would be the top left of the group.
				int rX = regionX;
				int rZ = regionZ;
				if (check.getX() <= 1) {
					rX--;
				}
				if (check.getZ() <= 1) {
					rZ--;
				}

				XZPair topLeft = hut.structurePosInRegion(rX, rZ, baseSeed);
				if (topLeft.getX() < 22 || topLeft.getZ() < 22) {
					continue;
				}

				XZPair topRight = hut.structurePosInRegion(rX+1, rZ, baseSeed);
				if (topRight.getX() > 1 || topRight.getZ() < 22) {
					continue;
				}

				XZPair bottomLeft = hut.structurePosInRegion(rX, rZ+1, baseSeed);
				if (bottomLeft.getX() < 22 || bottomLeft.getZ() > 1) {
					continue;
				}

				XZPair bottomRight = hut.structurePosInRegion(rX+1, rZ+1, baseSeed);
				if (bottomRight.getX() > 1 || bottomRight.getZ() > 1) {
					continue;
				}

				return new XZPair[]{
					getFullChunk(rX, rZ, topLeft),
					getFullChunk(rX+1, rZ, topRight),
					getFullChunk(rX, rZ+1, bottomLeft),
					getFullChunk(rX+1, rZ+1, bottomRight)
				};
			}
		}

		return null;
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		for (XZPair location : chunkLocations) {
			if (!hut.structureWillSpawn(location.getX(), location.getZ(), generator)) {
				return false;
			}
		}
		return true;
	}
}
