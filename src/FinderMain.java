import java.lang.*;
import java.util.Random;
import com.scicraft.seedfinder.*;
import com.scicraft.seedfinder.finder.*;

public class FinderMain {
	public static void main(String[] args) {
		String finderName = "QuadHut";
		if (args.length < 1) {
			System.out.println("No finder specified, using default...");
			System.out.println("Available finders:");
			System.out.println("	QuadHut");
			System.out.println("	QuadHutOceanSpawn");
			System.out.println("	QuadHutExoticBiomes");
			System.out.println("	QuadHutMultiMansion");
			System.out.println("	QuadHutThreeMansion");
			System.out.println("	QuadHutExoticSpawn");
			System.out.println("	ExtraCloseQuadHut");
			System.out.println("	QuadHutMonument");
		} else {
			finderName = args[0];
		}

		SeedFinder finder;
		switch (finderName) {
			case "QuadHut":
				finder = new QuadHutFinder();
				break;
			case "QuadHutOceanSpawn":
				finder = new QuadHutOceanSpawnFinder();
				break;
			case "QuadHutExoticBiomes":
				finder = new QuadHutExoticBiomesFinder();
				break;
			case "QuadHutMultiMansion":
				finder = new QuadHutMultiMansionFinder();
				break;
			case "QuadHutThreeMansion":
				finder = new QuadHutThreeMansionFinder();
				break;
			case "QuadHutExoticSpawn":
				finder = new QuadHutExoticSpawnFinder();
				break;
			case "ExtraCloseQuadHut":
				finder = new ExtraCloseQuadHutFinder();
				break;
			case "QuadHutMonument":
				finder = new QuadHutMonumentFinder();
				break;
			default:
				System.out.println("Invalid finder name specified.");
				finder = null;
				System.exit(1);
		}

		long startSeed;
		if (args.length >= 2) {
			startSeed = Long.parseLong(args[1]);
		} else {
			System.out.println("No start seed specified, picking randomly...");
			Random rnd = new Random();
			startSeed = ((long)rnd.nextInt(1<<16) << 32) + rnd.nextInt();
		}

		int radius;
		if (args.length >= 3) {
			radius = Integer.parseInt(args[2]);
		} else {
			System.out.println("No radius specified, using default...");
			radius = 4;
		}

		System.out.println("Finder: " + finderName + "...");
		System.out.printf(
				"Start seed: %d, Radius: %d regions (%d chunks, %d blocks)\n",
				startSeed, radius, radius*32, radius*32*16);
		System.out.println("========================================================================");
		finder.findSeeds(startSeed, radius);
	}
}
