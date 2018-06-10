import com.scicraft.seedfinder.*;

abstract public class StructureAndBiomeFinder extends SeedFinder {
	abstract protected XZPair[] seedPotential(long baseSeed, int radius);
	abstract protected boolean fullSeedWorks(long seed, int radius, XZPair[] chunkLocations);

	protected XZPair getFullChunk(int regionX, int regionZ, XZPair regionChunk) {
		return new XZPair(regionX*32 + regionChunk.getX(), regionZ*32 + regionChunk.getZ());
	}

	public void findSeeds(long startSeed, int radius) {
		for (long baseSeed = startSeed; baseSeed <= endSeed; baseSeed++) {
			XZPair[] potential = seedPotential(baseSeed, radius);
			if (potential == null) {
				continue;
			}

			System.out.println("Checking bits with potential " + baseSeed + "...");
			for (long high=0; high<(1<<16); high++) {
				long fullSeed = (high << 48) + baseSeed;
				if (fullSeedWorks(fullSeed, radius, potential)) {
					hooray(fullSeed, potential);
				}
			}
		}
	}

	protected void hooray(long seed, XZPair[] chunkLocations) {
		System.out.print(seed);
		for (XZPair location:chunkLocations) {
			System.out.print(" (" + location.getX()*16 + ", " + location.getZ()*16 + ")");
		}
		System.out.print("\n");
	}
}
