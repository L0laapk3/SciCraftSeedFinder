import com.scicraft.seedfinder.*;

public class QuadHutFinder extends StructureAndBiomeFinder {
	private final WitchHutLocator hut;

	public QuadHutFinder() {
		hut = new WitchHutLocator();
	}

	protected XZPair[] seedPotential(long baseSeed, int radius) {
		// Skips by two to find any potential hut that could be a member of
		// a quad hut group.
		for (int regionX=-radius; regionX < radius; regionX+=2) {
			for (int regionZ=-radius; regionZ < radius; regionZ+=2) {
				XZPair check = hut.structurePosInRegionEdge(regionX, regionZ, baseSeed, 2);
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

				// TODO: Get the precise spawn positions and orientations, and
				// verify that all really are close enough.
				return new XZPair[]{
					hut.fullCoordinates(rX, rZ, topLeft),
					hut.fullCoordinates(rX+1, rZ, topRight),
					hut.fullCoordinates(rX, rZ+1, bottomLeft),
					hut.fullCoordinates(rX+1, rZ+1, bottomRight)
				};
			}
		}

		return null;
	}

	protected boolean quadHutsWillSpawn(XZPair[] chunkLocations, BiomeGenerator generator) {
		// Only iterate through first four structures so that subclasses can
		// have additional ones more easily.
		for (int i=0; i<4; i++) {
			XZPair location = chunkLocations[i];
			if (!hut.structureWillSpawn(location, generator)) {
				return false;
			}
		}
		return true;
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		return quadHutsWillSpawn(chunkLocations, generator);
	}
}
