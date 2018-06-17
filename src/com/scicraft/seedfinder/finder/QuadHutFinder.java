import com.scicraft.seedfinder.*;

public class QuadHutFinder extends StructureAndBiomeFinder {
	protected final WitchHutLocator hut;

	public QuadHutFinder() {
		hut = new WitchHutLocator();
	}

	protected XZPair[] seedPotential(long baseSeed, int radius) {
		// MC-131462 prevents East or South huts from spawning in negative X/Z
		// coordinates respecitvely (this should start at -radius for regionX
		// and regionZ and should be fixed if the bug is fixed.).

		// Skips by two to find any potential hut that could be a member of
		// a quad hut group.
		for (int regionX=0; regionX < radius; regionX += (regionX < radius-2 ? 2 : 1)) {
			for (int regionZ=0; regionZ < radius; regionZ += (regionZ < radius-2 ? 2 : 1)) {
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
				if (topLeft == null || topLeft.getX() < 22 || topLeft.getZ() < 22) {
					continue;
				}

				XZPair topRight = hut.structurePosInRegion(rX+1, rZ, baseSeed);
				if (topRight == null || topRight.getX() > 1 || topRight.getZ() < 22) {
					continue;
				}

				XZPair bottomLeft = hut.structurePosInRegion(rX, rZ+1, baseSeed);
				if (bottomLeft == null || bottomLeft.getX() < 22 || bottomLeft.getZ() > 1) {
					continue;
				}

				XZPair bottomRight = hut.structurePosInRegion(rX+1, rZ+1, baseSeed);
				if (bottomRight == null || bottomRight.getX() > 1 || bottomRight.getZ() > 1) {
					continue;
				}

				// TODO: Get the precise spawn positions and orientations, and
				// verify that all really are close enough.
				// you can get the orientation code from decompiled 1.12
				// probably
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

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		return checker.quadHutsWillSpawn(hut, chunkLocations, generator);
	}
}
