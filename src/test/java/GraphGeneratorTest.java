import com.example.jgrafy.GraphGenerator;
import org.junit.jupiter.api.Test;
import com.example.jgrafy.Graph;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.fail;


public class GraphGeneratorTest {
    @Test
    void testReadFromFile(){
        try {
            Graph graph = GraphGenerator.readGraphFromFile(new File("mygraph2"));
            graph.getNumOfRows();
            System.out.println(graph.getNumOfColumns());
            assertThat(graph.getNumOfRows()).isEqualTo(5);
            assertThat(graph.getNumOfColumns()).isEqualTo(5);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    void testGraphGenerator(){
        Graph graph = GraphGenerator.generateCohesiveGraph(10, 10);
        assertThat(graph.getNumOfRows()).isEqualTo(10);
        assertThat(graph.getNumOfColumns()).isEqualTo(10);
        graph = GraphGenerator.generateCohesiveRandomGraph(10, 10);
        assertThat(graph.getNumOfRows()).isEqualTo(10);
        assertThat(graph.getNumOfColumns()).isEqualTo(10);
        graph = GraphGenerator.generateRandomGraph(10, 10);
        assertThat(graph.getNumOfRows()).isEqualTo(10);
        assertThat(graph.getNumOfColumns()).isEqualTo(10);
    }

}
