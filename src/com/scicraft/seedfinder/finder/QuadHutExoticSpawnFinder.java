import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutExoticSpawnFinder extends QuadHutFinder {
	private static final float EXOTIC_FRACTION = 0.70f;
	private static final float MAX_EXCLUDED_FRACTION = 0.80f;
	private static final Biome[] EXOTIC_BIOMES = {
		Biome.mushroomIsland,
		Biome.mushroomIslandShore,
		Biome.mushroomIslandM,
		Biome.mushroomIslandShoreM,
		Biome.mesa,
		Biome.mesaBryce,
		Biome.mesaPlateau,
		Biome.mesaPlateauF,
		Biome.mesaPlateauM,
		Biome.mesaPlateauFM,
		Biome.icePlainsSpikes,
	};
	// Biomes that may surround the exotic biomes but shouldn't count against
	// the spawn area as being an exotic biome. Mushroom islands and ice spikes
	// tend to be very small so otherwise we'd have almost all mesas.
	private static final Biome[] EXCLUDED_BIOMES = {
		Biome.ocean,
		Biome.deepOcean,
		Biome.frozenOcean,
		Biome.river,
		Biome.frozenRiver,
		Biome.icePlains,
		Biome.iceMountains,
		Biome.coldTaiga,
		Biome.coldTaigaHills,
		Biome.oceanM,
		Biome.deepOceanM,
		Biome.frozenOceanM,
		Biome.riverM,
		Biome.frozenRiverM,
		Biome.coldTaigaM,
		Biome.coldTaigaHillsM,
		Biome.iceMountainsM,
	};

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!quadHutsWillSpawn(chunkLocations, generator)) {
			return false;
		}

		Hashtable<Integer, Float> census = generator.biomeCensus(
				worldSpawn.getX(), worldSpawn.getZ(), SPAWN_SIZE, SPAWN_SIZE, true);

		float excludedFraction = 0f;
		for (Biome biome : EXCLUDED_BIOMES) {
			excludedFraction += census.get(biome.index);
		}
		if (excludedFraction > MAX_EXCLUDED_FRACTION) {
			return false;
		}

		float exoticFraction = 0f;
		for (Biome biome : EXOTIC_BIOMES) {
			exoticFraction += census.get(biome.index);
		}

		// Only consider the non-excluded portion of the spawn area.
		return exoticFraction / (1f - excludedFraction) >= EXOTIC_FRACTION;
	}
}

