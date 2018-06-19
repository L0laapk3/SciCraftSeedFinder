import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutExoticSpawnFinder extends QuadHutFinder {
	private static final float EXOTIC_FRACTION = 0.50f;
	private static final Biome[] EXOTIC_BIOMES = {
		Biome.mushroomIsland,
		Biome.mushroomIslandShore,
		Biome.mushroomIslandM,
		Biome.mushroomIslandShoreM,
		// Found too many mesa spawns and not enough of the others.
		// Biome.mesa,
		// Biome.mesaBryce,
		// Biome.mesaPlateau,
		// Biome.mesaPlateauF,
		// Biome.mesaPlateauM,
		// Biome.mesaPlateauFM,
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
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}

		return checker.hasBiomes(
				generator, SPAWN_SIZE, worldSpawn.getX(), worldSpawn.getZ(),
				EXOTIC_BIOMES, EXCLUDED_BIOMES, EXOTIC_FRACTION);
	}
}

