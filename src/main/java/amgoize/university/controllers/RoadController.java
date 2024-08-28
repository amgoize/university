package amgoize.university.controllers;

import amgoize.university.road.Edge;
import amgoize.university.road.RunDinicAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/road")
public class RoadController {

    @GetMapping("/graph")
    public String showGraphPage(Model model) {
        return "road/graph";
    }

    @PostMapping("/calculateMaxFlow")
    public ResponseEntity<Integer> calculateMaxFlow(@RequestBody Map<String, Object> payload) {
        try {
            // Разбор входных данных из payload
            Object edgesObj = payload.get("edges");
            if (edgesObj instanceof List) {
                List<?> edgesList = (List<?>) edgesObj;
                if (!edgesList.isEmpty() && edgesList.get(0) instanceof Map) {
                    List<Map<String, Integer>> edges = (List<Map<String, Integer>>) edgesObj;
                    int numVertices = Integer.parseInt((String) payload.get("numVertices"));
                    int source = Integer.parseInt((String) payload.get("source"));
                    int sink = Integer.parseInt((String) payload.get("sink"));

                    // Преобразование ребер в объекты Edge
                    List<Edge> edgeList = new ArrayList<>();
                    for (Map<String, Integer> edge : edges) {
                        int from = edge.get("from");
                        int to = edge.get("to");
                        int capacity = edge.get("capacity");
                        edgeList.add(new Edge(from, to, capacity));
                    }

                    // Создание графа и вычисление максимального потока
                    RunDinicAlgorithm dinic = new RunDinicAlgorithm(numVertices ,edgeList);
                    int maxFlow = dinic.calculateMaxFlow(source, sink);

                    return ResponseEntity.ok(maxFlow);
                } else {
                    // Обработка ошибки: список ребер пуст или содержит объекты другого типа
                    System.err.println("Error: Edges list is empty or contains objects of a different type.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            } else {
                // Обработка ошибки: объект не является списком
                System.err.println("Error: Edges object is not a list.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
