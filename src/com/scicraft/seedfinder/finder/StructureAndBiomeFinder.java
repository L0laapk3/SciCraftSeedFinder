import com.scicraft.seedfinder.*;

abstract public class StructureAndBiomeFinder extends SeedFinder {
	protected static final SpawnLocator spawnLocator = new SpawnLocator();
	protected static final int SPAWN_SIZE = 256;
	protected static final int SPAWN_AREA = 256*256;

	abstract protected XZPair[] seedPotential(long baseSeed, int radius);
	abstract protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn);

	protected XZPair getFullChunk(int regionX, int regionZ, XZPair regionChunk) {
		return new XZPair(regionX*32 + regionChunk.getX(), regionZ*32 + regionChunk.getZ());
	}

	public void findSeeds(long startSeed, int radius) {
		for (long baseSeed = startSeed; baseSeed <= endSeed; baseSeed++) {
			XZPair[] potential = seedPotential(baseSeed, radius);
			if (potential == null) {
				continue;
			}

			System.out.printf("Checking bits with potential %15d...\n", baseSeed);
			for (long high=0; high<(1<<16); high++) {
				long fullSeed = (high << 48) ^ baseSeed;
				BiomeGenerator generator = new BiomeGenerator(fullSeed, 2);
				XZPair worldSpawn = spawnLocator.getSpawnPosition(fullSeed, generator);
				if (worldSpawn == null) {
					worldSpawn = new XZPair(0, 0);
				}
				try {
					if (fullSeedWorks(fullSeed, generator, radius, potential, worldSpawn)) {
						hooray(fullSeed, generator, potential, worldSpawn);
					}
				} catch (Exception e) {
					System.out.printf("Exception thrown for seed %d.\n", fullSeed);
				}
			}
		}
	}

	protected void hooray(
			long seed, BiomeGenerator generator, XZPair[] chunkLocations, XZPair worldSpawn) {
		System.out.printf("%24d : (spawn: %s)", seed, worldSpawn.asString());
		for (XZPair location : chunkLocations) {
			System.out.printf(" (%s)", location.asString());
		}
		System.out.print("\n");

		/* Debugging ascii art spawn area map
		int[] biomes = generator.getBiomeData(
				worldSpawn.getX()-SPAWN_SIZE/2, worldSpawn.getZ()-SPAWN_SIZE/2, SPAWN_SIZE, SPAWN_SIZE, false);
		for (int z=0; z<SPAWN_SIZE; z+=4) {
			for (int x=0; x<SPAWN_SIZE; x+=2) {
				System.out.print((char)(biomes[x+z*SPAWN_SIZE]+64));
			}
			System.out.println("");
		}
		*/
	}
}
