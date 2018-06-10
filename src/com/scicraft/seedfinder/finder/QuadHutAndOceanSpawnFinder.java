import com.scicraft.seedfinder.*;
import java.util.*;
import java.util.stream.IntStream;

public class QuadHutAndOceanSpawnFinder extends QuadHutFinder {
	private static final float WATER_FRACTION = 0.80f;

	private static final List<Biome> WATER_BIOMES = Arrays.asList(
		new Biome[] {
			Biome.ocean, // appears to not actually be ocean and is weird?
			Biome.deepOcean,
			Biome.frozenOcean,
			Biome.oceanM,
			Biome.deepOceanM,
			Biome.frozenOceanM,
		}
	);

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!super.fullSeedWorks(seed, generator, radius, chunkLocations, worldSpawn)) {
			return false;
		}

		// Quarter resolution was acting weird, so oh well.
		int[] biomes = generator.getBiomeData(
				worldSpawn.getX()-SPAWN_SIZE/2, worldSpawn.getZ()-SPAWN_SIZE/2, SPAWN_SIZE, SPAWN_SIZE, false);
		int waterCount = 0;
		for (int i=0; i<SPAWN_AREA; i+=10) {
			if (WATER_BIOMES.contains(Biome.biomes[biomes[i]])) {
				waterCount++;
			}
		}

		return (float)waterCount / (SPAWN_AREA/10) >= WATER_FRACTION;
	}
}
