SHELL := /bin/bash

path_finder.o: path_finder.c
	cc -c path_finder.c -o path_finder.o

cohesion: graph_cohesion.o
	cc graph_cohesion.o -o cohesion.out

graph_cohesion.o: graph_cohesion.c
	cc -c graph_cohesion.c -o graph_cohesion.o

file_handler: file_handler.o
	cc file_handler.o -o file_handler.out

file_handler.o: file_handler.c
	cc -c file_handler.c -o file_handler.o

file_handler_test: file_handler.o file_handler_test.o path_finder.o graph_generator.o
	cc file_handler.o file_handler_test.o path_finder.o graph_generator.o -o file_handler_test.out
	./file_handler_test.out
	diff mygraph4 mygraph

file_handler_test.o: file_handler_test.c
	cc -c file_handler_test.c -o file_handler_test.o

graph_generator: graph_generator.o
	cc graph_generator.o -o graph_generator.out

graph_generator.o: graph_generator.c
	cc -c graph_generator.c -o graph_generator.o

cohesion_test: graph_cohesion.o graph_cohesion_test.o path_finder.o
	cc graph_cohesion.o graph_cohesion_test.o path_finder.o -o cohesion_test.out
	./cohesion_test.out > cohesion_test_output.txt
	diff cohesion_test_expected.txt cohesion_test_output.txt

graph_cohesion_test.o: graph_cohesion_test.c
	cc -c graph_cohesion_test.c -o graph_cohesion_test.o

main.o: main.c
	cc -c main.c -o main.o

cgrafy: graph_cohesion.o path_finder.o graph_generator.o file_handler.o main.o
	cc graph_cohesion.o path_finder.o graph_generator.o file_handler.o main.o -o cgrafy.out