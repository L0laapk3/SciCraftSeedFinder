package com.scicraft.seedfinder;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SpawnLocator {

	public static final ArrayList<Biome> validBiomes = new ArrayList<Biome>(Arrays.asList(
			Biome.forest,
			Biome.plains,
			Biome.taiga,
			Biome.taigaHills,
			Biome.forestHills,
			Biome.jungle,
			Biome.jungleHills
		));

	public static XZPair findValidLocation(int searchX, int searchY, int size, List<Biome> paramList, Random random, BiomeGenerator generator) {
		// TODO: Find out if we should useQuarterResolutionMap or not
		int x1 = searchX - size >> 2;
		int y1 = searchY - size >> 2;
		int x2 = searchX + size >> 2;
		int y2 = searchY + size >> 2;

		int width = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		int[] arrayOfInt = generator.getBiomeData(x1, y1, width, height, true);
		XZPair location = null;
		int numberOfValidFound = 0;
		for (int i = 0; i < width*height; i++) {
			int x = x1 + i % width << 2;
			int y = y1 + i / width << 2;
			if (arrayOfInt[i] > Biome.biomes.length)
				return null;
			Biome localBiome = Biome.biomes[arrayOfInt[i]];
			if ((!paramList.contains(localBiome)) || ((location != null) && (random.nextInt(numberOfValidFound + 1) != 0)))
				continue;
			location = new XZPair(x, y);
			numberOfValidFound++;
		}

		return location;
	}

	public XZPair getSpawnPosition(long seed, BiomeGenerator generator) {
		Random random = new Random(seed);
		XZPair location = findValidLocation(0, 0, 256, validBiomes, random, generator);
		int x = 0;
		int z = 0;
		if (location != null) {
			x = location.getX();
			z = location.getZ();
		} else {
			return null;
		}

		return new XZPair(x, z);
	}
}
