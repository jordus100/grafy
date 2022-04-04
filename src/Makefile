SHELL := /bin/bash

cohesion: graph_cohesion.o
	cc graph_cohesion.o -o cohesion.out # Runs third

graph_cohesion.o: graph_cohesion.c
	cc -c graph_cohesion.c -o graph_cohesion.o # Runs second

cohesion_test: graph_cohesion.o graph_cohesion_test.o
	cc graph_cohesion.o graph_cohesion_test.o -o cohesion_test.out
	./cohesion_test.out > cohesion_test_output.txt
	diff "cohesion_test_expected.txt" "cohesion_test_output.txt"

graph_cohesion_test.o: graph_cohesion_test.c
	cc -c graph_cohesion_test.c -o graph_cohesion_test.o