#/bin/bash
ant
java -jar dist/seedfinder.jar QuadHut               0 > seeds-000000000000000.log &
java -jar dist/seedfinder.jar QuadHut  20000000000000 > seeds-020000000000000.log &
java -jar dist/seedfinder.jar QuadHut  40000000000000 > seeds-040000000000000.log &
java -jar dist/seedfinder.jar QuadHut  60000000000000 > seeds-060000000000000.log &
java -jar dist/seedfinder.jar QuadHut  80000000000000 > seeds-080000000000000.log &
java -jar dist/seedfinder.jar QuadHut 100000000000000 > seeds-100000000000000.log &
