import com.example.jgrafy.Graph;
import com.example.jgrafy.GraphAnalyzer;
import com.example.jgrafy.GraphGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GraphAnalyzerTest {
    @Test
    void testBFS(){
        try {
            Graph graph = GraphGenerator.readGraphFromFile("mygraph");
            assertTrue(GraphAnalyzer.checkCohesion(graph).cohesive);
        }
        catch (Exception e){
            fail();
        }
    }
}
