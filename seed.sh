#/bin/bash
ant

# Uncomment the ones you want to run several searches in parallel. All of these
# look for quad witch huts within ~2k of spawn, but have some other constraints
# to make them more interesting.

# All rare biomes in ~2k of spawn, spawn in a mostly-ocean area.
# java -jar dist/seedfinder.jar QuadHutExoticBiomes               0 >> exotic-biomes-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticBiomes  50000000000000 >> exotic-biomes-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticBiomes 100000000000000 >> exotic-biomes-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticBiomes 150000000000000 >> exotic-biomes-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticBiomes 200000000000000 >> exotic-biomes-200000000000000.log &
#
# Spawn in a rare biome.
# java -jar dist/seedfinder.jar QuadHutExoticSpawn                0 >>  exotic-spawn-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticSpawn   50000000000000 >>  exotic-spawn-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticSpawn  100000000000000 >>  exotic-spawn-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticSpawn  150000000000000 >>  exotic-spawn-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutExoticSpawn  200000000000000 >>  exotic-spawn-200000000000000.log &
#
# At least two woodland mansions within ~2.5k, spawn in ocean area.
# java -jar dist/seedfinder.jar QuadHutMultiMansion               0 >>       mansion-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMultiMansion  50000000000000 >>       mansion-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMultiMansion 100000000000000 >>       mansion-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMultiMansion 150000000000000 >>       mansion-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMultiMansion 200000000000000 >>       mansion-200000000000000.log &
#
# At least three woodland mansions within ~2.5k.
# java -jar dist/seedfinder.jar QuadHutThreeMansion               0 >> three-mansion-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutThreeMansion  50000000000000 >> three-mansion-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutThreeMansion 100000000000000 >> three-mansion-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutThreeMansion 150000000000000 >> three-mansion-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutThreeMansion 200000000000000 >> three-mansion-200000000000000.log &
#
# Ocean monument very close to quad huts. Inside perimeter maybe, but very rare.
# java -jar dist/seedfinder.jar QuadHutMonument                   0 >>      monument-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMonument      50000000000000 >>      monument-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMonument     100000000000000 >>      monument-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMonument     150000000000000 >>      monument-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMonument     200000000000000 >>      monument-200000000000000.log &
#
# Lots of mushroom island area around, in an ocean spawn.
# java -jar dist/seedfinder.jar QuadHutMushroom                   0 >>      mushroom-000000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMushroom      50000000000000 >>      mushroom-050000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMushroom     100000000000000 >>      mushroom-100000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMushroom     150000000000000 >>      mushroom-150000000000000.log &
# java -jar dist/seedfinder.jar QuadHutMushroom     200000000000000 >>      mushroom-200000000000000.log &
#
# Quad huts in the closest possible configuration (1/256 quad hut seeds).
# java -jar dist/seedfinder.jar ExtraCloseQuadHut                 0 >>   extra-close-000000000000000.log &
# java -jar dist/seedfinder.jar ExtraCloseQuadHut    50000000000000 >>   extra-close-050000000000000.log &
# java -jar dist/seedfinder.jar ExtraCloseQuadHut   100000000000000 >>   extra-close-100000000000000.log &
# java -jar dist/seedfinder.jar ExtraCloseQuadHut   150000000000000 >>   extra-close-150000000000000.log &
# java -jar dist/seedfinder.jar ExtraCloseQuadHut   200000000000000 >>   extra-close-200000000000000.log &
