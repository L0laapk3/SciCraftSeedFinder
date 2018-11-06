import java.lang.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import com.scicraft.seedfinder.*;
import com.scicraft.seedfinder.finder.*;
import java.io.*;

public class FinderMain {
	public static void main(String[] args) {
		String finderName = "QuadHut";
		if (args.length < 1) {
			System.out.println("No finder specified, using default...");
			System.out.println("Available finders:");
			System.out.println("	QuadHut - basic quad hut finder");
			System.out.println("	QuadHutOceanSpawn - with mostly ocean spawn chunks");
			System.out.println("	QuadHutExoticBiomes - ocean spawn and all rare biomes nearby");
			System.out.println("	QuadHutMultiMansion - ocean spawn and at least two woodland mansions nearby");
			System.out.println("	QuadHutThreeMansion - at least three woodland mansions nearby");
			System.out.println("	QuadHutExoticSpawn - rare biome in spawn chunks");
			System.out.println("	ExtraCloseQuadHut - quad huts chunks are as close together as possible");
			System.out.println("	QuadHutMonument - ocean monument very near quad huts");
			System.out.println("	QuadHutMushroom - lots of mushroom biome area");
			System.out.println("	Everything - ocean spawn, all the biomes, close woodland mansion");
		} else {
			finderName = args[0];
		}

		Class finderType;
		switch (finderName) {
			case "QuadHut":
				finderType = QuadHutFinder.class;
				break;
			case "QuadHutOceanSpawn":
				finderType = QuadHutOceanSpawnFinder.class;
				break;
			case "QuadHutExoticBiomes":
				finderType = QuadHutExoticBiomesFinder.class;
				break;
			case "QuadHutMultiMansion":
				finderType = QuadHutMultiMansionFinder.class;
				break;
			case "QuadHutThreeMansion":
				finderType = QuadHutThreeMansionFinder.class;
				break;
			case "QuadHutExoticSpawn":
				finderType = QuadHutExoticSpawnFinder.class;
				break;
			case "ExtraCloseQuadHut":
				finderType = ExtraCloseQuadHutFinder.class;
				break;
			case "QuadHutMonument":
				finderType = QuadHutMonumentFinder.class;
				break;
			case "QuadHutMushroom":
				finderType = QuadHutMushroomFinder.class;
				break;
			case "Everything":
				finderType = EverythingFinder.class;
				break;
			default:
				System.out.println("Invalid finder name specified.");
				finderType = null;
				System.exit(1);
		}

		long startSeed;
		if (args.length >= 3) {
			startSeed = Long.parseLong(args[2]);
		} else {
			System.out.println("No start seed specified, picking randomly...");
			Random rnd = new Random();
			startSeed = ((long)rnd.nextInt(1<<16) << 32) + rnd.nextInt();
		}

		int radius;
		if (args.length >= 2) {
			radius = Integer.parseInt(args[1]);
		} else {
			System.out.println("No radius specified, using default...");
			radius = 4;
		}


		if (args.length >= 5) {
			int threadNumber = Integer.parseInt(args[3]);
			int maxThreads = Integer.parseInt(args[4]);
			try {
				SeedFinder finder;
				try {
					finder = (SeedFinder)finderType.asSubclass(SeedFinder.class).getConstructor(long.class, int.class, int.class, int.class).newInstance(startSeed, radius, threadNumber, maxThreads);
				} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
					throw new RuntimeException(ex);
				}
				finder.start(); 
			} catch (RuntimeException ex) {
				throw ex;
			}
		} else {

			int threads = Runtime.getRuntime().availableProcessors();
	
			System.out.println("Finder: " + finderType.getName() + "...");
			try {
				for (int i=0; i < threads; i++) 
				{	
					ProcessBuilder pb = new ProcessBuilder(
						System.getProperty("java.home") + "/bin/java.exe",
						"-jar",
						new File(FinderMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath(),
						finderName,
						Integer.toString(radius),
						Long.toString(startSeed),
						Integer.toString(i),
						Integer.toString(threads)
					);
					pb.inheritIO();
					pb.start();
					System.out.printf(
						"Thread %d/%d, Start seed: %d, Radius: %d regions (%d chunks, %d blocks)\n",
						 i+1, threads, startSeed, radius, radius*32, radius*32*16);
				}

				while (true) {
					Thread.sleep(Long.MAX_VALUE);
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
